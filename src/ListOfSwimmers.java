import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;


public class ListOfSwimmers {

    private ArrayList<Swimmer> listOfSwimmers;

    public ListOfSwimmers(){
        this.listOfSwimmers = new ArrayList<>();
    }

    public void addSwimmer(Swimmer swimmer){
        listOfSwimmers.add(swimmer);
    }

    public List<Swimmer> getSwimmersIsPaidFalseList() {

        List<Swimmer> listSwimmersIsPaidFalse = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (!swimmer.getIsPaid()) {
                listSwimmersIsPaidFalse.add(swimmer);
            }
        }

        return listSwimmersIsPaidFalse;

    }

    public List<Swimmer> getListOfAllSwimmers() {

        List<Swimmer> listOfAllSwimmers = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            listOfAllSwimmers.add(swimmer);
        }
        return listOfAllSwimmers;
    }

    public List<Swimmer> getCompSwimmersList() {
        List<Swimmer> compSwimmersList = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (swimmer instanceof CompSwimmer) {
                compSwimmersList.add(swimmer);
            }
        }
        return compSwimmersList;
    }



    public String toString() {
        StringBuilder sb = new StringBuilder("Listen af svømmere:\n");
        int id = 1;
        for (Swimmer swimmer : listOfSwimmers) {
            sb.append("ID " + id + ": " + swimmer).append("\n");
            id++;
        }
        return sb.toString();
    }

    public Swimmer getSwimmerByIndex(int index) {
        return listOfSwimmers.get(index);
    }

    public void dummieList() {

        listOfSwimmers.add(new NonCompSwimmer("Peter", "Petersen", LocalDate.of(1985, 5, 9), true, true));
        listOfSwimmers.add(new CompSwimmer("Anders", "Andersen", LocalDate.of(2001, 12, 12), false, true, new ArrayList<Training>(Arrays.asList(new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2024, 12, 17), Duration.parse("PT, 1M, 1S")),
                new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2024, 10, 17), Duration.parse("PT, 1M, 5S"))))));
        listOfSwimmers.add(new NonCompSwimmer("Niels", "Nielsen", LocalDate.of(1975, 1, 22), false, false));
        listOfSwimmers.add(new CompSwimmer("Jakob", "Jakobsen", LocalDate.of(2000, 3, 9), true, false, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 5, 9), Duration.parse("PT, 1M, 15S"))));
        listOfSwimmers.add(new NonCompSwimmer("Søren", "Sørensen", LocalDate.of(2012, 10, 17), false, true));
        listOfSwimmers.add(new NonCompSwimmer("Peder", "Pedersen", LocalDate.of(2017, 7, 29), true, true));
        listOfSwimmers.add(new CompSwimmer("Anna", "Mogensen", LocalDate.of(1999, 2, 19), true, false, new Training(SwimmingDiscipline.BACKSTROKE, LocalDate.of(2025, 5, 12), Duration.parse("PT, 1M, 3S"))));
        listOfSwimmers.add(new NonCompSwimmer("Karoline", "Ingemann", LocalDate.of(2000, 7, 7), false, true));
        listOfSwimmers.add(new CompSwimmer("Matilde", "Jensen", LocalDate.of(2000, 4, 16), true, false, new Training(SwimmingDiscipline.BUTTERFLY, LocalDate.of(2025, 5, 9), Duration.parse("PT, 1M, 7S"))));
        listOfSwimmers.add(new NonCompSwimmer("Thomas", "Anker", LocalDate.of(2020, 5, 12), true, false));
        listOfSwimmers.add(new NonCompSwimmer("Jenny", "Andersen", LocalDate.of(1989, 8, 3), false, true));
        listOfSwimmers.add(new CompSwimmer("Betina", "Hoffmann", LocalDate.of(1992, 3, 19), false, true, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2015, 1, 19), Duration.parse("PT, 0M, 57S"))));
        listOfSwimmers.add(new NonCompSwimmer("Lisbet", "Søndergaard", LocalDate.of(1972, 4, 5), true, true));
        listOfSwimmers.add(new CompSwimmer("Cirkeline", "Sommerfelt", LocalDate.of(2011, 6, 1), true, false, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 3, 19), Duration.parse("PT, 1M, 16S"))));
        listOfSwimmers.add(new CompSwimmer("Mikkel", "Østergaard", LocalDate.of(1998, 11, 15), false, false, new Training(SwimmingDiscipline.BUTTERFLY, LocalDate.of(2024, 11, 15), Duration.parse("PT, 1M, 2S"))));
        listOfSwimmers.add(new NonCompSwimmer("Alexander", "Aabech", LocalDate.of(1994, 12, 4), false, true));
        listOfSwimmers.add(new CompSwimmer("Magnus", "Jørgensen", LocalDate.of(1998, 9, 10), true, true, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 5, 17), Duration.parse("PT, 0M, 56S"))));
        listOfSwimmers.add(new CompSwimmer("Moskinen", "Morty", LocalDate.of(2015, 7, 11), true, true, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 3, 22), Duration.parse("PT, 0M, 56S"))));
        listOfSwimmers.add(new NonCompSwimmer("Jonah", "Baby", LocalDate.of(2017, 10, 16), false, false));
        listOfSwimmers.add(new CompSwimmer("Doofus", "Winther", LocalDate.of(2016, 12, 22), true, false, new Training(SwimmingDiscipline.BACKSTROKE, LocalDate.of(2024, 9, 14), Duration.parse("PT, 0M, 59S"))));
        listOfSwimmers.add(new CompSwimmer("Oskinen", "Olle", LocalDate.of(2016, 12, 23), false, true,  new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 5, 12), Duration.parse("PT, 1M, 13S"))));
        listOfSwimmers.add(new CompSwimmer("Solvej", "Weber", LocalDate.of(2004, 5, 18), true, true, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 2, 12), Duration.parse("PT, 1M, 22S"))));

        //Breast senior
        listOfSwimmers.add(new CompSwimmer("Caroline", "Pedersen", LocalDate.of(1999, 3, 29), true, false, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2024, 6, 23), Duration.parse("PT, 1M, 4S"))));
        listOfSwimmers.add(new CompSwimmer("Anker", "Jensen", LocalDate.of(1997, 7, 3), false, false, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 1, 2), Duration.parse("PT, 0M, 58S"))));

        //Breast junir
        listOfSwimmers.add(new CompSwimmer("Lars", "Larsen", LocalDate.of(2014, 9, 15), true, true, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 4, 15), Duration.parse("PT, 1M ,13S"))));
        listOfSwimmers.add(new CompSwimmer("Thomas", "Anker", LocalDate.of(2013, 10, 30), false, true, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 1, 24), Duration.parse("PT, 1M, 9S"))));
        listOfSwimmers.add(new CompSwimmer("Inger", "Jensen", LocalDate.of(2018, 9, 23), true, false, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2025, 3, 18), Duration.parse("PT, 1M, 23S"))));
        listOfSwimmers.add(new CompSwimmer("Maria", "Sørensen", LocalDate.of(2016, 12, 4), true, true, new Training(SwimmingDiscipline.BREASTSTROKE, LocalDate.of(2024, 12, 19), Duration.parse("PT, 1M, 19S"))));

        //Free sen
        listOfSwimmers.add(new CompSwimmer("Gertrud", "Madsen", LocalDate.of(1987, 6, 18), false, true, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 3, 12), Duration.parse("PT, 1M, 12S"))));
        listOfSwimmers.add(new CompSwimmer("Amanda", "Nygaard", LocalDate.of(2002, 7, 1), true, true, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2024, 11, 28), Duration.parse("PT, 0M, 59S"))));
        listOfSwimmers.add(new CompSwimmer("Mads", "Mikkelsen", LocalDate.of(1995, 8, 12), true, false, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 6, 1), Duration.parse("PT, 0M, 56S"))));

        //Free jun
        listOfSwimmers.add(new CompSwimmer("Niklas", "Eilsøe", LocalDate.of(2016, 3, 8), false, false, new Training(SwimmingDiscipline.FREESTYLE, LocalDate.of(2025, 2, 22), Duration.parse("PT, 1M, 3S"))));
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());

        //Back sen
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());

        //back jun
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());

        //Butt sen
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());

        //Butt jun
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());
        listOfSwimmers.add(new CompSwimmer());


    }

    public List<Swimmer> getSwimmersAtOrAboveAge(int age) {
        List<Swimmer> swimmersAtOrAboveAgeList = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (swimmer.calculateAge() >= age) {
                swimmersAtOrAboveAgeList.add(swimmer);
            }
        }

        return swimmersAtOrAboveAgeList;
    }

}