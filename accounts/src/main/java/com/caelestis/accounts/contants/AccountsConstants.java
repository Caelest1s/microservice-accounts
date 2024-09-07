package com.caelestis.accounts.contants;

public class AccountsConstants {

    private AccountsConstants() {

    }

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "123 Main Street, New York";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
    // public static final String STATUS_500 = "500";
    // public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";

    public static final String ERR_MESSAGE_MOBILE = "Mobile number must be 11 digits";
    public static final String VALID_MOBILE_NUMBER = "(^$|[0-9]{11})";
}
