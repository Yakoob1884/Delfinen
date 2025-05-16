package delfin.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class TimingCompetition extends TimingTraining implements Serializable {

    @Serial
    private static final long serialVersionUID = 1505202504;

    private String competitionName;
    private int ranking;

    public TimingCompetition(String competitionTitel, LocalDate date, int ranking, SwimmingDiscipline discipline, Duration timeRegister){

        super(discipline, date, timeRegister);
        this.competitionName = competitionTitel;
        this.ranking = ranking;
    }

    public String getCompetitionName() {
        return this.competitionName;
    }

    public int getRanking() {
        return this.ranking;
    }

    public String toString(){
        long minutes = getTimeRegister().toMinutes();
        int seconds = getTimeRegister().toSecondsPart();
        return "\nStævnets info:" +
                "\nNavn " + competitionName +
                "\nDato: " + getDate() +
                "\nPlacering: " + ranking +
                "\nDisciplin: " + getDiscipline() +
                "\nRegistreret tid: " + minutes + " min " + seconds + " sek\n";
    }





}
