package com.example.project_machimo.mypage.Controller;

import com.example.project_machimo.basket.Service.BasketService;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Dto.SalesItem;
import com.example.project_machimo.mypage.Service.MypageService;
import com.example.project_machimo.mypage.Dto.PurchaseItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/mypage")
@Slf4j
@Controller
public class MypageController {
    @Autowired
    private MypageService service;

    @RequestMapping("/mypage_page")
    public String mypage(HttpSession session, Model model){
        UsersDto user = (UsersDto)session.getAttribute("user");
        if(user == null){
            return "redirect:/loginT/login?login_try=no";
        }
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
}
