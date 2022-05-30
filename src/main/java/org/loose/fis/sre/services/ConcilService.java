package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.model.Council;
import org.loose.fis.sre.model.Solution;

import java.util.ArrayList;
import java.util.Objects;

public class ConcilService {
    private static ObjectRepository<Council> consumerRepository = UserService.getConsumerRepository();

    public static void addConsumer(String username,String firstName, String lastName, String address, String phone) throws EmptyFieldsException {
        checkIfFieldsEmpty(username, firstName, lastName, address, phone);
        consumerRepository.insert(new Council(username, firstName, lastName, address, phone));
    }

    private static void checkIfFieldsEmpty(String username, String firstName, String lastName, String address, String phone) throws EmptyFieldsException {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty())
            throw new EmptyFieldsException();
    }

    public static Council getConsumerByUsername(String username){
        for (Council c : consumerRepository.find())
            if(Objects.equals(c.getUsername(), username))
                return c;
        return new Council();
    }

    public static Council getConsumerByOrderId(NitriteId id) {
        for (Council c : consumerRepository.find())
            for (Solution o : c.getPastOrders())
                if (Objects.equals(o.getId(), id))
                    return c;

        return new Council();
    }

    public static ArrayList<Council> getAllConsumers(){
        ArrayList<Council> temp = new ArrayList<Council>();
        for(Council c : consumerRepository.find())
            temp.add(c);
        return temp;
    }
}
