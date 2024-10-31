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
}
