package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	
//	
//	public static HashMap<String, String> storeValues = new HashMap();

	public static List<HashMap<String, String>> data(String filepath, String sheetName) {
		
		
		List<HashMap<String, String>> mydata = new ArrayList<>();
		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
		
			Row HeaderRow = sheet.getRow(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) 
				{
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<String, String>();
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) 
					{
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) 
						{
							case STRING:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
					case BLANK:
						break;
					case BOOLEAN:
						break;
					case ERROR:
						break;
					case FORMULA:
						
						
						break;
					case NUMERIC:
						break;
					case _NONE:
						break;
					default:
						break;
						}
					}
				mydata.add(currentHash);
				}
			fs.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}
	
//	public static List<HashMap<String,String>> Datareader(String sheetname, String filepath) throws FileNotFoundException{
//		
//		
//		List<HashMap<String, String>> li = new ArrayList();
//		FileInputStream fs = new FileInputStream(filepath);
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sh = wb.getSheet(sheetname);
//		
//	  Row roeh = sh.getRow(0);
//		for(int i =0;i<sh.getPhysicalNumberOfRows();i++) {
//			
//			Row currentRow = sh.getRow(i);
//		
//		HashMap<String, String> currentHash = new HashMap<String, String>();
//		for(int j =0;j<currentRow.getPhysicalNumberOfCells();j++) {
//			
//			Cell currentCell = currentRow.getCell(j);
//
//		}
//		
//		
//		
//		return null;
//	
//		
	//}
//}
}

