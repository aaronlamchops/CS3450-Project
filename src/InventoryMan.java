package project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class InventoryMan {



	public Boolean uploadNewItem(Item uploadItem){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");

			//getting the database, user, password:
			c = DriverManager
					.getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
							"postgres", "toothpaste");

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			Item newItem = new Item(uploadItem);

			//

			//the commands that you are utilizing in the database
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"INVENTORY\" WHERE \"NAME\" = '" + newItem.getName() + "' AND " +
					"\"DISTRIBUTOR\" = '" +newItem.getDist() +"';");

			if(!rs.isBeforeFirst()) {
				String sql = "INSERT INTO \"INVENTORY\" (\"NAME\",\"QUANTITY\",\"PRICE\",\"DISTRIBUTOR\",\"WEIGHT\") "
						+ "VALUES ('" + newItem.getName() + "', "
						+ Double.toString(newItem.getQuantity()) + ", " + Double.toString(newItem.getPrice())
						+ ", '" + newItem.getDist() + "', '" + newItem.getWeight() + "');";

				//updates to the sql server
				stmt.executeUpdate(sql);


				stmt.close();
				c.commit();
				c.close();
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Duplicate name/distributor combination found in DB.\n" +
				"If you'd like to change this item's attributes, please do it from the Edit Item dialog.", "Input error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			return false;
			//System.exit(0);
		}

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

	public void updateItem(Item toUpdateItem, int sku){

		Connection c = null;
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test","postgres", "toothpaste");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "UPDATE \"INVENTORY\" SET NAME = " + "'" + toUpdateItem.getName() + "'" + " where \"SKU\" = " + sku + ";";
			stmt.executeUpdate(sql);

			stmt2 = c.createStatement();
			String sql2 = "UPDATE \"INVENTORY\" SET QUANTITY = " + "'" + toUpdateItem.getQuantity() + "'" + " where \"SKU\" = " + sku + ";";
			stmt2.executeUpdate(sql2);

			stmt3 = c.createStatement();
			String sql3 = "UPDATE \"INVENTORY\" SET PRICE = " + "'" + toUpdateItem.getPrice() + "'" + " where \"SKU\" = " + sku + ";";
			stmt3.executeUpdate(sql3);

			stmt4 = c.createStatement();
			String sql4 = "UPDATE \"INVENTORY\" SET DISTRIBUTOR = " + "'" + toUpdateItem.getDist() + "'" + " where \"SKU\" = " + sku + ";";
			stmt4.executeUpdate(sql4);

			stmt5 = c.createStatement();
			String sql5 = "UPDATE \"INVENTORY\" SET WEIGHT = " + "'" + toUpdateItem.getWeight() + "'" + " where \"SKU\" = " + sku + ";";
			stmt5.executeUpdate(sql5);

			c.commit();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public Boolean deleteItem(int sku){

		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test","postgres", "toothpaste");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//statement to execute
			stmt = c.createStatement();
			String sql = "DELETE FROM inventory WHERE \"SKU\" = " + sku + ";";
			stmt.executeUpdate(sql);

			c.commit();
			stmt.close();
			c.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");

		return true;
	}

	//Search code determined by whether "SKU" or "description" is selected in the search window.
	public Vector searchItem(String searchItem, int searchCode){
		Item invItem = new Item();
		Vector returnStringSet = new Vector();
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
			ResultSet rs;

			//checks what kind of input
			if(searchCode == 0){			//If search selection = SKU
				rs = stmt.executeQuery( "SELECT * FROM \"INVENTORY\" WHERE \"SKU\" = " + searchItem + ";");
			}
			else{							//If search selection = Description
				String input = searchItem;
				rs = stmt.executeQuery( "SELECT * FROM \"INVENTORY\" WHERE LOWER(\"NAME\") LIKE LOWER('" + input + "%') OR "
						+ "LOWER(\"DISTRIBUTOR\") LIKE LOWER('" + input + "%') OR "
						+ "LOWER(\"WEIGHT\") LIKE LOWER('" + input + "%');");
			}

			System.out.println("here");

			while ( rs.next() ) {

				invItem.setSku(rs.getInt("SKU"));
				invItem.setName(rs.getString("name"));
				invItem.setQuantity(rs.getFloat("quantity"));
				invItem.setPrice(rs.getFloat("price"));
				invItem.setDist(rs.getString("distributor"));
				invItem.setWeight(rs.getString("weight"));

				//print out:
				//System.out.println(invItem.toString());	//needs to output to a jtextarea
				returnStringSet.add(invItem.toString());

			}
			rs.close();
			stmt.close();
			c.close();
			return returnStringSet;

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return returnStringSet;
	}
}