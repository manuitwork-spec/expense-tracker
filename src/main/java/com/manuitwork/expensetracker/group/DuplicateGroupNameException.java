package com.manuitwork.expensetracker.group;

public class DuplicateGroupNameException extends RuntimeException {

    public DuplicateGroupNameException(String message) {
        super(message);
    }
}
