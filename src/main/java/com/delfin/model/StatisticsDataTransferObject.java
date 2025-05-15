package delfin.model;


public class StatisticsDataTransferObject {

    private Swimmer compSwimmer;
    private Training relevantTimingSession;

    public StatisticsDataTransferObject(CompSwimmer compSwimmer, Training timingSession) {

        this.compSwimmer = compSwimmer;
        this.relevantTimingSession = timingSession;
    }

    public Swimmer getSwimmer() {
        return this.compSwimmer;
    }

    public Training getRelevantTimingSession() {
        return this.relevantTimingSession;
    }


}
