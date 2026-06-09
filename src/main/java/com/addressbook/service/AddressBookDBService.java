package com.addressbook.service;

import com.addressbook.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    public List<Person> readData() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Person person = new Person(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("zip"),
                    resultSet.getString("phone_number")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Database Read Error: " + e.getMessage());
        }
        return persons;
    }
}
