package google.demo.controller;

import google.demo.service.GoogleQueryService;
import google.demo.model.Keyword;
import google.demo.model.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class GoogleSearchController {

    @Autowired
    private GoogleQueryService googleQueryService;

    @GetMapping("/search")
    public HashMap<String, String> search(@RequestParam("q") String query) {
        try {
            ArrayList<Keyword> keywords = new ArrayList<>();
            keywords.add(new Keyword("綠色", 1.0));
            keywords.add(new Keyword("能源", 0.9));
            
            ArrayList<WebPage> webPages = googleQueryService.searchAndCalculateScore(query, keywords);

            HashMap<String, String> results = new HashMap<>();
            for (WebPage page : webPages) {
                results.put(page.getTitle(), page.getUrl());
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
