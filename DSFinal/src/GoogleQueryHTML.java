import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.HashMap;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQueryHTML

{

	public String searchKeyword;

	public String url;

	public String content;

	public GoogleQueryHTML(String searchKeyword)

	{

		this.searchKeyword = searchKeyword+" NBA";

		this.url = "http://www.google.com.tw/search?q="+searchKeyword+"&oe=utf8&num=10";

	}

	private String fetchContent() throws IOException

	{
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
		}
		return retVal;
	}
	public ArrayList<String[]> query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		ArrayList<String[]> array = new ArrayList<String[]>();
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
		for(Element li : lis)
		{
			try 
			{
				String[] tc = new String [2];
				String title = li.select(".BNeawe").text();
				String citeUrl = li.select("a").get(0).attr("href");
				if(!citeUrl.contains("https://google.com")&& citeUrl.startsWith("/")) {
					citeUrl = "https://google.com"+citeUrl;
				}
				if(!citeUrl.contains("search")) {          //處理掉相關搜尋
				tc[0] = title;
				tc[1] = citeUrl;
				array.add(tc);
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		
//		ArrayList<String[]> related_result = new ArrayList();
//		Elements eles = doc.select("div");
//		eles = eles.select(".X7NTVe");
//		for(Element e: eles) {
//			String item = e.text();
//			//System.out.print(e.text());
//			related_result.add(new String[] {item});
//			System.out.println(item);
//		}
		
		return array;

	}
}