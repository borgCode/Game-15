import javax.swing.*;
import java.awt.*;

public class FifteenGame extends JFrame {

    JPanel boardPanel = new JPanel();
    JPanel northPanel = new JPanel();

    JButton[][] buttons;



    FifteenGame(){
        GameLogic gameLogic = new GameLogic();
        int[][] board = gameLogic.getBoard();
        buttons = new JButton[board.length][board.length];
        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        boardPanel.setLayout(new GridLayout(4,4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton();
                JButton tempButton = buttons[i][j];
                if (board[i][j] == 0) {
                    tempButton.setText("");
                    tempButton.setEnabled(false);
                } else {
                    tempButton.setText(String.valueOf(board[i][j]));
                }
                buttons[i][j].addActionListener(e -> {
                    gameLogic.performeMove(tempButton.getText());
                    updateUI(board);
                });
                boardPanel.add(buttons[i][j]);
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

    private void updateUI(int[][] board) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (board[i][j] == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(false);
                } else {
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }

    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}