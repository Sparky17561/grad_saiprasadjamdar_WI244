package com;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Testing Calculator Operations")
@TestInstance(Lifecycle.PER_CLASS)
public class CalculatorTest {
	
	static Calculator c1;
	private boolean flag=true;
	
	@BeforeAll // before all the testing .. load this method only once .. similarly to delete this we will be needing AfterAll .. catch is we have to make the method static .. to make it work  
	public static void createObject() {
		c1 = new Calculator();
		System.out.println("Created Constructor Object");
	}
	
	@BeforeEach // before each test case this will be executed
	public void abc() {
		System.out.println("Initializing testing ");
	}
	
	
	// how to ignore a test case simply either remove @Test or if u want to keep @Test use @Disabled .. it will not execute but in console u can see this disabled .. if u remove @Test there wont be anything in console abt that method
	// u can also provide alias by using ... @DisplayName("message")
	@Test
	@Disabled
	void test() {
		assertTrue(true);
	}
	
	@Test
	@Tag("math")

	public void testAdd() {
		System.out.println(" Testing Add ");
		int actual = c1.add(10,20);
		int expected = 30;
		assertEquals(expected,actual);
	}
	
	@Test
	@Tag("math")

	public void testSub() {
		System.out.println(" Testing Sub ");		assertEquals(-10,c1.sub(10,20));
		assertEquals(20,c1.sub(40,20));
		assertEquals(30,c1.sub(50,20));
		assertEquals(10,c1.sub(30,20));
		
	}
	
	@Test
	@Tag("math")

	public void testMod() {
		System.out.println(" Testing Mod ");
		assertEquals(0,c1.mod(10,2));
		assertEquals(1,c1.mod(40,3));
		assertEquals(2,c1.mod(50,3));
		assertEquals(1,c1.mod(50,7));
		
	}
	
	@Test
	@Tag("math")
	public void testMul() {
		System.out.println(" Testing Multiply ");
		assertAll(
				() -> assertEquals(20,c1.mul(10,2)),
				() -> assertEquals(120,c1.mul(40,3)),
				() -> assertEquals(150,c1.mul(50,3)),
				() -> assertEquals(210,c1.mul(30,7))
				);
		
		
	}
	
	
	@Test
	@Tag("math")
	public void testDiv() {
		System.out.println("Testing Division");
				assertEquals(6,c1.div(30, 5));
				assertThrows(ArithmeticException.class, ()-> c1.div(30,0)); // this is to show that u handled the errors
	}
	
	@AfterAll 
	public static void removeObject() {
		c1 = null;
		System.out.println("Removed the Constructor Object");
	}
	
	@AfterEach // before each test case this will be executed
	public void xyz() {
		System.out.println("Test Completed ");
	}
	
	
	@Test
	@Tag("os")
	@EnabledOnOs(OS.WINDOWS) // will only execute on windows
	public void testDll(){
		System.out.println("testDll()");
	}
	
	@Test
	@Tag("os")
	@EnabledOnOs(value = {OS.MAC,OS.LINUX}) // will execute on these devices
	public void testLambda() {
		System.out.println("testLambda()");
	}
	
	
	@Test
//	@EnabledOnJre(JRE.JAVA_8)
	@EnabledForJreRange(min=JRE.JAVA_8, max=JRE.JAVA_14) // this will execute if in range 
	public void check() {
		System.out.println("this will execute when u have JRE v8");
		assumeTrue(flag); // other way to disable is to assume ur flag to be false ...
	}
}
