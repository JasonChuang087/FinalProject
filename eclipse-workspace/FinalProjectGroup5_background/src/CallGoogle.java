import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;

import java.util.HashMap;

import java.util.ArrayList;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class CallGoogle

{

	public String searchKeyword;

	public String url;

	public String content;
	
	private ArrayList<Element> searchUrl = new ArrayList<Element>();

	public CallGoogle(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10/"; 

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
	public void query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}
		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
		for(Element li : lis)
		{	
			try 

			{
				String title = li.select(".BNeawe").get(0).text();
				String citeUrl = li.select("a").get(0).attr("href");
				citeUrl="https://google.com"+citeUrl;
				if(!citeUrl.contains("search")){
					System.out.println(title);
					System.out.println(citeUrl);
				}else {
					citeUrl="";
				}
								
				System.out.println();
				
				

			} catch (IndexOutOfBoundsException e) {


			}
		}
	}
}