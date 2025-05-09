import java.util.*;


public class ListOfSwimmers {

    private ArrayList<Swimmer> listOfSwimmers;

    public ListOfSwimmers(){
        this.listOfSwimmers = new ArrayList<>();
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



}