import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.Clip;

public class Effect {
    public static final String backgoundRed = "\u001B[41m";
    public static final String backgoundGreen = "\u001B[42m";
    public static final String backgoundYellow = "\u001B[43m";
    public static final String backgoundBlue = "\u001B[44m";
    public static final String backgoundPurple = "\u001B[45m";
    public static final String backgoundCyan = "\u001B[46m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String reset = "\u001B[0m";
    public static Clip clip;
    public static int enterChoice(int maxChoice, Scanner scanner) {
        int choice = 3;
        do {
            System.out.print(Effect.green + "Enter function: ");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice <= 0 || choice > maxChoice) {
                System.out.println("Enter invalid function! Please enter again!");
            }
            System.out.println(Effect.blue);
        } while (choice <= 0 || choice > maxChoice);
        return choice;
    }

    public static void EnterToContinue(Scanner scanner) {
        while (true) {
            System.out.print("Press Enter to continue...");
            String enter = scanner.nextLine();
            if (enter.isEmpty()) {
                return;
            }
        }
    }

    public static void loading(String input) {
        System.out.println(Effect.red);
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Effect.blue);
    }
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
    public static void image(Scanner scanner){
       System.out.println(Effect.cyan +
               "cccccccccccccccccccccc:;,,,'........................','',,,,;,,,,,,;;,,::ccccccccccccccc:::::::ccc:::::::::::::::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,,,,,,,,,,,,,,,,,,,''''''''''''''''''''...........\r\n" + //
               "cccccccccccccccccccccc:;,,,,............................'',;,''''.',;,,:cccccccccccccc:;,,,;;cxkkkkdolc:::::::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,;;,,,,,,,,,,,,,,,,,,,''''''''''''''''''''''..........\r\n" + //
               "ccccccccccccccccccccccc;,,,'..............'''........'','','.......,;,,:ccccccccccc:;,'''',::lkO000K00kdlc:::::::;;:;;;;;;;;;;;;;;;;;;;;;;;;;;;,;;,,,,,,,,,,,,,,,,,,,,,,''''''''''''''''''''..........\r\n" + //
               "cccccccccccccccccccccc:;,;;'.............''''........'''''.........,;,,;ccccccccc:;,''''''''',cdxkOOOO0K0Oxoc::::::::;;;;;;;;;;;;;;;;;;;;;;;;;,;;;,,,,,,,,,,,,,,,,,,,,''''''''''''''''''''............\r\n" + //
               "cccccccccccccccccccccc:;;;;'..............'''......................';,,:ccccccc:;,'''''''''''':odxOOOOO0KXNX0koc::::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,,,,,,,,,,,,,,,,,,,,,,'''''''''''''''''..'.........\r\n" + //
               "ccccccccccccccccccccccc;,,,'.......................................';,,:ccccc:;,''''''''''',;;lk00KKKKXXXNNWWWN0oc:::::;;;;;;;;;;;;;;;;;;;;;,;;;;;,,,,,,,,,,,,,,,,,,,,,,'''''''''''''''''''...........\r\n" + //
               "ccccccccccccccccccccccc;;;;'.......................................,;,,:cccc;,'.''''''''''',;;:x0KNNXNNWWWWWWMMMW0o:::::;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,,,,,,,,,,,,,,,,,,,''''''''''''''''''............\r\n" + //
               "ccccccccccccccccccccccc;;;;'...........................''..........,;,,:cc:;'.''''''''''''',:c;lxOXNXXXXNNWWWWMMMMNOl:::::;;;;;;;;;;;;;;;;;;;;;;;;;,,,,,,,,''''''''''''''.............................\r\n" + //
               "cccccccccccccccccccccc:;;;;'...........................'...........';,,:c:,'..''''''''''''',:lc:loxOOOOOO0KXNWMMMMMWKo:::::;;;;;;;;;;;;;;;;;;;;;;;;,'.................................................\r\n" + //
               "clccccccccccccccccccccc;;;;'.......'........'''....................';;,:;,'.'''''.''''''',,;cdo::cdk00KXXXNNWWWWMMMMMXxc::;;;;;;;;;;;;;;;;;;;;;;;;;,'..'..............................................\r\n" + //
               "ccccccccccccccccccccccc;;:;'................'''....................,;;,,'....''..''''''',,;:cc:;:lkKXNNWWWWWNNNWWMMMMMNkc::;;;;;;;;;;;;;;;;;;;;,;;;,'..'.................                      .......\r\n" + //
               "ccccccccccccccccccccccc;;:;,............''..''.....................,;;''..''.''..'''''''',,,;ooccdXWWWNNX0OOOO0KNWMMMMMWOl::;;;;;;;;;;;;;;;;;;;;;;;,'..'.................  ..                  .......\r\n" + //
               "cccccccccccccccccccccc:;:;;,''''',,,,,,,,;;;;,,,,,,,,,,,,,,,,,,;;;;;;,''''''.......'''''''',cddc;cdolcccccldO0XXNWWMMMMMW0l::;;;;;;;;;;;;;;;;;;;;,,,'..'.................  ...                  ......\r\n" + //
               "cccccccccccccccccccccc:;:;,,,,,,,,;,,,,,;,,;;;;;;;;;;;;;;;;;;;;;;;;;,'''..................'',,,'',;;:ldkOKXXNNWWWWWWMMMMMWOc:::;;;;;;;;;;;;;;;;;;,,,'.....    ........                          ......\r\n" + //
               "ccccccccccccccccccccccc:;;;;;;;;;;;;:::::::::::::::::::::cccccccccc;''....................''',,,,;ccokKXXKKKXWWWWWWWWMMMMMNx::::;;;;;;;;;;;;;;;;;;,,'...... ...........                         ......\r\n" + //
               "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccclllllc;'.........'''';col;,;lddl;;:;;;;;;;,;;,;ckXNXK00XWWWWMMMXd::::;;;;;;;;;;;;;;;;;,,'...'..............                          .....\r\n" + //
               "::::::::::::::::::::::::::::::::::::::::::::::ccc::ccc:cccccccllc:'..'',;:::::ccd0NWWOdkXWMXkO0dcc;;lo:;:;:cdxxolc:cxkkKWWWMXxc:::::;;;;;;;;;;;;;,,,'...'.......'......                     .    .....\r\n" + //
               ",,,,,,,,,,,,,,,,,;;,;;,;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::ccc:'.',;cokOkdollokNMMWKkONMMWXXWX0xodOK0Oklclc:;::::;,''cOXXNWWKOO0K0kl:;;;;;;,,,,,,,'...'............                           ......\r\n" + //
               "''''''''''''''''''''''''''''''',,,,,'',,,,,,,,,,,,,,,,,,,,,,;;::,',;cdOXNKkdlc:cxNMMW0kOXWMWKKNWWKxkKOxdolllc;;cllll:;;l0WMMMMMMMMMMMNxc;;;;;,,,,,,,'......  ........                           ......\r\n" + //
               "..'''''''''''''''''''''''''''''''''''''''''''''''''''''''',,,,,,,;lx0NWWXOxolcclOWMMNkxOXWMN0OXWMWOol::clodoc;:loxxdl:co0WMMMMMMMMMMMMWOc,,,,,,,,,,,'....... .........                      ..........\r\n" + //
               "............'''..'''..'''''''''''''''''''''''''''''''''''',,,,;:okKNWMMN0kkxddx0NWWWKkOXWMMXO0NWMMXl,;loooddoc;cldxxdoloONMMWNNNWWWNWMMW0l,'...'',,''......                                 ......... \r\n" + //
               ".............'...........'..''''''''''''''''''''''''''''''',,:lkKNWMMMMWXKKKKXNWWWNX0KNWMWXOOXWMWMXxlooolclodo:;coxkkoclONMMN0kkkOOOXWMMMNk;...............                                  ......   \r\n" + //
               ".............................'.....''''''''''''...........';coOXWMMMMMMMWWWWWWWMMMWXNWWXkddkXWMWXK0KK0K0kxxdddl,,cxKXKOk0NWMMNOddxxkOXWMMMWKl.................................. ..........       .... \r\n" + //
               ".......................................''''''''.       ..';cdOXWMMMMMMMMMMMMMWWMMMMWWKxl:;l0WMXd::lkKNWWWWNX0kc'',l0WWWWNNXKNWKdlodxkONWMMMMNx,............................................      .... \r\n" + //
               "........................................''''''..    ..,;;:cokXWMMMMMMMMMMMMMMMMMMMW0oc;;;;:lxxc''';ldOXWWMMMWXx:'',:odk0NWXxdkxlllodxk0NMMMMMWk:''''..................................................\r\n" + //
               ".........................................'.........',;;::ldOXWMMMMMMMMMMMMMMMMMMMMWk:::c::;,'...'';:cldOKXNNWWNk;'',;;:coxxolllllllodxOXWMMMMMWKd:,,,,,,''''''''''''''''..............................\r\n" + //
               ".............................................  ..',;;:::lxKNMMMMMMMMMMMMMMMMMMMMMMMKocclc:,'...'',;::::cokOOKNNOc,,;:ccccllllloollloddx0NMMMMMMMWKd;...','''''..''''............................... ..\r\n" + //
               "..........................................      .',;;:cox0XWMMMMMMMMMMMMMMMMMMMMMMMWXOdolc;'',,,,;:lllcccodddkOd:;:ccllodxxxxddddooodxk0NMMMMMMMMMWKc...,,''''..'''''............................     \r\n" + //
               ".................................                ..';o0XXNWMMMMMMMMMMMMMMMMMMMMMMMMMMMWX0kdc;;,,;;:ccccloxkO0KK0OOOkdodx0XNNXX00OkdddxOKWMMMMMMMMMWXd,..;c;,'''''''''.............................    \r\n" + //
               "...............................                   ...ckKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWNKOdc;;;;:cokKNWWMWWMMMMWKkkk0NMMMMWWWNKOkOXWMMMMMMMMMWKd:'.'cll:;'.....''............................     \r\n" + //
               "............................                       ....,lx0XWWWWNXXXNWWWWWNXKkdoddxkkOOO0XWWMMWX0xlclx0NWMMMMWWNNNNWN0kO0KNWWMMMMMMWNNWMMMMMWNNX0xl;'.';cllll:'.   ...............................    \r\n" + //
               ".........................                         ........';cllcckXKOkxdolc:;,'......',;cokKWMMMMNxdOXNXK0Okdc;,,;;:cc::cldkKWWMMMMWWNNNXK00Okdlc;'..,;:cclllll:,.   .',,'....                        \r\n" + //
               ".......................          ...            ..............',lONWXOoc;;,,,,'.........',;cdONWMWNKKOxolc:,.   .........'',;ccloddoloooolllc:;'...',:::ccccllllc;.  ..,,,,,'..                       \r\n" + //
               "......................            ...  .........................'d0XNNKklc:;,,,'..........',;:oOXNWOollll:,.   ........''''..     ................',;;::ccccccccc:'. ..'',;;;,'..                     \r\n" + //
               "....................               .......................       .:x0XNNKxlc:;;,...........',;:loxkoclllc,.    .......',,,.         .        ...'',,,;;:::ccc::::;'. ....',;,,,,'..                   \r\n" + //
               ".................                     .. ................          .;d0NNNKxoc:;'............,;:::cc:::,..    .....',,,,'.                 ....'''.'',;;;::::::;;'.  ....',;;,,,,,'..                 \r\n" + //
               "..............                         ..................            .;xKNNN0dlc;'.............''''.....     ....',,,,,.                    .........',,;;;;;;;,..   .',,;;;;;,,,,,,,..               \r\n" + //
               "............                            .................              .;xKXXKkoc;'...................      ....',,;,'.                         .......',,,,,'..   ..',,,;,,,,;,,,,,,,'...            \r\n" + //
               ".........                                 ...............                .:xKXX0d:;,'................      ...',,;;'.                                .........   ..''',,,,,,,,,,,,,'',,,'.........    \r\n" + //
               "......                                     ...............                 .;dO0Oxc;''................    ...',,,'.                                              .',,'''',,,,;,,,,,,,',,,'...         \r\n" + //
               "....                                        ..............                   .,lxkxdc;'............  .  ...'',;,.                                             ....',,,,',,,,,,,,,,,,,,,,,;,'..        \r\n" + //
               ".                                            .............                     .'cdxdoc;'..........  ....',,,,,..                                          ........'',,,,,;;,,,,,,,,,,,;;;;,,'..      \r\n" + //
               "                                           .......... ...    ..                  ..;loolc:,'....  .....',,,,'','...                                     ..............''',,,,,,,,,,,,,,;;;;,,,,'..    \r\n" + //
               "                                        ...........   ...   ...                    ..,:olc::;'......',,,,,'',,,'....                                    .................'',,,,,,,',,,,;;;,,,,;;,..   \r\n" + //
               "                                        ..........    ...   ...                   ..  .co:::::,'''''',;;,,,,,,,'....                                      ................''',,,,,,',,,,,,,,,,,;;,'.. \r\n" + //
               "                                        ..........    ...   ...       ..          ..  .:d:';::;;;;,''';:,,,,;;,,'....                                       ................'',,,','',',,,,,,,,,;,,'..\r\n" + //
               "                                          ....  ..    ..   ....      ....         .    :x:.';::::;,'',::;,;;;;,,''...                                          ..............',,,''',,'''',,,,,,,,,,'.\r\n" + //"\r\n" + //
"" + Effect.reset);
       EnterToContinue(scanner);
    }
}
