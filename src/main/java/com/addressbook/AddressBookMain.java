package com.addressbook;

import com.addressbook.io.CsvFileService;
import com.addressbook.io.GsonJsonFileService;
import com.addressbook.io.ManualJsonFileService;
import com.addressbook.server.JsonServerService;
import com.addressbook.service.AddressBookService;
import com.addressbook.service.AsyncIOService;

import java.util.List;
import java.util.Scanner;

public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AddressBookService addressBookService = new AddressBookService();
    private static final ManualJsonFileService manualJsonFileService = new ManualJsonFileService();
    private static final CsvFileService csvFileService = new CsvFileService();
    private static final GsonJsonFileService gsonJsonFileService = new GsonJsonFileService();
    private static final JsonServerService jsonServerService = new JsonServerService();
    private static final AsyncIOService asyncIOService = new AsyncIOService();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Address Book Menu ---");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person");
            System.out.println("3. Delete Person");
            System.out.println("4. View All Persons");
            System.out.println("5. Search Persons by City");
            System.out.println("6. Search Persons by State");
            System.out.println("7. View Persons by City (from Dictionary)");
            System.out.println("8. View Persons by State (from Dictionary)");
            System.out.println("9. Sort Persons");
            System.out.println("10. Write using Manual JSON File Handler");
            System.out.println("11. Read using Manual JSON File Handler");
            System.out.println("12. Write using CSV File Handler");
            System.out.println("13. Read using CSV File Handler");
            System.out.println("14. Write using GSON File Handler");
            System.out.println("15. Read using GSON File Handler");
            System.out.println("16. Save Person to JSON Server (REST Assured)");
            System.out.println("17. Read Persons from JSON Server (REST Assured)");
            System.out.println("18. Write to CSV Async");
            System.out.println("19. Read from CSV Async");
            System.out.println("20. Write to GSON Async");
            System.out.println("21. Read from GSON Async");
            System.out.println("22. Save to JSON Server Async");
            System.out.println("23. Read from JSON Server Async");
            System.out.println("24. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        Person person = createPerson(scanner);
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
                    case 5 -> {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine();
                        addressBookService.displayPersons(addressBookService.searchPersonsByCity(city));
                    }
                    case 6 -> {
                        System.out.print("Enter state: ");
                        String state = scanner.nextLine();
                        addressBookService.displayPersons(addressBookService.searchPersonsByState(state));
                    }
                    case 7 -> {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine();
                        addressBookService.displayPersons(addressBookService.viewPersonsByCity(city));
                    }
                    case 8 -> {
                        System.out.print("Enter state: ");
                        String state = scanner.nextLine();
                        addressBookService.displayPersons(addressBookService.viewPersonsByState(state));
                    }
                    case 9 -> {
                        System.out.println("Sort by: 1. Name, 2. City, 3. State, 4. Zip");
                        int sortChoice = Integer.parseInt(scanner.nextLine());
                        List<Person> sorted = switch (sortChoice) {
                            case 1 -> addressBookService.sortByName();
                            case 2 -> addressBookService.sortByCity();
                            case 3 -> addressBookService.sortByState();
                            case 4 -> addressBookService.sortByZip();
                            default -> null;
                        };
                        if (sorted != null) {
                            addressBookService.displayPersons(sorted);
                        } else {
                            System.out.println("Invalid sorting choice.");
                        }
                    }
                    case 10 -> manualJsonFileService.writeData(addressBookService.getAllPersons());
                    case 11 -> manualJsonFileService.readData();
                    case 12 -> csvFileService.writeData(addressBookService.getAllPersons());
                    case 13 -> {
                        List<Person> read = csvFileService.readData();
                        addressBookService.setAllPersons(read);
                        addressBookService.displayPersons(read);
                    }
                    case 14 -> gsonJsonFileService.writeData(addressBookService.getAllPersons());
                    case 15 -> {
                        List<Person> read = gsonJsonFileService.readData();
                        addressBookService.setAllPersons(read);
                        addressBookService.displayPersons(read);
                    }
                    case 16 -> {
                        System.out.print("Enter details of person to save to server:\n");
                        Person person = createPerson(scanner);
                        jsonServerService.addPersonToServer(person);
                    }
                    case 17 -> {
                        List<Person> read = jsonServerService.readPersonsFromServer();
                        addressBookService.setAllPersons(read);
                        addressBookService.displayPersons(read);
                    }
                    case 18 -> {
                        System.out.println("Starting Async CSV write...");
                        asyncIOService.writeCsvAsync(addressBookService.getAllPersons())
                                .thenRun(() -> System.out.println("Async CSV Write Completed."));
                    }
                    case 19 -> {
                        System.out.println("Starting Async CSV read...");
                        asyncIOService.readCsvAsync()
                                .thenAccept(read -> {
                                    System.out.println("Async CSV Read Completed.");
                                    addressBookService.setAllPersons(read);
                                    addressBookService.displayPersons(read);
                                });
                    }
                    case 20 -> {
                        System.out.println("Starting Async GSON write...");
                        asyncIOService.writeJsonAsync(addressBookService.getAllPersons())
                                .thenRun(() -> System.out.println("Async GSON Write Completed."));
                    }
                    case 21 -> {
                        System.out.println("Starting Async GSON read...");
                        asyncIOService.readJsonAsync()
                                .thenAccept(read -> {
                                    System.out.println("Async GSON Read Completed.");
                                    addressBookService.setAllPersons(read);
                                    addressBookService.displayPersons(read);
                                });
                    }
                    case 22 -> {
                        System.out.println("Starting Async JSON Server save...");
                        Person person = createPerson(scanner);
                        asyncIOService.saveToJsonServerAsync(person)
                                .thenRun(() -> System.out.println("Async Server Save Completed."));
                    }
                    case 23 -> {
                        System.out.println("Starting Async JSON Server read...");
                        asyncIOService.readFromJsonServerAsync()
                                .thenAccept(read -> {
                                    System.out.println("Async Server Read Completed.");
                                    addressBookService.setAllPersons(read);
                                    addressBookService.displayPersons(read);
                                });
                    }
                    case 24 -> exit = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
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
