import java.time.LocalDate;

public class NonCompSwimmer implements Swimmer {
        //Attributes
    String firstName;
    String lastName;
    LocalDate birthday;
    boolean isActive;
    boolean isPaid;



    public NonCompSwimmer (String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.isActive = isActive;
        this.isPaid = isPaid;
    }

}
