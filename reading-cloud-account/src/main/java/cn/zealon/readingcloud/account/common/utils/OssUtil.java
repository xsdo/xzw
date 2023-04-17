package cn.zealon.readingcloud.account.common.utils;

import cn.hutool.core.date.DateTime;
import cn.zealon.readingcloud.account.common.config.OssProperties;
import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OssUtil {
    @Resource
    private  OssProperties ossProperties;
    /**
     * 上传文件
     *
     * @param inputStream      上传的文件
     * @param module           oss目录
     * @param originalFilename 文件名称
     * @param fileType         文件类型 private 私有，public 共有
     * @return
     */
    public Map upload(InputStream inputStream, String module, String originalFilename, String fileType) {
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();
        String objectName = "";
        Map<String, Object> map = new HashMap<>();

        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header。
        ObjectMetadata meta = new ObjectMetadata();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String folder = new DateTime().toString("yyyy/MM/dd");
            // 文件名
            String fileName = UUID.randomUUID().toString();
            //文件扩展名
            String fileExtention = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (StringUtils.isNotEmpty(module)) {
                //最终的路径 类似avatar/2021/12/05/xxxxxxxxx.jpg
                objectName = module + "/" + folder + "/" + fileName + fileExtention;
            } else {
                objectName = folder + "/" + fileName + fileExtention;
            }
            meta.setContentType("text/plain");
            meta.setContentDisposition("inline");

            // 设置存储空间的读写权限。例如将examplebucket的读写权限ACL设置为私有Private。
//            ossClient.setBucketAcl(bucketName, CannedAccessControlList.Private);
            if ("private".equals(fileType)) {
                //将文件访问读写权限设置为私有
                meta.setObjectAcl(CannedAccessControlList.Private);
                // 上传文件
                ossClient.putObject(bucketName, objectName, inputStream, meta);
                // 设置签名URL过期时间，单位为毫秒。
                Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
                // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
                String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
                map.put("fileUrl", url);
                return map;
            }
            // 上传文件
            ossClient.putObject(bucketName, objectName, inputStream, meta);
            //https://rxt.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg
            endpoint = endpoint.split("//")[1];
            map.put("fileUrl", "https://" + bucketName + "." + endpoint + "/" + objectName);
            return map;
        } catch (OSSException oe) {
            throw new ServiceException(oe.getMessage());
        } catch (ClientException ce) {
            throw new ServiceException(ce.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static Map uploadOss(String endpoint,String accessKeyId,String accessKeySecret,String bucketName, InputStream inputStream, String module, String fileName) {
        String objectName = "";
        Map<String, Object> map = new HashMap<>();
        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header。
        ObjectMetadata meta = new ObjectMetadata();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (StringUtils.isNotEmpty(module)) {
                //最终的路径 类似avatar/2021/12/05/xxxxxxxxx.jpg
                objectName = module + fileName ;
            } else {
                objectName = fileName ;
            }

            meta.setContentEncoding("UTF-8");
            // 设置存储空间的读写权限。例如将examplebucket的读写权限ACL设置为私有Private。
//            ossClient.setBucketAcl(bucketName, CannedAccessControlList.Private);
            // 上传文件
            ossClient.putObject(bucketName, objectName, inputStream);
            //https://rxt.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg
            if (endpoint.contains("//")){
                endpoint = endpoint.split("//")[1];
            }

            map.put("fileUrl", "https://" + bucketName + "." + endpoint + "/" + objectName);
            return map;
        } catch (OSSException oe) {
            throw new ServiceException(oe.getMessage());
        } catch (ClientException ce) {
            throw new ServiceException(ce.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }
    public static Map uploadOssFile(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, File file, String module, String fileName) {
        String objectName = "";
        Map<String, Object> map = new HashMap<>();
        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header。
        ObjectMetadata meta = new ObjectMetadata();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (StringUtils.isNotEmpty(module)) {
                //最终的路径 类似avatar/2021/12/05/xxxxxxxxx.jpg
                objectName = module + fileName ;
            } else {
                objectName = fileName ;
            }

            meta.setContentEncoding("UTF-8");
            // 设置存储空间的读写权限。例如将examplebucket的读写权限ACL设置为私有Private。
//            ossClient.setBucketAcl(bucketName, CannedAccessControlList.Private);
            // 上传文件
            ossClient.putObject(bucketName, objectName, file);
            //https://rxt.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg
            if (endpoint.contains("//")){
                endpoint = endpoint.split("//")[1];
            }

            map.put("fileUrl", "https://" + bucketName + "." + endpoint + "/" + objectName);
            return map;
        } catch (OSSException oe) {
            throw new ServiceException(oe.getMessage());
        } catch (ClientException ce) {
            throw new ServiceException(ce.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }
}
