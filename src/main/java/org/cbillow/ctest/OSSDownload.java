package org.cbillow.ctest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Cbillow on 15/11/30.
 */
public class OSSDownload {

    /**
     * 从阿里云上下载文件
     *
     * 默认保存在D盘---cloudprint----该用户目录下
     *
     * @param pf
     * @param user
     */
    public void fileDownloadFromOSS(PrintFile pf, User user) {
        HttpClient httpClient = new DefaultHttpClient();
        File file;
        InputStream is = null;
        FileOutputStream fos = null;

        HttpGet get = new HttpGet(pf.getPath());
        get.setHeader("Content-Type", "application/octet-stream");
        get.setHeader("Content-Disposition", "attachment;filename=" + pf.getFilename());
        try {
            HttpResponse response = httpClient.execute(get);
            file = new File("D:/cloudprint/" + user.getNickname() + "/"+ pf.getFilename());
            is = response.getEntity().getContent();
            fos = new FileOutputStream(file);

            int ch = 0;
            while ((ch = is.read()) != -1) {
                fos.write(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
