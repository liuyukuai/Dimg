package com.itxiaoer.dimg.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author : liuyk
 */
public final class FileUtils {
    /**
     * Constructor
     */
    private FileUtils() {
    }

    /**
     * 将文件头转换成16进制字符串
     *
     * @param bytes
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 得到文件头
     *
     * @param inputStream 输入流
     * @return 文件头
     * @throws IOException
     */
    private static String header(InputStream inputStream) throws IOException {
        if (Objects.isNull(inputStream)) {
            return "";
        }

        byte[] bytes = new byte[28];
        try {
            inputStream.read(bytes, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return bytesToHexString(bytes);
    }

    /**
     * 判断文件类型
     *
     * @param inputStream 输入流
     * @return 文件类型
     */
    public static FileType type(InputStream inputStream) throws IOException {

        String header = header(inputStream);
        if (header.isEmpty()) {
            return null;
        }
        header = header.toUpperCase();
        return FileType.getFileType(header);
    }

}
