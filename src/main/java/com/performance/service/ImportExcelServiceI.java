package com.performance.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

public interface ImportExcelServiceI<T> {
	void ImportExcelToDB(String tableName, FileInputStream sheetFile)
			throws IOException, XPathExpressionException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException;
	
	void setEventProxy(ImportExcelEventI<T> event);
	
	String exportExcel(String fileName);

	String exportExcel(String fileName, String tableName, String hsql, Map<String, Object> params);

	//public File exportExcel(File file, String tableName, String hsql, Map<String, Object> params) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, XPathExpressionException;
}
