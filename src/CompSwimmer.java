import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.EnumSet;


public class CompSwimmer extends NonCompSwimmer implements CompetitionSwimmer {
    //Attribute

    EnumSet<SwimmingDiscipline> discipline;
    ArrayList<Competition> compTime;
    ArrayList<Training> trainingTime;


    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, List<Training> trainingTime, EnumSet<SwimmingDiscipline> enums) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = enums;
        this.compTime = new ArrayList<>();
        this.trainingTime = new ArrayList<>(trainingTime);
    }

    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, ArrayList<Competition> compTime, EnumSet<SwimmingDiscipline> enums) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = enums;
        this.compTime = new ArrayList<>(compTime);
        this.trainingTime = new ArrayList<>();
    }

    @Override
    public List<Training> getTrainingTime() {
        return this.trainingTime;
    }

    @Override
    public List<Competition> getCompTime() {
        return this.compTime;
    }

    @Override
    public Duration getFastestTrainingTimeBreastStroke() {


        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Training training : trainingTime) {
            if (training.getDiscipline() == SwimmingDiscipline.BREASTSTROKE && training.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = training.getTimeRegister();
            }
        }
    return tempDuration;

    }

    @Override
    public Duration getFastestCompetitionTimeBreastStroke() {

        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Competition competition : compTime) {
            if (competition.getDiscipline() == SwimmingDiscipline.BREASTSTROKE && competition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = competition.getTimeRegister();
            }
        }
        return tempDuration;

    }

    @Override
    public Training getFastestTrainingBreastStroke() {

        Training tempTraining = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Training training : trainingTime) {
            if (training.getDiscipline() == SwimmingDiscipline.BREASTSTROKE && training.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = training.getTimeRegister();
                tempTraining = training;
            }
        }
        return tempTraining;
    }

    @Override
    public Competition getFastestCompetitionBreastStroke() {

        Competition tempCompetition = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Competition competition : compTime) {
            if (competition.getDiscipline() == SwimmingDiscipline.BREASTSTROKE && competition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = competition.getTimeRegister();
                tempCompetition = competition;
            }
        }
        return tempCompetition;
    }








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

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append(String.format("%-20s  %-20s  %20s  %5b  %5b", this.lastName, this.firstName, this.birthday, this.isActive, this.isPaid));
        tempString.append(this.discipline);
        tempString.append(compTime.toString());
        tempString.append(trainingTime.toString());
        return tempString.toString();



    }








    }
