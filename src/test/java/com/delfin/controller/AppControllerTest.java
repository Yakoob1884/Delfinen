package delfin.controller;

import delfin.model.NonCompSwimmer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    @Test
    void calculateFee() {

        AppController controller = new AppController("collectionBackup.ser");

        //Test af en svømmer som er senior, men under 60 år, og aktivt medlem
        assertEquals(1600, controller.calculateFee(new NonCompSwimmer("Sofie", "Andersen", LocalDate.of(1973, 2, 12), true, true)));

        //Test af en svømmer som er senior over 60 år og aktivt medlem
        assertEquals(1200, controller.calculateFee(new NonCompSwimmer("Jens", "Hansen", LocalDate.of(1950, 11, 22), true, true)));

        //Test af en svømmer som er junior under 18 år og aktivt medlem
        assertEquals(1000, controller.calculateFee(new NonCompSwimmer("Gitte", "Petersen", LocalDate.of(2010, 7, 29), true, true)));




    }
}