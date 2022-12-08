package com.bjqg.web.controller;

import com.bjqg.web.core.common.Constants;
import com.bjqg.web.config.BjConfig;
import com.bjqg.web.config.ServerConfig;
import com.bjqg.web.core.AjaxResult;
import com.bjqg.web.utils.file.FileUploadUtils;
import com.bjqg.web.utils.file.FileUtils;
import com.bjqg.web.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理类
 * @author: lbj
 * @date: 2022/12/6 10:10
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    public static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    public static final String FILE_DELIMETER = ",";

    /**
     *
     * @param: fileName 文件名称
     * @param: delete 是否删除
     * @param: response
     * @param: request
     * @return :
     * @author : lbj
     * @description: <通用下载请求>
     * @date : 2022/12/8 10:06
     */
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
    /**
     *
     * @param: file
     * @return : {@link AjaxResult}
     * @author : lbj
     * @description: <通用上传请求（单个上传）>
     * @date : 2022/12/8 11:09
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception{
        try {
            //上传文件的路径
            String filePath = BjConfig.getDownloadPath();
            //上传并返回文件的名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url",url);
            ajax.put("fileName",fileName);
            ajax.put("newFileName",FileUtils.getName(fileName));
            ajax.put("originalFilename",file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     *
     * @param: files
     * @return : {@link AjaxResult}
     * @author : lbj
     * @description: <通用上传请求（多个上传）>
     * @date : 2022/12/8 11:24
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception{
        try {
            //上传文件路径
            String filePath = BjConfig.getUploadPath();
            List<String> urls = new ArrayList<>();
            List<String> fileNames = new ArrayList<>();
            List<String> newFileNames = new ArrayList<>();
            List<String> originalFilenames = new ArrayList<>();
            for (MultipartFile file : files) {
                //上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls",StringUtils.join(urls,FILE_DELIMETER));
            ajax.put("fileNames",StringUtils.join(fileNames,FILE_DELIMETER));
            ajax.put("newFileNames",StringUtils.join(newFileNames,FILE_DELIMETER));
            ajax.put("originalFilenames",StringUtils.join(originalFilenames,FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/download/resource")
    public void resourceDownload(String resource,HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        try {
            if (!FileUtils.checkAllowDownload(resource)){
                throw new Exception(StringUtils.format("资源文件({})非法，不能下载：",resource));
            }
            //本地资源路径
            String localPath = BjConfig.getProfile();
            //数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            //下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response,downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e){
            log.error("文件下载失败",e);
        }
    }

}

