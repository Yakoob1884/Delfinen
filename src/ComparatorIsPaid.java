import java.util.Comparator;


public class ComparatorIsPaid implements Comparator<Swimmer> {

    @Override
    public int compare(Swimmer swimmer1, Swimmer swimmer2) {
        return Boolean.compare(swimmer1.getIsPaid(), swimmer2.getIsPaid());
    }

}
