package google.demo.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class WordCounter
{
    private String urlStr;
    private String content;
    private String title;

    public WordCounter(String urlStr)
    {
        this.urlStr = urlStr;
    }

    private String fetchContent() throws IOException
    {
        URL url = new URL(this.urlStr);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String retVal = "";

        String line = null;

        while ((line = br.readLine()) != null)
        {
            retVal = retVal + line + "\n";
        }

        return retVal;
    }

    public int countKeyword(String keyword) throws IOException
    {
        if (content == null)
        {
            content = fetchContent();
        }

        // To do a case-insensitive search, we turn the whole content and keyword into
        // upper-case:
        content = content.toUpperCase();
        keyword = keyword.toUpperCase();

        int retVal = 0;
        int fromIdx = 0;
        int found = -1;

        while ((found = content.indexOf(keyword, fromIdx)) != -1)
        {
            retVal++;
            fromIdx = found + keyword.length();
        }

        return retVal;
    }

    // 計算標題中關鍵字的次數
    public int countKeywordInTitle(ArrayList<Keyword> keywords) throws IOException {
        if (content == null) {
            content = fetchContent();
        }

        // 提取標題
        if (title == null) {
            title = content.split("<title>")[1].split("</title>")[0];
        }

        int count = 0;
        for (Keyword k : keywords) {
            int index = title.toUpperCase().indexOf(k.name.toUpperCase());
            if (index != -1) {
                count++;
            }
        }

        return count;
    }
}
