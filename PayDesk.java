package citb304.projectstore.v2.multithreading;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Bibi
 */
public class PayDesk implements Runnable{
    
    final private Store store;
    final private List<Goods> listItemsToBeSold;
    final private Thread payDesk;
    final private Cashier cashier;
    private double recepieIncome = 0;
    private int receiptsCountGenerator = 0;
    
    public PayDesk(String n, Cashier c, List<Goods> g, Store s){
        store = s;
        listItemsToBeSold = g;
        //listItemsToBeSold = Collections.synchronizedList(new ArrayList(g));
        payDesk = new Thread(this, n);
        cashier = c;
        //System.out.println("PAYDESK CREATED!");
    }

    /*
    private synchronized void sellProductPassedAsAnObject(Goods product, int amount)throws NoSuchElementException, InsufficientProductAmount {
        int countInList = 0;//A variable to count the occurances of the element in the List
        //check if amount is enough
        for(Goods g : store.getStoreGoodsList()) {if(g.equals(product)){countInList++;}}
        //If there is no amount of this product
        if(countInList == 0) throw new NoSuchElementException(product);
        //If there is not enough product to be sold
        if(countInList < amount) throw new InsufficientProductAmount(countInList, product);
        countInList = 0;//We set the counter to zero, because (for example) we want to sell 2 chocolates, but we found 7
        synchronized(store.getStoreGoodsList()){
            try{
            store.getStoreGoodsList().removeAll(listItemsToBeSold);
            }
            catch(Exception e){
                System.out.println(e);
            }
            //store.setStoreIncome(g.getGoodsPrice());//We increase the income of the store with the price of the sold product     
              
            try{
                generateRecepies();
            }   
            catch(Exception e){
                System.err.println(e);
            }
            countInList++;
            }   
            
        }
    
    */
    
    public Cashier getPayDeskCashier(){return cashier;}
    public double getRecepieValue(){return recepieIncome;}
    private void setRecepieValue(double p){recepieIncome += p;}
    
    private void generateRecepies() throws IOException{
        store.setReceiptsCount(receiptsCountGenerator++);
        
        String data = receiptsCountGenerator + System.lineSeparator() + cashier + System.lineSeparator() +
                " Time: " + LocalTime.now() + System.lineSeparator() +
                " Date: " + LocalDate.now() +
                System.lineSeparator() + listItemsToBeSold;
        
        String filename = "C:\\Users\\georg\\Documents\\NBU\\NetBeansProjects\\CITB304-ProjectStore-V2-Multithreading\\src\\citb304\\projectstore\\v2\\multithreading";
        try(FileWriter f0 = new FileWriter(new File(filename + receiptsCountGenerator + ".txt"))) {
            f0.write(data);
        } catch(Exception e) {
            System.err.println(e);
        }        
    }
    
    private synchronized void sellProductPassedAsAnObject(Goods product, int amount)throws NoSuchElementException, InsufficientProductAmount {
        int countInList = 0;//A variable to count the occurances of the element in the List
        //check if amount is enough
        for(Goods g : store.getStoreGoodsList()) {if(g.equals(product)){countInList++;}}
        //If there is no amount of this product
        if(countInList == 0) throw new NoSuchElementException(product);
        //If there is not enough product to be sold
        if(countInList < amount) throw new InsufficientProductAmount(countInList, product);
        countInList = 0;//We set the counter to zero, because (for example) we want to sell 2 chocolates, but we found 7
        //synchronized(store.getStoreGoodsList()){
            Goods g;
            Iterator<Goods> iter = store.getStoreGoodsList().iterator();//The iterator is a way to iterate through the list and remove the elements at the same time
            while(iter.hasNext() && countInList < amount){//While there is next element and we still have to sell
                g = iter.next();
                if(g.equals(product)){
                    store.setStoreIncome(g.getGoodsPrice());
                    setRecepieValue(g.getGoodsPrice());
                    iter.remove();
                    try{
                        generateRecepies();
                    }   
                    catch(Exception e){
                        System.err.println(e);
                    }
                    countInList++;
                }   
            }
        //}
    }
    
    public void sellProduct(Goods product, int amount) throws IllegalArgumentException, NoSuchElementException, InsufficientProductAmount{
        if(amount <= 0) throw new IllegalArgumentException("Only selling positive amount of product: PassedAmount:" + amount);        
        else {
            sellProductPassedAsAnObject(product, amount);
        }
    }
    
    @Override
    public void run(){
        try{
            //System.out.println("Running PAYDESK thread!");
            System.out.println("Cashier: " + cashier + " at " + payDesk.getName() + "\n");
            for(Goods g : listItemsToBeSold) {
                sellProduct(g, 1);
                System.out.println("Sold Product: by " + payDesk + "\n" + g);
            }
        }
        catch(Exception e){
            System.err.println(e);
        }       
    }
}
