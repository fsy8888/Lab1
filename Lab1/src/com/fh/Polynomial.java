package com.fh;

import java.util.ArrayList;

public class Polynomial {
    private boolean isLegal = false;
    private boolean[] vars = new boolean[26];   // store the variables existing in polynomial
    private String polynomial = null;   // store the correct polynomial
    private ArrayList<SubPolynomial> subPolynomials = new ArrayList<>();    // store the subPolynomials of legal polynomial 
    
    public ArrayList<SubPolynomial> getSubPolynomial() {
        return subPolynomials;
    }

    public void setSubPolynomial(ArrayList<SubPolynomial> subPolynomial) {
        this.subPolynomials = subPolynomial;
    }

    public boolean[] getVars() {
        return vars;
    }
    
    public void setVars(boolean[] vars) {
        this.vars = vars;
    }
    
    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean isLegal) {
        this.isLegal = isLegal;
    }
    
    // delete the whitespace existing in SubPolynomial
    public String deleteWhitespace (String tempInput) {
        String input = "";
        for (int i = 0; i < tempInput.length(); i++) {
            if (Character.isWhitespace(tempInput.charAt(i)))
                continue;
            input += tempInput.charAt(i);
        }
        return input;
    }
    
    public void verifyPolynomial(String tempInput, int preIndexOfPolynomial, int postIndexOfPolynomial) {
        String input = this.deleteWhitespace(tempInput);
        
        while (postIndexOfPolynomial < input.length()) {
            if (Character.isLetter(input.charAt(postIndexOfPolynomial))) {  // if the current character is a letter
                if (postIndexOfPolynomial != input.length() - 1) {
                    if (input.charAt(postIndexOfPolynomial + 1) != '+' && input.charAt(postIndexOfPolynomial + 1) != '*') {
                        this.isLegal = false;
                        ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                        return;
                    }
                }
                postIndexOfPolynomial++;
            }
            else if (Character.isDigit(input.charAt(postIndexOfPolynomial))) {  // if the current character is a digit
                String temp = "";
                while (postIndexOfPolynomial < input.length() && input.charAt(postIndexOfPolynomial) != '+' && input.charAt(postIndexOfPolynomial) != '*') {
                    temp += input.charAt(postIndexOfPolynomial);
                    postIndexOfPolynomial++;
                }
                try {
                    Double.parseDouble(temp);
                }
                catch (Exception e) {
                    this.isLegal = false;
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
            }
            else if (input.charAt(postIndexOfPolynomial) == '+' || input.charAt(postIndexOfPolynomial) == '*') {    // if the current character is '+' or '*' 
                if (postIndexOfPolynomial == input.length() - 1) {
                    this.isLegal = false;
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                else if (!Character.isDigit(input.charAt(postIndexOfPolynomial + 1)) && !Character.isLetter(input.charAt(postIndexOfPolynomial + 1))) {
                    this.isLegal = false;
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                postIndexOfPolynomial++;
            }
            else {
                this.isLegal = false;
                ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                return;
            }
        }
        this.polynomial = input;
        resolveToSubPolynomial(preIndexOfPolynomial, postIndexOfPolynomial);
    }
    
    public void resolveToSubPolynomial(int preIndexOfPolynomial, int postIndexOfPolynomial) {
        this.subPolynomials.clear();
        
        String subTemp; // temp store the subPolynomials of polynomial
        for (postIndexOfPolynomial = 0; postIndexOfPolynomial <= this.polynomial.length(); postIndexOfPolynomial++) {
            if (postIndexOfPolynomial != this.polynomial.length() && this.polynomial.charAt(postIndexOfPolynomial) != '+') 
                continue;
            subTemp = this.polynomial.substring(preIndexOfPolynomial, postIndexOfPolynomial);
            SubPolynomial subTempPolynomial = new SubPolynomial();  // temp store the subPolynomials of polynomial
            String doubleSubTemp;   // the factor of subPolynomials
            int i, j;
            i = j = 0;
            for (; j <= subTemp.length(); j++) {
                if (j != subTemp.length() && subTemp.charAt(j) != '*')
                    continue;
                doubleSubTemp = subTemp.substring(i, j);
                if (Character.isLetter(doubleSubTemp.charAt(0))) {
                    subTempPolynomial.setVariablesAdd(doubleSubTemp);
                    subTempPolynomial.setIsVariable(true);
                    this.vars[doubleSubTemp.charAt(0) - 97] = true;
                }
                else {
                    subTempPolynomial.setCofficientMul(Double.parseDouble(doubleSubTemp));
                }
                i = j + 1;
            }
            subTempPolynomial.sort();
            subPolynomials.add(subTempPolynomial);
            preIndexOfPolynomial = postIndexOfPolynomial + 1;
        }
        
        this.isLegal = true;
        System.out.println(this.polynomial);
    }
    
    // clone a copy of subPolynomials
    public ArrayList<SubPolynomial> clone (ArrayList<SubPolynomial> subPolynomials) {
        ArrayList<SubPolynomial> clonePolynomials = new ArrayList<>();      
        for (int i = 0; i < subPolynomials.size(); i++) {
            SubPolynomial temp = new SubPolynomial();
            temp.setIsVariable(subPolynomials.get(i).getIsVariable());
            temp.setCofficientMul(subPolynomials.get(i).getCofficient());
            temp.setVariables(subPolynomials.get(i).getVariables());
            clonePolynomials.add(temp);
        }
        return clonePolynomials;
    }
    
    // merge similar subPolynomials
    public String combine (ArrayList<SubPolynomial> subPolynomials) {
        for (int i = 0; i < subPolynomials.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (subPolynomials.get(j).getVariables().equals(subPolynomials.get(i).getVariables())) {
                    if (j != i) {
                        subPolynomials.get(j).setCofficientAdd(subPolynomials.get(i).getCofficient());
                        subPolynomials.remove(subPolynomials.get(i));
                        i--;
                        break;
                    }
                }
            }
        }
        String output = "";
        for (int i = 0; i < subPolynomials.size(); i++) {
            if (subPolynomials.get(i).getCofficient() == 0) {
                continue;
            }
            else if (subPolynomials.get(i).getCofficient() == 1) {
                if (i != 0)
                    output += "+";
                if (subPolynomials.get(i).getIsVariable() && subPolynomials.get(i).getVariables() != "")
                    output += subPolynomials.get(i).getVariables();
                else
                    output += subPolynomials.get(i).getCofficient();
            }
            else {
                if (i != 0)
                    output += "+";
                output += subPolynomials.get(i).getCofficient();
                if (subPolynomials.get(i).getIsVariable() && subPolynomials.get(i).getVariables() != "")
                    output += "*" + subPolynomials.get(i).getVariables();
            }
        }
        if (output == "")
        {
            System.out.println("0.0");
            return "0.0";
        }
        else
        {
            System.out.println(output);
            return output;
        }       
    }
}


















