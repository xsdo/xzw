package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import cn.zealon.readingcloud.book.dao.CNoteDao;
import cn.zealon.readingcloud.book.service.CNoteService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 笔记表(CNote)表服务实现类
 *
 * @author makejava
 * @since 2023-03-08 14:27:23
 */
@Service("cNoteService")
public class CNoteServiceImpl implements CNoteService {
    @Resource
    private CNoteDao cNoteDao;

    @Resource
    private UserAttributeClient userAttributeClient;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CNote queryById(Long id) {
        return this.cNoteDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cNote       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CNote> queryByPage(CNote cNote, PageRequest pageRequest) {
        long total = this.cNoteDao.count(cNote);
        return new PageImpl<>(this.cNoteDao.queryAllByLimit(cNote, pageRequest), pageRequest, total);
    }
    @Override
    public List<CNote> queryAll(CNote cNote) {
        cNote.setIsused(0);
        return this.cNoteDao.queryAll(cNote);
    }

    @Override
    public JSONObject doNode(Long userId, String note){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询用户
            UAttribute uAttribute=userAttributeClient.queryByUserId(userId);
            System.out.println(uAttribute.getId());
            CNote cNote = new CNote();
            if (uAttribute != null) {
                cNote.setIsused(0);
                cNote.setStatus(2);
                cNote.setUserId(uAttribute.getId());
                cNote.setUserName(uAttribute.getQqnum());
                cNote.setUserHead(uAttribute.getPortrait());
                cNote.setUserTable(uAttribute.getRemarks());
                cNote.setContent(note);
                cNote.setDiscuss(0);
                cNote.setLikes(0);
                cNote.setReadtimes(0);
                cNote.setType(0);
                cNote.setCreateTime(new Date());
                cNote.setUpdateTime(new Date());
                this.cNoteDao.insert(cNote);
                data.put("note",cNote);
                result.put("sign",00);
                data.put("data","发布成功");
            }else {
                result.put("sign",-1);
                data.put("data","未找到用户信息");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return  result;
    }

    /**
     * 新增数据
     *
     * @param cNote 实例对象
     * @return 实例对象
     */
    @Override
    public CNote insert(CNote cNote) {
        this.cNoteDao.insert(cNote);
        return cNote;
    }

    /**
     * 修改数据
     *
     * @param cNote 实例对象
     * @return 实例对象
     */
    @Override
    public CNote update(CNote cNote) {
        this.cNoteDao.update(cNote);
        return this.queryById(cNote.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cNoteDao.deleteById(id) > 0;
    }
}
