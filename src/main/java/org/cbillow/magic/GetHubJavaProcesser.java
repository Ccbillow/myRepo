package org.cbillow.magic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GetHubJavaProcesser implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000) ;
	
	public void process(Page page) {
		List<String> urls = page.getHtml().css("div.pagination").links().all();
		page.addTargetRequests(urls);
	}

	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
		Spider.create(new GetHubJavaProcesser())
			.addUrl("https://github.com/search?l=Java&p=1&q=stars%3A%3E1&s=stars&type=Repositories")
			.addPipeline(new ConsolePipeline()).run(); 
	}
	
}
