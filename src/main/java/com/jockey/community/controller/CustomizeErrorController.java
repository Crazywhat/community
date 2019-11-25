package com.jockey.community.controller;

import com.jockey.community.dto.ResultDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController{

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

        ResultDTO resultDTO = getErrorResult(request);

        Map<String,Object> resultMap = new LinkedHashMap<>();
        resultMap.put("code", resultDTO.getCode());
        resultMap.put("message", resultDTO.getMessage());
        return new ModelAndView("error", resultMap);
    }

    @RequestMapping
    public ResultDTO error(HttpServletRequest request) {
        return getErrorResult(request);
    }

    private ResultDTO getErrorResult(HttpServletRequest request){
        ResultDTO resultDTO = null;
        if(request.getAttribute("result") == null){
            HttpStatus status = getStatus(request);

            Integer code = status.value();
            String message = status.getReasonPhrase();

            if(status.is4xxClientError()){
                message = "小伙子，请求姿势不对！应该先这样，再这样，最后这样，明白了吗？(" + message + ")";
            }

            if(status.is5xxServerError()){
                message = "服务器炸了！！！真的，没骗你（认真脸）(" + message + ")";
            }

            resultDTO = ResultDTO.errorOf(code,message);
        }else{
            resultDTO = (ResultDTO) request.getAttribute("result");
        }
        return resultDTO;
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
