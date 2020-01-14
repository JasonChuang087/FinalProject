import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public Double score=0.0;
	public WordCounter counter;

	public WebPage(String name, String url) throws IOException {
		this.url=url;
		this.name = name;
		this.counter = new WordCounter(url);
	}
//	public void setScore(ArrayList<Keyword> keywords) throws IOException{
//		score = 0.0;
//		for(Keyword k : keywords){			
//			score += counter.countKeyword(this.url , k.name)* k.weight;	
//		}
//		score +=counter.countImgVideo(this.url);
//	}
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0.0;
		for(Keyword k : keywords){			
			score += counter.countKeyword(this.url, k.name)* k.weight;	
		}
			score += counter.countImgVideo(this.url);
	}
	public String toString() {
		return "["+ name +","+score+"]";
	}
}
