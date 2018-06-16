/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plikiistrumienie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import plikiistrumienie.SystemCache.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Witajcie
 */
public class ExtendedSystemCache extends SystemCache{
    
   
    public void exportCSV(String path) throws IOException //–wyeksportuje wszystkie przechowywane w mapie obiekty do pliku CSV
    {  
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            exportCSV(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void exportCSV(File file) throws IOException
    {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            exportCSV(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(file)");
        }        
    }
    public void exportCSV(OutputStream stream) throws IOException
    {
        if(stream == null){
            System.out.println("BRAK PLIKU exportCSV(OutputStream)");
            return;
        }
        for (HashMap.Entry<Parameter, Double>  entry : this.cache.entrySet()){
            Parameter key = entry.getKey();
            double[] values = key.getValues();
            Double value = entry.getValue();
            stream.write((value.toString()+",").getBytes());
            for(int i=0; i<values.length;i++){
                stream.write((Double.toString(values[i])+",").getBytes());
            }
            //System.out.println(values.length);
            stream.write("\n".getBytes());
        }     
    }


    public void importCSV(String path) throws IOException //–zaimportuje wszystkie rekordy z pliku CSV do mapy
    { 
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            importCSV(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU importCSV(String)");
        }
    }
    public void importCSV(File file) throws IOException
    {
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            importCSV(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU importCSV(file)");
        }
    }
    public void importCSV(InputStream stream ) throws IOException{
        if(stream == null){
            System.out.println("BRAK PLIKU importCSV(OutputStream)");
            return;
        }
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        while(reader.ready())
        {
             String line = reader.readLine();
             String[] parts = line.split(",");
             System.out.println(parts.length);
             
             double parametrs[] = new double[parts.length-1];
             for(int i=1;i<parts.length;i++){
                 parametrs[i-1] = Double.parseDouble(parts[i]);
                 System.out.println(parametrs[i-1]);
             }
             this.put(parametrs, Double.parseDouble(parts[0]));
        }    
    }

    public void serialize(String path) throws IOException //–zapisze do pliku wszystkie pola znajdujące się w ExtendedSystemCache wykorzystując mechanizm serializacji
    {       
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            serialize(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void serialize (File file) throws IOException
    {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            serialize(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void serialize (OutputStream stream) throws IOException
    {
        if(stream == null){
            System.out.println("BRAK PLIKU exportCSV(OutputStream)");
            return;
         }
        ObjectOutputStream objectOutputStream = null;
        try {            
            objectOutputStream = new ObjectOutputStream(stream);
            for (HashMap.Entry<Parameter, Double>  entry : this.cache.entrySet()){
                objectOutputStream.writeObject(1);
                Parameter key = entry.getKey();
                Double value = entry.getValue();
                objectOutputStream.writeDouble(value);
                objectOutputStream.writeObject(key);
            }    
            objectOutputStream.writeObject(null);
        }
        finally{
            objectOutputStream.close();
        }
    }

    public void deserialize(String path) throws IOException//–odczyta z pliku wszystkie pola znajdujące się w  ExtendedSystemCache wykorzystując mechanizm serializacji
    { 
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            deserialize(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void deserialize(File file) throws IOException
    {
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            deserialize(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void deserialize(InputStream stream) throws IOException{
         if(stream == null){
            System.out.println("BRAK PLIKU exportCSV(OutputStream)");
            return;
         }
        ObjectInputStream objectInputStream = null;
        String obj;
        try {
            objectInputStream = new ObjectInputStream(stream);
            while((obj = (String) objectInputStream.readObject()) != null){
                
                double value = objectInputStream.readDouble();
                Parameter parametrs = (Parameter) objectInputStream.readObject();
            }     
        } 
        catch (ClassNotFoundException ex) {         
            Logger.getLogger(ExtendedSystemCache.class.getName()).log(Level.SEVERE, null, ex);
        }        finally{         
         objectInputStream.close();
        }
    }
       
    
    public void save(String path) throws IOException //–zapisze do pliku wszystkie pola znajdujące się w ExtendedSystemCache wykorzystując mechanizm serializacji
    {       
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            save(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void save(File file) throws IOException
    {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            save(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void save(OutputStream stream) throws IOException
    {
        if(stream == null){
            System.out.println("BRAK PLIKU exportCSV(OutputStream)");
            return;
         }
        ObjectOutputStream objectOutputStream = null;
        try {            
            objectOutputStream = new ObjectOutputStream(stream);
            for (HashMap.Entry<Parameter, Double>  entry : this.cache.entrySet()){
                objectOutputStream.writeObject(1);
                Parameter key = entry.getKey();
                Double value = entry.getValue();
                objectOutputStream.writeDouble(value);
                objectOutputStream.writeObject(key);
            }    
            objectOutputStream.writeObject(null);
        }
        finally{
            objectOutputStream.close();
        }
    }

    public void load(String path) throws IOException//–odczyta z pliku wszystkie pola znajdujące się w  ExtendedSystemCache wykorzystując mechanizm serializacji
    { 
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            load(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void load(File file) throws IOException
    {
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            load(stream);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("BRAK PLIKU exportCSV(string)");
        } 
    }
    public void load(InputStream stream) throws IOException{
         if(stream == null){
            System.out.println("BRAK PLIKU exportCSV(OutputStream)");
            return;
         }
        ObjectInputStream objectInputStream = null;
        String obj;
        try {
            objectInputStream = new ObjectInputStream(stream);
            while((obj = (String) objectInputStream.readObject()) != null){
                
                double value = objectInputStream.readDouble();
                Parameter parametrs = (Parameter) objectInputStream.readObject();
            }     
        } 
        catch (ClassNotFoundException ex) {         
            Logger.getLogger(ExtendedSystemCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{         
         objectInputStream.close();
        }
    }
}
