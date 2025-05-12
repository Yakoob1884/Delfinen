import java.util.Comparator;

public class ComparatorFastestTraining implements Comparator<CompSwimmer> {
    @Override
    public int compare(CompSwimmer swimmer1, CompSwimmer swimmer2) {
        return swimmer1.getFastestTimeTrainingDiscipline(SwimmingDiscipline.BREASTSTROKE).compareTo(swimmer2.getFastestTimeTrainingDiscipline(SwimmingDiscipline.BREASTSTROKE));

    }
}
