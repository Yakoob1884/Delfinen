package delfin.main;

import delfin.controller.AppController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            AppController controller = new AppController("listCollectionBackup.ser", scanner);
            UI uiConsoleMenu = new UI(controller, scanner);

            uiConsoleMenu.menuOptions();

        } catch (IOException e) {
            System.err.println("FEJL UNDER LÆSNING ELLER SKRIVNING TIL FIL");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.err.println("EN KLASSE ER INKOMPATIBEL OG KAN IKKE MODTAGE DATA FRA FILEN");
            e.printStackTrace();
        }



    }

}
