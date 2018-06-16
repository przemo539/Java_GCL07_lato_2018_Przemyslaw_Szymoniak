/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Kalkulator.FieldCalcuklator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Witajcie
 */
public class FieldCalcuklatorTest {
    private static double a;
    private static double b;
    private double delta = 0;
    private FieldCalcuklator calculator;
    public FieldCalcuklatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
          System.out.println("Rozpoczynam sekwencje testu klasy FieldCalcuklator");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Koniec sekwencji testow klasy FieldCalcuklator");
    }
    
    @Before
    public void setUp() {
        calculator = new FieldCalcuklator();
        a+=1;
        b+=1;
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    // SQUARE
    @Test(expected = IllegalArgumentException.class)
    public void calculateSquareShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateSquare(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateSquareShouldThrownIllegalArgumentExceptionOnZero() {
       calculator.calculateSquare(0);
    }
     @Test
    public void calculateSquareTest1X() {
       double methodReturned = calculator.calculateSquare(a);
        //Assert.equals(methodReturned, );
        assertEquals((a*a), methodReturned, delta);
    }  
     @Test
    public void calculateSquareTest2X() {
       double methodReturned = calculator.calculateSquare(a);
        //Assert.equals(methodReturned, );
        assertEquals((a*a), methodReturned, delta);
    }  
     @Test
    public void calculateSquareTest3X() {
       double methodReturned = calculator.calculateSquare(a);
        //Assert.equals(methodReturned, );
        assertEquals((a*a), methodReturned, delta);
    }  
    
    
    //CIRCLE
    @Test(expected = IllegalArgumentException.class)
    public void calculateCircleShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateCircle(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateCircleShouldThrownIllegalArgumentExceptionOnZero() {
       calculator.calculateCircle(0);
    }
     @Test
    public void calculateCircleTest1X() {
       double methodReturned = calculator.calculateCircle(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.PI*a*a), methodReturned, delta);
    } 
     @Test
     public void calculateCircleTest2X() {
       double methodReturned = calculator.calculateCircle(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.PI*a*a), methodReturned, delta);
    } 
    @Test
    public void calculateCircleTest3X() {
       double methodReturned = calculator.calculateCircle(a);
        //Assert.equals(methodReturned, );
        assertEquals((Math.PI*a*a), methodReturned, delta);
    } 
    
    
    //TRIANGLE
    @Test(expected = IllegalArgumentException.class)
    public void calculateTriangleBaseShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateTriangle(-1, 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateTriangleBaseShouldThrownIllegalArgumentExceptionOnZero() {
       calculator.calculateTriangle(0, 5);
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void calculateTriangleHeightShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateTriangle(5, -5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateTriangleHeightShouldThrownIllegalArgumentExceptionOnZero() {
       calculator.calculateTriangle(5, 0);
    }
    @Test
    public void calculateTriangleTest1X() {
       double methodReturned = calculator.calculateTriangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((0.5*a*b), methodReturned, delta);
    } 
    @Test
    public void calculateTriangleTest2X() {
       double methodReturned = calculator.calculateTriangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((0.5*a*b), methodReturned, delta);
    } 
    @Test
    public void calculateTriangleTest3X() {
       double methodReturned = calculator.calculateTriangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((0.5*a*b), methodReturned, delta);
    } 
    
    
    //RECTANGLE
     @Test(expected = IllegalArgumentException.class)
    public void calculateRectangleAShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateRectangle(-1, 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateRectangleBShouldThrownIllegalArgumentExceptionOnMinus() {
       calculator.calculateRectangle(5, -5);
    }
    @Test
    public void calculateRectangleTest1X() {
       double methodReturned = calculator.calculateRectangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    } 
    @Test
    public void calculateRectangleTest2X() {
       double methodReturned = calculator.calculateRectangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    } 
    @Test
    public void calculateRectangleTest3X() {
       double methodReturned = calculator.calculateRectangle(a,b);
        //Assert.equals(methodReturned, );
        assertEquals((a*b), methodReturned, delta);
    } 
    
}
