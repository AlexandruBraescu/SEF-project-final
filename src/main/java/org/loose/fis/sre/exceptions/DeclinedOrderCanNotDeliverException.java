package org.loose.fis.sre.exceptions;

public class DeclinedOrderCanNotDeliverException extends Exception{
    public DeclinedOrderCanNotDeliverException() {
        super("A declined/pending/delivered order can't be marked as delivered");
    }
}
