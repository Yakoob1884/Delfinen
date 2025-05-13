package delfin.main;

import delfin.controller.AppController;

public class Main {

    public static void main(String[] args) {

        AppController controller = new AppController("collectionBackup.ser");
        UI uiConsoleMenu = new UI(controller);

        uiConsoleMenu.menuOptions();

    }

}
