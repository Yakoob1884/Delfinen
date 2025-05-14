package delfin.utils;

import delfin.model.Swimmer;

import java.io.*;
import java.util.*;


public class FileObjectOutputInput {

    private final String FILENAME;

    public FileObjectOutputInput(String filename) {
        this.FILENAME = filename;
    }

    public void WriteSwimmerListToFile(List<Swimmer> listOfSwimmers) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(listOfSwimmers);

        objectOutputStream.close();

        fileOutputStream.close();

    }

}
