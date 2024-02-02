 /*===============================================
 * XmlDomTest07.java
 * - 콘솔 기반 자바 프로그램
 * - XML DOM 활용 -> 원격(local) XML 읽어내기
 *   (http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108)
 *   [???.xml)
 *   ※ 기상철 날씨누리로부터 얻어낸 데이터
 ==============================================*/

/*
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108 전국
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109 서울 경기도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=105 강원도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=131 충청북도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=133 충청남도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=146 전라북도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=156 전라남도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=143 경상북도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=159 경상남도
 * http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=184 제주특별자치도
 */
package com.test;



import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest07
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String[] stnId = {"108", "109", "105", "131", "133", "146", "156", "143", "159", "184"};
		
		
		do
		{
			try
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document xmlObj = null;
				
				// Local XML 파일인 경우
//				String url = "rss.xml";	// 경로지정
//				xmlObj = builder.parse(url);
				
				// Remote XML 파일인 경우
				
				// 안내 -> 외부의 입력값 처리
				System.out.println("■■■ 기상청 육상 중기 예보 ■■■");
				System.out.println("-----------------------------------");
				System.out.println("1. 전국");
				System.out.println("2. 서울 경기도");
				System.out.println("3. 강원도");
				System.out.println("4. 충청북도");
				System.out.println("5. 충청남도");
				System.out.println("6. 전라북도");
				System.out.println("7. 전라남도");
				System.out.println("8. 경상북도");
				System.out.println("9. 경상남도");
				System.out.println("10. 제주특별자치도");
				System.out.println("-----------------------------------");
				System.out.print(">> 지역 선택(종료 : 0) : ");
				
 				String m = sc.next();
 				if(m.equals("0"))
 					break;
 				
 				// 사용자가 선택한 지역 요청 주소에 담기
 				String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s"
 						                  , stnId[Integer.parseInt(m) - 1]);
				
 				//System.out.println(str);
 				
 				// 문자열로 구성한 요청 주소를 URL 객체 구성 -> 절대 경로 지정 방식
 				URL url = new URL(str);
 				
 				// 구성한 URL 로 접근하여 데이터 내용 읽어오기 -> 스트림(stream)
 				InputSource is = new InputSource(url.openStream());
  				xmlObj = builder.parse(is);
  				
  				// ----------------------------------------------------
  				
  				// 루트 엘리먼트 접근
  				Element root = xmlObj.getDocumentElement();
  				
  				// 타이틀 추출하기
  				Node itemNode = root.getElementsByTagName("item").item(0);
  				Element itemElement = (Element)itemNode;
  				System.out.printf("%s\n", XMLDOM.getText(itemElement, "title"));
  				
  				// 기상전망 추출하기
  				System.out.println("[기상 전망] ------------------------------------------------------------");
  				Node wfNode = root.getElementsByTagName("wf").item(0);
  				Element wfElement = (Element)wfNode;
  				
  				System.out.printf("%s%n%n", wfElement.getTextContent().replaceAll("<br />", "\n"));
  				
  				// 도시별 기상예보 추출하기
  				System.out.println("[육상 날씨] ------------------------------------------------------------");

  				NodeList locationNodeList = root.getElementsByTagName("location");
  				
  				for(int i = 0; i < locationNodeList.getLength(); i++)
  				{
  					Node locationNode = locationNodeList.item(i);
  					Element locationElement = (Element)locationNode;
  					
  					// 도시 : 도시
  					System.out.println("------------------------------------------------------------------");
  					System.out.printf("도시 : %s\n", XMLDOM.getText(locationElement, "city"));
  					System.out.println("------------------------------------------------------------------");
  					
  					NodeList dataNodeList = root.getElementsByTagName("data");
  					
  					// 날짜 시간, 날씨 예보, 최저 온도 ~ 최고 온도 / 강수 확률
  					for(int j = 0; j < dataNodeList.getLength(); j++)
  					{
  						Node dataNode = dataNodeList.item(j);
  						Element dataElement = (Element)dataNode;
  						
  						System.out.printf("%s   /   %s   /   %s℃ ~ %s℃   /   %s%%  %n"
  								, XMLDOM.getText(dataElement, "tmEf")
  								, XMLDOM.getText(dataElement, "wf")
  								, XMLDOM.getText(dataElement, "tmn")
  								, XMLDOM.getText(dataElement, "tmx")
  								, XMLDOM.getText(dataElement, "rnSt")
  								);
  						
  					}
  				}
  				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		} while (true);
		
		
	}
}


