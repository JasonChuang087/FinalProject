import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class WordCounter {
	private String preUrl;
	private String urlStr;
    private String content;
    private ArrayList<String> sublinkUrl;
    private int sublinkHeight;
    private int keywordIndex;
	private int imgIndex;
	private int videoIndex;
	
    public WordCounter(String urlStr){
    	this.preUrl=urlStr;
    	this.urlStr = urlStr;
    	this.sublinkUrl = new ArrayList<String>();
    	this.sublinkHeight=0;
    	this.keywordIndex=0;
    	this.imgIndex=0;
    	this.videoIndex=0;
    }
    
    public String fetchContent(String inputUrl) throws IOException{
		URL url = new URL(inputUrl);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader br = new BufferedReader(inReader);
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
    public double countKeyword(String urlString, String keyword) throws IOException{
    	if (content == null){
		    content = fetchContent(this.urlStr);
		}
    	if(content.length() < 500) {
    		Document doc = Jsoup.parse(content);
    		urlStr = doc.select("a").get(0).attr("href");
    		content = fetchContent(this.urlStr);
    	}
    	content = content.toUpperCase();
		keyword = keyword.toUpperCase();
    	Document doc = Jsoup.parse(content);
    	Elements lis = doc.select("div");
    	
		for(Element li : lis) {
			for(int i=0; i<li.select("h1, h2, h3, h4, h5, h6, p, span, li").text().length(); i++) {		//嘗試h1.p等等，但文字可能放在p,h1~6,li.....
				if(content.indexOf(keyword)!=-1) {
					i=content.indexOf(keyword)+keyword.length();
					keywordIndex++;
					content=content.substring(i);
					i--;
				}
			}
		}
		

    	//以上為主Link的count
    	while(sublinkHeight<2) {
    		if(getSublink().size()>=2) {
    			countKeyword(getSublink().get(this.sublinkUrl.size()/2),keyword);
    			countKeyword(getSublink().get(this.sublinkUrl.size()/2+1),keyword);
    			sublinkHeight++;
    		}															//此處控制sublink只跑一層，用中間的兩個sublink作為Count,因為網頁通常最正中央
    	}																//會是比較有內容的
		return keywordIndex ;
    }
    public double countImgVideo(String urlString) throws IOException {
    	content = fetchContent(this.urlStr);
    	if(content.length() < 500) {
    		Document doc = Jsoup.parse(content);	
    		urlStr = doc.select("a").get(0).attr("href");
    		content = fetchContent(this.urlStr);
    	}
    	Document doc = Jsoup.parse(content);
    	Elements lis = doc.select("div");
    	
    	for(Element li:lis) {
    		if(li.select("a").attr("href").contains("VIDEO")) {
    			videoIndex++;
    		}
    	}
    	for(Element li :lis.select("img, figure")) {				//有些圖片是包在Figure or img裡面 or (figure包img)，因此要讀不一樣的tag
    		if(li!=null){
    		imgIndex++;
    		}
    	}
    	for(Element li : lis.select("video")) {
    		if(li!=null){
        		videoIndex++;
        		}
    	}
    	//以上為主Link的count
    	while(sublinkHeight<2) {
    		if(getSublink().size()>=2) {
    			countImgVideo(getSublink().get(this.sublinkUrl.size()/2));
    			countImgVideo(getSublink().get(this.sublinkUrl.size()/2+1));
    			sublinkHeight++;
    		}															//此處控制sublink只跑一層，用中間的兩個sublink作為Count,因為網頁通常最正中央
    	}				
    	return imgIndex * 1.5 + videoIndex* 3;
    }
    
    public ArrayList<String> getSublink()throws IOException {
    	String content2="";
    	String thisurl="";
		content2 = fetchContent(this.urlStr);
    	Document doc = Jsoup.parse(content2);
    	Elements lis = doc.select("div");
    	for(Element li:lis) {
    		thisurl=li.select("a").attr("href");
    		if(thisurl.startsWith("/") && !thisurl.startsWith("//") && !thisurl.contains("picture") && !thisurl.contains("video") && !thisurl.contains("app") &&!thisurl.contains("#") && thisurl.length()>5) {    // !thisurl.contains("video") && 
    			if(isDomain(thisurl)) {
    				this.sublinkUrl.add(thisurl);
    				System.out.println(thisurl);
    			}
    		}
    	}
    	return this.sublinkUrl;
    }
    public boolean isDomain(String sublink) throws MalformedURLException {
    	if(new URL(this.preUrl).getHost().equals(new URL(sublink).getHost())) {
    		return true;
    	}
    	return false;
    }
}
