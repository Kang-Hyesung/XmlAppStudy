package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest03
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "memList.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			NodeList memeberInfoList = root.getElementsByTagName("memberInfo");
			
			for(int i = 0; i < memeberInfoList.getLength(); i++)
			{
				Node memberInfoNode = memeberInfoList.item(i);
				
				Element memberInfoElement = (Element)memberInfoNode;
				
				System.out.printf("%s %s ", getText(memberInfoElement, "name")
										 , getText(memberInfoElement, "addr"));
				
				
				NodeList curriculumNodeList = memberInfoElement.getElementsByTagName("curriculum");
				
				if(curriculumNodeList.getLength() > 0)
				{
					Node curriculumNode = curriculumNodeList.item(0);
					Element curriculumElement = (Element)curriculumNode;
					
		
					NodeList subNodeList = curriculumElement.getElementsByTagName("sub");
					for(int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						Element subElement = (Element)subNode;
						System.out.printf("%s ", subElement.getTextContent());
					}
					System.out.println();
				}
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
		NodeList curNodeList = parent.getChildNodes();
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
}
