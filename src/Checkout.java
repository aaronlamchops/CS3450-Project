package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Checkout {
	
	Receipt checkReceipt;
	Vector<Item> checkoutItems;
	Vector<Double> itemQuantity;
	
	public Checkout(){
		checkReceipt = new Receipt();
		checkoutItems = new Vector<Item>();
		itemQuantity = new Vector<Double>();
	}
	
	public void askForSku(){
        //read input
		Scanner reader = new Scanner(System.in);
		String input = new String(" ");
		while(!input.equals("q")){
			System.out.println("Enter the sku: ");
			input = reader.next();
			if(!input.equals("q")) {
				this.addItems(input);
			}
			
		}
	}
	
	// Function utilized in creating a new order
	public void addItems(String s){
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
	         

	         Item getItem = new Item();
	         
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM INVENTORY WHERE sku = " + s + ";" );
	         while ( rs.next() ) {
	        	 getItem.setSku(rs.getInt("sku"));
	        	 getItem.setName(rs.getString("name"));
	        	 getItem.setQuantity(rs.getFloat("quantity"));
	        	 getItem.setPrice(rs.getFloat("price"));
	        	 getItem.setDist(rs.getString("distributor"));
	        	 getItem.setWeight(rs.getString("weight"));
	        	 
	        	 checkoutItems.add(getItem);
	         }
	         
	         
	         rs.close();
	         stmt.close();
	         c.close();
	       } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	       }
	       System.out.println("Operation done successfully");
	}
	
	// function in adding payment type to an order
	public void addPaymentType(String payType){
		checkReceipt.setPayMethod(payType);
		
		//get time and date
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yy");
	    checkReceipt.setDate(ft.format(dNow).toString());
	    ft = new SimpleDateFormat("h:mma");
	    checkReceipt.setTime(ft.format(dNow).toString());
	    
	}
	
	// takes everything stored in private checkoutItems and adds to a receipt
	public void createRecTrans(){
		checkReceipt.setItemVector(checkoutItems);
		checkReceipt.createReceipt();
	}
	
	public double findItemQuantity(int sku){
		
		Vector<Integer> onlySku = new Vector<Integer>();
        for (int j = 0; j < checkoutItems.size(); j++){
        	onlySku.add(checkoutItems.elementAt(j).getSku());	//convert the item list to a vector of only the total sku's
        }
		double occurrences = Collections.frequency(onlySku, sku);	//find the amount of sku's that are the same
		return occurrences;
		
	}
	
	//function to update the inventory; only should be called after customer approves transaction
	public void updateInventory(){
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
	    Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/test","postgres", "aaronrocks");
	        c.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
	        //create a vector of the unique number of sku's
	        Vector<Integer> uniqueItemSet = new Vector<Integer>();
	        for (int j = 0; j < checkoutItems.size(); j++){
	        	if(!uniqueItemSet.contains(checkoutItems.elementAt(j).getSku())){	//if its not in the vector, add it(doesnt add duplicates)
	        		uniqueItemSet.add(checkoutItems.elementAt(j).getSku());
	        	}
	        }
	        //iterate through and find the total amount purchasing and subtract from previous quantity
	        for(int i = 0; i < uniqueItemSet.size(); i++){
	        	double amount = new Double(findItemQuantity(uniqueItemSet.elementAt(i)));	//find the total amount purchased of each
	        	double updateAmount = 0.0;
	        	for(int j = 0; j < checkoutItems.size(); j++){
	        		if(checkoutItems.elementAt(j).getSku() == uniqueItemSet.elementAt(i)){
	        			updateAmount = checkoutItems.elementAt(i).getQuantity() - amount; 	//subtract from previous quantity
	        		}
	        	}
	        	
	        	//output statement to the database
	        	stmt = c.createStatement();
		        String sql = "UPDATE inventory SET QUANTITY = " + updateAmount + " where SKU = " + uniqueItemSet.elementAt(i) + ";";
		        stmt.executeUpdate(sql);
	        	
	        }
	        
	        c.commit();
	        stmt.close();
	        c.close();
	       	} catch ( Exception e ) {
	       		System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	       		System.exit(0);
	       }
	       System.out.println("Operation done successfully");
	}
	
	
}




