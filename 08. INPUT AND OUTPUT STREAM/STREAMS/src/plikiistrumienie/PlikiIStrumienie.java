/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plikiistrumienie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Witajcie
 */
public class PlikiIStrumienie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ExtendedSystemCache testowo = new ExtendedSystemCache();
        ExtendedSystemCache testowo2 = new ExtendedSystemCache();
        double[] input = {1.0,2.0,3.0};
        testowo.put(input, 1);
        double[] input2 = {2.0,2.0,3.0};
        testowo.put(input2, 2);
        try {
            testowo.exportCSV(new FileOutputStream("C:\\plik.csv"));
            testowo2.importCSV(new FileInputStream("C:\\plik.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlikiIStrumienie.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(testowo.hashCode());
        System.out.println(testowo2.hashCode());
    }
    
}
