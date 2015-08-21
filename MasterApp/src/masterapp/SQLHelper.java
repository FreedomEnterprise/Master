/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Justin
 */
public class SQLHelper {
    
    private static String dbURL = "jdbc:derby://localhost:1527/priceDB;create=true;user=Justin;password=jwong";
    private static String tableName = "JUSTIN.PRICES";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private float Price;
    private int Hours;
    
    public static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
            
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    public void addEntry(String ProductType, String ProductProperty, String ProductSpec, String ProductOption, float PricePerUnit, int HourPerUnit)
    {
        try
        {
            stmt = conn.createStatement();
            
            //stmt.executeQuery("IDENT_CURRENT('" + tableName + "')");
            //System.out.println("insert into " + tableName + " values('" + ProductType + "','" + ProductProperty + "','" + ProductSpec + "','" + ProductOption + "'," + PricePerUnit + "," + HourPerUnit + ")");
            stmt.execute("insert into " + tableName + " values('" + ProductType + "','" + ProductProperty + "','" + ProductSpec + "','" + ProductOption + "'," + PricePerUnit + "," + HourPerUnit + ")");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    
    public void selectPriceAndHours(String ProductType, String ProductProperty, String ProductSpec, String ProductOption)
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select HourPerUnit, PricePerUnit from " + tableName + " where ProductType='" + ProductType + "' and ProductProperty='" + ProductProperty + "' and ProductSpec='" + ProductSpec + "' and ProductOption='" + ProductOption + "'");
            //System.out.println("select HourPerUnit from " + tableName + " where ProductType='" + ProductType + "' and ProductProperty='" + ProductProperty + "' and ProductSpec='" + ProductSpec + "' and ProductOption='" + ProductOption + "'");
            
            
            if (results.next()){
                this.Price = results.getFloat("PricePerUnit");
                this.Hours = results.getInt("HourPerUnit");
                
            }
            
            
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public float getPrice()
    {
        return Price;
    }
    public int getHours()
    {
        return Hours;
    }
    
    public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }
        finally 
        {
         System.out.println("connection ended");
        }

    }
}
