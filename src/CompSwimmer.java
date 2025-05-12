import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

public class CompSwimmer extends NonCompSwimmer implements CompetitionSwimmer {
    //Attribute

    EnumSet<SwimmingDiscipline> discipline;
    ArrayList<Competition> compTime;
    ArrayList<Training> trainingTime;


    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, ArrayList<Training> trainingTime) {
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

//    @Override
//    public boolean hasResults(SwimmingDiscipline discipline) {
//        for (Competition competition : compTime) {
//        }
//    }



    @Override
    public Duration getFastestTimeCompetitionDiscipline(SwimmingDiscipline discipline) {

        Duration fastest = Duration.parse("PT0H0M0S");

        if (compTime.isEmpty()) {
            return fastest;
        }

        for (Competition competition : compTime) {
            if (competition.getTimeRegister().compareTo(fastest) < 0) {
                fastest = competition.getTimeRegister();
            }
        }

        return fastest;
    }

    @Override
    public Duration getFastestTimeTrainingDiscipline(SwimmingDiscipline discipline) {

        Duration fastest = Duration.parse("PT0H0M0S");

        if (trainingTime.isEmpty()) {
            return fastest;
        }

        for (Training training : trainingTime) {
            if (training.getTimeRegister().compareTo(fastest) < 0) {
                fastest = training.getTimeRegister();
            }
        }

        return fastest;
    }

    //Metode der returnerer true hvis en swimmer er registreret med disciplin i enten Competition eller Training:

    public boolean hasResult(SwimmingDiscipline discipline) {


        for (Competition competition : compTime) {
            if (competition.getDiscipline().equals(discipline)) {
                return true;
            }
        }

        for (Training training : trainingTime) {
            if (training.getDiscipline().equals(discipline)) {
                return true;
            }
        }

        return false;

    }








    }
