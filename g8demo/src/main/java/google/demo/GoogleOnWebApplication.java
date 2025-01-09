package google.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import google.demo.model.*;
import google.demo.service.GoogleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

import java.util.ArrayList;

@SpringBootApplication
public class GoogleOnWebApplication {

	@Autowired
	private static GoogleQueryService googleQueryService; // 注入 GoogleQueryService

	public static void main(String[] args) {
		SpringApplication.run(GoogleOnWebApplication.class, args);

		ArrayList<Keyword> keywords = new ArrayList<>(); // 儲存關鍵字的列表
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
		// 在這裡設置您想搜尋的關鍵字
		String query = "綠能"; // 這是範例關鍵字，您可以根據需求修改

		try {
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
