package com.chengnanhuakai.upload.controller;

import com.chengnanhuakai.upload.config.MysqlConfig;
import com.chengnanhuakai.upload.config.Qiniu;
import com.chengnanhuakai.upload.service.QiniuyunService;
import com.chengnanhuakai.upload.utils.QiniuException;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName ImageUploadController
 * @Description 七牛云图片上传整理
 * @Author Aaryn
 * @Date 2018/9/20 9:34
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/image/uploadtoken")
@Api(description = "七牛云图片上传接口")
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Autowired
    private QiniuyunService qiniuyunService;

    /**
     * 生成七牛云简单上传凭证
     * @return
     */
    @RequestMapping(value = "/simple",method = RequestMethod.GET)
    @ApiOperation(value = "生成七牛云上传简单凭证")
    public String uploadTokenSimple(){
        String simpleToken = qiniuyunService.getSimpleToken();
        return simpleToken;
    }


    /**
     * 生成七牛云覆盖上传凭证
     * @param fileKey   需要覆盖的文件名称
     * @return          覆盖上传凭证
     */
    @RequestMapping(value = "/override",method = RequestMethod.GET)
    public String uploadTokenOverride(String fileKey){
        String overrideToken = qiniuyunService.getOverrideToken(fileKey);
        return overrideToken;
    }

    /**
     * 带有回调信息的上传凭证
     * @return  上传凭证
     */
    public String uploadTokenWithReturn(){
        String withReturnToken = qiniuyunService.getWithReturnToken();
        return withReturnToken;
    }

    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ApiOperation(value = "上传本地文件到七牛云")
    public void uploadImage(@RequestParam(value = "uploadfile") MultipartFile multipartFile){
        try {
            qiniuyunService.uploadImageToQiniuyun(multipartFile);
        }catch (QiniuException e){
            logger.error("上传图片异常" + e.toString());

        }catch (Exception e){
            logger.error("未知错误" + e.toString());
        }
    }

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String getCallBackBody(HttpServletRequest request){
        String returnBody = request.getParameter("returnBody");

        if (returnBody != null){
            System.out.println("七牛云返回信息为" + returnBody);
        }

        return returnBody;
    }

}
