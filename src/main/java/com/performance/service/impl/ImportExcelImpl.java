package com.performance.service.impl;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.util.excel.ExcelObject;
import com.performance.util.excel.ExcelObjectFactory;
import com.performance.util.excel.JavaClass;
import com.performance.util.excel.Match;
import com.performance.util.excel.XMLParse;
import com.performances.model.TDomain;
import com.performances.model.TLab;
import com.performances.model.TLwStutas;
import com.performances.model.TPerson;
import com.performances.model.TPubPart;
import com.performances.model.TSk;
import com.performances.model.TUser;
import com.performances.model.TUserCate;
import com.performances.model.TUserType;
import com.performances.model.TXm;
import com.performances.model.TYj;
import com.performances.model.TZl;

@Transactional
@Service("ImportExcelService")
public class ImportExcelImpl<T> implements ImportExcelServiceI<T> {
	private static final Logger logger = Logger.getLogger(ImportExcelImpl.class);
	private ExcelObjectFactory<T> _factory;
	private XMLParse _xmlParse;
	private BaseDaoI<T> _dbDao;
	private ImportExcelEventI<T> _importExcelEvent;
	
	public BaseDaoI<T> getDBDao() {
		return _dbDao;
	}
	
	@Autowired
	public void setDBDao(BaseDaoI<T> dbDao) {
		this._dbDao = dbDao;
	}
	
	public ImportExcelImpl() throws IOException, URISyntaxException{
		_factory = new ExcelObjectFactory<T>();
		_xmlParse = new XMLParse();
	}
	

	public void ImportExcelToDB(String tableName, FileInputStream sheetFile) throws IOException, XPathExpressionException, 
					InstantiationException, IllegalAccessException, ClassNotFoundException{
		ExcelObject<T> excelObject = _factory.getExcelObject("xlsx", sheetFile);		//采用Excel 2007
		String className = _xmlParse.GetTableAttribute(tableName, "class");
		int sheetIndex = Integer.parseInt(_xmlParse.GetTableAttribute(tableName, "sheetIndex"));
		String key = _xmlParse.GetKey(tableName);
		Map<String, String> keyWheres = _xmlParse.GetKeyWheres(tableName);
		Map<String, String> relations = _xmlParse.GetTableMaps(tableName);
		List<String> updateSQL = _xmlParse.GetUpdateSQLs(tableName);
		List<Match> matches = _xmlParse.GetMatches(tableName);
		T model = (T)Class.forName(className).newInstance();
		Map<Integer, Integer> relationIndexs = excelObject.GetMapRelationIndexs(model, sheetIndex, relations);
		for(int i = excelObject.getStartPosOfRow(sheetIndex) + 1; i < excelObject.getNumberOfRows(sheetIndex); i++)
		{
			//1.设置模型数据
			//T newModel = (T)Class.forName(className).newInstance();
			
			Class clazz=Class.forName(className);
			T newModel = (T)clazz.newInstance();
			
			T updateModel = excelObject.SetModelValue(newModel, sheetIndex, i, relationIndexs, matches);
				
			Method m;
			try {
				boolean skEntity=newModel instanceof TSk;
				boolean yjEntity=newModel instanceof TYj;
				boolean zlEntity=newModel instanceof TZl;
				boolean xmEntity=newModel instanceof TXm;
				boolean personEntity=newModel instanceof TPerson;
				boolean userEntity=newModel instanceof TUser;
				boolean pubPartEntity=newModel instanceof TPubPart;
				if(zlEntity==true){
					UpdateTimeByModel(clazz, updateModel);
				}
				if(personEntity==true){
					UpdateTimeByModel(clazz, updateModel);
				}
				if(userEntity==true){
					UpdateTimeByModel(clazz, updateModel);
				}
				if(pubPartEntity==true){
					UpdateTimeByModel(clazz, updateModel);
				}
				if((skEntity&&yjEntity&&xmEntity)==true){
					UpdateTimeByModel(clazz, updateModel);
				}
			} catch (Exception e1) {
				try {
					m=clazz.getDeclaredMethod("setSavetime", new Class[]{java.sql.Timestamp.class});
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒 
					Timestamp now = new Timestamp(new java.util.Date().getTime());//获取系统当前时间 
					String str = df.format(now);         
					Timestamp ts = Timestamp.valueOf(str);  
					m.invoke(updateModel, ts);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}


			if(_importExcelEvent != null){
				updateModel = _importExcelEvent.BeforeSaveModel(updateModel);
				if(!_importExcelEvent.IsValidModel(updateModel))
					continue;
			}
			
			//2.更新主键
			try
			{
				//System.out.println("表格行号: " + i + "\n");
				UpdateMode(updateModel, keyWheres, key);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void UpdateTimeByModel(Class clazz, T updateModel)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Method m;
		m=clazz.getDeclaredMethod("setSavetime", new Class[]{java.util.Date.class});
		m.invoke(updateModel, new Timestamp(new java.util.Date().getTime()));
	}
	
	public void setEventProxy(ImportExcelEventI<T> event){
		this._importExcelEvent = event;
	}
	
	public String exportExcel(String fileName){
		try {
			
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String path = ServletActionContext.getServletContext().getRealPath("/downloads");
			File pathFile = new File(path);
			if(!pathFile.exists()){
				pathFile.mkdir();
			}
			
			//1.判断是否有重复文件，有则删除，否则新增
			String realFileName = getFileName(path , fileName);
			String strFile = path + "/" + realFileName + ".xlsx";
			
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        String table = (String)session.get("QUERY_TABLE");
	        String hsql = (String)session.get("QUERY_HSQL");
	        Map<String, Object> params = (Map<String, Object>)session.get("QUERY_PARAMS");
			File file = new File (strFile);
			file = exportExcel(file, table, hsql, params);
			
			return fileName + ".xlsx";
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String exportExcel(String fileName, String tableName, String hsql, Map<String, Object> params){
		try {
			//获取上传文件路径，应在配置文件中定义
			Properties prop = new Properties();
			String propFileName = "config.properties";

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				try{
					prop.load(inputStream);
					inputStream.close();
				}catch(IOException e){
//				this.success = false;
//				this.message = "加载config.property文件出错！";
//				return ERROR;
				}
			} else {
				//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//			this.success = false;
//			this.message = "未发现配置文件config.property";
//			return ERROR;
			}

			String path = prop.getProperty("uploadDirectory");
			File pathFile = new File(path);
			if(!pathFile.exists()){
				pathFile.mkdir();
			}

			//1.判断是否有重复文件，有则删除，否则新增
			String realFileName = getFileName(path , fileName);
			String strFile = path + "/" + realFileName + ".xlsx";
			File file = new File (strFile);
			file = exportExcel(file, tableName, hsql, params);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();

		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			return fileName + ".xlsx";
		}
	}

	private String getFileName(String path, String fileName){
		String strFile = path + "/" + fileName + ".xlsx";
		String result = fileName;
		File file = new File(strFile);
		int i = 0;
		
		while(file.exists()){
			try
			{
				file.delete();
			}catch(Exception e){
				i++;
				strFile = path + "/" + fileName + i + ".xlsx";
				result = fileName + i;
				file = new File(strFile);
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("resource")
	private File exportExcel(File file, String tableName, String hsql, Map<String, Object> params) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, XPathExpressionException
	{
		Map<String, String> relations = _xmlParse.GetTableMaps(tableName);
		List<String> orderLists = _xmlParse.GetOrderList(tableName);
		String className = _xmlParse.GetTableAttribute(tableName, "class");
		long start = System.nanoTime();
		//time consuming code here.
		//...

		int count =_dbDao.count("select count(*) from " + className).intValue();
		int pageCount, rows;
		if(count > 1000){
			rows = 1000;
			pageCount = count / 1000 + 1;
		}else {
			pageCount = 1;
			rows = count;
		}



			long end = System.nanoTime();
			long used = end - start;
			logger.info("db search used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");

			start = System.nanoTime();
			//time consuming code here.
			//...
//		Workbook wb;
//		if (file.getAbsolutePath().endsWith(".xlsx")) {
//		    //wb = new XSSFWorkbook();
//			wb = new SXSSFWorkbook();
//
//		} else  {
//		    wb = new HSSFWorkbook();
//		}

			//更换为SXSSFWorkbook,放弃使用stream类型workbook
			SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
			wb.setCompressTempFiles(true);

			SXSSFSheet sheet = (SXSSFSheet) wb.createSheet();

			//Sheet sheet = wb.createSheet();
			org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);


		for(int page = 1; page <= pageCount; page++) {
			List<T> lists = _dbDao.find(hsql, params, page, rows);
			T model = null;
			if (lists.size() == 0) {
				model = (T) Class.forName(className).newInstance();
			} else {
				model = lists.get(0);
			}

			//1.写表头
			Field[] originFields = model.getClass().getDeclaredFields();
			//2.排列顺序
			ArrayList<String> fields = new ArrayList<>();
			for (String name : orderLists) {
				for (int j = 0; j < originFields.length; j++) {
					Field field = originFields[j];
					if (name.equals(field.getName())) {
						fields.add(name);
						break;
					}
				}
			}

			List<Integer> ingnoreColumns = new ArrayList<Integer>();
			int cellIndex = 0;
			for (int j = 0; j < fields.size(); j++) {
				String name = fields.get(j);
				if (relations.containsKey(name)) {
					header.createCell(cellIndex++).setCellValue(relations.get(name));
				} else {
					ingnoreColumns.add(j);
				}
			}


			//2.写表内容数据
			for (int i = 0; i < lists.size(); i++) {
				T itemModel = lists.get(i);
				org.apache.poi.ss.usermodel.Row row = sheet.createRow((page - 1) * rows + i + 1);
				cellIndex = 0;
				for (int j = 0; j < fields.size(); j++) {
					if (ingnoreColumns.contains(j))
						continue;
					String value = "";
					try {
						value = JavaClass.getFieldValue(itemModel, fields.get(j));
					} catch (Exception e) {
						e.printStackTrace();
					}

					row.createCell(cellIndex++).setCellValue(value);
				}

				// manually control how rows are flushed to disk
				if (i % 100 == 0) {
					sheet.flushRows(100); // retain 100 last rows and flush all others
				}
			}

		}

		FileOutputStream fileOut = new FileOutputStream(file);
		try {
			wb.write(fileOut);
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException ex) {
					logger.info("表" + tableName + "数据写入excel失败!");
					logger.info(ex);
				}
			}
		}

		end = System.nanoTime();
		used = end - start;
		logger.info("write to excel used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");
		return file;
	}
	
	private T UpdateMode(T model, Map<String, String> keyWheres, String key) throws IllegalAccessException{
		if(keyWheres.size() < 1)
			return model;
		
		//1.获取查询SQL
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("from ");
		sb.append(model.getClass().getSimpleName());
		sb.append(" WHERE 1=1 ");
		
		int size = keyWheres.size();
		for (Entry<String, String> entry : keyWheres.entrySet()) {
			if(entry.getKey()==null){
				--size;
				continue;
				
			} else {
				try{
					params.put(entry.getValue(), JavaClass.getFieldObject(model, entry.getKey()));
				}catch(Exception e){
					--size;
					continue;
				}
				sb.append(" AND ");	
				sb.append(entry.getValue());
				sb.append(" = :" + entry.getValue());
			}
		}
		String SQL = sb.toString();
		
		boolean saveModel = false;
		List<T> lists = null;
		if(!keyWheres.isEmpty() && size < 1){
			saveModel = true;
			return model;
		} else {
			lists = _dbDao.find(SQL, params);
			if(lists == null || lists.size() < 1){
				saveModel = true;
			}
		}
		
		//2.查询SQL
		if(saveModel)
		{
			//3.保存或更新模型
			_dbDao.save(model);
			
			if(this._importExcelEvent != null){
				this._importExcelEvent.AfterSaveModel(model);
			}
			
			return model;
		} else if(lists.size() > 1 || lists == null){
			//无法定位是唯一记录，不做任何更新
			return model;
		}

		model = mergeModel(lists.get(0), model, key);
		//BeanUtils.copyProperties(lists.get(0), model);
	
		//3.保存或更新模型
		_dbDao.saveOrUpdate(model);
		
		if(this._importExcelEvent != null){
			this._importExcelEvent.AfterUpdateModel(model);
		}
		
		return model;
	}
	
	private T mergeModel(T oldModel, T newModel, String key){
		Field [] fields = newModel.getClass().getDeclaredFields();
		for (int j=0; j < fields.length; j++) {
			try{
				String fieldName = fields[j].getName();
				if(fieldName.equalsIgnoreCase(key))
					continue;
				Object value = JavaClass.invokeMethod(newModel, fieldName);
//				if(value != null){
					JavaClass.setFieldValue(oldModel, fieldName, value);
//				}
				
			} catch(Exception e){
				//e.printStackTrace();
			}
		}
		
		return oldModel;
	}
}
