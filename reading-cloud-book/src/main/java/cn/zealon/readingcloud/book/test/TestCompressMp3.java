package cn.zealon.readingcloud.book.test;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;


public class TestCompressMp3 {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stubtestCompressMp3Samll();
        testCompressMp3Samll();
    }
        public static void testCompressMp3Samll() throws Exception {
            Long t1 = System.currentTimeMillis();
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            //设置比特率
            audio.setSamplingRate(44100);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            //设置格式
            attrs.setAudioAttributes(audio);
            attrs.setDuration(360f);
            // 设置截取的时长
            Encoder encoder = new Encoder();
            encoder.encode(new File("D:/test/体育旅行社.mp3"), new File("D:/test/target64.mp3"), attrs);
            Long t2 = System.currentTimeMillis();
            System.out.println("消耗：" + (t2 - t1) + "ms");
        }
    }
