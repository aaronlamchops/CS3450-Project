package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Checkout {

	Receipt checkReceipt;
	Vector<Item> checkoutItems;

	public Checkout(){
		checkReceipt = new Receipt();
		checkoutItems = new Vector<Item>();
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

	public void addPaymentType(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the payment type: ");
		String input = reader.next();
		checkReceipt.setPayMethod(input);

		//get time and date
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yy");
	    checkReceipt.setDate(ft.format(dNow).toString());
	    	//System.out.println(ft.format(dNow).toString());
	    ft = new SimpleDateFormat("h:mma");
	    	//System.out.println(ft.format(dNow).toString());
	    checkReceipt.setTime(ft.format(dNow).toString());

	}

	public void createRecTrans(){
		checkReceipt.setItemVector(checkoutItems);
		checkReceipt.createReceipt();
	}

	public void updateInventory(){

	}


}
