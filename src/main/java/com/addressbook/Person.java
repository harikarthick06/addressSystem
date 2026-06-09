package com.addressbook;

public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public Person(String firstName, String lastName, String address,
                  String city, String state, String zip, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Name: " + getFullName()
                + ", Address: " + address
                + ", City: " + city
                + ", State: " + state
                + ", Zip: " + zip
                + ", Phone: " + phoneNumber;
    }
}
