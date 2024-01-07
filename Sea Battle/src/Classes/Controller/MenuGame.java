import java.util.Scanner;

public class MenuGame {

    private void instruction(Scanner scanner, Player player, Player oppositePlayer) {
        System.out.println(Effect.yellow
                + "         ___ _   _ ____ _____ ____  _   _  ____ _____ ___ ___  _   _ \r\n" + //
                "        |_ _| \\ | / ___|_   _|  _ \\| | | |/ ___|_   _|_ _/ _ \\| \\ | |\r\n" + //
                "         | ||  \\| \\___ \\ | | | |_) | | | | |     | |  | | | | |  \\| |\r\n" + //
                "         | || |\\  |___) || | |  _ <| |_| | |___  | |  | | |_| | |\\  |\r\n" + //
                "        |___|_| \\_|____/ |_| |_| \\_\\\\___/ \\____| |_| |___\\___/|_| \\_|" + Effect.blue);
        System.out.println(
                "\nThe game board is a square matrix with rows and columns labeled by numbers.");
        System.out.println(
                "Use the keyboard to input the position of the ship and the position where you want to shoot.");
        System.out.println("If you sink all the ships, you will achieve victory.");
        Effect.EnterToContinue(scanner);
    }

    public void menu(Scanner scanner, Player player, Player oppositePlayer, Music music) {
        Effect.clearScreen();
        System.out.println(Effect.yellow +
                "         ______   ______   ________        _______   ________   _________  _________  __       ______\n"
                + "        /_____/\\ /_____/\\ /_______/\\     /_______/\\ /_______/\\ /________/\\/________/\\/_/|     /_____/\\\n"
                + "        \\::::_\\/_\\::::_\\/_\\::: _  \\ \\    \\::: _  \\ \\\\::: _  \\ \\\\__.::.__\\/\\__.::.__\\/\\:\\ \\    \\::::_\\/_  \n"
                + "         \\:\\/___/\\\\:\\/___/\\\\::(_)  \\ \\    \\::(_)  \\/_\\::(_)  \\ \\  \\::\\ \\     \\::\\ \\   \\:\\ \\    \\:\\/___/\\\n"
                + "          \\_::._\\:\\\\::___\\/_\\:: __  \\ \\    \\::  _  \\ \\\\:: __  \\ \\  \\::\\ \\     \\::\\ \\   \\:\\ \\____\\::___\\/_  \n"
                + "            /____\\:\\\\:\\____/\\\\:.\\ \\  \\ \\    \\::(_)  \\ \\\\:.\\ \\  \\ \\  \\::\\ \\     \\::\\ \\   \\:\\/___/\\\\:\\____/\\ \n"
                + "            \\_____\\/ \\_____\\/ \\__\\/\\__\\/     \\_______\\/ \\__\\/\\__\\/   \\__\\/      \\__\\/    \\_____\\/ \\_____\\/\n"
                + Effect.blue);
        System.out.println("\n1. New play");
        System.out.println("2. Continue Play");
        System.out.println("3. Instruction");
        System.out.println("4. Ranking");
        System.out.println("5. Music");
        System.out.println("6. Exit");
        int choice = Effect.enterChoice(6, scanner);
        Effect.clearScreen();
        switch (choice) {
            case 1:
                enterSize(scanner, player, oppositePlayer, music);
                break;
            case 2:
                continuePlay(scanner, player, oppositePlayer, music);
                break;
            case 3:
                instruction(scanner, player, oppositePlayer);
                menu(scanner, player, oppositePlayer, music);
                break;
            case 4:
                File file = new File();
                file.printRanking(scanner, player, oppositePlayer);
                menu(scanner, player, oppositePlayer, music);
                break;
            case 5:
                FunctionMusic functionMusic = new FunctionMusic();
                functionMusic.menuMusic(scanner, player, oppositePlayer, music);
                break;
            case 6:
                Effect.clearScreen();
                Effect.image(scanner);
                return;
        }
    }

    private void enterSize(Scanner scanner, Player player, Player oppositPlayer, Music music) {
        System.out.println(Effect.yellow + "           ____ ___ __________   ____   ___    _    ____  ____  \r\n" + //
                "          / ___|_ _|__  / ____| | __ ) / _ \\  / \\  |  _ \\|  _ \\ \r\n" + //
                "          \\___ \\| |  / /|  _|   |  _ \\| | | |/ _ \\ | |_) | | | |\r\n" + //
                "           ___) | | / /_| |___  | |_) | |_| / ___ \\|  _ <| |_| |\r\n" + //
                "          |____/___/____|_____| |____/ \\___/_/   \\_\\_| \\_\\____/ \n" + Effect.blue);
        int size;
        do{
            System.out.print("Enter size board (10 - 20): ");
            size = Integer.parseInt(scanner.nextLine());
            if (size<10 || size>20){
                System.out.println(Effect.red + "You entered wrong size!" + Effect.blue);
            }
        }while (size<10 || size>20);
        newPlay(scanner, oppositPlayer, player, size, music);
    }

    public void continuePlay(Scanner scanner, Player player, Player oppositePlayer, Music music) {
        System.out.println(Effect.yellow
                + "          ____ ___  _   _ _____ ___ _   _ _   _ _____                     \r\n" +
                "         / ___/ _ \\| \\ | |_   _|_ _| \\ | | | | | ____|                    \r\n" +
                "        | |  | | | |  \\| | | |  | ||  \\| | | | |  _|                      \r\n" +
                "        | |__| |_| | |\\  | | |  | || |\\  | |_| | |___                     \r\n" +
                "         \\____\\___/|_|_\\_| |_| |___|_| \\_|\\___/|_____|                    \r\n" +
                "                  |  _ \\| |      / \\\\ \\ / /                               \r\n" +
                "                  | |_) | |     / _ \\\\ V /                                \r\n" +
                "                  |  __/| |___ / ___ \\| |                                 \r\n" +
                "                  |_|   |_____/_/   \\_\\_|                                 \n" + Effect.blue);
        File file = new File();
        file.read(scanner, player, oppositePlayer, music);
    }

    public void newPlay(Scanner scanner, Player player, Player oppositePlayer, int size, Music music) {
        Effect.clearScreen();
        System.out.println(Effect.yellow + "         _   _ _______        __  ____  _        _ __   __\r\n" + //
                "        | \\ | | ____\\ \\      / / |  _ \\| |      / \\\\ \\ / /\r\n" + //
                "        |  \\| |  _|  \\ \\ /\\ / /  | |_) | |     / _ \\\\ V / \r\n" + //
                "        | |\\  | |___  \\ V  V /   |  __/| |___ / ___ \\| |  \r\n" + //
                "        |_| \\_|_____|  \\_/\\_/    |_|   |_____/_/   \\_\\_|  " + Effect.blue);
        System.out.println("\n1. Play with player");
        System.out.println("2. Play with computer");
        System.out.println("3. Exit");
        int choice = Effect.enterChoice(4, scanner);
        Effect.clearScreen();
        switch (choice) {
            case 1:
                MenuPlayer menuPlayer = new MenuPlayer();
                menuPlayer.menuWithPlayer(scanner, player, oppositePlayer, size, music);
                break;
            case 2:
                MenuComputer menuComputer = new MenuComputer();
                menuComputer.menu(scanner, player, oppositePlayer, size, music);
                break;
            case 3:
                menu(scanner, player, oppositePlayer, music);
                return;
        }
    }
}
