package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;
import com.sun.media.sound.InvalidFormatException;

public class TestUtil extends TestBase{

	public static long PAGE_LOAD_TIMEOUT=30;
	public static long IMPLICIT_WAIT=20;

	public static String TESTDATA_SHEET_PATH="D:\\FreeCRMTest\\src"
			+ "\\main\\java\\com\\crm\\qa\\testdata\\FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");

	}


	public static Object[][] getTestData(String sheetName){
		FileInputStream file=null;
		try{
			file=new FileInputStream(TESTDATA_SHEET_PATH);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try{
			book=WorkbookFactory.create(file);
		}catch (InvalidFormatException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
	}


	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d1=new Date();
		String dte=d1.toString().replace(':', '_').replace(' ', '_');
		//String currentDir = System.getProperty("D:\\FreeCRMTest\\screenShot");

		FileUtils.copyFile(scrFile, new File("D:\\FreeCRMTest\\screenShot\\screenshot_"+dte+".png"));

	}

}
