package com.scars.controller;

import com.scars.pojo.Result;
import com.scars.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @RequestMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名为{}", image.getName());

        // 调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件url为：{}", url);

        return Result.success(url);
    }
}
