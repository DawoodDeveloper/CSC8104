package uk.ac.newcastle.enterprisemiddleware.contact;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Contact.FIND_ALL, query = "SELECT c FROM Contact c ORDER BY c.lastName ASC, c.firstName ASC"),
        @NamedQuery(name = Contact.FIND_BY_EMAIL, query = "SELECT c FROM Contact c WHERE c.email = :email")
})
@XmlRootElement
@Table(name = "contact", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Contact.findAll";
    public static final String FIND_BY_EMAIL = "Contact.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z-]+", message = "Please use a name without numbers or special characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z-]+", message = "Please use a name without numbers or special characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email(message = "The email address must be in the format of name@domain.com")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\([2-9][0-8][0-9]\\)\\s?[0-9]{3}\\-[0-9]{4}$")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "[A-Za-z-]+", message = "Please use a name without numbers or special characters")
    @Column(name = "taxi_id")
    private String taxiId;

    @NotNull
    @Pattern(regexp = "\\d+", message = "Please enter a valid number")
    @Column(name = "num_seats")
    private String numSeats;

    @NotNull
    @Past(message = "Future date cannot be in the past. Please choose a date from the past")
    @Column(name = "future_date")
    @Temporal(TemporalType.DATE)
    private Date futureDate;

    @Column(name = "state")
    private String state;

    // Getters and Setters
    // Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // First Name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Last Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Phone Number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Taxi ID
    public String getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(String taxiId) {
        this.taxiId = taxiId;
    }

    // Number of Seats
    public String getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(String numSeats) {
        this.numSeats = numSeats;
    }

    // Future Date
    public Date getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(Date futureDate) {
        this.futureDate = futureDate;
    }

    // State
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
