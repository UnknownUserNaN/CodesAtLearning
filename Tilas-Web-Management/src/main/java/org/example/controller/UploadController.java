package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    private static final String UPLOAD_DIR = "D:/images/";

    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile file) throws IOException { // 一般情况下，请注意这里的参数名与html文件中的参数名一致
        log.info("上传文件：{}, {}, {}", username, age, file);
        if (!file.isEmpty()) {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")); // 截取拓展名
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName; // 使用UUID生成唯一文件名
            // 上文中的UUID是随机生成的，所以每次上传的文件名都是唯一的

            // 拼接完整的文件路径
            File targetFile = new File(UPLOAD_DIR + uniqueFileName);

            // 如果目标目录不存在，则创建它
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 保存文件
            file.transferTo(targetFile);
        }
        return Result.success();
    }
}
