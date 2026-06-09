package com.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program");

        Scanner scanner = new Scanner(System.in);

        Person person = createPerson(scanner);

        System.out.println("Person Added Successfully");
        System.out.println(person);

        System.out.print("Enter first name to delete: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name to delete: ");
        String lastName = scanner.nextLine();

        if (person.getFirstName().equalsIgnoreCase(firstName)
                && person.getLastName().equalsIgnoreCase(lastName)) {

            person = null;
            System.out.println("Person Deleted Successfully");

        } else {
            System.out.println("Person Not Found");
        }
    }

    private static Person createPerson(Scanner scanner) {

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter state: ");
        String state = scanner.nextLine();

        System.out.print("Enter zip: ");
        String zip = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        return new Person(firstName, lastName, address, city, state, zip, phoneNumber);
    }
}
