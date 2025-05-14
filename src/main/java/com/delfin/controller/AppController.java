package delfin.controller;


import delfin.model.*;
import java.time.Duration;
import java.util.*;
import java.time.LocalDate;

public class AppController {

    private final int SENIOR_START_AGE = 18;
    private final double JUNIOR_PRICE = 1000;
    private final double SENIOR_PRICE = 1600;
    private final double PASSIVE_PRICE = 500;
    private final int EXTRA_DISCOUNT_START_AGE = 61;
    private final double EXTRA_DISCOUNT_PERCENTAGE = 0.75;

    Scanner scanner;

    ListOfSwimmers listOfSwimmers;

    public AppController(String fileName, Scanner scanner) {
        this.listOfSwimmers = new ListOfSwimmers();
        this.scanner = scanner;
    }

    public List<Swimmer> viewAllSwimmers() {

        List<Swimmer> listOfAllSwimmers = listOfSwimmers.getListOfAllSwimmers();

        return listOfAllSwimmers;
    }

    public List<Swimmer> viewRestanceSwimmers() {

        List<Swimmer> listSwimmersIsPaidFalse = listOfSwimmers.getSwimmersIsPaidFalseList();

        Collections.sort(listSwimmersIsPaidFalse);

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

    public void printAllTestToRemoveAgain() {
        listOfSwimmers.getListOfAllSwimmers().forEach(System.out::println);

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
//                if (((delfin.model.CompSwimmer) swimmer).hasResults(discipline)) {
//                    startList.add(swimmer);
//                }
            }
        }

        return filteredList;
    }


    public double calculateEstimatedRevenue() {
        double estimatedRevenue = 0.00;
        List<Swimmer> listOfAllSwimmers = viewAllSwimmers();
        for (Swimmer swimmer : listOfAllSwimmers) {
            estimatedRevenue += calculateFee(swimmer);
        }
        return estimatedRevenue;
    }

    public void top5() {

        List<CompSwimmer> activeCompSwimmers;

        activeCompSwimmers = listOfSwimmers.getActiveCompSwimmersList();

        activeCompSwimmers = extractJuniorCompSwimmers(activeCompSwimmers, SENIOR_START_AGE);

        //activeCompSwimmers = extractSeniorCompSwimmers(activeCompSwimmers, SENIOR_START_AGE);


        activeCompSwimmers.sort(Comparator.comparing(CompSwimmer::getFastestTrainingTimeBreastStroke));

        CompSwimmer tempCompSwimmer;

        if (!activeCompSwimmers.isEmpty()) {
            System.out.println("Top 5 Træningstider i Brystsvømning for Junior");
            System.out.println("Efternavn             Fornavn               Fødselsdato      Træningsdato            Disciplin               Svømmmetid\n");
        }

        for (int i = 0; i < 5; i++) {
            tempCompSwimmer = activeCompSwimmers.get(i);
            System.out.printf("%-20s  %-20s  %-15s  %-20s %15s  %15s\n",tempCompSwimmer.getLastName(), tempCompSwimmer.getFirstName(), tempCompSwimmer.getBirthday(), tempCompSwimmer.getFastestTrainingBreastStroke().getDate(), tempCompSwimmer.getFastestTrainingBreastStroke().getDiscipline(), formatDuration(tempCompSwimmer.getFastestTrainingBreastStroke().getTimeRegister()));
        }

        System.out.println();
        System.out.println();

        activeCompSwimmers = listOfSwimmers.getActiveCompSwimmersList();

        //activeCompSwimmers = extractJuniorCompSwimmers(activeCompSwimmers, SENIOR_START_AGE);

        activeCompSwimmers = extractSeniorCompSwimmers(activeCompSwimmers, SENIOR_START_AGE);


        activeCompSwimmers.sort(Comparator.comparing(CompSwimmer::getFastestCompetitionTimeBreastStroke));

        if (!activeCompSwimmers.isEmpty()) {
            System.out.println("Top 5 Stævnetider i Brystsvømning for Senior");
            System.out.println("Efternavn             Fornavn               Fødselsdato      Stævnedato           Stævne                       Disciplin              Svømmmetid\n");
        }


        for (int i = 0; i < 5; i++) {
            tempCompSwimmer = activeCompSwimmers.get(i);
            System.out.printf("%-20s  %-20s  %-15s  %-20s %-25s %15s %15s\n",tempCompSwimmer.getLastName(), tempCompSwimmer.getFirstName(), tempCompSwimmer.getBirthday(), tempCompSwimmer.getFastestCompetitionBreastStroke().getDate(), tempCompSwimmer.getFastestCompetitionBreastStroke().getCompetitionName(), tempCompSwimmer.getFastestCompetitionBreastStroke().getDiscipline(), formatDuration(tempCompSwimmer.getFastestCompetitionBreastStroke().getTimeRegister()));
        }

        System.out.println();
        System.out.println();

    }


    public List<CompSwimmer> extractJuniorCompSwimmers(List<CompSwimmer> compSwimmerList, int ageForSenior) {
        List<CompSwimmer> juniorCompSwimmers = new ArrayList<>();

        for (CompSwimmer compSwimmer : compSwimmerList) {
            if (compSwimmer.calculateAge() < ageForSenior) {
                juniorCompSwimmers.add(compSwimmer);
            }
        }
        return juniorCompSwimmers;
    }

    public List<CompSwimmer> extractSeniorCompSwimmers(List<CompSwimmer> compSwimmerList, int ageForSenior) {
        List<CompSwimmer> seniorCompSwimmers = new ArrayList<>();

        for (CompSwimmer compSwimmer : compSwimmerList) {
            if (compSwimmer.calculateAge() >= ageForSenior) {
                seniorCompSwimmers.add(compSwimmer);
            }
        }
        return seniorCompSwimmers;
    }

    public String formatDuration(Duration duration) {
        Duration secondsIsolated = duration.minusMinutes(duration.toMinutes());

        return String.format("%02d:%02d", duration.toMinutes(), secondsIsolated.toSeconds());

    }

    public void addCompSwimmerToList(String firstName, String lastName, LocalDate birthday, EnumSet<SwimmingDiscipline> disciplines){
        Swimmer swimmer = new CompSwimmer(firstName, lastName, birthday, disciplines);
        listOfSwimmers.addSwimmer(swimmer);
    }

    public void addNonCompSwimmerToList(String firstName, String lastName, LocalDate birthday){
        Swimmer swimmer = new NonCompSwimmer(firstName, lastName, birthday);
        listOfSwimmers.addSwimmer(swimmer);

    }

    public Swimmer getSwimmerById(Scanner scanner) {
        System.out.println(listOfSwimmers.getListOfAllSwimmers());

        System.out.println("Vælg et medlem ud fra det given ID nummer. Tast 0 for at gå tilbage");
        int input = scanner.nextInt();

        Swimmer swimmerById = listOfSwimmers.getSwimmerByIndex(input - 1);

        return swimmerById;

    }
}
