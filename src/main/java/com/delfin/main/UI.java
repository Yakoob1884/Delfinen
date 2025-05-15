package delfin.main;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
//package delfin.main;

import delfin.controller.AppController;
import delfin.model.*;
import delfin.utils.SwimPassiveException;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Duration;



public class UI {

    private Scanner scanner;
    private AppController controller;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final int TOP_LIST_LENGTH = 5;


    public UI(AppController controller, Scanner scanner) {
        this.scanner = scanner;
        this.controller = controller;

    }



    public void menuOptions () throws IOException {


        boolean run = true;

        while(run) {


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


            int choice = getValidInt(0,8, false);

            switch (choice) {

                case 1:
                    createSwimmer();
                    break;
                case 2:
                    viewSwimmer();
                    break;
                case 3:
                    //changeSwimmer();
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
                    System.out.println(printListNameBirthday(controller.returnGetSwimmersIsPaidFalseList()));
                    controller.swimmerHasPaid();
                    break;
                case 8:
                    System.out.println("Årets forventede totale omsætning er: ");
                    System.out.println(controller.calculateEstimatedRevenue());
                    break;
                case 0:
                    //controller.saveListOfSwimmersToFile();

                    System.out.println("Programmet afslutter...");
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 1-8");


            }
        }




    }

    public void createSwimmer() {

        boolean run = true;

        while(run) {
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
                    System.out.println("Fejl. Tast 1-2.");


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
            System.out.println("0. for at gå tilbage");

            int choice = getValidInt(0, 2, false);

            switch (choice) {
                case 1:
                    System.out.println(printListNameBirthday(controller.getAllSwimmers()));
                    break;
                case 2:
                    System.out.println(printListNameBirthday(controller.viewCompetitionSwimmers()));
                    List<Swimmer>competitionSwimmerList = controller.viewCompetitionSwimmers();
                    System.out.println(printSingleSwimmerNameBirthday(competitionSwimmerList.get(getValidInt(0, competitionSwimmerList.size(), false) -1)));
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 1-2.");


            }
        }
    }

    public void addResult() {

        boolean run = true;

        while(run) {
            System.out.println("Registrer tider:");
            System.out.println("Vil du registrer tidsresultat for en træning eller en konkurrence?");
            System.out.println("1. for træningsresultat");
            System.out.println("2. for konkurrenceresultat");
            System.out.println("0. for at gå tilbage");

            int choice = getValidInt(0, 2, false);

            switch (choice) {
                case 1:

                    System.out.print(printListNameBirthday(controller.viewCompetitionSwimmers()));
                    List<Swimmer>competitionSwimmerList = controller.viewCompetitionSwimmers();
                    try {
                        addTrainingResult((CompSwimmer) competitionSwimmerList.get(getValidInt(1, competitionSwimmerList.size(), false) - 1));
                    } catch (SwimPassiveException e) {
                        System.err.println("Svømmer er \"passiv\". Du kan ikke registrer ny tid.\nPrøv igen.");
                        break;
                    }
                    break;
                case 2:
                    System.out.print(printListNameBirthday(controller.viewCompetitionSwimmers()));
                    List<Swimmer>competitionSwimmerList2 = controller.viewCompetitionSwimmers();
                    try {
                        addCompetitionResult((CompSwimmer) competitionSwimmerList2.get(getValidInt(1, competitionSwimmerList2.size(), false) - 1));
                    } catch (SwimPassiveException e){
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

    public void subMenuStatistics() {
        boolean run = true;

        while(run) {
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
                    printStatisticsDTO(controller.getTopTrainingJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Junior", TOP_LIST_LENGTH));

                    break;
                case 2:
                    printStatisticsDTO(controller.getTopTrainingJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Junior", TOP_LIST_LENGTH));

                    break;
                case 3:
                    printStatisticsDTO(controller.getTopTrainingJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Junior", TOP_LIST_LENGTH));

                    break;
                case 4:
                    printStatisticsDTO(controller.getTopTrainingJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Junior", TOP_LIST_LENGTH));

                    break;
                case 5:
                    printStatisticsDTO(controller.getTopCompetitionJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Junior", TOP_LIST_LENGTH));

                    break;
                case 6:
                    printStatisticsDTO(controller.getTopCompetitionJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Junior", TOP_LIST_LENGTH));

                    break;
                case 7:
                    printStatisticsDTO(controller.getTopCompetitionJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Junior", TOP_LIST_LENGTH));

                    break;
                case 8:
                    printStatisticsDTO(controller.getTopCompetitionJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Junior", TOP_LIST_LENGTH));

                    break;
                case 9:
                    printStatisticsDTO(controller.getTopTrainingSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Senior", TOP_LIST_LENGTH));

                    break;
                case 10:
                    printStatisticsDTO(controller.getTopTrainingSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Senior", TOP_LIST_LENGTH));

                    break;
                case 11:
                    printStatisticsDTO(controller.getTopTrainingSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Senior", TOP_LIST_LENGTH));

                    break;
                case 12:
                    printStatisticsDTO(controller.getTopTrainingSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Senior", TOP_LIST_LENGTH));

                    break;
                case 13:
                    printStatisticsDTO(controller.getTopCompetitionSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Senior", TOP_LIST_LENGTH));

                    break;
                case 14:
                    printStatisticsDTO(controller.getTopCompetitionSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Senior", TOP_LIST_LENGTH));

                    break;
                case 15:
                    printStatisticsDTO(controller.getTopCompetitionSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Senior", TOP_LIST_LENGTH));

                    break;
                case 16:
                    printStatisticsDTO(controller.getTopCompetitionSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Senior", TOP_LIST_LENGTH));

                    break;
                case 17:
                    printStatisticsDTO(controller.getTopTrainingJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Junior", TOP_LIST_LENGTH));

                    printStatisticsDTO(controller.getTopCompetitionJuniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionJuniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionJuniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Junior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionJuniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Junior", TOP_LIST_LENGTH));

                    break;
                case 18:
                    printStatisticsDTO(controller.getTopTrainingSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Brystsvømning for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Rygcrawl for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Crawl for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopTrainingSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Træningstider i Butterfly for Senior", TOP_LIST_LENGTH));

                    printStatisticsDTO(controller.getTopCompetitionSeniorBreaststroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Brystsvømning for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionSeniorBackstroke(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Rygcrawl for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionSeniorFreestyle(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Crawl for Senior", TOP_LIST_LENGTH));
                    printStatisticsDTO(controller.getTopCompetitionSeniorButterfly(TOP_LIST_LENGTH), String.format("Top [] Stævnetider i Butterfly for Senior", TOP_LIST_LENGTH));


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
        int id = 1;
        for (Swimmer swimmer : formatList) {
            sb.append("ID ").append(id).append(": ")
                    .append("Efternavn: ").append(swimmer.getLastName()).append(", ")
                    .append("Fornavn: ").append(swimmer.getFirstName()).append(", ")
                    .append("Fødselsdato: ").append(swimmer.getBirthday().format(dateFormat)).append("\n \n");
            id++;
        }
        return sb.toString();
    }

    public String printSingleSwimmerNameBirthday(Swimmer swimmer) {
        if (swimmer instanceof CompSwimmer) {
            CompSwimmer compSwimmer = (CompSwimmer) swimmer;
            return compSwimmer.toString();
        }
        else {
            StringBuilder sb = new StringBuilder("Svømmer information:\n\n");
            sb.append("Efternavn: ").append(swimmer.getLastName()).append("\n")
                    .append("Fornavn: ").append(swimmer.getFirstName()).append("\n")
                    .append("Fødselsdato: ").append(swimmer.getBirthday().format(dateFormat)).append("\n");
            return sb.toString();
        }
    }

    public String printRestanceSwimmers(List<Swimmer> formatList){
        StringBuilder sb = new StringBuilder("Svømmere i restance:\n\n");
        for (Swimmer swimmer : formatList) {
            sb.append(String.format("%-20s  %-20s  %20s  %5b ", swimmer.getLastName(), swimmer.getFirstName(), swimmer.getBirthday(), swimmer.getIsPaid()));
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

    public void createNonCompSwimmer (){

        System.out.println("Indtast fornavn");
        String firstName = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Indtast efternavn");
        String lastName = scanner.nextLine();

        System.out.println("Indtast fødselsdato som YYYY-MM-DD");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);

        controller.addNonCompSwimmerToList(firstName, lastName, birthday);
        System.out.println("Svømmeren er oprettet");

    }

    public void createCompSwimmer (){

        System.out.println("Indtast fornavn");
        String firstName = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Indtast efternavn");
        String lastName = scanner.nextLine();

        System.out.println("Indtast fødselsdato som YYYY-MM-DD");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);

        EnumSet<SwimmingDiscipline> disciplines = EnumSet.noneOf(SwimmingDiscipline.class);

        System.out.println("Er svømmeren aktiv i crawl? \nTryk 1 for ja - Tryk 0 for nej");
        int freestyle = scanner.nextInt();
        if (freestyle == 1) {
            disciplines.add(SwimmingDiscipline.FREESTYLE);
        }

        System.out.println("Er svømmeren aktiv i rygcrawl? \nTryk 1 for ja - Tryk 0 for nej");
        int backStroke = scanner.nextInt();
        if (backStroke == 1) {
            disciplines.add(SwimmingDiscipline.BACKSTROKE);
        }

        System.out.println("Er svømmeren aktiv i brystsvømning? \nTryk 1 for ja - Tryk 0 for nej");
        int breastStroke = scanner.nextInt();
        if (breastStroke == 1) {
            disciplines.add(SwimmingDiscipline.BREASTSTROKE);
        }

        System.out.println("Er svømmeren aktiv i butterfly? \nTryk 1 for ja - Tryk 0 for nej");
        int butterfly = scanner.nextInt();
        if (butterfly == 1) {
            disciplines.add(SwimmingDiscipline.BUTTERFLY);
        }

        controller.addCompSwimmerToList(firstName, lastName, birthday, disciplines);
        System.out.println("Svømmeren er oprettet");
    }


    public void addTrainingResult(CompSwimmer swimmer) throws SwimPassiveException {
        if (!swimmer.getIsActive()){
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

        System.out.println("Hvilken dato er træningsresultatet fra? Indtast dato som YYYY-MM-DD");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

        System.out.println("Hvad er træningstiden? \n Indtast antal minutter");
        int minutes = scanner.nextInt();

        System.out.println("Indtast antal sekunder");
        int seconds = scanner.nextInt();

        Duration duration = Duration.ofMinutes(minutes).plusSeconds(seconds);

        Training training = new Training(discipline, date, duration);

        if (swimmer != null) {
            swimmer.addTrainingTime(training);
            System.out.println("Træningsresultat tilføjet til " + swimmer.getFirstName());
        }
        scanner.nextLine();

    }

    public void addCompetitionResult(CompSwimmer swimmer) throws SwimPassiveException{
        if (!swimmer.getIsActive()){
            throw new SwimPassiveException("Svømmer er \"passiv\". Du kan ikke registrer ny tid.");
        }

        System.out.println("Hvad er navnet på stævnet svømmeren har deltaget i?");
        String competitionName = scanner.nextLine();

        System.out.println("Hvilken dato er stævneresultatet fra? Indtast dato som YYYY-MM-DD");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

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
        int ranking = scanner.nextInt();

        System.out.println("Hvad blev tiden? \n Indtast antal minutter");
        int minutes = scanner.nextInt();

        System.out.println("Indtast antal sekunder");
        int seconds = scanner.nextInt();

        Duration duration = Duration.ofMinutes(minutes).plusSeconds(seconds);

        Competition comp = new Competition(competitionName, date, ranking, discipline, duration);

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

            if(input.isEmpty()) {
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
                }
            }

            //Tjek at String er indenfor maxLength:
            if (input.length() > maxLength) {
                System.err.printf("Inputtet er for langt. Det må max være %d tegn. Prøv igen. \n", maxLength);
            } else {
                return input;
            }


        }
    }

    public void printStatisticsDTO(List<StatisticsDataTransferObject> dtoList, String title) {

        if (!dtoList.isEmpty()) {
            System.out.println(title.replace("[]", Integer.toString(dtoList.size())));

            if (dtoList.getFirst().getRelevantTimingSession() instanceof Competition) {
                System.out.println("Efternavn          Fornavn          Fødselsdato      Stævnedato      Stævne                         Disciplin          Placering         Svømmmetid\n");
                for (StatisticsDataTransferObject dto : dtoList) {
                    Competition tempCompetition = (Competition) dto.getRelevantTimingSession();
                    System.out.printf("%-17s  %-15s  %-15s  %-15s %-30s %-17s %2d. %20s\n"
                            , dto.getSwimmer().getLastName()
                            , dto.getSwimmer().getFirstName()
                            , dto.getSwimmer().getBirthday().format(dateFormat)
                            , dto.getRelevantTimingSession().getDate().format(dateFormat)
                            , tempCompetition.getCompetitionName()
                            , dto.getRelevantTimingSession().getDiscipline().toString()
                            , tempCompetition.getRanking()
                            , formatDuration(dto.getRelevantTimingSession().getTimeRegister()));

                }

                } else {
                System.out.println("Efternavn             Fornavn               Fødselsdato      Træningsdato         Disciplin                  Svømmmetid\n");

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

        return String.format("%02d:%02d", duration.toMinutes(), secondsIsolated.toSeconds());

    }









    //Validerer input af varighed. 100 meter må ikke vare timer, men max 59 minutter 0g 59 sekunder.
    public Duration getValidDuration() {

        String input;

        //Regulært udtryk (kun for minutter : sekunder):
        //regex definerer mønsteret som et input skal matche.
        //^ siger at det skal matche fra start. mulige tal i hhv minutter : sekunder.
        //$ afslutter mønsteret
        String regex = "^[0-5][0-9]:[0-5][0-9]$";

        while (true) {
            input = scanner.nextLine();

            //If: tomt input, else if: input ikke matcher regex, else: hvis input matcher spilt det op i minutter og sekunder: :
            if (input.isEmpty()) {
                System.err.println("Skriv noget");
            }else if (!input.matches(regex)) {
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





}
