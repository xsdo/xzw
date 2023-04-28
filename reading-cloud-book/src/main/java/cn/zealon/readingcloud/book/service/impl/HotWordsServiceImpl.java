package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.common.utils.StrSimilarityUtils;
import cn.zealon.readingcloud.common.pojo.xzwresources.HotWords;
import cn.zealon.readingcloud.book.dao.HotWordsDao;
import cn.zealon.readingcloud.book.service.HotWordsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 热搜表(HotWords)表服务实现类
 *
 * @author makejava
 * @since 2023-04-27 15:24:36
 */
@Service("hotWordsService")
public class HotWordsServiceImpl implements HotWordsService {
    @Resource
    private HotWordsDao hotWordsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */

    @Override
    public HotWords queryById(Long id) {
        return this.hotWordsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hotWords    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<HotWords> queryByPage(HotWords hotWords, PageRequest pageRequest) {
        long total = this.hotWordsDao.count(hotWords);
        return new PageImpl<>(this.hotWordsDao.queryAllByLimit(hotWords, pageRequest), pageRequest, total);
    }

    @Override
    public List<HotWords> queryAll(HotWords hotWords) {
        return this.hotWordsDao.queryAll(hotWords);
    }

    @Override
    public void addHotWords(String words){
        if (words.length() >=2){
            HotWords hotWords = new HotWords();
            hotWords.setIsused(0);
            List<HotWords> list = this.queryAll(hotWords);
            int count =0;
            if (list != null && list.size() > 0) {
                for (HotWords hw:list) {
                    if (StrSimilarityUtils.getSimilarityRatio(hw.getName(),words)>0.4){
                        hw.setSearchTimes(hw.getSearchTimes()+1);
                        this.update(hw);
                        count++;
                    }
                }
            }
            if (count == 0) {
                hotWords.setName(words);
                hotWords.setSearchTimes(1);
                this.insert(hotWords);
            }
        }

    }


    /**
     * 清理热词
     *
     *
     */
    @Override
    public void cleanLikes(){
        HotWords hotWords = new HotWords();
        hotWords.setIsused(0);
        List<HotWords>hotWordsList =this.queryAll(hotWords);
        if (hotWordsList!=null&&hotWordsList.size() > 0){
            for (HotWords hote: hotWordsList) {
                hote.setRemarks("-1");
                hote.setSearchTimes(1);
                this.update(hote);
            }
        }
    }
    @Override
    public List<HotWords>queryTop(){
        return this.hotWordsDao.queryTop(8);
    }
    @Override
    public List<HotWords>queryLikes() {
        List<HotWords>likes=new ArrayList<>();
        HotWords hotWords = new HotWords();
        hotWords.setIsused(0);
        hotWords.setRemarks("1");
        List<HotWords> suggestList = this.hotWordsDao.queryAll(hotWords);
        if (suggestList != null && suggestList.size() > 0) {
            for (HotWords hote: suggestList) {
                likes.add(hote);
            }
        }
        List<HotWords> tops = this.queryTop();
        if (tops != null && tops.size() > 0) {
            for (HotWords hote: tops) {
                if (!likes.contains(hote)){
                    likes.add(hote);
                    if (likes.size() >=8){
                        break;
                    }
                }
            }
        }

        return likes;
    }

    /**
     * 新增数据
     *
     * @param hotWords 实例对象
     * @return 实例对象
     */
    @Override
    public HotWords insert(HotWords hotWords) {
        this.hotWordsDao.insert(hotWords);
        return hotWords;
    }

    /**
     * 修改数据
     *
     * @param hotWords 实例对象
     * @return 实例对象
     */
    @Override
    public HotWords update(HotWords hotWords) {
        this.hotWordsDao.update(hotWords);
        return this.queryById(hotWords.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hotWordsDao.deleteById(id) > 0;
    }
}
