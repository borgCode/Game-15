public class GameLogic {
    private int size = 4;
    private int[][] board;

    public GameLogic() {
        initBoard();
    }

    private void initBoard() {
        board = new int[size][size];
        int value = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = value;
                System.out.println(value);
                value++;
            }
        }
    }
}
