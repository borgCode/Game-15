import javax.swing.*;
import java.awt.*;

public class FifteenGame extends JFrame {

    JPanel boardPanel = new JPanel();
    JPanel northPanel = new JPanel();
    GameLogic gameLogic;
    JButton[][] buttons;
    JButton cheatButton;
    JLabel counter;

    FifteenGame(){
        gameLogic = new GameLogic();
        int[][] board = gameLogic.getBoard();

        setLayout(new BorderLayout());
        
        //Initiera button array
        buttons = new JButton[board.length][board.length];
        boardPanel.setLayout(new GridLayout(4,4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                
                //Settar int array värden till buttons
                buttons[i][j] = new JButton();
                JButton tempButton = buttons[i][j];
                
                //Om värdet är noll skriv ingen text
                if (board[i][j] == 0) {
                    tempButton.setText("");
                    tempButton.setEnabled(false);
                } else {
                    tempButton.setText(String.valueOf(board[i][j]));
                }
                
                //Lägg till actionlistener på varje button
                buttons[i][j].addActionListener(e -> {
                    gameLogic.performMove(tempButton.getText());
                    updateUI();
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        
        //Skapa new game button och counter som läggs till i North
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameLogic.startNewGame();
            updateUI();
        });
        
        counter = new JLabel("0");
        northPanel.setLayout(new FlowLayout());
        northPanel.add(newGameButton);
        northPanel.add(counter);
        cheatButton = new JButton("Cheat");
        cheatButton.addActionListener(e -> {
                    gameLogic.startCheatMode();
                    updateUI();
                });
        
        
        northPanel.add(cheatButton);
        add(northPanel, BorderLayout.NORTH);
        

        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateUI() {
        int[][] board = gameLogic.getBoard();
        
        //Uppdatera GUI med nya värden efter flytt
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
        
        //Uppdatera counter
        counter.setText(String.valueOf(gameLogic.getMoveCounter()));
        
        if (gameLogic.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Grattis, du vann!");
            gameLogic.startNewGame();
            gameLogic.setGameWon(false);
            updateUI();
        }
    }

    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}