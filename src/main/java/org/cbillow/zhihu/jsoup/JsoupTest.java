package org.cbillow.zhihu.jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static List<Zhihu> jsoupSprid(String url) {

		List<Zhihu> results = null ;			//存放最后结果
		List<String> temps = null ;				//临时存放答案，将Elements对象变为String
		try {
			results = new ArrayList<Zhihu>() ;
			
			Document document = ZhihuUtils.beginSpider(url) ;	//得到推荐页面的源码
	
			Elements elements = document.getElementsByClass("question_link") ;		//根据class查找元素，推荐页面问题的节点
			
			//遍历查找到的问题，封装到results里面
			for(Element s : elements) {
				Zhihu temp = new Zhihu() ;
				
				String queUrl = ZhihuUtils.getRealUrl(s.attr("href")) ;		//得到问题的真实链接
				Document queDocument = ZhihuUtils.beginSpider(queUrl) ;		//得到问题的源码
				
				Element queDesc = queDocument.getElementById("zh-question-detail") ;		//查找问题描述
				Elements queAnses = queDocument.getElementsByAttributeValue("data-action", "/answer/content") ;//查找问题的所有回答
				
				//将Elements对象变为String
				temps = new ArrayList<String>() ;
				for(Element answer : queAnses) {
					temps.add(answer.text()) ;
				}
				
				temp.setQuestion(s.text());		//得到元素中文本
				temp.setZhihuUrl(queUrl);
				temp.setQuestionDescription(queDesc.text());
				temp.setAnswers(temps);
				results.add(temp) ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results ;
	}
	
	public static void writeIntoFile(List<Zhihu> results) {
		File file = null ;
		BufferedWriter bw = null ;
		try {
			file = new File("zhihuRecommond.txt") ;
			bw = new BufferedWriter(new FileWriter(file)) ;
			bw.write(results.toString());
		} catch (IOException e) {
			e.printStackTrace(); 
		} finally {
			try {
				bw.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
