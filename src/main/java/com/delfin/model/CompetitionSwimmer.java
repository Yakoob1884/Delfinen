package delfin.model;

import java.time.Duration;
import java.util.List;

public interface CompetitionSwimmer extends Swimmer {


    List<Competition> getCompTime();

    List<Training> getTrainingTime();

    Duration getFastestTimeCompetitionDiscipline(SwimmingDiscipline discipline);
    Duration getFastestTimeTrainingDiscipline(SwimmingDiscipline discipline);

    boolean hasResult(SwimmingDiscipline discipline);

    Duration getFastestTrainingTimeBreastStroke();
    Training getFastestTrainingBreastStroke();

    Duration getFastestCompetitionTimeBreastStroke();

    Competition getFastestCompetitionBreastStroke();





}
