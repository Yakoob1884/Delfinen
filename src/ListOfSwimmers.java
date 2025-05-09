import java.util.*;


public class ListOfSwimmers {

    private ArrayList<Swimmer> listOfSwimmers;

    public ListOfSwimmers(){
        this.listOfSwimmers = new ArrayList<>();
    }

    public void addSwimmer(Swimmer swimmer){
        listOfSwimmers.add(swimmer);
    }

    public List<Swimmer> getSwimmersIsPaidFalse() {

        List<Swimmer> listSwimmersIsPaidFalse = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (!swimmer.getIsPaid()) {
                listSwimmersIsPaidFalse.add(swimmer);
            }
        }

        return listSwimmersIsPaidFalse;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        for (Swimmer swimmer : listOfSwimmers) {
            sb.append(swimmer).append("\n");
        }
        return sb.toString();
    }

    public Swimmer getSwimmer(int index) {
        return listOfSwimmers.get(index);
    }



}