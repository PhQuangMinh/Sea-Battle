import java.util.Scanner;

public class MenuComputer {
    public void menu(Scanner scanner, Player player, Player oppositePlayer, int size, Music music) {
        Effect.clearScreen();
        System.out.println(Effect.yellow
                + "         ____  _        _ __   __ __        _____ _____ _   _                     \r\n" + //
                "        |  _ \\| |      / \\\\ \\ / / \\ \\      / /_ _|_   _| | | |                    \r\n" + //
                "        | |_) | |     / _ \\\\ V /   \\ \\ /\\ / / | |  | | | |_| |                    \r\n" + //
                "        |  __/| |___ / ___ \\| |     \\ V  V /  | |  | | |  _  |                    \r\n" + //
                "        |_| __|_____/_/_  \\_\\_|__  _ \\_/\\_/__|___|_|_|_|_| |_|                    \r\n" + //
                "           / ___/ _ \\|  \\/  |  _ \\| | | |_   _| ____|  _ \\                        \r\n" + //
                "          | |  | | | | |\\/| | |_) | | | | | | |  _| | |_) |                       \r\n" + //
                "          | |__| |_| | |  | |  __/| |_| | | | | |___|  _ <                        \r\n" + //
                "           \\____\\___/|_|  |_|_|    \\___/  |_| |_____|_| \\_\\                       \n" + Effect.blue);
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        System.out.println("4. Exit");
        int choice = Effect.enterChoice(4, scanner);
        switch (choice) {
            case 1:
            case 2:
            case 3:
                menuWithComputer(scanner, player, oppositePlayer, choice, size, music);
                break;
            case 4:
                MenuGame menuGame = new MenuGame();
                menuGame.newPlay(scanner, player, oppositePlayer, size, music);
                return;
        }
    }

    public void menuWithComputer(Scanner scanner, Player player, Player oppositePlayer, int level, int size, Music music) {
        Effect.clearScreen();
        if (player.getIndex() >0) {
            Player templePlayer = player;
            player = oppositePlayer;
            oppositePlayer = templePlayer;
        }
        do {
            Effect.clearScreen();
            System.out.println(Effect.yellow + "                     _____ _   _ _____ _____ ____\r\n" + //
                    "                    | ____| \\ | |_   _| ____|  _ \\              \r\n" + //
                    "                    |  _| |  \\| | | | |  _| | |_) |             \r\n" + //
                    "                    | |___| |\\  | | | | |___|  _ <              \r\n" + //
                    "         ___ _   _ _|_____|_| \\_| |_| |_____|_|_\\_\\_ ___  _   _ \r\n" + //
                    "        |_ _| \\ | |  ___/ _ \\|  \\/  |  / \\|_   _|_ _/ _ \\| \\ | |\r\n" + //
                    "         | ||  \\| | |_ | | | | |\\/| | / _ \\ | |  | | | | |  \\| |\r\n" + //
                    "         | || |\\  |  _|| |_| | |  | |/ ___ \\| |  | | |_| | |\\  |\r\n" + //
                    "        |___|_| \\_|_|   \\___/|_|  |_/_/   \\_\\_| |___\\___/|_| \\_|" + Effect.blue);
            System.out.println("\n1. Player");
            System.out.println("2. Play");
            System.out.println("3. Exit");
            FunctionPlayer functionPlayer = new FunctionPlayer();
            int choice = Effect.enterChoice(4, scanner);
            switch (choice) {
                case 1:
                    functionPlayer.enterPlayerWithComputer(scanner, 0, player, oppositePlayer, level, size, music);
                    break;
                case 2:
                    if (!player.getCompleted()) {
                        System.out.println(Effect.red + "You haven't finished entering player" + Effect.blue);
                        Effect.EnterToContinue(scanner);
                    }
                    if (player.getCompleted()) {
                        Play play = new Play();
                        play.menuInGame(scanner, player, oppositePlayer, level, 1, music);
                    }
                    break;
                case 3:
                    MenuGame menuGame = new MenuGame();
                    menuGame.newPlay(scanner, player, oppositePlayer, size, music);
                    return;
            }
        } while (true);
    }
}
