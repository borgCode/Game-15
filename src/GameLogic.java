import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private int size = 4;
    private int[][] board;

    public GameLogic() {
        initBoard();
    }

    

    private void initBoard() {
        
        List<Integer> listOfNumbers = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            listOfNumbers.add(i);
        }
        
        Collections.shuffle(listOfNumbers);
        
        //TODO ifSolvable
        
        board = new int[size][size];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = listOfNumbers.get(index);
                index++;
            }
        }
        
    }

    public int[][] getBoard() {
        return board;
    }

    public void performeMove(String number) {
        int numberInt = Integer.parseInt(number);
        for (int row = 0; row < 4; row++) {
            for (int colum = 0; colum < 4; colum++) {
                if (board[row][colum] == numberInt) {
                    swapWithZero(row, colum);
            }
        }
        System.out.println(number);
    }
}

    private void swapWithZero(int row, int colum) {
        if (row > 0 && board[row - 1][colum] == 0) {
            board[row - 1][colum] = board[row][colum];
            board[row][colum] = 0;
        }
    }
    }
