package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.Council;
import org.loose.fis.sre.model.Student;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.model.Problem;

import java.util.ArrayList;
import java.util.Objects;

public class StudentService {
    private static ObjectRepository<Student> farmerRepository = UserService.getFarmerRepository();
    private static ObjectRepository<Council> consumerRepository = UserService.getConsumerRepository();
    public static void addStudent(String username, String firstName, String lastName, String description, String address, String phone, boolean availabilityStatus) throws EmptyFieldsException {
        checkIfFieldsEmpty(username, firstName, lastName, description, address, phone);
        farmerRepository.insert(new Student(username, firstName, lastName, description, address, phone, availabilityStatus));
    }

    private static void checkIfFieldsEmpty(String username, String firstName, String lastName, String description, String address, String phone) throws EmptyFieldsException {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || description.isEmpty() || address.isEmpty() || phone.isEmpty())
            throw new EmptyFieldsException();
    }

    public static ArrayList<Student> getAllFarmers() {
        ArrayList<Student> array = new ArrayList<>();
        for (Student f : farmerRepository.find())
            array.add(f);

        return array;
    }
    public static ArrayList<Problem> getAllProductsByUsername(String username) {
        for (Student student : farmerRepository.find()) {
            if (Objects.equals(username, student.getUsername())) {
                return student.getProducts();
            }
        }

        return new ArrayList<>();
    }

    public static void addProductToFarmer(String username, String name, String description, String quantity, String price) throws EmptyFieldsException, NotANumberException {
        Problem p = ProductService.addProduct(name, description, quantity, price);

        for (Student student : farmerRepository.find()) {
            if (Objects.equals(username, student.getUsername())) {
                student.addProduct(p);

                farmerRepository.update(student);
            }
        }
    }

    public static void addOrderToFarmer(Problem p, Council c, String quantity, String deliveryMethod) throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
        Solution o = OrderService.addOrder(p, c.getUsername(), quantity, deliveryMethod);

        Student f = getFarmerByProductId(p.getId());
        f.addOrderToFarmer(o);
        c.addOrderToConsumer(o);
        farmerRepository.update(f);
        consumerRepository.update(c);

    }

    public static Student getFarmerByUsername(String username){
        for(Student f : farmerRepository.find())
            if(Objects.equals(f.getUsername(), username))
                return f;
        return new Student();
    }

    public static Student getFarmerByProductId(NitriteId id) {
        for (Student f : farmerRepository.find()) {
            if (f.getProducts() != null) {
                for (Problem p : f.getProducts())
                    if (Objects.equals(id, p.getId())) {
                        return f;
                    }
            }
        }

        return new Student();
    }

    public static void updateFarmerByUsername(String username, String firstName, String lastName, String description, String address, String phone, boolean status) throws EmptyFieldsException {
            checkIfFieldsEmpty(username, firstName, lastName, description, address, phone);
            for (Student f : farmerRepository.find()) {
                if (Objects.equals(username, f.getUsername())) {
                    f.setFirstName(firstName);
                    f.setLastName(lastName);
                    f.setDescription(description);
                    f.setAddress(address);
                    f.setPhone(phone);
                    f.setAvailabilityStatus(status);

                    farmerRepository.update(f);
                }
        }
    }
  
    public static ArrayList<Student> filter(String search, String filterBy) {
        ArrayList<Student> shownStudents = new ArrayList<Student>();
        if (filterBy.equals("product")) {
            for(Student f : farmerRepository.find()) {
                if (f.getProducts() != null && f.isAvailabilityStatus())
                    for (Problem p : f.getProducts())
                        if (p.getName().contains(search) && p.getQuantity() > 0) {
                            shownStudents.add(f);
                            break;
                        }
            }
        } else if (filterBy.equals("county")) {
            for(Student f : farmerRepository.find()) {
                if (f.getProducts() != null && f.isAvailabilityStatus())
                    if (f.getAddress().contains(search)) {
                        shownStudents.add(f);
                    }
            }
        }
        return shownStudents;
    }

}
