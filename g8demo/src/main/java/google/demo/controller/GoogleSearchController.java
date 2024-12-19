package google.demo.controller;

import google.demo.service.GoogleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import google.demo.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // 根據你的前端地址調整
public class GoogleSearchController {

    @Autowired
    private GoogleQueryService googleQueryService;

    @GetMapping("/search")
    public List<WebPage> search(@RequestParam("q") String query) {
        try {
            // 初始化預設的關鍵字和權重
            ArrayList<Keyword> keywords = new ArrayList<>();
            keywords.add(new Keyword("綠色", 3));
            keywords.add(new Keyword("綠能", 3));
            keywords.add(new Keyword("能源", 3));
            keywords.add(new Keyword("氣候", 3));
            keywords.add(new Keyword("環保", 3));
            keywords.add(new Keyword("環境", 3));
            keywords.add(new Keyword("政策", 2));
            keywords.add(new Keyword("法規", 2));

            // 搜尋並計算分數
            List<WebPage> webPages = googleQueryService.searchAndCalculateScore(query, keywords);
            webPages.sort((a, b) -> Double.compare(b.getScore(), a.getScore())); // 排序
            return webPages;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
