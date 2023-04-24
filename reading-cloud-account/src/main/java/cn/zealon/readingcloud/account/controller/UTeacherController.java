package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.account.service.WxService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 教师班级表(UTeacher)表控制层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Api(description = "班级老师接口")
@RestController
@RequestMapping("account/uTeacher")
public class UTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private UTeacherService uTeacherService;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private WxService wxService;

    /**
     * 分页查询
     *
     * @param uTeacher    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<UTeacher>> queryByPage(UTeacher uTeacher, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uTeacherService.queryByPage(uTeacher, pageRequest));
    }

//    @GetMapping("queryAll")
    public List<UTeacher>queryAll(UTeacher uTeacher){
        return this.uTeacherService.queryAll(uTeacher);
    }

    @GetMapping("getAllTeacher")
    public List<UTeacher>getAllTeacher(){
        UTeacher teacher = new UTeacher();
        teacher.setIsused(0);
        return this.uTeacherService.queryAll(teacher);
    }
    @GetMapping("queryByUserId")
    public UTeacher queryByUserId(Long userId){
        UTeacher teacher=new UTeacher();
        UAttribute uAttribute =this.uAttributeService.queryById(userId);
        if (uAttribute!=null){
            Long teacherId =uAttribute.getTeacherid();
            if (teacherId>0){
                teacher=this.uTeacherService.queryById(teacherId);
                if (teacher != null) {
                    if (teacher.getQrCode()==null){
                        this.uTeacherService.teacherQRCodePress(teacherId);
                    }
                }
                teacher.setRemarks(uAttribute.getUType()+"");
            }
        }
        return teacher;
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UTeacher> queryById(@PathVariable("id") Long id) {
        UTeacher teacher=this.uTeacherService.queryById(id);
        if (teacher != null) {
            if (teacher.getQrCode()==null){
                this.uTeacherService.teacherQRCodePress(id);
            }
        }
        return ResponseEntity.ok(this.uTeacherService.queryById(id));
    }
    @ApiOperation("上传图片")
    @PostMapping("updateImg")
    public Map<String, String> updateImg(MultipartFile avatar){
        if (wxService.checkImg(avatar)){return null;}
        return this.uTeacherService.updateHeadImg(avatar);
    }

    @ApiOperation("修改班级头像")
    @GetMapping("changeHead")
    public UTeacher changeHead(Long teacherId, String fileUrl){
        return this.uTeacherService.changeHead(teacherId, fileUrl);
    }

    @ApiOperation("修改教师头像")
    @GetMapping("changeTeacherHead")
    public UTeacher changeTeacherHead(Long teacherId, String fileUrl){
        return this.uTeacherService.changeTeacherHead(teacherId, fileUrl);
    }

    @ApiOperation("生成班级二维码")
    @GetMapping("teacherQRCode")
    public UTeacher teacherQRCode(Long teacherId){
        return this.uTeacherService.teacherQRCodePress(teacherId);
    }


    @ApiOperation("扫码创建班级")
    @GetMapping("doBindingTeacher")
    public JSONObject doBinding(Long userId, Long schoolId , String grade, String term, int student){
        if (wxService.checkText(grade)){return null;}
        if (wxService.checkText(term)){return null;}
        return this.uTeacherService.doBinding(userId, schoolId, grade, term, student);
    }

    @ApiOperation("修改班级信息")
    @GetMapping("editTeacher")
    public UTeacher editTeacher(Long id,String grade, String term, int student,int open ,String slogan){
        if (wxService.checkText(grade)){return null;}
        if (wxService.checkText(term)){return null;}
        if (wxService.checkText(slogan)){return null;}
        UTeacher uTeacher =new UTeacher();
        uTeacher.setId(id);
        uTeacher.setTGrade(grade);
        uTeacher.setTTerm(term);
        uTeacher.setTName(grade+term);
        uTeacher.setTStudent(student);
        uTeacher.setTOpen(open);
        uTeacher.setTSlogan(slogan);
        this.uTeacherService.update(uTeacher);
        return this.uTeacherService.queryById(id);
    }
    @ApiOperation("同步班主任信息")
    @GetMapping("editTeacherUser")
    public UTeacher editTeacherUser(Long id){
        UTeacher uTeacher=this.uTeacherService.queryById(id);
        if (uTeacher != null) {
            Long teacherId = uTeacher.getTeacherId();
            UAttribute uAttribute =this.uAttributeService.queryById(teacherId);
            if (uAttribute != null) {
                uTeacher.setTeaName(uAttribute.getQqnum());
                uTeacher.setTeaImage(uAttribute.getPortrait());
                uTeacher.setTeaSign(uAttribute.getSign());
                this.uTeacherService.update(uTeacher);
            }
        }
        return this.uTeacherService.queryById(id);
    }




    /**
     * 新增数据
     *
     * @param uTeacher 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<UTeacher> add(@RequestBody UTeacher uTeacher) {
        return ResponseEntity.ok(this.uTeacherService.insert(uTeacher));
    }

    /**
     * 编辑数据
     *
     * @param uTeacher 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<UTeacher> edit(UTeacher uTeacher) {
        return ResponseEntity.ok(this.uTeacherService.update(uTeacher));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uTeacherService.deleteById(id));
    }

}

