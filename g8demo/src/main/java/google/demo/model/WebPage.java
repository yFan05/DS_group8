package google.demo.model;
import java.io.IOException;
import java.util.ArrayList;

public class WebPage{
	public String url;
	public String name;
	public WordCounter counter;
	public double score;

	public WebPage(String url, String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}
	public String getUrl() {
		return this.url;
	}
	public String getTitle() {
	    	return this.name;  // 假設 title 存儲在 'name' 屬性中
	}

	public void setScore(ArrayList<Keyword> keywords) throws IOException {
	    double totalScore = 0;

	    // 計算每個關鍵字的得分
	    for (Keyword keyword : keywords) {
	        // 計算頁面中關鍵字出現的次數
	        int keywordCount = new WordCounter(this.url).countKeyword(keyword.name);
	        totalScore += keywordCount * keyword.weight;  // 根據關鍵字的權重進行加權
	    }
	    this.score = totalScore;  // 設置頁面的總分數
	}
	public double getScore() {
        return score;
    }
}
