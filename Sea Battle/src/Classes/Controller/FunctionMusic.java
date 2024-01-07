import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;

public class FunctionMusic {
    public void playMusic(String file, boolean bool) {
        if (!bool) {
            Effect.clip.stop();
            return;
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(file));
            Effect.clip = AudioSystem.getClip();
            Effect.clip.open(audioStream);
            Effect.clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    private void printMusic() {
        System.out.println(Effect.yellow + "                   __  __ _   _ ____ ___ ____ \r\n" + //
                "                  |  \\/  | | | / ___|_ _/ ___|\r\n" + //
                "                  | |\\/| | | | \\___ \\| | |    \r\n" + //
                "                  | |  | | |_| |___) | | |___ \r\n" + //
                "                  |_|  |_|\\___/|____/___\\____|\n" + Effect.blue);
    }

    private void onOff() {
        Effect.clearScreen();
        printMusic();
        System.out.println("1. On");
        System.out.println("2. Off");
        System.out.println("3. Exit");
    }

    public void menuMusic(Scanner scanner, Player player, Player oppositePlayer, Music music) {
        onOff();
        int choice = Effect.enterChoice(3, scanner);
        String sound = "ProPTIT";
        switch (choice) {
            case 1:
                System.out.println(Effect.red + "Music is turned on!" + Effect.blue);
                music.setGame(true);
                sound = "D:\\Programming\\PushGit\\seabattle\\Phạm Quang Minh\\Product\\Audio\\Beach.wav";
                break;
            case 2:
                System.out.println(Effect.red + "Music is turned off!" + Effect.blue);
                music.setGame(false);
                break;
            case 3:
                MenuGame menuGame = new MenuGame();
                menuGame.menu(scanner, player, oppositePlayer, music);
                return;
        }
        if (music.getGame()) {
            playMusic(sound, true);
            Effect.clip.loop(Effect.clip.LOOP_CONTINUOUSLY);
        }
        else{
            playMusic(sound, false);
        }
        Effect.EnterToContinue(scanner);
        MenuGame menuGame = new MenuGame();
        menuGame.menu(scanner, player, oppositePlayer, music);
    }
}
