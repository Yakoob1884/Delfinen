package delfin.main;



import delfin.controller.AppController;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        AppController controller = new AppController("collectionBackup.ser", scanner);
        UI uiConsoleMenu = new UI(controller, scanner);

        uiConsoleMenu.menuOptions();

    }

}
