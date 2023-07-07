package com.example.project_machimo.gyuha;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler implements ErrorController {


    //에러가 발생시 아래 화면 보여줌
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);


        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            //404에러시 404에러 html페이지를 반ㄴ환
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404error";
            } else {
                //그외에 에러는 아래와 같이 반환함
                return "error";
            }
        }

        return "error/error";
    }
}



