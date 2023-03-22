package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.common.pojo.xzwusers.UQuestion;
import cn.zealon.readingcloud.account.service.UQuestionService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题反馈表(UQuestion)表控制层
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
@Api(description = "问题反馈接口")
@RestController
@RequestMapping("account/uQuestion")
public class UQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private UQuestionService uQuestionService;

    /**
     * 分页查询
     *
     * @param uQuestion   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UQuestion>> queryByPage(UQuestion uQuestion, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uQuestionService.queryByPage(uQuestion, pageRequest));
    }

    @GetMapping("queryAll")
    public List<UQuestion>queryAll(UQuestion uQuestion){
        return this.uQuestionService.queryAll(uQuestion);
    }

    @GetMapping("toQuestion")
    public JSONObject toQuestion(Long userId,int type,String content, String email){

        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            UQuestion uQuestion=new UQuestion();
            uQuestion.setIsused(0);
            uQuestion.setCreateTime(new Date());
            uQuestion.setUpdateTime(new Date());
            uQuestion.setUserId(userId);
            uQuestion.setQType(type);
            uQuestion.setQContent(content);
            uQuestion.setEmail(email);
            this.uQuestionService.insert(uQuestion);
            result.put("sign",00);
            data.put("data","您反馈的问题已提交管理平台，管理员马上会回复你的，请耐心等待。");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UQuestion> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uQuestionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uQuestion 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UQuestion> add(UQuestion uQuestion) {
        return ResponseEntity.ok(this.uQuestionService.insert(uQuestion));
    }

    /**
     * 编辑数据
     *
     * @param uQuestion 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UQuestion> edit(UQuestion uQuestion) {
        return ResponseEntity.ok(this.uQuestionService.update(uQuestion));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uQuestionService.deleteById(id));
    }

}

