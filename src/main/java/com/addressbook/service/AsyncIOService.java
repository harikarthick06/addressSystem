package com.addressbook.service;

import com.addressbook.Person;
import com.addressbook.io.CsvFileService;
import com.addressbook.io.GsonJsonFileService;
import com.addressbook.server.JsonServerService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncIOService {

    private final CsvFileService csvFileService = new CsvFileService();

    private final GsonJsonFileService gsonJsonFileService = new GsonJsonFileService();

    private final JsonServerService jsonServerService = new JsonServerService();

    public CompletableFuture<Void> writeCsvAsync(List<Person> persons) {

        return CompletableFuture.runAsync(() -> csvFileService.writeData(persons));
    }

    public CompletableFuture<List<Person>> readCsvAsync() {

        return CompletableFuture.supplyAsync(csvFileService::readData);
    }

    public CompletableFuture<Void> writeJsonAsync(List<Person> persons) {

        return CompletableFuture.runAsync(() -> gsonJsonFileService.writeData(persons));
    }

    public CompletableFuture<List<Person>> readJsonAsync() {

        return CompletableFuture.supplyAsync(gsonJsonFileService::readData);
    }

    public CompletableFuture<Void> saveToJsonServerAsync(Person person) {

        return CompletableFuture.runAsync(() -> jsonServerService.addPersonToServer(person));
    }

    public CompletableFuture<List<Person>> readFromJsonServerAsync() {

        return CompletableFuture.supplyAsync(jsonServerService::readPersonsFromServer);
    }
}
