public class AppController {

    private final int SENIOR_START_AGE = 18;
    private final double JUNIOR_PRICE = 1000;
    private final double SENIOR_PRICE = 1600;
    private final double PASSIVE_PRICE = 500;
    private final int EXTRA_DISCOUNT_START_AGE = 61;
    private final double EXTRA_DISCOUNT_PERCENTAGE = 0.25;


    ListOfSwimmers listOfSwimmers;

    public AppController(String fileName) {
        this.listOfSwimmers = new ListOfSwimmers;

    }

    public void printRestanceSwimmers() {
        // call method i Collection class som sorterer efter getter til isPaid eller lign

        listOfSwimmers.sortIsPaid();


        // lav code som udskriver listen indtil den foerste som er paid

        int index = 0;
        while (true) {
            if (listOfSwimmers.get(index).getIsPaid()) {
                break;
            } else {
                System.out.println(listOfSwimmers.get(index));
            }
        }

    }

}
