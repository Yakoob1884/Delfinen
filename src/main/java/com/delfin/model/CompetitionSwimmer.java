package delfin.model;

import java.time.Duration;
import java.util.EnumSet;
import java.util.List;

public interface CompetitionSwimmer extends Swimmer {

    List<Competition> getCompTime();
    List<Training> getTrainingTime();

    Duration getFastestTrainingTimeFromDiscipline(Enum<SwimmingDiscipline> discipline);
    Training getFastestTrainingFromDiscipline(Enum<SwimmingDiscipline> discipline);

    Duration getFastestCompetitionTimeFromDiscipline(Enum<SwimmingDiscipline> discipline);
    Competition getFastestCompetitionFromDiscipline(Enum<SwimmingDiscipline> discipline);

    EnumSet<SwimmingDiscipline> getDiscipline();
    void setDiscipline(EnumSet<SwimmingDiscipline> enums);


    boolean hasResult(SwimmingDiscipline discipline);

    Duration getFastestTrainingTimeBreastStroke();
    Duration getFastestTrainingTimeBackStroke();
    Duration getFastestTrainingTimeFreestyle();
    Duration getFastestTrainingTimeButterfly();

    Training getFastestTrainingBreastStroke();
    Training getFastestTrainingBackstroke();
    Training getFastestTrainingFreestyle();
    Training getFastestTrainingButterfly();

    Duration getFastestCompetitionTimeBreastStroke();
    Duration getFastestCompetitionTimeBackstroke();
    Duration getFastestCompetitionTimeFreestyle();
    Duration getFastestCompetitionTimeButterfly();

    Competition getFastestCompetitionBreastStroke();
    Competition getFastestCompetitionBackstroke();
    Competition getFastestCompetitionFreestyle();
    Competition getFastestCompetitionButterfly();







    void addTrainingTime(Training training);

    void addCompTime(Competition comp);





}
