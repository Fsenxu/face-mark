package com.heqichao.springBootDemo.base.util;

import com.heqichao.springBootDemo.base.param.RequestContext;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

/**
 * Created by heqichao on 2018-12-9.
 */
public class FileUtil {
    /**
     * 临时创建的下载目录文件
     * @param fileName
     * @return
     */
    public static File createTempDownloadFile(String fileName){
        // 获取临时目录
        String filePath = RequestContext.getContext().getSession().getServletContext().getRealPath("/download/");
        File rootDir =new File(filePath);
        if(!rootDir.exists()){
            rootDir.mkdirs();
        }
        // 在临时目录下面创建临时子目录
        File dir = new File(filePath+ System.currentTimeMillis());
        if(!dir.exists()){
            dir.mkdir();
        }
        return new File(dir.getAbsolutePath()+File.separator+fileName);
    }

    /**
     * 删除 文件
     * @param file
     */
    public static void deleteFile(File file){
        try {
            if(file!=null ){
//                file.delete();
            	//删除临时文件夹及文件
                FileUtils.deleteDirectory(file.getParentFile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String bytes2HexStringByLength(byte[] b,int length) {
        String r = "";

        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        FileInputStream fis = null;
        DataInputStream dis = null;
        String rootPath = System.getProperty("user.dir");
        String pathFile = rootPath+"/data/TEM-C-APPV2100.bin";
        File file = new File(pathFile);
        try {
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            // 开始读，采用byte数组，一次读取多个字节。最多读取“数组.length”个字节。
//            byte[] bytes = new byte[4];// 准备一个4个长度的byte数组，一次最多读取4个字节。
//            int readCount = 0;
//            // 这个方法的返回值是：读取到的字节数量。（不是字节本身）;1个字节都没有读取到返回-1(文件读到末尾)
//            while((readCount = fis.read(bytes)) != -1) {
//            	// 不应该全部都转换，应该是读取了多少个字节，转换多少个。
////                System.out.print(new String(bytes, 0, readCount));
//                System.out.print(bytesToHexString(bytes));
//            }
            byte []b = new byte [1024];
    		dis.read(b);

    		String helloHex = DatatypeConverter.printHexBinary(b);
    		System.out.printf(helloHex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	// 在finally语句块当中确保流一定关闭。
            if (fis != null) {// 避免空指针异常！
            	// 关闭流的前提是：流不是空。流是null的时候没必要关闭。
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
