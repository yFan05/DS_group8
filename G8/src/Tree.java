import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root; // 樹的根節點

    // Constructor
    public Tree(Node root) {
        this.root = root;
    }

    // 前序遍歷
    public List<Node> preOrderTraverse() {
        List<Node> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private void preOrderHelper(Node node, List<Node> result) {
        if (node != null) {
            result.add(node);
            for (Node child : node.getChildren()) {
                preOrderHelper(child, result);
            }
        }
    }

    // 檢查是否為空
    public boolean isEmpty() {
        return root == null;
    }
}
