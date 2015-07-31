package org.cbillow.spider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;


public class HttpClientTest {
	
	private static HttpClient httpClient = new HttpClient() ;
	
	public static void main(String[] args) throws HttpException, IOException {
		HttpClientTest.downloadPage("http://www.baidu.com") ;
	}
	
	 /** 
     * @param path 
     *            目标网页的链接 
     * @return 返回布尔值，表示是否正常下载目标页面 
     * @throws Exception 
     *             读取网页流或写入本地文件流的IO异常 
     */
	public static boolean downloadPage(String path) throws HttpException, IOException {
		InputStream is = null ;
		OutputStream os = null ;
		
		// 得到 post 方法  
		GetMethod gm = new GetMethod(path) ;
		
		// 执行，返回状态码
		int statusCode = httpClient.executeMethod(gm) ;
		
		// 简单起见，只处理返回值为 200 的状态码  
		if(statusCode == HttpStatus.SC_OK) {
			is = gm.getResponseBodyAsStream() ;
			// 通过对URL的得到文件名 
			String filename = path.substring(path.lastIndexOf('/')+1) ;
			// 获得文件输出流
			os = new FileOutputStream(filename) ;
			
			// 输出到文件 
			int tempByte = -1 ;
			while((tempByte=is.read()) > 0) {
				os.write(tempByte);
			}
			
			if(is != null) {
				is.close();
			}
			
			if(os != null) {
				os.close(); 
			}
			return true ;
		}
		return false ;
	}

}
