<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}/resources/js/fileupload/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript">

function fileDownload(aid,type){
	$.ajax({
		url : '${basePath}/file/ensureFileExist',
		dataType : 'json' ,
		type : "post",
		data : {
			"aid":aid,
			"fileType":type
		},
		success : function(data) {
			if (data.success) {
				location.href='${basePath}/file/downloadFile?aid='+aid+'&fileType='+type+"&code="+data.msg;
			} else {
				$("#msgBoxInfo").html(data.msg);
				$("#msgBox").modal('show');
				$("#msgBoxOKButton").on('click' , function(){
				});
			}
		},
		error : function(data) {
			$("#msgBoxInfo").html("程序执行出错");
			$("#msgBox").modal('show');
		}
	});
}

function auditing(){
	
	$.ajax({
		url : '${basePath}/auditing/auditing',
		dataType : 'json' ,
		type : "post",
		data :$("#dataForm").serialize(),
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
				$("#msgBoxOKButton").on('click' , function(){
				});
			}
		},
		error : function(data) {
			$("#msgBoxInfo").html("程序执行出错");
			$("#msgBox").modal('show');
		}
	});
}

function displayFeedback(attr){
	$("#feed_back_div").css("display",attr);
}
</script>
</head>
<body>
	<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal" >
			<input type="hidden" id="id" name="id" value="${app.id }" />
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议室
				</label>
				<div class="up-col-sm-3">
					<input type="hidden" id="placeId" name="placeId" value="${app.placeId }">
					<input type="text" class="up-form-control" id="placeName" name="placeName" value="${app.placeName }" readonly="readonly">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议主题
				</label>
				<div class="up-col-sm-3">
					<input type="text" readonly class="up-form-control" id="subject" name="subject" placeholder="请输入会议主题" value="${app.subject }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>申请人
				</label>
				<div class="up-col-sm-3">
					<input type="hidden" id="uidApplicant" name="uidApplicant" value="${app.uidApplicant }">
					<input type="text" class="up-form-control" id="unameApplicant" name="unameApplicant" readOnly  value="${app.unameApplicant }">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>申请日期
				</label>
				<div class="up-col-sm-3">
					<input type="date" readOnly class="up-form-control" id="startDate" name="startDate" value="${app.startDate }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>开始时间段：
				</label>
				<div class="up-col-sm-3">
					<input type="text" readOnly id="startTime" name="startTime" value="${app.startTime }"  class="up-form-control">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>结束时间段：
				</label>
				<div class="up-col-sm-3">
					<input type="text" readOnly value="${app.endTime }" id="endTime" name="endTime"  class="up-form-control">
				</div>
			</div>
			<hr>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>参会人
				</label>
				<div class="up-col-sm-3">
					<select  id="all_users" class="up-form-control" multiple="multiple" style="width:260px;height: 200px;">
						<c:forEach var="u"  items="${partakes }">
							<option value="${u.id}">${u.uname }</option>
						</c:forEach>
						
					</select>
				</div>
			</div>
			<!-- <div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>参会人
				</label>
				<div class="up-col-sm-3">
					<ul id="tree" class="ztree" ></ul>
				</div>
				
			</div> -->
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议纪要人
				</label>
				<div class="up-col-sm-3">
					<input type="hidden" id="uidMinutes" name="uidMinutes" value="${app.uidMinutes }">
					<input type="text" readOnly value="${app.unameMinutes }" class="up-form-control" id="unameMinutes" name="unameMinutes">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议附件
				</label>
				<div class="up-col-sm-2">
					<input type="button" class="up-btn up-btn-primary"  onclick="fileDownload('${app.id}',1)" value="下载会议附件">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>备注
				</label>
				<div class="up-col-sm-6">
					<textarea readOnly style="resize:none;" rows="8" class="up-form-control" id="appRemark" name="appRemark">${app.appRemark }</textarea>
				</div>
			</div>
			<hr>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>审核意见
				</label>
				<div class="up-col-sm-3">
					<input type="radio" onclick="displayFeedback('none')" style="position: relative;top:5px;width:15px;height:15px;" name="appState" value="2">
					<label style="position: relative;top:3px;font-size: 10pt;">通过</label>
				</div>
				<div class="up-col-sm-3">
					<input type="radio" onclick="displayFeedback('inherit')" style="position: relative;top:5px;width:15px;height:15px;" name="appState" value="3">
					<label style="position: relative;top:3px;font-size: 10pt;" >不通过</label>
				</div>
			</div>
			<div class="up-form-group" id="feed_back_div" style="display:none;">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>反馈信息
				</label>
				<div class="up-col-sm-6">
					<textarea style="resize:none;" rows="8" class="up-form-control" id="auditingFeedback" name=auditingFeedBack>${app.auditingFeedBack }</textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="auditing()">确认</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
		
</body>
</html>