package delfin.model;

public enum SwimmingDiscipline {
    FREESTYLE("Crawl"),
    BACKSTROKE("Rygsvømning"),
    BREASTSTROKE("Brystsvømning"),
    BUTTERFLY("Butterfly");

    private final String swimmingDisciplineDK;

    SwimmingDiscipline(String swimmingDisciplineDK) {
        this.swimmingDisciplineDK = swimmingDisciplineDK;
    }

    @Override
    public String toString() {
        return swimmingDisciplineDK;
    }
}
