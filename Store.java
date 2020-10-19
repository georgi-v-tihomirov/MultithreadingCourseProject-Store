package citb304.projectstore.v2.multithreading;
import java.util.List;
import java.util.ArrayList;
import java.util.*; 
/**
 *
 * @author Bibi
 */
public class Store {
    private List<Cashier> storeCashierList;
    private List<Goods> storeGoodsList;
    private int receiptsCount;
    private double storeIncome = 0;
        
    public Store(){
        storeGoodsList = new ArrayList();
        storeCashierList = new ArrayList();
        receiptsCount = 0;
        storeIncome = 0;
        System.out.println("STORE created!");
    }
    
    public Store(List<Goods> sGL, List<Cashier> sCL){
        //storeGoodsList = Collections.synchronizedList(new ArrayList(sGL));
        storeGoodsList = sGL;
        storeCashierList = sCL;
        System.out.println("STORE Param created!");
    }
       
    public List<Goods> getStoreGoodsList(){return storeGoodsList;}
    public List<Cashier> getStoreCashierList(){return storeCashierList;}
    public int getReceiptsCount(){return receiptsCount;}
    public double getStoreIncome(){return storeIncome;}
    
    public void setStoreIncome(double price){storeIncome += price;}
    private void setStoreGoodsList(List<Goods> sGL){storeGoodsList = sGL;}
    private void setStoreCashierList(List<Cashier> sCL){storeCashierList = sCL;}
    public void setReceiptsCount(int n){receiptsCount += n;}
    
    public void hireCashier(Cashier c){
        if(!storeCashierList.contains(c)) storeCashierList.add(c);
    }
    
    public void hireCashier(String name){
        Cashier c = new Cashier(name);
        if(!storeCashierList.contains(c)) storeCashierList.add(c);
    }
    
    //The fireCashier() with OBJECT detection will let us work if the Cashier is passed as an object or as a name
    public void fireCashier(Object cashier) throws NoSuchElementException, IllegalArgumentException {
        if(cashier instanceof String){
            for(Cashier c : storeCashierList) 
                if(c.getCashierName().equals(cashier)){ 
                    storeCashierList.remove(c);
                    return;
                }
            throw new NoSuchElementException("The cashier doesn't work in this store!");
        }
        else if(cashier instanceof Cashier){
            if(storeCashierList.contains(cashier)){ 
                storeCashierList.remove(cashier); 
                return;
            }
            throw new NoSuchElementException("The cashier doesn't work in this store!");
        }
        else throw new IllegalArgumentException("IllegalArgumentException caught!"); 
    }
  
    public void loadProduct(Goods product){this.storeGoodsList.add(product);}
    
    /*
    private void sellProductPassedByProductName(String name, int amount) throws NoSuchElementException, InsufficientProductAmount {
        int countInList = 0;//A variable to count the occurances of the element in the List
        //check if amount is enough
        for(Goods g : storeGoodsList) {if(g.getGoodsName().equals(name)){countInList++;}}
        //If there is no amount of this product
        if(countInList == 0) throw new NoSuchElementException(name);
        //If there is not enough product to be sold
        if(countInList < amount) throw new InsufficientProductAmount(countInList, name);
        countInList = 0;//We set the counter to zero, because (for example) we want to sell 2 chocolates, but we found 7
        Iterator<Goods> iter = storeGoodsList.iterator();//The iterator is a way to iterate through the list and remove the elements at the same time
        Goods g;
        while(iter.hasNext() && countInList < amount){//While there is next element and we still have to sell
            g = iter.next();
            if(g.getGoodsName().equals(name)){
                setStoreIncome(g.getGoodsPrice());//We increase the income of the store with the price of the sold product
                iter.remove();
                countInList++;
            }
        }
    }
    
    private void sellProductPassedAsAnObject(Goods product, int amount)throws NoSuchElementException, InsufficientProductAmount {
        int countInList = 0;//A variable to count the occurances of the element in the List
        //check if amount is enough
        for(Goods g : storeGoodsList) {if(g.equals(product)){countInList++;}}
        //If there is no amount of this product
        if(countInList == 0) throw new NoSuchElementException(product);
        //If there is not enough product to be sold
        if(countInList < amount) throw new InsufficientProductAmount(countInList, product);
        countInList = 0;//We set the counter to zero, because (for example) we want to sell 2 chocolates, but we found 7
        Iterator<Goods> iter = storeGoodsList.iterator();//The iterator is a way to iterate through the list and remove the elements at the same time
        Goods g;
        while(iter.hasNext() && countInList < amount){//While there is next element and we still have to sell
            g = iter.next();
            if(g.equals(product)){
                setStoreIncome(g.getGoodsPrice());//We increase the income of the store with the price of the sold product
                iter.remove();
                countInList++;
            }                    
        }
    }
    
    public void sellProduct(Object product, int amount) throws IllegalArgumentException, NoSuchElementException, InsufficientProductAmount{
        //Check if amount is positive
        if(amount < 0) throw new IllegalArgumentException("Only selling positive amount of product: PassedAmount:" + amount);        
        //Code to be executed if the Object is instance of String - the passed value is the NAME OF THE PRODUCT
        if(product instanceof String){
            String p = product.toString();
            sellProductPassedByProductName(p, amount);
        }
        //Code to be executed if the Object is <Goods> - the passed value is a PRODUCT
        else{
            Goods p = (Goods)product;
            sellProductPassedAsAnObject(p, amount);
        }
    }
       */
    
    //Function to show the Store
    public void showStore(){
        System.out.println("\nPRODUCTS AVAILABLE");
        for(Goods p : storeGoodsList) System.out.println(" " + p);
        System.out.println("\nCASHIERS");
        for(Cashier c : storeCashierList) System.out.println(" " + c);
    }
     
    @Override
    public String toString() { 
        return "STORE:\nPRODUCTS AVAILABLE\n " + storeGoodsList + "\nCASHIERS:\n " + storeCashierList + "\n"; 
    }
}
