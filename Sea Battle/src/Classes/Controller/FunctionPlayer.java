import java.util.ArrayList;
import java.util.Scanner;

public class FunctionPlayer {
    public void enterPlayerWithComputer(Scanner scanner, int index, Player player, Player oppositePlayer, int level,
            int size, Music music) {
        Board board = new Board();
        char[][] boardTemple = board.createBoard(size);
        char[][] oppositeBoard = board.createBoard(size);
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Shot> shots = new ArrayList<>();
        oppositePlayer = new Player(level, "Computer", 0, boardTemple, oppositeBoard, true, ships, shots, size, false);
        GameShip gameShip = new GameShip();
        gameShip.createShips(scanner, oppositePlayer);
        enterPlayerWithPlayer(scanner, index, player, oppositePlayer, false, level, size, music);
    }

    public void enterPlayerWithPlayer(Scanner scanner, int index, Player player, Player oppositePlayer, boolean bool,
            int level, int size, Music music) {
        Effect.clearScreen();
        System.out.println(Effect.yellow
                + " _   _    _    __  __ _____      ____  _        _ __   _______ ____   \r\n" + //
                "| \\ | |  / \\  |  \\/  | ____|    |  _ \\| |      / \\\\ \\ / / ____|  _ \\  \r\n" + //
                "|  \\| | / _ \\ | |\\/| |  _|      | |_) | |     / _ \\\\ V /|  _| | |_) | \r\n" + //
                "| |\\  |/ ___ \\| |  | | |___     |  __/| |___ / ___ \\| | | |___|  _ <  \r\n" + //
                "|_| \\_/_/   \\_\\_|  |_|_____|    |_|   |_____/_/   \\_\\_| |_____|_| \\_\\\n " + Effect.blue);
        System.out.print("Enter name player: " + Effect.red);
        String namePlayer = scanner.nextLine();
        System.out.println(Effect.blue);
        Board board = new Board();
        char[][] boardTemple = board.createBoard(size);
        char[][] oppositeBoard = board.createBoard(size);
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Shot> shots = new ArrayList<>();
        player = new Player(index, namePlayer, 0, boardTemple, oppositeBoard, false, ships, shots, size, false);
        enterTypeShip(scanner, player, oppositePlayer, bool, level, music);
    }

    public void enterTypeShip(Scanner scanner, Player player, Player oppositePlayer, boolean bool, int level, Music music) {
        Effect.clearScreen();
        System.out.println(Effect.yellow + " _____ _   _ _____ _____ ____    ____  _   _ ___ ____  ____  \r\n" + //
                "| ____| \\ | |_   _| ____|  _ \\  / ___|| | | |_ _|  _ \\/ ___| \r\n" + //
                "|  _| |  \\| | | | |  _| | |_) | \\___ \\| |_| || || |_) \\___ \\ \r\n" + //
                "| |___| |\\  | | | | |___|  _ <   ___) |  _  || ||  __/ ___) |\r\n" + //
                "|_____|_| \\_| |_| |_____|_| \\_\\ |____/|_| |_|___|_|   |____/ \n" + Effect.blue);
        System.out.println("1. Automatic ship entry");
        System.out.println("2. Custom ship entry");
        System.out.println("3. Exit");
        int choice = Effect.enterChoice(3, scanner);
        GameShip gameShip = new GameShip();
        switch (choice) {
            case 1:
                gameShip.automaticShip(scanner, player, oppositePlayer, bool, level, music);
                break;
            case 2:
                gameShip.customShip(scanner, player, oppositePlayer, bool, level, music);
                break;
            case 3:
                if (bool) {
                    MenuPlayer menuPlayer = new MenuPlayer();
                    menuPlayer.menuWithPlayer(scanner, player, oppositePlayer, player.getSizeBoard(), music);
                } else {
                    MenuComputer menuComputer = new MenuComputer();
                    menuComputer.menuWithComputer(scanner, player, oppositePlayer, level, player.getSizeBoard(), music);
                }
                break;
        }

    }

    public int shot(Scanner scanner, Player player, Player oppositePlayer, int swap, Music music) {
        System.out.println(Effect.green + "ENTER THE POSITION YOU WANT TO SHOT" + Effect.reset + Effect.blue);
        System.out.print("Enter row: " + Effect.red);
        int row = Integer.parseInt(scanner.nextLine());
        System.out.print(Effect.blue + "Enter column: " + Effect.red);
        int column = Integer.parseInt(scanner.nextLine());
        System.out.println(Effect.blue);
        Shot shot = new Shot(row, column);
        GameShot gameShot = new GameShot();
        return gameShot.checkShipShot(player, oppositePlayer, shot, swap, scanner, music);
    }

    public int selectionInGame(Scanner scanner, Player player, Player oppositePlayer, int swap, int level, Music music) {
        System.out.println("1. Shot");
        System.out.println("2. Exit");
        int choice = Effect.enterChoice(2, scanner);
        switch (choice) {
            case 1:
                return shot(scanner, player, oppositePlayer, swap, music);
            case 2:
                File file = new File();
                file.write(player, oppositePlayer, swap, level);
                return 3;
        }
        return 3;
    }

    public int playerShotOut(Scanner scanner, Player player, Player oppositePlayer, int swap, int level, Music music) {
        Play play = new Play();
        play.printInformation(player, oppositePlayer);
        return selectionInGame(scanner, player, oppositePlayer, swap, level, music);
    }
}
