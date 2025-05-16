package delfin.model;


public class StatisticsDataTransferObject {

    private Swimmer compSwimmer;
    private TimingTraining relevantTimingSession;

    public StatisticsDataTransferObject(CompSwimmer compSwimmer, TimingTraining timingSession) {

        this.compSwimmer = compSwimmer;
        this.relevantTimingSession = timingSession;
    }

    public Swimmer getSwimmer() {
        return this.compSwimmer;
    }

    public TimingTraining getRelevantTimingSession() {
        return this.relevantTimingSession;
    }


}
