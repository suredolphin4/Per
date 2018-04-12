package com.performance.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.performance.util.excel.XMLParse;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	public void createTemplate() {

	}

	/**
	 * 读取excel中的数据
	 * 
	 * @param url
	 */
	public void readExcel07(String url) {

		File file = new File(url);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(
					FileUtils.openInputStream(file));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <= lastRowNum; i++) {

				HSSFRow row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					HSSFCell cell = row.getCell(j);
					String valueString = cell.getStringCellValue();
					System.out.println(valueString + "");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readExcel13(String url) {

		File file = new File(url);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(
					FileUtils.openInputStream(file));
			XSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <= lastRowNum; i++) {

				XSSFRow row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell.getCellType() == 0) {
						System.out.println(cell.getBooleanCellValue());
					} else if (cell.getCellType() == 1) {

					} else if (cell.getCellType() == 2) {

					} else if (cell.getCellType() == 3) {

					} else if (cell.getCellType() == 4) {

					} else if (cell.getCellType() == 5) {

					}
					System.out.println(cell.getCellType());
					String valueString = cell.getStringCellValue();
					System.out.println(valueString.trim() + "--------");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 读取Excel表格表头的内容
	 * 20151113 yangxp update
	 * @param InputStream
	 * @return String 表头内容的数组
	 * 
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
	        fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell(i));
		}
		return title;
	}
	

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContent(InputStream is) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += getCellFormatValue(row.getCell(j)).trim() + "    ";
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	// /**
	// * 获取单元格数据内容为日期类型的数据
	// *
	// * @param cell
	// * Excel单元格
	// * @return String 单元格数据内容
	// */
	// private String getDateCellValue(HSSFCell cell) {
	// String result = "";
	// try {
	// int cellType = cell.getCellType();
	// if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
	// Date date = cell.getDateCellValue();
	// result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
	// + "-" + date.getDate();
	// } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
	// String date = getStringCellValue(cell);
	// result = date.replaceAll("[年月]", "-").replace("日", "").trim();
	// } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
	// result = "";
	// }
	// } catch (Exception e) {
	// System.out.println("日期格式不正确!");
	// e.printStackTrace();
	// }
	// return result;
	// }

	/**
	 * 根据HSSFCell类型设置数据
	 *  适用excel03
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
	/**
	 * 根据XHSSFCell类型设置数据
	 *  适用excel07
	 * @param cell
	 * @return
	 */
	private String getXCellFormatValue(XSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA: {
				cellvalue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			// 如果当前Cell的Type为STRIN
			case XSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
				// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
		
	}

	/**
	 * yangixnpeng  touch
	 * @param filePath
	 * @return
	 */
	 public static boolean isExcel2003(String filePath)  
	    {  
	  
	        return filePath.matches("^.+\\.(?i)(xls)$");  
	  
	    }  
	 
	 /**
		 * yangixnpeng  touch
		 * @param filePath
		 * @return
		 */
	 public static boolean isExcel2007(String filePath)  
	    {  
	  
	        return filePath.matches("^.+\\.(?i)(xlsx)$");  
	  
	    }  
	 XSSFWorkbook xwb=null;
	 XSSFSheet xsheet=null;
	 XSSFRow xrow=null;
	 XSSFCell xcell=null;
	 public String[] readExportExcelTitle(String filePath) {
		 		String []title=null;
		 		try{
				if(!isExcel2003(filePath)){
					xwb = new XSSFWorkbook(new FileInputStream(filePath)); 
					xsheet = xwb.getSheetAt(0);
					xrow = xsheet.getRow(0);
					// 标题总列数
					int colNum = xrow.getPhysicalNumberOfCells();
					System.out.println("colNum:" + colNum);
					title=new String[colNum];
					for (int i = 0; i < colNum; i++) {
						
						title[i] = getXCellFormatValue(xrow.getCell(i)).trim();
					}
					
					
				}else if(isExcel2007(filePath)){
					wb = new HSSFWorkbook(new FileInputStream(filePath)); 
					sheet = wb.getSheetAt(0);
					row = sheet.getRow(0);
					// 标题总列数
					int colNum = row.getPhysicalNumberOfCells();
					System.out.println("colNum:" + colNum);
					title = new String[colNum];
					for (int i = 0; i < colNum; i++) {
						title[i] = getCellFormatValue(row.getCell(i)).trim();
					}
				}
		 		}catch(Exception e){
		 			e.printStackTrace();
		 		}
				
				return title;
		}
	
	
}
