import java.time.Duration;
import java.time.LocalDate;

public class Competition {

    private String competitionName;
    private LocalDate date;
    private String location;
    private SwimmingDiscipline discipline;
    private Duration timeRegister;

    public Competition(String competitionTitel, LocalDate date, String location, SwimmingDiscipline discipline, Duration timeRegister){

        this.competitionName = competitionTitel;
        this.date = date;
        this.location = location;
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
        return ("\nStævnets info: \nNavn: " + competitionName + "\nDato: " + date + "\nSted: " + location + "\nDisciplin: " + discipline + "\nRegistreret tid: " + timeRegister + "\n");
    }





}
