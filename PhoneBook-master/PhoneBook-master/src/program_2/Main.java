package program_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {

    // Components for Contact Form
    private final JLabel lblFirstName;
    private final JLabel lblLastName;
    private final JLabel lblPhone;
    private final JTextField txtFirstName;
    private final JTextField txtLastName;
    private final JTextField txtPhone;
    private final JButton btnAdd;
    private final JButton btnRemove;
    private final JButton btnSearch;
    private final JButton btnUpdate;
    private final JButton btnDisplayAll;

    // The PhoneBook object to manage contacts
    private final PhoneBook phoneBook;

    // Constructor to setup the GUI
    public Main() {
        // Initialize the phoneBook with some default file (optional)
        phoneBook = new PhoneBook("contacts.txt");
        if (phoneBook.load()) {
            JOptionPane.showMessageDialog(null, "PhoneBook loaded successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "PhoneBook could not be loaded.");
        }

        // Setup the UI components
        lblFirstName = new JLabel("First Name:");
        lblLastName = new JLabel("Last Name:");
        lblPhone = new JLabel("Phone:");
        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtPhone = new JTextField(15);

        btnAdd = new JButton("Add Contact");
        btnRemove = new JButton("Remove Contact");
        btnSearch = new JButton("Search Contact");
        btnUpdate = new JButton("Update Contact");
        btnDisplayAll = new JButton("Display All Contacts");

        // Set layout for the main frame
        setLayout(new GridLayout(6, 2, 10, 10));

        // Add components to the frame
        add(lblFirstName);
        add(txtFirstName);
        add(lblLastName);
        add(txtLastName);
        add(lblPhone);
        add(txtPhone);
        add(btnAdd);
        add(btnRemove);
        add(btnSearch);
        add(btnUpdate);
        add(btnDisplayAll);

        // Set action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeContact();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
            }
        });

        btnDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllContacts();
            }
        });

        // JFrame settings
        setTitle("Contact Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);  // Set the size of the form
        setLocationRelativeTo(null);  // Center the form on the screen
        setVisible(true);  // Make the form visible
    }

    // Method to add a contact
private void addContact() {
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String phone = txtPhone.getText();
    if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all fields.");
    } else {
        Contact newContact = new Contact(firstName, lastName, phone);
        if (phoneBook.add(newContact)) {
            JOptionPane.showMessageDialog(null, "Contact added.");
        } else {
            JOptionPane.showMessageDialog(null, "Contact already exists.");
        }
    }
}


    // Method to remove a contact
    private void removeContact() {
        String firstName = JOptionPane.showInputDialog("Enter First Name to Remove:");
        if (phoneBook.remove(firstName)) {
            JOptionPane.showMessageDialog(null, "Contact removed.");
        } else {
            JOptionPane.showMessageDialog(null, "Contact not found.");
        }
    }

    // Method to search for a contact
    private void searchContact() {
        String firstName = JOptionPane.showInputDialog("Enter First Name to Search:");
        Contact contact = phoneBook.search(firstName);
        if (contact != null) {
            JOptionPane.showMessageDialog(null, "Found: " + contact.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Contact not found.");
        }
    }

    // Method to update a contact
    private void updateContact() {
        String firstName = JOptionPane.showInputDialog("Enter First Name of Contact to Update:");
        Contact contact = phoneBook.search(firstName);
        if (contact != null) {
            String newFirstName = JOptionPane.showInputDialog("New First Name:", contact.getFirstName());
            String newLastName = JOptionPane.showInputDialog("New Last Name:", contact.getLastName());
            String newPhone = JOptionPane.showInputDialog("New Phone:", contact.getPhone());
            phoneBook.remove(firstName);  // Remove old contact
            phoneBook.add(new Contact(newFirstName, newLastName, newPhone));  // Add updated contact
            JOptionPane.showMessageDialog(null, "Contact updated.");
        } else {
            JOptionPane.showMessageDialog(null, "Contact not found.");
        }
    }

    // Method to display all contacts using JTable
    private void displayAllContacts() {
        ArrayList<Contact> contacts = phoneBook.getAllContacts();
        if (contacts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No contacts to display.");
            return;
        }

        // Define column headers for the table
        String[] columnNames = {"First Name", "Last Name", "Phone"};

        // Convert the contacts list into a 2D array for JTable
        Object[][] data = new Object[contacts.size()][3];
        for (int i = 0; i < contacts.size(); i++) {
            data[i][0] = contacts.get(i).getFirstName();
            data[i][1] = contacts.get(i).getLastName();
            data[i][2] = contacts.get(i).getPhone();
        }

        // Create the JTable with the data and column headers
        JTable contactTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(contactTable);

        // Display the JTable in a new window
        JFrame tableFrame = new JFrame("All Contacts");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setSize(400, 300);
        tableFrame.add(scrollPane);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        new Main();
    }
}
