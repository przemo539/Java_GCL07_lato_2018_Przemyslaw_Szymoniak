/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Kalkulator.BasicCalculator;
import org.junit.Assert;

/**
 *
 * @author Witajcie
 */
public class BasicCalculatorTest {
    private BasicCalculator calculator;
    private static double a;
    private static double b;
    private double delta = 1e-15;
    
    public BasicCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Rozpoczynam sekwencje testu klasy BasicCalculator");
        a=1;
        b=1;
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Koniec sekwencji testow klasy BasicCalculator");
    }
    
    @Before
    public void setUp() {
        calculator = new BasicCalculator();
        a+=1;
        b+=1;
    }
    
    @After
    public void tearDown() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    /*******************************TEST WYJATKOW***********************/
    @Test(expected = IllegalArgumentException.class)
    public void CalculateDivisionShouldThrownIllegalArgumentException() {
       calculator.calculateDivision(0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void CalculateSqlrShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateSqlr(-1);
    }
    
    /***************************TEST wynikow****************************/
    @Test
    public void calculateSumTest1X() {
       double methodReturned = calculator.calculateSum(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a+b), methodReturned, delta);
    }    
    @Test
    public void calculateSumTest2X() {
       double methodReturned = calculator.calculateSum(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a+b), methodReturned, delta);
    }     
    @Test
    public void calculateSumTest3X() {
       double methodReturned = calculator.calculateSum(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a+b), methodReturned, delta);
    }//caclulateDifference
    
    @Test
    public void caclulateDifferenceTest1X() {
       double methodReturned = calculator.caclulateDifference(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a-b), methodReturned, delta);
    }    
    @Test
    public void caclulateDifferenceTest2X() {
       double methodReturned = calculator.caclulateDifference(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a-b), methodReturned, delta);
    }    
    @Test
    public void caclulateDifferenceTest3X() {
       double methodReturned = calculator.caclulateDifference(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a-b), methodReturned, delta);
    }
            
    @Test
    public void calculateMultiplicationTest1X() {
       double methodReturned = calculator.calculateMultiplication(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    }
    @Test
    public void calculateMultiplicationTest2X() {
       double methodReturned = calculator.calculateMultiplication(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    }
    @Test
    public void calculateMultiplicationTest3X() {
       double methodReturned = calculator.calculateMultiplication(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    }
            
    @Test
    public void calculateDivisionTest1x() {
       double methodReturned = calculator.calculateDivision(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a/b), methodReturned, delta);
    }
    @Test
    public void calculateDivisionTest2x() {
       double methodReturned = calculator.calculateDivision(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a/b), methodReturned, delta);
    }
    @Test
    public void calculateDivisionTest3x() {
       double methodReturned = calculator.calculateDivision(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((a/b), methodReturned, delta);
    }
    
    
    @Test
    public void calculatePowTest1x() {
       double methodReturned = calculator.calculatePow(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((Math.pow(a, b)), methodReturned, delta);
    }
    @Test
    public void calculatePowTest2x() {
       double methodReturned = calculator.calculatePow(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((Math.pow(a, b)), methodReturned, delta);
    }
    @Test
    public void calculatePowTest3x() {
       double methodReturned = calculator.calculatePow(a, b);
        //Assert.equals(methodReturned, );
        assertEquals((Math.pow(a, b)), methodReturned, delta);
    }
            
    @Test
    public void calculateSqlrTest1x() {
        System.out.println(a);
       double methodReturned = calculator.calculateSqlr(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.sqrt(a)), methodReturned, delta);
    }
    @Test
    public void calculateSqlrTest2x() {
        System.out.println(a);
       double methodReturned = calculator.calculateSqlr(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.sqrt(a)), methodReturned, delta);
    }
    @Test
    public void calculateSqlrTest3x() {
        System.out.println(a);
       double methodReturned = calculator.calculateSqlr(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.sqrt(a)), methodReturned, delta);
    }
}
