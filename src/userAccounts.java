package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class userAccounts {
	
	int userId;
	String name;
	int password;
	String accType;
	
	//default constructor
	public userAccounts(){
		userId = 0;
		name = null;
		password = 0;
		accType = null;
	}
	
	//constructor
	public userAccounts(int i, String n, int p, String aT){
		userId = i;
		name = n;
		password = p;
		accType = aT;
	}
	
	//setter
	public void setAll(int i, String n, int p, String aT){
		userId = i;
		name = n;
		password = p;
		accType = aT;
	}
	
	public String toString(){
		return "ID = " + userId +
							"\r" + "NAME = " + name +
							"\r"+ "PASSWORD = " + password +
							"\r" + "TYPE = " + accType;
	}
	
	//setter for two parameters
	public void setCredentials(int u, int p){
		userId = u;
		password = p;
	}
	
	public boolean loginCheck(){					//check logins
		
		//get user input
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter your user id: ");
		int u = reader.nextInt();
		System.out.println("Please enter your password: ");
		int p = reader.nextInt();
		
		//set credentials for two parameters 
		this.setCredentials(u, p);
		
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
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS WHERE PASSWORD = " + Integer.toString(p) 
	        								+ " AND ID = " + u + ";" );
	        
	        while ( rs.next() ) {
	        	//store data into class type temps
	        	int uId = rs.getInt("id");
	 	        String na = rs.getString("name");
	 	        int pass  = rs.getInt("password");
	 	        String at = rs.getString("type");
	 	        
	 	        //check if credentials is correct and store in userAccount instance if true, else return false
	        	if(this.userId == uId && this.password == pass){
	        		this.setAll(uId, na, pass, at);
	        		System.out.println("User login success!");
	        		return true;
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
		return false;
	}
	
	//get data from table on all users
	public void selectFromTable()
    {
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
        ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
        while ( rs.next() ) {
        	//store data into class type
           userId = rs.getInt("id");
           name = rs.getString("name");
           password  = rs.getInt("password");
           accType = rs.getString("type");
           
           //print out:
           System.out.println( "ID = " + userId );
           System.out.println( "NAME = " + name );
           System.out.println( "PASSWORD = " + password );
           System.out.println( "TYPE = " + accType );
           System.out.println();
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

	//allows a admin user to create a user.
	public boolean insertIntoTable(){ 
		
		if(this.accType.equals("Guest") || this.accType.equals("Inv")){
			System.out.println("Your account is not Admin." +
								"\r" + "Please consult your Administrator.");
			return false;
		}
		else{
			//module for user input
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
		         
		           // Reading from System.in
		         System.out.println("Please enter id-number: ");
		         String id = reader.next();
		         System.out.println("Please enter name: ");
		         String name = reader.next();
		         System.out.println("Please enter password: ");
		         String pass = reader.next();
		         System.out.println("Please enter account type: ");
		         String type = reader.next();
		         
		         //the commands that you are utilizing in the database
		         String sql = "INSERT INTO USERS (ID,NAME,PASSWORD,TYPE) "
		               + "VALUES (" + id + ", '" + name + "', " 
		        		 + pass + ", '" + type + "' );";
		         
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
			return true;
		}
	}
	
	
	public boolean deleteUser() {
		//check if user is admin
		if(this.accType.equals("Guest") || this.accType.equals("Inv")){
			System.out.println("Your account is not Admin." +
								"\r" + "Please consult your Administrator.");
			return false;
		}
		
		Scanner reader = new Scanner(System.in);
      Connection c = null;
      Statement stmt = null;
      
      try {
      Class.forName("org.postgresql.Driver");
        c = DriverManager
           .getConnection("jdbc:postgresql://localhost:5433/test",
           "postgres", "aaronrocks");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        //input on what user to delete
        System.out.println("Please enter id-number you would like to delete: ");
        int id = reader.nextInt();
        //double check if they would like to.
        System.out.println("Would you like to proceed with this command? <Y> or <N>: ");
        String yesorno = reader.next();
        
        //complete if yes
        if(yesorno.equals("y") || yesorno.equals("Y")){
        	stmt = c.createStatement();
            String sql = "DELETE from USERS where ID = " + Integer.toString(id) + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            System.out.println("User deleted.");
            return true;
        }
        //exit if they say no
        else{
        	System.out.println("Ending Task.");
        	return false;
        }
        
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      System.out.println("Operation done successfully");
	return true;
    }

	
}
