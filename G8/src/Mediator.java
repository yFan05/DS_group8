import java.util.ArrayList;
import java.util.List;

public class Mediator {
    private Facade facade;
    private List<String> keywords;

    // 建構子
    public Mediator() {
        this.facade = new Facade();
        this.keywords = new ArrayList<>();
    }

    // 處理使用者輸入的關鍵字
    public void handleKeywords(String[] inputKeywords) {
        for (String keyword : inputKeywords) {
            String trimmedKeyword = keyword.trim();
            if (!keywords.contains(trimmedKeyword)) {
                keywords.add(trimmedKeyword); // 去重後加入關鍵字清單
            }
        }
    }

    // 執行關鍵字搜尋（Command）
    public void executeSearch() {
        // 傳遞關鍵字給後端
        Tree resultTree = facade.buildTree("https://rooturl.com", generateKeywordObjects());
        System.out.println("Search executed. Result tree built.");
        // TODO: 在這裡可以進一步展示結果
    }

    // 將 String 關鍵字轉換為 Keyword 物件
    private List<Keyword> generateKeywordObjects() {
        List<Keyword> keywordObjects = new ArrayList<>();
        for (String keyword : keywords) {
            keywordObjects.add(new Keyword(keyword, 1.0)); // 默認權重設為 1.0
        }
        return keywordObjects;
    }
}
