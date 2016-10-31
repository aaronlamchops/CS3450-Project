package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLS_Import {
	
	//driver class to run readFromXLS()
	public void readFile(String filename){
		try {
			readFromXLS(filename);
	       } catch (Exception e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       }
	}
	
	public void createExample(String start, String end){
		try {
			createReportSheet(start, end);
	       } catch (Exception e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       }
	}
	
	public void readFromXLS(String filename) throws Exception{
		
		InventoryMan inventory = new InventoryMan();
		Item newItem = new Item();
		XSSFRow row;
		Integer count = new Integer(1);
		Integer first = new Integer(1);
		
		FileInputStream fis = new FileInputStream(
			      new File(filename));
			      XSSFWorkbook workbook = new XSSFWorkbook(fis);
			      XSSFSheet spreadsheet = workbook.getSheetAt(0);
			      Iterator < Row > rowIterator = spreadsheet.iterator();
			      while (rowIterator.hasNext()) 
			      {
			         row = (XSSFRow) rowIterator.next();
			         Iterator < Cell > cellIterator = row.cellIterator();
			         while ( cellIterator.hasNext()) 
			         {
			        	 Cell cell = cellIterator.next();
			        	if(first == 1){
			        		System.out.print(cell.getStringCellValue() + " ");
			        	}
			        	else{
				            if(count.equals(1)){
				          	   //System.out.println(cell.getStringCellValue());
				           	   String sku = cell.getStringCellValue();
				           	   newItem.setSku(Integer.parseInt(sku));
				            }
				            else if(count.equals(2)){
				           	   newItem.setName(cell.getStringCellValue());
				            }
				            else if(count.equals(3)){
				            	String Q = cell.getStringCellValue();
				           	   	newItem.setQuantity(Double.parseDouble(Q));
				            }
				            else if(count.equals(4)){
				            	String P = cell.getStringCellValue();
				           	   	newItem.setPrice(Double.parseDouble(P));
				            }
				            else if(count.equals(5)){
				           	   newItem.setDist(cell.getStringCellValue());
				            }
				            else{
				           	   newItem.setWeight(cell.getStringCellValue());
				            }  
				            count++;
			        	}
			        	
			         }
			         count = 1;
			         first = 2;
			         if(first == 2){
			        	 if(!inventory.uploadNewItem(newItem)){
			        		 System.out.println("Duplicate found or Error with input");
			        	 }
			        	 System.out.println();
				         System.out.println(newItem.toString());
			         }
			         
			      }
			      fis.close();
		
	}
	
	@SuppressWarnings("null")
	public void createReportSheet(String startDate, String endDate) throws Exception{
		
		//Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet( 
	      "Test XLS Import File");
	      //Create row object
	      XSSFRow row;
	      //This data needs to be written (Object[])
	      Map < String, Object[] > empinfo = 
	      new TreeMap < String, Object[] >(); 
	      
	      //check how many receipts there are:
	      
	      
	      //end
	      
	      
	      //create a query from the database to find a range of items sold from a set date period
	      
	      Vector<Receipt> listOfReceipts = new Vector<Receipt>();
	      Receipt temp = new Receipt();
	      
	      double total = 0.0;
	      Connection c = null;
	      Connection c2 = null;
	      Statement stmt = null;
	      Statement stmt2 = null;
		  		try {
		  		Class.forName("org.postgresql.Driver");
		        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/test","postgres", "aaronrocks");
		        c.setAutoCommit(false);
		        c2 = DriverManager.getConnection("jdbc:postgresql://localhost:5433/test","postgres", "aaronrocks");
		 	    c2.setAutoCommit(false);
		 	     
		        System.out.println("Opened database successfully");
		         
		        stmt = c.createStatement();
		        stmt2 = c2.createStatement();
		        ResultSet rs = stmt.executeQuery( "SELECT * FROM receipts WHERE date BETWEEN '" + startDate +"' AND '" + endDate + "';" );
		        ResultSet ss;
		        int index = 0; 
		        
		        Receipt fromQuery = new Receipt();
		        //first connection to grab everything but item list
		        while ( rs.next() ) {
		        	listOfReceipts.addElement(temp);
		        	System.out.println("Here!");
		        	System.out.println(index);
		        	fromQuery.setReceiptNum(rs.getInt("receiptnumber"));
		        	fromQuery.setDate(rs.getString("date"));
		        	fromQuery.setTime(rs.getString("time"));
		        	fromQuery.setPayMethod(rs.getString("paymethod"));
		        	total += rs.getFloat("totalpay");
		        	System.out.println(total);
		        	System.out.println("Here!");
		            //allows for sql arrays to be converted to a vector of items
		            Array its = rs.getArray(6);
		            String[] str_items = (String[])its.getArray();
		            Vector<String> newItem_str = new Vector<String>(Arrays.asList(str_items));
		            Vector<Item> realItems = new Vector<Item>();
		            System.out.println("Here!");
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
		            }  
		            fromQuery.getItemVector().addAll(realItems);
		            listOfReceipts.addElement(fromQuery);
		            index++;
		        } 
		        rs.close();
		        stmt.close();
		        c.close();
		  } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		  }
		  System.out.println("Operation done successfully");
	      
		  double totalProfit = 0.0;
		  double itemsSoldCount = 0.0;
		  Vector<Integer> onlySku = new Vector<Integer>();
		  
	      for(int i = 0; i < listOfReceipts.size(); i++){
	    	  totalProfit += listOfReceipts.elementAt(i).getTotalPay();
	    	  System.out.println(totalProfit);
	    	  
	    	  for(int j = 0; j < listOfReceipts.elementAt(i).getItemVector().size(); j++){
	    		  itemsSoldCount++;
	    		  onlySku.add(listOfReceipts.elementAt(i).getItemVector().elementAt(j).getSku());
	    	  }
	      }
	      
	      System.out.println(onlySku.size());
	      
		  Set<Integer> uniqueSku = new HashSet<Integer>();
	      for(int i = 0; i < onlySku.size(); i++){
	    	  if(uniqueSku.add(onlySku.elementAt(i)) == false){
	    		  System.out.println("duplicate found");
	    	  }
	      }
	      
	      Vector<Integer> noDuplicates = new Vector<Integer>(uniqueSku);
	      double occurrences = 0.0;
	      
	      Vector<String> skuForReport = new Vector<String>();
	      Vector<String> quantityForReport = new Vector<String>();

	      
	      for(int i = 0; i < uniqueSku.size(); i++){
	    	  occurrences = Collections.frequency(onlySku, noDuplicates.elementAt(i));
	    	  skuForReport.addElement(noDuplicates.elementAt(i).toString());
	    	  quantityForReport.addElement(Double.toString(occurrences));
	      }
	       
	      
	      //end
	      
	      empinfo.put( "1", new Object[] { "Report for Dates:", startDate, endDate, " ", " ", " " });
	      int after = 0;
	      for(int i = 0; i < skuForReport.size(); i++){
	    	  after++;
	    	  double q = Double.parseDouble(quantityForReport.elementAt(i));
	    	  System.out.println(q);
	    	  empinfo.put(Integer.toString(i + 2), new Object[] { skuForReport.elementAt(i) + ": ", Double.toString(q/3.0) , " items sold", " ", " ", " " });
	      }
	      after = after + 2;
	      empinfo.put( Integer.toString(after), new Object[] { "Total number of Orders: ", Integer.toString(listOfReceipts.size()), " ", " ", " ", " " });
	      empinfo.put( Integer.toString(after+1), new Object[] { "Total profit: ", Double.toString(total), " ", " ", " ", " " });
	      
	      //Iterate over data and write to sheet
	      Set < String > keyid = empinfo.keySet();
	      int rowid = 0;
	      for (String key : keyid)
	      {
	         row = spreadsheet.createRow(rowid++);
	         Object [] objectArr = empinfo.get(key);
	         int cellid = 0;
	         for (Object obj : objectArr)
	         {
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }
	      //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream( 
	      new File("XLSImportTest.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println( 
	      "XLSImportTest written successfully" );
	}
	
	
}
