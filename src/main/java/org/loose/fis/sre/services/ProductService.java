package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.model.Problem;
import org.loose.fis.sre.model.Student;

import java.util.ArrayList;
import java.util.Objects;

public class ProductService {
    private static ObjectRepository<Problem> productRepository = UserService.getProductRepository();
    private static ObjectRepository<Student> farmerRepository = UserService.getFarmerRepository();

    public static Problem addProduct(String name, String description, String quantity, String pricePerUnit) throws EmptyFieldsException, NotANumberException {
        checkIfFieldsAreEmpty(name, description, quantity, pricePerUnit);
        double quantityD;
        double priceD;

        try {
            quantityD = Double.parseDouble(quantity);
            priceD = Double.parseDouble(pricePerUnit);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }

        Problem p = new Problem(name, description, quantityD, priceD);
        productRepository.insert(p);

        return p;
    }

    private static void checkIfFieldsAreEmpty(String name, String description, String quantity, String pricePerUnit) throws EmptyFieldsException {
        if (name.isEmpty() || description.isEmpty() || quantity.isEmpty() || pricePerUnit.isEmpty())
            throw new EmptyFieldsException();
    }

    public static void removeProduct(NitriteId id) {
        for (Student f : farmerRepository.find()) {
            if (f.getProducts() == null)
                continue;

            f.removeProduct(id);

            farmerRepository.update(f);
        }

        for (Problem problem : productRepository.find())
            if (problem.getId() == id)
                productRepository.remove(problem);
    }

    public static Problem getProductById(NitriteId id) throws Exception {
        for (Problem p : productRepository.find()) {
            if (Objects.equals(p.getId(), id))
                return p;
        }

        throw new Exception("no product");
    }

    public static ArrayList<Problem> getAllProducts() {
        ArrayList<Problem> problems = new ArrayList<>();
        for (Problem p : productRepository.find())
            problems.add(p);

        return problems;
    }

    public static void updateProductById(NitriteId id, String name, String description, String quantity, String pricePerUnit) throws NotANumberException, EmptyFieldsException {
        checkIfFieldsAreEmpty(name, description, quantity, pricePerUnit);
        double quantityD;
        double priceD;

        try {
            quantityD = Double.parseDouble(quantity);
            priceD = Double.parseDouble(pricePerUnit);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }

        Problem editedProblem = null;

        for (Problem p : productRepository.find()) {
            if (Objects.equals(id, p.getId())) {
                p.setName(name);
                p.setDescription(description);
                p.setQuantity(quantityD);
                p.setPricePerUnit(priceD);

                productRepository.update(p);
                editedProblem = p;
            }
        }

        for (Student f : farmerRepository.find()) {
            if (f.getProducts() == null)
                continue;

            for (Problem p : f.getProducts())
                if (Objects.equals(id, p.getId()))
                    f.updateProduct(id, editedProblem);

            farmerRepository.update(f);
        }
    }
}
