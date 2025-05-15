package delfin.controller;


import delfin.model.*;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;

public class AppController {

    private final int SENIOR_START_AGE = 18;
    private final double JUNIOR_PRICE = 1000;
    private final double SENIOR_PRICE = 1600;
    private final double PASSIVE_PRICE = 500;
    private final int EXTRA_DISCOUNT_START_AGE = 61;
    private final double EXTRA_DISCOUNT_PERCENTAGE = 0.75;

    Scanner scanner;

    ListOfSwimmers listOfSwimmers;

    public AppController(String filename, Scanner scanner) throws IOException, ClassNotFoundException {
        this.listOfSwimmers = new ListOfSwimmers(filename);
        this.scanner = scanner;
    }

    public List<Swimmer> getAllSwimmers() {

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
        List<Swimmer> listOfAllSwimmers = getAllSwimmers();
        for (Swimmer swimmer : listOfAllSwimmers) {
            estimatedRevenue += calculateFee(swimmer);
        }
        return estimatedRevenue;
    }

    public void saveListOfSwimmersToFile() throws IOException {
        listOfSwimmers.saveListOfSwimmersToFile();

    }

    public Swimmer getSwimmerByIndex(int index) {
        return listOfSwimmers.getSwimmerByIndex(index);
    }

    public List<StatisticsDataTransferObject> getTopTrainingJuniorBreaststroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeBreastStroke = CompSwimmer::getFastestTrainingTimeBreastStroke;
        Function<CompSwimmer, Training> getFastestTrainingBreastStroke = CompSwimmer::getFastestTrainingBreastStroke;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestTrainingTimeBreastStroke, getFastestTrainingBreastStroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopTrainingJuniorBackstroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeBackstroke = CompSwimmer::getFastestTrainingTimeBackStroke;
        Function<CompSwimmer, Training> getFastestTrainingBackstroke = CompSwimmer::getFastestTrainingBackstroke;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestTrainingTimeBackstroke, getFastestTrainingBackstroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopTrainingJuniorFreestyle(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeFreestyle = CompSwimmer::getFastestTrainingTimeFreestyle;
        Function<CompSwimmer, Training> getFastestTrainingFreestyle = CompSwimmer::getFastestTrainingFreestyle;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestTrainingTimeFreestyle, getFastestTrainingFreestyle, ageGroup, topListLength);


    }


    public List<StatisticsDataTransferObject> getTopTrainingJuniorButterfly(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeButterfly = CompSwimmer::getFastestTrainingTimeButterfly;
        Function<CompSwimmer, Training> getFastestTrainingButterfly = CompSwimmer::getFastestTrainingButterfly;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestTrainingTimeButterfly, getFastestTrainingButterfly, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopTrainingSeniorBreaststroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeBreastStroke = CompSwimmer::getFastestTrainingTimeBreastStroke;
        Function<CompSwimmer, Training> getFastestTrainingBreastStroke = CompSwimmer::getFastestTrainingBreastStroke;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestTrainingTimeBreastStroke, getFastestTrainingBreastStroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopTrainingSeniorBackstroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeBackstroke = CompSwimmer::getFastestTrainingTimeBackStroke;
        Function<CompSwimmer, Training> getFastestTrainingBackstroke = CompSwimmer::getFastestTrainingBackstroke;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestTrainingTimeBackstroke, getFastestTrainingBackstroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopTrainingSeniorFreestyle(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeFreestyle = CompSwimmer::getFastestTrainingTimeFreestyle;
        Function<CompSwimmer, Training> getFastestTrainingFreestyle = CompSwimmer::getFastestTrainingFreestyle;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestTrainingTimeFreestyle, getFastestTrainingFreestyle, ageGroup, topListLength);


    }


    public List<StatisticsDataTransferObject> getTopTrainingSeniorButterfly(int topListLength) {

        Function<CompSwimmer, Duration> getFastestTrainingTimeButterfly = CompSwimmer::getFastestTrainingTimeButterfly;
        Function<CompSwimmer, Training> getFastestTrainingButterfly = CompSwimmer::getFastestTrainingButterfly;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestTrainingTimeButterfly, getFastestTrainingButterfly, ageGroup, topListLength);


    }








    public List<StatisticsDataTransferObject> getTopCompetitionJuniorBreaststroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeBreastStroke = CompSwimmer::getFastestCompetitionTimeBreastStroke;
        Function<CompSwimmer, Training> getFastestCompetitionBreastStroke = CompSwimmer::getFastestCompetitionBreastStroke;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestCompetitionTimeBreastStroke, getFastestCompetitionBreastStroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionJuniorBackstroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeBackstroke = CompSwimmer::getFastestCompetitionTimeBackstroke;
        Function<CompSwimmer, Training> getFastestCompetitionBackstroke = CompSwimmer::getFastestCompetitionBackstroke;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestCompetitionTimeBackstroke, getFastestCompetitionBackstroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionJuniorFreestyle(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeFreestyle = CompSwimmer::getFastestCompetitionTimeFreestyle;
        Function<CompSwimmer, Training> getFastestCompetitionFreestyle = CompSwimmer::getFastestCompetitionFreestyle;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestCompetitionTimeFreestyle, getFastestCompetitionFreestyle, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionJuniorButterfly(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeButterfly = CompSwimmer::getFastestCompetitionTimeButterfly;
        Function<CompSwimmer, Training> getFastestCompetitionButterfly = CompSwimmer::getFastestCompetitionButterfly;
        Function<Integer, Boolean> ageGroup = this::isJunior;

        return statisticsDtoMaker(getFastestCompetitionTimeButterfly, getFastestCompetitionButterfly, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionSeniorBreaststroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeBreastStroke = CompSwimmer::getFastestCompetitionTimeBreastStroke;
        Function<CompSwimmer, Training> getFastestCompetitionBreastStroke = CompSwimmer::getFastestCompetitionBreastStroke;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestCompetitionTimeBreastStroke, getFastestCompetitionBreastStroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionSeniorBackstroke(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeBackstroke = CompSwimmer::getFastestCompetitionTimeBackstroke;
        Function<CompSwimmer, Training> getFastestCompetitionBackstroke = CompSwimmer::getFastestCompetitionBackstroke;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestCompetitionTimeBackstroke, getFastestCompetitionBackstroke, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionSeniorFreestyle(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeFreestyle = CompSwimmer::getFastestCompetitionTimeFreestyle;
        Function<CompSwimmer, Training> getFastestCompetitionFreestyle = CompSwimmer::getFastestCompetitionFreestyle;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestCompetitionTimeFreestyle, getFastestCompetitionFreestyle, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> getTopCompetitionSeniorButterfly(int topListLength) {

        Function<CompSwimmer, Duration> getFastestCompetitionTimeButterfly = CompSwimmer::getFastestCompetitionTimeButterfly;
        Function<CompSwimmer, Training> getFastestCompetitionButterfly = CompSwimmer::getFastestCompetitionButterfly;
        Function<Integer, Boolean> ageGroup = this::isSenior;

        return statisticsDtoMaker(getFastestCompetitionTimeButterfly, getFastestCompetitionButterfly, ageGroup, topListLength);


    }

    public List<StatisticsDataTransferObject> statisticsDtoMaker(Function<CompSwimmer, Duration> getter, Function<CompSwimmer, Training> getTraining, Function<Integer, Boolean> ageGroup, int topListLength)  {

        List<CompSwimmer> activeCompSwimmers = listOfSwimmers.getListOfAllSwimmers()
                .stream()
                .filter(CompSwimmer.class::isInstance)
                .map(CompSwimmer.class::cast)
                .filter(swimmer -> ageGroup.apply(swimmer.calculateAge()))
                .filter(Swimmer::getIsActive)
                .sorted(Comparator.comparing(getter))
                .limit(topListLength)
                .toList();



        List<StatisticsDataTransferObject> dtoList = new ArrayList<>();

        CompSwimmer tempCompSwimmer;

        Training tempTraining;
        for (CompSwimmer activeCompSwimmer : activeCompSwimmers) {

            tempCompSwimmer = activeCompSwimmer;
            tempTraining = getTraining.apply(tempCompSwimmer);

            if (tempCompSwimmer != null && tempTraining != null) {
                dtoList.add(new StatisticsDataTransferObject(tempCompSwimmer, tempTraining));
            }

        }
        return dtoList;
    }


    public Boolean isSenior(Integer age) {
        return (age >= SENIOR_START_AGE);
    }

    public Boolean isJunior(Integer age) {
        return (age < SENIOR_START_AGE);
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

        System.out.println("Vælg et medlem ud fra det given ID nummer. Tast 0 for at gå tilbage.");
        int input = scanner.nextInt();

        Swimmer swimmerById = listOfSwimmers.getSwimmerByIndex(input - 1);

        return swimmerById;

    }

    public List<Swimmer> returnGetSwimmersIsPaidFalseList (){
        return listOfSwimmers.getSwimmersIsPaidFalseList();
    }

    public void swimmerHasPaid() {
       System.out.println("Vælg et medlem ud fra det given ID nummer. Tast 0 for at gå tilbage.");
        int input = scanner.nextInt();

        Swimmer swimmerById = listOfSwimmers.getSwimmersIsPaidFalseList().get(input - 1);

        swimmerById.setIsPaid(true);

        System.out.println("Betaling registreret for: " + swimmerById.getFirstName() + " " + swimmerById.getLastName());
    }
}
