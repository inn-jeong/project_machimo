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
    public HashMap<String, String> switchRequestToMem(MemberRequestDto requestDto) {
        HashMap<String,String> param = new HashMap<>();
        param.put("u_id",requestDto.getU_id());
        param.put("u_password",requestDto.getU_password());
        param.put("u_name",requestDto.getU_name());
        param.put("u_jumin",requestDto.getU_jumin());
        param.put("u_nickname",requestDto.getU_nickname());
        param.put("u_phone",requestDto.getU_phone());
        param.put("u_email",requestDto.getU_email());
        param.put("u_address",requestDto.getU_address());
        log.info("@# param u_id===>"+param.get("u_id"));
        log.info("@# param u_pw===>"+param.get("u_password"));
        log.info("@# param u_na===>"+param.get("u_name"));
        log.info("@# param u_ju===>"+param.get("u_jumin"));
        log.info("@# param u_ni===>"+param.get("u_nickname"));
        log.info("@# param u_ph===>"+param.get("u_phone"));
        log.info("@# param u_em===>"+param.get("u_email"));
        log.info("@# param u_add===>"+param.get("u_address"));
        return param;
    }

    @Override
    public MemberRequestDto switchMemToRequest(MemDto memDto) {
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setU_name(memDto.getU_name());
        requestDto.setU_email(memDto.getU_email());
        requestDto.setU_phone(memDto.getU_phone());
        requestDto.setU_jumin(memDto.getU_jumin());
        return requestDto;
    }

    @Override
    public MemDto findMemPhone(String u_phone) {
        LoginDao dao = sqlSession.getMapper(LoginDao.class);
        dao.findMemPhone(u_phone);
        return dao.findMemPhone(u_phone);
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
