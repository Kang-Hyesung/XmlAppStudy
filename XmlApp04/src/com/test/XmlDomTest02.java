/*===============================================
 * XmlDomTest02.java
 * - 콘솔 기반 자바 프로그램
 * - XML DOM 활용 -> 로컬(local) XML 읽어내기
 *   [memberList.xml)
 ==============================================*/

// 노노노 010-1234-4567
// 김김김 010-9987-8876

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest02
{
	public static void main(String[] args)
	{
		try
		{
			// 1. XML 파일(memberList.xml)을 메모리에 로드
			//    -> XML DOM 생성
			// 2. 루트 엘리먼트 접근
			// 3. 루트 엘리먼트 특정 하위 엘리먼트 접근
			//    -> 위치, 이름 등을 기준으로 접근(사실상 문법적으로 다양한 접근 방법 지원)
			// 4. 텍스트 노드(속성 노드) 접근
			//    -> 원하는 데이터 얻어내기
			// 5. 결과 처리
			//    -> 특정 데이터 출력
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "memberList.xml";
			xmlObj = builder.parse(url);
			
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			// 문서의 대표 엘리먼트(루트 엘리먼트)를 얻어내는 과정
			
			//System.out.println(root.getNodeName());	// memberList
			
			// 얻어낸 루트 엘리먼트를 찾아 특정 하위 엘리먼트에 접근
			NodeList memberNodeList = root.getElementsByTagName("memberInfo");
			// 태그의 이름을 가지고 자식이나 자손 노드에 접근을 수행하는 메소드
			
			// XML 에서 모든 노드는 루트 엘리먼트의 하위에 존재
			
			// 이렇게 얻어낸 NodeList 객체에 포함되어 있는 Node 의 갯수를
			// getLength() 메소드를 통해 확인할 수 있다.
			// 이를 활용하여 각각의 Node 에 접근하는 반복의 횟수를 특정할 수 있다.
			
			//테스트 System.out.println(memberNodeList.getLength());	// 2
			
			for(int i = 0; i < memberNodeList.getLength(); i++)
			{
				Node memberNode = memberNodeList.item(i);
				// getElementsByTagName() 메소드가 이름을 통해 대상을 획득했다면
				// item() 메소드는 위치(인덱스)를 통해 대상을 획득하게 된다.
				
				Element memberElement = (Element)memberNode;
				// 이와 같은 코드 구성이 가능한 이유는 엘리먼트가 노드의 하위개념이기 때문에 가능한 구문
				
				
				System.out.printf("%s %s%n"
						       , getText(memberElement, "name")
						       , getText(memberElement, "telephone"));
				
				// 노노노 010-1234-4567 JAVA SE JAVA SE 
				
				// 대상 문서(memberList.xml)의
				// 커리큘럼(curriculum)에 대한 처리 추가-------------------
				
				// memberList.xml 의
				// memberInfoElement 로 부터 curriculum NodeList 얻어오기
				NodeList curriculumNodeList = memberElement.getElementsByTagName("curriculum");
				
				if(curriculumNodeList.getLength() > 0)
				{
					Node curriculumNode = curriculumNodeList.item(0);
					Element curriculumElement = (Element)curriculumNode;
					
					// 방법 1
					/*
					NodeList subNodeList = curriculumElement.getElementsByTagName("sub");
					for(int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						Element subElement = (Element)subNode;
						System.out.printf("%s", subElement.getTextContent());
					}
					System.out.println();
					*/
					
					// 방법 2
					/*
					 -------------------- --------------------------------------------
					 Node Type            Named Constant
					 -------------------- --------------------------------------------
					     1                ELEMENT_NODE
					     2				  ATTRIBUTE_NODE
					     3                TEXT_NODE
					     4				  CDATA_SECTION_NODE
					     5                ENTITY_REFERENCE_NODE
					     6				  ENTITY_NODE
					     7				  PROCESSING_INSTRUCTION_NODE
					     8 				  COMMENT_NODE
					     0                DOCUMENT_NODE
					     10				  DOCUMENT_TYPE_NODE
					     11               DOCUMENT_FRAGMENT_NODE
					     12				  NOTATION_NODE
					 -------------------- --------------------------------------------
					 */
					
					NodeList subNodeList = curriculumElement.getChildNodes(); // 구조를 통해서 얻어오는 형태
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						if(subNode.getNodeType() == 1)		// ELEMENT_NODE 	// check
						{
							Element subElement = (Element)subNode;
							System.out.printf("%s ", subElement.getTextContent());
						}
						System.out.println();
					}
					
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
		NodeList curNodeList = parent.getChildNodes();
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
	
}
















