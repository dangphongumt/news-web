<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Trang Chủ</title>
</head>
<body>

 <div class="row">

      <div class="col-lg-3">
		<br>
		<br>
        <h1 class="my-4">Danh Mục</h1>
        <div class="list-group">
         <c:forEach var="item" items="${categories}">
          <a href="#" class="list-group-item">${item.name}</a>	
         </c:forEach>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        		<div>
        			<p>${model.title}</p>
        			<p>${model.createdDate}</p>
        			
        			<p>${model.shortDescription}</p>
        			<p>${model.content}</p>
        			<p>${model.createdBy}</p>
        			
        		</div>
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->
	
    </div>
    <!-- /.row -->
  
</body>
</html>