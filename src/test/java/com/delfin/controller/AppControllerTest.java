package delfin.controller;

import delfin.model.NonCompSwimmer;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    @Test
    void calculateFee() {
        Scanner scanner = new Scanner(System.in);

        AppController controller = new AppController("collectionBackup.ser", scanner);

        //Test af en svømmer som er senior, men under 60 år, og aktivt medlem
        assertEquals(1600, controller.calculateFee(new NonCompSwimmer("Sofie", "Andersen", LocalDate.of(1973, 2, 12), true, true)));

        //Test af en svømmer som er senior over 60 år og aktivt medlem
        assertEquals(1200, controller.calculateFee(new NonCompSwimmer("Jens", "Hansen", LocalDate.of(1950, 11, 22), true, true)));

        //Test af en svømmer som er junior under 18 år og aktivt medlem
        assertEquals(1000, controller.calculateFee(new NonCompSwimmer("Gitte", "Petersen", LocalDate.of(2012, 7, 11), true, true)));

        //Test af en svømmer som lige akkurat er fyldt 18 og aktivt medlem
        assertEquals(1600, controller.calculateFee(new NonCompSwimmer("Janus", "Janusen", LocalDate.of(2007, 5, 10), true, true)));

        //Test af en svømmer som lige akkurat IKKE er fyld 18 og aktivt medlem
        assertEquals(1000, controller.calculateFee(new NonCompSwimmer("Silke", "Silkesen", LocalDate.of(2007, 5, 25), true, true)));

        //Test af en søvmmer som er passivt medlem
        assertEquals(500, controller.calculateFee(new NonCompSwimmer("Olivia", "Oliviasen", LocalDate.of(1973, 2, 12), false, true)));

    }
}