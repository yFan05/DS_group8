package google.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import google.demo.model.*;

@Service
public class GoogleQueryService {
    private String searchKeyword;
    private String url;
    private String content;

    public GoogleQueryService() {
        // 空的構造函數
    }

    public HashMap<String, String> search(String searchKeyword) throws IOException {

        String appendedKeywords = " AND \u7DA0\u80FD AND \u74B0\u4FDD";
        this.searchKeyword = searchKeyword + appendedKeywords;

        try {
            String encodeKeyword = URLEncoder.encode(this.searchKeyword, "utf-8");
            this.url = "https://www.google.com/search?q=" + encodeKeyword + "&oe=utf8&num=20";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (content == null) {
            content = fetchContent();
        }

        HashMap<String, String> retVal = new HashMap<>();

        System.out.println("\u6293\u53D6\u7684 HTML \u5167\u5BB9\uff1a");
        System.out.println(this.content);

        Document doc = Jsoup.parse(content);

        Elements lis = doc.select("div.kCrYT");

        for (Element li : lis) {
            try {
                String citeUrl = li.select("a").attr("href").replace("/url?q=", "").split("&")[0];
                String title = li.select("a").select(".vvjwJb").text();

                if (title.equals("")) {
                    continue;
                }

                System.out.println("Title: " + title + " , url: " + citeUrl);

                retVal.put(title, citeUrl);

            } catch (IndexOutOfBoundsException e) {
                // 忽略例外
            }
        }

        return retVal;
    }

    private String fetchContent() throws IOException {
        StringBuilder retVal = new StringBuilder();

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        // 設置 HTTP 標頭
        conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");

        try {
            // 檢查 HTTP 狀態碼
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) { // 如果不是 200
                System.err.println("HTTP error: " + statusCode + " for URL: " + url);
                throw new IOException("Failed to fetch content. HTTP status: " + statusCode);
            }

            // 獲取 InputStream 並讀取內容
            try (InputStream in = conn.getInputStream();
                 InputStreamReader inReader = new InputStreamReader(in, "utf-8");
                 BufferedReader bufReader = new BufferedReader(inReader)) {
                String line;
                while ((line = bufReader.readLine()) != null) {
                    retVal.append(line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("404 Not Found: " + url);
            return ""; // 返回空內容，繼續處理其他 URL
        } catch (IOException e) {
            System.err.println("Error fetching content from: " + url);
            throw e; // 重新丟出例外，讓上層知道此問題
        } finally {
            conn.disconnect();
        }

        return retVal.toString();
    }


    public ArrayList<WebPage> searchAndCalculateScore(String searchKeyword, ArrayList<Keyword> keywords) throws IOException {
        HashMap<String, String> searchResults = search(searchKeyword);

        ArrayList<WebPage> webPages = new ArrayList<>();

        for (String title : searchResults.keySet()) {
            String url = searchResults.get(title);
            try {
                WebPage webPage = new WebPage(url, title);
                webPage.setScore(keywords); // 根據關鍵字設定分數
                webPages.add(webPage);
            } catch (IOException e) {
                System.err.println("Skipping URL due to error: " + url);
                // 跳過該 URL，繼續處理其他結果
            }
        }


        webPages.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        return webPages;
    }
}
