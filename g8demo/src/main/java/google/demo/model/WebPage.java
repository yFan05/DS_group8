package google.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
    public String url;
    public String name;
    
    @JsonIgnore // 排除序列化
    public WordCounter counter;
    
    public double score;

    public WebPage(String url, String name) {
        this.url = url;
        this.name = name;
        this.counter = new WordCounter(url);
    }

    public void setScore(ArrayList<Keyword> keywords) throws IOException {
        score = 0;
        int mainKeywordCount = 0;
        int secondaryKeywordCount = 0;
        int titleKeywordCount = 0;

        // 計算主要關鍵字出現次數、次要關鍵字出現次數、標題關鍵字出現次數
        for (Keyword k : keywords) {
            int count = counter.countKeyword(k.name);
            if (k.weight == 3) { // 主要關鍵字
                mainKeywordCount += count;
            } else if (k.weight == 2) { // 次要關鍵字
                secondaryKeywordCount += count;
            }
        }

        // 計算標題中出現的關鍵字次數
        titleKeywordCount = counter.countKeywordInTitle(keywords);

        // 計算總分
        score = 3 * mainKeywordCount + 2 * secondaryKeywordCount + titleKeywordCount;

        // 判斷是否為官方網站，加分
        if (url.contains(".gov") || url.contains(".edu")) {
            score += 5; // 這裡根據需要調整額外加分
        }
    }

    public double getScore() {
        return score;
    }

    public String getUrl() {
        return url;
    }
}
