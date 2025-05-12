import java.util.List;

public interface CompetitionSwimmer extends Swimmer {

    List<Training> getTrainingTime();
    List<Competition> getCompTime();

}
