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
	function saveApplication(){
		
		$.ajax({
			url : '${basePath}/app/saveOrUpdateMyApp',
			dataType : 'json' ,
			type : "post",
			data : $("#dataForm").serialize(),
			success : function(data) {
				if (data.success) {
					
					$("#msgBoxInfo").html(data.msg);
					$("#msgBox").modal('show');
					$("#msgBoxOKButton").on('click' , function(){
						parent.window.location.reload();
					});
					
					fileUpload(data.dataList[0])
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
	
	function submitApplication(){
		if(checkInfo())
			return ;
		
		$.ajax({
			url : '${basePath}/app/submitMyApp',
			dataType : 'json' ,
			type : "post",
			data : $("#dataForm").serialize(),
			success : function(data) {
				if (data.success) {
					
					$("#msgBoxInfo").html(data.msg);
					$("#msgBox").modal('show');
					$("#msgBoxOKButton").on('click' , function(){
						parent.window.location.reload();
					});
					
					fileUpload(data.dataList[0])
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
	
	function checkInfo(){
		if( !checkLengthBetween( $("#subject").val(), 0, 50) ){
			$("#msgBoxInfo").html("主题不能超过50字");
			$("#msgBox").modal('show');
			return true;
		}
		if( !checkLengthBetween( $("#project").val() , 0, 100) ){
			$("#msgBoxInfo").html("项目名不能超过100字");
			$("#msgBox").modal('show');
			return true;
		}
		
		if( !checkBlank($("#strDate").val()) ){
			$("#msgBoxInfo").html("请选择时间");
			$("#msgBox").modal('show');
			return true;
		}
		
// 		console.info($("partakes option").size())		//验证参与人不能为空 失败
// 		if($("partakes option").size() == 0){
// 			$("#msgBoxInfo").html("请选择参会人");
// 			$("#msgBox").modal('show');
// 			return;
// 		}
			
		var flag = true;
		$("#partakes option").each(function(){//将partake中的每一项都选中
		  	$(this).attr("selected", "selected");
			flag = false;
		 });
		if(flag){	//验证参与人不能为空
			$("#msgBoxInfo").html("请选择参会人");
			$("#msgBox").modal('show');
			return true;
		}
		return false;
	}
	
	function fileUpload(aid){
		$.ajaxFileUpload({
			url : '${basePath}/app/uploadFile?fileType=1&aid='+aid,
			type : "post",
			secureuri : false,
			fileElementId : "app_file",
			dataType : "json" ,
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
	
	function addPartakes(){
		$("#all_users :selected").each(function(){
		     $("#partakes" ).append("<option value='" + $(this).val() + "'>" + $(this).text() + "</option>");
		     $("#uid_jiyao" ).append("<option value='" + $(this).val() + "'>" + $(this).text() + "</option>");
		     $(this).remove();
		 });
	}
	
	function removePartakes(){
		$("#partakes :selected").each(function(){
		     $("#all_users" ).append("<option value='" + $(this).val() + "'>" + $(this).text() + "</option>");
		     $("#uid_jiyao option[value=" + $(this).val() + "]" ).remove();
		     $(this).remove();
		 });
	}
	
	var setting = {
       		check : {
       			autoCheckTrigger : false,
	        	chkboxType : { "Y" : "ps", "N" : "ps" },
				chkboxStyle : "checkbox",
	        	enable : true,
	        	nocheckInherit : false
       		},
			data : {
				simpleData : {
					enable : true
				}
			}
			
	    };
	var treeNodes = ""//${users};
	var treeObj ;
// 	$(function() {
// 		treeObj = $.fn.zTree.init($("#tree"), setting, treeNodes);
// 		console.info(treeObj)
// 	 });  
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
					<select class="up-form-control" id="pid" name="pid" >
						<c:forEach var="p" items="${places }">
							<option value="${p.id }" <c:if test="${p.id eq app.placeId }">selected</c:if> >${p.pname }</option>
						</c:forEach>
					</select>
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议主题
				</label>
				<div class="up-col-sm-3">
					<input type="text" class="up-form-control" id="subject" name="subject" placeholder="请输入会议主题" value="${app.subject }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>申请人
				</label>
				<div class="up-col-sm-3">
					<input type="hidden" id="uid_shenqing" name="uid_shenqing" value="${LOGIN_USER.id }">
					<input type="text" class="up-form-control" id="uname_shenqing" name="uname_shenqing" readOnly  value="${LOGIN_USER.uname }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>申请日期
				</label>
				<div class="up-col-sm-3">
					<input type="date" class="up-form-control" id="startDate" name="startDate" value="${app.startDate }">
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>开始时间段：
				</label>
				<div class="up-col-sm-3">
					<select id="startTime" name="startTime"  class="up-form-control" onchange="resetEndTimePoints()">
						<c:forEach var="t" items="${time_points }" end="14">
							<option value="${t }" >${t}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>
				</label>
				<div class="up-col-sm-3">
					<div class="up-col-sm-3">
						<span  style="width:260px"></span>
					</div>
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>结束时间段：
				</label>
				<div class="up-col-sm-3">
					<select id="endTime" name="endTime"  class="up-form-control">
						<c:forEach var="t" begin="1" items="${time_points }">
							<option value="${t }">${t}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<hr>
			<label style="position:relative;top:-10px; margin-left: 200px;">所有人</label>
			<label style="position:relative;top:-10px; margin-left: 350px;">已添加人</label>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>参会人
				</label>
				<div class="up-col-sm-3">
					<select  id="all_users" class="up-form-control" multiple="multiple" style="width:260px;height: 200px;">
						<optgroup label="g1">
						<c:forEach var="u"  items="${users }">
							<option value="${u.id}">${u.uname }</option>
						</c:forEach>
						</optgroup>
						<optgroup label="g1">
							<option value="1">name1</option>
							<option value="2">name2</option>
						</optgroup>
					</select>
				</div>
				<div class="up-col-sm-3" style="margin-left: 100px;">
					<select  id="partakes" name="partakes" class="up-form-control" multiple="multiple" style="width:260px;height: 200px;">
						
					</select>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>选择会议纪要人
				</label>
				<div class="up-col-sm-3">
					<select class="up-form-control" id="uid_jiyao" name="uid_jiyao">
					</select>
				</div>
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>会议附件
				</label>
				<div class="up-col-sm-3">
					<input type="file" name="app_file" style="position: relative;"  class="up-form-control" id="app_file">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>备注
				</label>
				<div class="up-col-sm-6">
					<textarea style="resize:none;" rows="8" class="up-form-control" id="remark" name="remark">${app.remark}</textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="submitApplication()">提交</button>
		<button type="button" class="up-btn up-btn-primary" onClick="saveApplication()">暂存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
		<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
		<button id="addBtn" style="position: relative;top:-488px;left:428px;font-size: 12pt;" onclick="addPartakes()" class="up-btn up-btn-primary" >&gt;&gt;</button>
		<button id="removeBtn" style="position: relative;top:-428px;left:375px;font-size: 12pt;" onclick="removePartakes()" class="up-btn up-btn-primary" >&lt;&lt;</button>
		
</body>
</html>