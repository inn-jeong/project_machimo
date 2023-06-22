package com.example.project_machimo.login.Controller;

import com.example.project_machimo.login.Dao.LoginDao;
import com.example.project_machimo.login.Dto.KakaoDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/loginT")
@Controller
public class LoginController {
    @Autowired
    private LoginService service;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        log.info("login");

//        HttpSession session = request.getSession();
        String login_try = request.getParameter("login_try");
        //로그인 화면 진입 시 실패 후 진입인지 처음 진입인지 알기 위한 처리

        if (login_try != null) {
            if (login_try.equals("yes")) {
                model.addAttribute("login_try", "yes");
            }
        }

        return "login/loginTest";
//        return "login/main";
    }

    @RequestMapping("/login_process")
    public String login_process(@RequestParam HashMap<String, String> param,HttpServletRequest request, Model model) {
        log.info("@# login_process start");
//        HttpSession session = request.getSession();

        int re = service.loginYn(param);
        String str;
        if(re == 1) {
            str = "redirect:/loginT/login_ok";
//            session.setAttribute("login_ok", "yes");
        }else {
            str = "redirect:/loginT/login?login_try=yes";
//            session.setAttribute("login_ok", "no");
        }
        log.info("@# login_process end");
        return str;
    }

    @RequestMapping("/login_ok")
    public String login_ok(Model model) {
        log.info("login_ok");
        return "login/login_ok";
    }


    @RequestMapping("/register_page")
    public String register_jsp(@SessionAttribute(name = "naverMember",required = false) MemDto memDto, HttpServletRequest request, Model model) {
        String naverMem = request.getParameter("naverMem");
        MemberRequestDto requestDto = new MemberRequestDto();
        if(naverMem != null){
            if(naverMem.equals("yes")){
                requestDto = service.switchMemToRequest(memDto);
                model.addAttribute("naverMem","yes");
            }
        }
        model.addAttribute("userDto",requestDto);
        return "login/registerTest";
    }

    @RequestMapping("/register")
    public String register(@RequestParam HashMap<String, String> param,Model model) {
        log.info("@# id ===>"+param.get("u_id"));
        service.memberInsert(param);
        return "redirect:/loginT/login";
    }

    @RequestMapping("/callback")
    public String naverCallback(Model model){
        return "login/callback";
    }

    @RequestMapping("/naverLogin_ok")
    public String naverLogin(@SessionAttribute(name = "naverMember",required = false) MemDto memDto, Model model){
        model.addAttribute("naverMem",memDto);
        log.info("@# naver_ok phone ===>"+memDto.getU_phone());
        MemDto dto = service.findMemPhone(memDto.getU_phone());
        String u_phone = null;
        if(dto != null){
            u_phone = dto.getU_phone();
        }
        if(u_phone == null){
            return "redirect:/loginT/register_page?naverMem=yes";
        }

        return "login/login_ok";
    }
    @RequestMapping("/naverLogin_process")
//    public String naverLogin_ok(@RequestParam HashMap<String,String> param, HttpServletRequest request, Model model) {
    public String naverLogin_ok(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        log.info("naverLogin_ok");
        log.info("@# naverLogin_ok name ===> "+request.getParameter("name"));
        log.info("@# naverLogin_ok birthyear ===> "+request.getParameter("birthyear"));
//        model.addAttribute("name", request.getParameter("name"));
//        model.addAttribute("email", request.getParameter("email"));
//        model.addAttribute("phone", request.getParameter("phone"));
//        model.addAttribute("birthday", request.getParameter("birthday"));
//        model.addAttribute("birthyear", request.getParameter("birthyear"));
//        return "redirect:/loginT/login_ok";

        MemDto dto = new MemDto();

        //생년웛일 조합
        String birthday = request.getParameter("birthday");
        String birthyear = request.getParameter("birthyear");
        birthday = birthday.replace("-","");
        birthyear = birthyear.substring(2);
        String u_jumin = birthyear+birthday;
        log.info("@# naverLogin_ok u_jumin ===> "+u_jumin);

        //객체 조립하여 세션에 개인정보 저장
        dto.setU_name(request.getParameter("name"));
        dto.setU_email(request.getParameter("email"));
        dto.setU_jumin(u_jumin);
        dto.setU_phone(request.getParameter("phone"));
        session.setAttribute("naverMember",dto);

        return "login/childWin";
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
            return "login/registerTest";
        }
        log.info("@# register success=============");
        service.memberInsert(service.switchRequestToMem(userDto));
        return "redirect:/loginT/login";
    }

    @RequestMapping("/checkMember")
    public String checkMamber(@RequestParam("u_id") String u_id, Model model){
        log.info("@# checkMember start====");
        HashMap<String,String> param = new HashMap<>();
        String result;
        log.info("@# checkMember u_id ====>"+u_id);
        param.put("u_id",u_id);

        MemDto member = service.findMem(param);
        if(member != null){
            result = "login/denined";
        }else{
            result = "login/confirm";
        }
        log.info("@# checkMember end====");
        return result;
    }

    /**
     * 카카오 로그인 API
     * [GET] /app/login/kakao
     * @return BaseResponse<String>
     */
    ////////////////////////////////////////카카오/////////////////////////////////////////////
//    @ResponseBody
//    @RequestMapping("/kakao")
//    public BaseResponse<KakaoDto> kakaoLogin(@RequestParam(required = false) String code) {
//        try {
//            // URL에 포함된 code를 이용하여 액세스 토큰 발급
//            String accessToken = service.getKakaoAccessToken(code);
//            System.out.println(accessToken);
//
//            // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
//            HashMap<String, Object> userInfo = service.getUserInfo(accessToken);
//            System.out.println("login Controller : " + userInfo);
//
//            PostLoginRes postLoginRes = null;
//
//            // 만일, DB에 해당 email을 가지는 유저가 없으면 회원가입 시키고 유저 식별자와 JWT 반환
//            // 현재 카카오 유저의 전화번호를 받아올 권한이 없어서 테스트를 하지 못함.
//            if(loginProvider.checkEmail(String.valueOf(userInfo.get("email"))) == 0) {
//                //PostLoginRes postLoginRes = 해당 서비스;
//                return new BaseResponse<>(postLoginRes);
//            } else {
//                // 아니면 기존 유저의 로그인으로 판단하고 유저 식별자와 JWT 반환
//                postLoginRes = loginProvider.getUserInfo(String.valueOf(userInfo.get("email")));
//                return new BaseResponse<>(postLoginRes);
//            }
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
}
