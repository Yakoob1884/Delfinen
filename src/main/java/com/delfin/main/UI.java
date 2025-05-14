package delfin.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
//package delfin.main;

import delfin.controller.AppController;
import delfin.model.*;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Duration;



public class UI {

    private Scanner scanner;
    private AppController controller;

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
            System.out.println("4. Vis top 5 svømmere");
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
                    controller.viewAllSwimmers();
                    break;
//                case 3:
                    //changeSwimmer();
//                    break;
                case 4:
                    controller.top5();
                    break;
                case 5:
                    addResult();
                    break;
                case 6:
                    printSwimmerListLastNameFirstName(controller.viewRestanceSwimmers(), "Medlemmer som ikke har betalt");
                    printSwimmerListLastNameFirstName(controller.viewCompetitionSwimmers(), "Medlemmer som er konkurrencesvømmere");
                    controller.printAllTestToRemoveAgain();
                    break;
                case 7:
                    //registerPayment();
                    controller.swimmerHasPaid();
                    break;
                case 8:
                    //expectedRevenue();
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
                    //addTrainingResult();
                    break;
                case 2:
                    //addCompetitionResult();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Fejl. Tast 1-2.");


            }
        }
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

    public String formatListPrint(ArrayList<Swimmer> formatList) {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        int id = 1;
        for (Swimmer swimmer : formatList) {
            sb.append("ID " + id + ": " + swimmer).append("\n");
            id++;
        }
        return sb.toString();
    }

    public void addTrainingResult(CompSwimmer swimmer){

        SwimmingDiscipline discipline = null;
        System.out.println("Hvilken Svømmedisciplin vil du tilføje træningstid for? Tast 1-4\n" +
                           "1. Crawl \n2.Rygcrawl \n3.Brystsvømning \n4. Butterfly");
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

    }

    public void addCompetitionResult(CompSwimmer swimmer){

        System.out.println("Hvad er navnet på stævnet svømmeren har deltaget i?");
        String competitionName = scanner.nextLine();

        System.out.println("Hvilken dato er stævneresultatet fra? Indtast dato som YYYY-MM-DD");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

        SwimmingDiscipline discipline = null;
        System.out.println("Hvilken Svømmedisciplin vil du tilføje træningstid for? Tast 1-4\n" +
                "1. Crawl \n2.Rygcrawl \n3.Brystsvømning \n4. Butterfly");
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









    //Duration: Spilt mellem :, 1 regel for den ene og 1 anden regel for den anden.





}
