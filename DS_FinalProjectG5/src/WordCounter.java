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
//import java.util.ArrayList;

public class WordCounter {
	private String urlStr;
    private String content;
//    private ArrayList<String> sublinkUrl=new ArrayList<String>();
    private int keywordIndex;
	private int imgIndex;
	private int videoIndex;
	
    public WordCounter(String urlStr) throws IOException{
    	this.urlStr = urlStr;
//    	this.sublinkUrl=getSublink();
    	this.keywordIndex=0;
    	this.imgIndex=0;
    	this.videoIndex=0;
    }
    
    public String fetchContent(String inputUrl) throws IOException{
		try {
    	URL url = new URL(inputUrl);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"UTF-8");
		BufferedReader br = new BufferedReader(inReader);
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
		
		return retVal;
		}catch(IOException e) {
			return null;	
		}
    }
    
  public int countKeyword(String url, String keyword) throws IOException{
		try {
	  	if (content == null){
		    content = fetchContent(this.urlStr);
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    keywordIndex++;
		    fromIdx = found + keyword.length();
		}
    	//以上為主Link的count
		int sublinkHeight=0;
//    	while(sublinkHeight<1) {
//    		if(this.sublinkUrl.size()>=2) {
//    			countKeyword(this.sublinkUrl.get(this.sublinkUrl.size()/2),keyword);
//    			countKeyword(this.sublinkUrl.get(this.sublinkUrl.size()/2+1),keyword);
//    			sublinkHeight++;
//    		}															//此處控制sublink只跑一層，用中間的兩個sublink作為Count,因為網頁通常最正中央
//    	}																//會是比較有內容的
		}catch(IOException e) {}
		finally{return keywordIndex ;}
    }
    public double countImgVideo(String urlString) throws IOException {
    	try {
    	content = fetchContent(this.urlStr);
//    	if(content.length() < 500) {
//    		Document doc = Jsoup.parse(content);	
//    		urlStr = doc.select("a").get(0).attr("href");
//    		content = fetchContent(this.urlStr);
//    	}
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
    	int sublinkHeight=0;
//    	while(sublinkHeight<1) {
//    		if(this.sublinkUrl.size()>=2) {
//    			countImgVideo(this.sublinkUrl.get(this.sublinkUrl.size()/2));
//    			countImgVideo(this.sublinkUrl.get(this.sublinkUrl.size()/2+1));
//    			sublinkHeight++;
//    		}															//此處控制sublink只跑一層，用中間的兩個sublink作為Count,因為網頁通常最正中央
//    	}				
    	}catch(IOException e) {}
    	finally {
    		return imgIndex * 1.5 + videoIndex* 3;
    	}
    }
    
//    public ArrayList<String> getSublink()throws IOException {
//    	try {
//    	String content2="";
//    	String thisurl="";
//		content2 = fetchContent(this.urlStr);
//    	Document doc = Jsoup.parse(content2);
//    	Elements lis = doc.select("div");
//    	for(Element li:lis) {
//    		thisurl=li.select("a").attr("href");
//    		if(!thisurl.contains("picture") && !thisurl.contains("video") && !thisurl.contains("app") &&!thisurl.contains("#") && thisurl.length()>5) {    // !thisurl.contains("video") && 
//    				thisurl="https://google.com"+thisurl;
//    				this.sublinkUrl.add(thisurl);
////    				System.out.println(thisurl);
//    			}
//    	}
//    	}catch(IOException e) {}
//    	finally{
//    		return this.sublinkUrl;
//    	}
//    }
//    public boolean isDomain(String sublink) throws MalformedURLException {
//    	if(new URL(this.preUrl).getAuthority().equals(new URL(sublink).getAuthority())) {
//    		return true;
//    	}
//    	return false;
//    }
}

