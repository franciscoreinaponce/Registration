package com.franciscoreina.registration.constants;

public interface Constants {

    // Regex
    /** Explanation of the pattern
     *
     * ^                Match the beginning of the string
     * [a-zA-Z0-9]      Require to be alphanumeric
     * +                One or more characters
     * $                Match the end of the string
     */
    String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";

    /** Explanation of the pattern
     *
     * ^                Match the beginning of the string
     * (?=.*[0-9])      Require that at least one digit appear anywhere in the string
     * (?=.*[a-z])      Require that at least one lowercase letter appear anywhere in the string
     * (?=.*[A-Z])      Require that at least one uppercase letter appear anywhere in the string
     * .{4,}            The password must be at least 4 characters long
     * $                Match the end of the string
     */
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$";

}