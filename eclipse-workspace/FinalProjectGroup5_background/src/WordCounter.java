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
    	Elements lis2 = doc.select("img, figure, video");  //���ǹϤ��O�]�bFigure or img�̭� or (figure�]img)�A�]���nŪ���@�˪�tag
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
//    				j--;   //j++��j��index == text��index
//				}
//			}
//		}
		Elements lis = doc.select("div");						//�쥻�b����h1.p�����A����r�i���bp,h1~6,li.....���]�bdiv���A�]��Ū���div
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
    	//��Xsublink array ������link(�q�`���n���e�b������)
    	if (content == null){
		    content = fetchContent();							//�H�U��code�X�G�MkeywordCount�@�ˡA��}�ӬO�]�����Ʊ�bkeywordCount���ɭ�
		}														//�|call�ۤv�A�o��sublink�@�����U�]�B�|�]�Ӧh���A���F�ٮɥu�]2�h(���Google�������Ӧ��]�L�����ʤF)
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