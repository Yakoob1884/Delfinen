package delfin.main;

import delfin.controller.AppController;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            AppController controller = new AppController("listCollectionBackup.ser", scanner);
            UI uiConsoleMenu = new UI(controller, scanner);

            uiConsoleMenu.menuOptions();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR READING OR WRITING SWIMMERS FROM FILE");
            e.printStackTrace();
        }

    }

}
