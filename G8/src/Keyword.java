
public class Keyword {
    private String name; // 關鍵字名稱
    private double count; // 關鍵字出現次數
    private double weight; // 關鍵字權重

    // Constructor
    public Keyword(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.count = 0;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getScore() {
        return count * weight; // 計算單一關鍵字的分
    }
}
