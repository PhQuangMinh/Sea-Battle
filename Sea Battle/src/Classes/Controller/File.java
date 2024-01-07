import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class File {
    public void printRanking(Scanner scanner, Player player, Player oppositePlayer) {
        printTitle();
        ArrayList<Player> listPlayer = new ArrayList<>();
        read(listPlayer);
        if (!listPlayer.isEmpty()) {
            sortPlayer(listPlayer);
            printTerminal(listPlayer, player, oppositePlayer);
        }
        Effect.EnterToContinue(scanner);
    }

    private void read(ArrayList<Player> listPlayer) {
        try {
            FileReader fileReader = new FileReader("Ranking.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name = bufferedReader.readLine();
            if (name == null) {
                bufferedReader.close();
                return;
            }
            int size = Integer.parseInt(name);
            for (int i = 1; i <= size; i++) {
                name = bufferedReader.readLine();
                int shots = Integer.parseInt(bufferedReader.readLine());
                int ship = Integer.parseInt(bufferedReader.readLine());
                Player newPlayer = new Player(name, shots, ship);
                listPlayer.add(newPlayer);
            }
            bufferedReader.close();
        } catch (IOException e) {
        }
    }

    public void addPlayer(Player player) {
        ArrayList<Player> listPlayer = new ArrayList<>();
        read(listPlayer);
        Player newPlayer = new Player(player.getName(), player.getNumberShot(), player.remainNumberShips());
        listPlayer.add(newPlayer);
        sortPlayer(listPlayer);
        writeFile(listPlayer);
    }

    private void sortPlayer(ArrayList<Player> listPlayer) {
        listPlayer.sort(new Comparator<Player>() {
            public int compare(Player player1, Player player2) {
                int compareByShot = Integer.compare(player1.getNumberShot(), player2.getNumberShot());
                if (compareByShot != 0) {
                    return compareByShot;
                } else {
                    return Integer.compare(player1.getShip(), player2.getShip());
                }
            }
        });
    }

    private void printTitle() {
        System.out.println(Effect.yellow + " ____      _    _   _ _  _____ _   _  ____ \r\n" + //
                "|  _ \\    / \\  | \\ | | |/ /_ _| \\ | |/ ___|\r\n" + //
                "| |_) |  / _ \\ |  \\| | ' / | ||  \\| | |  _ \r\n" + //
                "|  _ <  / ___ \\| |\\  | . \\ | || |\\  | |_| |\r\n" + //
                "|_| \\_\\/_/   \\_\\_| \\_|_|\\_\\___|_| \\_|\\____|" + Effect.blue);
        System.out.print(Effect.red + "Rank");
        System.out.print(Effect.cyan + String.format("%10s", "Name"));
        System.out.print(Effect.green + String.format("%14s", "Shot"));
        System.out.print(Effect.blue + String.format("%14s\n", "Ship"));
    }

    private void printInformation(Player player, int index) {
        System.out.print(String.format("%-10s", index + 1));
        System.out.print(String.format("%-14s", player.getName()));
        System.out.print(String.format("%-14s", player.getNumberShot()));
        System.out.print(String.format("%-14s\n", player.getShip()));
    }

    private void printTerminal(ArrayList<Player> listPlayer, Player player, Player oppositePlayer) {
        for (int i = 0; i < listPlayer.size(); i++) {
            printInformation(listPlayer.get(i), i);
        }
    }

    private void writeFile(ArrayList<Player> listPlayer) {

        try {
            FileWriter fileWriter = new FileWriter("Ranking.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(listPlayer.size()));
            bufferedWriter.newLine();
            for (Player player : listPlayer) {
                bufferedWriter.write(player.getName());
                bufferedWriter.newLine();
                String numberShot = String.valueOf(player.getNumberShot());
                bufferedWriter.write(numberShot);
                bufferedWriter.newLine();
                String ship = String.valueOf(player.getShip());
                bufferedWriter.write(ship);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    private void writeBoard(char[][] board, BufferedWriter bufferedWriter, Player player) {
        int size = player.getSizeBoard();
        try {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++)
                    bufferedWriter.write(String.valueOf(board[i][j]));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {

        }
    }

    private void writeShip(ArrayList<Ship> list, BufferedWriter bufferedWriter) {
        try {
            for (Ship temple : list) {
                bufferedWriter.write(temple.getName());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getRowStart()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getRowEnd()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getColumnStart()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getColumnEnd()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getLength()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getStatus()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {

        }
    }

    private void writeShot(ArrayList<Shot> list, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(String.valueOf(list.size()));
            bufferedWriter.newLine();
            for (Shot temple : list) {
                bufferedWriter.write(String.valueOf(temple.getRow()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temple.getColumn()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {

        }
    }

    private void writePlayer(Player player, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(String.valueOf(player.getIndex()));
            bufferedWriter.newLine();
            bufferedWriter.write(player.getName());
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(player.getNumberShot()));
            bufferedWriter.newLine();
            writeBoard(player.getBoard(), bufferedWriter, player);
            writeBoard(player.getOppositeBoard(), bufferedWriter, player);
            bufferedWriter.write(String.valueOf(player.getCompleted()));
            bufferedWriter.newLine();
            writeShip(player.getShips(), bufferedWriter);
            writeShot(player.getShots(), bufferedWriter);
            bufferedWriter.write(String.valueOf(player.getStatusNormal()));
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    public void write(Player player, Player oppositePlayer, int swap, int level) {
        try {
            FileWriter fileWriter = new FileWriter("Continue.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(3-swap));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(player.getSizeBoard()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(level));
            bufferedWriter.newLine();
            writePlayer(player, bufferedWriter);
            writePlayer(oppositePlayer, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    private char[][] readBoard(BufferedReader bufferedReader, int size) {
        char[][] board = new char[25][25];
        try {
            for (int i = 1; i <= size; i++) {
                String temple = bufferedReader.readLine();
                for (int j = 1; j <= size; j++)
                    board[i][j] = temple.charAt(j - 1);
            }
        } catch (IOException e) {
        }
        return board;
    }

    private ArrayList<Ship> readShip(BufferedReader bufferedReader) {
        ArrayList<Ship> list = new ArrayList<>();
        try {
            for (int i = 0; i < 5; i++) {
                String name = bufferedReader.readLine();
                int rowStart = Integer.parseInt(bufferedReader.readLine());
                int rowEnd = Integer.parseInt(bufferedReader.readLine());
                int columnStart = Integer.parseInt(bufferedReader.readLine());
                int columnEnd = Integer.parseInt(bufferedReader.readLine());
                int length = Integer.parseInt(bufferedReader.readLine());
                boolean status = Boolean.parseBoolean(bufferedReader.readLine());
                Ship ship = new Ship(name, rowStart, columnStart, rowEnd, columnEnd, length, status);
                list.add(ship);
            }
        } catch (IOException e) {

        }
        return list;

    }

    private ArrayList<Shot> readShot(BufferedReader bufferedReader) {
        ArrayList<Shot> list = new ArrayList<>();
        try {
            int size = Integer.parseInt(bufferedReader.readLine());
            for (int i = 1; i <= size; i++) {
                int row = Integer.parseInt(bufferedReader.readLine());
                int column = Integer.parseInt(bufferedReader.readLine());
                Shot shot = new Shot(row, column);
                list.add(shot);
            }
        } catch (IOException e) {

        }
        return list;
    }

    private Player readPlayer(BufferedReader bufferedReader, int size) {
        Player player = new Player();
        try {
            int index = Integer.parseInt(bufferedReader.readLine());
            String name = bufferedReader.readLine();
            int numberShot = Integer.parseInt(bufferedReader.readLine());
            char[][] board = readBoard(bufferedReader, size);
            char[][] oppositeBoard = readBoard(bufferedReader, size);
            boolean completed = Boolean.parseBoolean(bufferedReader.readLine());
            ArrayList<Ship> ships = readShip(bufferedReader);
            ArrayList<Shot> shots = readShot(bufferedReader);
            boolean statusNormal = Boolean.parseBoolean(bufferedReader.readLine());
            player = new Player(index, name, numberShot, board, oppositeBoard, completed, ships, shots, size,
                    statusNormal);
        } catch (IOException e) {
        }
        return player;
    }

    public void read(Scanner scanner, Player player, Player oppositePlayer, Music music) {
        try {
            FileReader fileReader = new FileReader("Continue.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name = bufferedReader.readLine();
            if (name == null) {
                System.out.println(Effect.red + "The game hasn't any datas" + Effect.blue);
                Effect.EnterToContinue(scanner);
                MenuGame menuGame = new MenuGame();
                menuGame.menu(scanner, player, oppositePlayer, music);
                bufferedReader.close();
                return;
            }
            int swap = Integer.parseInt(name);
            int size = Integer.parseInt(bufferedReader.readLine());
            int level = Integer.parseInt(bufferedReader.readLine());
            player = readPlayer(bufferedReader, size);
            oppositePlayer = readPlayer(bufferedReader, size);
            bufferedReader.close();
            Effect.EnterToContinue(scanner);
            Effect.clearScreen();
            Play play = new Play();
            play.menuInGame(scanner, player, oppositePlayer, level, swap, music);
        } catch (NullPointerException | IOException e) {
            System.out.println(Effect.red + "The game hasn't any datas" + Effect.blue);
            Effect.EnterToContinue(scanner);
            MenuGame menuGame = new MenuGame();
            menuGame.menu(scanner, player, oppositePlayer, music);
        }
    }
}
