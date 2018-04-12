package com.performance.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.performances.model.TDomain;

public class ExcelObjectFactory<T> {

	/**
	 * 获取Excel对象
	 * @param file	Excel文件
	 * @return		Excel操作对象
	 * @throws IOException
	 */
	public ExcelObject<T> getExcelObject(File file) throws IOException{
		
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		FileInputStream is = FileUtils.openInputStream(file);
		
		return getExcelObject(extension, is);
	}
	
	/**
	 * 获取Excel对象
	 * @param suffix	Excel文件后缀名
	 * @param is		Excel文件流
	 * @return			Excel操作对象
	 * @throws IOException
	 */
	public ExcelObject<T> getExcelObject(String suffix, InputStream is) throws IOException{
		switch (suffix)
		{
			case "xls":
				return new Excel2003Object<T>(is);
			case "xlsx":
			default:
				return new Excel2007Object<T>(is);
		}
	}
	
	public static void main(String[] args) {
		File file = new File("F:\\workspace\\PerformanceAssessment\\data\\绩效初版\\领域 .xlsx");
		if(!file.isFile())
			return;
		try {
			ExcelObjectFactory<TDomain> factory = new ExcelObjectFactory<TDomain>();
			ExcelObject<TDomain> excel = factory.getExcelObject(file);
			
			List<String> names = excel.GetSheetNames();
			for(int i = 0; i < names.size(); i++)
			{
				String name = names.get(i);
				Map<Integer, String> columns = excel.GetSheetColumns(i);
				Map<Integer, Integer> columnTypes = excel.GetSheetColumnTypes(i);
				Object value = excel.GetCellValue(i, 1, 1);
				int type = excel.GetCellType(i, 1, 1);
				TDomain domain = new TDomain();
				//domain = excel.SetModelValue(domain, i, 2, new HashMap(){{put(0,0); put(1,1);}});
			}
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
