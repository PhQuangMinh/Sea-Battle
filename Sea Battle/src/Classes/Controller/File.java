import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class File {
    public void printRanking(Scanner scanner, Player player, Player oppositePlayer) {
        printTitle();
        ArrayList<Player> listPlayer = read();
       if (!listPlayer.isEmpty()) {
           listPlayer = sortPlayer(listPlayer);
           printTerminal(listPlayer, player, oppositePlayer);
       }
        Effect.EnterToContinue(scanner);
    }

    private ArrayList<Player> read() {
        ArrayList<Player> listPlayer = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Rank.in"));
            listPlayer = (ArrayList<Player>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            return listPlayer;
        }
        return listPlayer;
    }

    public void addPlayer(Player player) {
        ArrayList<Player> listPlayer = read();
        listPlayer.add(player);
        listPlayer = sortPlayer(listPlayer);
        writeFile(listPlayer);
    }

    private ArrayList<Player> sortPlayer(ArrayList<Player> listPlayer) {
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
        return listPlayer;
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
        System.out.print(String.format("%-14s\n", player.remainNumberShips()));
    }

    private void printTerminal(ArrayList<Player> listPlayer, Player player, Player oppositePlayer) {
        for (int i = 0; i < listPlayer.size(); i++) {
            printInformation(listPlayer.get(i), i);
        }
    }

    private void writeFile(ArrayList<Player> listPlayer) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("rank.in"));
            objectOutputStream.reset();
            objectOutputStream.writeObject(listPlayer);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    public void write(Player player, Player oppositePlayer, int swap, int level) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("continue.in"));
            objectOutputStream.reset();
            objectOutputStream.writeInt(3-swap);
            objectOutputStream.writeInt(level);
            objectOutputStream.writeObject(player);
            objectOutputStream.writeObject(oppositePlayer);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    public void read(Scanner scanner, Player player, Player oppositePlayer, Music music) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("continue.in"));
            int swap = objectInputStream.readInt();
            int level = objectInputStream.readInt();
            player = (Player)objectInputStream.readObject();
            oppositePlayer = (Player)objectInputStream.readObject();
            objectInputStream.close();
            Effect.EnterToContinue(scanner);
            Effect.clearScreen();
            Play play = new Play();
            play.menuInGame(scanner, player, oppositePlayer, level, swap, music);
        } catch (NullPointerException | IOException | ClassNotFoundException e) {
            System.out.println(Effect.red + "The game hasn't any datas" + Effect.blue);
            Effect.EnterToContinue(scanner);
            MenuGame menuGame = new MenuGame();
            menuGame.menu(scanner, player, oppositePlayer, music);
        }
    }
}
