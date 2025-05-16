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
    private ArrayList<Competition> compTime;
    private ArrayList<Training> trainingTime;


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
    public List<Training> getTrainingTime() {
        return this.trainingTime;
    }

    @Override
    public List<Competition> getCompTime() {
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
    public Training getFastestTrainingBreastStroke() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public Duration getFastestTrainingTimeBackStroke() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Training getFastestTrainingBackstroke() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Duration getFastestTrainingTimeFreestyle() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public Training getFastestTrainingFreestyle() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public Duration getFastestTrainingTimeButterfly() {

        return getFastestTrainingTimeFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }

    @Override
    public Training getFastestTrainingButterfly() {

        return getFastestTrainingFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }


    public Duration getFastestTrainingTimeFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);

        for (Training training : trainingTime) {
            if (training.getDiscipline() == discipline && training.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = training.getTimeRegister();
            }
        }

        return tempDuration;
    }


    @Override
    public Training getFastestTrainingFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        Training tempTraining = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Training training : trainingTime) {
            if (training.getDiscipline() == discipline && training.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = training.getTimeRegister();
                tempTraining = training;
            }
        }
        return tempTraining;
    }


    @Override
    public Duration getFastestCompetitionTimeBreastStroke() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public Competition getFastestCompetitionBreastStroke() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BREASTSTROKE);
    }

    @Override
    public Duration getFastestCompetitionTimeBackstroke() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Competition getFastestCompetitionBackstroke() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BACKSTROKE);
    }

    @Override
    public Duration getFastestCompetitionTimeFreestyle() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }

    @Override
    public Competition getFastestCompetitionFreestyle() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.FREESTYLE);
    }


    @Override
    public Duration getFastestCompetitionTimeButterfly() {

        return getFastestCompetitionTimeFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }

    @Override
    public Competition getFastestCompetitionButterfly() {

        return getFastestCompetitionFromDiscipline(SwimmingDiscipline.BUTTERFLY);
    }


    @Override
    public Duration getFastestCompetitionTimeFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        Competition tempCompetition = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);

        for (Competition competition : compTime) {
            if (competition.getDiscipline() == discipline && competition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = competition.getTimeRegister();
            }
        }
        return tempDuration;
    }


    @Override
    public Competition getFastestCompetitionFromDiscipline(Enum<SwimmingDiscipline> discipline) {

        Competition tempCompetition = null;
        Duration tempDuration = Duration.ofSeconds(Long.MAX_VALUE);


        for (Competition competition : compTime) {
            if (competition.getDiscipline() == discipline && competition.getTimeRegister().compareTo(tempDuration) < 1) {
                tempDuration = competition.getTimeRegister();
                tempCompetition = competition;
            }
        }
        return tempCompetition;
    }


    //Metode der returnerer true hvis en swimmer er registreret med disciplin i enten delfin.model.Competition eller delfin.model.Training:
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
    public void addTrainingTime(Training training) {
        trainingTime.add(training);
    }

    @Override
    public void addCompTime(Competition comp) {
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
            compTime.sort(Comparator.comparing(Training::getDate));
            tempString.append("\n\nSvømmerens stævneresultater er følgende: " + compTime.toString());
        }
        if (trainingTime.isEmpty()) {
            tempString.append("\n\nSvømmeren har ingen registrerede træningstider\n");
        } else {
            trainingTime.sort(Comparator.comparing(Training::getDate));
            tempString.append("\nSvømmerens træningsresultater er følgende: " + trainingTime.toString() + "\n");
        }
        return tempString.toString();


    }


}
