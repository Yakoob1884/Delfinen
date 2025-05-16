package delfin.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.EnumSet;


public class CompSwimmer extends NonCompSwimmer implements CompetitionSwimmer, Serializable {

    @Serial
    private static final long serialVersionUID = 1505202502;

    private EnumSet<SwimmingDiscipline> discipline;
    private ArrayList<TimingCompetition> compTime;
    private ArrayList<TimingTraining> trainingTime;


    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, List<TimingTraining> trainingTime, EnumSet<SwimmingDiscipline> enums) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = enums;
        this.compTime = new ArrayList<>();
        this.trainingTime = new ArrayList<>(trainingTime);
    }

    public CompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid, ArrayList<TimingCompetition> compTime, EnumSet<SwimmingDiscipline> enums) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = enums;
        this.compTime = new ArrayList<>(compTime);
        this.trainingTime = new ArrayList<>();
    }

    public CompSwimmer(String firstName, String lastName, LocalDate birthday, EnumSet<SwimmingDiscipline> disciplines, boolean isActive, boolean isPaid) {
        super(firstName, lastName, birthday, isActive, isPaid);
        this.discipline = disciplines;
        this.trainingTime = new ArrayList<>();
        this.compTime = new ArrayList<>();
    }

    public CompSwimmer(NonCompSwimmer swimmer) {
        super(swimmer.getFirstName(), swimmer.getLastName(), swimmer.getBirthday(), swimmer.getIsActive(), swimmer.getIsPaid());
        this.discipline = EnumSet.noneOf(SwimmingDiscipline.class);
        this.compTime = new ArrayList<>();
        this.trainingTime = new ArrayList<>();
    }

    @Override
    public void setDiscipline (EnumSet<SwimmingDiscipline> enums) {
        this.discipline = enums;
    }

    @Override
    public List<TimingTraining> getTrainingTime() {
        return this.trainingTime;
    }

    @Override
    public List<TimingCompetition> getCompTime() {
        return this.compTime;
    }

    @Override
    public EnumSet<SwimmingDiscipline> getDiscipline() {
        return discipline;
    }

    @Override
    public Duration getFastestTrainingTimeBreastStroke() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public TimingTraining getFastestTrainingBreastStroke() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public Duration getFastestTrainingTimeBackStroke() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public TimingTraining getFastestTrainingBackstroke() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Duration getFastestTrainingTimeFreestyle() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public TimingTraining getFastestTrainingFreestyle() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public Duration getFastestTrainingTimeButterfly() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }

    @Override
    public TimingTraining getFastestTrainingButterfly() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }


    public Duration getFastestTrainingTimeFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);

        for (TimingTraining timingTraining : trainingTime) {
            if (timingTraining.getDiscipline() == discipline && timingTraining.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = timingTraining.getTimeRegister();
            }
        }

        return tempDuration;
    }


    @Override
    public TimingTraining getFastestTrainingFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        TimingTraining tempTimingTraining = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (TimingTraining timingTraining : trainingTime) {
            if (timingTraining.getDiscipline() == discipline && timingTraining.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = timingTraining.getTimeRegister();
                tempTimingTraining = timingTraining;
            }
        }
        return tempTimingTraining;
    }


    @Override
    public Duration getFastestCompetitionTimeBreastStroke() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public TimingCompetition getFastestCompetitionBreastStroke() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public Duration getFastestCompetitionTimeBackstroke() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public TimingCompetition getFastestCompetitionBackstroke() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Duration getFastestCompetitionTimeFreestyle() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public TimingCompetition getFastestCompetitionFreestyle() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }


    @Override
    public Duration getFastestCompetitionTimeButterfly() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }

    @Override
    public TimingCompetition getFastestCompetitionButterfly() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }


    @Override
    public Duration getFastestCompetitionTimeFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        TimingCompetition tempTimingCompetition = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);

        for (TimingCompetition timingCompetition : compTime) {
            if (timingCompetition.getDiscipline() == discipline && timingCompetition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = timingCompetition.getTimeRegister();
            }
        }
        return tempDuration;
    }


    @Override
    public TimingCompetition getFastestCompetitionFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        TimingCompetition tempTimingCompetition = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (TimingCompetition timingCompetition : compTime) {
            if (timingCompetition.getDiscipline() == discipline && timingCompetition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = timingCompetition.getTimeRegister();
                tempTimingCompetition = timingCompetition;
            }
        }
        return tempTimingCompetition;
    }


    //Metode der returnerer true hvis en swimmer er registreret med disciplin i enten delfin.model.TimingCompetition eller delfin.model.TimingTraining:
    public boolean hasResult(SwimmingDiscipline discipline) {


        for (TimingCompetition timingCompetition : compTime) {
            if (timingCompetition.getDiscipline().equals(discipline)) {
                return true;
            }
        }

        for (TimingTraining timingTraining : trainingTime) {
            if (timingTraining.getDiscipline().equals(discipline)) {
                return true;
            }
        }

        return false;

    }

    @Override
    public void addTrainingTime(TimingTraining timingTraining) {
        trainingTime.add(timingTraining);
    }

    @Override
    public void addCompTime(TimingCompetition comp) {
        compTime.add(comp);
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append(String.format("%-20s  %-20s  %20s", "Efternavn", "Fornavn", "Fødselsdag\n"));
        tempString.append(String.format("%-20s  %-20s  %19s", this.getLastName(), this.getFirstName(), this.getBirthday()));
        tempString.append("\n\nDeltager i følgende discipliner:" + this.discipline);
        if (compTime.isEmpty()) {
            tempString.append("\n\nSvømmeren har ingen registrerede konkurrencetider\n");
        } else {
            compTime.sort(Comparator.comparing(TimingTraining::getDate));
            tempString.append("\n\nSvømmerens stævneresultater er følgende: " + compTime.toString());
        }
        if (trainingTime.isEmpty()) {
            tempString.append("\n\nSvømmeren har ingen registrerede træningstider\n");
        } else {
            trainingTime.sort(Comparator.comparing(TimingTraining::getDate));
            tempString.append("\nSvømmerens træningsresultater er følgende: " + trainingTime.toString() + "\n");
        }
        return tempString.toString();


    }


}
