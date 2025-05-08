import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private Controller controller;

    public UI(Controller controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;

    }



    public void menuOptions (Scanner scanner) {



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
                    createSwimmer();
                    break;
                case 2:
                    viewSwimmers();
                    break;
                case 3:
                    changeSwimmer();
                    break;
                case 4:
                    viewTopFive();
                    break;
                case 5:
                    registerTimes();
                    break;
                case 6:
                    viewRestance();
                    break;
                case 7:
                    registerPayment();
                    break;
                case 8:
                    expectedRevenue();
                    break;
                default:
                    System.out.println("Fejl. Tast 1-8");


            }




    }


}
