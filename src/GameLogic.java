import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private int size = 4;
    private int[][] board;
    private List<Integer> listOfNumbers;
    private int moveCounter = 0;
    boolean isGameWon = false;


    public GameLogic() {
        initBoard();
    }


    private void initBoard() {

        //Skapa först en lista med siffor 0 till 15

        listOfNumbers = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            listOfNumbers.add(i);
        }

        Collections.shuffle(listOfNumbers);

        //TODO ifSolvable


        //Lägg in sifforna från listan till 2D array
        addNumbersToBoard(listOfNumbers);

    }

    private void addNumbersToBoard(List<Integer> listOfNumbers) {
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

    public int getMoveCounter() {
        return moveCounter;
    }

    public void performMove(String number) {
        //Leta efter siffran i 2D array
        int numberInt = Integer.parseInt(number);
        for (int row = 0; row < 4; row++) {
            for (int colum = 0; colum < 4; colum++) {
                if (board[row][colum] == numberInt) {
                    //Kalla metod för att byta siffran med 0 om dem ligger bredvid varandra
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
            moveCounter++;
            checkIfGameIsWon();
        }
        //kollar undre
        else if (row < board.length - 1 && board[row + 1][colum] == 0) {
            board[row + 1][colum] = board[row][colum];
            board[row][colum] = 0;
            moveCounter++;
            checkIfGameIsWon();
        }
        //kollar till vänster
        else if (colum > 0 && board[row][colum - 1] == 0) {
            board[row][colum - 1] = board[row][colum];
            board[row][colum] = 0;
            moveCounter++;
            checkIfGameIsWon();
        }
        //kollar till högre
        else if (colum < board[row].length - 1 && board[row][colum + 1] == 0) {
            board[row][colum + 1] = board[row][colum];
            board[row][colum] = 0;
            moveCounter++;
            checkIfGameIsWon();
        }
    }

    public void startNewGame() {
        Collections.shuffle(listOfNumbers);

        //TODO ifSolvable

        //Lägg in sifforna från listan till 2D array
        addNumbersToBoard(listOfNumbers);

        moveCounter = 0;
    }

    private void checkIfGameIsWon() {
        int number = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (number == 15) {
                    isGameWon = true;
                    break;
                }
                if (board[i][j] != number) {
                    isGameWon = false;
                }
                number++;
            }
        }
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void startCheatMode() {
        int number = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (number <= 14) {
                    board[row][col] = number;
                    number++;
                } else if (number == 15) {
                    board[row][col] = 0;
                    number++;
                } else {
                    board[row][col] = 15;
                }
            }
        }

    }
}
