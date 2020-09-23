<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp"%><!--  contain library support JSP -->
    <c:url var = "APIurl" value = "/api-admin-new"/>
    <c:url var = "NewURL" value = "/admin-new"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
         
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
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
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                    	<c:if test = "${empty model.categoryCode}">
                                    		<option value="">Chọn loại bài viết</option>
	                                     	<c:forEach var = "item" items = "${categories}">
	                                     		<option value="${item.code}">${item.name}</option>
	                                     	</c:forEach>
                                    	</c:if>
                                    	<c:if test="${not empty model.categoryCode}">
                                    			<c:forEach var = "item" items = "${categories}">
	                                    			<option value="${item.code}" <c:if test="${item.code == model.categoryCode }"> selected = "selected"</c:if>>
	                                    			${item.name}
	                                    			</option>
                                    			</c:forEach>
	                                     				<option value="">Chọn loại bài viết</option>
                                    	</c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var editor = '';
	$( document ).ready(function() {
	    editor = CKEDITOR.replace('content');
	});//W-100 for any size photo or video.???

	$('#btnAddOrUpdateNew').click(function (e){
		e.preventDefault();
// 		var title = $('#title').val();

		var data = {};
        var formData  = $('#formSubmit').serializeArray();
        $.each(formData, function(i, v){
            //console.log(value);
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();//serializeArray can not get data from CKEDITOR
        var id = $('#id').val();
        if(id == ""){
        	addNew(data);
        }else{
        	updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	 window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=insert_success";
            }, 
            error: function (error) {
            	 window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	 window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success";
            }, 
            error: function (error) {
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>