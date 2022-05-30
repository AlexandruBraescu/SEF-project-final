package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Solution {
    @Id
    private NitriteId id;
    private Problem problem;
    private String consumer;
    private double quantity;
    private double totalprice;
    private OrderStatusEnum status;
    private String deliveryMethod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Double.compare(solution.quantity, quantity) == 0 && Double.compare(solution.totalprice, totalprice) == 0 && id.equals(solution.id) && problem.equals(solution.problem) && consumer.equals(solution.consumer) && status == solution.status && deliveryMethod.equals(solution.deliveryMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, problem, consumer, quantity, totalprice, status, deliveryMethod);
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }


    public NitriteId getId() {
        return id;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public Problem getProduct() {
        return problem;
    }

    public void setProduct(Problem problem) {
        this.problem = problem;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return problem + " x " + quantity + "; " + totalprice + " RON; Status: " + status + "; Method: " + deliveryMethod;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Solution() {
    }

    public Solution(Problem problem, String consumer, double quantity, double totalprice, OrderStatusEnum status, String deliveryMethod) {
        this.problem = problem;
        this.consumer = consumer;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.status = status;
        this.deliveryMethod = deliveryMethod;
    }
}
