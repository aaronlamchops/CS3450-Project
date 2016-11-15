package project;


import java.io.File;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.security.auth.login.LoginContext;

public class PostgreSQLJDBC {
	
   public static void main( String args[] ){
	   
       userAccounts myUser = new userAccounts();
       
/*Test Trials for RECEIPT*/       
       Receipt myReceipt = new Receipt();
       //myReceipt.returnReceipt("123");
       //myReceipt.returnBackItem("54334", 87654, 2.0);
       
       
/*Test Trials for CHECKOUT*/
//       Checkout myCheckout = new Checkout();
//       myCheckout.addItems("87654");
//       myCheckout.addItems("87654");
//       myCheckout.addItems("87654");
//       myCheckout.addPaymentType("MasterCard");
//       myCheckout.createRecTrans();
//       myCheckout.updateInventory();
       
       
       //myUser.createTable();
       
       
       MainScreen screen = new MainScreen();
       
       
/*Test Trials for XLS_IMPORT*/
       XLS_Import newImport = new XLS_Import();
//       try {
//		newImport.createReportSheet("10-01-16", "10-04-16");
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
       //newImport.createExample();
       //newImport.readFile("XLSimportTest.xlsx");
       
       
/*Test Trials for INVENTORY*/       
       InventoryMan newInv = new InventoryMan();
//       Item update = new Item(123, "DogFood", 10.0, 8.25, "HealthDogs", "3lb");
//       newInv.uploadNewItem(update);
       //newInv.updateItem(update, 123);
       //newInv.deleteItem(123);
       //newInv.searchItem();
       
       
       //calling a function from UserAccounts.java
//       myUser.loginCheck();
//       System.out.println(myUser.toString());
//       myUser.deleteUser();
     }
} 