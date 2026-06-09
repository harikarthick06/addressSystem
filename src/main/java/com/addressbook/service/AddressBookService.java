package com.addressbook.service;

import com.addressbook.Person;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookService {

    private final List<Person> personList = new ArrayList<>();

    private final Map<String, List<Person>> cityPersonMap = new HashMap<>();
    private final Map<String, List<Person>> statePersonMap = new HashMap<>();

    public boolean addPerson(Person person) {

        boolean duplicate = personList.stream()
                .anyMatch(existingPerson -> existingPerson.equals(person));

        if (duplicate) {
            System.out.println("Duplicate entry found. Person already exists.");
            return false;
        }

        personList.add(person);
        updateCityAndStateDictionary();

        System.out.println("Person added successfully.");
        return true;
    }

    public boolean editPerson(String firstName, String lastName, Scanner scanner) {

        Optional<Person> personOptional = findPersonByName(firstName, lastName);

        if (personOptional.isEmpty()) {
            System.out.println("Person not found.");
            return false;
        }

        Person person = personOptional.get();

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

        updateCityAndStateDictionary();

        System.out.println("Person updated successfully.");
        return true;
    }

    public boolean deletePerson(String firstName, String lastName) {

        Optional<Person> personOptional = findPersonByName(firstName, lastName);

        if (personOptional.isEmpty()) {
            System.out.println("Person not found.");
            return false;
        }

        personList.remove(personOptional.get());
        updateCityAndStateDictionary();

        System.out.println("Person deleted successfully.");
        return true;
    }

    public Optional<Person> findPersonByName(String firstName, String lastName) {

        return personList.stream()
                .filter(person -> person.getFirstName().equalsIgnoreCase(firstName)
                        && person.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

    public List<Person> sortByName() {

        return personList.stream()
                .sorted(Comparator.comparing(Person::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Person::getLastName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Person> sortByCity() {

        return personList.stream()
                .sorted(Comparator.comparing(Person::getCity, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Person> sortByState() {

        return personList.stream()
                .sorted(Comparator.comparing(Person::getState, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Person> sortByZip() {

        return personList.stream()
                .sorted(Comparator.comparing(Person::getZip))
                .collect(Collectors.toList());
    }

    public List<Person> viewPersonsByCity(String city) {

        return cityPersonMap.getOrDefault(city.toLowerCase(), new ArrayList<>());
    }

    public List<Person> viewPersonsByState(String state) {

        return statePersonMap.getOrDefault(state.toLowerCase(), new ArrayList<>());
    }

    public List<Person> searchPersonsByCity(String city) {

        return personList.stream()
                .filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Person> searchPersonsByState(String state) {

        return personList.stream()
                .filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    private void updateCityAndStateDictionary() {

        cityPersonMap.clear();
        statePersonMap.clear();

        for (Person person : personList) {

            cityPersonMap
                    .computeIfAbsent(person.getCity().toLowerCase(), key -> new ArrayList<>())
                    .add(person);

            statePersonMap
                    .computeIfAbsent(person.getState().toLowerCase(), key -> new ArrayList<>())
                    .add(person);
        }
    }

    public List<Person> getAllPersons() {
        return personList;
    }

    public void setAllPersons(List<Person> persons) {

        personList.clear();
        personList.addAll(persons);
        updateCityAndStateDictionary();
    }

    public void displayPersons(List<Person> persons) {

        if (persons.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        persons.forEach(System.out::println);
    }
}
