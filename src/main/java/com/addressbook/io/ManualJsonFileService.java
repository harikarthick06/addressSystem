package com.addressbook.io;

import com.addressbook.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManualJsonFileService {

    private static final String FILE_PATH = "addressbook_manual.json";

    public void writeData(List<Person> persons) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            writer.write("[\n");

            for (int i = 0; i < persons.size(); i++) {

                Person person = persons.get(i);

                writer.write("  {\n");
                writer.write("    \"firstName\": \"" + person.getFirstName() + "\",\n");
                writer.write("    \"lastName\": \"" + person.getLastName() + "\",\n");
                writer.write("    \"address\": \"" + person.getAddress() + "\",\n");
                writer.write("    \"city\": \"" + person.getCity() + "\",\n");
                writer.write("    \"state\": \"" + person.getState() + "\",\n");
                writer.write("    \"zip\": \"" + person.getZip() + "\",\n");
                writer.write("    \"phoneNumber\": \"" + person.getPhoneNumber() + "\"\n");
                writer.write("  }");

                if (i < persons.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]");

            System.out.println("Data written using Java File Handler.");

        } catch (IOException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    public List<Person> readData() {
        // Will be implemented in UC 11
        return new ArrayList<>();
    }
}
