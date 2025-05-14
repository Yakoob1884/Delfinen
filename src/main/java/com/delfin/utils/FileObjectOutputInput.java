package delfin.utils;

import delfin.model.Swimmer;

import java.io.*;
import java.util.*;


public class FileObjectOutputInput {

    private final String FILENAME;

    public FileObjectOutputInput(String filename) {
        this.FILENAME = filename;
    }

    public void writeSwimmerListToFile(List<Swimmer> listOfSwimmers) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(listOfSwimmers);

        objectOutputStream.close();

        fileOutputStream.close();

    }

    public List<Swimmer> readSwimmerListFromFile() throws IOException, ClassNotFoundException {

        List<Swimmer> listOfSwimmers = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(FILENAME);

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);



        listOfSwimmers = (ArrayList<Swimmer>) objectInputStream.readObject();

        return listOfSwimmers;

    }

}
