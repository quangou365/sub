<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div data-role="page" id="pageone" data-theme="d" width="700px">
  <div data-role="content">
  ${message}
<br>
<a href="#pageone" data-role="button" data-inline="true" data-icon="back">返回</a>

订阅记录
  <ol data-filter="true">
   <ul data-role="listview" data-autodividers="true" data-inset="true" data-filter="true" id="uls">
    
    <li>
       <a href="#">        
       <h2>Google Chrome</h2>
       <p>Google Chrome is a free, open-source web browser. Released in 2008.</p>
       </a>
       <a href="#">Some Text</a>
    </li>
    
    <c:forEach items="${list }" var="b">
    <li>
       <a href="#">        
       <h2>${b.cname }</h2>
       <p>订约时间:${b.createTime }</p>
       </a>
       <a href="#">Some Text</a>
    </li>
    </c:forEach>
    
    
    
  </ul>
  </ol>
  </div>
</div> 

</body>
</html>