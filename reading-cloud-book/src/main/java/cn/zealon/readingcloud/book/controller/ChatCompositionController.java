package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.common.utils.HttpRequest;
import cn.zealon.readingcloud.book.common.utils.HttpRequestUtil;
import cn.zealon.readingcloud.book.service.HotWordsService;
import cn.zealon.readingcloud.book.service.WxService;
import cn.zealon.readingcloud.common.pojo.xzwresources.ChatComposition;
import cn.zealon.readingcloud.book.service.ChatCompositionService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chat作文表(ChatComposition)表控制层
 *
 * @author makejava
 * @since 2023-04-25 15:18:22
 */
@Api(description = "chat作文接口")
@RestController
@RequestMapping("book/chatComposition")
public class ChatCompositionController {
    /**
     * 服务对象
     */
    @Resource
    private ChatCompositionService chatCompositionService;

    @Resource
    private HotWordsService hotWordsService;

    @Resource
    private WxService wxService;

    /**
     * 分页查询
     *
     * @param chatComposition 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ChatComposition>> queryByPage(ChatComposition chatComposition, PageRequest pageRequest) {
        return ResponseEntity.ok(this.chatCompositionService.queryByPage(chatComposition, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ChatComposition> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.chatCompositionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param chatComposition 实体
     * @return 新增结果
     */
//    @PostMapping
    public ResponseEntity<ChatComposition> add(ChatComposition chatComposition) {
        return ResponseEntity.ok(this.chatCompositionService.insert(chatComposition));
    }

    @ApiOperation(value = "获取AI范文")
    @GetMapping("getAnswer")
    public Object getAnswer(Long userId,String grade ,String type ,int wordNumber,String keyword ){
        if (wxService.checkText(keyword)){return null;}
        if (keyword!=null){
            this.hotWordsService.addHotWords(keyword);
        }
        return this.chatCompositionService.getAnswer(userId, grade, type, wordNumber, keyword);
    }

    @ApiOperation(value = "根据用户id查询AI范文")
    @GetMapping("queryByUserId")
    public List<ChatComposition> queryByUserId(Long userId){
        return this.chatCompositionService.queryByUserId(userId);
    }
    /**
     * 编辑数据
     *
     * @param chatComposition 实体
     * @return 编辑结果
     */
//    @PutMapping
    public ResponseEntity<ChatComposition> edit(ChatComposition chatComposition) {
        return ResponseEntity.ok(this.chatCompositionService.update(chatComposition));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.chatCompositionService.deleteById(id));
    }

}

