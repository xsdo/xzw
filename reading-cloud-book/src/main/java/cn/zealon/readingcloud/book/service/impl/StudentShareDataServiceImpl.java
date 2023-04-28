package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.StudentShareData;
import cn.zealon.readingcloud.book.dao.StudentShareDataDao;
import cn.zealon.readingcloud.book.service.StudentShareDataService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 学生分享表(StudentShareData)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 10:21:10
 */
@Service("studentShareDataService")
public class StudentShareDataServiceImpl implements StudentShareDataService {
    @Resource
    private StudentShareDataDao studentShareDataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StudentShareData queryById(Long id) {
        return this.studentShareDataDao.queryById(id);
    }

    public StudentShareData queryByUserId(Long userId){
        StudentShareData studentShareData =new StudentShareData();
        studentShareData.setIsused(0);
        studentShareData.setUserId(userId);
        List<StudentShareData>shareDataList =this.studentShareDataDao.queryAll(studentShareData);
        if (!shareDataList.isEmpty()) {
            return shareDataList.get(0);
        }else {
            return null;
        }
    }

    //根据用户id查询分享次数
    @Override
    public StudentShareData queryChatNumberByUserId(Long userId) {
        StudentShareData studentShareData =new StudentShareData();
        studentShareData.setIsused(0);
        studentShareData.setUserId(userId);
        List<StudentShareData>shareDataList =this.studentShareDataDao.queryAll(studentShareData);
        if (!shareDataList.isEmpty()) {
            return shareDataList.get(0);
        }else {
            studentShareData.setCreateTime(new Date());
            studentShareData.setUpdateTime(new Date());
            studentShareData.setChatNumber(5);
            studentShareData.setChatNumber(0);
            this.studentShareDataDao.insert(studentShareData);
            return studentShareData;
        }
    }
    @Override
    public int queryNumberByUserId(Long userId) {
        StudentShareData studentShareData =new StudentShareData();
        studentShareData.setIsused(0);
        studentShareData.setUserId(userId);
        List<StudentShareData>shareDataList =this.studentShareDataDao.queryAll(studentShareData);
        if (!shareDataList.isEmpty()) {
            return shareDataList.get(0).getChatNumber()+shareDataList.get(0).getNewcomerNumber();
        }else {
            studentShareData.setCreateTime(new Date());
            studentShareData.setUpdateTime(new Date());
            studentShareData.setNewcomerNumber(5);
            studentShareData.setChatNumber(0);
            this.studentShareDataDao.insert(studentShareData);
            return 5;
        }
    }

    @Override
    public StudentShareData doShare(Long userId){
        StudentShareData studentShareData =this.queryByUserId(userId);
        if (studentShareData != null) {
            studentShareData.setUpdateTime(new Date());
            studentShareData.setChatNumber(studentShareData.getChatNumber()+1);
            this.update(studentShareData);
        }else {
            studentShareData =new StudentShareData();
            studentShareData.setIsused(0);
            studentShareData.setCreateTime(new Date());
            studentShareData.setUpdateTime(new Date());
            studentShareData.setUserId(userId);
            studentShareData.setChatNumber(1);
            studentShareData.setNewcomerNumber(5);
            this.insert(studentShareData);
        }
        return this.queryByUserId(userId);
    }

    @Override
    public void doChat(Long userId){
        if (this.queryNumberByUserId(userId) > 0) {
            StudentShareData studentShareData =this.queryByUserId(userId);
            if (studentShareData != null) {
                studentShareData.setUpdateTime(new Date());
                if (studentShareData.getChatNumber() > 0) {
                    studentShareData.setChatNumber(studentShareData.getChatNumber()-1);
                }else {
                    studentShareData.setNewcomerNumber(studentShareData.getNewcomerNumber()-1);
                }
                this.update(studentShareData);
            }
        }
    }
    /**
     * 分页查询
     *
     * @param studentShareData 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<StudentShareData> queryByPage(StudentShareData studentShareData, PageRequest pageRequest) {
        long total = this.studentShareDataDao.count(studentShareData);
        return new PageImpl<>(this.studentShareDataDao.queryAllByLimit(studentShareData, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param studentShareData 实例对象
     * @return 实例对象
     */
    @Override
    public StudentShareData insert(StudentShareData studentShareData) {
        this.studentShareDataDao.insert(studentShareData);
        return studentShareData;
    }

    /**
     * 修改数据
     *
     * @param studentShareData 实例对象
     * @return 实例对象
     */
    @Override
    public StudentShareData update(StudentShareData studentShareData) {
        this.studentShareDataDao.update(studentShareData);
        return this.queryById(studentShareData.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.studentShareDataDao.deleteById(id) > 0;
    }
}
