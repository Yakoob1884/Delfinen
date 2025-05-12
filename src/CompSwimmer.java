import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

public class CompSwimmer extends NonCompSwimmer implements CompetitionSwimmer {
    //Attribute

    EnumSet<SwimmingDiscipline> discipline;
    ArrayList<Competition> compTime;
    Training trainingTime;


    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, Training trainingTime) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = EnumSet.noneOf(SwimmingDiscipline.class);
        this.compTime = new ArrayList<>();
        this.trainingTime = trainingTime;
    }

    @Override
    public Training getTrainingTime() {
        return this.trainingTime;
    }

    @Override
    public List<Competition> getCompTime() {
        return this.compTime;
    }

    @Override
    public boolean hasResults(SwimmingDiscipline discipline) {
        for (Competition competition : compTime) {
    }

}
