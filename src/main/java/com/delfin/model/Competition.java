package delfin.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class Competition extends Training implements Serializable {

    private String competitionName;
    private int ranking;

    public Competition(String competitionTitel, LocalDate date, int ranking, SwimmingDiscipline discipline, Duration timeRegister){

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
        return ("\nStævnets info: \nNavn: " + competitionName + "\nDato: " + getDate() + "\nPlacering: " + ranking + "\nDisciplin: " + getDiscipline() + "\nRegistreret tid: " + getTimeRegister() + "\n");
    }





}
