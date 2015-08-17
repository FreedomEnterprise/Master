/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterapp;

/**
 *
 * @author Justin
 */
public class MasterApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SQLHelper.createConnection();
        //insertRestaurants(5, "LaVals", "Berkeley");
        SQLHelper foo = new SQLHelper();
        foo.selectPriceAndHours("PartitionWall","Frame", "Height", "9");
        //foo.addEntry("ParititionWall","Frame", "Height", "12", 13, 53);
        System.out.println(foo.getHours());
        SQLHelper.shutdown();
    }
    
}
