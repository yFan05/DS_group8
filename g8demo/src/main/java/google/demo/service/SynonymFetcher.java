package google.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import google.demo.model.Keyword;
import org.springframework.stereotype.Service;

@Service
public class SynonymFetcher {

    // 模擬一個簡單的同義詞字典
    private static final HashMap<String, ArrayList<String>> synonymDict = new HashMap<>();

    static {
        ArrayList<String> greenSynonyms = new ArrayList<>();
        greenSynonyms.add("環保");
        greenSynonyms.add("綠色");
        greenSynonyms.add("永續");
        synonymDict.put("綠色", greenSynonyms);

        ArrayList<String> energySynonyms = new ArrayList<>();
        energySynonyms.add("能源");
        energySynonyms.add("綠能");
        synonymDict.put("能源", energySynonyms);

        // 你可以根據需要繼續添加更多同義詞
    }

    // 根據關鍵字取得同義詞
    public ArrayList<String> getSynonyms(String keyword) {
        return synonymDict.getOrDefault(keyword, new ArrayList<>());
    }
}
