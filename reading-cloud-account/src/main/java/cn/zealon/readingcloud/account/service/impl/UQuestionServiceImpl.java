package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UQuestion;
import cn.zealon.readingcloud.account.dao.UQuestionDao;
import cn.zealon.readingcloud.account.service.UQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题反馈表(UQuestion)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:54:35
 */
@Service("uQuestionService")
public class UQuestionServiceImpl implements UQuestionService {
    @Resource
    private UQuestionDao uQuestionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UQuestion queryById(Long id) {
        return this.uQuestionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uQuestion   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UQuestion> queryByPage(UQuestion uQuestion, PageRequest pageRequest) {
        long total = this.uQuestionDao.count(uQuestion);
        return new PageImpl<>(this.uQuestionDao.queryAllByLimit(uQuestion, pageRequest), pageRequest, total);
    }

    @Override
    public List<UQuestion>queryAll(UQuestion uQuestion){
        return this.uQuestionDao.queryAll(uQuestion);
    }

    /**
     * 新增数据
     *
     * @param uQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public UQuestion insert(UQuestion uQuestion) {
        this.uQuestionDao.insert(uQuestion);
        return uQuestion;
    }

    /**
     * 修改数据
     *
     * @param uQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public UQuestion update(UQuestion uQuestion) {
        this.uQuestionDao.update(uQuestion);
        return this.queryById(uQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uQuestionDao.deleteById(id) > 0;
    }
}
