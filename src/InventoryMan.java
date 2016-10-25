package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class InventoryMan {
	
	public void uploadNewItem(){
		Scanner reader = new Scanner(System.in);
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         
	         //getting the database, user, password:
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5433/test",
	            "postgres", "aaronrocks");
	         
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         
	         Item newItem = new Item();
	         
	           // Reading from System.in
	         System.out.println("Please enter a new sku: ");
	         newItem.setSku(reader.nextInt());
	         System.out.println("Please enter name of product: ");
	         newItem.setName(reader.next());
	         System.out.println("Please enter quantity: ");
	         newItem.setQuantity(reader.nextDouble());
	         System.out.println("Please enter price: ");
	         newItem.setPrice(reader.nextDouble());
	         System.out.println("Please enter distributor: ");
	         newItem.setDist(reader.next());
	         System.out.println("Please enter weight: ");
	         newItem.setWeight(reader.next());
	         
	         //the commands that you are utilizing in the database
	         String sql = "INSERT INTO INVENTORY (SKU,NAME,QUANTITY,PRICE,DISTRIBUTOR,WEIGHT) "
	               + "VALUES (" + Integer.toString(newItem.getSku()) + ", '" + newItem.getName() + "', " 
	        		 + Double.toString(newItem.getQuantity()) + ", " + Double.toString(newItem.getPrice()) 
	        		 + ", '" + newItem.getDist() + "', '" + newItem.getWeight() + "');";
	         
	         //updates to the sql server
	         stmt.executeUpdate(sql);

	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	}
	
	static boolean intOrNot(String input){
		try{
			Integer.parseInt(input);
		}
		catch(NumberFormatException ex){
			return false;
		}
		return true;
	}
	
	public String searchItem(String searchItem){
		Scanner reader = new Scanner(System.in);
		Item invItem = new Item();
		Connection c = null;
	      Statement stmt = null;
	      try {
	      Class.forName("org.postgresql.Driver");
	        c = DriverManager
	           .getConnection("jdbc:postgresql://localhost:5433/test",
	           "postgres", "aaronrocks");
	        c.setAutoCommit(false);
	        System.out.println("Opened database successfully");

	        stmt = c.createStatement();
	        ResultSet rs;
	        //System.out.print("Search: ");
	        
	        //checks what kind of input
	        if(searchItem.matches(".*\\d.*")){
	        	rs = stmt.executeQuery( "SELECT * FROM INVENTORY WHERE sku = " + searchItem + ";");
	        }
//	        else if(reader.hasNextDouble()){
//	        	String doubleSearch = searchItem;
//	        	rs = stmt.executeQuery( "SELECT * FROM INVENTORY WHERE quantity = " + doubleSearch + " OR "
//	        			+ "price = " + doubleSearch + ";");
//	        }
	        else{
	        	String input = searchItem;
		        rs = stmt.executeQuery( "SELECT * FROM INVENTORY WHERE name LIKE '" + input + "%' OR "
		        		+ "distributor LIKE '" + input + "%' OR "
		        				+ "weight LIKE '" + input + "%';");
	        }
	        
	        System.out.println("here");
	        
	        while ( rs.next() ) {
	        	//
	           invItem.setSku(rs.getInt("sku"));
	           invItem.setName(rs.getString("name"));
	           invItem.setQuantity(rs.getFloat("quantity"));
	           invItem.setPrice(rs.getFloat("price"));
	           invItem.setDist(rs.getString("distributor"));
	           invItem.setWeight(rs.getString("weight"));
	           
	           //print out:
	           System.out.println(invItem.toString());	//needs to output to a jtextarea
	           
	        }
	        rs.close();
	        stmt.close();
	        c.close();
	        
	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	        System.exit(0);
	      }
	      System.out.println("Operation done successfully");
		return invItem.toString();
	      
	}
}
