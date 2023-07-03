package com.example.project_machimo.mypage.Controller;

import com.example.project_machimo.basket.Service.BasketService;
import com.example.project_machimo.login.Dto.UserRequestDto;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Dto.SalesItem;
import com.example.project_machimo.mypage.Dto.UserUpdateRequestDto;
import com.example.project_machimo.mypage.Service.MypageService;
import com.example.project_machimo.mypage.Dto.PurchaseItem;
import com.example.project_machimo.sqlLogTest.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/mypage")
@Slf4j
@Controller
public class MypageController {
    @Autowired
    private MypageService service;

    @RequestMapping("/mypage_page")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        String modify = request.getParameter("modify");
        if(modify != null) model.addAttribute("modify","success");
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<PurchaseItem> items = service.getPurchaseItems(userId);
        model.addAttribute("items",items);
        model.addAttribute("type","main");
        return "mypage/mypage";
    }

    @RequestMapping("/order_list")
    public String orderList(HttpSession session, Model model){
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<PurchaseItem> items = service.getPurchaseItems(userId);
        model.addAttribute("items",items);
        model.addAttribute("type","order");
        return "mypage/mypage";
    }

    @RequestMapping("/sales_list")
    public String salesList(HttpSession session, Model model){
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
        Integer userId = user.getUserId();
        log.info("@# basket user_id===>" +userId);
        List<SalesItem> items = service.getSalesItems(userId);
        model.addAttribute("salesItems",items);
        model.addAttribute("type","sales");
        return "mypage/mypage";
    }

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

    @RequestMapping("/joinProc")
//    @ResponseBody
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
//        return "redirect:/loginT/login";
        log.info("@# register success=============");
        if(re == 1){
            result = "redirect:/mypage/mypage_page?modify=success";
        }else{
            result = "redirect:/mypage/mypage_page?modify=fail";
        }
        UsersDto user = service.findUser(userDto.getUId());
        session.setAttribute("user",user);
        return result;
//        return "mypage/mypage";
    }


}
