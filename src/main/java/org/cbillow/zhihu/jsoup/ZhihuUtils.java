package org.cbillow.zhihu.jsoup;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ZhihuUtils {

	/**
	 * 链接到指定url，返回该页面的源代码
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Document beginSpider(String url) throws IOException {
		//连接到指定的url
		Connection conn = Jsoup.connect(url);
		//得到document对象，即源代码
		Document document = conn.get();
		return document ;
	}
	
	/**
	 * 根据相对路径得到真实路径
	 * @param url
	 * @return
	 */
	public static String getRealUrl(String url) {
		// 将http://www.zhihu.com/question/22355264/answer/21102139  
        // 转化成http://www.zhihu.com/question/22355264  
        // 否则不变
		Pattern pattern = Pattern.compile("question/(.*?)/") ;
		Matcher matcher = pattern.matcher(url) ;
		if(matcher.find()) {
			return "http://www.zhihu.com/question/" + matcher.group(1) ;
		} else {
			return "http://www.zhihu.com" + url ;
		}
	}
}
