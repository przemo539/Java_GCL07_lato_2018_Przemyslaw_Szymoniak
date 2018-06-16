/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import static javafx.scene.input.KeyCode.T;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import plikiistrumienie.ExtendedSystemCache;


/**
 *
 * @author Witajcie
 */
public class sprawdzaniePoprawnosciImportuCSV {
    
    public sprawdzaniePoprawnosciImportuCSV() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void compareTwoObject() throws IOException {
        ExtendedSystemCache test1 = new ExtendedSystemCache();
        ExtendedSystemCache test2 = new ExtendedSystemCache();
        double[] input = {1.0,2.0,3.0};
        test1.put(input, 1);
        test1.serialize("C:\\plik.csv");
        test2.deserialize("C:\\plik.csv");
       assertReflectionEquals();
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
