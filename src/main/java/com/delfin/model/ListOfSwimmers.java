package delfin.model;

import delfin.utils.FileObjectOutputInput;

import java.io.IOException;
import java.util.*;


public class ListOfSwimmers {

    private final FileObjectOutputInput listOfSwimmersFile;

    private ArrayList<Swimmer> listOfSwimmers;


    public ListOfSwimmers(String filename) throws IOException, ClassNotFoundException {


        this.listOfSwimmers = new ArrayList<>();
        this.listOfSwimmersFile = new FileObjectOutputInput(filename);
        this.listOfSwimmers = (ArrayList<Swimmer>) listOfSwimmersFile.readSwimmerListFromFile();

    }

    public void addSwimmer(Swimmer swimmer) {
        listOfSwimmers.add(swimmer);
    }

    public void removeSwimmer(Swimmer swimmer) {
        listOfSwimmers.remove(swimmer);

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

    public List<Swimmer> getPassiveSwimmers() {
        List<Swimmer> listSwimmersIsPassive = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (!swimmer.getIsActive()) {
                listSwimmersIsPassive.add(swimmer);
            }
        }

        return listSwimmersIsPassive;
    }

    public List<Swimmer> getActiveSwimmers() {
        List<Swimmer> listSwimmersIsActive = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (swimmer.getIsActive()) {
                listSwimmersIsActive.add(swimmer);
            }
        }

        return listSwimmersIsActive;
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

    public List<Swimmer> getNonCompSwimmersList() {
        List<Swimmer> nonCompSwimmersList = new ArrayList<>();

        for (Swimmer swimmer : listOfSwimmers) {
            if (!(swimmer instanceof CompSwimmer)) {
                nonCompSwimmersList.add(swimmer);
            }
        }
        return nonCompSwimmersList;
    }


    public void saveListOfSwimmersToFile() throws IOException {
        listOfSwimmersFile.writeSwimmerListToFile(listOfSwimmers);

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
}