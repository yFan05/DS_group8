# Greeny Search 🌱  
An Environmental News Search Engine  

**Authors**  
- 資管二 112306013 郭孟綺  
- 資管二 112306029 楊凡萱  
- 資管二 112306030 莊紫喬  
- 資管二 112306064 周宥慈  
- 資管二 112306089 石祐瑄

## Introduction  
**Greeny Search** is a project focused on enhancing access to environmental information. With the rise of environmental awareness, it is essential to provide users with a tool to locate policies, regulations, and news related to green topics efficiently. Greeny Search refines search results by assigning weights to keywords and analyzing their relevance to environmental themes, ensuring precise and meaningful results.

## Features  
1. **Keyword-Based Search**  
   - Keywords are classified into primary and secondary groups based on weight.
   - Keyword weights and frequencies are used to calculate scores for ranking pages.  

2. **Page & Site Ranking**  
   - **Page Ranking:** Evaluate and rank individual pages based on keyword scores.  
   - **Site Ranking:** Aggregate subpage scores to rank entire websites.  

3. **Google Integration**  
   - Search queries are modified with additional parameters like "environment" and quotation marks for improved accuracy.  

4. **Synonym Expansion**  
   - Synonyms are dynamically retrieved using a custom dictionary or external platforms to broaden search results.  

5. **Dynamic Interface**  
   - An interactive and user-friendly interface with green-themed design for intuitive navigation.  

6. **Content Sections**  
   - **Trending Environmental Issues:** Stay updated with Google Trends on climate, energy, and more.  
   - **Environmental Policy Column:** Access the latest updates on policies and regulations.  
   - **Free Writing Column:** Publish or read articles by experts and enthusiasts.  

---

## System Design  

### Class Descriptions  
- **Keyword:** Records keyword names and weights.  
- **WordCounter:** Extracts content from webpages and calculates keyword frequency.  
- **WebPage:** Stores webpage details and calculates its score.  
- **WebNode & WebTree:** Represents the website structure and enables traversal.  
- **GoogleQueryService:** Fetches data from Google and ranks search results.  
- **SynonymFetcher:** Retrieves synonyms dynamically for better search coverage.  
- **Frontend Components:**  
  - `Index` for webpage structure.  
  - `Style` for green-themed design.  
  - `Script` for interactive and dynamic features.  

---

## System Development Stages  
1. **Page Ranking**  
   - Rank specific URLs based on keyword scores and frequencies.  

2. **Site Ranking**  
   - Rank websites by aggregating their subpages' scores.  

3. **Google Search Refinement**  
   - Enhance Google search queries with additional keywords and evaluate results using site ranking.  

4. **Semantic Analysis**  
   - Expand search queries using synonyms from a custom dictionary or external platforms.  

5. **Publish Greeny Online**  
   - Deploy the system and provide users with a web-based interface for environmental news browsing.  

---

## Getting Started  

### Prerequisites  
- **Java 17+**  
- **Spring Boot**  
- **Maven**  
- **JSoup** for web scraping  

### Installation  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/your-repository/greeny-search.git  
   cd greeny-search  
   ```  
2. Install dependencies:  
   ```bash  
   mvn install  
   ```  
3. Run the application:  
   ```bash  
   mvn spring-boot:run  
   ```  
4. Access the application at [http://localhost:8080](http://localhost:8080).  

---

## Usage  
1. Input a keyword in the search bar (e.g., "green energy").  
2. View ranked results based on relevance and keyword scores.  
3. Explore additional sections for trending topics, policy updates, and user-contributed content.  

---

## Future Improvements  
- Integrate a real-time synonym fetching system.  
- Enhance UI/UX for mobile compatibility.  
- Extend the search functionality to include global policies and trends.  

---

## Contributing  
Contributions are welcome! Please submit a pull request or open an issue to get started.  

---

## License  
This project is licensed under the MIT License.  


