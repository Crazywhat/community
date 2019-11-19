package com.jockey.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController{

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String,Object> model = new HashMap<>();

        model.put("errorCode",status.value());
        model.put("errorReason",status.getReasonPhrase());

        if(status.is4xxClientError()){
            model.put("errorMsg","小伙子，请求姿势不对！应该先这样，再这样，最后这样，明白了吗？");
        }

        if(status.is5xxServerError()){
            model.put("errorMsg","服务器炸了！！！真的，没骗你（认真脸）");
        }

        return new ModelAndView("error", model);
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("errorCode",status.value());
        body.put("errorReason",status.getReasonPhrase());

        if(status.is4xxClientError()){
            body.put("errorMsg","小伙子，你这...请求姿势不对呀！你应该先这样，然后这样，最后再这样，明白了吗？");
        }

        if(status.is5xxServerError()){
            body.put("errorMsg","服务器炸了！！！真的，没骗你（认真脸）");
        }

        return new ResponseEntity<>(body, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @Override
    public String getErrorPath() {
        return null;
    }


}
