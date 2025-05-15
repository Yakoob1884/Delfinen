package delfin.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class Competition extends Training implements Serializable {

    private String competitionName;
    //private LocalDate date;
    private int ranking;
    //private SwimmingDiscipline discipline;
    //private Duration timeRegister;

    public Competition(String competitionTitel, LocalDate date, int ranking, SwimmingDiscipline discipline, Duration timeRegister){

        super(discipline, date, timeRegister);
        this.competitionName = competitionTitel;
        //this.date = date;
        this.ranking = ranking;
        //this.discipline = discipline;
        //this.timeRegister = timeRegister;

    }

//    public Duration getTimeRegister() {
//        return this.timeRegister;
//    }
//
//    public SwimmingDiscipline getDiscipline() {
//        return this.discipline;
//    }
//
//    public LocalDate getDate() {
//        return this.date;
//
//    }

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
