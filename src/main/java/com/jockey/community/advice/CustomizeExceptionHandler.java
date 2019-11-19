package com.jockey.community.advice;

import com.jockey.community.exception.CustomizeException;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("com.jockey.community.controller")
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    String handleControllerException(HttpServletRequest request
            , Throwable ex
            , Model model) {
        HttpStatus status = getStatus(request);

        int errorCode = status.value();
        String errorReason = status.getReasonPhrase();


        model.addAttribute("errorCode",errorCode);
        model.addAttribute("errorReason",errorReason);
        model.addAttribute("errorMsg","报错而已，放心，问题不大...");

        if(ex instanceof CustomizeException){
            model.addAttribute("errorMsg",ex.getMessage());
        }

        return "forward:/error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
