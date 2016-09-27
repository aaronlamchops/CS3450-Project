package project;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
	
   public static void main( String args[] ){
       
       //insert into table:
       User myUser = new User();
       
       //calling a function from User.java
       myUser.insertIntoTable();
       
     }
}