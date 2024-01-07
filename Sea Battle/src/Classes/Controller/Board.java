public class Board {
    public char[][] createBoard(int size) {
        char[][] board = new char[25][25];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                board[i][j] = ' ';
        return board;
    }

    private void printShip(char[][] board, int row, int column) {
        if (board[row][column] >= '2' && board[row][column] <= '5') {
            String[] color = { "\u001B[41m", "\u001B[42m", "\u001B[46m", "\u001B[44m" };
            int index = (int) (board[row][column] - '0');
            System.out.print(Effect.reset + color[index - 2]);
            System.out.print(" " + "s" + " |");
        } else {
            if (board[row][column] == 'x')
                System.out.print(Effect.reset + Effect.backgoundPurple);
            else if (board[row][column] == 'o')
                System.out.print(Effect.reset + Effect.backgoundYellow);
            System.out.print(" " + board[row][column] + " |");
        }
        if (board[row][column] != ' ') {
            System.out.print(Effect.reset + Effect.green);
        }
    }

    public void displayBoardShip(char[][] board, Player player) {
        System.out.print(Effect.reset + Effect.green);
        for (int i = 0; i < (player.getSizeBoard()+1)*4; i++)
            System.out.print("_");
        System.out.println();
        System.out.print("|  |");
        for (int i = 1; i <= player.getSizeBoard(); i++)
            System.out.print(String.format(" %-2s|", i));
        System.out.println();
        for (int i = 1; i <= player.getSizeBoard(); i++) {
            System.out.print(String.format("|%2s|", i));
            for (int j = 1; j <= player.getSizeBoard(); j++)
                printShip(board, i, j);
            System.out.println();
        }
        System.out.print(Effect.reset + Effect.blue);
    }

    public void updateBoardShip(Ship ship, Player player) {
        char length = (char) (ship.getLength() + 48);
        char[][] board = player.getBoard();
        if (ship.getColumnStart() == ship.getColumnEnd()) {
            int begin = Math.min(ship.getRowEnd(), ship.getRowStart());
            int end = Math.max(ship.getRowEnd(), ship.getRowStart());
            for (int i = begin; i <= end; i++) {
                board[i][ship.getColumnStart()] = length;
            }
        } else {
            int begin = Math.min(ship.getColumnEnd(), ship.getColumnStart());
            int end = Math.max(ship.getColumnEnd(), ship.getColumnStart());
            for (int i = begin; i <= end; i++)
                board[ship.getRowStart()][i] = length;
        }
        player.setBoard(board);
    }

    private void printSpace() {
        System.out.print(String.format("%10s", ""));
    }

    private void printColumn(int size) {
        System.out.print("|  |");
        for (int i = 1; i <= size; i++)
            System.out.print(String.format(" %-2s|", i));
    }

    private void printRow(char[][] board, int row, Player player) {
        System.out.print(String.format("|%2s|", row));
        for (int j = 1; j <= player.getSizeBoard(); j++) {
            printShip(board, row, j);
        }
    }

    private void print_(int size) {
        for (int i = 0; i < size*4; i++)
            System.out.print("_");
    }

    public void displayBoardGame(char[][] board, char[][] boardOpposite, Player player) {
        int size = player.getSizeBoard();
        System.out.print(Effect.reset + Effect.green);
        print_(size+1);
        printSpace();
        print_(size+1);
        System.out.println();
        printColumn(size);
        printSpace();
        printColumn(size);
        System.out.println();
        for (int i = 1; i <= size; i++) {
            printRow(board, i, player);
            printSpace();
            printRow(boardOpposite, i, player);
            System.out.println();
        }
        System.out.print(Effect.reset + Effect.blue);
    }
}
