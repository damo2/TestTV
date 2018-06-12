package com.leimo.common.file;

import java.io.*;

/**
 * Created by wangru
 * Date: 2018/6/1  16:47
 * mail: 1902065822@qq.com
 * describe:
 */

public class FileSave {
    public static void saveFileToSD(String filepath, byte[] bytes) {
        try {
            FileOutputStream output = new FileOutputStream(filepath, false);
            output.write(bytes);//将bytes写入到输出流中
            output.close(); //关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从SD卡获取文件
    public static byte[] loadFileFromSDCard(String filepath) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(filepath)));
            byte[] buffer = new byte[8 * 1024];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void saveStringToSD(String filepath, String data) {
        saveFileToSD(filepath, data.getBytes());
    }

    public static String getStringFromSDCard(String filepath) {
        byte[] bytes= loadFileFromSDCard(filepath);
        if(bytes!=null){
            return new String(bytes);
        }
        return null;
    }
}
