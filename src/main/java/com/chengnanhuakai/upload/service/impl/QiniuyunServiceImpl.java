package com.chengnanhuakai.upload.service.impl;

import com.chengnanhuakai.upload.config.Qiniu;
import com.chengnanhuakai.upload.controller.ImageUploadController;
import com.chengnanhuakai.upload.service.QiniuyunService;
import com.chengnanhuakai.upload.utils.QiNiuYunUtil;
import com.chengnanhuakai.upload.utils.QiniuException;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName QiniuyunServiceImpl
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/7 11:33
 * @Version 1.0
 */
@Service
@Transactional
public class QiniuyunServiceImpl implements QiniuyunService {

    private static final Logger logger = LoggerFactory.getLogger(QiniuyunServiceImpl.class);

    @Resource
    private Qiniu qiniu;

    /** 获取auth */
    private final Auth auth = QiNiuYunUtil.getAuth(qiniu);

    @Override
    public String getSimpleToken() {
        String upToken = auth.uploadToken(qiniu.getBucket());
        logger.info("Create simple upload token : " + upToken);
        return upToken;
    }

    @Override
    public String getOverrideToken(String fileKey) {
        String upToken = auth.uploadToken(qiniu.getBucket(), fileKey);
        logger.info("Create override upload token : " + upToken);
        return upToken;
    }

    @Override
    public String getWithReturnToken() {
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(qiniu.getBucket(), null, expireSeconds, putPolicy);
        logger.info("Create upload token with return info : " + upToken);
        return upToken;
    }

    @Override
    public void uploadImageToQiniuyun(MultipartFile multipartFile) throws Exception{
        String uploadToken = getWithReturnToken();
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        // 获取文件名称
        String originalFilename = multipartFile.getOriginalFilename();
        // 生成字符串
        String uuid = UUID.randomUUID().toString();
        // 获取要上传的文件
        InputStream inputStream = multipartFile.getInputStream();
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        // 使用（文件前缀+UUID+文件名称）作为文件的空间key，防止出现key重复
        String key = "image/upload/" + uuid + "/" + originalFilename;
        // 上传文件
        Response response = uploadManager.put(inputStream, key, uploadToken,null,null);
        //解析上传成功的结果
        logger.info("上传结果为" + response.getInfo());
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        logger.info("解析结果为--" + putRet.toString());
    }


}
