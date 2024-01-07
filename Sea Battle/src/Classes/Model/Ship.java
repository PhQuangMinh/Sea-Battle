public class Ship {
    private String name;
    private int rowStart;
    private int columnStart;
    private int rowEnd;
    private int columnEnd;
    private int length;
    private boolean status;

    Ship() {
    }

    Ship(String name, int rowStart, int columnStart, int rowEnd, int columnEnd, int length, boolean status) {
        this.name = name;
        this.rowStart = rowStart;
        this.columnStart = columnStart;
        this.rowEnd = rowEnd;
        this.columnEnd = columnEnd;
        this.length = length;
        this.status = status;
    }

    public String nameShip(int index) {
        switch (index) {
            case 1:
                return "Patrol Boat";
            case 2:
                return "Destroyer Boat";
            case 3:
                return "Submarine";
        }
        return "Battle Ship";
    }

    private boolean checkScope(int size) {
        if (rowStart < 1 || rowEnd > size || columnStart < 1 || columnEnd > size)
            return false;
        return true;
    }

    private boolean checkValid() {
        if (rowStart != rowEnd && columnStart != columnEnd)
            return false;
        return true;
    }

    private boolean checkLength() {
        if (rowEnd == rowStart && Math.abs(columnEnd - columnStart) != length - 1
                || columnEnd == columnStart && Math.abs(rowEnd - rowStart) != length - 1)
            return false;
        return true;
    }

    private boolean checkOverlapOrStatus(char[][] board, boolean bool) {
        char character;
        if (bool)
            character = ' ';
        else
            character = 'x';
        if (columnStart == columnEnd) {
            int begin = Math.min(rowEnd, rowStart);
            int end = Math.max(rowEnd, rowStart);
            for (int i = begin; i <= end; i++)
                if (board[i][columnStart] != character)
                    return false;
        } else {
            int begin = Math.min(columnEnd, columnStart);
            int end = Math.max(columnEnd, columnStart);
            for (int i = begin; i <= end; i++)
                if (board[rowStart][i] != character)
                    return false;
        }
        return true;
    }

    public boolean checkPosition(char[][] board, int size) {
        if (!checkScope(size) || !checkValid() || !checkLength() || !checkOverlapOrStatus(board, true))
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getColumnStart() {
        return columnStart;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public boolean checkDestroyed(char[][] board) {
        return checkOverlapOrStatus(board, false);
    }

    public boolean checkShotInShip(Shot shot) {
        int minColumn = Math.min(columnStart, columnEnd);
        int maxColumn = Math.max(columnEnd, columnStart);
        int minRow = Math.min(rowStart, rowEnd);
        int maxRow = Math.max(rowStart, rowEnd);
        if (minColumn <= shot.getColumn() && maxColumn >= shot.getColumn() && minRow <= shot.getRow()
                && maxRow >= shot.getRow())
            return true;
        return false;
    }
}
