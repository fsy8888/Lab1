package com.fh;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSimplify2 {
    protected String testPol;
    protected String testSim;
    
    @Before
    public void setUp() throws Exception {
        testPol = "x*y+2.3";
        testSim = "!simplify x=1";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimplify2() {
        ExpressionProcessor testProcessor = new ExpressionProcessor();
        testProcessor.expression(testPol);
        assertEquals("y+2.3", testProcessor.getSimplifyExpression().simplify(testProcessor.getPolynomial(), testSim, 0, 0));
    }

}
