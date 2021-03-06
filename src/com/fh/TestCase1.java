package com.fh;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCase1 {

	ExpressionProcessor testExp;
	@Before
	public void setUp() throws Exception {
		testExp = new ExpressionProcessor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		
		testExp.expression("x + y * 2");
		assertEquals(true, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
		
	}
	
	@Test
	public void test2() {
		testExp.expression("a+y");
		assertEquals(true, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test3() {
		testExp.expression("x + y ");
		testExp.expression("!d/dx");
		assertEquals(true, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(true, testExp.isDrivate());
	}
	@Test
	public void test4() {
		testExp.expression("x + y ");
		testExp.expression("!simplify x=1");
		assertEquals(true, testExp.isLegal());
		assertEquals(true, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test5() {
		testExp.expression("+a*");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test6() {
		testExp.expression("&&&7&");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test7() {
		testExp.expression("a*b+s");
		assertEquals(true, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test8() {
		testExp.expression("?*(5-4)");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test9() {
		testExp.expression("xx+y");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test10() {
		testExp.expression("x+y");
		testExp.expression("!p");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test11() {
		testExp.expression("x+y");
		testExp.expression("!  Simplify");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test12() {
		testExp.expression("keep");
		
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test13() {
		testExp.expression("x++y");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	
	
	@Test
	public void test14() {
		testExp.expression("x+y");
		testExp.expression("!simplify x=1 x=2");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test15() {
		testExp.expression("x+y");
		testExp.expression("!  Simplify x=1 y");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test16() {
		testExp.expression("x+y");
		testExp.expression("!simplify z=1");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	@Test
	public void test17() {
		testExp.expression("x+y");
		testExp.expression("!simplify xy=1");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}

	@Test
	public void test18() {
		testExp.expression("x + y ");
		testExp.expression("!d /da");
		assertEquals(false, testExp.isLegal());
		assertEquals(false, testExp.isSimplify());
		assertEquals(false, testExp.isDrivate());
	}
	
	
	
	
	
	
	
}
