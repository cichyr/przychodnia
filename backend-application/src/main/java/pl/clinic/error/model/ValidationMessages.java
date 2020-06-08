package pl.clinic.error.model;

public class ValidationMessages {
    public static final String NOT_BLANK = "must not be blank";
    public static final String SIZE_1_20 = "must consist of 1 to 20 characters";
    public static final String SIZE_0_60 = "must consist of 0 to 60 characters";
    public static final String SIZE_1_60 = "must consist of 1 to 60 characters";
    public static final String INVALID_PHONE = "must consist of digits (max 20) or '+' and digits (max 20)";
    public static final String NOT_UNIQUE = "must be unique";
    public static final String INVALID_PESEL = "must consist of 11 digits";
}
