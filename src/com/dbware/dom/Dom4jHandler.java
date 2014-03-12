package com.dbware.dom;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @Copyright 2012-2013 donnie(donnie4w@gmail.com)
 * @date 2012-12-12
 * @verion 1.0
 */
public interface Dom4jHandler {

	public Document loadbyString(String xml) throws Dom4jHandlerException;

	public Document loadbyPath(String path) throws Dom4jHandlerException;

	public Document getDocument();
	
	public String getXMLString();
	
    public String toString();
    
	public void setDocument(Document document);
	
	public List<String> getNodeAttributeValues(String path, String name) throws Dom4jHandlerException;

	public String getNodeAttributeValue(String path, String name) throws Dom4jHandlerException;

	public String getNodeValue(String path) throws Dom4jHandlerException;
	
	public List<String> getNodesValue(String path) throws Dom4jHandlerException;

	public List<Element> getNodes(String path) throws Dom4jHandlerException;

	public boolean hasNode(String path);

	public List<Element> getChildNodes(String path) throws Dom4jHandlerException;

	public Element getNode(String path) throws Dom4jHandlerException;

}
