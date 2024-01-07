import java.util.ArrayList;
import java.util.Scanner;

public class GameShot {
    public int checkShipShot(Player player, Player oppositePlayer, Shot shot, int swap, Scanner scanner, Music music) {
        char[][] oppositeBoard = oppositePlayer.getBoard();
        char[][] board = player.getOppositeBoard();
        System.out.println(Effect.yellow + "      ____  _____ ____  _   _ _   _____ \r\n" + //
                "     |  _ \\| ____/ ___|| | | | | |_   _|\r\n" + //
                "     | |_) |  _| \\___ \\| | | | |   | |  \r\n" + //
                "     |  _ <| |___ ___) | |_| | |___| |  \r\n" + //
                "     |_| \\_\\_____|____/ \\___/|_____|_|  \n" + Effect.blue);
        if (!shot.checkPosition(board, player.getSizeBoard())) {
            System.out.println(
                    Effect.red + "Your shooting position is not valid. Please shoot again!" + Effect.reset);
        } else {
            if (shot.checkHit(oppositeBoard)) {
                board[shot.getRow()][shot.getColumn()] = 'x';
                oppositeBoard[shot.getRow()][shot.getColumn()] = 'x';
                GameShip gameShip = new GameShip();
                gameShip.updateShip(player, oppositePlayer);
                ArrayList<Ship> list = oppositePlayer.getShips();
                boolean bool = true;
                for (Ship ship : list) {
                    if (ship.checkShotInShip(shot)){
                        if (ship.checkDestroyed(oppositeBoard) == true) {
                            System.out.println(Effect.red + ship.getName() + " is destroyed!");
                            FunctionMusic functionMusic = new FunctionMusic();
                            if (music.getGame()) functionMusic.playMusic("D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Destroy.wav", true);
                            bool = false;
                        }
                    }
                }
                if (music.getGame() && bool){
                    FunctionMusic functionMusic = new FunctionMusic();
                    functionMusic.playMusic("D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Hit.wav", true);
                }
                System.out.println(Effect.red + "Hit the opponent's ship!" + Effect.blue);
                if (oppositePlayer.remainNumberShips()>0){
                    System.out.println(Effect.purple + "Please continue shooting!" + Effect.blue);
                }
            } else {
                board[shot.getRow()][shot.getColumn()] = 'o';
                oppositeBoard[shot.getRow()][shot.getColumn()] = 'o';
                swap = 3-swap;
                if (music.getGame()){
                    FunctionMusic functionMusic = new FunctionMusic();
                    if (music.getGame()) functionMusic.playMusic("D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Miss.wav", true);
                }
                System.out.println(Effect.red + "Missed the opponent's ship!" + Effect.blue);
            }
            player.setOppositeBoard(board);
            oppositePlayer.setBoard(oppositeBoard);
            player.setNumberShot(player.getNumberShot() + 1);
        }
        Effect.EnterToContinue(scanner);
        return swap;
    }
}
