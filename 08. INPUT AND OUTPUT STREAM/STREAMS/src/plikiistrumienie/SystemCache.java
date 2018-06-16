/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plikiistrumienie;
import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author Witajcie
 */
public class SystemCache {
    protected HashMap<Parameter, Double> cache = new HashMap<>();
    
    public void put( double[] input, double output)
    {
        this.cache.put( new Parameter(input ), output );
    }
    
    public Double get( double[]input )
    {
     return this.cache.get( new Parameter(input ) );
    }

    protected class Parameter
    {
        private double[] values;

        public double[] getValues() {
            return values;
        }
        
        public Parameter(double[] values )
        {
         this.values = values;
        }

        @Override 
        public int hashCode()
        {
            return 31 + Arrays.hashCode( this.values);
        }

        @Override
        public boolean equals( Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(this.getClass() != obj.getClass() )
                return false;
            Parameter other = (Parameter) obj;
            if(!Arrays.equals(this.values, other.values) )
                return false;
            
            return true;
        }
    }

}
