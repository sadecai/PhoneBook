package program_2;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;

    // Constructor
    public Contact(String firstName, String lastName, String phone, String email) {
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
    public String getEmail() {
    return email;
}

    @Override
public String toString() {
    return firstName + " " + lastName + " " + phone + (email != null ? " " + email : "");
    }
}
