<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${basePath}/resources/css/ztree/zTreeStyle.css">
<script type="text/javascript" src="${basePath}/resources/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/fileupload/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/fileupload/ajaxfileupload.js"></script>

<script type="text/javascript">
	function resetEndTimePoints(){
		var sTime = new Date("2017/2/16 "+$("#startTime").val());
		var obj;
		$("#endTime option").each(function(){
			var eTime = new Date("2017/2/16 "+$(this).val());
			if(eTime <= sTime){
				$(this).attr("hidden","true");		//比开始时间小，则隐藏
			}else{
				 if(typeof($(this).attr("hidden"))=="undefined"){
					 return;
				 }
				$(this).removeAttr("hidden");		//比开始时间大，则显示
			}
			$(this).attr("selected","selected");
		});
	} 
</script>
</head>
<body>
	<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal" >
			<input type="hidden" id="id" name="id" value="${app.id }" />
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>会议室
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="placeName" name="placeName" value="${app.placeName }" readonly="readonly">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议主题
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="subject" name="subject"  value="${app.subject }" readonly="readonly">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>申请人
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control"  id="applicant" name="applicant" value="${app.unameApplicant }" readonly="readonly"> 
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>申请日期
				</label>
				<div class="up-col-sm-3">
					<input type="date" class="up-form-control" id="startDate" name="startDate" value="${app.startDate }" readonly="readonly">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>开始时间段：
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="startTime" name="startTime" value="${app.startTime }" readonly="readonly">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>结束时间段：
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="endTime" name="endTime" value="${app.endTime }" readonly="readonly">
				</div>
			</div>
			<hr>
			<label style="position:relative;top:-10px; margin-left: 350px;">已添加人</label>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>参会人
				</label>
				<div class="up-col-sm-3" style="margin-left: 100px;">
					<select  id="all_users" name="all_users" class="up-form-control" multiple="multiple" style="width:260px;height: 200px;">
						<c:forEach var="u" items="${partakes }" >
							<option value="${u.id}">${u.uname }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>会议纪要人
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="endTime" name="endTime" value="${app.unameMinutes }" readonly="readonly">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议附件
				</label>
				<div class="up-col-sm-3">
					<input type="text" name="app_file" style="position: relative;"  class="up-form-control" id="app_file" value="${app.appFileName }" readonly="readonly">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>备注
				</label>
				<div class="up-col-sm-6">
					<textarea style="resize:none;" rows="8" class="up-form-control" id="remark" name="remark" readonly="readonly">${app.appRemark}</textarea>
				</div>
			</div>
			<c:if test="${app.appState == 3 }">
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>反馈信息
				</label>
				<div class="up-col-sm-6">
					<textarea style="resize:none;" rows="8" class="up-form-control" id="auditingFeedBack" name="auditingFeedBack" readonly="readonly">${app.auditingFeedBack}</textarea>
				</div>
			</div>
			</c:if>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
		<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
<!-- 		<button id="addBtn" style="position: relative;top:-488px;left:428px;font-size: 12pt;" onclick="addPartakes()" class="up-btn up-btn-primary" >&gt;&gt;</button> -->
<!-- 		<button id="removeBtn" style="position: relative;top:-428px;left:375px;font-size: 12pt;" onclick="removePartakes()" class="up-btn up-btn-primary" >&lt;&lt;</button> -->
		
</body>
</html>