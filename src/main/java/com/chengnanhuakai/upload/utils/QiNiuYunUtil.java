package com.chengnanhuakai.upload.utils;

import com.chengnanhuakai.upload.config.Qiniu;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName QiNiuYunUtil
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/10/9 14:18
 * @Version 1.0
 */
@Component
public class QiNiuYunUtil {



    /**
     * 创建七牛云上传凭证所需auth
     * @return
     */
    public static Auth getAuth(Qiniu qiniu) {
        String accessKey = qiniu.getAccessKey();
        String secretKey = qiniu.getSecretKey();
        String bucket = qiniu.getBucket();
        // Parameter Calibration
        if (StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(bucket)){
            throw new QiniuException("Lack Of Key Parameters!");
        }
        return Auth.create(accessKey, secretKey);
    }

}
