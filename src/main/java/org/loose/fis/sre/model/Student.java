package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.Objects;

public class Student {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String address;
    private String phone;
    private boolean availabilityStatus;
    private ArrayList<Problem> problems;

    private ArrayList<Solution> pendingSolutions;
    private ArrayList<Solution> solutionHistory;

    public ArrayList<Problem> getProducts() {
        return problems;
    }

    public Student(String username, String firstName, String lastName, String description, String address, String phone, boolean availabilityStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.availabilityStatus = availabilityStatus;
        
        problems = new ArrayList<>();
        pendingSolutions = new ArrayList<>();
        solutionHistory = new ArrayList<>();
    }

    public ArrayList<Solution> getPendingOrders() {
        return pendingSolutions;
    }

    public ArrayList<Solution> getOrderHistory() {
        return solutionHistory;
    }

    public Student() {
        problems = new ArrayList<>();
        pendingSolutions = new ArrayList<>();
        solutionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setProducts(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return availabilityStatus == student.availabilityStatus && username.equals(student.username) && firstName.equals(student.firstName) && lastName.equals(student.lastName) && description.equals(student.description) && address.equals(student.address) && phone.equals(student.phone) && problems.equals(student.problems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, description, address, phone, availabilityStatus, problems);
    }

    public void addProduct(Problem p) {
        problems.add(p);
    }

    public void updateProduct(NitriteId id, Problem editedProblem) {
        for (Problem p : problems)
            if (Objects.equals(id, p.getId()))
                problems.set(problems.indexOf(p), editedProblem);
    }

    public void removeProduct(NitriteId id) {
        problems.removeIf(p -> Objects.equals(p.getId(), id));
    }

    public void addOrderToFarmer(Solution o) { pendingSolutions.add(o); }

    public void changeOrderStatus(NitriteId id, Solution edited) {
        pendingSolutions.removeIf(o -> Objects.equals(o.getId(), id));

        solutionHistory.add(edited);
    }

    @Override
    public String toString() {
        return "" + firstName + " " +  lastName + "; " + address;
    }
}
