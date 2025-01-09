package google.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import google.demo.model.*;
import google.demo.service.GoogleQueryService;
import google.demo.service.SynonymFetcher; // 引入 SynonymFetcher
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class GoogleOnWebApplication {

    @Autowired
    private static GoogleQueryService googleQueryService;

    @Autowired
    private static SynonymFetcher synonymFetcher; // 加入 SynonymFetcher

    public static void main(String[] args) {
        SpringApplication.run(GoogleOnWebApplication.class, args);

        ArrayList<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("綠色", 8));
        keywords.add(new Keyword("綠能", 10));
        keywords.add(new Keyword("能源", 6));
        keywords.add(new Keyword("氣候", 6));
        keywords.add(new Keyword("環保", 10));
        keywords.add(new Keyword("環境", 5));
        keywords.add(new Keyword("政策", 8));
        keywords.add(new Keyword("法規", 8));
        keywords.add(new Keyword("新聞", 30));
        keywords.add(new Keyword("永續", 6));
        keywords.add(new Keyword("碳排放", 6));

        String query = "環保"; 

        try {
            // 在這裡先抓取同義詞並加入 keywords 中
            ArrayList<String> synonyms = synonymFetcher.getSynonyms(query); // 抓取關鍵字的同義詞
            for (String synonym : synonyms) {
                keywords.add(new Keyword(synonym, 5)); // 同義詞的權重為5
            }

            // 呼叫 searchAndCalculateScore
            ArrayList<WebPage> webPages = googleQueryService.searchAndCalculateScore(query, keywords);
            for (WebPage webPage : webPages) {
                System.out.println("標題: " + webPage.name + " | 分數: " + webPage.getScore());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
