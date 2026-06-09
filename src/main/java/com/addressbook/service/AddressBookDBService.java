package com.addressbook.service;

import com.addressbook.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int updatePersonContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber) {
        String sql = "UPDATE contacts SET address = ?, city = ?, state = ?, zip = ?, phone_number = ? WHERE first_name = ? AND last_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, address);
            pstmt.setString(2, city);
            pstmt.setString(3, state);
            pstmt.setString(4, zip);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, firstName);
            pstmt.setString(7, lastName);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB Update Error: " + e.getMessage());
            return 0;
        }
    }
}
