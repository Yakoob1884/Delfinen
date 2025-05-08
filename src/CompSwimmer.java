import java.time.LocalDate;

public class CompSwimmer extends NonCompSwimmer implements CompetitionSwimmer{
    //Attribute


    public CompSwimmer (String firstName, String lastName, LocalDate birthday, boolean isActive, boolean isPaid){
        super(firstName, lastName, birthday, isActive, isPaid);

    }

}
