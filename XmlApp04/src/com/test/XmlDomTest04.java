/*===============================================
 * XmlDomTest04.java
 * - 콘솔 기반 자바 프로그램
 * - XML DOM 활용 -> 로컬(local) XML 읽어내기
  *   [breakfast_menu.xml)
 ==============================================*/

// breakfast_menu.xml 파일을 대상으로
/*
 * ■[Belgian Maffles] $5.59	650칼로리
 * - Light Belgian waffles covered with strawberries and whipped cream
 * --------------------------------------------------------------------
 * ■[Berry-Berry Belgian Waffles] $8.95	900칼로리
 * - Light Belgian waffles covered with an assortment of fresh berries and whipped cream
 */
// 이와 같이 출력이 이루어질 수 있도록 프로그램을 작성한다.
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest04
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "breakfast_menu.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			NodeList foodNodeList = root.getElementsByTagName("food");
			
			for(int i = 0; i < foodNodeList.getLength(); i++)
			{
				Node foodNode = foodNodeList.item(i);
				
				Element foodElement = (Element)foodNode;
				
				System.out.printf("■[%s] %s   %s칼로리 \n%s"
						         , getText(foodElement, "name")
						         , getText(foodElement, "price")
						         , getText(foodElement, "calories")
						         , getText(foodElement, "description")
						         );
				System.out.println();
				System.out.println();
			}
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
	}
	
	public static String getText(Element parent, String tagName)
	{
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
		
	}
}


































