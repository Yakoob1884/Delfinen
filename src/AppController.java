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

    public List<Swimmer> viewAllSwimmers(){

        List<Swimmer> listOfAllSwimmers = listOfSwimmers.getListOfAllSwimmers();

        return listOfAllSwimmers;
    }

    public List<Swimmer> viewRestanceSwimmers() {

        List<Swimmer> listSwimmersIsPaidFalse = listOfSwimmers.getSwimmersIsPaidFalseList();

        Collections.sort(listSwimmersIsPaidFalse, new ComparatorIsPaid());

        return listSwimmersIsPaidFalse;

    }

    public List<Swimmer> viewCompetitionSwimmers() {

        List<Swimmer> compSwimmersList = listOfSwimmers.getCompSwimmersList();
        Collections.sort(compSwimmersList);
        return compSwimmersList;

    }

    public double calculateFee(Swimmer swimmer) {
        if (swimmer.getIsActive() == false) return PASSIVE_PRICE;
        if (swimmer.calculateAge() < SENIOR_START_AGE) return JUNIOR_PRICE;
        if (swimmer.calculateAge() >= EXTRA_DISCOUNT_START_AGE) return SENIOR_PRICE * EXTRA_DISCOUNT_PERCENTAGE;

        return SENIOR_PRICE;
    }

    public List<Swimmer> getSwimmersMaxAge(List<Swimmer> startList, int maxAge) {
        List<Swimmer> filteredList = new ArrayList<>();

        for (Swimmer swimmer : startList) {
            if (swimmer.calculateAge() <= maxAge) {
                filteredList.add(swimmer);
            }
        }

        return filteredList;
    }

    public List<Swimmer> getSwimmersMinAge(List<Swimmer> startList, int minAge) {
        List<Swimmer> filteredList = new ArrayList<>();

        for (Swimmer swimmer : startList) {
            if (swimmer.calculateAge() >= minAge) {
                filteredList.add(swimmer);
            }
        }

        return filteredList;
    }

    public List<Swimmer> getSwimmersHavingResults(List<Swimmer> startList, SwimmingDiscipline discipline) {
        List<Swimmer> filteredList = new ArrayList<>();

        for (Swimmer swimmer : startList) {
            if (swimmer instanceof CompSwimmer) {
                ((CompSwimmer) swimmer).getTrainingTime();

            }
        }


    }




    public double calculateEstimatedRevenue() {
        double estimatedRevenue = 0.00;
        List<Swimmer> listOfAllSwimmers = viewAllSwimmers();
        for (Swimmer swimmer : listOfAllSwimmers) {
            estimatedRevenue += calculateFee(swimmer);
        }
        return estimatedRevenue;
    }

}
