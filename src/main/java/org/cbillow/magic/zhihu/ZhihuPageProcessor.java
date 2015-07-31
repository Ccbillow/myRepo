package org.cbillow.magic.zhihu;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ZhihuPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000) ;
	
	public Site getSite() {
		return site;
	}

	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		List<String> urls = page.getHtml().xpath("//a[@class='question_link']")
				.links().regex("(http://www.zhihu.com/question/\\w+)").all() ;
		page.addTargetRequests(urls) ;
		page.putField("question"
				, page.getHtml().xpath("//h2[@class='zm-item-title zm-editable-content']/text()"));
		page.putField("questionDescription"
				, page.getHtml().xpath("//div[@class='zm-editable-content']/text()"));
		page.putField("answers"
				, page.getHtml().css("div.zm-item-answer ")
				.xpath("//div[@data-action='/answer/content']/div[@class='zm-editable-content']/text()").all());
		
		//page.putField("urls", page.getHtml().links().regex("(http://www.zhihu.com/question/\\w+)").all().size());
	}
	
	public static void main(String[] args) {
        Spider.create(new ZhihuPageProcessor())
        	.addUrl("http://www.zhihu.com/explore/recommendations")
        	.addPipeline(new ZhihuRecommondPepeline())
        	.thread(5).run();
    }

}
