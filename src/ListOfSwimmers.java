import java.util.ArrayList;
import java.util.Collections;

public class ListOfSwimmers {

    private ArrayList<Swimmer> listOfSwimmers;

    public ListOfSwimmers(){
        this.listOfSwimmers = new ArrayList<>();
    }

    public void addSwimmer(Swimmer swimmer){
        listOfSwimmers.add(swimmer);
    }

    public void sortIsPaid() {
        Collections.sort(listOfSwimmers, new ComparatorIsPaid());

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        for (Swimmer swimmer : listOfSwimmers) {
            sb.append(swimmer).append("\n");
        }
        return sb.toString();
    }

}