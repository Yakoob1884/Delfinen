import java.time.Duration;
import java.time.LocalDate;

public class Competition {

    private String competitionName;
    private LocalDate date;
    private String location;
    private SwimmingDiscipline disciplin;
    private Duration timeRegister;

    public Competition(String competitionTitel, LocalDate date, String location, SwimmingDiscipline disciplin, Duration timeRegister){

        this.competitionName = competitionTitel;
        this.date = date;
        this.location = location;
        this.disciplin = disciplin;
        this.timeRegister = timeRegister;

    }

    public Duration getTimeRegister() {
        return this.timeRegister;
    }

    public SwimmingDiscipline getDiscipline() {
        return this.discipline;
    }


    public String toString(){
        return ("Stævnets info: \nNavn: " + competitionName + "\nDato: " + date + "\nSted: " + location + "\nDisciplin: " + disciplin + "\nRegistreret tid: " + timeRegister);
    }





}
