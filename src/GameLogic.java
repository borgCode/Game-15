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

    public void performMove(String number) {

        int numberInt = Integer.parseInt(number);
        for (int row = 0; row < 4; row++) {
            for (int colum = 0; colum < 4; colum++) {
                if (board[row][colum] == numberInt) {
                    swapWithZero(row, colum);
                    row = 4;
                    break;
                }
            }
        }
    }

    private void swapWithZero(int row, int colum) {
        //kollar övre
        System.out.println("Kollar metoden");
        if (row > 0 && board[row - 1][colum] == 0) {
            board[row - 1][colum] = board[row][colum];
            board[row][colum] = 0;
            System.out.println(board[row - 1][colum]);
            System.out.println(board[row][colum]);
        }
        //kollar undre
        else if (row < board.length -1 && board[row + 1][colum] == 0) {
            board[row + 1][colum] = board[row][colum];
            board[row][colum] = 0;
            System.out.println(board[row + 1][colum]);
            System.out.println(board[row][colum]);
        }
        //kollar till vänster
        else if (colum > 0 && board[row][colum - 1] == 0) {
            board[row][colum - 1] = board[row][colum];
            board[row][colum] = 0;
            System.out.println(board[row][colum - 1]);
            System.out.println(board[row][colum]);
        }
        //kollar till högre
        else if (colum < board[row].length -1 && board[row][colum + 1] == 0) {
            board[row][colum + 1] = board[row][colum];
            board[row][colum] = 0;
            System.out.println(board[row][colum + 1]);
            System.out.println(board[row][colum]);
        }
    }
}
