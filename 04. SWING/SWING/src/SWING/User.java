/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWING;

/**
 *
 * @author Witajcie
 */
     public class User{
        static User adres;

    public static User getAdres() {
        return adres;
    }

    public static void setAdres(User adres) {
        User.adres = adres;
    }
        public String Nazwa_studenta;
        public String Temat_pracy;
        public int Stron_pracy;
        public String Nazwa_promotora;
        public double Ocena_promotora;
        public String Nazwa_recenzenta;
        public double Ocena_recenzenta;
        
        public User( String Nazwa_studenta, String Temat_pracy, int Stron_pracy, String Nazwa_promotora, double Ocena_promotora, String Nazwa_recenzenta, double Ocena_recenzenta)
        {
            this.Nazwa_studenta = Nazwa_studenta;
            this.Temat_pracy = Temat_pracy;
            this.Stron_pracy = Stron_pracy;
            this.Nazwa_promotora = Nazwa_promotora;
            this.Ocena_promotora = Ocena_promotora;
            this.Nazwa_recenzenta = Nazwa_recenzenta;
            this.Ocena_recenzenta = Ocena_recenzenta;
        }

        User() {
             
        }
        
        public void Set(String Nazwa_studenta, String Temat_pracy, int Stron_pracy, String Nazwa_promotora, double Ocena_promotora, String Nazwa_recenzenta, double Ocena_recenzenta)
        {
            this.Nazwa_studenta = Nazwa_studenta;
            this.Temat_pracy = Temat_pracy;
            this.Stron_pracy = Stron_pracy;
            this.Nazwa_promotora = Nazwa_promotora;
            this.Ocena_promotora = Ocena_promotora;
            this.Nazwa_recenzenta = Nazwa_recenzenta;
            this.Ocena_recenzenta = Ocena_recenzenta;
        }
    }