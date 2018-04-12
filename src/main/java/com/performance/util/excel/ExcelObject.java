package com.performance.util.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ExcelObject<T> {
	
	public List<String> GetSheetNames();
	
	public Map<Integer, String> GetSheetColumns(int sheetIndex);
	
	public Map<Integer, Integer> GetSheetColumnTypes(int sheetIndex);
	
	public String GetMatchInfo(int sheetIndex, T t, List<String> columns);
	
	public int GetCellType(int sheetIndex, int row, int cell);
	
	public Object GetCellValue(int sheetIndex, int row, int cell);
	
	public Map<Integer, Integer> GetMapRelationIndexs(T t, int sheetIndex, Map<String, String> relations);
	
	public T SetModelValue(T t, int sheetIndex, int nrow, Map<Integer, Integer> mapRelations, List<Match> matches);
	
	public int getNumberOfRows(int indexName);
	
	public int getStartPosOfRow(int indexName);
}
