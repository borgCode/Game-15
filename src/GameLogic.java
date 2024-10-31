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
        

        startNewGame();

    }

    //https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
    // Algorithm för att se om det går att lösa pusslet.
    private boolean isSolvable(int[][] listOfNumbers) {
        // Count inversions in given puzzle
        int[] arr = new int[size * size];
        int k = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                arr[k++] = listOfNumbers[i][j];

        int invCount = getInvCount(arr);

        // If grid is odd, return true if inversion
        // count is even.
        if (size % 2 == 1)
            return invCount % 2 == 0;
        else // grid is even
        {
            int pos = findXPosition(listOfNumbers);
            if (pos % 2 == 1)
                return invCount % 2 == 0;
            else
                return invCount % 2 == 1;
        }
    }

    // A utility function to count inversions in given
    // array 'arr[]'. Note that this function can be
    // optimized to work in O(n Log n) time. The idea
    // here is to keep code small and simple.
    private int getInvCount(int[] arr)
    {
        int inv_count = 0;
        for (int i = 0; i < size * size - 1; i++) {
            for (int j = i + 1; j < size * size; j++) {
                // count pairs(arr[i], arr[j]) such that
                // i < j but arr[i] > arr[j]
                if (arr[j] != 0 && arr[i] != 0
                        && arr[i] > arr[j])
                    inv_count++;
            }
        }
        return inv_count;
    }

    int findXPosition(int[][] puzzle)
    {
        // start from bottom-right corner of matrix
        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--)
                if (puzzle[i][j] == 0)
                    return size - i;
        return -1;
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
        moveCounter = 0;
        do {
            Collections.shuffle(listOfNumbers);
            addNumbersToBoard(listOfNumbers);
        } while (!isSolvable(board));

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
                    break;
                }
                number++;
            }
        }
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
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
