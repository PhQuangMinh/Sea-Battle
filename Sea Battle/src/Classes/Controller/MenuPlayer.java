import java.util.Scanner;

public class MenuPlayer {
    public void menuWithPlayer(Scanner scanner, Player player, Player oppositePlayer, int size, Music music) {
        if (player.getIndex()>0) {
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
            System.out.println("\n1. Player 1");
            System.out.println("2. Player 2");
            System.out.println("3. Play");
            System.out.println("4. Exit");
            int choice = Effect.enterChoice(4, scanner);
            FunctionPlayer functionPlayer = new FunctionPlayer();
            switch (choice) {
                case 1:
                    functionPlayer.enterPlayerWithPlayer(scanner, 0, player, oppositePlayer, true, 0, size, music);
                    break;
                case 2:
                    functionPlayer.enterPlayerWithPlayer(scanner, 1, oppositePlayer, player, true, 0, size, music);
                    break;
                case 3:
                    if (!player.getCompleted() || !oppositePlayer.getCompleted()) {
                        if (!player.getCompleted()) 
                            System.out.println(
                                    Effect.red + "You haven't finished entering player 1." + Effect.blue);
                        if (!oppositePlayer.getCompleted())
                            System.out.println(
                                    Effect.red + "You haven't finished entering player 2." + Effect.blue);
                            Effect.EnterToContinue(scanner);
                    }
                    if (player.getCompleted() && oppositePlayer.getCompleted()) {
                        Play play = new Play();
                        play.menuInGame(scanner, player, oppositePlayer, 0, 1, music);
                        return;
                    }
                    break;
                case 4:
                    MenuGame menuGame = new MenuGame();
                    menuGame.newPlay(scanner, player, oppositePlayer, size, music);
                    return;
            }
        } while (true);
    }

}
