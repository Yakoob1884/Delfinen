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

    public SwimmingDiscipline getDiscipline() {
        return this.discipline;
    }

    public Duration getTimeRegister() {
        return this.timeRegister;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString(){
        return ("\nTræningsinfo: \nTrænings type: " + discipline + "\nDato: " + date + "\nRegistreret tid: " + timeRegister + "\n");
    }


}
