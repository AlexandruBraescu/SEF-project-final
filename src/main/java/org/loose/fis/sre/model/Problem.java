package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.text.MessageFormat;
import java.util.Objects;

public class Problem {

    @Id
    private NitriteId id;
    private String name;
    private String description;
    private double quantity;
    private double pricePerUnit;

    public Problem(String name, String description, double quantity, double pricePerUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Problem() {
    }

    public NitriteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Double.compare(problem.quantity, quantity) == 0 && Double.compare(problem.pricePerUnit, pricePerUnit) == 0 && id.equals(problem.id) && name.equals(problem.name) && description.equals(problem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, quantity, pricePerUnit);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}; {1}; {2} RON/unitate", name, description, pricePerUnit);
    }
}
