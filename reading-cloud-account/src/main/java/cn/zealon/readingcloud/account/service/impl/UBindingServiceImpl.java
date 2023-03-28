package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.account.dao.UBindingDao;
import cn.zealon.readingcloud.account.service.UBindingService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * 绑定班级表(UBinding)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
@Service("uBindingService")
public class UBindingServiceImpl implements UBindingService {
    @Resource
    private UBindingDao uBindingDao;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private UTeacherService uTeacherService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UBinding queryById(Long id) {
        return this.uBindingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uBinding    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UBinding> queryByPage(UBinding uBinding, PageRequest pageRequest) {
        long total = this.uBindingDao.count(uBinding);
        return new PageImpl<>(this.uBindingDao.queryAllByLimit(uBinding, pageRequest), pageRequest, total);
    }

    @Override
    public List<UBinding>queryAll(UBinding uBinding){
        return this.uBindingDao.queryAll(uBinding);
    }

    @Override
    public List<UBinding> queryByTeacherId(Long teacherId){
        List<UBinding> list=new ArrayList<>();
        //找出老师id
        UTeacher teacher=this.uTeacherService.queryById(teacherId);
        if (teacher != null) {
            UBinding uBinding=new UBinding();
            uBinding.setIsused(0);
            uBinding.setTeacherId(teacherId);
            List<UBinding> bindings =this.queryAll(uBinding);
            for (UBinding ub: bindings) {
                if (ub.getUserId()!=teacher.getTeacherId()){
                    list.add(ub);
                }
            }
        }

        return list;
    }

    @Override//绑定班级
    public JSONObject doBinding(Long userId,Long teacherId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UAttribute uAttribute =this.uAttributeService.queryById(userId);
            if (uAttribute != null) {
                if (uAttribute.getUType()==5) {
                    result.put("sign", -1);
                    data.put("data", "班主任不可加入其他班级");
                }else {
                    UBinding ubinding = new UBinding();
                    ubinding.setIsused(0);
                    ubinding.setUserId(userId);
                    ubinding.setTeacherId(teacherId);
                    List<UBinding>uBindingList=this.uBindingDao.queryAll(ubinding);
                    if (uBindingList.size() > 0) {
                        UBinding u=uBindingList.get(0);
                        if (u!=null){
                            if (u.getBStatus() == 1){
                                result.put("sign", 00);
                                data.put("data", "您已经是该班级成员");
                            }else  {
                                u.setBStatus(0);
                                u.setUpdateTime(new Date());
                                u.setScantime(new Date());
                                this.uBindingDao.update(u);
                                result.put("sign", 00);
                                data.put("data", "申请成功，快让班主任同意进班吧");
                            }
                        }
                    }else {
                        ubinding.setCreateTime(new Date());
                        ubinding.setUpdateTime(new Date());
                        ubinding.setScantime(new Date());
                        ubinding.setBStatus(0);
                        this.uBindingDao.insert(ubinding);
                        result.put("sign", 00);
                        data.put("data", "申请成功，快让班主任同意进班吧");
                    }

                }
            }

            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Override
    public JSONObject conductBingding(Long userId,Long bingdingId,int type){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UBinding ubing = this.queryById(bingdingId);
            if (ubing != null){
                UTeacher uteacher=this.uTeacherService.queryById(ubing.getTeacherId());
                if (uteacher != null) {
                    if (uteacher.getTeacherId()!=userId){
                        result.put("sign", -1);
                        data.put("data", "非本班教师不可修改");
                    }else{
                        if (type==1){
                            ubing.setBindtime(new Date());
                            ubing.setBStatus(1);
                            this.update(ubing);
                            UAttribute u=this.uAttributeService.queryById(ubing.getUserId());
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(ubing.getTeacherId());
                                this.uAttributeService.update(u);
                            }
                            result.put("sign", 00);
                            data.put("data", "加入班级成功");
                        }else if(type==2){
                            this.deleteById(bingdingId);
                            UAttribute u=this.uAttributeService.queryById(ubing.getUserId());
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(new Long(-1));
                                this.uAttributeService.update(u);
                            }
                            result.put("sign", 00);
                            data.put("data", "已删除");
                        }
                    }
                }

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
     * 新增数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    @Override
    public UBinding insert(UBinding uBinding) {
        this.uBindingDao.insert(uBinding);
        return uBinding;
    }

    /**
     * 修改数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    @Override
    public UBinding update(UBinding uBinding) {
        this.uBindingDao.update(uBinding);
        return this.queryById(uBinding.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uBindingDao.deleteById(id) > 0;
    }
}
