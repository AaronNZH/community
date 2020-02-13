package com.nzh.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.nzh.community.exception.CustomizeErrorCode;
import com.nzh.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class AliyunProvider {

    @Value("${Aliyun-endpoint}")
    private String endpoint;
    @Value("${Aliyun-accessKeyId}")
    private String accessKeyId;
    @Value("${Aliyun-accessKeySecret}")
    private String accessKeySecret;
    @Value("${Aliyun-bucketName}")
    private String bucketName;
    @Value("${Aliyun-objectName}")
    private String objectName;


    public String upload(InputStream inputStream, String fileName) {
        String[] filePaths = fileName.split("\\.");
        String generateFileName;
        if (filePaths.length > 1) {
            generateFileName = objectName + UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件流
            ossClient.putObject(bucketName, generateFileName, inputStream);
            Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365);
            String url = ossClient.generatePresignedUrl(bucketName, generateFileName, expiration).toString();
            // 关闭OSSClient。
            ossClient.shutdown();
            return url;
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }

}
