package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.*;

import java.util.ArrayList;
import java.util.Objects;

public class OrderService {
    private static ObjectRepository<Solution> orderRepository = UserService.getOrderRepository();
    private static ObjectRepository<Student> farmerRepository = UserService.getFarmerRepository();

    public static Solution addOrder(Problem p, String c, String quantity, String deliveryMethod) throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
        checkQuantity(p, quantity);
        double q = Double.parseDouble(quantity);
        Solution o = new Solution(p, c, Double.parseDouble(quantity), p.getPricePerUnit() * Double.parseDouble(quantity), OrderStatusEnum.Pending, deliveryMethod);
        orderRepository.insert(o);
        ProductService.updateProductById(p.getId(), p.getName(), p.getDescription(), String.valueOf(p.getQuantity() - q), String.valueOf(p.getPricePerUnit()));

        return o;
    }

    private static void checkQuantity(Problem p, String quantity) throws NotANumberException, QuantityNotAvailableException {
        double q;
        try {
            q = Double.parseDouble(quantity);
        } catch (NumberFormatException ex) {
            throw new NotANumberException();
        }

        if (p.getQuantity() < q)
            throw new QuantityNotAvailableException();
    }

    public static ArrayList<Solution> getAllOrdersByUsername(String username){
        ArrayList<Solution> shownSolutions = new ArrayList<Solution>();
        for(Solution o : orderRepository.find()) {
            if (Objects.equals(o.getConsumer(), username))
                shownSolutions.add(o);
        }
        return shownSolutions;
    }

    public static Solution getOrderById(NitriteId id) {
        for(Solution o : orderRepository.find())
            if (Objects.equals(o.getId(), id))
                return o;

        return new Solution();
    }

    public static void changeOrderStatus(NitriteId id, OrderStatusEnum status) {
        Solution o = OrderService.getOrderById(id);
        o.setStatus(status);
        orderRepository.update(o);

        for (Student f : farmerRepository.find()) {
            if (status == OrderStatusEnum.Delivered) {
                for (Solution solution : f.getOrderHistory())
                    if (Objects.equals(solution.getId(), id)) {
                        f.changeOrderStatus(id, o);
                        farmerRepository.update(f);
                        break;
                    }
            } else {
                for (Solution solution : f.getPendingOrders())
                    if (Objects.equals(solution.getId(), id)) {
                        f.changeOrderStatus(id, o);
                        farmerRepository.update(f);
                        break;
                    }
            }
        }
    }

    public static ArrayList<Solution> getAllOrders(){
        ArrayList<Solution> temp = new ArrayList<Solution>();
        for (Solution o : orderRepository.find())
            temp.add(o);
        return temp;
    }
}
