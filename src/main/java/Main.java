/*
 * Main.java 25/08/2017
 *
 * Created by Bondarenko Oleh
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String menuCommand;
        Scanner in = new Scanner(System.in);

        Buyer[] buyers = new Buyer[15];
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer();
        }

        ConsoleMenu consoleMenu = new ConsoleMenu();

        do {
            System.out.print("Enter command: ");
            menuCommand = in.nextLine();
        }  while (consoleMenu.processCommand(buyers, menuCommand));
    }
}