<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function saveUser(){
		 if( !checkCharAndNum( $("#loginName").val() ) || !checkLengthBetween($("#loginName").val() , 4, 20) ){
			$("#msgBoxInfo").html("帐号只允许数字字母,长度4-20位");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkLengthBetween($("#userName").val() , 1, 20) ){
			$("#msgBoxInfo").html("姓名只允许数字字母,长度1-20位");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkBlank($("#userRole").val()) ){
			$("#msgBoxInfo").html("请选择角色");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkBlank($("#ipSegmentId").val()) ){
			$("#msgBoxInfo").html("请选择IP段");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkBlank($("#ipNum").val()) || !checkNum($("#ipNum").val())){
			$("#msgBoxInfo").html("请输入合法IP");
			$("#msgBox").modal('show');
			return;
		} 
		
		var ipSegment = $("#ipSegmentId").find("option:selected");
		var begin = Number(ipSegment.attr("begin"));
		var end = Number(ipSegment.attr("end"));
		var ip = Number($("#ipNum").val());
		if( ip < begin || ip > end){
			$("#msgBoxInfo").html("IP必须在IP段范围内");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkBlank($("#site").val()) ){
			$("#msgBoxInfo").html("请选择位置");
			$("#msgBox").modal('show');
			return;
		}
		
		if( !checkEmail($("#email").val()) || !checkLengthBetween($("#email").val() , 1, 50) ){
			$("#msgBoxInfo").html("请输入合法邮箱地址");
			$("#msgBox").modal('show');
			return;
		}
		
		if(  !checkLengthBetween($("#userCode").val() , 0, 10) ){
			$("#msgBoxInfo").html("工号长度在10以内");
			$("#msgBox").modal('show');
			return;
		}
		
		if(  !checkLengthBetween($("#seatNum").val() , 0, 10) ){
			$("#msgBoxInfo").html("工位号长度在10以内");
			$("#msgBox").modal('show');
			return;
		}
		
		$.ajax({
			url : '${basePath}/userInfo/saveOrUpdateUser',
			dataType : 'json' ,
			data : $("#dataForm").serialize(),
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
			<input type="hidden" id="id" name="id" value="${user.id }" />
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>帐号
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="loginName" name="loginName" placeholder="请输入帐号" <c:if test="${not empty  user }">readOnly="true"</c:if> value="${user.loginName }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>姓名
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="userName" name="userName" placeholder="请输入姓名" <c:if test="${not empty  user }">readOnly="true"</c:if>  value="${user.userName }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>角色
				</label>
				<div class="up-col-sm-4">
					<select name="userRole" id="userRole" class="up-form-control" style="width:260px">
						<option value="">请选择</option>
						<option value="3" <c:if test="${user.userRole eq '3' }">selected</c:if> >员工</option>
						<option value="2" <c:if test="${user.userRole eq '2' }">selected</c:if> >中心经理</option>
						<option value="1" <c:if test="${user.userRole eq '1' }">selected</c:if> >管理员</option>
					</select>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>IP
				</label>
				<div class="up-col-sm-7">
					<div class="up-col-sm-7">
						<select name="ipSegmentId" id="ipSegmentId" class="up-form-control"  style="width:260px">
							<c:forEach var="userIp" items="${ipList }">
								<option value="${userIp.id}" begin="${userIp.ipBegin }" end="${userIp.ipEnd }" <c:if test="${user.ipSegmentId eq userIp.id }">selected</c:if>>${userIp.paramName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="up-col-sm-2" style="padding-left:0">     
                            <input type="text" id="ipNum" name="ipNum" class="up-form-control"  placeholder="请输入IP" value="${user.ipNum }"/>
                    </div>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>位置
				</label>
				<div class="up-col-sm-4">
					<select name="site" id="site" class="up-form-control"  style="width:260px">
						<c:forEach var="site"  items="${siteList }">
							<option value="${site.paramValue }" <c:if test="${user.site eq site.paramValue }">selected</c:if>>${site.paramName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>邮箱
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="email" name="email" placeholder="请输入邮箱" value="${user.email }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<spanclass="up-cq-red-star"></span>工位号
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="seatNum" name="seatNum" placeholder="请输入工位号" value="${user.seatNum }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<spanclass="up-cq-red-star"></span>工号
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="userCode" name="userCode" placeholder="请输入工号" value="${user.userCode }">
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="saveUser()">保存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
</body>
</html>