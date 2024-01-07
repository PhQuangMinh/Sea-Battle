public class Shot {
    private int row;
    private int column;

    Shot() {
    }

    Shot(int row, int column) {
        this.row = row;
        this.column = column;
    }

    private boolean checkScope(int size) {
        if (row < 1 || row > size || column < 1 || column > size)
            return false;
        return true;
    }

    private boolean checkOverlap(char[][] board) {
        if (board[row][column] == 'x' || board[row][column] == 'o')
            return false;
        return true;
    }

    public boolean checkPosition(char board[][], int size) {
        if (!checkScope(size) || !checkOverlap(board))
            return false;
        return true;
    }

    public boolean checkHit(char[][] board) {
        if (board[row][column] >= '2' && board[row][column] <= '5')
            return true;
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
