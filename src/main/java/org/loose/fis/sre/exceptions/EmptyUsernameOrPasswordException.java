package org.loose.fis.sre.exceptions;

public class EmptyUsernameOrPasswordException extends Exception {

    public EmptyUsernameOrPasswordException() {
        super("Enter your data!");
    }
}
