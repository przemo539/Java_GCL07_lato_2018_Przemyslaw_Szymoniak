/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kalkulator;

/**
 *
 * @author Witajcie
 */
public class FieldCalcuklator {
    public double calculateSquare(double a){
        if(a <=0){
             throw new IllegalArgumentException("Bok kwadratu mniejszy od 0");
        }else{
            return a*a;
        }
    }
    public double calculateCircle(double r){
        if(r <= 0){
             throw new IllegalArgumentException("Promien mniejszy od zero");
        }else{
            return Math.PI*r*r;
        }
    }
    public double calculateTriangle(double base, double height){
        if(base <=0){
             throw new IllegalArgumentException("Podstawa trójkąta mniejsza od zero");
        }else if(height <=0){
             throw new IllegalArgumentException("Wysokość mniejsza od zero");
        }else{
           return (0.5*base*height);
        }
    }
    public double calculateRectangle(double a, double b){
        if(a <=0){
             throw new IllegalArgumentException("Pierwszy bok prostokata mniejszy od 0");
        }else if(b <=0){
             throw new IllegalArgumentException("Drugi bok prostokata mniejsz od 0");
        }else{
            return a*b;
        }
    }
}
