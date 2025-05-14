package delfin.model;

import java.time.Duration;
import java.time.LocalDate;

public class Competition {

    private String competitionName;
    private LocalDate date;
    private int ranking;
    private SwimmingDiscipline discipline;
    private Duration timeRegister;

    public Competition(String competitionTitel, LocalDate date, int ranking, SwimmingDiscipline discipline, Duration timeRegister){

        this.competitionName = competitionTitel;
        this.date = date;
        this.ranking = ranking;
        this.discipline = discipline;
        this.timeRegister = timeRegister;

    }

    public Duration getTimeRegister() {
        return this.timeRegister;
    }

    public SwimmingDiscipline getDiscipline() {
        return this.discipline;
    }

    public LocalDate getDate() {
        return this.date;

    }

    public String getCompetitionName() {
        return this.competitionName;
    }

    public String toString(){
        return ("\nStævnets info: \nNavn: " + competitionName + "\nDato: " + date + "\nPlacering: " + ranking + "\nDisciplin: " + discipline + "\nRegistreret tid: " + timeRegister + "\n");
    }





}
