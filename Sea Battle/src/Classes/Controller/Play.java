import java.util.Scanner;

public class Play {
    private void printBetween(String name, String preName) {
        int space = (113 - name.length()) / 2;
        System.out.print(String.format("%" + space + "s", ""));
        System.out.print(preName + Effect.reset + Effect.purple + String.format("%" + name.length() + "s", name)
                + Effect.reset
                + Effect.blue);
        System.out.println();
    }

    public void printInformation(Player player, Player oppositePlayer) {
        Board board = new Board();
        System.out.println(Effect.yellow
                + "                                 _____ _   _ _____      __  __    _  _____ ____ _   _ \r\n" + //
                "                                |_   _| | | | ____|    |  \\/  |  / \\|_   _/ ___| | | |\r\n" + //
                "                                  | | | |_| |  _|      | |\\/| | / _ \\ | || |   | |_| |\r\n" + //
                "                                  | | |  _  | |___     | |  | |/ ___ \\| || |___|  _  |\r\n" + //
                "                                  |_| |_| |_|_____|    |_|  |_/_/   \\_\\_| \\____|_| |_|\n"
                + Effect.blue);
        printBetween(player.getName(), "ROUND: ");
        printNamePlayer(player, oppositePlayer);
        board.displayBoardGame(player.getBoard(), player.getOppositeBoard(), player);
        System.out
                .println("The number of shot on the enemy: " + Effect.red + player.getNumberShot() + Effect.blue);
        System.out.println(
                "The number of ships destroyed: " + Effect.red + oppositePlayer.destroyedShips() + Effect.blue);
        System.out.println(
                "The remaining number of ships: " + Effect.red + player.remainNumberShips() + Effect.blue);
    }

    public void menuInGame(Scanner scanner, Player player, Player oppositePlayer, int level, int swap, Music music) {
        oppositePlayer.setStatusNormal(false);
        do {
            Effect.clearScreen();
            FunctionMusic functionMusic = new FunctionMusic();
            if (player.remainNumberShips() == 0 || oppositePlayer.remainNumberShips() == 0) {
                if (music.getGame()) {
                    functionMusic.playMusic("ProPTIT", false);
                    functionMusic.playMusic(
                            "D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Win.wav", true);
                }
                if (player.remainNumberShips() == 0) {
                    winner(scanner, oppositePlayer, player, level);
                } else
                    winner(scanner, player, oppositePlayer, level);
                Effect.EnterToContinue(scanner);
                if (music.getGame()) {
                    functionMusic.playMusic("ProPTIT", false);
                    functionMusic.playMusic(
                            "D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Beach.wav", true);
                    Effect.clip.loop(Effect.clip.LOOP_CONTINUOUSLY);
                }
                MenuGame menuGame = new MenuGame();
                menuGame.menu(scanner, player, oppositePlayer, music);
                return;
            }
            FunctionPlayer functionPlayer = new FunctionPlayer();
            FunctionComputer functionComputer = new FunctionComputer();
            switch (swap) {
                case 1:
                    swap = functionPlayer.playerShotOut(scanner, player, oppositePlayer, swap, level, music);
                    break;
                case 2:
                    if (level == 0)
                        swap = functionPlayer.playerShotOut(scanner, oppositePlayer, player, swap, level, music);
                    else
                        swap = functionComputer.computerShotOut(scanner, oppositePlayer, player, level, swap, music);
                    break;
                case 3:
                    MenuGame menuGame = new MenuGame();
                    menuGame.menu(scanner, player, oppositePlayer, music);
                    return;
            }
        } while (true);
    }

    private void printName(Player player, boolean choice) {
        String namePlayer = "Board: " + player.getName();
        int space = (44 - namePlayer.length()) / 2;
        System.out.print(String.format("%" + space + "s", ""));
        System.out.print("BOARD: " + Effect.reset + Effect.purple
                + String.format("%" + player.getName().length() + "s", player.getName())
                + Effect.reset
                + Effect.blue);
        if (choice) {
            System.out.print(String.format("%" + (space + 25) + "s", ""));
        } else
            System.out.println();
    }

    public void printNamePlayer(Player player, Player oppositePlayer) {
        printName(player, true);
        printName(oppositePlayer, false);
    }

    private void winner(Scanner scanner, Player player, Player oppositePlayer, int level) {
        System.out.println(Effect.yellow
                + "                                      __        _____ _   _ _   _ _____ ____  \r\n" + //
                "                                      \\ \\      / /_ _| \\ | | \\ | | ____|  _ \\ \r\n" + //
                "                                       \\ \\ /\\ / / | ||  \\| |  \\| |  _| | |_) |\r\n" + //
                "                                        \\ V  V /  | || |\\  | |\\  | |___|  _ < \r\n" + //
                "                                         \\_/\\_/  |___|_| \\_|_| \\_|_____|_| \\_\\\n" + Effect.blue);
        printBetween(player.getName(), "PLAYER: ");
        printNamePlayer(player, oppositePlayer);
        Board board = new Board();
        board.displayBoardGame(player.getBoard(), oppositePlayer.getBoard(), player);
        if (level == 0) {
            File file = new File();
            file.addPlayer(player);
        }
        return;
    }
}
