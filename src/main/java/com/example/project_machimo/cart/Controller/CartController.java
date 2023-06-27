package com.example.project_machimo.cart.Controller;

import com.example.project_machimo.cart.Service.CartService;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.login.Service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;

    @RequestMapping("/page")
    public String cart(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UsersDto dto = (UsersDto) session.getAttribute("user");
        String user_id = dto.getUser_id();
        model.addAttribute("user_id",user_id);

        return "cart/cart";
    }
}
