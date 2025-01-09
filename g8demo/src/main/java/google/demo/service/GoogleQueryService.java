package google.demo.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import google.demo.controller.*;
import google.demo.*;
import google.demo.model.*;
import java.util.ArrayList;
import google.demo.model.WebPage;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public class GoogleQueryService {
    private String searchKeyword;
    private String url;
    private String content;
    @Autowired
    private SynonymFetcher synonymFetcher;

    public GoogleQueryService() {
        // 空的構造函數 
    }
    
    public ArrayList<WebPage> searchAndCalculateScore(String searchKeyword, ArrayList<Keyword> keywords) throws IOException {
        ArrayList<String> allKeywords = new ArrayList<>();
        allKeywords.add(searchKeyword);  // 先加入原始關鍵字
        for (Keyword keyword : keywords) {
            ArrayList<String> synonyms = synonymFetcher.getSynonyms(keyword.name);  // 查詢每個關鍵字的同義詞
            allKeywords.addAll(synonyms);  // 把同義詞加入到 allKeywords 中
        }
        
        ArrayList<WebPage> webPages = new ArrayList<>();
        
        for (String keyword : allKeywords) {
            HashMap<String, String> searchResults = search(keyword);  // 根據每個關鍵字進行搜尋

            // 根據搜尋結果創建 WebPage 並設置分數
            for (String title : searchResults.keySet()) {
                String url = searchResults.get(title);
                WebPage webPage = new WebPage(url, title);

                // 設置 WebPage 的分數，這裡用到傳入的 Keyword 來計算分數
                webPage.setScore(keywords);  // 假設 WebPage 類中的 setScore 方法會根據關鍵字來設置分數
                webPages.add(webPage);
            }
        }

        // 排序：首先根據標題是否包含 searchKeyword，然後根據分數排序
        webPages.sort((a, b) -> {
            boolean aContains = a.getTitle().contains(searchKeyword);
            boolean bContains = b.getTitle().contains(searchKeyword);

            if (aContains && !bContains) {
                return -1;
            } else if (!aContains && bContains) {
                return 1;
            }
            return Double.compare(b.getScore(), a.getScore()); // 根據分數排序
        });

        return webPages;
    }
       
    


    public HashMap<String, String> search(String searchKeyword) throws IOException {
    	
        String appendKeyword = "環保";
        this.searchKeyword = "\"" + searchKeyword + "\"" +appendKeyword ;

        try {
            String encodeKeyword = URLEncoder.encode(this.searchKeyword, "utf-8");
            this.url = "https://www.google.com/search?q=" + encodeKeyword + "&oe=utf8&num=50&tbs=qdr:y2";
        } catch (Exception e) {
            System.out.println("Error encoding search keyword: " + e.getMessage());
        }

        this.content=fetchContent();
        HashMap<String, String> retVal = new HashMap<>();
        
     // 清空舊的內容
        this.content = null;

        if (content == null) {
            content = fetchContent();
        }
         // 使用 Jsoup 分析 HTML 字符串
        Document doc = Jsoup.parse(content);
        Elements lis = doc.select("div.kCrYT");

        for (Element li : lis) {
            try {
                String citeUrl = li.select("a").attr("href").replace("/url?q=", "").split("&")[0];
                citeUrl = URLDecoder.decode(citeUrl, "UTF-8");
                String title = li.select("a").select(".vvjwJb").text();
              
                if (title.equals("")) {
                    continue;
                }
                System.out.println("Title: " + title + " , url: " + citeUrl);
                // 將標題和 URL 放入 HashMap
                retVal.put(title, citeUrl);
            } catch (IndexOutOfBoundsException e) {
                // 忽略例外
            }}
        return retVal;
            }

    private String fetchContent() throws IOException {
       
        StringBuilder retVal = new StringBuilder();

        URL u = new URL(url);
        URLConnection conn = u.openConnection();
        // 設置 HTTP 標頭 
        conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
        InputStream in = conn.getInputStream();

        InputStreamReader inReader = new InputStreamReader(in, "utf-8");
        BufferedReader bufReader = new BufferedReader(inReader);
        String line;

        while ((line = bufReader.readLine()) != null) {
            retVal.append(line);
        }
        bufReader.close();
        return retVal.toString();
    }
    
}
