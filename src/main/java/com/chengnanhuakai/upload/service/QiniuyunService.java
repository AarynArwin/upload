package com.chengnanhuakai.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName QiniuyunService
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/7 11:30
 * @Version 1.0
 */
public interface QiniuyunService {
    /**
     * 生成七牛云简单上传凭证
     * @return  七牛云简单上传凭证，覆盖上传等操作不可用
     */
    String getSimpleToken();

    /**
     * 生成七牛云覆盖上传凭证
     * @param fileKey   需要覆盖的文件名称
     * @return          七牛云覆盖上传凭证
     */
    String getOverrideToken(String fileKey);

    /**
     * 生成带有回调信息的上传凭证
     * @return  七牛云上传凭证（附带回调信息）
     */
    String getWithReturnToken();

    /**
     * 上传图片到七牛云
     * @param multipartFile
     */
    void uploadImageToQiniuyun(MultipartFile multipartFile) throws Exception;
}