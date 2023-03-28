package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTable;
import cn.zealon.readingcloud.account.dao.UTableDao;
import cn.zealon.readingcloud.account.service.UTableService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户标签表(UTable)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:55
 */
@Service("uTableService")
public class UTableServiceImpl implements UTableService {
    @Resource
    private UTableDao uTableDao;

    @Resource
    private UAttributeService uAttributeService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UTable queryById(Long id) {
        return this.uTableDao.queryById(id);
    }

    @Override
    public void toTableAdd(Long userId,Long tableId){
        UTable uTable = this.uTableDao.queryById(tableId);
        if (uTable != null) {
        UAttribute uAttribute = this.uAttributeService.queryById(userId);
        if (uAttribute != null) {
            if (uAttribute.getUTableuse()==null){
                uAttribute.setUTableuse(tableId.intValue());
                uAttribute.setUTableids(tableId+"");
                uAttribute.setRemarks(uTable.getUTableName());
                this.uAttributeService.update(uAttribute);
            }else {
                String[]tableIds=uAttribute.getUTableids().split(",");
                int count=0;
                for (String id: tableIds) {
                    if (id.equals(tableId.toString())) {
                        count++;
                    }
                }
                if (count == 0) {
                    uAttribute.setUTableids(uAttribute.getUTableids()+","+tableId);
                    this.uAttributeService.update(uAttribute);
                }
            }
        }
        }
    }

    @Override
    public List<UTable>queryByIds(ArrayList ids){
        return this.uTableDao.queryByIds(ids);
    }

    /**
     * 分页查询
     *
     * @param uTable      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UTable> queryByPage(UTable uTable, PageRequest pageRequest) {
        long total = this.uTableDao.count(uTable);
        return new PageImpl<>(this.uTableDao.queryAllByLimit(uTable, pageRequest), pageRequest, total);
    }

    @Override
    public List<UTable> queryAll(UTable uTable) {
       return this.uTableDao.queryAll(uTable);
    }

    /**
     * 新增数据
     *
     * @param uTable 实例对象
     * @return 实例对象
     */
    @Override
    public UTable insert(UTable uTable) {
        this.uTableDao.insert(uTable);
        return uTable;
    }

    /**
     * 修改数据
     *
     * @param uTable 实例对象
     * @return 实例对象
     */
    @Override
    public UTable update(UTable uTable) {
        this.uTableDao.update(uTable);
        return this.queryById(uTable.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uTableDao.deleteById(id) > 0;
    }
}
