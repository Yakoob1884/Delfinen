import java.time.Duration;
import java.time.LocalDate;

public class Competition {

    private String competitionName;
    private LocalDate date;
    private String location;
    private SwimmingDisciplin disciplin;
    private Duration timeRegister;

    public Competition(String competitionTitel, LocalDate date, String location, SwimmingDisciplin disciplin, Duration timeRegister){

        this.competitionName = competitionTitel;
        this.date = date;
        this.location = location;
        this.disciplin = disciplin;
        this.timeRegister = timeRegister;

    }

    public String toString(){
        return ("Stævnets info: \nNavn: " + competitionName + "\nDato: " + date + "\nSted: " + location + "\nDisciplin: " + disciplin + "\nRegistreret tid: " + timeRegister);
    }





}
