package com.fh;

public class ErrorMessage {
    public static final int NULL_EXPRESSION = 0;    // null expression
    public static final int GRAMMAR_ERROR = 1;  // grammar error
    public static final int NONE_VARIABLE = 2;  // no such variable(s)
    public static final int NULL_INPUT = 3; // null input
    public static final String[] ERROR_MESSAGES = {"Error, please input an initial polynomial!", "Error, input error!", "Error, no such variable!", "Error, please input!"};
    
    public static void proceException(int errorType) {
        System.out.println(ErrorMessage.ERROR_MESSAGES[errorType]);
    }
}
