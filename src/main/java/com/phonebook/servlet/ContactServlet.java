package com.phonebook.servlet;

import com.phonebook.model.Contact;
import com.phonebook.service.ContactService;
import com.phonebook.storage.ContactStorage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Collection;

public class ContactServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();
    private ContactStorage contactStorage;
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        super.init();

        //App environment building and dependency injecting, like main() in CLII app
        contactStorage = new ContactStorage();
        contactService = new ContactService(contactStorage);
        System.out.println("Servlet init processed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();                  //Get a request URL, if you need to route request
        String name = req.getParameter("name");     //Get a parameter

        Collection<Contact> contacts = contactService.get(name);

        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), contacts);    //Set response body with serialization to JSON
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Contact contact = mapper.readValue(req.getInputStream(), Contact.class);  //Get a request body with deserialization from JSON

        contactService.add(contact);

        resp.setStatus(HttpServletResponse.SC_CREATED);         //Set response status codee
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String phone = req.getParameter("phone");

        contactService.delete(phone);

        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
