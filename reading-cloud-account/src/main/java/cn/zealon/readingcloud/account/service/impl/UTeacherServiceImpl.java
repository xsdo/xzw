package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.common.utils.QRCodeUtil;
import cn.zealon.readingcloud.account.dao.UTeacherDao;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import cn.zealon.readingcloud.common.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
        // 文件大小验证

        System.out.println(properties.getAvatarMaxSize());
        System.out.println(multipartFile.getSize());
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getAvatar());

            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/avatar/"+fileUrl);
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
                String text = "teacherId="+teacherId;
                // 生成的二维码的路径及名称
                String name=System.currentTimeMillis()+"";
                String destPath =properties.getPath().getPath() + name + ".jpg";

                //生成二维码
                QRCodeUtil.encode(text, null, destPath, true);
                // 解析二维码
                String str = QRCodeUtil.decode(destPath);

                String codePath="/Resource/News/"+name + ".jpg";
                uteacher.setQrCode(codePath);
                this.update(uteacher);
            }catch (Exception e) {

            }
        }
        return this.queryById(teacherId);
    }

    public JSONObject doBinding(Long userId, Long schoolId ,String grade,String term,int student){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UTeacher teacher = new UTeacher();
            teacher.setIsused(0);
            teacher.setTeacherId(userId);
            teacher.setTSchoolid(schoolId);
            List<UTeacher>teacherList=this.uTeacherDao.queryAll(teacher);
            if (teacherList.size() > 0){
                result.put("sign", 00);
                data.put("data", "您已经创建过班级了");
            }else {
                teacher.setCreateTime(new Date());
                teacher.setUpdateTime(new Date());
                teacher.setTName(grade+term);
                teacher.setTGrade(grade);
                teacher.setTTerm(term);
                teacher.setTStudent(student);
                teacher.setTFlowers(0);
                teacher.setTImage("/Resource/avatar/2023/3/16/2023031611095329856324534-2bc3-481f-b222-d64e9155ef33.png");
                teacher.setTeaName("班主任");
                teacher.setTeaImage("/Resource/avatar/2023/3/16/20230316110813591997da7f0-c7dc-46e0-ba46-f1d0949ff9dc.png");
                teacher.setTeaSign("班主任签名");
                teacher.setTSlogan("班级寄语");
                teacher.setWrites(0);
                teacher.setReadings(0);
                teacher.setReadalouds(0);
                teacher.setTOpen(1);
                teacher.setRankings(1);
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
