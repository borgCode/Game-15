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
        add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());

        setVisible(true);
        setSize(400, 400);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}