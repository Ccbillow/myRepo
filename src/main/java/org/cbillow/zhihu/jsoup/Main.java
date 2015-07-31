package org.cbillow.zhihu.jsoup;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		String url = "http://www.zhihu.com/explore/recommendations";
		List<Zhihu> results = JsoupTest.jsoupSprid(url) ;
//		System.out.println(results.get(0).getAnswers().get(0));
//		System.out.println(results.get(0).getQuestion());
//		System.out.println(results.get(0).getQuestionDescription());
		JsoupTest.writeIntoFile(results);
	}
}
