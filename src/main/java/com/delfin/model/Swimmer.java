package delfin.model;

import java.time.LocalDate;


public interface Swimmer extends Comparable<Swimmer> {

    boolean getIsPaid();
    String getLastName();
    String getFirstName();
    LocalDate getBirthday();
    boolean getIsActive();
    int calculateAge();
}
