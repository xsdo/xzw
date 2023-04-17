package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.common.config.OssProperties;
import cn.zealon.readingcloud.book.common.utils.OssUtil;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwresources.Images;
import cn.zealon.readingcloud.book.dao.ImagesDao;
import cn.zealon.readingcloud.book.service.ImagesService;
import cn.zealon.readingcloud.common.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 随机图表(Images)表服务实现类
 *
 * @author makejava
 * @since 2023-03-22 18:22:35
 */
@Service("imagesService")
public class ImagesServiceImpl implements ImagesService {
    @Resource
    private ImagesDao imagesDao;

    @Resource
    private FileProperties properties;

    @Resource
    private OssProperties ossProperties;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Images queryById(Long id) {
        return this.imagesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param images      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Images> queryByPage(Images images, PageRequest pageRequest) {
        long total = this.imagesDao.count(images);
        return new PageImpl<>(this.imagesDao.queryAllByLimit(images, pageRequest), pageRequest, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateImage(MultipartFile multipartFile) {
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            byte [] byteArr=multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            //上传服务器
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getPath());
            //上传oss
            String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String path = fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1);
            Map<String, String> map = new HashMap<>();
            map= OssUtil.uploadOss(endpoint, accessKeyId ,accessKeySecret,bucketName,inputStream,"Resource/News/"+path,filename);
            String fileUrlOss=map.get("fileUrl");
            System.out.println(fileUrlOss);
            Images images = new Images();
            images.setImageb("/Resource/News/"+fileUrl);
            images.setType(2);
            this.insert(images);
            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/News/"+fileUrl);
                put("fileUrlOss", fileUrlOss);
            }};
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateImageTwo(MultipartFile multipartFile,MultipartFile multipartFile2 ) {
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile2.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String fileType2 = FileUtil.getExtensionName(multipartFile2.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        if(fileType2 != null && !image.contains(fileType2)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            byte [] byteArr=multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            //上传服务器
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getPath());

            byte [] byteArr2=multipartFile2.getBytes();
            InputStream inputStream2 = new ByteArrayInputStream(byteArr2);
            //上传服务器2
            String fileUrl2 = FileUtil.uploadFile(multipartFile2, properties.getPath().getPath());

            //上传oss
            String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String path = fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1);
            Map<String, String> map = new HashMap<>();
            map= OssUtil.uploadOss(endpoint, accessKeyId ,accessKeySecret,bucketName,inputStream,"Resource/News/"+path,filename);
            String fileUrlOss=map.get("fileUrl");
            System.out.println(fileUrlOss);

            //上传oss2
            String filename2 = fileUrl2.substring(fileUrl2.lastIndexOf("/") + 1);
            String path2 = fileUrl2.substring(0, fileUrl2.lastIndexOf("/") + 1);
            Map<String, String> map2 = new HashMap<>();
            map2= OssUtil.uploadOss(endpoint, accessKeyId ,accessKeySecret,bucketName,inputStream2,"Resource/News/"+path2,filename2);
            String fileUrlOss2=map2.get("fileUrl");
            System.out.println(fileUrlOss2);

            Images images = new Images();
            images.setImageb("/Resource/News/"+fileUrl);
            images.setImages("/Resource/News/"+fileUrl2);
            images.setType(1);
            this.insert(images);
            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/News/"+fileUrl);
                put("fileUrl2", "/Resource/News/"+fileUrl2);
                put("fileUrlOss", fileUrlOss);
                put("fileUrlOss2", fileUrlOss2);
            }};
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Images queryRandTwo(){
        List<Images> images = this.imagesDao.queryRand(1,1);
        if (images.size() > 0){
            return images.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Images queryRand(){
        List<Images> images = this.imagesDao.queryRand(1,2);
        if (images.size() > 0){
            return images.get(0);
        }else {
            return null;
        }
    }
    /**
     * 新增数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    @Override
    public Images insert(Images images) {
        this.imagesDao.insert(images);
        return images;
    }

    /**
     * 修改数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    @Override
    public Images update(Images images) {
        this.imagesDao.update(images);
        return this.queryById(images.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.imagesDao.deleteById(id) > 0;
    }
}
