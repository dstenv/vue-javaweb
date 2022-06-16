package com.tdw.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.tdw.pojo.Good;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;


public class Base64Util {
    public String base64ToFile(String base64Img,String realPath,String imgTypes) {
        String ret_fileName = null;
        // 临时文件路径
        File file_normer = new File(realPath+"/");
        if (!file_normer.exists()) {
            file_normer.mkdirs();
        }
        if (base64Img == null) // 图像数据为空
            return "图像数据为空";
        base64Img = base64Img.replaceAll(" ","+").replace("data:image/png;base64,", "");
        //base64Img = base64Img.replace("data:image/"+imgTypes+";base64,","");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64Img);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成图片
            ret_fileName = System.currentTimeMillis()+"."+imgTypes+"";
            File file = new File(realPath + "/" + ret_fileName );
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret_fileName ;
    }

    public String Filetobase64(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream inputStream = null;
        byte[] buffer = null;
        //读取图片字节数组
        try {
            inputStream = new FileInputStream(imgFile);
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            buffer = new byte[count];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        return new BASE64Encoder().encode(buffer);
    }


}
