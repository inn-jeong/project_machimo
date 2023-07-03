package com.example.project_machimo.login.Controller;

import com.example.project_machimo.login.Dto.MailDto;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.login.Dto.UserRequestDto;
import com.example.project_machimo.login.Service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestMapping("/loginT")
@Controller
public class LoginController {
    @Autowired
    private LoginService service;

    //로그인 화면
    //1. 로그인 시도 후 진입 시 알림
    //2. 회원가입 성공 후 진입 시 알림
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        log.info("login");

//        HttpSession session = request.getSession();
        String login_try = request.getParameter("login_try");
        String register = request.getParameter("register");
        //로그인 화면 진입 시 실패 후 진입인지 처음 진입인지 알기 위한 처리
        if (login_try != null) {
            if (login_try.equals("yes")) {
                log.info("@# login_try===>"+login_try);
                model.addAttribute("login_try", "yes");
            }else if (login_try.equals("no")){
                model.addAttribute("login_try", "no");
            }
        }
        if (register != null) {
            if (register.equals("ok")) {
                log.info("@# register===>"+register);
                model.addAttribute("register", "ok");
            }
        }

//        return "login/loginTest2";
        return "login/loginTest";
//        return "login/main";
    }

    //로그인 기능 수행
    @RequestMapping("/login_process")
    public String login_process(@RequestParam HashMap<String, String> param,HttpServletRequest request, Model model) {
        log.info("@# login_process start");
        HttpSession session = request.getSession();

        int re = service.loginYn(param);
        String str;

        if(re == 1) { //로그인 성공시 메인 페이지로 이동
            UsersDto dto = service.findUser(param);
            log.info("@# login_process user set ===>"+dto);
            session.setAttribute("user",dto);
            str = "redirect:/loginT/login_ok";
        }else { //로그인 실패시 다시 로그인 화면으로 이동, 알림
            str = "redirect:/loginT/login?login_try=yes";
        }
        log.info("@# login_process end");
        return str;
    }

    //로그인 성공시 화면(메인화면으로 수정해야 함)
    @RequestMapping("/login_ok")
    public String login_ok(Model model) {
        log.info("login_ok");
        return "login/login_ok";
    }

    @RequestMapping("/register_page")
    public String register_jsp(@SessionAttribute(value ="naverUser" ,required = false) UsersDto naverUser,
                               @SessionAttribute(value ="kakaoUser" ,required = false) UsersDto kakaoUser,
                               HttpServletRequest request, Model model) {
        String naverMem = request.getParameter("naverMem");
        String kakaoMem = request.getParameter("kakaoMem");
//        log.info("@# register mem u_email ===>" + kakaoUser.getU_email());

        //유효성 검사를 위한 Dto 생성
        UserRequestDto requestDto = new UserRequestDto();

        //네이버 로그인으로 회원가입 폼 진입시
        if(naverMem != null ){
            if(naverMem.equals("yes")){
                requestDto = service.convertNaver(naverUser);
                //model에 네이버 개인정보 저장 후 회원가입 폼으로 넘어감
                model.addAttribute("naverMem","yes");
            }
        }
        //카카오 로그인으로 회원가입 폼 진입시
        if(kakaoMem != null ){
            if(kakaoMem.equals("yes")){
                //model에 카카오 개인정보 저장 후 회원가입 폼으로 넘어감(그래봤자 이메일과 닉네임 2개..)
                log.info("@# kakaoMem u_email===>"+kakaoUser.getUEmail());
                requestDto = service.convertKakao(kakaoUser);
                model.addAttribute("kakaoMem","yes");
            }
        }
        model.addAttribute("userDto",requestDto);
        return "login/registerTest";
    }

    //회원가입 폼에서 유효성 검사를 마치고 넘어온 정보들을 DB에 insert
    @RequestMapping("/register")
    public String register(@RequestParam HashMap<String, String> param,HttpSession session,Model model) {
        log.info("@# id ===>"+param.get("uId"));
        log.info("@# register naverUser ====>"+session.getAttribute("naverUser"));
        if(session.getAttribute("naverUser") == null && session.getAttribute("kakaoUser") == null){
            service.userInsert(param);
        }else{
            service.socialUserInsert(param);
        }
        //logion 페이지에 register 값을 넘김으로 회원가입 후 넘어감을 알림
        return "redirect:/loginT/login?register=ok";
    }

    //네이버 callback 처리
    @RequestMapping("/callback")
    public String naverCallback(Model model){
        return "login/callback";
    }

    @RequestMapping("/naverLogin_ok")
    public String naverLogin(@SessionAttribute(name = "naverUser",required = false) UsersDto usersDto,HttpServletRequest request, Model model){
        String page;
        HttpSession session = request.getSession();
        model.addAttribute("naverMem", usersDto);
        log.info("@# naver_ok u_social ===>"+ usersDto.getUSocial());
        UsersDto dto = service.findUserId(usersDto.getUSocial());
        if(dto == null){
            page = "redirect:/loginT/register_page?naverMem=yes";
        }else{
            session.setAttribute("user",dto);
            page = "login/login_ok";
        }

        return page;
    }

    @RequestMapping("/naverLogin_process")
//    public String naverLogin_ok(@RequestParam HashMap<String,String> param, HttpServletRequest request, Model model) {
    public String naverLogin_ok(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        log.info("naverLogin_ok");
        log.info("@# naverLogin_ok id ===> "+request.getParameter("id"));
        log.info("@# naverLogin_ok name ===> "+request.getParameter("name"));
        log.info("@# naverLogin_ok birthyear ===> "+request.getParameter("birthyear"));

        UsersDto dto = new UsersDto();

        //생년웛일 조합
        String birthday = request.getParameter("birthday");
        String birthyear = request.getParameter("birthyear");
        birthday = birthday.replace("-","");
        birthyear = birthyear.substring(2);
        String uJumin = birthyear+birthday;
        log.info("@# naverLogin_ok u_jumin ===> "+uJumin);

        //객체 조립하여 세션에 개인정보 저장
        dto.setUSocial(request.getParameter("id"));
        dto.setUName(request.getParameter("name"));
        dto.setUEmail(request.getParameter("email"));
        dto.setUJumin(uJumin);
        dto.setUPhone(request.getParameter("phone"));
        session.setAttribute("naverUser",dto);
//        model.addAttribute("loginType","naver");
        return "login/childWin";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid UserRequestDto userDto, Errors errors, HttpSession session, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("userDto", userDto);
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            log.info("@# check address ===>"+userDto.getUAddress());

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            log.info("@# message ===>"+model.getAttribute("valid_u_id"));
            /* 회원가입 페이지로 다시 리턴 */
            return "login/registerTest";
        }
        //비밀번호 확인
        if(!userDto.getUPassword().equals(userDto.getUPwdCheck())){
            model.addAttribute("userDto", userDto);
            model.addAttribute("valid_uPwdCheck", "비밀번호를 확인해주세요.");
            return "login/registerTest";
        }

        //naver 유저나 kakao 유저가 아니면 u_social 값이 null이므로 다른 쿼리를 적용하기 위해 분기처리        
        if(session.getAttribute("naverUser") == null && session.getAttribute("kakaoUser") == null){
            service.userInsert(service.switchRequestToUser(userDto));
        }else{
            service.socialUserInsert(service.switchRequestToUser(userDto));
        }
//        service.userInsert(service.switchRequestToUser(userDto));
//        return "redirect:/loginT/login";
        log.info("@# register success=============");
        return "redirect:/loginT/login?register=ok";
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkMamber(@RequestParam("uId") String uId, Model model){
        log.info("@# checkUser start====");
        HashMap<String,String> param = new HashMap<>();
        String result;
        log.info("@# checkUser u_id ====>"+uId);
        param.put("uId",uId);

        UsersDto User = service.findUser(param);
        if(User != null){
            result = "denined";
        }else{
            result = "confirm";
        }
        log.info("@# checkUser end====");
        return result;
    }

    @RequestMapping("kakao/callback")
//    public String kakaoLogin(@RequestParam("code")String code,Model model){
    public String kakaoLogin(@RequestParam(value = "response",required = false)Map<String, Objects> parameters, HttpServletRequest request, Model model){
        String json = parameters.get("response").toString();

        return "login/kakaoCallback";
    }

    @RequestMapping("/kakaoLogin_process")
    @ResponseBody
//    public String naverLogin_ok(@RequestParam HashMap<String,String> param, HttpServletRequest request, Model model) {
    public String kakaoLogin_ok(@RequestBody UsersDto usersDto, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String result;
        log.info("kakaoLogin_ok");
        log.info("@# kakaoLogin_ok nickname ===> "+ usersDto.getUNickname());
        log.info("@# kakaoLogin_ok email ===> "+ usersDto.getUEmail());
        log.info("@# kakaoLogin_ok social ===> "+ usersDto.getUSocial());

        session.setAttribute("kakaoUser", usersDto);
//        model.addAttribute("loginType","kakao");
        UsersDto kakaoDto = service.findUserId(usersDto.getUSocial());
        if(kakaoDto == null){
            result= "denined";
        }else {
            result = "confirm";
        }
        return result;
    }

    @RequestMapping("/kakaoLogin_ok")
    public String kakaoLogin(@RequestParam("login_ok") String login_ok,
                             HttpServletRequest request,
                             Model model){
        String page;
        HttpSession session = request.getSession();
        UsersDto kakaoUser = (UsersDto)session.getAttribute("kakaoUser");
        if (login_ok.equals("yes")){
            page = "login/login_ok";
            session.setAttribute("user",service.findUserId(kakaoUser.getUSocial()));
        }else{
            page = "redirect:/loginT/register_page?kakaoMem=yes";
        }

        return page;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        return "login/loginTest";
    }

    @RequestMapping("/jusoPopup")
    public String jusoPopup(HttpServletRequest request, Model model){
        //request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
        //request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
        log.info("@# =========juso init============");
        String inputYn = request.getParameter("inputYn");
        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");

        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String addrDetail = request.getParameter("addrDetail");
        String admCd    = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn  = request.getParameter("bdMgtSn");
        /** API 서비스 제공항목 확대 (2017.02) **/
        String detBdNmList  = request.getParameter("detBdNmList");
        String bdNm  = request.getParameter("bdNm");
        String bdKdcd  = request.getParameter("bdKdcd");
        String siNm  = request.getParameter("siNm");
        String sggNm  = request.getParameter("sggNm");
        String emdNm  = request.getParameter("emdNm");
        String liNm  = request.getParameter("liNm");
        String rn  = request.getParameter("rn");
        String udrtYn  = request.getParameter("udrtYn");
        String buldMnnm  = request.getParameter("buldMnnm");
        String buldSlno  = request.getParameter("buldSlno");
        String mtYn  = request.getParameter("mtYn");
        String lnbrMnnm  = request.getParameter("lnbrMnnm");
        String lnbrSlno  = request.getParameter("lnbrSlno");
        String emdNo  = request.getParameter("emdNo");

        log.info("@# juso input ===> "+inputYn);
        log.info("@# juso part1 ===> "+roadAddrPart1);

        model.addAttribute("inputYn",inputYn);
        model.addAttribute("roadAddrPart1",roadAddrPart1);
        model.addAttribute("addrDetail",addrDetail);
        model.addAttribute("zipNo",zipNo);


        return "login/jusoPopup";
    }

    @RequestMapping("/findPassword_page")
    public String findPassword(){
        return "login/findPassword";
    }

    @Transactional
    @RequestMapping("/sendEmail")
    public String sendEmail(@RequestParam("userEmail") String userEmail,Model model){
        MailDto dto = service.createMailAndChangePassword(userEmail);
        service.mailSend(dto);
        model.addAttribute("userEmail",userEmail);
        return "login/childWin";
    }

    @RequestMapping("/emailDuplication")
    @ResponseBody
    public String emailDuplication(@RequestParam("userEmail") String userEmail){
        UsersDto dto = service.findUserEmail(userEmail);
        String result;
        if(dto != null){
            result = "confirm";
        }else{
            result = "=denined";
        }
        return result;
    }

    @RequestMapping("/findId_page")
    public String findId_page(HttpServletRequest request, Model model){

        return "login/findId";
    }
    @RequestMapping("/findId")
    @ResponseBody
    public String findId(HttpServletRequest request, Model model){
        String uEmail = request.getParameter("userEmail");
        UsersDto dto = service.findUserEmail(uEmail);
        if(dto != null){
            log.info(dto.getUId());
        }else{
            return "denined";
        }
        return dto.getUId();
    }
}
