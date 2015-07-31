package org.cbillow.magic.zhihu;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.model.annotation.HelpUrl;

@HelpUrl("http://www.zhihu.com/explore/recommendations")
public class Zhihu {

	private String question ;   			//问题
	private String zhihuUrl ;			//网页链接
	private String questionDescription ;//问题描述
	private List<String> answers ;	//存储所有回答的数组
	
	public Zhihu() {}
	public Zhihu(String question, String zhihuUrl, String questionDescription,
			ArrayList<String> answers) {
		this.question = question;
		this.zhihuUrl = zhihuUrl;
		this.questionDescription = questionDescription;
		this.answers = answers;
	}


	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
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
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "Zhihu [question=" + question + ", zhihuUrl=" + zhihuUrl
				+ ", questionDescription=" + questionDescription + ", answers="
				+ answers + "]";
	}
	
}
