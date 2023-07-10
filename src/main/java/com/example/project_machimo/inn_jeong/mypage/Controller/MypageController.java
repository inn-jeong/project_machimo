package com.example.project_machimo.inn_jeong.mypage.Controller;

import com.example.project_machimo.gyuha.basket.dto.BasketDTO;
import com.example.project_machimo.gyuha.basket.service.BasketService;
import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;
import com.example.project_machimo.gyuha.wishlists.service.WishListsService;
import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.inn_jeong.mypage.Dto.*;
import com.example.project_machimo.inn_jeong.mypage.Service.MypageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/mypage")
@Slf4j
@Controller
public class MypageController {
    @Autowired
    private MypageService service;
    //장바구니 추가를 위한 규하 패키지에서 가져옴
    @Autowired
    private BasketService service2;
    //관심상품 삭제를 위해 규하 패키지에서 가져옴
    @Autowired
    private WishListsService service3;

    //마이페이지 컨트롤러
    @RequestMapping("/mypage_page")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        //마이페이지 진입 전 세션 검사를 통해 보안 기능 추가
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){//로그인 상태가 아니면 로그인 페이지로 넘어감
            return "redirect:/loginT/login?login_try=no";
        }
        //개인정보 수정 후 넘어왔을 경우 수정 완료 알림을 띄우기 위해 값을 넘김
        String modify = request.getParameter("modify");
        if(modify != null) model.addAttribute("modify","success");

        //마이페이지 기본 페이지가 구매내역이기에 구매내역 조회 후 model에 담아 넘김
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<PurchaseItem> items = service.getPurchaseItems(userId);
        model.addAttribute("items",items);
        model.addAttribute("type","main"); //type은 main
        return "mypage/mypage";
    }

    //주문내역(구매내역)
    @RequestMapping("/orderlist")
    public String orderList(HttpSession session, Model model){
        //세션 검사
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        //구매내역을 조회하여 넘김
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<PurchaseItem> items = service.getPurchaseItems(userId);
        model.addAttribute("items",items);
        model.addAttribute("type","order"); //type은 order
        return "mypage/mypage";
    }

    //판매내역
    @RequestMapping("/saleslist")
    public String salesList(HttpSession session, Model model){
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<SalesItem> items = service.getSalesItems(userId);
        model.addAttribute("salesItems",items);
        model.addAttribute("type","sales");//type은 sales
        return "mypage/mypage";
    }

    //제품 삭제
    @ResponseBody
    @RequestMapping("/delete_item")
    public String deleteItem(HttpServletRequest request, Model model){
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        int deleteResult = service.deleteItem(productId);
        String result;
        if(deleteResult == 1){
            result = "success";
        }else{
            result = "fail";
        }
        return result;
    }

    //개인정보 수정
    @RequestMapping("/modify")
    public String modify(HttpSession session, Model model){
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        log.info("@# modify user ===> "+user);
        model.addAttribute("user",user);
        model.addAttribute("type","modify");

        return "mypage/mypage";
    }

    //유효성 검사
    @RequestMapping("/joinProc")
    public String joinProc(@Valid UserUpdateRequestDto userDto, Errors errors, HttpSession session, Model model) {
        String result;
        if (errors.hasErrors()) {
            model.addAttribute("type","modify");
            model.addAttribute("user", userDto);
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            log.info("@# check address ===>"+userDto.getUAddress());

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            log.info("@# message ===>"+model.getAttribute("valid_u_id"));
            /* 회원가입 페이지로 다시 리턴 */
            return "mypage/mypage";
        }
        //비밀번호 확인
        if(!userDto.getUPassword().equals(userDto.getUPwdCheck())){
            model.addAttribute("userDto", userDto);
            model.addAttribute("type","modify");
            model.addAttribute("valid_uPwdCheck", "비밀번호를 확인해주세요.");
            return "mypage/mypage";
        }
        model.addAttribute("type","main");
        int re = service.updateUser(service.switchRequestToUser(userDto));

        log.info("@# register success=============");
        if(re == 1){//정보 수정 성공시
            result = "redirect:/mypage/mypage_page?modify=success";
        }else{//정보 수정 실패시
            result = "redirect:/mypage/mypage_page?modify=fail";
        }
        //수정된 개인정보로 된 세션 새로 세팅
        UsersDto user = service.findUser(userDto.getUId());
        session.setAttribute("user",user);
        return result;
    }

    //관심상품
    @RequestMapping("/wishlist")
    public String wishList(HttpSession session,Model model){
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }

        List<WishItem> items= service.getWishItem(user.getUserId());
        model.addAttribute("type","wish");
        model.addAttribute("items",items);
        return "mypage/mypage";
    }

    //입찰상품
    @RequestMapping("/auctionlist")
    public String auctionList(HttpSession session,Model model){
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        List<AuctionItem> items= service.getAuctionItems(user.getUserId());
        model.addAttribute("type","auction");
        model.addAttribute("items",items);
        return "mypage/mypage";
    }

    //나의 문의내역
    @RequestMapping("/boardlist")
    public String boardList(HttpSession session, Model model){
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        ArrayList<BoardItemDto> boards = service.getBoards(user.getUserId());
        model.addAttribute("type","board");
        model.addAttribute("boards",boards);
        return "mypage/mypage";
    }

    //탈퇴
    @RequestMapping("/withdrawal")
    public String withdrawal(HttpSession session,Model model){
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        model.addAttribute("type","withdrawal");
        return "mypage/mypage";
    }

    //탈퇴 처리
    @RequestMapping("/check_withdrawal")
    @ResponseBody
    public String checkWithdrawal(HttpServletRequest request){
        HttpSession session = request.getSession();
        String result;
        String uPassword = ((UsersDto)session.getAttribute("user")).getUPassword();
        String inputPwd = request.getParameter("uPassword");
        String checkWith = request.getParameter("checkWith");

        if(uPassword.equals(inputPwd) && checkWith.equals("탈퇴하겠습니다")){
            result = "confirm";
        }else{
            result = "denined";
        }
        session.invalidate();
        return result;
    }

    //관심상품에서 장바구니로 이동시
    @RequestMapping("/wishTobasket")
    public String wishTobasket(@RequestParam List<Integer> productId, Model model, HttpSession session){
        UsersDto user = (UsersDto) session.getAttribute("user");
        Integer userId = user.getUserId();
        for(Integer pId:productId){
            BasketDTO basket = new BasketDTO();
            basket.setProductId(pId);
            basket.setUserId(userId);
            WishlistsDTO wishlist = new WishlistsDTO();
            wishlist.setProductId(pId);
            wishlist.setUserId(userId);
            int re = service2.addBasket(basket);
            service3.deleteWish(wishlist);
        }
        model.addAttribute("type","wishlist");
        return "redirect:/mypage/mypage_page";
    }
}
