package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.common.config.OssProperties;
import cn.zealon.readingcloud.account.common.utils.OssUtil;
import cn.zealon.readingcloud.account.common.utils.QRCodeUtil;
import cn.zealon.readingcloud.account.common.utils.QRCodeUtils;
import cn.zealon.readingcloud.account.dao.UTeacherDao;
import cn.zealon.readingcloud.account.service.StudentService;
import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.UBindingService;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwusers.*;
import cn.zealon.readingcloud.common.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师班级表(UTeacher)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Service("uTeacherService")
public class UTeacherServiceImpl implements UTeacherService {
    @Resource
    private UTeacherDao uTeacherDao;

    @Resource
    private FileProperties properties;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private UBindingService uBindingService;

    @Resource
    private StudentService studentService;

    @Resource
    private OssProperties ossProperties;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UTeacher queryById(Long id) {
        return this.uTeacherDao.queryById(id);
    }

    @Override
    public List<UTeacher>queryAll(UTeacher uTeacher){
        return this.uTeacherDao.queryAll(uTeacher);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateHeadImg(MultipartFile multipartFile) {
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
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getAvatar());
            //上传oss
            String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String path = fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1);
            Map<String, String> map = new HashMap<>();
            map= OssUtil.uploadOss(endpoint, accessKeyId ,accessKeySecret,bucketName,inputStream,"Resource/avatar/"+path,filename);
            String fileUrlOss=map.get("fileUrl");
            System.out.println(fileUrlOss);
            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/avatar/"+fileUrl);
                put("fileUrlOss", fileUrlOss);
            }};
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public UTeacher changeHead(Long teacherId, String fileUrl){
        UTeacher uteacher=this.queryById(teacherId);
        if (uteacher!=null){
            uteacher.setTImage(fileUrl);
            this.update(uteacher);
        }
        return this.queryById(teacherId);
    }

    @Override
    public UTeacher changeTeacherHead(Long teacherId, String fileUrl){
        UTeacher uteacher=this.queryById(teacherId);
        if (uteacher!=null){
            uteacher.setTeaImage(fileUrl);
            this.update(uteacher);
        }
        return this.queryById(teacherId);
    }


    @Override
    public UTeacher teacherQRCode(Long teacherId) {
        UTeacher uteacher=this.queryById(teacherId);
        if (uteacher != null) {
            try{
                // 存放在二维码中的内容
                // 二维码中的内容可以是文字，可以是链接等
                String text = "https://xzw.aace.com.cn/teacher/?teacherId="+teacherId;
                // 生成的二维码的路径及名称
                String name=System.currentTimeMillis()+"";
                String destPath =properties.getPath().getPath() + name + ".jpg";

                //生成二维码
                QRCodeUtil.encode(text, null, destPath, true);
                // 解析二维码 部分二维码错误 略去解析步骤
//                String str = QRCodeUtil.decode(destPath);
//                System.out.println(str);

                String codePath="/Resource/News/"+name + ".jpg";
                uteacher.setQrCode(codePath);
                this.update(uteacher);
            }catch (Exception e) {

            }
        }
        return this.queryById(teacherId);
    }
    @Override
    public UTeacher teacherQRCodePress(Long teacherId) {
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();

        UTeacher uteacher=this.queryById(teacherId);
        if (uteacher != null) {
            try{
                // 存放在二维码中的内容
                BufferedImage image = null;
                File uploadFile = null;
                // 存放在二维码中的内容
                // 二维码中的内容可以是文字，可以是链接等
                String text = "https://xzw.aace.com.cn/teacher/?teacherId="+teacherId;
                image = QRCodeUtils.createQRCode(text);
                image=QRCodeUtils.pressText(image,null, Color.BLACK,"微信扫一扫，加入班级");
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
                uteacher.setQrCode(codePath);
                this.update(uteacher);
            }catch (Exception e) {

            }
        }
        return this.queryById(teacherId);
    }

    @Override
    public JSONObject doBinding(Long userId, Long schoolId ,String grade,String term,int student){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UTeacher teacher = new UTeacher();
            teacher.setIsused(0);
            teacher.setTeacherId(userId);
            List<UTeacher>teacherList=this.uTeacherDao.queryAll(teacher);
            Student ss =new Student();
            ss.setUserId(userId);
            List<Student>studentList=this.studentService.queryAll(ss);
//            UBinding ub = new UBinding();
//            ub.setUserId(userId);
//            List<UBinding>uBindingList=this.uBindingService.queryAll(ub);
            UAttribute ua =this.uAttributeService.queryById(userId);
            if (teacherList.size() > 0||ua.getTeacherid() > 0||studentList.size() > 0 ){
                result.put("sign", -1);
                data.put("data", "您已经创建或加入过班级了");
            } else {
                teacher.setTSchoolid(schoolId);
                teacher.setCreateTime(new Date());
                teacher.setUpdateTime(new Date());
                teacher.setTName(grade+term);
                teacher.setTGrade(grade);
                teacher.setTTerm(term);
                teacher.setTStudent(student);
                teacher.setTFlowers(0);
                teacher.setTImage("/Resource/avatar/2023/3/16/2023031611095329856324534-2bc3-481f-b222-d64e9155ef33.png");
                UAttribute uAttribute =this.uAttributeService.queryById(userId);
                if (uAttribute != null) {
                    teacher.setTeaName(uAttribute.getQqnum());
                    teacher.setTeaImage(uAttribute.getPortrait());
                    teacher.setTeaSign(uAttribute.getSign());
                }else {
                    teacher.setTeaName("班主任");
                    teacher.setTeaImage("/Resource/avatar/2023/3/16/20230316110813591997da7f0-c7dc-46e0-ba46-f1d0949ff9dc.png");
                    teacher.setTeaSign("班主任签名");
                }
                teacher.setTSlogan("班级寄语");
                teacher.setWrites(0);
                teacher.setReadings(0);
                teacher.setReadalouds(0);
                teacher.setTOpen(1);
                teacher.setRankings(1);
                this.insert(teacher);

                UTeacher t = new UTeacher();
                t.setIsused(0);
                t.setTeacherId(userId);
                t.setTSchoolid(schoolId);
                List<UTeacher>tList=this.uTeacherDao.queryAll(t);
                if (tList.size() > 0) {
                    UAttribute uat =this.uAttributeService.queryById(userId);
                    if (uat!=null){
                        uat.setUType(5);
                        uat.setTeacherid(tList.get(0).getId());
                        this.uAttributeService.update(uat);
                    }
                }

                result.put("sign", 00);
                data.put("data", "班级创建成功，快生成您的班级专属二维码邀请你的学生扫码进班吧");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }
    /**
     * 分页查询
     *
     * @param uTeacher    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UTeacher> queryByPage(UTeacher uTeacher, PageRequest pageRequest) {
        long total = this.uTeacherDao.count(uTeacher);
        return new PageImpl<>(this.uTeacherDao.queryAllByLimit(uTeacher, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public UTeacher insert(UTeacher uTeacher) {
        this.uTeacherDao.insert(uTeacher);
        return uTeacher;
    }

    /**
     * 修改数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public UTeacher update(UTeacher uTeacher) {
        this.uTeacherDao.update(uTeacher);
        return this.queryById(uTeacher.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uTeacherDao.deleteById(id) > 0;
    }
}
