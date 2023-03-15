package cn.zealon.readingcloud.book.vo;

import cn.zealon.readingcloud.common.pojo.xzwresources.CDiscuss;
import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import lombok.Data;

@Data
public class DiscussComVO {

    private CDiscuss cDiscuss;

    private Composition composition;

    private CNote note;

    private Circle circle;
}
