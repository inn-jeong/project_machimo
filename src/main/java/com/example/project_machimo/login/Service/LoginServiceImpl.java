package com.example.project_machimo.login.Service;

import com.example.project_machimo.admin.dto.UsersDto;
import com.example.project_machimo.login.Dao.LoginDao;
import com.example.project_machimo.login.Dto.MemDto;
import com.example.project_machimo.login.Dto.MemberRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("LoginService")
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public MemDto findMem(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        return dao.findMem(param);
    }

    @Override
    public int loginYn(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        int re;
        String mem_pwd = param.get("u_password");
        MemDto dto = dao.findMem(param);

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
    public void memberInsert(HashMap<String, String> param) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        dao.memberInsert(param);
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
    public HashMap<String, String> switchMem(MemberRequestDto requestDto) {
        HashMap<String,String> param = new HashMap<>();
        param.put("u_id",requestDto.getId());
        param.put("u_password",requestDto.getPassword());
        param.put("u_name",requestDto.getName());
        param.put("u_jumin",requestDto.getJumin());
        param.put("u_nickname",requestDto.getNickname());
        param.put("u_phone",requestDto.getPhoneNumber());
        param.put("u_email",requestDto.getEmail());

        return param;
    }

//    @Override
//    public MemDto switchMem(MemberRequestDto requestDto) {
//        MemDto memDto = new MemDto();
//
//        memDto.setU_password(requestDto.getPassword());
//        memDto.setU_name(requestDto.getName());
//        memDto.setU_address(requestDto.getEmail());
//        memDto.setU_jumin(requestDto.getJumin());
//        memDto.setU_phone(requestDto.getPhoneNumber());
//        memDto.setU_jumin(requestDto.getJumin());
//        memDto.setU_address(requestDto.getAddress());
//
//        return memDto;
//    }
}
