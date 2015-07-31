package org.cbillow.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取网页源代码
 * @author Cbillow
 *
 */
public class WebPageSourceCode {

	public static void main(String[] args) {
		int responseCode ;
		
		URL url ;
		HttpURLConnection huc ;
		BufferedReader br ;
		
		try {
			url = new URL("http://www.qq.com");
			huc = (HttpURLConnection) url.openConnection();
			responseCode = huc.getResponseCode() ;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "utf-8")) ;
				while(br.readLine() != null) {
					System.out.println(br.readLine());
				}
			} else {
				System.out.println("找不到这个网页!!!");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
