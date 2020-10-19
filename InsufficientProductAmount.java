package citb304.projectstore.v2.multithreading;

/**
 *
 * @author Bibi
 */
public class InsufficientProductAmount extends Exception{
    
    private int productAmount;
    private Goods product;
    
    public InsufficientProductAmount(){}//System.out.println("IPA exception!");}
    
    public InsufficientProductAmount(int pA, Goods p){
        productAmount = pA;
        product = p;
        //System.out.println("IPA Param exception!");
    }
    
    public Object getExceptionProductName(){return product;}
    public int getExceptionProductAmount(){return productAmount;}
    
    @Override
    public String toString() {
        return "EXCEPTION--->InsufficientProductAmount{" + "AmountAvailable: " + productAmount + "; Product:" + product + '}';
    }
    
}

