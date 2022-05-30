package org.loose.fis.sre.exceptions;

public class UsernameAndPasswordDoNotMatchException extends Exception{

    public UsernameAndPasswordDoNotMatchException() {
        super("Username and password do not match!");
    }
}
