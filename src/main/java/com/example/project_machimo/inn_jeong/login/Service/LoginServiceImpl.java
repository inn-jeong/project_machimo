package com.example.project_machimo.inn_jeong.login.Service;

import com.example.project_machimo.inn_jeong.login.Dao.LoginDao;
import com.example.project_machimo.inn_jeong.login.Dto.MailDto;
import com.example.project_machimo.inn_jeong.login.Dto.UserRequestDto;
import com.example.project_machimo.inn_jeong.login.Dto.UserSuspension;
import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
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
        String mem_pwd = param.get("uPassword");
        UsersDto dto = dao.findUser(param);

        if(dto != null) {//isEmpty() 도 가능
            String db_mem_pwd = dto.getUPassword();
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
    public void socialUserInsert(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        dao.socialUserInsert(param);
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
        param.put("uId",requestDto.getUId());
        param.put("uPassword",requestDto.getUPassword());
        param.put("uName",requestDto.getUName());
        param.put("uJumin",requestDto.getUJumin());
        param.put("uNickname",requestDto.getUNickname());
        param.put("uPhone",requestDto.getUPhone());
        param.put("uEmail",requestDto.getUEmail());
        param.put("uAddrPostcode",requestDto.getUAddrPostcode());
        param.put("uAddress",requestDto.getUAddress());
        param.put("uAddressSub",requestDto.getUAddressSub());
        param.put("uSocial",requestDto.getUSocial());
        log.info("@# param u_id===>"+param.get("uId"));
        log.info("@# param u_pw===>"+param.get("uPassword"));
        log.info("@# param u_na===>"+param.get("uName"));
        log.info("@# param u_ju===>"+param.get("uJumin"));
        log.info("@# param u_ni===>"+param.get("uNickname"));
        log.info("@# param u_ph===>"+param.get("uPhone"));
        log.info("@# param u_em===>"+param.get("uEmail"));
        log.info("@# param u_add===>"+param.get("uAddress"));
        log.info("@# param u_social===>"+param.get("uSocial"));
        log.info("@# param u_add2===>"+param.get("uAddressSub"));
        return param;
    }

    @Override
    public UserRequestDto convertNaver(UsersDto usersDto) {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUSocial(usersDto.getUSocial());
        requestDto.setUName(usersDto.getUName());
        requestDto.setUEmail(usersDto.getUEmail());
        requestDto.setUPhone(usersDto.getUPhone());
        requestDto.setUJumin(usersDto.getUJumin());
        return requestDto;
    }

    @Override
    public UserRequestDto convertKakao(UsersDto usersDto) {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUSocial(usersDto.getUSocial());
        requestDto.setUEmail(usersDto.getUEmail());
        requestDto.setUNickname(usersDto.getUNickname());
        return requestDto;
    }

        @Override
    public UsersDto findUserId(String uSocial) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        log.info("@# dao u_social ===> "+uSocial);
        UsersDto dto = dao.findUserId(uSocial);
        if(dto != null) {
            log.info("@# dao dto.u_social ===> " + dto.getUSocial());
            log.info("@# dao dto.u_name ===> " + dto.getUName());
        }else{
            log.info("@# dao dto is null ===============");
        }

//        return dao.findUserId(u_social);
        return dto;
    }

    @Override
    public UsersDto findUserPhone(String uPhone) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findMemPhone(uPhone);
    }

    @Override
    public UsersDto findUserEmail(String uEmail) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findMemEmail(uEmail);
    }

    @Override
    public MailDto createMailAndChangePassword(String userEmail) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle("마취모 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. \n마취모 임시비밀번호 안내 관련 이메일 입니다.\n" + " 회원님의 임시 비밀번호는  \" "
                + str + " \"  입니다. \n\n" + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePassword(userEmail,str);
        return dto;
    }

    @Override
    public void updatePassword(String userEmail, String password) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        String userId = findUserEmail(userEmail).getUId();
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

    @Override
    public UserSuspension checkBlur(Integer userId) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        int re = dao.updateBlur(userId);
        if(re == 1){
            dao.initialReport(userId);
        }
        return dao.checkBlur(userId);
    }
}