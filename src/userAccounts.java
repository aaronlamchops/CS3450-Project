//package project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

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

	public void setEq (userAccounts ua)
	{
		this.userId = ua.userId;
		this.name = ua.name;
		this.password = ua.password;
		this.accType = ua.accType;
	}

	public Vector returnUIDS(){
		{
			Connection c = null;
			Statement stmt = null;
			Vector UIDList = new Vector();
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager
						.getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
								"postgres", "toothpaste");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT \"ID\" FROM \"USERS\"" );



				while ( rs.next() ) {
					//store data into class type temps
					UIDList.add(rs.getInt("ID"));

					//check if credentials is correct and store in userAccount instance if true, else return false

				}

				rs.close();
				stmt.close();
				c.close();
				return UIDList;
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName()+": "+ e.getMessage() );
				if(e.getLocalizedMessage() == "The connection attempt failed.")
				{
					JOptionPane.showConfirmDialog(null,"The PostgreSQL server is temporarily down.\nPlease contact your"+
							"administrator.","Error",JOptionPane.OK_OPTION);
				}
				System.exit(0);
			}
			System.out.println("Operation done successfully");
			return UIDList;
		}



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

	public boolean loginCheck(int u, int p){					//check logins


		//set credentials for two parameters
		this.setCredentials(u, p);

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
			ResultSet rs = stmt.executeQuery( "SELECT * FROM \"USERS\" WHERE \"PASSWORD\" = " + Integer.toString(p)
					+ " AND \"ID\" = " + u + ";" );

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
			if(e.getLocalizedMessage() == "The connection attempt failed.")
			{
				JOptionPane.showConfirmDialog(null,"The PostgreSQL server is temporarily down.\nPlease contact your"+
						"administrator.","Error",JOptionPane.OK_OPTION);
			}
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
           .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
           "postgres", "toothpaste");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM \"USERS\";" );
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

    public boolean GUIInsertIntoTable(userAccounts ua) {

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


            //Checking for duplicate ID
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"USERS\" WHERE \"ID\" = "+ ua.userId + ";");

            if (!rs.isBeforeFirst()) {
                String sql = "INSERT INTO \"USERS\" (\"ID\",\"NAME\",\"PASSWORD\",\"TYPE\") "
                        + "VALUES (" + ua.userId + ", '" + ua.name + "', "
                        + ua.password + ", '" + ua.accType + "' );";

                //updates to the sql server
                stmt.executeUpdate(sql);
            }
            else {
                JOptionPane.showMessageDialog(null, "Duplicate ID already exists in database!");

                rs.close();
                stmt.close();
                c.commit();
                c.close();

                return false;
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();

            return true;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return true;

    }

	public boolean GUIDeleteUser(int UID) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
							"postgres", "toothpaste");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			int decision = JOptionPane.showConfirmDialog(null, "Are you sure you'd like to delete this user?","Confirm Delete", JOptionPane.YES_NO_OPTION);


            if (decision == JOptionPane.YES_OPTION)
            {
                stmt = c.createStatement();
                String sql = "DELETE from \"USERS\" where \"ID\" = " + UID + ";";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();
                c.close();
                return true;
            }


			//exit if they say no
			else{
				System.out.println("Ending Task.");
                c.close();
                stmt.close();
				return false;
			}

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return true;
	}

	public boolean GUIEditUser(userAccounts ua) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://vowoodhome.asuscomm.com:5454/test",
                            "postgres", "toothpaste");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            int decision = JOptionPane.showConfirmDialog(null, "Please confirm the following changes:\n" +
                    "UserID: " + ua.userId + "\n" +"Name: " + ua.name +"\n" + "Passcode: " + ua.password +"\n" +
                    "User role: " + ua.accType + "\n\nIs this data all correct?","Confirm Edit",
                    JOptionPane.YES_NO_OPTION);


            if (decision == JOptionPane.YES_OPTION)
            {
                stmt = c.createStatement();
                String sql = "UPDATE \"USERS\" SET \"NAME\" = '" + ua.name +"', \"PASSWORD\" = " +ua.password +", " +
                 "\"TYPE\" = '" +ua.accType + "' WHERE \"ID\" = " + ua.userId + ";";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();
                c.close();
                return true;
            }


            //exit if they say no
            else{
                System.out.println("Ending Task.");
                c.close();
                stmt.close();
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