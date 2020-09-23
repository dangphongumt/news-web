<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url var = "APIurl" value = "/api-admin-new"/>
<c:url var = "NewURL" value = "/admin-user"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách User</title>
</head>

<body>
    <div class="main-content">
    <form action ="<c:url value = '/admin-user'/>" id ="formSubmit" method="get"><!-- call doGet in /admin-new everytime Submit form-->
       
            <div class="main-content-inner">
                <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="#">Danh Sách USER</a>
                        </li>
                    </ul><!-- /.breadcrumb -->
                </div>
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                        		<c:if test="${not empty messageResponse }">
                        				<div class="alert alert-${alert}" role="alert">
						 					${messageResponse}
										</div>
                        		</c:if>
                        				
                            <div class="widget-box table-filter">
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <a flag="info"
                                            class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                            title='Thêm User' href='<c:url value="/admin-new?type=edit"/>'>
                                                        <span>
                                                            <i class="fa fa-plus-circle bigger-110 blue"></i>
                                                        </span>
                                            </a>
                                            <button id="btnDelete" type="button"
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
                                                            <span>
                                                                <i class="fa fa-trash-o bigger-110 red"></i>
                                                            </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-12">
                                    <div class= "table-responsive">

                                        <table  class="table table-bordered ">
                                            <thead>
                                                <tr>
                                                    <th> <input type="checkbox" id = "checkAll"></th>
                                                    <th>ID</th>
                                                    <th>User Name</th>
                                                    <th>Full Name</th>
                                                    <th>Role</th>
                                                    <th>Trạng thái</th>
                                                 	<th>Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="item" items="${model.listResult}">
                                                <tr>    
                                                        <td> <input type="checkbox" id = "checkbox_${item.id}" value = "${item.id}"></td>
                                                        <td>${item.id }</td>
                                                        <td>${item.username }</td>
                                                        <td>${item.fullName }</td>
                                                        <td>${item.roleId}</td>
                                                        <td>${item.status }</td>
                                                        <td>
                                                            <c:url var= "editURL" value = "/admin-new">
                                                                <c:param name = "type" value = "edit"/>
                                                                <c:param name = "id" value = "${item.id}"/>
                                                            </c:url>
                                                            <a href='${editURL}' class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                            	title = "Cập nhật bài viết">
                                                                <i class="fa fa-pencil-square-o" aria-hiden="true"></i>
                                                                
                                                             
                                                            </a>
                                                        </td>
                                                </tr>
                                            </c:forEach>


                                            </tbody>
                                        </table>

                                        <ul class="pagination" id="pagination"></ul>
                                      <input type="hidden" value="" id="page" name="page"/> <!--hidden input use for script  -->
                                      <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                      <input type="hidden" value="" id="sortName" name = "sortName"/>
                                      <input type="hidden" value="" id="sortBy" name = "sortBy"/>
                                   <input type="hidden" value="" id="type" name = "type"/> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
           			 </div>
       
    </form>
 </div>
<!--  <script type="text/javascript">  
//         var currentPage = ${model.page};// System.constant.MODEL
//         var totalPages = ${model.totalPage};
//       //  var visiblePages = ${model.maxPageItem};
//         var limit = 5;
//          $(function () {
//              window.pagObj = $('#pagination').twbsPagination({
//                  totalPages: totalPages,
//                  visiblePages:4 ,//list pages to show
//                  startPage:currentPage,
//                  onPageClick: function (event, page) {
//                     if(currentPage != page){
//                     $('#maxPageItem').val(limit);
//                     $('#page').val(page);
//                     $('#sortName').val('id');
//                     $('#sortBy').val('desc');
//                     $('#type').val('list');
//                     $('#formSubmit').submit();
//                     }
//                  }
//             });
//         });

//         $( "#btnDelete" ).click(function() {
//            var data = {};
//            var ids = $('tbody input[type=checkbox]:checked').map(function(){
//                return $(this).val();//if a row has checked, it will be add in ids
//                }).get();
//                data['ids'] = ids;
//                deleteNew(data);
//         });
//         function deleteNew(data) {
//         $.ajax({
//             url: '${APIurl}',
//             type: 'DELETE',
//             contentType: 'application/json',
//             data: JSON.stringify(data),
//             success: function (result) {
//                 window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=delete_success";//redirect to controller
//             }, 
//             error: function (error) {
//             	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
//             }
//         });
//     }
<!--     </script> -->
</body>

</html>