import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameShip {
    private int lengthShip(int index) {
        switch (index) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 3;
        }
        return 5;
    }

    private void menuAuto() {
        System.out.println("1. Ok");
        System.out.println("2. Swap");
        System.out.println("3. Exit");
    }

    public void createShips(Scanner scanner, Player player) {
        int[] length = { 1, 1, 3, 2, 4 };
        String[] nameShip = {"Patrol Boat", "Patrol Boat", "Destroyer Boat", "Submarine", "Battle Ship"};
        for (int i = 0; i <= 4; i++) {
            createAutomaticShip(scanner, length[i], player, nameShip[i]);
        }
    }
    public void automaticShip(Scanner scanner, Player player, Player oppositePlayer, boolean bool, int level, Music music) {
        do {
            Effect.clearScreen();
            createShips(scanner, player);
            Board board = new Board();
            System.out.println(Effect.yellow + "          _    ____  ____    ____  _   _ ___ ____  ____  \r\n" + //
                    "         / \\  |  _ \\|  _ \\  / ___|| | | |_ _|  _ \\/ ___| \r\n" + //
                    "        / _ \\ | | | | | | | \\___ \\| |_| || || |_) \\___ \\ \r\n" + //
                    "       / ___ \\| |_| | |_| |  ___) |  _  || ||  __/ ___) |\r\n" + //
                    "      /_/   \\_\\____/|____/  |____/|_| |_|___|_|   |____/ \n" + Effect.blue);
            System.out.print(String.format("%20s", ""));
            System.out.print(Effect.blue + "BOARD: " + Effect.reset);
            System.out.println(Effect.purple + player.getName() + Effect.reset);
            board.displayBoardShip(player.getBoard(), player);
            menuAuto();
            int choice = Effect.enterChoice(3, scanner);
            switch (choice) {
                case 1:
                    player.setCompleted(true);
                    break;
                case 2:
                    ArrayList<Ship> ships = new ArrayList<>();
                    player.setShips(ships);
                    player.setBoard(board.createBoard(player.getSizeBoard()));
                    break;
                case 3:
                    break;
            }
            if (choice == 1 || choice == 3) {
                if (bool) {
                    MenuPlayer menuPlayer = new MenuPlayer();
                    menuPlayer.menuWithPlayer(scanner, player, oppositePlayer, player.getSizeBoard(), music);
                    return;
                } else {
                    MenuComputer menuComputer = new MenuComputer();
                    menuComputer.menuWithComputer(scanner, player, oppositePlayer, level, player.getSizeBoard(), music);
                    return;
                }
            }
        } while (true);
    }

    private void createAutomaticShip(Scanner scanner, int length, Player player, String nameShip) {
        Random random = new Random();
        do {
            int rowStart = random.nextInt(player.getSizeBoard()) + 1;
            int columnStart = random.nextInt(player.getSizeBoard()) + 1;
            int rowEnd = rowStart, columnEnd = columnStart;
            int choice = random.nextInt(4) + 1;
            switch (choice) {
                case 1:
                    columnEnd = columnStart - length;
                    break;
                case 2:
                    rowEnd = rowStart - length;
                    break;
                case 3:
                    columnEnd = columnStart + length;
                    break;
                case 4:
                    rowEnd = rowStart + length;
                    break;
            }
            if (columnEnd < 1 || rowEnd < 1)
                continue;
            Ship ship = new Ship(nameShip, rowStart, columnStart, rowEnd, columnEnd, length + 1, true);
            if (checkValidShip(scanner, player, ship, false)) {
                return;
            }
        } while (true);
    }

    public void customShip(Scanner scanner, Player player, Player oppositePlayer, boolean bool, int level, Music music) {
        int count[] = { 0, 2, 1, 1, 1 };
        int quantity = 0;
        do {
            Effect.clearScreen();
            System.out.println(Effect.yellow + "          _    ____  ____    ____  _   _ ___ ____  ____  \r\n" + //
                    "         / \\  |  _ \\|  _ \\  / ___|| | | |_ _|  _ \\/ ___| \r\n" + //
                    "        / _ \\ | | | | | | | \\___ \\| |_| || || |_) \\___ \\ \r\n" + //
                    "       / ___ \\| |_| | |_| |  ___) |  _  || ||  __/ ___) |\r\n" + //
                    "      /_/   \\_\\____/|____/  |____/|_| |_|___|_|   |____/ \n" + Effect.blue);
            Board board = new Board();
            board.displayBoardShip(player.getBoard(), player);
            System.out.println("\n1. Enter Position Patrol Boat(1 x 2).");
            System.out.println("2. Enter Position Destroyer Boat(1 x 4).");
            System.out.println("3. Enter Position Submarine(1 x 3).");
            System.out.println("4. Enter Position Battle Ship(1 x 5).");
            System.out.println("5. Exit");
            int choice = Effect.enterChoice(5, scanner);
            if (choice == 5) {
                Effect.clearScreen();
                if (quantity == 5) {
                    player.setCompleted(true);
                }
                if (bool) {
                    MenuPlayer menuPlayer = new MenuPlayer();
                    menuPlayer.menuWithPlayer(scanner, player, oppositePlayer, player.getSizeBoard(), music);
                    return;
                } else {
                    MenuComputer menuComputer = new MenuComputer();
                    menuComputer.menuWithComputer(scanner, player, oppositePlayer, level, player.getSizeBoard(), music);
                    return;
                }
            }
            if (count[choice] == 0) {
                System.out.println(Effect.red + "You have entered a sufficient quantity of this type of ship." + Effect.blue);
                Effect.EnterToContinue(scanner);
                continue;
            }
            Ship ship = new Ship();
            String nameShip = ship.nameShip(choice);
            System.out.println(choice + ". Enter Position " + nameShip + "(1 x " + lengthShip(choice) + ").");
            if (createCustomShip(scanner, nameShip, choice, player, true)) {
                count[choice]--;
                quantity++;
            }
        } while (true);
    }

    public boolean createCustomShip(Scanner scanner, String nameShip, int index, Player player, boolean choice) {
        System.out.println(Effect.purple + "Enter starting Position:" + Effect.blue);
        System.out.print("Enter row: " + Effect.red);
        int rowStart = Integer.parseInt(scanner.nextLine());
        System.out.print(Effect.blue + "Enter column: " + Effect.red);
        int columnStart = Integer.parseInt(scanner.nextLine());
        System.out.println(Effect.purple + "Enter ending Position:" + Effect.blue);
        System.out.print("Enter row: " + Effect.red);
        int rowEnd = Integer.parseInt(scanner.nextLine());
        System.out.print(Effect.blue + "Enter column: "  + Effect.red);
        int columnEnd = Integer.parseInt(scanner.nextLine());
        System.out.println(Effect.blue);
        int length = lengthShip(index);
        Ship ship = new Ship(nameShip, rowStart, columnStart, rowEnd, columnEnd, length, true);
        return checkValidShip(scanner, player, ship, choice);
    }

    private boolean checkValidShip(Scanner scanner, Player player, Ship ship, boolean choice) {
        boolean bool = true;
        if (!ship.checkPosition(player.getBoard(), player.getSizeBoard())) {
            bool = false;
            if (choice) System.out.println(Effect.red + "You haven't successfully added a ship." + Effect.blue);
        } else {
            ArrayList<Ship> ships = player.getShips();
            ships.add(ship);
            player.setShips(ships);
            Board board = new Board();
            board.updateBoardShip(ship, player);
            if (choice) System.out.println(Effect.red + "You haven successfully added a ship." + Effect.blue);
        }
        if (choice) Effect.EnterToContinue(scanner);
        return bool;
    }

    public void updateShip(Player player, Player oppositePlayer) {
        ArrayList<Ship> ships = oppositePlayer.getShips();
        for (var ship : ships) {
            if (ship.checkDestroyed(oppositePlayer.getBoard())) {
                ship.setStatus(false);
            }
        }
        oppositePlayer.setShips(ships);
    }
}
