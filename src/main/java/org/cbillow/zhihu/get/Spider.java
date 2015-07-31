package org.cbillow.zhihu.get;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
  *  抓取知乎的推荐
  */
public class Spider {

	public static String sendGet(String url) {
		StringBuffer sb = new StringBuffer() ;		//定义一个字符串来存储抓取网页的内容
		BufferedReader br = null ;	//定义一个缓冲字符的流
		
		try {
			URL realUrl = new URL(url) ;						//将string生成url对象
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection() ;		//初始化一个链接到那个url的连接
			conn.connect(); 									//开始实际的连接
			br = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;//初始化 BufferedReader 输入流来读取url的响应
			String line ;										//用来临时存储抓取的每一行数据
			while((line = br.readLine()) != null) {
				sb.append(line) ;								//遍历抓取到的每一行将其存到result里面
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
		System.out.println(sb.toString());
		return sb.toString() ;
	}
	
	
	
	/**
	 * 根据url得到网页源代码
	 * @param htmlUrl
	 * @return
	 */
	public static String getHtmlContent(String htmlUrl) {
		URL url = null;
		String result = null; 
		InputStream is;
		try {
			for (int i = 0; i < 1; i++) {
				url = new URL(htmlUrl);
				byte bytes[] = new byte[1024 * 100];						//用 byte 数组还存网页代码
				int index = 0;												//记录一个索引
				is = url.openStream();										//打开输入流
				int count = is.read(bytes, index, 1024 * 10);				//读 1024*10 个字节
				while (count != -1) {
					index += count;
					count = is.read(bytes, index, 1);						//一个一个的读
				}
				ByteArrayInputStream biArrayInputStream = new ByteArrayInputStream(bytes);	
				result = uncompress(biArrayInputStream, "utf-8") ;
				is.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result ;
	}
	
	/**
	 * 解压
	 * @param in
	 * @param charset
	 * @return
	 */
	public static String uncompress(ByteArrayInputStream in, String charset) {
		try {
			GZIPInputStream gInputStream = new GZIPInputStream(in);
			byte[] by = new byte[1024];
			StringBuffer strBuffer = new StringBuffer();
			int len = 0;
			while ((len = gInputStream.read(by)) != -1) {
				strBuffer.append(new String(by, 0, len, charset));
			}
			return strBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 匹配规则来抓取想要的内容
	 * @param content
	 * @return
	 */
	public static ArrayList<Zhihu> GetZhihu(String content) {
		ArrayList<Zhihu> results = new ArrayList<Zhihu>();
		
		//用来匹配标题
		Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
		Matcher questionMatcher = questionPattern.matcher(content) ;
		
		//用来匹配url，也就是问题的链接
		Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
		Matcher urlMatcher = urlPattern.matcher(content);
		
		boolean isFind = questionMatcher.find() && urlMatcher.find() ;	//问题和链接均要能找到
		
		while(isFind) {
			Zhihu temp = new Zhihu() ;
			temp.setQuestion(questionMatcher.group(1)) ;
			temp.setZhihuUrl("http://www.zhihu.com" + urlMatcher.group(1)) ;
			
			results.add(temp) ;											//添加匹配成功的结果
			isFind = questionMatcher.find() && urlMatcher.find() ;		//继续下一个查找
		}
		
		return results ;
	}
	
}
