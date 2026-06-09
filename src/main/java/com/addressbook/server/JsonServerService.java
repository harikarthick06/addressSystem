package com.addressbook.server;

import com.addressbook.Person;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class JsonServerService {

    private static final String BASE_URL = "http://localhost:3000/persons";

    public void addPersonToServer(Person person) {

        given()
                .header("Content-Type", "application/json")
                .body(person)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201);

        System.out.println("Person saved to JSON Server.");
    }

    public List<Person> readPersonsFromServer() {

        Person[] persons = get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .as(Person[].class);

        System.out.println("Persons read from JSON Server.");

        return Arrays.asList(persons);
    }
}
