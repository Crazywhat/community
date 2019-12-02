package com.jockey.community.controller;

import com.jockey.community.dto.FileDTO;
import com.jockey.community.exception.CustomizeErrorCode;
import com.jockey.community.exception.CustomizeException;
import com.jockey.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class FileController {

    @Autowired
    UCloudProvider uCloudProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public Object upload(HttpServletRequest request){
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("editormd-image-file");

        FileDTO fileDTO = new FileDTO();
        try{
            String url = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());

            fileDTO.setSuccess(1);
            fileDTO.setMessage("上传成功");
            fileDTO.setUrl(url);
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        return fileDTO;
    }
}
