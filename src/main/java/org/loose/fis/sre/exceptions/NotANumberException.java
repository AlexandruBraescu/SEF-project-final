package org.loose.fis.sre.exceptions;

public class NotANumberException extends Exception {
    public NotANumberException() {
        super("Quantity and Price need to be a number");
    }
}
