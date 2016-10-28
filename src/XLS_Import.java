package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
	
	public void createExample(){
		try {
			createExampleSheet();
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
	
	public void createExampleSheet() throws Exception{
		
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
	      
	      empinfo.put( "1", new Object[] { "SKU", "NAME", "QUANTITY", "PRICE", "DISTRIBUTOR", "WEIGHT" });
	      empinfo.put( "2", new Object[] { "1357", "Mustard", "30", "2.99", "Heinz", "13oz" });
	      empinfo.put( "3", new Object[] { "1468", "Pepper", "15", "2.49", "McCormick", "6oz" });
	      empinfo.put( "4", new Object[] { "2468", "Salt", "15", "2.49", "Morton", "26oz" });
	      empinfo.put( "5", new Object[] { "7890", "Sausage", "13", "5.29", "Hillshire", "16oz" });
	      empinfo.put( "6", new Object[] { "4567", "Milk", "10", "3.29", "MeadowGold", "1gal" });
	      
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
