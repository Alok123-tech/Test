package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader2{
	
	public static List<HashMap<String,String>> dataReader(String filepath,String sheetname){
		
		List<HashMap<String,String>> mydata = new ArrayList<>();

		 
		try {
			FileInputStream fis = new FileInputStream(filepath);

		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sheet = wb.getSheet(sheetname);
		    Row header = sheet.getRow(0);
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				
				Row currentRow = sheet.getRow(i);
			
			HashMap<String,String> hp = new HashMap<>();
			for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++) {
				 Cell currentcell  = currentRow.getCell(i);
				 
	              switch(currentcell.getCellType()) {
	              
	              case STRING :
			     hp.put(header.getCell(j).getStringCellValue(), currentcell.getStringCellValue());
			     break;
	              case BLANK :
	            	  break;
	              }
				
			}
			
			mydata.add(hp);
			
			}
			fis.close();
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return mydata;
		
		
	}
	
	
}