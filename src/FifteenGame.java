import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FifteenGame extends JFrame {

    JPanel boardPanel = new JPanel();
    JPanel northPanel = new JPanel();



    FifteenGame(){
        GameLogic gameLogic = new GameLogic();
        int[][] board = gameLogic.getBoard();

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        boardPanel.setLayout(new GridLayout(4,4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton numberButton = new JButton();
                if (board[i][j] == 0) {
                    numberButton.setText("");
                    numberButton.setEnabled(false);
                } else {
                    numberButton.setText(String.valueOf(board[i][j]));
                }
                numberButton.addActionListener(l -> gameLogic.performeMove(numberButton.getText()));
                boardPanel.add(numberButton);
            }
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