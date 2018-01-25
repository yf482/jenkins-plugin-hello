package org.jenkinsci.plugins.sample;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author yunfeng
 * @version V.1.0
 * @title
 * @Desc
 * @create 2018-01-25 1:34
 **/
public class HttpFileDownload {
    /**
     * 通过HTTP方式获取文件
     *
     * @param strUrl
     * @param fileName
     * @return
     * @throws IOException
     */
    private static boolean  getRemoteFile(String strUrl, String fileName) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        支持FTP方式的获取
//        URLConnection conn = url.openConnection();
        DataInputStream input = new DataInputStream(conn.getInputStream());
        DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName));
        byte[] buffer = new byte[1024 * 8];
        int count = 0;
        while ((count = input.read(buffer)) > 0) {
            output.write(buffer, 0, count);
        }
        output.close();
        input.close();
        return true;
    }

    @Test
    public void test1() {
        String fileUrl = "http://central.maven.org/maven2/HTTPClient/HTTPClient/maven-metadata.xml";
//        String fileUrl = "http://central.maven.org/maven2/HTTPClient/HTTPClient/";
//        String fileUrl = "http://central.maven.org/maven2/HTTPClient/";
        String fileName = "D:/maven-metadata.xml";
//        String fileName = "D:/testdir";
//        String fileName = "D:/testdir2";
        try {
            System.out.println(getRemoteFile(fileUrl, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
