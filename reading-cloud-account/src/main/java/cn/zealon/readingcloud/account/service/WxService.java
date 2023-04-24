package cn.zealon.readingcloud.account.service;

import org.springframework.web.multipart.MultipartFile;

public interface WxService {


    Boolean checkText(String msg);

    Boolean checkImg(MultipartFile multipartFile);

}
