package com.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TheKoreaTimesDAO
{
	// 공통 멤버 구성
	private Document xmlObj;
	private XPath xPath;
	
	// 기본 생성자
	public TheKoreaTimesDAO() throws ParserConfigurationException, IOException, SAXException {
		this("rss");
	}
	
	// 매개변수 있는 생성자
	public TheKoreaTimesDAO(String section) throws ParserConfigurationException, IOException, SAXException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		String str = String.format("https://www.koreatimes.co.kr/www/rss/%s.xml", section);
		
		URL url = new URL(str);
		InputSource is = new InputSource(url.openStream());
		
		xmlObj = builder.parse(is);
		xPath = XPathFactory.newInstance().newXPath();	
	}
	
	// 타이틀 가져오기
	public String getTitle() throws XPathExpressionException {
		String result = "";
		
		result = xPath.compile("/rss/channel/title").evaluate(xmlObj);
		
		return result;
	}
	
	// section 가져오기
	public String getSection() throws XPathExpressionException {
		String result = "";
		
		result = xPath.compile("/rss/channel/description").evaluate(xmlObj);
		
		return result;
	}
	
	// item 리스트 구하기
	public ArrayList<TheKoreaTimesDTO> getItemList() throws XPathExpressionException{
		
		ArrayList<TheKoreaTimesDTO> itemList = new ArrayList<TheKoreaTimesDTO>();
		
		NodeList itemNodeList = (NodeList)xPath.compile("/rss/channel/item").evaluate(xmlObj, XPathConstants.NODESET);
		
		for(int i = 1; i <= itemNodeList.getLength(); i++)
		{
			String title = xPath.compile(String.format("/rss/channel/item[%s]/title", i)).evaluate(xmlObj);
			String description = xPath.compile(String.format("/rss/channel/item[%s]/description", i)).evaluate(xmlObj);
			
			Element itemElement = (Element)itemNodeList.item(i-1);
			Element mediaElement = (Element)itemElement.getElementsByTagName("media:content").item(0);
			
			String media = xPath.compile(String.format("/rss/channel/item[%s]/content/@url", i)).evaluate(xmlObj);
			String author = xPath.compile(String.format("/rss/channel/item[%s]/author", i)).evaluate(xmlObj);
			String pubDate = xPath.compile(String.format("/rss/channel/item[%s]/pubDate", i)).evaluate(xmlObj);
			String link = xPath.compile(String.format("/rss/channel/item[%s]/link", i)).evaluate(xmlObj);
//
//			if(mediaElement != null) {
//				media = mediaElement.getAttribute("url");
//			}
//			
			
			TheKoreaTimesDTO dto = new TheKoreaTimesDTO(title, description, media, author, pubDate, link);
			
			itemList.add(dto);
		}
		
		return itemList;
	}
}






































