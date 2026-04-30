package com.phonebook.service;

import com.phonebook.model.Contact;
import com.phonebook.storage.ContactStorage;

import java.util.Collection;

public class ContactService {

    private final ContactStorage contactStorage;

    public ContactService(ContactStorage contactStorage) {
        this.contactStorage = contactStorage;
    }

    public Collection<Contact> get(String name) {
        if (name != null) {
            return contactStorage.get(name);
        } else {
            return contactStorage.getAll();
        }
    }

    public void add(Contact contact) {
        contactStorage.add(contact);
    }

    public void delete(String phone) {
        contactStorage.delete(phone);
    }
}
