package com.performance.action;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.performance.util.ExcelReader;
import com.performance.util.excel.XMLParse;

@ParentPackage("basePackage")
@Namespace("/")
public abstract class BaseAction<T> extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected File upload;
	protected String contentType; //上传文件类型
	protected String uploadFileName;    //上传文件名称
	public XMLParse _xmlParse=null;
	private ExcelReader _excelReader=null;
    public BaseAction(){
    	try {
			_xmlParse=new XMLParse();
			_excelReader=new ExcelReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void setUpload(File file) {
		this.upload = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.uploadFileName = filename;
	}
	
	public void writeJson(Object object) {
		try {
			String json =JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			json = JsonCharFilter(json);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/// <summary>
/// Json特符字符过滤，参见http://www.json.org/
/// </summary>
/// <param name="sourceStr">要过滤的源字符串</param>
/// <returns>返回过滤的字符串</returns>
	public static String JsonCharFilter(String sourceStr)
	{
		sourceStr = sourceStr.replace("\b", "");
		sourceStr = sourceStr.replace("\t", "");
		sourceStr = sourceStr.replace("\n", "");
		sourceStr = sourceStr.replace("\f", "");
		sourceStr = sourceStr.replace("\r", "");
		return sourceStr;
	}

	protected String getUserCode(){
		ActionContext context=ActionContext.getContext();     
	    Map  parameterMap=context.getParameters();  
	    String[] params = (String[]) parameterMap.get("usercode");
	    return params[0];
	}
	
	
	
	/**
	 * 
	 * @param url  上传文件的路径
	 * @return 是否继续导入
	 */
		private Class<T> clazz;
		public boolean compareTitle(String url) {
			 ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
			try {
				List<String> excelTitles = new ArrayList<String>();
				List<String> xmlTitles = new ArrayList<String>();
				HashMap<String, String>  xmlTitlesMap =new HashMap<String,String>();
				String[] titles = _excelReader.readExportExcelTitle(url);
				Collections.addAll(excelTitles, titles);
				xmlTitlesMap = (HashMap<String, String>) _xmlParse.GetTableMaps("t_"+clazz.getSimpleName().toLowerCase());
				int acount=xmlTitlesMap.size();
				for (String key:xmlTitlesMap.keySet()) {
					xmlTitles.add(xmlTitlesMap.get(key));
				}
				//boolean a=excelTitles.retainAll(xmlTitles);
				xmlTitles.retainAll(excelTitles);
				 if(xmlTitles.size()!=acount){
		 			return false;
		 		}else{
			 		return true;
		 
		 		}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		
		}

}
