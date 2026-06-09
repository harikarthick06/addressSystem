package com.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program");

        Scanner scanner = new Scanner(System.in);

        List<Person> addressBook = new ArrayList<>();

        System.out.print("How many persons do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {

            System.out.println("Enter details for person " + i);
            Person person = createPerson(scanner);

            boolean duplicate = addressBook.stream()
                    .anyMatch(existingPerson -> existingPerson.equals(person));

            if (duplicate) {
                System.out.println("Duplicate Person Found. Person Not Added.");
            } else {
                addressBook.add(person);
                System.out.println("Person Added Successfully.");
            }
        }

        System.out.println("1. Search Persons By City");
        System.out.println("2. Search Persons By State");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {

            System.out.print("Enter city: ");
            String city = scanner.nextLine();

            addressBook.stream()
                    .filter(person -> person.getCity().equalsIgnoreCase(city))
                    .forEach(System.out::println);

        } else if (choice == 2) {

            System.out.print("Enter state: ");
            String state = scanner.nextLine();

            addressBook.stream()
                    .filter(person -> person.getState().equalsIgnoreCase(state))
                    .forEach(System.out::println);

        } else {
            System.out.println("Invalid Choice");
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
