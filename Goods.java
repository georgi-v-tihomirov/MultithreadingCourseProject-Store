package citb304.projectstore.v2.multithreading;

/**
 *
 * @author Bibi
 */
public class Goods {
    
    private int goodsIdentityNumber = 0;
    private String goodsName;
    private double goodsPrice;
    private String expirationDate;
    
    private static int goodsNumberGenerator = 0;
    public Goods(){
        goodsNumberGenerator++;
        goodsIdentityNumber = goodsNumberGenerator;
        goodsName = "n|a";
        goodsPrice = 0;
        expirationDate = "n|a";
        //System.out.println("GOODS created!");
    }
    
    public Goods(String n, double price, String expirationDate){
        goodsNumberGenerator++;
        goodsIdentityNumber = goodsNumberGenerator;
        goodsName = n;
        goodsPrice = price;
        this.expirationDate = expirationDate;
        //System.out.println("GOODS PARAM created!");
    }
    
    public String getGoodsName(){return goodsName;}
    public String getGoodsExpirationDate(){return expirationDate;}
    public int getGoodsIdentityNumber(){return goodsIdentityNumber;}
    public double getGoodsPrice(){return goodsPrice;}
    
    private void setGoodsName(String n){goodsName = n;}
    private void setGoodsExpirationDate(String expirationDate){this.expirationDate = expirationDate;}
    private void setGoodsPrice(double price){goodsPrice = price;}
    
    @Override
    public String toString() { 
        return "Product: " + goodsName + 
                ";\n  Price: " + goodsPrice + 
                ";\n  ID number: " + goodsIdentityNumber + 
                ";\n  Exp. date: " + expirationDate; 
    }
}
