package program_2;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;

    // Constructor
    public Contact(String firstName, String lastName, String phone) {
       if (firstName == null || firstName.trim().isEmpty()) {
        throw new IllegalArgumentException("First name cannot be empty.");
    }
    if (lastName == null || lastName.trim().isEmpty()) {
        throw new IllegalArgumentException("Last name cannot be empty.");
    }
    if (phone == null || phone.trim().isEmpty()) {
        throw new IllegalArgumentException("Phone number cannot be empty.");
    }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + phone;
    }
}
