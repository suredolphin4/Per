package com.performance.util.excel;

import java.io.Console;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class JavaClass {
	
	 public static  String getFieldValue(Object owner, String fieldName)
	 {
	     return invokeMethod(owner, fieldName).toString();
	 }
	 
	 public static  Object getFieldObject(Object owner, String fieldName)
	 {
	     return invokeMethod(owner, fieldName);
	 }
	 
	 /**
     * 
     * 执行某个Field的getField方法
     * 
     * @param owner 类
     * @param fieldName 类的属性名称
     * @param  参数，默认为null
     * @return 
     */
    public static  Object invokeMethod(Object owner, String fieldName)
    {
        Class<? extends Object> ownerClass = owner.getClass();
        
        //fieldName -> FieldName  
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        
        Method method = null;
        try 
        {
            method = ownerClass.getMethod("get" + methodName);
        } 
        catch (SecurityException e) 
        {
        	return null;
            //e.printStackTrace();
        } 
        catch (NoSuchMethodException e) 
        {
            // e.printStackTrace();
            return null;
        }
        
        //invoke getMethod
        try
        {
            return method.invoke(owner);
        } 
        catch (Exception e)
        {
            return null;
        }
    }
    
    public static boolean setFieldValue(Object owner, String fieldName, Object args) throws NoSuchFieldException, SecurityException
    {
//    	if(args == null)
//    		return false;
    	
    	Class<? extends Object> ownerClass = owner.getClass();
    	Field field = ownerClass.getDeclaredField(fieldName);
    	String methodName = "set" + field.getName().substring(0, 1).toUpperCase()+ field.getName().substring(1);
    	
        Method method = null;
        try 
        {
        	method = ownerClass.getDeclaredMethod(methodName, field.getType());
        } 
        catch (SecurityException e) 
        {
            //e.printStackTrace();
            return false;
        } 
        catch (NoSuchMethodException e) 
        {
            //e.printStackTrace();
        	return false;
        }
        
        try
        {
        	method.invoke(owner, args);
            return true;
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
    }
    
    public static boolean setFieldValue(Object owner, String fieldName, Object args, List<Match> matches)
    {
    	Class<? extends Object> ownerClass = owner.getClass();
    	Field field;
		try {
			field = ownerClass.getDeclaredField(fieldName);
			return setFieldValue(owner, field, args, matches);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return false;
    }
    
    public static boolean setFieldValue(Object owner, Field field, Object args, List<Match> matches)
    {
    	if(args == null)
    		return false;
    	
    	String methodName = "set" + field.getName().substring(0, 1).toUpperCase()+ field.getName().substring(1);
    	
        Class<? extends Object> ownerClass = owner.getClass();
        Method method = null;
        try 
        {
        	method = ownerClass.getDeclaredMethod(methodName, field.getType());
        } 
        catch (SecurityException e) 
        {
            //e.printStackTrace();
            return false;
        } 
        catch (NoSuchMethodException e) 
        {
            //e.printStackTrace();
        	return false;
        }
        
        try
        {
        	method.invoke(owner, typeConversion(field.getType(), args,  matches));
            return true;
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
    }
    
    
    public static Object typeConversion(Class<?> cls, Object object, List<Match> matches) {  
        Object obj = object;  
        String nameType = cls.getSimpleName();  
        switch (nameType)
        {
        	case "int":
        	case "integer":
        	case "Integer":
        		if( object instanceof Double){
        			return Integer.parseInt(new java.text.DecimalFormat("0").format(object));
        			//return (int)(object); 
        		} else if(object instanceof Integer){
        			return object;
        		} else {
        			if(!object.toString().isEmpty() && isNumeric(object.toString()))
            		{
        				return Integer.valueOf(object.toString()).intValue();  
            		} else {
            			for(Match match: matches){
            				switch(match.Type){
            					case 0:
            						if(match.Key.equals(object.toString())){
            							return Integer.valueOf(match.Value);
            						}
            					case 1:
            						break;
            					default:
            						break;
            				}
            			}
            		}
        		}
        		
        		return 0;
        	case "float":
        	case "Float":
        		try{
        			return Float.valueOf(object.toString()).floatValue();  
        		} catch(Exception e) {
        			for(Match match: matches){
        				switch(match.Type){
        					case 0:
        						if(match.Key.equals(object.toString())){
        							return Float.valueOf(match.Value);
        						}
        					case 1:
        						break;
        					default:
        						break;
        				}
        			}
        		}
        		return 0;
        	case "double":
        	case "Double":
//        		try
//        		{
//					return Double.valueOf(object.toString()).doubleValue();
//        		} catch(Exception e) {
//        			for(Match match: matches){
//        				switch(match.Type){
//        					case 0:
//        						if(match.Key.equals(object.toString())){
//        							return Double.valueOf(match.Value);
//        						}
//        					case 1:
//        						break;
//        					default:
//        						break;
//        				}
//        			}
//        		}
				if(!object.toString().isEmpty() && isNumeric(object.toString())){
					return Double.valueOf(object.toString()).doubleValue();
				}else{
					for(Match match: matches){
						switch(match.Type){
							case 0:
								if(match.Key.equals(object.toString())){
									return Double.valueOf(match.Value);
								}
							case 1:
								break;
							default:
								break;
						}
					}
				}
        		return 0.0;
        	case "long":
        	case "Long":
        		if(!object.toString().isEmpty() && isNumeric(object.toString())){
        			return Long.valueOf(object.toString()).longValue();
        		} else {
        			for(Match match: matches){
        				switch(match.Type){
        					case 0:
        						if(match.Key.equals(object.toString())){
        							return Long.valueOf(match.Value);
        						}
        					case 1:
        						break;
        					default:
        						break;
        				}
        			}
        		}
        		 
        		return 0;
        	case "short":
        	case "Short":
        		if(!object.toString().isEmpty() && isNumeric(object.toString())){
        			return Short.valueOf(object.toString()).shortValue();  
        		} else {
        			for(Match match: matches){
        				switch(match.Type){
        					case 0:
        						if(match.Key.equals(object.toString())){
        							return Short.valueOf(match.Value);
        						}
        					case 1:
        						break;
        					default:
        						break;
        				}
        			}
        		}
        		return 0;
        	case "String":
        		if( obj instanceof Double){
        			Double d = (Double)obj;
        			if(d == d.intValue()){
        				return new java.text.DecimalFormat("0").format(object);
        			} else {
        				return obj.toString();
        			}
        		} else if(obj instanceof Integer){
        			return obj.toString();
        		}
        		
        		for(Match match: matches){
    				switch(match.Type){
    					case 4:
    						if(match.Key.equals(object.toString())){
    							return match.Value;
    						}
    						break;
    					case 5:
    						if(object.toString().contains(match.Key)){
    							return match.Value;
    						}
    						break;
    					default:
    						break;
    				}
    			}
        		return obj.toString(); 
        	
        	case "boolean":
        	case "Boolean":
        		return Boolean.valueOf(object.toString());  
        	
        	case "Character":
        		return object.toString().charAt(1); 

        	case "Date":
				try {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
					return format.parse(object.toString());
				}catch (ParseException e){
					return "";
				}
        	default: 
        		break;
        		
        }

        return obj;  
    }  
        
    public static boolean isNumeric(String str){
		return str.matches("[-+]?\\d*\\.?\\d+");
		//return str.matches("\\d*");
 //   	String reg="([+-]{0,1}0\\.\\d+)|([+-]{0,1}[1-9]\\d*\\.\\d+)|([+-]{0,1}[1-9]\\d*)|([+-]{0,1}0)";
 //   	return str.matches(reg);
//    	Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+"); 
//    	return pattern.matcher(str).matches();
    }
}
