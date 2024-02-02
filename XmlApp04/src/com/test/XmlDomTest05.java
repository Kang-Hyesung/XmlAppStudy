package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest05
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			NodeList VehicleList = root.getElementsByTagName("VEHICLE");
			
			
			System.out.println("-----------------------------------------------------");
			System.out.println("no MAKE    MODEL    YEAR    STYLE             PRICE");
			System.out.println("-----------------------------------------------------");
			
			for(int i = 0; i < VehicleList.getLength(); i++)
			{
				Node vehicleNode = VehicleList.item(i);
				
				Element vehicleElement = (Element)vehicleNode;
				
				System.out.printf("%s  %s   %s  %s    %s     %s\n"
						        , getText(vehicleElement, "INVENTORY_NUMBER")
						        , getText(vehicleElement, "MAKE")
						        , getText(vehicleElement, "MODEL")
						        , getText(vehicleElement, "YEAR")
						        , getText(vehicleElement, "STYLE")
						        , getText(vehicleElement, "PRICE")
						        );
				System.out.println("Options ---------------------------------------------");
				
				NodeList optionNodeList = vehicleElement.getElementsByTagName("OPTIONS");
				
				if(optionNodeList.getLength() > 0)
				{
					Node optionNode = optionNodeList.item(0);
					Element optionElement = (Element)optionNode;
					NodeList subNodeList = optionElement.getChildNodes();
					
					for(int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						if(subNode.getNodeType() == 1)
						{
							Element subElement = (Element)subNode;
							System.out.printf("       %s : %s\n", subElement.getNodeName(), subElement.getTextContent());
						}
					}
					System.out.println("-----------------------------------------------------");
				}
				
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



















