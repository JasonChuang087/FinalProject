import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class WordCounter {
	private String urlStr;
    private String content;
    private ArrayList<String> sublinkUrl;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    	this.sublinkUrl = new ArrayList<String>();
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
    
    public String countKeyword(String keyword) throws IOException{
    	if (content == null){
		    content = fetchContent(this.urlStr);
		}
    	content = content.toUpperCase();
		keyword = keyword.toUpperCase();
    	Document doc = Jsoup.parse(content);
    	Elements lis2 = doc.select("img, figure, video");  //有些圖片是包在Figure or img裡面 or (figure包img)，因此要讀不一樣的tag
    	int index2 = 0;
    	for(Element li :lis2) {
    		if(li!=null){
    		index2++;
    		}
    	}
//    	Elements lis3 = doc.select("h1, h2, h3, h4, h5, h6, p"); 
//    	int index3 = 0;
//    	for(int i=0; i<lis3.size(); i++) {
//    		for(int j=0; j<lis3.get(i).text().length(); j++) {
//    			String text = lis3.get(i).text();
//    			if(text.indexOf(keyword)!=-1) {
//    				j=j+text.indexOf(keyword)+keyword.length();
//    				index3++;
//    				text=text.substring(j);
//    				j--;   //j++後j的index == text的index
//				}
//			}
//		}
		Elements lis = doc.select("div");						//原本在嘗試h1.p等等，但文字可能放在p,h1~6,li.....都包在div內，因此讀整個div
		int index = 0;
		for(Element li : lis) {
			for(int i=0; i<li.text().length(); i++) {
				if(content.indexOf(keyword)!=-1) {
					i=content.indexOf(keyword)+keyword.length();
					index++;
					content=content.substring(i);
					i--;
				}
			}
		}
		
	
		 
		
//		for(int i=0; i<content.length()-1; i++) {
//			if(content.indexOf(keyword)!=-1) {
//				i=content.indexOf(keyword)+keyword.length();
//				index++;
//				content=content.substring(i);
//				i--;
//			}
//		}
//		System.out.println("-----------------------------------------------");
//		System.out.println(content);
		
		
	
		return "div= "+index + "\nimg= "+index2;
    }
    
    public ArrayList<String> getSublink()throws IOException {
    	if (content == null){
		    content = fetchContent();
		}
    	content = content.toUpperCase();
    	Document doc = Jsoup.parse(content);
    	Elements lis = doc.select("div");
    	for(Element li:lis) {
    		this.sublinkUrl.add(li.select("a").get(0).attr("href"));
    	}
    			
    	return this.sublinkUrl;
    }
    public String sublinkCount(String keyword) throws IOException {
    	//抓出sublink array 的中間link(通常重要內容在正中間)
    	if (content == null){
		    content = fetchContent();							//以下的code幾乎和keywordCount一樣，拆開來是因為不希望在keywordCount的時候
		}														//會call自己，這樣sublink一直往下跑、會跑太多次，為了省時只跑2層(其實Google本身應該有跑過相關性了)
    	content = content.toUpperCase();
		keyword = keyword.toUpperCase();
    	Document doc = Jsoup.parse(content);
    	Elements lis2 = doc.select("img, figure, video");  
    	int index2 = 0;
    	for(Element li :lis2) {
    		if(li!=null){
    		index2++;
    		}
    	}
    	Elements lis = doc.select("div");						
		int index = 0;
		for(Element li : lis) {
			for(int i=0; i<li.text().length(); i++) {
				if(content.indexOf(keyword)!=-1) {
					i=content.indexOf(keyword)+keyword.length();
					index++;
					content=content.substring(i);
					i--;
				}
			}
		}
    	return "wordCount= "+index+ "graph + video= "+index2;
    }
}