package google.demo.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class SynonymFetcher {

    // 根據關鍵字取得同義詞
    public ArrayList<String> getSynonyms(String keyword) {
        ArrayList<String> synonyms = new ArrayList<>();
        try {
            // 假設從金山詞霸或其他網站上獲取同義詞
            String url = "https://www.iciba.com/" + keyword;  // 根據您的需求修改網址
            Document doc = Jsoup.connect(url);
            
            // 根據實際網頁結構調整 CSS 選擇器
            Elements synonymElements = doc.select(".synonym");  // 假設同義詞被放在 .synonym 類中

            // 提取所有同義詞並添加到列表
            for (Element synonymElement : synonymElements) {
                synonyms.add(synonymElement.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return synonyms;
    }
}
