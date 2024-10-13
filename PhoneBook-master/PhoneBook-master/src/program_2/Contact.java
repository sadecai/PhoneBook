package program_2;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;

    // Constructor
    public Contact(String firstName, String lastName, String phone) {
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
