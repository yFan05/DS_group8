
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
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0; 
		int n =content.length();
		int m = keyword.length();
		for(int i=0;i<n-m;i++) {
			int j=0;
		while(j < m && content.charAt(i+j) == keyword.charAt(j)) {
			j++;
		}
		if(j==m) {
			retVal ++;
		}
		
		}
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
		
		
		return retVal;
		
    }

    public double getScore() {
        return count * weight; // 計算單一關鍵字的分數
    }
}
