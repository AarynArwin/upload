package com.chengnanhuakai.upload.service.impl;

import com.chengnanhuakai.upload.config.Qiniu;
import com.chengnanhuakai.upload.controller.ImageUploadController;
import com.chengnanhuakai.upload.service.QiniuyunService;
import com.chengnanhuakai.upload.utils.QiNiuYunUtil;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    // 获取auth
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


}
