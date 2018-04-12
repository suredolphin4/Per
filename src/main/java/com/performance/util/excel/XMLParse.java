package com.performance.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.performances.model.TDomain;

public class XMLParse {

	private Document docsDoc = null;
	
	public XMLParse() throws IOException, URISyntaxException{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data_import.xml").toURI());
		init(file);
	}
	
	public XMLParse(File file) throws IOException{
		init(file);
	}
	
	public String GetKey(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		Element elem = (Element) node;
		NodeList nodeList = elem.getElementsByTagName("key");
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node keyNode = nodeList.item(i);
	    	switch (keyNode.getNodeType()){
		    	case Node.ELEMENT_NODE:
		    	{
		    		return GetNodeAttributeItem(keyNode, "id");
		    	}
		    	default:
		    		break;
	    	}
		}
		return null;
	}
	
	public List<Match> GetMatches(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		Element elem = (Element) node;
		List<Match> matches = new ArrayList<Match>();
		NodeList nodeList = elem.getElementsByTagName("matches").item(0).getChildNodes();
		if(nodeList == null)
			return matches;
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node matchNode = nodeList.item(i);
	    	switch (matchNode.getNodeType())
	    	{
		    	case Node.ELEMENT_NODE:
		    	{
		    		int type = Integer.parseInt(GetNodeAttributeItem(matchNode, "type"));
		    		String key = GetNodeAttributeItem(matchNode, "key");
		    		String value = GetNodeValue(matchNode);
		    		matches.add(new Match(type, key, value));
		    		break;
		    	}
		    	default:
		    		break;
	    	}
		}
		
		return matches;
	}
	
	public Map<String, String> GetKeyWheres(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		Map<String, String> maps = new HashMap<String, String>();
		Element elem = (Element) node;
		NodeList nodeList = elem.getElementsByTagName("key").item(0).getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node mapNode = nodeList.item(i);
	    	switch (mapNode.getNodeType())
	    	{
		    	case Node.ELEMENT_NODE:
		    	{
			    	String objectField = GetNodeAttributeItem(mapNode, "id");
			    	String excelColumn = GetNodeValue(mapNode);
			    	if(!objectField.isEmpty() && !excelColumn.isEmpty()){
			    		maps.put(objectField, excelColumn);
			    	}
		    		break;
		    	}
		    	default:
		    		break;
	    	}
		}
		
		return maps;
	}
	
	public String GetTableAttribute(String tableName, String attrName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		return GetNodeAttributeItem(node, attrName);
	}
	
	public Map<String, String> GetTableMaps(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		Map<String, String> maps = new HashMap<String, String>();
		Element elem = (Element) node;
		NodeList nodeList = elem.getElementsByTagName("maps").item(0).getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node mapNode = nodeList.item(i);
	    	switch (mapNode.getNodeType())
	    	{
		    	case Node.ELEMENT_NODE:
		    	{
			    	String objectField = GetNodeAttributeItem(mapNode, "id");
			    	String excelColumn = GetNodeValue(mapNode);
			    	if(!objectField.isEmpty() && !excelColumn.isEmpty()){
			    		maps.put(objectField, excelColumn);
			    	}
		    		break;
		    	}
		    	default:
		    		break;
	    	}
		}
		
		return maps;
	}
	
	public ArrayList<String> GetOrderList(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		ArrayList<String> lists = new ArrayList<String>();
		Element elem = (Element) node;
		NodeList nodeList = elem.getElementsByTagName("maps").item(0).getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node mapNode = nodeList.item(i);
	    	switch (mapNode.getNodeType())
	    	{
		    	case Node.ELEMENT_NODE:
		    	{
			    	String objectField = GetNodeAttributeItem(mapNode, "id");
			    	String excelColumn = GetNodeValue(mapNode);
			    	if(!objectField.isEmpty() && !excelColumn.isEmpty()){
			    		lists.add(objectField);
			    	}
		    		break;
		    	}
		    	default:
		    		break;
	    	}
		}
		
		return lists;
	}
	
	public List<String> GetUpdateSQLs(String tableName) throws XPathExpressionException{
		Node node = GetTableNode(tableName);
		if(node == null)
			return null;
		List<String> lists = new ArrayList<String>();
		Element elem = (Element) node;
		NodeList nodeList = elem.getElementsByTagName("updates").item(0).getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	Node updateNode = nodeList.item(i);
	    	switch (updateNode.getNodeType())
	    	{
		    	case Node.ELEMENT_NODE:
		    	{
			    	String updateSQL = GetNodeValue(updateNode);
			    	if(!updateSQL.isEmpty())
			    		lists.add(updateSQL);
		    		break;
		    	}
		    	default:
		    		break;
	    	}
		}
		
		return lists;
	}
	
	private Node GetTableNode(String tableName) throws XPathExpressionException{
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//table[@name=\"" + tableName + "\"]");
		NodeList tableNodeList = (NodeList) expr.evaluate(docsDoc, XPathConstants.NODESET);
		if(tableNodeList == null || tableNodeList.getLength() < 1)
			return null;
		return tableNodeList.item(0);
	}
	
	private String GetNodeAttributeItem(Node node, String itemName){
		Node childNode = node.getAttributes().getNamedItem(itemName);
		if(childNode == null)
			return null;
		return childNode.getNodeValue();
	}
	
	private String GetNodeValue(Node node){
		NodeList nodeList = node.getChildNodes();
		if(nodeList == null || nodeList.getLength() < 1)
			return "";
		return nodeList.item(0).getNodeValue();
	}
	
	private void init(File file) throws IOException{
		InputStream is = FileUtils.openInputStream(file);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    docsDoc = db.parse(is);
		}
		catch(ParserConfigurationException e) {e.printStackTrace();}
		catch(SAXException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public static void main(String[] args) throws URISyntaxException {

		XMLParse xmlParse;
		try {
			xmlParse = new XMLParse();
			try {
				String test = xmlParse.GetTableAttribute("t_domain", "class");
				test = xmlParse.GetTableAttribute("t_domain", "class1");
				test = xmlParse.GetTableAttribute("t_domain", "sheetIndex");
				String key = xmlParse.GetKey("t_domain");
				Map<String,String> tablemaps=null;
				tablemaps=xmlParse.GetTableMaps("t_domain");
				xmlParse.GetUpdateSQLs("t_domain");
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
