public class SearchKeywordCommand extends AbstractCommand {
    private Node node;

    // Constructor
    public SearchKeywordCommand(Node node) {
        this.node = node;
    }

    @Override
    public void execute() {
        node.calculateSum(); // 計算節點的總分
    }
}
