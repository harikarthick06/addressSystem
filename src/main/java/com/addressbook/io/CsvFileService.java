package com.addressbook.io;

import com.addressbook.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvFileService {

    private static final String FILE_PATH = "addressbook.csv";

    public void writeData(List<Person> persons) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {

            String[] header = {
                    "First Name",
                    "Last Name",
                    "Address",
                    "City",
                    "State",
                    "Zip",
                    "Phone Number"
            };

            writer.writeNext(header);

            for (Person person : persons) {

                String[] data = {
                        person.getFirstName(),
                        person.getLastName(),
                        person.getAddress(),
                        person.getCity(),
                        person.getState(),
                        person.getZip(),
                        person.getPhoneNumber()
                };

                writer.writeNext(data);
            }

            System.out.println("Data written to CSV file.");

        } catch (Exception exception) {
            System.out.println("CSV write error: " + exception.getMessage());
        }
    }

    public List<Person> readData() {

        List<Person> persons = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {

            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++) {

                String[] data = records.get(i);

                Person person = new Person(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6]
                );

                persons.add(person);
            }

            System.out.println("Data read from CSV file.");

        } catch (Exception exception) {
            System.out.println("CSV read error: " + exception.getMessage());
        }

        return persons;
    }
}
