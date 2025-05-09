import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private AppController controller;

    public UI(AppController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;

    }



    public void menuOptions () {



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


            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    //createSwimmer();
                    break;
                case 2:
                    //viewSwimmers();
                    break;
                case 3:
                    //changeSwimmer();
                    break;
                case 4:
                    //viewTopFive();
                    break;
                case 5:
                    //registerTimes();
                    break;
                case 6:
                    printSwimmerListLastNameFirstName(controller.viewRestanceSwimmers(), "Medlemmer som ikke har betalt");
                    printSwimmerListLastNameFirstName(controller.viewCompetitionSwimmers(), "Medlemmer som er konkurrencesvømmere");


                    break;
                case 7:
                    //registerPayment();
                    break;
                case 8:
                    //expectedRevenue();
                    break;
                default:
                    System.out.println("Fejl. Tast 1-8");


            }




    }

    public void printSwimmerListLastNameFirstName(List<Swimmer> list, String title) {
        System.out.println(title + ": ");

        if (list.isEmpty()) {
            System.out.println("Ingen data at vise");
        } else {
            for (Swimmer swimmer : list) {
                System.out.printf("%-20s %-20s\n", swimmer.getLastName(), swimmer.getFirstName());
            }
        }
    }


}
