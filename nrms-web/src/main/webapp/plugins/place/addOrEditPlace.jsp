<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	textarea {
		resize : none;
	}
</style>
<script type="text/javascript">
	function savePlace(){
		if( !checkBlank($("#placeName").val())){
			$("#msgBoxInfo").html("请填写会议室名");
			$("#msgBox").modal('show');
			return;
		}
			
		if( !checkLengthBetween($("#placeName").val() , 1, 20) ){
			$("#msgBoxInfo").html("会议室名只允许长度1-20位");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkLengthBetween($("#placeDesc").val() , 0, 500) ){
			$("#msgBoxInfo").html("描述信息不能超过500字");
			$("#msgBox").modal('show');
			return;
		}
		
		$.ajax({
			url : '${basePath}/place/saveOrUpdatePlace',
			dataType : 'json' ,
			type : "post",
			data : $("#dataForm").serialize() ,
			success : function(data) {
				if (data.success) {
					$("#msgBoxInfo").html(data.msg);
					$("#msgBox").modal('show');
					$("#msgBoxOKButton").on('click' , function(){
						parent.window.location.reload();
					});
					
				} else {
					$("#msgBoxInfo").html(data.msg);
					$("#msgBox").modal('show');
				}
			},
			error : function(data) {
				$("#msgBoxInfo").html("程序执行出错");
				$("#msgBox").modal('show');
			}
		});
		
	}
</script>

</head>
<body>
	<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal">
			<input type="hidden" id="id" name="id" value="${place.id }" />
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>会议室名
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="placeName" name="placeName" placeholder="请输入会议室名" value="${place.placeName }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>描述
				</label>
				<div class="up-col-sm-7">
					<textarea rows="8" class="up-form-control" id="placeDesc" name="placeDesc">${place.placeDesc }</textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="savePlace()">保存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
</body>
</html>