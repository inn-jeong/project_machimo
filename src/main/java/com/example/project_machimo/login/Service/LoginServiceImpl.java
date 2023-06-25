package com.example.project_machimo.login.Service;

import com.example.project_machimo.login.Dao.LoginDao;
import com.example.project_machimo.login.Dto.MailDto;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.login.Dto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("LoginService")
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SqlSession sqlSession;

    //메일 전송 서비스를 위한 변수와 생성자 추가
    private final JavaMailSender mailSender;

    public LoginServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    public UsersDto findUser(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findUser(param);
    }

    @Override
    public int loginYn(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        int re;
        String mem_pwd = param.get("u_password");
        UsersDto dto = dao.findUser(param);

        if(dto != null) {//isEmpty() 도 가능
            String db_mem_pwd = dto.getU_password();
            log.info("@# service db_mem_uid ===>"+db_mem_pwd);

            if(mem_pwd.equals(db_mem_pwd)) {
                re=1;
            }else {
                re=0;
            }
        }else {
            re=-1;
        }
        return re;
    }

    @Override
    public void userInsert(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        dao.userInsert(param);
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Override
    public HashMap<String, String> switchRequestToUser(UserRequestDto requestDto) {
        HashMap<String,String> param = new HashMap<>();
        param.put("u_id",requestDto.getU_id());
        param.put("u_password",requestDto.getU_password());
        param.put("u_name",requestDto.getU_name());
        param.put("u_jumin",requestDto.getU_jumin());
        param.put("u_nickname",requestDto.getU_nickname());
        param.put("u_phone",requestDto.getU_phone());
        param.put("u_email",requestDto.getU_email());
        param.put("u_address",requestDto.getU_address());
        param.put("u_address_sub",requestDto.getU_address_sub());
        param.put("u_social",requestDto.getU_social());
        log.info("@# param u_id===>"+param.get("u_id"));
        log.info("@# param u_pw===>"+param.get("u_password"));
        log.info("@# param u_na===>"+param.get("u_name"));
        log.info("@# param u_ju===>"+param.get("u_jumin"));
        log.info("@# param u_ni===>"+param.get("u_nickname"));
        log.info("@# param u_ph===>"+param.get("u_phone"));
        log.info("@# param u_em===>"+param.get("u_email"));
        log.info("@# param u_add===>"+param.get("u_address"));
        log.info("@# param u_social===>"+param.get("u_social"));
        log.info("@# param u_add2===>"+param.get("u_address_sub"));
        return param;
    }

    @Override
    public UserRequestDto convertNaver(UsersDto usersDto) {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setU_social(usersDto.getU_social());
        requestDto.setU_name(usersDto.getU_name());
        requestDto.setU_email(usersDto.getU_email());
        requestDto.setU_phone(usersDto.getU_phone());
        requestDto.setU_jumin(usersDto.getU_jumin());
        return requestDto;
    }

    @Override
    public UserRequestDto convertKakao(UsersDto usersDto) {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setU_social(usersDto.getU_social());
        requestDto.setU_email(usersDto.getU_email());
        requestDto.setU_nickname(usersDto.getU_nickname());
        return requestDto;
    }

        @Override
    public UsersDto findUserId(String u_social) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        log.info("@# dao u_social ===> "+u_social);
        UsersDto dto = dao.findUserId(u_social);
        if(dto != null) {
            log.info("@# dao dto.u_social ===> " + dto.getU_social());
            log.info("@# dao dto.u_name ===> " + dto.getU_name());
        }else{
            log.info("@# dao dto is null ===============");
        }

//        return dao.findUserId(u_social);
        return dto;
    }

    @Override
    public UsersDto findUserPhone(String u_phone) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findMemPhone(u_phone);
    }

    @Override
    public UsersDto findUserEmail(String u_email) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findMemEmail(u_email);
    }

    @Override
    public MailDto createMailAndChangePassword(String userEmail) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle("마취모 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 마취모 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + str + " 입니다. \n" + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePassword(userEmail,str);
        return dto;
    }

    @Override
    public void updatePassword(String userEmail, String password) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        String userId = findUserEmail(userEmail).getU_id();
        dao.updatePassword(userId,password);
    }

    @Override
    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        log.info("@# tmpPWD =====> "+str);
        return str;
    }

    @Override
    public void mailSend(MailDto mailDto) {
        log.info("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom("innjeong429@gmail.com");
        message.setReplyTo("innjeong429@gmail.com");
        log.info("message"+message);
        mailSender.send(message);
    }
}
