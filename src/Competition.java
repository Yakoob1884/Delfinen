import java.time.Duration;
import java.time.LocalDate;

public class Competition {

    private String competitionName;
    private LocalDate date;
    private String location;
    private SwimmingDiscipline discipline;
    private Duration timeRegister;

    public Competition(String competitionTitle, LocalDate date, String location, SwimmingDiscipline disciplin, Duration timeRegister){

        this.competitionName = competitionTitle;
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


    public String toString(){
        return ("Stævnets info: \nNavn: " + competitionName + "\nDato: " + date + "\nSted: " + location + "\nDisciplin: " + discipline + "\nRegistreret tid: " + timeRegister);
    }


    //getter til discipline

    public SwimmingDiscipline getDiscipline() {
        return discipline;
    }





}
