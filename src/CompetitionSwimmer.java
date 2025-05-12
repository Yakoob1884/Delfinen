import java.util.List;

public interface CompetitionSwimmer extends Swimmer {

    Training getTrainingTime();
    List<Competition> getCompTime();

}
