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
                tempButton.setFont(new Font("SansSerif", Font.BOLD, 20));
                
                //Om värdet är noll skriv ingen text
                if (board[i][j] == 0) {
                    tempButton.setText("");
                    tempButton.setBackground(new Color(220, 220, 220));
                    tempButton.setEnabled(false);
                } else {
                    tempButton.setText(String.valueOf(board[i][j]));
                    tempButton.setBackground(new Color(173, 216, 230));
                    tempButton.setForeground(Color.BLACK);
                }
                
                //Lägg till actionlistener på varje button
                buttons[i][j].addActionListener(e -> {
                    gameLogic.performMove(tempButton.getText());
                    updateUI();
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        boardPanel.setBackground(new Color(240, 240, 240));
        add(boardPanel, BorderLayout.CENTER);

        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(new Color(240, 240, 240));

        //Skapa new game button och counter som läggs till i North
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBackground(new Color(170, 255, 170));
        newGameButton.addActionListener(e -> {
            gameLogic.startNewGame();
            updateUI();
        });
        northPanel.add(newGameButton);

        cheatButton = new JButton("Cheat");
        cheatButton.setBackground(new Color(255, 210, 170));
        cheatButton.addActionListener(e -> {
                    gameLogic.startCheatMode();
                    updateUI();
                });
        northPanel.add(cheatButton);

        JLabel movesLabel = new JLabel("Moves: ");
        movesLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        movesLabel.setForeground(new Color(80, 80, 80));

        northPanel.add(movesLabel);

        counter = new JLabel("0");
        counter.setFont(new Font("SansSerif", Font.BOLD, 14));
        counter.setForeground(new Color(80, 80, 80));
        northPanel.add(counter);
        
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
                    buttons[i][j].setBackground(new Color(220, 220, 220));
                    buttons[i][j].setEnabled(false);
                } else {
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setBackground(new Color(173, 216, 230));
                    buttons[i][j].setForeground(Color.BLACK);   
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
        
        //Uppdatera counter
        counter.setText(String.valueOf(gameLogic.getMoveCounter()));
        
        if (gameLogic.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Grattis, du vann!");
            gameLogic.startNewGame();

            //Set game won till false innan updateUI kallas igen
            gameLogic.setGameWon(false);
            updateUI();
        }
    }

    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}