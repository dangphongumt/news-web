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

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row">
			<c:forEach var = "item" items = "${model.listResult}">
				   <c:url var= "detailURL" value = "/tin-chi-tiet">                      
                          <c:param name = "id" value = "${item.id}"/>
                      </c:url>
                        
                                      <div class="col-lg-4 col-md-6 mb-4">
						            <div class="card h-100">
						              <a href='${detailURL}'>
						              <img class="card-img-top" src="" alt="">
							              <div class="card-body">
							                <h4 class="card-title">
							                 ${item.title}
							                </h4>
							                <p class="card-text">${item.shortDescription}</p>
							              </div>
						              </a>
						              <div class="card-footer">
						      
						              </div>
						            </div>
						          </div>
                          
                                    
                                                            
						 
			</c:forEach>
         	 							<ul class="pagination" id="pagination"></ul>
                                      <input type="hidden" value="" id="page" name="page"/> <!-- hidden input use for script-->
                                      <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                      <input type="hidden" value="" id="sortName" name = "sortName"/>
                                      <input type="hidden" value="" id="sortBy" name = "sortBy"/>
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->
	
    </div>
    <!-- /.row -->
    <script type="text/javascript">
    var currentPage = ${model.page};// System.constant.MODEL
    var totalPages = ${model.totalPage};
  //  var visiblePages = ${model.maxPageItem};
    var limit = 5;
     $(function () {
         window.pagObj = $('#pagination').twbsPagination({
             totalPages: totalPages,
             visiblePages:4 ,//list pages to show
             startPage:currentPage,
             onPageClick: function (event, page) {
                if(currentPage != page){
                $('#maxPageItem').val(limit);
                $('#page').val(page);
                $('#sortName').val('title');
                $('#sortBy').val('desc');
                $('#type').val('list');
                $('#formSubmit').submit();
                }
             }
        });
    });

    </script>
</body>
</html>