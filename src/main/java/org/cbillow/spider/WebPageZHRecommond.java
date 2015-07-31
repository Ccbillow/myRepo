package org.cbillow.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  *  抓取知乎的推荐
  */
public class WebPageZHRecommond {

	static String sendGet(String url) {
		String result = "" ;		//定义一个字符串来存储抓取网页的内容
		BufferedReader br = null ;	//定义一个缓冲字符的流
		
		try {
			URL realUrl = new URL(url) ;						//将string生成url对象
			URLConnection conn = realUrl.openConnection() ;		//初始化一个链接到那个url的连接
			conn.connect(); 									//开始实际的连接
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")) ;//初始化 BufferedReader 输入流来读取url的响应
			String line ;										//用来临时存储抓取的每一行数据
			while((line = br.readLine()) != null) {
				result += line ;								//遍历抓取到的每一行将其存到result里面
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {					
			try {
				br.close();										//关闭流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result ;
	}
	
	static ArrayList<String> RegexString(String targetStr, String patternStr) {
		ArrayList<String> results = new ArrayList<String>() ;
		//定义一个样式模板，里面使用了正则表达式，括号中是要抓取的内容
		Pattern pattern = Pattern.compile(patternStr) ;
		//定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr) ;
		boolean isFind = matcher.find() ;						//如果找到了
		while(isFind) {											//使用循环将所有匹配正则的字符串添加到results里面
			results.add(matcher.group(1)) ;						//添加成功匹配的结果
			isFind = matcher.find() ;							//继续查找下一个对象
		}
		return results ;
	}
	
	
	public static void main(String[] args) {
		String url = "http://www.zhihu.com/explore/recommendations" ;					//定义即将访问的链接
		String result = sendGet(url) ;							//获取链接并获取页面内容
		System.out.println(result);
		ArrayList<String> imgSrc = RegexString(result, "question_link.+?>(.+?)<") ;	//使用正则匹配图片的src内容
		System.out.println(imgSrc);								//打印出结果
	}
	
}
