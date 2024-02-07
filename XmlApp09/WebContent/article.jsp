<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.test.TheKoreaTimesDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.TheKoreaTimesDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	
	String section = "rss";

	if(request.getParameter("section") != null){
		section = request.getParameter("section");
		System.out.println(section);
	}
	
	TheKoreaTimesDAO dao = new TheKoreaTimesDAO(section);
	
	String title = dao.getTitle();
	String subject = dao.getSection();
		
	ArrayList<TheKoreaTimesDTO> itemList = dao.getItemList();
	
	StringBuffer sb = new StringBuffer();
	
	sb.append("<div class='articleList'>");
	
	for(TheKoreaTimesDTO dto : itemList)
	{
		sb.append("<div class='article'>"
			+	"<img src='"+ dto.getMedia() + "'" + "alt='사진정보없음'" + "/>"
			+	"<div class='content'>                                                                                                                                                          "
			+	"	<div class='contentList'><h2>Title : " + dto.getTitle()  + "</h2></div>   "
			+	"	<div class='contentList'>Description : " + dto.getDescription() + "</div>   "
			+	"	<div class='contentList'>Author :  " + dto.getAuthor() + "</div>             "
			+	"	<div class='contentList'>Publish : " + dto.getPubDate() + "</div>          "
			+	"	<div class='contentList'>Link : <a href='"+ dto.getLink() +"'>" + dto.getLink() + "</a></div>                                                             "
			+	"</div>                                                                                                                                                                         "
			+   " </div>"); 
	}
	
	sb.append("</div>");	
%>   
                                                                                                                                                                                       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
	@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
	*{font-family: 'Pretendard-Regular';}
	
	.main{display: flex;}
	.aside{width: 400px;}
	.article{display: flex; width: 1000px; border-top: 2px solid gray; padding-bottom: 20px;}
	.main{padding: 8px 0 0 15px;}
	img{width: 400px; height: 300px;}
	.contentList{padding: 8px;}
	.sort{padding-bottom: 10px; font-weight: bold;
		}
	a {text-decoration: none; color: gray;}
	.sort:hover,
	.sort:focus {
	  box-shadow: inset 0 0 0 2em var(--hover);
	}
	button {  
	  color: var(--color);
	  transition: 0.25s;
	  
	  &:hover,
	  &:focus { 
	    border-color: var(--hover);
	    color: #fff;
	    
	  }
	}
	.btn{width : 170px;
	    height : 50px;
	    font-size: 15px;}
	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

		$(document).ready(function()
		{
			$(".btn").click(function()
			{
				
				$(this).val();
				
				$(location).attr("href", "http://localhost:3306/XmlApp09/article.jsp?section=" + $(this).val());
			});
		});

</script>

</head>
<body>

<h1><%=title %></h1>
<h2><%=subject %></h2>

<div class="main">

	<div class="aside">
		<div class="sort"><button class="btn" value="rss">All News</button></div>
		<div class="sort"><button class="btn" value="northkorea">North Korea</button></div>
		<div class="sort"><button class="btn" value="entertainment">Entertainment</button></div>
		<div class="sort"><button class="btn" value="opinion">Opinion</button></div>
		<div class="sort"><button class="btn" value="nation">National</button></div>
		<div class="sort"><button class="btn" value="biz">Economy</button></div>
		<div class="sort"><button class="btn" value="tech">Biz & Tech</button></div>
		<div class="sort"><button class="btn" value="arts">Culture</button></div>
		<div class="sort"><button class="btn" value="sports">Sports</button></div>
		<div class="sort"><button class="btn" value="world">World</button></div>
	</div>
	<!-- 
	<div class="articleList">
	
		<div class="article">
			<img src="http://img.koreatimes.co.kr/upload/thumbnailV2/8288c2d113434a05b19996a846c38ea6.png" alt="" />
			
			<div class="content">
				<div class="contentList"><h3>제목 : SK Innovation 2023 net profit down 71.2% to $409.8 mil.</h3></div>
				<div class="contentList">설명 : SK Innovation on Tuesday reported its 2023 net income of 546.3 billion won ($409.8 million), down 71.2 percent from a year earlier.</div>
				<div class="contentList">이메일 :  webmaster@koreatimes.co.kr(KoreaTimes)</div>
				<div class="contentList">작성일 : Tue, 06 Feb 2024 13:05:05 GMT</div>
				<div class="contentList">링크 : https://koreatimes.co.kr/www/nation/2024/02/419_368303.html?utm_source=fl</div>
			</div>
		</div>
		<div class="article">
			<img src="http://img.koreatimes.co.kr/upload/thumbnailV2/8288c2d113434a05b19996a846c38ea6.png" alt="" />
			
			<div class="content">
				<div class="contentList"><h3>제목 : SK Innovation 2023 net profit down 71.2% to $409.8 mil.</h3></div>
				<div class="contentList">설명 : SK Innovation on Tuesday reported its 2023 net income of 546.3 billion won ($409.8 million), down 71.2 percent from a year earlier.</div>
				<div class="contentList">이메일 :  webmaster@koreatimes.co.kr(KoreaTimes)</div>
				<div class="contentList">작성일 : Tue, 06 Feb 2024 13:05:05 GMT</div>
				<div class="contentList">링크 : https://koreatimes.co.kr/www/nation/2024/02/419_368303.html?utm_source=fl</div>
			</div>
		</div>
	
	</div>
	  -->
	  
	  <%=sb.toString() %>
</div>



</body>
</html>