package com.addressbook.io;

import com.addressbook.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonJsonFileService {

    private static final String FILE_PATH = "addressbook_gson.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeData(List<Person> persons) {

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            gson.toJson(persons, writer);

            System.out.println("Data written to JSON file using GSON.");

        } catch (Exception exception) {
            System.out.println("GSON write error: " + exception.getMessage());
        }
    }

    public List<Person> readData() {

        List<Person> persons = new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            Type personListType = new TypeToken<ArrayList<Person>>() {
            }.getType();

            persons = gson.fromJson(reader, personListType);

            if (persons == null) {
                persons = new ArrayList<>();
            }

            System.out.println("Data read from JSON file using GSON.");

        } catch (Exception exception) {
            System.out.println("GSON read error: " + exception.getMessage());
        }

        return persons;
    }
}
