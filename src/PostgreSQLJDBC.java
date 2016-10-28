package project;


import java.io.File;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
	
   public static void main( String args[] ){
	   
       userAccounts myUser = new userAccounts();
       
/*Test Trials for RECEIPT*/       
       Receipt myReceipt = new Receipt();
       //myReceipt.returnReceipt("123");
       
       
/*Test Trials for CHECKOUT*/
//       Checkout myCheckout = new Checkout();
//       myCheckout.addItems("87654");
//       myCheckout.addItems("87654");
//       myCheckout.addItems("87654");
//       myCheckout.addPaymentType("MasterCard");
//       myCheckout.createRecTrans();
//       myCheckout.updateInventory();
       
       
       //myUser.createTable();
       
       
       //MainScreen screen = new MainScreen();
       
       
/*Test Trials for XLS_IMPORT*/
       XLS_Import newImport = new XLS_Import();
       //newImport.createExample();
       //newImport.readFile("XLSimportTest.xlsx");
       
       
/*Test Trials for INVENTORY*/       
       InventoryMan newInv = new InventoryMan();
//       Item update = new Item(87654, "Steak", 10.0, 7.15, "MooMooFarms", "12oz");
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