package com.phonebook.storage;

import com.phonebook.model.Contact;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ContactStorage {

    private final Map<String, Contact> contacts = new ConcurrentHashMap<>();

    public void add(Contact contact) {
        contacts.put(contact.getPhone(), contact);
    }

    public Collection<Contact> get(String name) {
        return contacts.values().stream()
                .filter(contact ->
                        contact.getFirstName().contains(name)
                        || contact.getLastName().contains(name))
                .toList();
    }

    public Collection<Contact> getAll() {
        return contacts.values();
    }

    public void delete(String phone) {
        contacts.remove(phone);
    }
}
