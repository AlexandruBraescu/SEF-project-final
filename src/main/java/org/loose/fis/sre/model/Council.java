package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.Objects;

public class Council {


    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private ArrayList<Solution> pastSolutions;

    public Council(String username, String firstName, String lastName, String address, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        pastSolutions = new ArrayList<Solution>();
    }
    public Council() {
        pastSolutions = new ArrayList<Solution>();
    }

    public ArrayList<Solution> getPastOrders() {
        return pastSolutions;
    }

    public void addOrderToConsumer(Solution o) {
        pastSolutions.add(o);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Council council = (Council) o;
        return username.equals(council.username) && firstName.equals(council.firstName) && lastName.equals(council.lastName) && address.equals(council.address) && phone.equals(council.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, address, phone);
    }
}
