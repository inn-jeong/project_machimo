package com.example.project_machimo.login.Controller;

import com.example.project_machimo.login.Dto.MemDto;
import com.example.project_machimo.login.Dto.MemberRequestDto;
import com.example.project_machimo.login.Service.LoginService;
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
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/loginT")
@Controller
public class LoginController {
    @Autowired
    private LoginService service;

    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");
//        return "loginTest";
        return "main";
    }

    @RequestMapping("/login-process")
    public String login_process(@RequestParam HashMap<String, String> param,Model model) {
        log.info("@# login_process start");
        int re = service.loginYn(param);
        String str = "";
        if(re == 1) {
            str = "redirect:login_ok";
        }else {
            str = "redirect:login";
        }
        log.info("@# login_process end");
        return str;
    }

    @RequestMapping("/login_ok")
    public String login_ok(Model model) {
        log.info("login_ok");
        return "login_ok";
    }


    @RequestMapping("/register_page")
    public String register_jsp(Model model) {
        model.addAttribute("userDto",new MemberRequestDto());
        return "registerTest";
    }

    @RequestMapping("/register")
    public String register(@RequestParam HashMap<String, String> param,Model model) {
        log.info("@# id ===>"+param.get("u_id"));
        service.memberInsert(param);
        return "redirect:login";
    }

    @RequestMapping("/callback")
    public String naverCallback(Model model){
        return "callback";
    }

    @RequestMapping("/naverLogin_ok")
    public String naverLogin(@SessionAttribute(name = "naverMember",required = false) MemDto memDto, Model model){
        model.addAttribute("naverMem",memDto);
        return "login_ok";
    }
    @RequestMapping("/naverLogin-process")
    public String naverLogin_ok(@RequestParam HashMap<String,String> param, HttpServletRequest request, Model model) {
        log.info("naverLogin_ok");
        log.info("@# naverLogin_ok name ===> "+param.get("name"));
        log.info("@# naverLogin_ok birthyear ===> "+param.get("birthyear"));
        model.addAttribute("name", param.get("name"));
        model.addAttribute("email", param.get("email"));
        model.addAttribute("phone", param.get("phone"));
        model.addAttribute("birthday", param.get("birthday"));
        model.addAttribute("birthyear", param.get("birthyear"));
//        return "redirect:/loginT/login_ok";
        HttpSession session = request.getSession();
        MemDto dto = new MemDto();

        String birthday = param.get("birthday");
        String birthyear = param.get("birthyear");
        birthday = birthday.replace("-","");
        birthyear = birthyear.substring(2);
        String u_jumin = birthyear+birthday;
        log.info("@# naverLogin_ok u_jumin ===> "+u_jumin);

        dto.setU_name(param.get("name"));
        dto.setU_email(param.get("email"));
        dto.setU_jumin(u_jumin);
        dto.setU_phone(param.get("phone"));
        session.setAttribute("naverMember",dto);

        return "childWin";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberRequestDto userDto, Errors errors, Model model) {

        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", userDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            log.info("@# message ===>"+model.getAttribute("valid_u_id"));
            /* 회원가입 페이지로 다시 리턴 */
            return "registerTest";
        }
        log.info("@# register success=============");
        service.memberInsert(service.switchMem(userDto));
        return "redirect:/loginT/login";
    }
}
