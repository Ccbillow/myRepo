package org.cbillow.zhihu.get;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String url = "http://www.zhihu.com/explore/recommendations" ;
		String content = Spider.getHtmlContent(url) ;    			//得到网页源代码
		ArrayList<Zhihu> myZhihu = Spider.GetZhihu(content) ;		//根据规则匹配
		System.out.println(myZhihu);
	}
}
