import java.time.Duration;
import java.time.LocalDate;
import java.util.*;


public class ListOfSwimmers {

    private ArrayList<Swimmer> listOfSwimmers;

    public ListOfSwimmers(){
        this.listOfSwimmers = new ArrayList<>();
        dummieList();
    }

    public void addSwimmer(Swimmer swimmer){
        listOfSwimmers.add(swimmer);
    }

    public List<Swimmer> getSwimmersIsPaidFalseList() {

        List<Swimmer> listSwimmersIsPaidFalse = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (!swimmer.getIsPaid()) {
                listSwimmersIsPaidFalse.add(swimmer);
            }
        }

        return listSwimmersIsPaidFalse;

    }

    public List<Swimmer> getCompSwimmersList() {
        List<Swimmer> compSwimmersList = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (swimmer instanceof CompSwimmer) {
                compSwimmersList.add(swimmer);
            }
        }
        return compSwimmersList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        int id = 1;
        for (Swimmer swimmer : listOfSwimmers) {
            sb.append("ID " + id + ": " + swimmer).append("\n");
            id++;
        }
        return sb.toString();
    }

    public Swimmer getSwimmer(int index) {
        return listOfSwimmers.get(index);
    }

    public void dummieList(){

        listOfSwimmers.add(new NonCompSwimmer("Peter", "Petersen", LocalDate.of(1985, 5, 9), true, true));
        listOfSwimmers.add(new NonCompSwimmer("Niels", "Nielsen", LocalDate.of(1975, 1, 22), false, false));
        listOfSwimmers.add(new CompSwimmer("Jakob", "Jakobsen", LocalDate.of(2000, 3, 9), true, false, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025,5,9), Duration.parse("PT, 1M, 15S"))));


        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new NonCompSwimmer());

    }

}