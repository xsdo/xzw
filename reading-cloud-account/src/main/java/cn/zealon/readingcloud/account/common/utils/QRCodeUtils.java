package cn.zealon.readingcloud.account.common.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class QRCodeUtils {

    /**
     * 二维码颜色 默认是黑色
     */
//    private static final int QRCOLOR = 0x808080;
    private static final int QRCOLOR = 0xFF000000;

    /**
     * 背景颜色 白色
     */
    private static final int BGWHITE = 0xFFFFFFFF;


    /**
     * 编码
     */
    private static final String CHARSET = "utf-8";

    /**
     *  二维码尺寸-宽度
     */
    private static int QRCODE_WIDTH=300;

    /**
     *  二维码尺寸-高度
     */
    private static int QRCODE_HEIGHT=300;

    /**
     * 画布高度
     */
    private static int CANVAS_HEIGHT=350;

    /**
     * 画布宽度
     */
    private static  int CANVAS_WIDTH=300;

    /**
     * LOGO默认最大宽度
     */
    private static  int LOGO_WIDTH = 90;

    /**
     * LOGO默认最大高度
     */
    private static  int LOGO_HEIGHT = 90;

    /**
     * 设置logo大小
     * @param logoWidth LOGO默认最大宽度
     * @param logHeight LOGO默认最大高度
     * @return
     */
    public static void setLogoSize(int logoWidth,int logHeight){
        QRCodeUtils.LOGO_WIDTH=logoWidth;
        QRCodeUtils.LOGO_HEIGHT=logHeight;
    }

    /**
     * 设置画布大小
     * @param canvasWidth 二维码尺寸-宽度
     * @param canvasHeight 二维码尺寸-高度
     * @return
     */
    public static void setCanvasSize(int canvasWidth,int canvasHeight){
        QRCodeUtils.CANVAS_WIDTH=canvasWidth;
        QRCodeUtils.CANVAS_HEIGHT=canvasHeight;
    }

    /**
     * 设置二维码大小
     * @param qrcodeWidth 画布宽度
     * @param qrcodeHeight 画布高度
     * @return
     */
    public static void setQrCodeSize(int qrcodeWidth,int qrcodeHeight){
        QRCodeUtils.QRCODE_WIDTH=qrcodeWidth;
        QRCodeUtils.QRCODE_HEIGHT=qrcodeHeight;
    }

    /**
     * 创建纯净的二维码
     * @param content 二维码内容
     * @return
     * @throws Exception
     */
    public static BufferedImage createQRCode(String content) throws Exception {
        if (StringUtils.isBlank(content)) {
            throw new Exception("二维码内容不能为空");
        }
        //用于设置QR二维码参数
        Hashtable hints = new Hashtable();
        // 容错级别设置 默认为L
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 字符转码格式设置
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 空白边距设置
        hints.put(EncodeHintType.MARGIN, 0);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE,QRCODE_WIDTH, QRCODE_HEIGHT,hints);
        //创建一个图片缓冲区存放二维码图片
        BufferedImage image = new BufferedImage(QRCODE_WIDTH, QRCODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < QRCODE_WIDTH; x++) {
            for (int y = 0; y < QRCODE_HEIGHT; y++) {
                image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
            }
        }
        return image;
    }

    /**
     * 二维码里面插入logo
     *
     * @param source       图片对象
     * @param logoPath      logo路径
     * @param needCompress true表示将嵌入二维码的图片进行压缩，false为则表示不压缩
     * @return
     * @throws Exception
     */
    public static BufferedImage insertLogo(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo图片路径"+logoPath+"不存在");
        }
        //读取图片
        Image src = ImageIO.read(new File(logoPath));
        // 获取图像的宽度
        int width = src.getWidth(null);
        // 获取图像的宽度
        int height = src.getHeight(null);
        if (needCompress) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        //插入logo
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_WIDTH - width) / 2;
        int y = (QRCODE_HEIGHT - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.dispose();
        graph.draw(shape);
        source.flush();
        return source;
    }

    /**
     * 文件解密
     * @param file 文件对象
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 在二维码下方添加文字 --建议最后添加下方文字
     * @param image 图片对象
     * @param font 字体格式 默认宋体 28号
     * @param color 字体颜色 默认白色
     * @param pressText 下方文字
     * @return
     * @throws Exception
     */
    public static BufferedImage pressText(BufferedImage image,Font font,Color color,String pressText) throws Exception {
        if (StringUtils.isBlank(pressText)) {
            throw new Exception("文字不能为空");
        }
        pressText = new String(pressText.getBytes(), "utf-8");
        //TYPE_INT_RGB设置颜色
        BufferedImage finalImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = finalImage.createGraphics();
        //设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0,QRCODE_WIDTH,CANVAS_HEIGHT);
        // 在画布上画上二维码  X轴Y轴，宽度高度
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
        //设置画笔的颜色
        if (color==null) {
            g.setColor(Color.WHITE);
        }else{
            g.setColor(color);
        }
        if (font==null) {
            font = new Font("宋体",  Font.PLAIN, 24);
        }
        g.setFont(font);
        //drawString(文字信息、x轴、y轴)方法根据参数设置文字的坐标轴 ，根据需要来进行调整
        int startX =(QRCODE_WIDTH - g.getFontMetrics().stringWidth(pressText)) / 2;
        int startY =CANVAS_HEIGHT-(CANVAS_HEIGHT-QRCODE_HEIGHT)/2+font.getSize()/4;
        g.drawString(pressText, startX, startY);
        g.dispose();
        image=finalImage;
        image.flush();
        return image;
    }

    /**
     * 输出到本地
     * @param image 图片对象
     * @param formatName 格式 默认png
     * @param destPath 路径 默认桌面
     * @throws IOException
     */
    public static void writeToLocalByPath(RenderedImage image,String formatName,String destPath) throws IOException {
        if (formatName==null) {
            formatName="png";
        }
        if (StringUtils.isBlank(destPath)) {
            //当前用户桌面路径
            File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
            long l = System.currentTimeMillis();
            destPath= desktopDir.getAbsolutePath()+"\\"+l+"."+formatName;
        }
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        ImageIO.write(image, formatName,file);
    };

    /**
     * 输出到本地
     * @param image 图片对象
     * @param formatName 格式 默认png
     * @param file 文件对象
     * @throws IOException
     */
    public static void writeToLocalByFile(RenderedImage image,String formatName,File file) throws IOException {
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        if (formatName==null) {
            formatName="png";
        }
        ImageIO.write(image, formatName, file);
    };

    /**
     * 输出到客户端
     * @param image 图片对象
     * @param formatName 格式 默认png
     * @param response 响应
     * @throws IOException
     */
    public static void writeToStream(RenderedImage image, String formatName, HttpServletResponse response) throws IOException {
        if (formatName==null) {
            formatName="png";
        }
        ImageIO.write(image, formatName, response.getOutputStream());
    }

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
//        String content = "三年级一班";
//        BufferedImage image = QRCodeUtils.createQRCode(content);
//        image=QRCodeUtils.pressText(image,null,Color.GREEN,"三年级一班");
//        String imgPath = "F:\\Resource\\downloadimg.png";
//        image=QRCodeUtils.insertLogo(image,imgPath,true);
//        String destPath = "F:\\qrCode\\test.jpg";
        String destPath = "C:\\Users\\leishen\\Desktop\\20221123.jpeg";
//        QRCodeUtils.writeToLocalByPath(image, "jpg", null);
        String decode = QRCodeUtils.decode(new File(destPath));
        System.out.println("decode = " + decode);
    }
}
