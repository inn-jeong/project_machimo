package com.example.project_machimo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler implements ErrorController{


    @GetMapping("/error")
    public String handleError(HttpServletRequest request){

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);


        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404error";
            } else {
                return "error";
            }
        }

        return "error/error";
    }    }



