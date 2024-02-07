 /*===============================================
 * XmlDomTest08.java
 * - 콘솔 기반 자바 프로그램
 * - XML DOM 활용 -> 원격(local) XML 읽어내기
 *   https://fs.jtbc.co.kr/RSS/newsflash.xml
 *   ※ 언론사 뉴스로부터 얻어낸 데이터
 ==============================================*/

/*
title> JTBC News
link> https://fs.jtbc.co.kr/RSS/newsflash.xml
description> 속보 RSS
copyright> Copyright(C) JTBC All rights reserved.

주요 기사 -------------------------------------------------------------------------------------------------
title> 2월 2일 (금) 아침& 다시보기
description> 시청자 여러분 안녕하십니까, 2월 2일 JTBC 아침& 시작합니다. 먼저 오늘 아침 주요뉴스입니다.
link>https://news.jtbc.co.kr/article/article.aspx?news_id=NB12163608
pubDate>2024.02.02
-----------------------------------------------------------------------------------------------------------
title>
description>
 */
package com.test;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest08
{
	public static void main(String[] args)
	{	
		/*
		 * 1. 원격 XML 정보를 요청하고, 그 결과를 메모리에 로드
		 *    -> XML DOM 구성
		 *    - DB 활용
		 *    - DBF 활용
		 *    - XML 로딩
		 * 2. 루트 엘리먼트 접근
		 * 3. 특정 하위 엘리먼트 접근
		 * 4. 텍스트 노드 접근
		 *    -> 필요한 데이터 획득
		 * 5. 결과 처리(출력)
		 * 
		 */
		try
		{
			// ※ DOM(Document Object Model)
			//    - XML 이나 HTML Document(문서)를 응용프로그램에서 사용하기 위한 API rbclr
			//    - DOM 은 Document(문서)의 각 부분들을 객체(Object)로 표현한 API
			
			// ※ DOM(Document Object Model) 파서(Parser)
			//    - XML 문서를 읽고, 해석한 후,
			//      해석한 결과를 메모리에 DOM 객체 트리 구조로 생성시키는 파서(Parser)
			//    - 원하는 데이터에 접근할 수 있도록 해주어
			//      대상 데이터를 검색, 수정, 삭제할 수 있도록 지원
			
			// ※ 주로 사용되는 DOM(Document Object Model) 인터페이스
			//    - Node
			//      : 모든 객체의 부모 인터페이스로서 공통적으로 기능하는 함수를 가진다.
			//    - NodeList (NODESET)
			//      : 노드들을 리스트로 받아 처리하기 쉽도록(일괄 처리) 한 것
			//    - Document
			//      : DOM(Document Object Model) 트리 구조의 최상위 노드로
			//        XML 문서 자체를 의미한다.
			//    - Element
			//      : XML 의 엘리먼트에 해당하는 객체 유형
			//    - Attr
			//      : XML 의 Attribute 에 해당하는 객체 유형
			//    - CharacterData
			//      : XML 의 데이터에 해당하는 객체 유형
			//    - Text
			//      : 문자 데이터(내용)에 해당하는 객체 유형
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// DocumentBuilderFactory
			//  DocumentBuilder(DOM Parser)를 생성할 수 있도록 해주는 Factory 클래스(객체)
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			// DocumentBuilder
			// DOM Parser 객체의 클래스
			
			Document xmlObj = null;
			
			String str = "https://fs.jtbc.co.kr/RSS/newsflash.xml";
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			xmlObj = builder.parse(is);
				
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			Node channelNode = root.getElementsByTagName("channel").item(0);
			Element channelElement = (Element)channelNode;
			
			System.out.printf("Title> %s\n", XMLDOM.getText(channelElement, "title"));
			System.out.printf("link> %s\n", XMLDOM.getText(channelElement, "link"));
			System.out.printf("description> %s\n", XMLDOM.getText(channelElement, "description"));
			System.out.printf("copyright> %s\n", XMLDOM.getText(channelElement, "copyright"));
			
			System.out.println("주요 기사------------------------------------------------------------------------------------");
			
			NodeList itemNodeList = root.getElementsByTagName("item");
//			for(int i = 0; i < itemNodeList.getLength(); i++)
//			{
//				Node itemNode = itemNodeList.item(i);
//				Element itemElement = (Element)itemNode;
//				
//				System.out.printf("Title> %s\n", XMLDOM.getText(itemElement, "title"));
//				System.out.printf("link> %s\n", XMLDOM.getText(itemElement, "link"));
//				System.out.printf("description> %s\n", XMLDOM.getText(itemElement, "description"));
//				System.out.printf("pubDate> %s\n", XMLDOM.getText(itemElement, "pubDate"));
//				System.out.println("-------------------------------------------------------------------------------------------------");
//			}
//			
			
			for(int i = 0; i < itemNodeList.getLength(); i++)
			{
				Node itemNode = itemNodeList.item(i);
				Element itemElement = (Element)itemNode;
				
				NodeList childNodeList = itemElement.getChildNodes();
				
				for(int j = 0; j < childNodeList.getLength(); j++)
				{
					System.out.printf("%s>%s%n"
									  , childNodeList.item(j).getNodeName()
									  , XMLDOM.getText(itemElement, childNodeList.item(j).getNodeName()));
				}
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}