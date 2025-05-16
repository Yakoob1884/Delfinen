package delfin.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class TimingTraining implements Serializable {

    @Serial
    private static final long serialVersionUID = 1505202503;

    private SwimmingDiscipline discipline;
    private LocalDate date;
    private Duration timeRegister;

    public TimingTraining(SwimmingDiscipline discipline, LocalDate date, Duration timeRegister) {

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
    public String toString() {
        long minutes = timeRegister.toMinutes();
        int seconds = timeRegister.toSecondsPart();
        return "\nTræningsresultat:" +
                "\nDisciplin: " + discipline +
                "\nDato: " + date +
                "\nRegistreret tid: " + minutes + " min " + seconds + " sek\n";
    }


}
