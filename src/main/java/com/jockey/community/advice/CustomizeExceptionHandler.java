package com.jockey.community.advice;

import com.jockey.community.dto.ResultDTO;
import com.jockey.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.jockey.community")
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    String handleControllerException(Throwable ex
                                        , Model model) {

        CustomizeException customizeException = (CustomizeException) ex;


        model.addAttribute("result", ResultDTO.errorOf(customizeException.getErrorCode(),customizeException.getMessage()));

        return "forward:/error";
    }

}
