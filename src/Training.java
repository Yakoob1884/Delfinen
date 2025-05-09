import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Training {

    private SwimmingDiscipline discipline;
    private LocalDate date;
    private Duration timeRegister;

    public Training(SwimmingDiscipline discipline, LocalDate date, Duration timeRegister) {

        this.discipline = discipline;
        this.date = date;
        this.timeRegister = timeRegister;
    }

    public String toString(){
        return ("Træningsinfo: \nTrænings type: " + discipline + "\nDato: " + date + "\nRegistreret tid: " + timeRegister);
    }


}
