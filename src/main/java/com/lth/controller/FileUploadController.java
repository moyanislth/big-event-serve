package com.lth.controller;


import com.lth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        // 保证文件名字唯一，避免覆盖
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        file.transferTo(new File(fileName));

        return Result.success(FileUploadController.class.getResource("/") + fileName);
    }

}
