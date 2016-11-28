package com.fh;

import java.util.Arrays;

public class SubPolynomial {
    private boolean isVariable = false; // check if there exists variable or not
    private double coefficient = 1; // the coefficient of subexpression
    private String variables = "";  // the vars of subexpression

    public void setIsVariable (boolean isVariable) {
        this.isVariable = isVariable;
    }
    
    public boolean getIsVariable () {
        return this.isVariable;
    }
    
    public void setCofficientMul (double coefficient) {
        this.coefficient *= coefficient;
    }
    
    public void setCofficientAdd (double coefficient) {
        this.coefficient += coefficient;
    }
    
    public double getCofficient () {
        return this.coefficient;
    }
    
    public void setVariablesAdd (String variables) {
        this.variables += variables;
    }
    
    public void setVariables (String variables) {
        this.variables = variables;
    }
    
    public String getVariables () {
        return this.variables;
    }
    
    public void sort () {
        byte[] tempStr = this.variables.getBytes();
        Arrays.sort(tempStr);
        this.variables = new String(tempStr);
    }
}
