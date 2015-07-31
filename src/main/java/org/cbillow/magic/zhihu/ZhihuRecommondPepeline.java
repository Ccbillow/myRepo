package org.cbillow.magic.zhihu;

import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class ZhihuRecommondPepeline implements Pipeline{
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void process(ResultItems paramResultItems, Task paramTask) {
		Zhihu zhihu =  null ;
		for (Map.Entry<String, Object> entry : paramResultItems.getAll().entrySet()) {
			zhihu =  new Zhihu() ;
			zhihu.setZhihuUrl(paramResultItems.getRequest().getUrl());
			zhihu.setQuestion(paramResultItems.get("question").toString());
			zhihu.setQuestionDescription(paramResultItems.get("questionDescription").toString());
			zhihu.setAnswers((List<String>) paramResultItems.get("answers"));
        }
		System.out.println(zhihu);
	}

}
