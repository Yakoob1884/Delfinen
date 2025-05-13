import java.util.EnumSet;
//package delfin.main;

import delfin.controller.AppController;
import delfin.model.CompSwimmer;
import delfin.model.NonCompSwimmer;
import delfin.model.Swimmer;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class UI {

    private Scanner scanner;
    private AppController controller;

    public UI(AppController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;

    }



    public void menuOptions () {

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


            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createSwimmer();
                    break;
                case 2:
                    //viewSwimmers();
                   // viewAllSwimmers();
                    break;
//                case 3:
                    //changeSwimmer();
//                    break;
                case 4:
                    //viewTopFive();
                    break;
                case 5:
                    //registerTimes(); konkorrence træning
                    break;
                case 6:
                    //printSwimmerListLastNameFirstName(controller.viewRestanceSwimmers(), "Medlemmer som ikke har betalt");
                    //printSwimmerListLastNameFirstName(controller.viewCompetitionSwimmers(), "Medlemmer som er konkurrencesvømmere");
                    //controller.printAllTestToRemoveAgain();
                    controller.top5();

                    break;
                case 7:
                    //registerPayment();
                    break;
                case 8:
                    //expectedRevenue();
                    break;
                case 0:
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
            System.out.println("Vil du oprette en motionist eller en konkurrencesvømmer? ");
            System.out.println("1. for motionist");
            System.out.println("2. for konkurrencesvømmer");
            System.out.println("0. for at gå tilbage");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createNonCompSwimmer();
                    break;
                case 2:
                    createCompSwimmer();
                    break;
                case 9:
                    run = false;
                default:
                    System.out.println("Fejl. Tast 1-2.");


            }
        }
    }

    public void viewSwimmers() {

        boolean run = true;

        while(run) {


            int choice = scanner.nextInt();

//            switch (choice) {
//                case 1:
//                    createNonCompSwimmer();
//                    break;
//                case 2:
//                    createCompSwimmer();
//                    break;
//                case 9:
//                    run = false;
//                default:
//                    System.out.println("Fejl. Tast 1-2.");
//
//
//            }
        }
    }


    public void printSwimmerListLastNameFirstName(List<Swimmer> list, String title) {
        System.out.println("\n" + title + ": ");

//        if (list.isEmpty()) {
//            System.out.println("Ingen data at vise");
//        } else {
//            for (delfin.model.Swimmer swimmer : list) {
//                System.out.printf("%-20s %-20s\n", swimmer.getLastName(), swimmer.getFirstName());
//            }
//        }
        list.forEach(System.out::println);


    }

    public void createNonCompSwimmer (){

        System.out.println("Indtast fornavn");
        String firstName = scanner.nextLine();

        System.out.println("Indtast efternavn");
        String lastName = scanner.nextLine();

        System.out.println("Indtast fødselsdato som YYYY-MM-DD");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);

        Swimmer swimmer = new NonCompSwimmer(firstName, lastName, birthday);
        ListOfSwimmers allSwimmers = new ListOfSwimmers();
        allSwimmers.addSwimmer(swimmer);
    }

    public void createCompSwimmer (){

        System.out.println("Indtast fornavn");
        String firstName = scanner.nextLine();

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

        Swimmer swimmer = new CompSwimmer(firstName, lastName, birthday, disciplines);
        ListOfSwimmers allSwimmers = new ListOfSwimmers();
        allSwimmers.addSwimmer(swimmer);
    }





}
