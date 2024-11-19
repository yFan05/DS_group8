import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public static void main(String[] args) {
        // 建立 JFrame
        JFrame frame = new JFrame("Keyword Search Engine");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立 JPanel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // 顯示視窗
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 關鍵字輸入提示標籤
        JLabel keywordLabel = new JLabel("Enter Keywords:");
        keywordLabel.setBounds(10, 20, 120, 25);
        panel.add(keywordLabel);

        // 關鍵字輸入框
        JTextField keywordText = new JTextField(20);
        keywordText.setBounds(140, 20, 200, 25);
        panel.add(keywordText);

        // 搜尋按鈕
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(140, 60, 100, 25);
        panel.add(searchButton);

        // 綁定按鈕行為
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = keywordText.getText();
                if (!input.isEmpty()) {
                    ViewControl.getInstance().processInput(input); // 處理使用者輸入
                    ViewControl.getInstance().executeCommand("Search"); // 執行搜尋 Command
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter keywords.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
