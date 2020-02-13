package com.nzh.community.Controller;

import com.nzh.community.dto.FileDTO;
import com.nzh.community.exception.CustomizeErrorCode;
import com.nzh.community.exception.CustomizeException;
import com.nzh.community.provider.AliyunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {

    @Autowired
    private AliyunProvider aliyunProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editored-image-file");
        FileDTO fileDTO = new FileDTO();
        assert file != null;
        try {
            String url = aliyunProvider.upload(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
            fileDTO.setUrl(url);
            fileDTO.setSuccess(1);
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileDTO;
    }
}
