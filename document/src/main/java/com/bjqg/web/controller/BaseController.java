package com.bjqg.web.controller;

import com.bjqg.web.config.BjConfig;
import com.bjqg.web.config.ServerConfig;
import com.bjqg.web.utils.FileUtils;
import com.bjqg.web.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用请求处理类
 * @author: lbj
 * @date: 2022/12/6 10:10
 */
@Controller
@RequestMapping("/base")
public class BaseController {
    public static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private ServerConfig serverConfig;

    public static final String FILE_DELIMETER = ",";

    @GetMapping("/download")
    public void fileDownload(String fileName, boolean delete, HttpServletResponse response, HttpServletRequest request){
        try {
            if (!FileUtils.checkAllowDownload(fileName)){
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载：",fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = BjConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
