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
public class BasicCalculator {
   public double calculateSum(double one, double two){
        return (one + two);
    }

   public double caclulateDifference(double one, double two){
        return (one-two);
    }

  public double calculateMultiplication(double one, double two){

        return (one * two);
    }

  public  double calculateDivision(double one, double two){
        if(two == 0) {
             throw new IllegalArgumentException("Nie mozna dzielić przez 0");
        }else{
            return (one / two);
        }

    }

  public  double calculatePow(double one, double two){
        return Math.pow(one, two);
    }

  public  double calculateSqlr(double one){
        if(one <0){
             throw new IllegalArgumentException("Pierwiastkowanie liczby ujemnej");
        }else{
            return Math.sqrt(one);
        }
    }
}
