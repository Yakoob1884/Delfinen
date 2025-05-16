package delfin.main;

import java.io.IOException;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
//package delfin.main;

import delfin.controller.AppController;
import delfin.model.*;
import delfin.utils.SwimPassiveException;

import java.time.LocalDate;
import java.time.Duration;


public class UI {

    private Scanner scanner;
    private AppController controller;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final int TOP_LIST_LENGTH = 5;


    public UI(AppController controller, Scanner scanner) {
        this.scanner = scanner;
        this.controller = controller;

    }


    public void menuOptions() throws IOException {

        boolean run = true;

        while (run) {


            System.out.println("Svømmeklubben Delfinen");
            System.out.println("Menu: ");
            System.out.println("1. Opret svømmemedlem");
            System.out.println("2. Se registrerede svømmemedlemmer");
            System.out.println("3. Rediger svømmemedlemmer");
            System.out.println("4. Vis statistik på svømmere");
            System.out.println("5. Registrer tider");
            System.out.println("6. Se medlemmer med restance");
            System.out.println("7. Registrer betaling");
            System.out.println("8. Se årets forventede omsætning");
            System.out.println("0. For at afslutte programmet");


            int choice = getValidInt(0, 8, false);

            switch (choice) {

                case 1:
                    createSwimmer();
                    break;
                case 2:
                    viewSwimmer();
                    break;
                case 3:
                    subMenuChangeSwimmer();
                    break;
                case 4:
                    subMenuStatistics();
                    break;
                case 5:
                    addResult();
                    break;
                case 6:
                    System.out.println(printRestanceSwimmers(controller.viewRestanceSwimmers()));
                    break;
                case 7:
                    List<Swimmer> swimmersIsPaidFalseList = controller.returnGetSwimmersIsPaidFalseList();
                    System.out.println(printListNameBirthday(swimmersIsPaidFalseList));
                    System.out.println("Vælg et medlem ud fra det givne ID nummer. Tast 0 for at gå tilbage.");
                    int userInput = getValidInt(0, swimmersIsPaidFalseList.size(), false);

                    if (userInput != 0) {
                        controller.swimmerHasPaid(userInput);
                    }
                    break;
                case 8:
                    System.out.println("Årets forventede totale omsætning er: ");
                    System.out.println(controller.calculateEstimatedRevenue());
                    break;
                case 0:
                    controller.saveListOfSwimmersToFile();

                    System.out.println("Programmet afslutter og gemmer data...");
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 0-8");


            }
        }

    }

    public void createSwimmer() {

        boolean run = true;

        while (run) {
            System.out.println("Opret svømmemedlem: ");
            System.out.println("Vil du oprette en motionist eller en konkurrencesvømmer? ");
            System.out.println("1. for motionist");
            System.out.println("2. for konkurrencesvømmer");
            System.out.println("0. for at gå tilbage");

            int choice = getValidInt(0, 2, false);

            switch (choice) {
                case 1:
                    createNonCompSwimmer();
                    break;
                case 2:
                    createCompSwimmer();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 0-2");


            }
        }
    }

    public void viewSwimmer() {

        boolean run = true;

        while (run) {
            System.out.println("Se svømmemedlemmer: ");
            System.out.println("Vil se alle svømmere eller kun konkurrencesvømmere? ");
            System.out.println("1. for alle");
            System.out.println("2. for konkurrencesvømmere");
            System.out.println("3. for motionssvømmere");
            System.out.println("4. for alle passive medlemmer");
            System.out.println("5. for alle aktive medlemmer");
            System.out.println("0. for at gå tilbage");

            int choice = getValidInt(0, 5, false);

            switch (choice) {
                case 1:
                    System.out.println(printListNameBirthday(controller.getAllSwimmers()));
                    break;
                case 2:
                    System.out.println(printListNameBirthday(controller.viewCompetitionSwimmers()));

                    List<Swimmer> competitionSwimmerList = controller.viewCompetitionSwimmers();
                    System.out.println("Vælg en svømmer du gerne vil se alle informationer for, ved at indtaste deres ID nummer\nEller 0 for tilbage til menu");
                    int temp = getValidInt(0, competitionSwimmerList.size(), false);
                    if (temp == 0) {
                        break;
                    } else {
                        System.out.println(printSingleSwimmerNameBirthday(competitionSwimmerList.get(temp - 1)));

                    }
                    break;
                case 3:
                    System.out.println(printListNameBirthday(controller.getNonCompSwimmersList()));

                    break;
                case 4:
                    System.out.println(printListNameBirthday(controller.getPassiveSwimmersList()));
                    break;
                case 5:
                    System.out.println(printListNameBirthday(controller.getActiveSwimmersList()));
                    break;


                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 1-5.");


            }
        }
    }

    public void addResult() {

        boolean run = true;

        while (run) {
            System.out.println("Registrer tider:");
            System.out.println("Vil du registrer tidsresultat for en træning eller en konkurrence?");
            System.out.println("1. for træningsresultat");
            System.out.println("2. for konkurrenceresultat");
            System.out.println("0. for at gå tilbage");

            int choice = getValidInt(0, 2, false);

            switch (choice) {
                case 1:

                    System.out.print(printListNameBirthday(controller.viewCompetitionSwimmers()));
                    List<Swimmer> competitionSwimmerList = controller.viewCompetitionSwimmers();

                    System.out.println("Vælg ID på svømmeren som skal have tilføjet resultat");

                    try {
                        addTrainingResult((CompSwimmer) competitionSwimmerList.get(getValidInt(1, competitionSwimmerList.size(), false) - 1));
                    } catch (SwimPassiveException e) {
                        System.err.println("Svømmer er \"passiv\". Du kan ikke registrere ny tid.\nPrøv igen.");
                        break;
                    }
                    break;
                case 2:
                    System.out.print(printListNameBirthday(controller.viewCompetitionSwimmers()));
                    List<Swimmer> competitionSwimmerList2 = controller.viewCompetitionSwimmers();

                    System.out.println("Vælg ID på svømmeren som skal have tilføjet resultat");
                    try {
                        addCompetitionResult((CompSwimmer) competitionSwimmerList2.get(getValidInt(1, competitionSwimmerList2.size(), false) - 1));
                    } catch (SwimPassiveException e) {
                        System.err.println("Svømmer er \"passiv\". Du kan ikke registrer ny tid.\nPrøv igen.");
                        break;
                    }
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 1-2.");


            }
        }
    }

    public void subMenuChangeSwimmer() {

        boolean run = true;

        while (run) {
            System.out.println("Rediger svømmere:");
            System.out.println("1. Ændre motionssvømmer til konkurrencesvømmer");
            System.out.println("\n0. for at gå tilbage");

            int choice = getValidInt(0, 1, false);

            switch (choice) {
                case 1:

                    List<Swimmer> nonCompetitionSwimmerList = controller.getNonCompSwimmersList();

                    System.out.print(printListNameBirthday(nonCompetitionSwimmerList));

                    System.out.println("\nTast et ID for at ændre motionisten til konkurrencesvømmer. 0 for exit");
                    int indexFromOne = getValidInt(0, nonCompetitionSwimmerList.size(), false);

                    if (indexFromOne != 0) {

                        NonCompSwimmer tempNonCompSwimmer = (NonCompSwimmer) nonCompetitionSwimmerList.get(indexFromOne - 1);

                        CompSwimmer compSwimmerConverted = new CompSwimmer(tempNonCompSwimmer);

                        controller.addSwimmingDisciplinesToSwimmer(compSwimmerConverted, getSwimmingDisciplineFromUser());

                        controller.removeSwimmer(tempNonCompSwimmer);
                        controller.addSwimmer(compSwimmerConverted);
                    }

                    break;

                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 0-1");


            }
        }


    }

    public void subMenuStatistics() {
        boolean run = true;


        while (run) {
            System.out.println("Vis statistik på konkurrencesvømmere:");
            System.out.println("\nJunior:                                               Senior:");
            System.out.println("  Træningstider:                                        Træningstider:");
            System.out.println("     1. Top 5 Brystsvømning                                9. Top 5 Brystsvømning");
            System.out.println("     2. Top 5 Rygcrawl                                    10. Top 5 Rygcrawl");
            System.out.println("     3. Top 5 Crawl                                       11. Top 5 Crawl");
            System.out.println("     4. Top 5 Butterfly                                   12. Top 5 Butterfly");
            System.out.println("  Stævnetider:                                          Stævnetider:");
            System.out.println("     5. Top 5 Brystsvømning                               13. Top 5 Brystsvømning");
            System.out.println("     6. Top 5 Rygcrawl                                    14. Top 5 Rygcrawl");
            System.out.println("     7. Top 5 Crawl                                       15. Top 5 Crawl");
            System.out.println("     8. Top 5 Butterfly                                   16. Top 5 Butterfly");
            System.out.println("\n    17. Top 5 Junior");
            System.out.println("    18. Top 5 Senior");
            System.out.println("    19. Vis samtlige top 5");
            System.out.println("\n     0. for at gå tilbage");

            int choice = getValidInt(0, 19, false);

            switch (choice) {
                case 1:
                    printStatisticsDTO(controller.getTopTrainingJuniorBreaststroke(TOP_LIST_LENGTH), "Top [] Træningstider i Brystsvømning for Junior");

                    break;
                case 2:
                    printStatisticsDTO(controller.getTopTrainingJuniorBackstroke(TOP_LIST_LENGTH), "Top [] Træningstider i Rygcrawl for Junior");

                    break;
                case 3:
                    printStatisticsDTO(controller.getTopTrainingJuniorFreestyle(TOP_LIST_LENGTH), "Top [] Træningstider i Crawl for Junior");

                    break;
                case 4:
                    printStatisticsDTO(controller.getTopTrainingJuniorButterfly(TOP_LIST_LENGTH), "Top [] Træningstider i Butterfly for Junior");

                    break;
                case 5:
                    printStatisticsDTO(controller.getTopCompetitionJuniorBreaststroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Brystsvømning for Junior");

                    break;
                case 6:
                    printStatisticsDTO(controller.getTopCompetitionJuniorBackstroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Rygcrawl for Junior");

                    break;
                case 7:
                    printStatisticsDTO(controller.getTopCompetitionJuniorFreestyle(TOP_LIST_LENGTH), "Top [] Stævnetider i Crawl for Junior");

                    break;
                case 8:
                    printStatisticsDTO(controller.getTopCompetitionJuniorButterfly(TOP_LIST_LENGTH), "Top [] Stævnetider i Butterfly for Junior");

                    break;
                case 9:
                    printStatisticsDTO(controller.getTopTrainingSeniorBreaststroke(TOP_LIST_LENGTH), "Top [] Træningstider i Brystsvømning for Senior");

                    break;
                case 10:
                    printStatisticsDTO(controller.getTopTrainingSeniorBackstroke(TOP_LIST_LENGTH), "Top [] Træningstider i Rygcrawl for Senior");

                    break;
                case 11:
                    printStatisticsDTO(controller.getTopTrainingSeniorFreestyle(TOP_LIST_LENGTH), "Top [] Træningstider i Crawl for Senior");

                    break;
                case 12:
                    printStatisticsDTO(controller.getTopTrainingSeniorButterfly(TOP_LIST_LENGTH), "Top [] Træningstider i Butterfly for Senior");

                    break;
                case 13:
                    printStatisticsDTO(controller.getTopCompetitionSeniorBreaststroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Brystsvømning for Senior");

                    break;
                case 14:
                    printStatisticsDTO(controller.getTopCompetitionSeniorBackstroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Rygcrawl for Senior");

                    break;
                case 15:
                    printStatisticsDTO(controller.getTopCompetitionSeniorFreestyle(TOP_LIST_LENGTH), "Top [] Stævnetider i Crawl for Senior");

                    break;
                case 16:
                    printStatisticsDTO(controller.getTopCompetitionSeniorButterfly(TOP_LIST_LENGTH), "Top [] Stævnetider i Butterfly for Senior");

                    break;
                case 17:
                    printStatisticsDTO(controller.getTopTrainingJuniorBreaststroke(TOP_LIST_LENGTH), "Top [] Træningstider i Brystsvømning for Junior");
                    printStatisticsDTO(controller.getTopTrainingJuniorBackstroke(TOP_LIST_LENGTH), "Top [] Træningstider i Rygcrawl for Junior");
                    printStatisticsDTO(controller.getTopTrainingJuniorFreestyle(TOP_LIST_LENGTH), "Top [] Træningstider i Crawl for Junior");
                    printStatisticsDTO(controller.getTopTrainingJuniorButterfly(TOP_LIST_LENGTH), "Top [] Træningstider i Butterfly for Junior");

                    printStatisticsDTO(controller.getTopCompetitionJuniorBreaststroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Brystsvømning for Junior");
                    printStatisticsDTO(controller.getTopCompetitionJuniorBackstroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Rygcrawl for Junior");
                    printStatisticsDTO(controller.getTopCompetitionJuniorFreestyle(TOP_LIST_LENGTH), "Top [] Stævnetider i Crawl for Junior");
                    printStatisticsDTO(controller.getTopCompetitionJuniorButterfly(TOP_LIST_LENGTH), "Top [] Stævnetider i Butterfly for Junior");

                    break;
                case 18:
                    printStatisticsDTO(controller.getTopTrainingSeniorBreaststroke(TOP_LIST_LENGTH), "Top [] Træningstider i Brystsvømning for Senior");
                    printStatisticsDTO(controller.getTopTrainingSeniorBackstroke(TOP_LIST_LENGTH), "Top [] Træningstider i Rygcrawl for Senior");
                    printStatisticsDTO(controller.getTopTrainingSeniorFreestyle(TOP_LIST_LENGTH), "Top [] Træningstider i Crawl for Senior");
                    printStatisticsDTO(controller.getTopTrainingSeniorButterfly(TOP_LIST_LENGTH), "Top [] Træningstider i Butterfly for Senior");

                    printStatisticsDTO(controller.getTopCompetitionSeniorBreaststroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Brystsvømning for Senior");
                    printStatisticsDTO(controller.getTopCompetitionSeniorBackstroke(TOP_LIST_LENGTH), "Top [] Stævnetider i Rygcrawl for Senior");
                    printStatisticsDTO(controller.getTopCompetitionSeniorFreestyle(TOP_LIST_LENGTH), "Top [] Stævnetider i Crawl for Senior");
                    printStatisticsDTO(controller.getTopCompetitionSeniorButterfly(TOP_LIST_LENGTH), "Top [] Stævnetider i Butterfly for Senior");


                    break;
                case 19:
                    printAllTopSwimmerStatistics();
                    break;


                case 0:
                    run = false;
                    break;

                default:
                    System.out.println("Fejl. Tast 0-19");
            }
        }
    }

    public String printListNameBirthday(List<Swimmer> formatList) {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        if (!formatList.isEmpty()) {

            int id = 1;
            for (Swimmer swimmer : formatList) {
                sb.append("ID ").append(id).append(": ")
                        .append("Efternavn: ").append(swimmer.getLastName()).append(", ")
                        .append("Fornavn: ").append(swimmer.getFirstName()).append(", ")
                        .append("Fødselsdato: ").append(swimmer.getBirthday().format(dateFormat)).append("\n \n");
                id++;
            }

        } else {
            sb.append("Der er ingen svømmere at vise på denne liste'n");
        }
        return sb.toString();
    }

    public String printSingleSwimmerNameBirthday(Swimmer swimmer) {
        if (swimmer instanceof CompSwimmer) {
            CompSwimmer compSwimmer = (CompSwimmer) swimmer;
            StringBuilder sb = new StringBuilder("Svømmer information:\n\n");
            sb.append("Efternavn            Fornavn            Fødselsdato\n");
            sb.append(String.format("%-20s %-18s %-15s\n", compSwimmer.getLastName(),compSwimmer.getFirstName(),compSwimmer.getBirthday()));

            sb.append("\nAktuelt aktiv i følgende discipliner:  ");

            for (SwimmingDiscipline discipline : compSwimmer.getDiscipline()) {
                sb.append(" ").append(discipline);
            }
            sb.append("\n");


            List<TimingTraining> timingTrainingList = compSwimmer.getTrainingTime();
            timingTrainingList.sort(Comparator.comparing(TimingTraining::getDate).reversed());

            if (!timingTrainingList.isEmpty()) {
                sb.append("\nTræningsdato         Disciplin          Svømmmetid\n");

                for (TimingTraining timingTraining : timingTrainingList) {
                    sb.append(printTimingObjects(timingTraining));
                }

            } else {
                sb.append("\nSvømmeren har ingen registrerede træningstider\n");
            }

            List<TimingCompetition> compList = compSwimmer.getCompTime();
            compList.sort(Comparator.comparing(TimingCompetition::getDate).reversed());
            if (!compList.isEmpty()) {
                sb.append("\nStævnedato           Disciplin          Svømmmetid     Placering      Stævne                                            \n");

                for (TimingCompetition timingCompetition : compList) {
                    sb.append(printTimingObjects(timingCompetition));
                }
            } else {
                sb.append("\nSvømmeren har ingen registrerede konkurrencetider\n");
            }

            return sb.toString();

            //return compSwimmer.toString();

        } else {
            StringBuilder sb = new StringBuilder("Svømmer information:\n\n");
            sb.append("Efternavn: ").append(swimmer.getLastName()).append("\n")
                    .append("Fornavn: ").append(swimmer.getFirstName()).append("\n")
                    .append("Fødselsdato: ").append(swimmer.getBirthday().format(dateFormat)).append("\n");
            return sb.toString();
        }
    }

    public String printRestanceSwimmers(List<Swimmer> formatList) {
        StringBuilder sb = new StringBuilder("Svømmere i restance:\n\n");
        for (Swimmer swimmer : formatList) {
            sb.append(String.format("%-15s  %-15s %-15s  %-10s", swimmer.getLastName(), swimmer.getFirstName(), swimmer.getBirthday(), (swimmer.getIsPaid()) ? "Har betalt" : "I restance"));
            sb.append("\n");
        }
        return sb.toString();
    }


    public void printSwimmerListLastNameFirstName(List<Swimmer> list, String title) {
        System.out.println("\n" + title + ": ");

        if (list.isEmpty()) {
            System.out.println("Ingen data at vise");
        } else {
            for (delfin.model.Swimmer swimmer : list) {
                System.out.printf("%-20s %-20s\n", swimmer.getLastName(), swimmer.getFirstName());
            }
        }
        list.forEach(System.out::println);


    }

    public void createNonCompSwimmer() {

        System.out.println("Indtast fornavn (max 15 bogstaver");
        String firstName = getValidString(15, false);

        System.out.println("Indtast efternavn (max 15 bogstaver");
        String lastName = getValidString(15, false);

        System.out.println("Indtast fødselsdato som DD-MM-YYYY");
        LocalDate birthday = getValidatedDate(false, 7);

        controller.addNonCompSwimmer(firstName, lastName, birthday, true, false);
        System.out.println("Svømmeren er oprettet");

    }

    public void createCompSwimmer() {

        System.out.println("Indtast fornavn (max 15 bogstaver");
        String firstName = getValidString(15, false);

        System.out.println("Indtast efternavn (max 15 bogstaver");
        String lastName = getValidString(15, false);

        System.out.println("Indtast fødselsdato som DD-MM-YYYY");
        LocalDate birthday = getValidatedDate(false, 7);

        EnumSet<SwimmingDiscipline> disciplines = getSwimmingDisciplineFromUser();

        controller.addCompSwimmerToList(firstName, lastName, birthday, disciplines, true, false);
        System.out.println("Svømmeren er oprettet");
    }


    public EnumSet<SwimmingDiscipline> getSwimmingDisciplineFromUser() {

        EnumSet<SwimmingDiscipline> disciplines = EnumSet.noneOf(SwimmingDiscipline.class);

        System.out.println("Er svømmeren aktiv i crawl? \nTryk 1 for ja - Tryk 0 for nej");
        int freestyle = getValidInt(0, 1, false);
        if (freestyle == 1) {
            disciplines.add(SwimmingDiscipline.FREESTYLE);
        }

        System.out.println("Er svømmeren aktiv i rygcrawl? \nTryk 1 for ja - Tryk 0 for nej");
        int backStroke = getValidInt(0, 1, false);
        if (backStroke == 1) {
            disciplines.add(SwimmingDiscipline.BACKSTROKE);
        }

        System.out.println("Er svømmeren aktiv i brystsvømning? \nTryk 1 for ja - Tryk 0 for nej");
        int breastStroke = getValidInt(0, 1, false);
        if (breastStroke == 1) {
            disciplines.add(SwimmingDiscipline.BREASTSTROKE);
        }

        System.out.println("Er svømmeren aktiv i butterfly? \nTryk 1 for ja - Tryk 0 for nej");
        int butterfly = getValidInt(0, 1, false);
        if (butterfly == 1) {
            disciplines.add(SwimmingDiscipline.BUTTERFLY);
        }

        return disciplines;
    }


    public void addTrainingResult(CompSwimmer swimmer) throws SwimPassiveException {
        if (!swimmer.getIsActive()) {
            throw new SwimPassiveException("Svømmer er \"passiv\". Du kan ikke registrer ny tid.");
        }

        SwimmingDiscipline discipline = null;
        System.out.println("Hvilken Svømmedisciplin vil du tilføje træningstid for? Tast 1-4\n" +
                "1.Crawl \n2.Rygcrawl \n3.Brystsvømning \n4.Butterfly");
        int choice = getValidInt(1, 4, false);
        switch (choice) {
            case 1:
                discipline = SwimmingDiscipline.FREESTYLE;
                break;
            case 2:
                discipline = SwimmingDiscipline.BACKSTROKE;
                break;
            case 3:
                discipline = SwimmingDiscipline.BREASTSTROKE;
                break;
            case 4:
                discipline = SwimmingDiscipline.BUTTERFLY;
                break;
            default:
                System.out.println("Ugyldigt valg. Vælg venligst mellem 1 og 4.");
                return;
        }

        System.out.println("Hvilken dato er træningsresultatet fra? Indtast dato som DD-MM-YYYY");

        LocalDate date = getValidatedDate(false, 0);

        System.out.println("Hvad er træningstiden? \n Indtast som format 00:00");
        Duration duration = getValidDuration();

        TimingTraining timingTraining = new TimingTraining(discipline, date, duration);

        if (swimmer != null) {
            swimmer.addTrainingTime(timingTraining);
            System.out.println("Træningsresultat tilføjet til " + swimmer.getFirstName());
        }


    }

    public void addCompetitionResult(CompSwimmer swimmer) throws SwimPassiveException {
        if (!swimmer.getIsActive()) {
            throw new SwimPassiveException("Svømmer er \"passiv\". Du kan ikke registrer ny tid.");
        }

        System.out.println("Hvad er navnet på stævnet svømmeren har deltaget i? (max 18 bogstaver)");
        String competitionName = getValidString(18, false);

        System.out.println("Hvilken dato er stævneresultatet fra? Indtast dato som DD-MM-YYYY");
        LocalDate date = getValidatedDate(false, 0);

        SwimmingDiscipline discipline = null;
        System.out.println("Hvilken Svømmedisciplin vil du tilføje træningstid for? Tast 1-4\n" +
                "1.Crawl \n2.Rygcrawl \n3.Brystsvømning \n4.Butterfly");
        int choice = getValidInt(1, 4, false);
        switch (choice) {
            case 1:
                discipline = SwimmingDiscipline.FREESTYLE;
                break;
            case 2:
                discipline = SwimmingDiscipline.BACKSTROKE;
                break;
            case 3:
                discipline = SwimmingDiscipline.BREASTSTROKE;
                break;
            case 4:
                discipline = SwimmingDiscipline.BUTTERFLY;
                break;
            default:
                System.out.println("Ugyldigt valg. Vælg venligst mellem 1 og 4.");
                return;
        }

        System.out.println("Hvilen placering blev svømmeren? Skriv som et heltal");
        int ranking = getValidInt(1,20, false);

        System.out.println("Hvad blev tiden? \n Indtast som format 00:00");

        Duration duration = getValidDuration();

        TimingCompetition comp = new TimingCompetition(competitionName, date, ranking, discipline, duration);

        if (swimmer != null) {
            swimmer.addCompTime(comp);
            System.out.println("Stævneresultat tilføjet til " + swimmer.getFirstName());
        }

    }


    //Metoder til validering af input:

    //Validerer at input er en int med en min og max range
    //og en boolean useEmpty, hvis true er det tilladt med et tomt input
    public int getValidInt(int min, int max, boolean useEmpty) {

        String input;
        int number;

        while (true) {
            input = scanner.nextLine();

            if (input.isEmpty()) {
                if (useEmpty) {
                    return Integer.MIN_VALUE;
                } else {
                    System.err.println("Skriv et tal.");
                }


                //Test om String indeholder tal:
            } else if (input.matches("-?\\d+")) {
                //Omdan tallene i Stringen til int:
                number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.printf("Tallet du har indtastet er uden for mulighederne. \nTallet skal være mellem %d og %d. \nPrøv igen\n", min, max);

                }

            } else {
                System.err.println("Det er ikke et tal du har indtastet. Prøv igen.");
            }
        }

    }


    //String Max karakter for navn.

    public String getValidString(int maxLength, boolean useEmpty) {
        String input;

        while (true) {
            input = scanner.nextLine();

            //Hvis empty er true; returneres en tom String:
            if (input.isEmpty()) {
                if (useEmpty) {
                    return "";
                } else {
                    System.err.println("Skriv noget.");
                    continue;
                }
            }

            //Tjek at String er indenfor maxLength:
            if (input.length() > maxLength) {
                System.err.printf("Inputtet er for langt. Det må max være %d tegn. Prøv igen. \n", maxLength);
                continue;
            }

            if (input.matches(".*\\d.*")) {
                System.out.println("Den tekst du har indtastet indeholder tal.\nEr det meningen?\n(1) Ja (2) Nej");
                if (getValidInt(1, 2, false) == 2) {
                    System.out.println("Prøv igen");
                    continue;
                }
            }

            return input;



        }
    }

    public void printStatisticsDTO(List<StatisticsDataTransferObject> dtoList, String title) {

        if (!dtoList.isEmpty()) {
            System.out.println(title.replace("[]", Integer.toString(dtoList.size())));

            if (dtoList.getFirst().getRelevantTimingSession() instanceof TimingCompetition) {
                System.out.println("Efternavn          Fornavn          Fødselsdato      Stævnedato      Stævne                         Disciplin       Placering      Svømmmetid\n");
                for (StatisticsDataTransferObject dto : dtoList) {
                    TimingCompetition tempTimingCompetition = (TimingCompetition) dto.getRelevantTimingSession();
                    System.out.printf("%-17s  %-15s  %-15s  %-15s %-30s %-21s %2d. %15s\n"
                            , dto.getSwimmer().getLastName()
                            , dto.getSwimmer().getFirstName()
                            , dto.getSwimmer().getBirthday().format(dateFormat)
                            , dto.getRelevantTimingSession().getDate().format(dateFormat)
                            , tempTimingCompetition.getCompetitionName()
                            , dto.getRelevantTimingSession().getDiscipline().toString()
                            , tempTimingCompetition.getRanking()
                            , formatDuration(dto.getRelevantTimingSession().getTimeRegister()));

                }

            } else {
                System.out.println("Efternavn             Fornavn               Fødselsdato      Træningsdato         Disciplin             Svømmmetid\n");

                for (StatisticsDataTransferObject dto : dtoList) {
                    System.out.printf("%-20s  %-20s  %-15s  %-20s %-15s  %15s\n"
                            , dto.getSwimmer().getLastName()
                            , dto.getSwimmer().getFirstName()
                            , dto.getSwimmer().getBirthday().format(dateFormat)
                            , dto.getRelevantTimingSession().getDate().format(dateFormat)
                            , dto.getRelevantTimingSession().getDiscipline().toString()
                            , formatDuration(dto.getRelevantTimingSession().getTimeRegister()));


                }
            }

            System.out.println();
            System.out.println();
        }
    }

    public String printTimingObjects(TimingTraining timingObject) {

        StringBuilder tempString = new StringBuilder();
        if (timingObject instanceof TimingCompetition) {

            TimingCompetition tempTimingCompetition = (TimingCompetition) timingObject;

            tempString.append(String.format("%-20s %-22s  %5s %12d.      %-26s\n"
                    , tempTimingCompetition.getDate().format(dateFormat)
                    , tempTimingCompetition.getDiscipline().toString()
                    , formatDuration(tempTimingCompetition.getTimeRegister())
                    , tempTimingCompetition.getRanking()
                    , tempTimingCompetition.getCompetitionName()));
        } else {
            tempString.append(String.format("%-20s %-22s  %5s\n"
                    , timingObject.getDate().format(dateFormat)
                    , timingObject.getDiscipline().toString()
                    , formatDuration(timingObject.getTimeRegister())));
        }
    return tempString.toString();
    }


    public void printAllTopSwimmerStatistics() {

        //training junior 4 disciplines
        printStatisticsDTO(controller.getTopTrainingJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Junior", TOP_LIST_LENGTH));

        //training senior 4 disciplines
        printStatisticsDTO(controller.getTopTrainingSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopTrainingSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Senior", TOP_LIST_LENGTH));

        //competition junior 4 disciplines
        printStatisticsDTO(controller.getTopCompetitionJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Junior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Junior", TOP_LIST_LENGTH));

        //competition senior 4 disciplines
        printStatisticsDTO(controller.getTopCompetitionSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Senior", TOP_LIST_LENGTH));
        printStatisticsDTO(controller.getTopCompetitionSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Senior", TOP_LIST_LENGTH));


    }

    public String formatDuration(Duration duration) {
        Duration secondsIsolated = duration.minusMinutes(duration.toMinutes());

        return String.format("%2d:%02d", duration.toMinutes(), secondsIsolated.toSeconds());

    }


    //Validerer input af varighed. 100 meter må ikke vare timer, men max 59 minutter 0g 59 sekunder.
    public Duration getValidDuration() {

        String input;

        //Regulært udtryk (kun for minutter : sekunder):
        //regex definerer mønsteret som et input skal matche.
        //^ siger at det skal matche fra start. mulige tal i hhv minutter : sekunder.
        //$ afslutter mønsteret
        String regex1 = "^[0-5][0-9]:[0-5][0-9]$";
        String regex2 = "^[0-9]:[0-5][0-9]$";

        while (true) {
            input = scanner.nextLine();

            //If: tomt input, else if: input ikke matcher regex, else: hvis input matcher spilt det op i minutter og sekunder: :
            if (input.isEmpty()) {
                System.err.println("Skriv noget");
            } else if (!(input.matches(regex1) || input.matches(regex2))) {
                System.err.println("Ugyldigt input! Skriv minutter:sekunder");
            } else {
                String[] parts = input.split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);

                //opretter et Duration objekt der kan returneres:
                Duration duration = Duration.ofMinutes(minutes).plusSeconds(seconds);
                return duration;
            }


        }


    }

    public LocalDate getValidatedDate(boolean acceptFutureDate, int minYearsRange) {

        LocalDate todaysDate = LocalDate.now();
        LocalDate userDate;
        Period range;

        while (true) {

            String textDate = scanner.nextLine();

            try {
                userDate = LocalDate.parse(textDate, dateFormat);
            } catch (DateTimeParseException e) {
                System.err.println("Du har ikke indtastet en gyldig dato\nPrøv igen\n");
                continue;
            }

            if (userDate.compareTo(todaysDate) >= 1 && !acceptFutureDate) {
                System.err.println("Du har indtastet end fremtidig dato\nPrøv igen\n");
                continue;
            }

            range = Period.between(todaysDate, userDate);
            if (Math.abs(range.getYears()) < minYearsRange) {
                System.err.println("Du skal indtaste en dato der ligger mindst " + minYearsRange + " år væk\n"
                + "Den indtastede dato er kun " + Math.abs(range.getYears()) + " år væk\nPrøv igen\n");
                continue;
            }

            return userDate;

        }



    }


}
