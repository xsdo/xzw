package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVideos;
import cn.zealon.readingcloud.book.service.CVideosService;
import cn.zealon.readingcloud.common.pojo.xzwresources.Department;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频表(CVideos)表控制层
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
@Api(description = "视频接口")
@RestController
@RequestMapping("book/cVideos")
public class CVideosController {
    /**
     * 服务对象
     */
    @Resource
    private CVideosService cVideosService;

    /**
     * 分页查询
     *
     * @param cVideos     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CVideos>> queryByPage(CVideos cVideos, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cVideosService.queryByPage(cVideos, pageRequest));
    }

    @GetMapping("/queryAll")
    public List<CVideos>queryAll(CVideos cVideos){
        cVideos.setIsused(0);
        return  this.cVideosService.queryAll(cVideos);
    }

    @GetMapping("queryByUnitarea")
    public CVideos queryByDepartment(String grade, String unitarea) {
        CVideos cVideos=new CVideos();
        cVideos.setIsused(0);
        cVideos.setVGrade(grade);
        cVideos.setVUnitarea(unitarea);
        List<CVideos> cVideosList = this.cVideosService.queryAll(cVideos);
        if (cVideosList!=null&&cVideosList.size() > 0){
            return cVideosList.get(0);
        }else {
            return null;
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CVideos> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cVideosService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param cVideos 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CVideos> add(CVideos cVideos) {
        return ResponseEntity.ok(this.cVideosService.insert(cVideos));
    }

    /**
     * 编辑数据
     *
     * @param cVideos 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CVideos> edit(CVideos cVideos) {
        return ResponseEntity.ok(this.cVideosService.update(cVideos));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.cVideosService.deleteById(id));
    }

}

