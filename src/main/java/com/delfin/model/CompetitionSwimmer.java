package delfin.model;

import java.time.Duration;
import java.util.EnumSet;
import java.util.List;

public interface CompetitionSwimmer extends Swimmer {

    List<TimingCompetition> getCompTime();
    List<TimingTraining> getTrainingTime();

    Duration getFastestTrainingTimeFromDiscipline(Enum<SwimmingDiscipline> discipline);
    TimingTraining getFastestTrainingFromDiscipline(Enum<SwimmingDiscipline> discipline);

    Duration getFastestCompetitionTimeFromDiscipline(Enum<SwimmingDiscipline> discipline);
    TimingCompetition getFastestCompetitionFromDiscipline(Enum<SwimmingDiscipline> discipline);

    EnumSet<SwimmingDiscipline> getDiscipline();
    void setDiscipline(EnumSet<SwimmingDiscipline> enums);




    Duration getFastestTrainingTimeBreastStroke();
    Duration getFastestTrainingTimeBackStroke();
    Duration getFastestTrainingTimeFreestyle();
    Duration getFastestTrainingTimeButterfly();

    TimingTraining getFastestTrainingBreastStroke();
    TimingTraining getFastestTrainingBackstroke();
    TimingTraining getFastestTrainingFreestyle();
    TimingTraining getFastestTrainingButterfly();

    Duration getFastestCompetitionTimeBreastStroke();
    Duration getFastestCompetitionTimeBackstroke();
    Duration getFastestCompetitionTimeFreestyle();
    Duration getFastestCompetitionTimeButterfly();

    TimingCompetition getFastestCompetitionBreastStroke();
    TimingCompetition getFastestCompetitionBackstroke();
    TimingCompetition getFastestCompetitionFreestyle();
    TimingCompetition getFastestCompetitionButterfly();







    void addTrainingTime(TimingTraining timingTraining);

    void addCompTime(TimingCompetition comp);





}
