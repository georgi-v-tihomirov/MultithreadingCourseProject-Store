package citb304.projectstore.v2.multithreading;

/**
 *
 * @author Bibi
 */
public class NoSuchElementException extends RuntimeException{
    
    private Object detail;
    
    public NoSuchElementException(){}
    
    public NoSuchElementException(Object s){
        detail = s;
        //System.out.println("NSEE Param exception!");
    }
    
    @Override
    public String toString() {
        return "\nEXCEPTION--->NoSuchElementFound{ ELEMENT: " + detail + " }\n";
    }
}
