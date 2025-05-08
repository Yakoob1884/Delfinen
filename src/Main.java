public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("collectionBackup.ser");
        UI uiConsoleMenu = new UI(controller);

        uiConsoleMenu.run();






    }

    //Denne method skal vaere i Controller

    

    //Denne method skal vaere i ListOfSwimmers
    public void sortIsPaid() {
        Collections.sort(listOfSwimmers, new ComparatorIsPaid());

    }

}
