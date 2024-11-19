public class ViewControl {
    private static ViewControl instance;
    private Mediator mediator;

    // 私有建構子
    private ViewControl() {
        mediator = new Mediator();
    }

    // 獲取唯一實例（Singleton Pattern）
    public static ViewControl getInstance() {
        if (instance == null) {
            instance = new ViewControl();
        }
        return instance;
    }

    // 接收使用者輸入的關鍵字並傳給 Facade
    public void processInput(String inputKeywords) {
        String[] keywords = inputKeywords.split(",");
        mediator.handleKeywords(keywords);
    }

    // 判斷 Signal 並執行對應的 Command
    public void executeCommand(String signal) {
        switch (signal) {
            case "Search":
                mediator.executeSearch();
                break;
            default:
                System.out.println("Invalid Signal");
        }
    }
}
