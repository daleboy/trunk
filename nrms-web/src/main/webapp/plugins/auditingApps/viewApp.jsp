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
function fileUpload(aid,type,elementId){
	$.ajaxFileUpload({
		url : '${basePath}/file/uploadFile?fileType='+type+'&aid='+aid,
		type : "post",
		secureuri : false,
		fileElementId : elementId,
		dataType : "json" ,
		success : function(data) {
			if (data.success) {
				$("#msgBoxInfo").html(data.msg);
				$("#msgBox").modal('show');
				$("#msgBoxOKButton").on('click' , function(){
					
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
</script>
</head>
<body>
	<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal" >
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议室
				</label>
				<div class="up-col-sm-3">
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
					<input type="text" readOnly value="${app.unameMinutes }" class="up-form-control" id="unameMinutes" name="unameMinutes">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议附件
				</label>
				<div class="up-col-sm-3">
					<c:if test="${LOGIN_USER.id eq app.uidApplicant }">
						<input type="file" onchange="fileUpload('${app.id }' ,1,'app_file')"  name="file" style="position: relative;"  class="up-form-control" id="app_file">
					</c:if>
				</div>
				<div class="up-col-sm-2">
					<input type="button" class="up-btn up-btn-primary"  onclick="fileDownload('${app.id}',1)" value="下载会议附件">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
				</label>
				<div class="up-col-sm-3">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>纪要附件
				</label>
				<div class="up-col-sm-3">
					<c:if test="${(LOGIN_USER.id eq app.uidMinutes) && (app.appState == 2)}">
						<input type="file" onchange="fileUpload('${app.id }' ,2,'jiyao_file')"  name="file" style="position: relative;"  class="up-form-control" id="jiyao_file">
					</c:if>
				</div>
				<div class="up-col-sm-2">
					<input type="button" class="up-btn up-btn-primary"  onclick="fileDownload('${app.id}',2)" value="下载纪要附件">
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
			<c:if test="${app.appState == 2 || app.appState == 3 }">
				<hr>
				<div class="up-form-group">
					<label for="" class="up-col-sm-2 up-control-label">
						<span class="up-cq-red-star"></span>审批人
					</label>
					<div class="up-col-sm-3">
						<input type="text"  class="up-form-control" value="${app.unameAuditor }" readonly="readonly"/>
					</div>
					<label for="" class="up-col-sm-2 up-control-label">
						<span class="up-cq-red-star"></span>审批时间
					</label>
					<div class="up-col-sm-3">
						<input type="text"  class="up-form-control" value="${app.auditingDate }" readonly/>
					</div>
				</div>
			</c:if>
			<c:if test="${app.appState == 3 }">
				<div class="up-form-group">
					<label for="" class="up-col-sm-2 up-control-label">
						<span class="up-cq-red-star"></span>反馈
					</label>
					<div class="up-col-sm-6">
						<textarea readOnly style="resize:none;" rows="8" class="up-form-control" id="auditingFeedBack" name="auditingFeedBack">${app.auditingFeedBack }</textarea>
					</div>
				</div>
			</c:if>
			<c:if test="${app.appState == 2 }">
				<hr>
				<div class="up-form-group">
					<label for="" class="up-col-sm-2 up-control-label">
						<span class="up-cq-red-star"></span>会议纪要
					</label>
					<div class="up-col-sm-6">
						<textarea readOnly style="resize:none;" rows="8" class="up-form-control" id="meetingMinutes" name="meetingMinutes">${app.meetingMinutes }</textarea>
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
		
</body>
</html>