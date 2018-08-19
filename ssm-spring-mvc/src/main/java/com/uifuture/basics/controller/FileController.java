/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件上传和下载的Controller
 *
 * @author chenhx
 * @version FileUploadController.java, v 0.1 2018-08-19 下午 6:25
 */
@Controller
@RequestMapping("file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/upload")
    @ResponseBody
    public String register(HttpServletRequest request,
                           User user, MultipartFile image) throws Exception {
        if (user == null || image == null) {
            return "error";
        }
        logger.info("参数，user={},imageName={}", user, image.getOriginalFilename());
        // 上传路径
        String path = request.getServletContext().getRealPath("/WEB-INF/file/images/");
        // 获取上传文件名
        String filename = image.getOriginalFilename();
        File filepath = new File(path, filename);
        // 判断路径是否存在，不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        // 将文件保存到一个目标文件中  File.separator-文件分隔符
        image.transferTo(new File(path + File.separator + filename));
        return "success";
    }
}