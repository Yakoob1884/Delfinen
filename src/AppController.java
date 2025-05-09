import java.util.*;

public class AppController {

    private final int SENIOR_START_AGE = 18;
    private final double JUNIOR_PRICE = 1000;
    private final double SENIOR_PRICE = 1600;
    private final double PASSIVE_PRICE = 500;
    private final int EXTRA_DISCOUNT_START_AGE = 61;
    private final double EXTRA_DISCOUNT_PERCENTAGE = 0.25;


    ListOfSwimmers listOfSwimmers;

    public AppController(String fileName) {
        this.listOfSwimmers = new ListOfSwimmers();

    }

    public List<Swimmer> viewRestanceSwimmers() {

        List<Swimmer> listSwimmersIsPaidFalse = listOfSwimmers.getSwimmersIsPaidFalse();

        Collections.sort(listSwimmersIsPaidFalse, new ComparatorIsPaid());

        return listSwimmersIsPaidFalse;

    }

}
