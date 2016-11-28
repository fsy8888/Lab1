package com.fh;
/**
 * @author hp
 * UTF-8 encoding
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionProcessor {
    private int preIndexOfExpression;	// the pre index in the expressions(contain Polynomial, simplify command, and derivation command)
	private int postIndexOfExpression;	// the post index in the expressions(contain Polynomial, simplify command, and derivation command)
	private Polynomial polynomial = new Polynomial();
	private Simplify simplifyExpression = new Simplify();
	private Derivate derivateExpression = new Derivate();
	private Scanner in = new Scanner(System.in);
	
	public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public Simplify getSimplifyExpression() {
        return simplifyExpression;
    }

    public void setSimplifyExpression(Simplify simplifyExpression) {
        this.simplifyExpression = simplifyExpression;
    }

    public Derivate getDerivateExpression() {
        return derivateExpression;
    }

    public void setDerivateExpression(Derivate derivateExpression) {
        this.derivateExpression = derivateExpression;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    // check the input is whether legal or not, and process it primarily
	public void expression (String tempInput) {
		this.preIndexOfExpression = 0;
		this.postIndexOfExpression = 0;
		
		// check the type of input and its legality
		if (tempInput.equals("")) {
		    ErrorMessage.proceException(ErrorMessage.NULL_INPUT);
			return;
		}
		else if (tempInput.charAt(postIndexOfExpression) != '!') {	// if it's SubPolynomial
		    polynomial.verifyPolynomial(tempInput, preIndexOfExpression, postIndexOfExpression);
		}
		else if (polynomial.getPolynomial() != null) {
			if (tempInput.length() == 5 && tempInput.substring(1, 4).equals("d/d")) {
			    derivateExpression.verifyDerivation(simplifyExpression);
			}
			else if (tempInput.length() >= 13 && tempInput.substring(1, 9).equals("simplify")) {
				// check the legality of simplify command
			    simplifyExpression.verifySimplify(polynomial, derivateExpression, tempInput, postIndexOfExpression);
			}
			else {
			    simplifyExpression.setLegal(false);
			    simplifyExpression.setSimplify(false);
			    derivateExpression.setLegal(false);
			    derivateExpression.setDrivate(false);
				ErrorMessage.proceException(ErrorMessage.GRAMMAR_ERROR);
			}
		}
		else {
		    simplifyExpression.setLegal(false);
            simplifyExpression.setSimplify(false);
            derivateExpression.setLegal(false);
            derivateExpression.setDrivate(false);
			ErrorMessage.proceException(ErrorMessage.NULL_EXPRESSION);
		}
		
		if (polynomial.isLegal() && simplifyExpression.isSimplify()) {
		    simplifyExpression.simplify(polynomial, tempInput, preIndexOfExpression, postIndexOfExpression);
		}
		else if (polynomial.isLegal() && derivateExpression.isDrivate()) {
		    derivateExpression.derivation(polynomial, tempInput);
		}
	}
}