package project;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Receipt {
	
	private int receiptNumber;
	private String date;
	private String time;
	private String payMethod;
	private double totalPay;
	private Vector<Item> items;
	
	//constructors
	public Receipt(){
		receiptNumber = 0;
		date = "01-01-00";
		time = "12:00 am";
		payMethod = "Card";
		totalPay = 0.0;
		items = new Vector<Item>();
	}
	
	public Receipt(int r, String d, String t, String pM, double tP, Vector<Item> i){
		receiptNumber = r;
		date = d;
		time = t;
		payMethod = pM;
		totalPay = tP;
		items = (Vector)i.clone();
	}
	//setters
	public void setReceiptNum(int r){
		receiptNumber = r;
	}
	public void setDate(String d){
		date = d;
	}
	
	public void setTime(String t){
		time = t;
	}

	public void setPayMethod(String pM){
		payMethod = pM;
	}
	
	public void setTotalPay(double tP){
		totalPay = tP;
	}
	
	public void setItemVector(Vector<Item> i){
		items = (Vector)i.clone();
	}
	//getters
	public int getReceiptNum(){
		return receiptNumber;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getPayMethod(){
		return payMethod;
	}
	
	public double getTotalPay(){
		return totalPay;
	}
	
	public Vector<Item> getItemVector(){
		return items;
	}
	
	//inserts a default receipt
	public void insertReceipt(){			
		Scanner reader = new Scanner(System.in);
		  Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
	            "postgres", "toothpaste");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         stmt = c.createStatement();
	         String sql = "INSERT INTO RECEIPTS (RECEIPTNUMBER,DATE,TIME,PAYMETHOD,TOTALPAY,ITEMLIST) "
	               + "VALUES (54321, '10-01-16', '8:30pm', 'Visa', 20.00, '{123,321,12345}');";
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
	
	//returns a receipt from the data base.
	public void returnReceipt(){
		Scanner reader = new Scanner(System.in);
		Connection c = null;
		Connection c2 = null;
	       Statement stmt = null;
	       Statement stmt2 = null;
	       try {
	       Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
	            "postgres", "toothpaste");
	         c.setAutoCommit(false);
	         c2 = DriverManager
	 	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
	 	            "postgres", "toothpaste");
	 	     c2.setAutoCommit(false);
	 	     
	         System.out.println("Opened database successfully");
	         
	         System.out.println("Please enter your receipt number: ");
	         String input = reader.next();
	         
	         Receipt newReceipt = new Receipt();
	         
	         stmt = c.createStatement();
	         stmt2 = c2.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM RECEIPTS WHERE receiptnumber = " + input + ";" );
	         ResultSet ss;
	         
	         //first connection to grab everything but item list
	         while ( rs.next() ) {
	            newReceipt.setReceiptNum(rs.getInt("receiptnumber"));
	            newReceipt.setDate(rs.getString("date"));
	            newReceipt.setTime(rs.getString("time"));
	            newReceipt.setPayMethod(rs.getString("paymethod"));
	            newReceipt.setTotalPay(rs.getFloat("totalpay"));
	            Array its = rs.getArray(6);
	            String[] str_items = (String[])its.getArray();
	            Vector<String> newItem_str = new Vector<String>(Arrays.asList(str_items));
	            Vector<Item> realItems = new Vector<Item>();
	            
	            //search database for items by sku in database and store in a vector
	            for(int i = 0; i < newItem_str.size(); i++){
	            	Item fromData = new Item();
	            	ss = stmt2.executeQuery("SELECT * FROM INVENTORY WHERE sku = " + Integer.parseInt(newItem_str.elementAt(i)) + ";");
	            	while(ss.next()){
	            		fromData.setSku(ss.getInt("sku"));
	            		fromData.setName(ss.getString("name"));
	            		fromData.setQuantity(ss.getFloat("quantity"));
	            		fromData.setPrice(ss.getFloat("price"));
	            		fromData.setDist(ss.getString("distributor"));
	            		fromData.setWeight(ss.getString("weight"));
	            	}
	            	realItems.add(fromData);
	            	//ss.close();
	            }
	            
	            newReceipt.setItemVector(realItems);
	            
	            System.out.println( "RECEIPT NUMBER = " + newReceipt.getReceiptNum() );
	            System.out.println( "DATE = " +  newReceipt.getDate() );
	            System.out.println( "TIME = " + newReceipt.getTime() );
	            System.out.println( "PAY METHOD = " + newReceipt.getPayMethod() );
	            System.out.println( "TOTAL PAY = " +  newReceipt.getTotalPay());
	            System.out.println( "-------------------");
	            for (int i = 0; i < newReceipt.getItemVector().size(); i++){
	            	System.out.println(newReceipt.getItemVector().elementAt(i).toString());
	            	System.out.println();
	            }
	            
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
	
	public void createReceipt(){	//creates a receipt based off of checkout items
		Connection c = null;
		Connection cs = null;
		Connection c2 = null;
	      Statement stmt = null;
	      Statement stmt2 = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",			//connection for look up of items
	            "postgres", "toothpaste");
	         c.setAutoCommit(false);
	         
	         c2 = DriverManager
	 	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",		//connection for latest receipt number
	 	            "postgres", "toothpaste");
	 	     c2.setAutoCommit(false);
	         
	         cs = DriverManager
	 	            .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",		//connection for insert into database
	 	            "postgres", "toothpaste");
	 	     cs.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         ResultSet ss;
	         ResultSet ss2;
	         stmt2 = c2.createStatement();
	         int latest = 0;
	         ss2 = stmt2.executeQuery("SELECT * FROM RECEIPTS ORDER BY receiptnumber DESC LIMIT 1"); //get the latest receipt number
	         while(ss2.next()){
	        	 latest = ss2.getInt("receiptNumber");
	         }
	         latest++;			//increment the latest receipt number
	         
	         //into database receipt
	         stmt = cs.createStatement();
	         
	         //grab skus from database to use in creating
	         Item newItem = new Item();
	         double total = 0;
	         Vector<String> skusToAdd = new Vector<String>();
	         String[] holder = new String[items.size()];
	         for(int i = 0; i < items.size(); i++){
	            	
	        	 	//find out what items to add
	            	ss = stmt.executeQuery("SELECT * FROM INVENTORY WHERE sku = " + Integer.toString(items.elementAt(i).getSku()) + ";");
	            	while(ss.next()){
	            		newItem.setSku(ss.getInt("sku"));
	            		newItem.setName(ss.getString("name"));
	            		newItem.setQuantity(ss.getFloat("quantity"));
	            		newItem.setPrice(ss.getFloat("price"));
	            		newItem.setDist(ss.getString("distributor"));
	            		newItem.setWeight(ss.getString("weight"));
	            		skusToAdd.add(Integer.toString(newItem.getSku()));
	            		total += newItem.getPrice();			//get the total ammount
	            	}
	            	
	         }
	         totalPay = total;
	         skusToAdd.toArray(holder);
	         Array arraySkus = cs.createArrayOf("text", holder);	//conversion to array that can be store in psql
	         
	         //insert into database with arrays
	         String sql = "INSERT INTO RECEIPTS VALUES (?, ?, ?, ?, ?, ?)"; //create a statement that will accept the "?" and insert appropriate elements
	         PreparedStatement pstmt = cs.prepareStatement(sql);
	         pstmt.setInt(1, latest);	//try to to get the last item in the table and increase by one
	         pstmt.setString(2, date);
	         pstmt.setString(3, time);
	         pstmt.setString(4, payMethod);
	         pstmt.setDouble(5, totalPay);
	         pstmt.setArray(6, arraySkus);
	         
	         pstmt.executeUpdate();
	         
	         System.out.println("Check");
	         stmt.close();
	         cs.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	}
	
}
