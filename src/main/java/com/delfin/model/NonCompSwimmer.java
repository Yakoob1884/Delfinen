package delfin.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class NonCompSwimmer implements Swimmer, Serializable {

    @Serial
    private static final long serialVersionUID = 1505202501;

    //Attributes
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean isActive;
    private boolean isPaid;


    public NonCompSwimmer(String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.isActive = isActive;
        this.isPaid = isPaid;
    }

    @Override
    public boolean getIsPaid() {
        return isPaid;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public LocalDate getBirthday(){ return birthday; }

    @Override
    public boolean getIsActive(){ return isActive; }

    @Override
    public void setIsPaid(boolean statusPaid){
        this.isPaid = statusPaid;
    }

    @Override
    public int calculateAge () {
        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(birthday, currentDate);

        return period.getYears();
    }

    @Override
    public int compareTo(Swimmer otherSwimmer) {
        if (lastName.equalsIgnoreCase(otherSwimmer.getLastName()) && firstName.equalsIgnoreCase(otherSwimmer.getFirstName())) {
            //return otherSwimmer.getBirthday().compareTo(this.getBirthday());
            return this.getBirthday().compareTo(otherSwimmer.getBirthday());
        }
        if (lastName.equalsIgnoreCase(otherSwimmer.getLastName())) {
            return firstName.compareToIgnoreCase(otherSwimmer.getFirstName());
        }

        return lastName.compareToIgnoreCase(otherSwimmer.getLastName());

    }

    @Override
    public String toString() {
        return String.format("%-20s  %-20s  %20s  %5b  %5b", this.lastName, this.firstName, this.birthday, this.isActive, this.isPaid);

    }
}
