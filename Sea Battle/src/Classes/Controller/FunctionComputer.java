import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class FunctionComputer {
    public int computerShotOut(Scanner scanner, Player player, Player oppositePlayer, int level, int swap,
            Music music) {
        switch (level) {
            case 1:
                return easyComputer(scanner, player, oppositePlayer, swap, level, music);
            case 2:
                return normalComputer(scanner, player, oppositePlayer, swap, level, music);
            case 3:
                return hardComputer(scanner, player, oppositePlayer, swap, level, music);
        }
        return 0;
    }

    private void printPosition(Shot shot) {
        System.out.println(Effect.red + "Computer's shooting position: " + Effect.blue);
        System.out.println("Row: " + Effect.red + shot.getRow());
        System.out.println(Effect.blue + "Column: " + Effect.red + shot.getColumn() + Effect.blue);
    }

    private void takeShot(Player oppositePlayer, ArrayList<Shot> list) {
        char[][] board = oppositePlayer.getBoard();
        for (int i = 1; i <= 10; i++)
            for (int j = 1; j <= 10; j++)
                if (board[i][j] >= '2' && board[i][j] <= '5') {
                    Shot shot = new Shot(i, j);
                    list.add(shot);
                }
    }

    private int shooting(Player player, Player oppositePlayer, Shot shot, int swap, Scanner scanner, Music music) {
        Effect.loading("The computer is shooting! Loading...");
        printPosition(shot);
        GameShot gameShot = new GameShot();
        return gameShot.checkShipShot(player, oppositePlayer, shot, swap, scanner, music);
    }

    private int listShoot(Player player, Player oppositePlayer, ArrayList<Shot> list, int swap, Scanner scanner,
            Music music) {
        for (Shot temple : list) {
            boolean bool = false;
            if (!temple.checkHit(oppositePlayer.getBoard())) {
                bool = true;
            } else {
                ArrayList<Ship> oppoShips = oppositePlayer.getShips();
                for (Ship ship : oppoShips) {
                    if (ship.checkShotInShip(temple)) {
                        char[][] oppositeBoard = oppositePlayer.getBoard();
                        char value = oppositeBoard[temple.getRow()][temple.getColumn()];
                        oppositeBoard[temple.getRow()][temple.getColumn()] = 'x';
                        if (ship.checkDestroyed(oppositeBoard) == true) {
                            oppositeBoard[temple.getRow()][temple.getColumn()] = value;
                            oppositePlayer.setBoard(oppositeBoard);
                            shooting(player, oppositePlayer, temple, swap, scanner, music);
                            player.setStatusNormal(false);
                            return 2;
                        }
                        oppositeBoard[temple.getRow()][temple.getColumn()] = value;
                        oppositePlayer.setBoard(oppositeBoard);
                    }
                }
            }
            shooting(player, oppositePlayer, temple, swap, scanner, music);
            Effect.clearScreen();
            if (bool)
                return 1;
        }
        return 2;
    }

    private Shot randomShot(Player oppsitePlayer, int level) {
        int[] rdom = { 100, 7, 6 };
        Random random = new Random();
        int number = random.nextInt(rdom[level - 1]) + 1;
        Shot shot = new Shot();
        if (number == level) {
            ArrayList<Shot> list = new ArrayList<>();
            takeShot(oppsitePlayer, list);
            number = random.nextInt(list.size());
            shot = list.get(number);
        } else {
            int row = random.nextInt(oppsitePlayer.getSizeBoard()) + 1;
            int column = random.nextInt(oppsitePlayer.getSizeBoard()) + 1;
            shot = new Shot(row, column);
        }
        if (!shot.checkPosition(oppsitePlayer.getBoard(), oppsitePlayer.getSizeBoard()))
            return randomShot(oppsitePlayer, level);
        return shot;
    }

    public int easyComputer(Scanner scanner, Player player, Player oppositePlayer, int swap, int level, Music music) {
        Shot shot = randomShot(oppositePlayer, level);
        return shooting(player, oppositePlayer, shot, swap, scanner, music);
    }

    private boolean check(Shot shot, char[][] board, int size) {
        if (shot.getRow() > size || shot.getRow() < 1 || shot.getColumn() > size || shot.getColumn() < 1
                || board[shot.getRow()][shot.getColumn()] == 'x'
                || board[shot.getRow()][shot.getColumn()] == 'o')
            return false;
        return true;
    }

    public int normalComputer(Scanner scanner, Player player, Player oppositePlayer, int swap, int level, Music music) {
        ArrayList<Shot> listShot = new ArrayList<>();
        char[][] oppositeBoard = oppositePlayer.getBoard();
        listShot = player.getShots();
        if (player.getStatusNormal()) {
            player.setStatusNormal(false);
            ArrayList<Shot> list = new ArrayList<>();
            player.setShots(list);
            return listShoot(player, oppositePlayer, listShot, swap, scanner, music);
        }
        if (listShot.size() > 1) {
            Random random = new Random();
            int number = random.nextInt(listShot.size() - 1) + 1;
            Shot firstShot = listShot.get(number);
            listShot.remove(firstShot);
            if (firstShot.checkHit(oppositeBoard)) {
                ArrayList<Shot> list = new ArrayList<>();
                ArrayList<Shot> oppoList = new ArrayList<>();
                Shot shot = listShot.get(0);
                listShot.clear();
                player.setShots(listShot);
                boolean okList = true;
                boolean okOppoList = true;
                int count = 0;
                do {
                    count++;
                    Shot temple1 = new Shot();
                    Shot temple2 = new Shot();
                    int oppoCount = count;
                    if (firstShot.getColumn() == shot.getColumn()) {
                        if (firstShot.getRow() < shot.getRow())
                            oppoCount = -count;
                        temple1 = new Shot(shot.getRow() + oppoCount, shot.getColumn());
                        temple2 = new Shot(shot.getRow() - oppoCount, shot.getColumn());
                    } else {
                        if (firstShot.getColumn() < shot.getColumn())
                            oppoCount = -count;
                        temple1 = new Shot(shot.getRow(), shot.getColumn() + oppoCount);
                        temple2 = new Shot(shot.getRow(), shot.getColumn() - oppoCount);
                    }
                    if (!check(temple1, oppositeBoard, player.getSizeBoard())
                            && !check(temple2, oppositeBoard, player.getSizeBoard()))
                        break;
                    if (check(temple1, oppositeBoard, player.getSizeBoard())
                            && temple1.checkPosition(oppositeBoard, player.getSizeBoard()) && okList){
                        list.add(temple1);
                        if (!temple1.checkHit(oppositeBoard)) okList = false;
                    }
                    if (check(temple2, oppositeBoard, player.getSizeBoard())
                            && temple2.checkPosition(oppositeBoard, player.getSizeBoard()) && okOppoList){
                        oppoList.add(temple2);
                        if (!temple2.checkHit(oppositeBoard)) okOppoList = false;
                    }
                } while (true);
                if (!oppoList.isEmpty() || oppoList.get(0).checkHit(oppositeBoard)) {
                    player.setShots(oppoList);
                    player.setStatusNormal(true);
                }
                for (Shot shottt:list){
                    System.out.println(shottt.getRow() + " " + shottt.getColumn());
                }
                return listShoot(player, oppositePlayer, list, swap, scanner, music);
            }
            return shooting(player, oppositePlayer, firstShot, swap, scanner, music);
        }
        int[] row = { 0, -1, 0, 1 };
        int[] column = { -1, 0, 1, 0 };
        Shot shot;
        shot = randomShot(oppositePlayer, level);
        if (shot.checkHit(oppositeBoard)) {
            ArrayList<Shot> list = new ArrayList<>();
            list.add(shot);
            for (int i = 0; i < 4; i++) {
                Shot secondShot = new Shot(shot.getRow() + row[i], shot.getColumn() + column[i]);
                if (secondShot.checkPosition(oppositeBoard, player.getSizeBoard()))
                    list.add(secondShot);
            }
            player.setShots(list);
        }
        Effect.clearScreen();
        return shooting(player, oppositePlayer, shot, swap, scanner, music);
    }

    public int hardComputer(Scanner scanner, Player player, Player oppositePlayer, int swap, int level, Music music) {
        int[] row = { 0, -1, 0, 1 };
        int[] column = { -1, 0, 1, 0 };
        char[][] board = oppositePlayer.getBoard();
        Shot shot;
        while (true) {
            shot = randomShot(oppositePlayer, level);
            if (shot.checkHit(board)) {
                Queue<Shot> queue = new LinkedList<>();
                queue.add(shot);
                while (!queue.isEmpty()) {
                    shot = queue.poll();
                    if (!shot.checkPosition(board, player.getSizeBoard()))
                        continue;
                    Effect.clearScreen();
                    shooting(player, oppositePlayer, shot, swap, scanner, music);
                    for (int i = 0; i < 4; i++) {
                        Shot secondShot = new Shot(shot.getRow() + row[i], shot.getColumn() + column[i]);
                        if (secondShot.checkPosition(board, player.getSizeBoard())) {
                            if (secondShot.checkHit(board)) {
                                queue.add(secondShot);
                            }
                        }
                    }
                    if (queue.isEmpty() && oppositePlayer.remainNumberShips() == 0) {
                        return 2;
                    }
                }
            } else {
                Effect.clearScreen();
                return shooting(player, oppositePlayer, shot, swap, scanner, music);
            }
        }
    }
}
