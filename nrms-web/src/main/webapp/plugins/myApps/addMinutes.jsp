<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${basePath}/resources/css/ztree/zTreeStyle.css">
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
function fileUpload(aid,type,ei){
	$.ajaxFileUpload({
		url : '${basePath}/file/uploadFile?fileType='+type+'&aid='+aid,
		type : "post",
		secureuri : false,
		fileElementId : ei,
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

function saveOrSubmitJiyao(aid,opType){
	if(!checkLengthBetween($("#meetingMinutes").val() , 1, 1000)){
		$("#msgBoxInfo").html("会议纪要长度长度要求：1-1000个字");
		$("#msgBox").modal('show');
		return;
	}
	$.ajax({
		url : '${basePath}/view/saveOrSubmitMinutes',
		dataType : 'json' ,
		type : "post",
		data : {
			"aid":aid,
			"opType":opType,
			"meetingMinutes":$("#meetingMinutes").val(),
		},
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
		<form id="dataForm" class="up-form-horizontal" >
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议室
				</label>
				<div class="up-col-sm-3">
					<input type="hidden" id="pid" name="pid" value="${app.placeId }">
					<input type="text" class="up-form-control" id="pname" name="pname" value="${app.placeName }" readonly="readonly">
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
					<input type="text" class="up-form-control" id="uname_shenqing" name="uname_shenqing" readOnly  value="${app.unameApplicant }">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>申请日期
				</label>
				<div class="up-col-sm-3">
					<input type="date" readOnly class="up-form-control" id="strDate" name="strDate" value="${app.startDate }">
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
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议纪要人
				</label>
				<div class="up-col-sm-3">
					<input type="text" readOnly value="${app.unameMinutes }" class="up-form-control" id="uid_jiyao" name="unameMinutes">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议附件
				</label>
				<div class="up-col-sm-3">
				</div>
				<div class="up-col-sm-2">
					<input type="button" class="up-btn up-btn-primary" onclick="fileDownload('${app.id}',1)" value="下载会议附件">
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
					<input type="button" class="up-btn up-btn-primary" onclick="fileDownload('${app.id}',2)" value="下载纪要附件">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>备注
				</label>
				<div class="up-col-sm-6">
					<textarea readOnly style="resize:none;" rows="8" class="up-form-control" id="remark" name="remark">${app.appRemark }</textarea>
				</div>
			</div>
			<hr>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>录入纪要
				</label>
				<div class="up-col-sm-10">
					<textarea style="resize:none;" rows="8" class="up-form-control"  name="meetingMinutes" id="meetingMinutes">${app.meetingMinutes }</textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="saveOrSubmitJiyao('${app.id}',2)">完成纪要录入</button>
		<button type="button" class="up-btn up-btn-primary" onClick="saveOrSubmitJiyao('${app.id}',1)">暂存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
		
</body>
</html>