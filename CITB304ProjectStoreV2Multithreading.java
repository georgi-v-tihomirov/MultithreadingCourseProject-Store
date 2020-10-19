package citb304.projectstore.v2.multithreading;
import java.util.*; 
/**
 *
 * @author Bibi
 */
public class CITB304ProjectStoreV2Multithreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Goods> toBeSold = new ArrayList();
        Goods boza = new Goods("Sofiiska", 2.00, "25.02.1920");
        toBeSold.add(boza);
        //Goods shokolad = new Goods("Milka", 2.00, "22.04.2020");
        //Goods shokolad1 = new Goods("Svoge", 3.00, "22.04.2020");
        //toBeSold.add(shokolad1);
        //toBeSold.add(shokolad);
        
        
        List<Goods> toBeSold1 = new ArrayList();
        Goods vafla = new Goods("Harmonika", 1.00, "15.07.2010");
        Goods chips = new Goods("Ruffles", 2.00, "17.04.2014");
        toBeSold1.add(vafla);
        toBeSold1.add(chips);    
        //toBeSold1.add(shokolad1);
        //toBeSold1.add(shokolad1);
        //toBeSold1.add(shokolad1);
        //toBeSold1.add(boza);
        
        List<Cashier> cashierList = new ArrayList();
        Cashier c1 = new Cashier("Toti");
        Cashier c2 = new Cashier("Nikolay");
        cashierList.add(c1);
        cashierList.add(c2);
        
        List<Goods> supermarket = new ArrayList();   
        Store Fantastico = new Store(supermarket, cashierList);
        Fantastico.loadProduct(vafla);
        Fantastico.loadProduct(boza);
        Fantastico.loadProduct(chips);
        Fantastico.showStore();
        //Fantastico.loadProduct(shokolad);
        //Fantastico.loadProduct(shokolad1);
        
        PayDesk payDesk1 = new PayDesk("First", c1, toBeSold, Fantastico);
        PayDesk payDesk2 = new PayDesk("Second", c2, toBeSold1, Fantastico);
        Thread p1 = new Thread(payDesk1);
        Thread p = new Thread(payDesk2);
        
        try{
            p1.start();
            p.start();
            p1.join();
            p.join();
        }
        catch(Exception w){
            System.err.println(w);
        }
        
        //Show the income of each paydesk
        System.out.println("First payDesk income " + payDesk1.getRecepieValue());
        System.out.println("Second payDesk income " + payDesk2.getRecepieValue());

        System.out.println("Store income " + Fantastico.getStoreIncome());
        
        Fantastico.hireCashier(new Cashier("Thanya"));
        Fantastico.showStore();
        Fantastico.fireCashier("Thanya");//Fantastico.fireCashier(c1)
        Fantastico.showStore();
        //example for NoSuchElementException
        /*
        try{
            Fantastico.fireCashier("Toi");
        }catch(Exception e){
            System.err.println(e);
        }*/
    }
    
}
