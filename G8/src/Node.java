import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name; // 節點名稱
    private String url; // 節點對應的網頁 URL
    private List<Node> children; // 子節點
    private List<Keyword> keywords; // 關鍵字列表
    private double sum; // 總分

    // Constructor
    public Node(String name, String url, List<Keyword> keywords) {
        this.name = name;
        this.url = url;
        this.keywords = keywords;
        this.children = new ArrayList<>();
        this.sum = 0;
    }

    // 計算節點的總分
    public void calculateSum() {
        sum = 0;
        for (Keyword keyword : keywords) {
            sum += keyword.getScore();
        }
    }

    // 添加子節點
    public void addChild(Node child) {
        children.add(child);
    }

    // Getter
    public double getSum() {
        return sum;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getUrl() {
        return url;
    }
}
