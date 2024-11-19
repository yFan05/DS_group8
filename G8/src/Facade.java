import java.util.List;

public class Facade {
    private HTMLHandler htmlHandler;

    // Constructor
    public Facade() {
        this.htmlHandler = new HTMLHandler();
    }

    // 執行整體邏輯
    public Tree buildTree(String rootUrl, List<Keyword> keywords) {
        Node rootNode = new Node("Root", rootUrl, htmlHandler.extractKeywords(rootUrl, keywords));
        Tree tree = new Tree(rootNode);

        // 獲取子網頁並建立樹結構
        List<String> childUrls = htmlHandler.fetchChildUrls(rootUrl);
        for (String url : childUrls) {
            Node childNode = new Node("Child", url, htmlHandler.extractKeywords(url, keywords));
            rootNode.addChild(childNode);
        }

        return tree;
    }
}
