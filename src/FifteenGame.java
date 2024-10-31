import javax.swing.*;
import java.awt.*;

public class FifteenGame extends JFrame {

    JPanel boardPanel = new JPanel();
    JPanel northPanel = new JPanel();



    FifteenGame(){
        GameLogic gameLogic = new GameLogic();

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        boardPanel.setLayout(new GridLayout(4,4));
        int index = 0;
        for (int i = 0; i < 16; i++) {
            boardPanel.add(new JButton("test"));
        }

        JButton NewGameButton = new JButton("New Game");
        JLabel counter = new JLabel("0");

        add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(NewGameButton);
        northPanel.add(counter);

        setVisible(true);
        setSize(400, 400);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}