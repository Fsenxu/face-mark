package com.heqichao.springBootDemo.megprotocol;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

@Slf4j
public class Utils {

    public interface CommonFunction {
        static byte[] getContent(String filePath) throws IOException {
            File file = new File(filePath);
            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                log.error("file too big...");
                return null;
            }

            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
            && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }

            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            fi.close();

            return buffer;
        }

        static String getMd5ByFile(File file) throws FileNotFoundException {
            String value = null;

            FileInputStream in = new FileInputStream(file);
            try {
                MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(byteBuffer);
                BigInteger bi = new BigInteger(1, md5.digest());
                value = bi.toString(16);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return value;
        }

        static void waitMs(int ms) {
            try {
                TimeUnit.MILLISECONDS.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
        *
        * @Description byte数组转换为无符号short整数
        * @param bytes
        * @param off
        * @return
        */
        static int byte2ToUnsignedShort(byte[] bytes, int off) {
            // 注意高位在后面，即大小端问题
            int low = bytes[off + 1];
            int high = bytes[off];
            return (high << 8 & 0xFF00) | (low & 0xFF);
        }

        /**
        *
        * @Description byte数组转换为int整数
        * @param bytes
        * @param off
        * @return
        */
        static int byte4ToInt(byte[] bytes, int off) {
            // 注意高位在后面，即大小端问题
            int b3 = bytes[off + 3] & 0xFF;
            int b2 = bytes[off + 2] & 0xFF;
            int b1 = bytes[off + 1] & 0xFF;
            int b0 = bytes[off + 0] & 0xFF;
            return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
        }

        /**
        *
        * @Description byte数组转换为int整数
        * @param bytes
        * @param off
        * @return
        */
        static long byte8ToLong(byte[] bytes, int off) {
            // 注意高位在后面，即大小端问题
            long b7 = bytes[off + 7] & 0xFF;
            long b6 = bytes[off + 6] & 0xFF;
            long b5 = bytes[off + 5] & 0xFF;
            long b4 = bytes[off + 4] & 0xFF;
            long b3 = bytes[off + 3] & 0xFF;
            long b2 = bytes[off + 2] & 0xFF;
            long b1 = bytes[off + 1] & 0xFF;
            long b0 = bytes[off + 0] & 0xFF;
            return (b0 << 56) | (b1 << 48) | (b2 << 40) | (b3 << 32) | (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;
        }

        static long getUtcTime() {
            // 1、取得本地时间：
            Calendar cal = Calendar.getInstance() ;
            // 2、取得时间偏移量：
            int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
            // 3、取得夏令时差：
            int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
            // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
            cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
            return cal.getTimeInMillis()/1000;
        }
    }
}
