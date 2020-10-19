/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citb304.projectstore.v2.multithreading;

/**
 *
 * @author Bibi
 */
public class Cashier {
    private String cashierName;
    private int cashierIDnumber;
    
    private static int cashierNumberGenerator = 0;
    
    public Cashier(){
        cashierName = "n|a";
        cashierNumberGenerator++;
        cashierIDnumber = cashierNumberGenerator;
        //System.out.println("CASHIER created!");
    }
    
    public Cashier(String n){
        cashierName = n;
        cashierNumberGenerator++;
        cashierIDnumber = cashierNumberGenerator;
        //System.out.println("CASHIER Param created!");
    }
    
    public String getCashierName(){return cashierName;}
    public int getCashierIDnumber(){return cashierIDnumber;}
    
    private void setCashierName(String n){cashierName = n;}
    
    @Override
    public String toString() { 
        return "CASHIER: " + cashierName + 
                "; ID number: " + cashierIDnumber; 
    }
}
