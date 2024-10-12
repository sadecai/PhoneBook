package program_2;

import java.io.*;
import java.util.ArrayList;

public class PhoneBook {
    private final ArrayList<Contact> contacts;
    private final String filename;

    public PhoneBook(String filename) {
        this.filename = filename;
        contacts = new ArrayList<>();
    }

    public boolean load() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean add(Contact contact) {
        if (!contactExists(contact)) {
            contacts.add(contact);
            save();
            return true;
        }
        return false;  // Contact already exists
    }

    public boolean remove(String firstName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                contacts.remove(contact);
                save();
                return true;
            }
        }
        return false;  // Contact not found
    }

    public Contact search(String firstName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                return contact;
            }
        }
        return null;  // Contact not found
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

    public boolean contactExists(Contact newContact) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(newContact.getFirstName()) &&
                contact.getPhone().equals(newContact.getPhone())) {
                return true;  // Duplicate contact
            }
        }
        return false;  // No duplicate found
    }

    private void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                bw.write(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getPhone());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
