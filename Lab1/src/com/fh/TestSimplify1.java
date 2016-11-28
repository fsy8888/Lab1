package com.fh;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.fh.*;

public class TestSimplify1 {
    protected String testPol;
    protected String testSim;
  
	@Before
	public void setUp() throws Exception {
	    testPol = "x";
	    testSim = "!simplify x=1";
	}

	@After
	public void tearDown() throws Exception {
	    
	}
	
	@Test
	public void testSimlipfy1() {
	    ExpressionProcessor testProcessor = new ExpressionProcessor();
	    testProcessor.expression(testPol);
	    assertEquals("1.0", testProcessor.getSimplifyExpression().simplify(testProcessor.getPolynomial(), testSim, 0, 0));
	}
}
