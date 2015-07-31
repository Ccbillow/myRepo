package org.cbillow.zhihu.get;

import java.util.ArrayList;

public class Zhihu {

	private String question ;   			//问题
	private String zhihuUrl ;			//网页链接
	private ArrayList<String> answers ;	//存储所有回答的数组
	
	public Zhihu() {
		question = "" ;
		zhihuUrl = "" ;
		answers = new ArrayList<String>() ;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getZhihuUrl() {
		return zhihuUrl;
	}
	public void setZhihuUrl(String zhihuUrl) {
		this.zhihuUrl = zhihuUrl;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "问题：" + question +  "\n链接：" 
				+ zhihuUrl + "\n回答：" + answers + "\n" ;
	}
	
}
