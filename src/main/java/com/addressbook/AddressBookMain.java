package com.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program");

        Scanner scanner = new Scanner(System.in);

        Person person = createPerson(scanner);

        System.out.println("Person Added Successfully");
        System.out.println(person);

        System.out.print("Do you want to edit this person? yes/no: ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {

            System.out.println("Enter new details except name");

            System.out.print("Enter new address: ");
            person.setAddress(scanner.nextLine());

            System.out.print("Enter new city: ");
            person.setCity(scanner.nextLine());

            System.out.print("Enter new state: ");
            person.setState(scanner.nextLine());

            System.out.print("Enter new zip: ");
            person.setZip(scanner.nextLine());

            System.out.print("Enter new phone number: ");
            person.setPhoneNumber(scanner.nextLine());

            System.out.println("Person Updated Successfully");
            System.out.println(person);
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
