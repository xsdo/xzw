package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadContest;
import cn.zealon.readingcloud.book.service.ReadContestService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动参赛表(ReadContest)表控制层
 *
 * @author makejava
 * @since 2023-04-17 09:28:17
 */
@Api(description = "活动参赛接口")
@RestController
@RequestMapping("book/readContest")
public class ReadContestController {
    /**
     * 服务对象
     */
    @Resource
    private ReadContestService readContestService;

    /**
     * 分页查询
     *
     * @param readContest 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
    public ResponseEntity<Page<ReadContest>> queryByPage(ReadContest readContest, PageRequest pageRequest) {
        return ResponseEntity.ok(this.readContestService.queryByPage(readContest, pageRequest));
    }

    @GetMapping("queryAll")
    public List<ReadContest> queryAll(ReadContest readContest){
        return this.readContestService.queryAll(readContest);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReadContest> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.readContestService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param readContest 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReadContest> add(ReadContest readContest) {
        return ResponseEntity.ok(this.readContestService.insert(readContest));
    }

    /**
     * 编辑数据
     *
     * @param readContest 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReadContest> edit(ReadContest readContest) {
        return ResponseEntity.ok(this.readContestService.update(readContest));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.readContestService.deleteById(id));
    }

}

