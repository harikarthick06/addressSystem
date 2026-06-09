package com.addressbook;

import com.addressbook.io.CsvFileService;
import com.addressbook.io.ManualJsonFileService;
import com.addressbook.service.AddressBookService;

import java.util.Scanner;

public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AddressBookService addressBookService = new AddressBookService();
    private static final ManualJsonFileService manualJsonFileService = new ManualJsonFileService();
    private static final CsvFileService csvFileService = new CsvFileService();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Address Book Menu ---");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person");
            System.out.println("3. Delete Person");
            System.out.println("4. View All Persons");
            System.out.println("5. Write using Manual JSON File Handler");
            System.out.println("6. Read using Manual JSON File Handler");
            System.out.println("7. Write using CSV File Handler");
            System.out.println("8. Read using CSV File Handler");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        Person person = createPerson();
                        addressBookService.addPerson(person);
                    }
                    case 2 -> {
                        System.out.print("Enter first name to edit: ");
                        String fn = scanner.nextLine();
                        System.out.print("Enter last name to edit: ");
                        String ln = scanner.nextLine();
                        addressBookService.editPerson(fn, ln, scanner);
                    }
                    case 3 -> {
                        System.out.print("Enter first name to delete: ");
                        String fn = scanner.nextLine();
                        System.out.print("Enter last name to delete: ");
                        String ln = scanner.nextLine();
                        addressBookService.deletePerson(fn, ln);
                    }
                    case 4 -> addressBookService.displayPersons(addressBookService.getAllPersons());
                    case 5 -> manualJsonFileService.writeData(addressBookService.getAllPersons());
                    case 6 -> manualJsonFileService.readData();
                    case 7 -> csvFileService.writeData(addressBookService.getAllPersons());
                    case 8 -> addressBookService.setAllPersons(csvFileService.readData());
                    case 9 -> exit = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static Person createPerson() {
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
