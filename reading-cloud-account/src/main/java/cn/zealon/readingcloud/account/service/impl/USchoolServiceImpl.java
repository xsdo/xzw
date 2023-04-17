package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.common.config.OssProperties;
import cn.zealon.readingcloud.account.common.utils.OssUtil;
import cn.zealon.readingcloud.account.common.utils.QRCodeUtil;
import cn.zealon.readingcloud.account.common.utils.QRCodeUtils;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.account.dao.USchoolDao;
import cn.zealon.readingcloud.account.service.USchoolService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户学校表(USchool)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Service("uSchoolService")
public class USchoolServiceImpl implements USchoolService {
    @Resource
    private USchoolDao uSchoolDao;

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
    public USchool queryById(Long id) {
        return this.uSchoolDao.queryById(id);
    }


    @Override
    public USchool schoolQRCode(Long schoolId) {
        USchool uSchool=this.queryById(schoolId);
        if (uSchool != null) {
            try{
                // 存放在二维码中的内容
                // 二维码中的内容可以是文字，可以是链接等
                String text = "https://xzw.aace.com.cn/school/?schoolId="+schoolId;
                // 生成的二维码的路径及名称
                String name=System.currentTimeMillis()+"";
                String destPath =properties.getPath().getPath() + name + ".jpg";

                //生成二维码
                QRCodeUtil.encode(text, null, destPath, true);
                // 解析二维码 部分二维码错误 略去解析步骤
//                String str = QRCodeUtil.decode(destPath);
//                System.out.println(str);

                String codePath="/Resource/News/"+name + ".jpg";
                uSchool.setQrCode(codePath);
                this.update(uSchool);
            }catch (Exception e) {

            }
        }
        return this.queryById(schoolId);
    }
    @Override
    public USchool schoolQRCodePress(Long schoolId) {
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();

        USchool uSchool=this.queryById(schoolId);
        if (uSchool != null) {
            try{
                // 存放在二维码中的内容
                BufferedImage image = null;
                File uploadFile = null;
                // 存放在二维码中的内容
                // 二维码中的内容可以是文字，可以是链接等
                String text = "https://xzw.aace.com.cn/school/?schoolId="+schoolId;
                image = QRCodeUtils.createQRCode(text);
                image=QRCodeUtils.pressText(image,null, Color.BLACK,uSchool.getSName());
                String imgPath =properties.getPath().getPath()+"/"+"logo.png";
                image= QRCodeUtils.insertLogo(image,imgPath,true);
                // 生成的二维码的路径及名称
                String name=System.currentTimeMillis()+"";
                String destPath =properties.getPath().getPath() + name + ".jpg";

                //生成二维码
//                QRCodeUtil.encode(text, null, destPath, true);
                //上传oss
                File file = new File(name + ".jpg");
                ImageIO.write(image, "jpg",file);
                Map<String, String> map = new HashMap<>();
                map= OssUtil.uploadOssFile(endpoint, accessKeyId ,accessKeySecret,bucketName,file,"Resource/News/",name + ".jpg");
                String fileUrlOss=map.get("fileUrl");
                System.out.println(fileUrlOss);
                file.delete();
                //上传服务器
                QRCodeUtils.writeToLocalByPath(image, "jpg", destPath);
                // 解析二维码 部分二维码错误 略去解析步骤
//                String str = QRCodeUtil.decode(destPath);
//                System.out.println(str);

                String codePath="/Resource/News/"+name + ".jpg";
                uSchool.setQrCode(codePath);
                this.update(uSchool);
            }catch (Exception e) {

            }
        }
        return this.queryById(schoolId);
    }

    /**
     * 分页查询
     *
     * @param uSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<USchool> queryByPage(USchool uSchool, PageRequest pageRequest) {
        long total = this.uSchoolDao.count(uSchool);
        return new PageImpl<>(this.uSchoolDao.queryAllByLimit(uSchool, pageRequest), pageRequest, total);
    }


    @Override
    public List<USchool>queryAll(USchool uSchool){
        return this.uSchoolDao.queryAll(uSchool);
    }
    /**
     * 新增数据
     *
     * @param uSchool 实例对象
     * @return 实例对象
     */
    @Override
    public USchool insert(USchool uSchool) {
        this.uSchoolDao.insert(uSchool);
        return uSchool;
    }

    /**
     * 修改数据
     *
     * @param uSchool 实例对象
     * @return 实例对象
     */
    @Override
    public USchool update(USchool uSchool) {
        this.uSchoolDao.update(uSchool);
        return this.queryById(uSchool.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uSchoolDao.deleteById(id) > 0;
    }
}
