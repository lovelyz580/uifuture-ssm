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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件上传和下载的Controller
 * 章节:7.3
 * @author chenhx
 * @version FileUploadController.java, v 0.1 2018-08-19 下午 6:25
 */
@Controller
@RequestMapping("file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/upload")
    public ModelAndView register(HttpServletRequest request,
                                 User user, MultipartFile image) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/upload/message");
        if (user == null || image == null) {
            modelAndView.addObject("message", "参数错误");
            return modelAndView;
        }
        logger.info("参数，user={},imageName={}", user, image.getOriginalFilename());
        // 上传的绝对路径
        //例如该项目中，路径为 ***/项目名/classes/artifacts/ssm_spring_mvc_war_exploded/WEB-INF/file/images/，***为省略的项目路径
        //getRealPath方法返回服务器filesystem上的绝对路径。getServletContext().getRealPath是返回上下文所在根路径的绝对路径
        String path = request.getServletContext().getRealPath("/WEB-INF/file/images/");
        // 获取上传文件名
        String filename = image.getOriginalFilename();
        File filepath = new File(path, filename);
        logger.info("路径:" + filepath);
        // 判断路径是否存在，不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        // 将文件保存到一个目标文件中  File.separator-文件分隔符
        image.transferTo(new File(path + File.separator + filename));
        modelAndView.addObject("message", "上传成功");
        return modelAndView;
    }
}