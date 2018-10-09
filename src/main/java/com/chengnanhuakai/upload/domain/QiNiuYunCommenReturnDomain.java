package com.chengnanhuakai.upload.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName QiNiuYunCommenReturnDomain
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/9/30 11:11
 * @Version 1.0
 */
@Data
@ApiModel(value = "七牛云上传图片后返回的文件信息")
public class QiNiuYunCommenReturnDomain {

    public String key;      // 文件名称
    public String hash;     // 文件哈希值
    public String bucket;   // 文件所在空间名称
    public long fsize;      //
}
