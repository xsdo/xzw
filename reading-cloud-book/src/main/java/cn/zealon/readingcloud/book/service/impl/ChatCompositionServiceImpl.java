package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.common.config.SmsConfig;
import cn.zealon.readingcloud.book.common.utils.HttpRequest;
import cn.zealon.readingcloud.book.service.StudentShareDataService;
import cn.zealon.readingcloud.common.pojo.xzwresources.ChatComposition;
import cn.zealon.readingcloud.book.dao.ChatCompositionDao;
import cn.zealon.readingcloud.book.service.ChatCompositionService;
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
 * chat作文表(ChatComposition)表服务实现类
 *
 * @author makejava
 * @since 2023-04-25 15:18:24
 */
@Service("chatCompositionService")
public class ChatCompositionServiceImpl implements ChatCompositionService {
    @Resource
    private ChatCompositionDao chatCompositionDao;

    @Resource
    private SmsConfig smsConfig;

    @Resource
    private StudentShareDataService studentShareDataService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ChatComposition queryById(Long id) {
        return this.chatCompositionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param chatComposition 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    @Override
    public Page<ChatComposition> queryByPage(ChatComposition chatComposition, PageRequest pageRequest) {
        long total = this.chatCompositionDao.count(chatComposition);
        return new PageImpl<>(this.chatCompositionDao.queryAllByLimit(chatComposition, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param chatComposition 实例对象
     * @return 实例对象
     */
    @Override
    public ChatComposition insert(ChatComposition chatComposition) {
        this.chatCompositionDao.insert(chatComposition);
        return chatComposition;
    }

    @Override
    public Object getAnswer(Long userId,String grade ,String type ,int wordNumber,String keyword ){
        if (this.studentShareDataService.queryNumberByUserId(userId)<1){
            return "该用户AI范文助手未激活";
        }
        if (wordNumber > 1000){
            return "字数不符合规范";
        }
        if (userId==null){
            return "未传用户id";
        }
        String urlChat = smsConfig.getChatUrl();
        String password = smsConfig.getChatPassword();
        String writeType = type+"作文";
        if (type.equals("诗歌")){
            writeType = "诗歌";
        }else if (type.equals("读后感")){
            writeType ="读后感";
        }
        String msg = "我要你担任作文老师。我会提供一些与作文内容相关的话题，你的工作就是用小学生的口吻写这些内容。" +
                "这可能包括提供一篇完整的作文，根据提供的作文题材来进行相关创作，题材可能包括写人、记事、想象、写景、状物、诗歌、读后感等方向。" +
                "我的第一个请求是“模仿"+grade + "学生，写一篇"+keyword+"的"+writeType + "，字数要求"+wordNumber + "字”，标题用《》显示";
        Map<String,Object> mapper = new HashMap<>();
        mapper.put("msg",msg);
        System.out.println(msg);
        JSONObject jsonObject = new JSONObject(mapper);
        String content = HttpRequest.sendPostChat(urlChat,jsonObject,password);
        System.out.println(content);
        String title="";
        String context="";
        Boolean checkChat=false;
        if (content != null && content.contains("》")){
//            if (content.contains("读后感")){
//                String[]contentList=content.split("读后感",2);
//                if (contentList.length > 0) {
//                    checkChat=true;
//                    for (int i = 0; i < contentList.length; i++) {
//                        if (contentList[i].startsWith("《")&&contentList[i].endsWith("》")){
//                            title=contentList[i]+"读后感";
//                        }else {
//                            context=contentList[i];
//                        }
//                    }
//                }
//            }else {
                String[]contentList=content.split("》",2);
                if (contentList.length > 0) {
                    checkChat=true;
                    for (int i = 0; i < contentList.length; i++) {
                        if (contentList[i].startsWith("《")){
                            title=contentList[i]+"》";
                        }else {
                            context=contentList[i];
                        }
                    }
                }
//            }

        }
        if (checkChat){
            ChatComposition composition = new ChatComposition();
            composition.setIsused(0);
            composition.setCreateTime(new Date());
            composition.setUpdateTime(new Date());
            composition.setGatetype(type);
            composition.setTitle(title);
            composition.setContent(context);
            composition.setWords(wordNumber);
            composition.setGrade(grade);
            composition.setKeywords(keyword);
            composition.setUserId(userId);
            this.insert(composition);
            this.studentShareDataService.doChat(userId);
        }
        return content;
    }

    @Override
    public List<ChatComposition>queryByUserId(Long userId) {
        ChatComposition chatComposition =new ChatComposition();
        chatComposition.setUserId(userId);
        return this.chatCompositionDao.queryAll(chatComposition);
    }

    /**
     * 修改数据
     *
     * @param chatComposition 实例对象
     * @return 实例对象
     */
    @Override
    public ChatComposition update(ChatComposition chatComposition) {
        this.chatCompositionDao.update(chatComposition);
        return this.queryById(chatComposition.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.chatCompositionDao.deleteById(id) > 0;
    }
}
