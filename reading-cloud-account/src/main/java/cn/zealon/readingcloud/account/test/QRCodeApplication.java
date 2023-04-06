package cn.zealon.readingcloud.account.test;


import cn.zealon.readingcloud.account.common.utils.QRCodeUtil;

/**
 * @author: 邹祥发
 * @date: 2021/11/8 13:50
 */
public class QRCodeApplication {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        // 二维码中的内容可以是文字，可以是链接等
        String text = "https://xzw.aace.com.cn/book/composition/503229286551916544";
        // 生成的二维码的路径及名称
        String destPath = "f:\\code\\" + System.currentTimeMillis() + ".jpg";
        //生成二维码
        QRCodeUtil.encode(text, null, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }
}
