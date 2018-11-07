package com.chengnanhuakai.upload.controller;

import com.chengnanhuakai.upload.config.MysqlConfig;
import com.chengnanhuakai.upload.config.Qiniu;
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

    @Resource
    private Qiniu qiniu;



    /**
     * 生成七牛云简单上传凭证
     * @return
     */
    @RequestMapping(value = "/simple",method = RequestMethod.GET)
    @ApiOperation(value = "生成七牛云上传简单凭证")
    public String uploadTokenSimple(){
//        Auth auth = getAuth();
//        String upToken = auth.uploadToken(qiniu.getBucket());
//        logger.info("upload---" + upToken);
//        return upToken;
        logger.info("执行生成七牛云上传简单凭证");
        return "生成凭证";
    }


    /**
     * 生成七牛云覆盖上传凭证
     * @param fileKey   需要覆盖的文件名称
     * @return          覆盖上传凭证
     */
    @RequestMapping(value = "/override",method = RequestMethod.GET)
    public String uploadTokenOverride(String fileKey){
        // Parameter Calibration
//        Auth auth = getAuth();
//        String upToken = auth.uploadToken(qiniu.getBucket(), fileKey);
//
//        return upToken;
        return "拦截";
    }

    /**
     * 带有回调信息的上传凭证
     * @return  上传凭证
     */
    public String uploadTokenWithReturn(){
        Auth auth = getAuth();
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(qiniu.getBucket(), null, expireSeconds, putPolicy);

        return upToken;
    }

    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ApiOperation(value = "上传本地文件到七牛云中，待补充从本地获取文件路径以及将上传凭证保存至本地")
    public void uploadImage(@RequestParam(value = "uploadfile") MultipartFile multipartFile) throws Exception{
        //String uploadToken = uploadTokenSimple();
        String uploadToken = uploadTokenWithReturn();
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        // TODO 文件路径为上传方传递
        //String localFilePath = "C:\\Users\\Aaryn\\Pictures\\悦心盒子\\320.png";
        // 获取文件名称
        String originalFilename = multipartFile.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();


        InputStream inputStream = multipartFile.getInputStream();


        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        // 使用（文件前缀+UUID+文件名称）作为文件的空间key，防止出现key重复
        String key = "image/upload/" + uuid + "/" + originalFilename;
        try {
            Response response = uploadManager.put(inputStream, key, uploadToken,null,null);
            //解析上传成功的结果
            System.out.println("上传结果为" + response.getInfo());
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //System.out.println("解析结果为" + putRet.toString());
            logger.info("解析结果为--" + putRet.toString());
        } catch (QiniuException ex) {

            System.err.println(ex.toString());
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
