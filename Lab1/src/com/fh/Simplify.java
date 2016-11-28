package com.fh;

import java.util.ArrayList;

public class Simplify {
    private boolean isLegal = false;    // the legality of expressions(contain Polynomial, simplify command, and derivation command)
    private boolean simplify = false;   // the current operation should be simplified or not
    
    public boolean isLegal() {
        return isLegal;
    }
    
    public void setLegal(boolean isLegal) {
        this.isLegal = isLegal;
    }
    
    public boolean isSimplify() {
        return simplify;
    }
    
    public void setSimplify(boolean simplify) {
        this.simplify = simplify;
    }
    
    public void verifySimplify(Polynomial polynomial, Derivate derivateExpression, String tempInput, int postIndexOfSimplify) {
        postIndexOfSimplify = 10;
        int[] var = new int [26];
        
        while (postIndexOfSimplify < tempInput.length()) {
            if (Character.isLetter(tempInput.charAt(postIndexOfSimplify))) {  // if the current character is a letter
                if (var[tempInput.charAt(postIndexOfSimplify) - 97] == 1) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                else if (!polynomial.getVars()[tempInput.charAt(postIndexOfSimplify) - 97]) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.NONE_VARIABLE);
                    return;
                }
                else if (postIndexOfSimplify == tempInput.length() - 1) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                else if (tempInput.charAt(postIndexOfSimplify + 1) != '=') {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                var[tempInput.charAt(postIndexOfSimplify) - 97] = 1;
                postIndexOfSimplify++;
            }
            else if (tempInput.charAt(postIndexOfSimplify) == '=') {
                if (postIndexOfSimplify == tempInput.length() - 1) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                else if (!Character.isDigit(tempInput.charAt(postIndexOfSimplify + 1))) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
                postIndexOfSimplify++;
            }
            else if (Character.isDigit(tempInput.charAt(postIndexOfSimplify))) {  // if the current character is a didit
                String temp = "";
                while (postIndexOfSimplify < tempInput.length() && !Character.isWhitespace(tempInput.charAt(postIndexOfSimplify))) {
                    temp += tempInput.charAt(postIndexOfSimplify);
                    postIndexOfSimplify++;
                }
                try {
                    Double.parseDouble(temp);
                }
                catch (Exception e) {
                    this.isLegal = false;
                    this.simplify = false;
                    derivateExpression.setLegal(false);
                    derivateExpression.setDrivate(false);
                    ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                    return;
                }
            }
            else if (tempInput.charAt(postIndexOfSimplify) == ' ') {
                if (postIndexOfSimplify != tempInput.length() - 1) {
                    if (!Character.isLetter(tempInput.charAt(postIndexOfSimplify + 1))) {
                        this.isLegal = false;
                        this.simplify = false;
                        derivateExpression.setLegal(false);
                        derivateExpression.setDrivate(false);
                        ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                        return;
                    }
                }
                postIndexOfSimplify++;
            }
            else {
                this.isLegal = false;
                this.simplify = false;
                derivateExpression.setLegal(false);
                derivateExpression.setDrivate(false);
                ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
                return;
            }
            
        }
        this.isLegal = true;
        this.simplify = true;
        derivateExpression.setLegal(false);
        derivateExpression.setDrivate(false);
    }
    
    // simplify polynomial
    public String simplify (Polynomial polynomial, String input, int preIndexOfSimplify, int postIndexOfSimplify) {
        ArrayList<SubPolynomial> simplifyPolynomial = new ArrayList<>();
        simplifyPolynomial = polynomial.clone(polynomial.getSubPolynomial());
        postIndexOfSimplify = 9;
        while (postIndexOfSimplify < input.length()) {
            if (!Character.isLetter(input.charAt(postIndexOfSimplify))) {
                postIndexOfSimplify++;
                continue;
            }
            preIndexOfSimplify = postIndexOfSimplify + 2;
            char var = input.charAt(postIndexOfSimplify);
            for (int i = 0; i < simplifyPolynomial.size(); i++) {
                if (simplifyPolynomial.get(i).getIsVariable()) {
                    int j = 0;
                    int count = 0;
                    while (j < simplifyPolynomial.get(i).getVariables().length()) {
                        if (simplifyPolynomial.get(i).getVariables().charAt(j) == var)
                            count++;
                        j++;
                    }
                    while (postIndexOfSimplify < input.length() && input.charAt(postIndexOfSimplify) != ' ')
                        postIndexOfSimplify++;
                    String temp = input.substring(preIndexOfSimplify, postIndexOfSimplify);
                    simplifyPolynomial.get(i).setCofficientMul(Math.pow(Double.parseDouble(temp), count));
                    String subTemp = "";
                    for (int k = 0; k < simplifyPolynomial.get(i).getVariables().length(); k++) {
                        if (simplifyPolynomial.get(i).getVariables().charAt(k) == var)
                            continue;
                        subTemp += simplifyPolynomial.get(i).getVariables().charAt(k);
                    }
                    simplifyPolynomial.get(i).setVariables(subTemp);
                }
            }
        }
        return polynomial.combine(simplifyPolynomial);
    }
}
