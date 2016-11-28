package com.fh;

import java.util.ArrayList;

public class Derivate {
    private boolean isLegal = false;    // the legality of expressions(contain Polynomial, simplify command, and derivation command)    
    private boolean drivate = false;    // the current operation should be derivated or not
    
    public boolean isLegal() {
        return isLegal;
    }
    
    public void setLegal(boolean isLegal) {
        this.isLegal = isLegal;
    }
    
    public boolean isDrivate() {
        return drivate;
    }
    
    public void setDrivate(boolean drivate) {
        this.drivate = drivate;
    }
    
    public void verifyDerivation(Simplify simplifyExpression) {
        this.isLegal = true;
        this.drivate = true;
        simplifyExpression.setLegal(false);
        simplifyExpression.setSimplify(false);
    }
    
    // derivate SubPolynomial
    public void derivation (Polynomial polynomial, String input) {
        ArrayList<SubPolynomial> derivatePolynomial = new ArrayList<>();
        derivatePolynomial = polynomial.clone(polynomial.getSubPolynomial());
        for (int i = 0; i < derivatePolynomial.size(); i++) {   //遍历表达式子项
            if (derivatePolynomial.get(i).getIsVariable()) {    //子项存在变量
                int count = 0;
                int j = 0;
                while (j < derivatePolynomial.get(i).getVariables().length()) {
                    if (derivatePolynomial.get(i).getVariables().charAt(j) == input.charAt(4))
                        count++;
                    j++;
                }
                if (count > 0) {
                    derivatePolynomial.get(i).setCofficientMul(count);
                    String tempDerivateExpression = "";
                    for (int k = 0; k < derivatePolynomial.get(i).getVariables().length(); k++) {
                        if (derivatePolynomial.get(i).getVariables().charAt(k) == input.charAt(4))
                            continue;
                        tempDerivateExpression += derivatePolynomial.get(i).getVariables().charAt(k);
                    }
                    if (count == 2) {
                        tempDerivateExpression += input.charAt(4);
                    }
                    else if (count > 2) {
                        tempDerivateExpression += input.charAt(4) + "^" + (count - 1);
                    }
                    derivatePolynomial.get(i).setVariables(tempDerivateExpression);
                }
                else if (count == 0) {
                    derivatePolynomial.get(i).setCofficientMul(0);
                    derivatePolynomial.get(i).setVariables("");
                }
            }
            else {
                derivatePolynomial.get(i).setCofficientMul(0);
                derivatePolynomial.get(i).setVariables("");
            }
        }
        polynomial.combine(derivatePolynomial);
    }
}
