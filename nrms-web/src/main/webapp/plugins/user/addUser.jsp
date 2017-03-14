<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 新增用户 */
	function saveUser(id){
		if(checkBlank(id)){
			resetUserInfo(id);
			return;
		} 
		var loginName = $("#loginName").val();
		if(!checkBlank(loginName)){
			$("#msgBoxInfo").html("请填写登陆账号");
			$("#msgBox").modal('show');
			return;
		}
		if( !checkCharAndNum( $("#loginName").val() ) || !checkLengthBetween($("#loginName").val() , 4, 15) ){
			$("#msgBoxInfo").html("登陆账号只允许数字字母,长度4-15位");
			$("#msgBox").modal('show');
			return;
		}
		
		var uname = $("#uname").val();
		if(!checkBlank(uname)){
			$("#msgBoxInfo").html("请填写用户姓名");
			$("#msgBox").modal('show');
			return;
		}
		var email = $("#email").val();
		if(checkBlank(email)){
			var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
			if(!reg.test(email)){
				$("#msgBoxInfo").html("邮箱格式不正确，请填写正确的邮箱");
				$("#msgBox").modal('show');
				return;
			}
		}
		var roleId = $("#roleId").find("option:selected").val();
		if(!checkBlank(roleId)){
			$("#msgBoxInfo").html("请选择角色");
			$("#msgBox").modal('show');
			return;
		}
		var deptKey = $("#deptKey option:selected").val();
		if(!checkBlank(deptKey)){
			$("#msgBoxInfo").html("请选择部门");
			$("#msgBox").modal('show');
			return;
		}
		var jobKey = $("#jobKey option:selected").val();
		if(!checkBlank(jobKey)){
			$("#msgBoxInfo").html("请选择工作");
			$("#msgBox").modal('show');
			return;
		}
		var positionKey = $("#positionKey option:selected").val();
		if(!checkBlank(positionKey)){
			$("#msgBoxInfo").html("请选择职位");
			$("#msgBox").modal('show');
			return;
		}
		$("#msgBoxConfirmInfo").html("确定要新增用户吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/user/addUser',
				data : {
					'email' : email,
					'loginName' : loginName,
					'uname' : uname,
					'email' : email,
					'roleId' : roleId,
					'deptKey' : deptKey,
					'jobKey' : jobKey,
					'positionKey' : positionKey
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$("#msgBoxInfo").html(data.msg);
						$("#msgBox").modal('show');
						$("#msgBoxOKButton").on('click' , function(){
							$("#msgBox").modal('hide');
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
		}); 
	}
	
	function resetUserInfo(id){
		var loginName = $("#loginName").val();
		if(!checkBlank(loginName)){
			$("#msgBoxInfo").html("请填写登陆账号");
			$("#msgBox").modal('show');
			return;
		}
		var uname = $("#uname").val();
		if(!checkBlank(uname)){
			$("#msgBoxInfo").html("请填写用户姓名");
			$("#msgBox").modal('show');
			return;
		}
		var email = $("#email").val();
		if(checkBlank(email)){
			var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
			if(!reg.test(email)){
				debugger;
				$("#msgBoxInfo").html("邮箱格式不正确，请填写正确的邮箱");
				$("#msgBox").modal('show');
				return;
			}
		}
		var roleId = $("#roleId").find("option:selected").val();
		if(!checkBlank(roleId)){
			$("#msgBoxInfo").html("请选择角色");
			$("#msgBox").modal('show');
			return;
		}
		var deptKey = $("#deptKey option:selected").val();
		if(!checkBlank(deptKey)){
			$("#msgBoxInfo").html("请选择部门");
			$("#msgBox").modal('show');
			return;
		}
		var jobKey = $("#jobKey option:selected").val();
		if(!checkBlank(jobKey)){
			$("#msgBoxInfo").html("请选择工作");
			$("#msgBox").modal('show');
			return;
		}
		var positionKey = $("#positionKey option:selected").val();
		if(!checkBlank(positionKey)){
			$("#msgBoxInfo").html("请选择职位");
			$("#msgBox").modal('show');
			return;
		}
		$("#msgBoxConfirmInfo").html("确定要修改用户吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/user/resetUserInfo',
				data : {
					'id' : id,
					'email' : email,
					'loginName' : loginName,
					'uname' : uname,
					'email' : email,
					'roleId' : roleId,
					'deptKey' : deptKey,
					'jobKey' : jobKey,
					'positionKey' : positionKey
				},
				dataType : 'json',
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
		});
	}
	
</script>

<head>
</head>

<body>

		<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal">
			<input type="hidden" id="id" name="id" value="${user.id }" />
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>登录账号
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="loginName" name="loginName" placeholder="请输入帐号" <c:if test="${not empty  user }">readOnly="true"</c:if> value="${user.loginName }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>用户姓名
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="uname" name="uname" placeholder="请输入姓名" <c:if test="${not empty  user }">readOnly="true"</c:if>  value="${user.uname }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star"></span>邮箱
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="email" name="email" placeholder="请输入邮箱"   value="${user.email }">
				</div>
			</div>
			<%-- <c:if test="${not empty  user }">readOnly="true"</c:if> 
			<c:if test="${not empty  user }">disabled="disabled"</c:if>
			
			--%>
			<%-- <c:if test="${not empty user }">
				<div class="up-form-group">
					<label for="" class="up-col-sm-2 up-control-label">
						<span class="up-cq-red-star">*</span>新的邮箱
					</label>
					<div class="up-col-sm-7">
						<input type="text" class="up-form-control" id="newEmail" name="newEmail" placeholder="请输入新的邮箱"   value="">
					</div>
				</div>
			</c:if> --%>
			
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>角色
				</label>
				<div class="up-col-sm-4">
					<c:if test="${not empty  user }">
						<select name="roleId" id="roleId" class="up-form-control"  style="width:260px">
							<c:forEach var="rolelist" items="${rolelist }">
								<option value="${rolelist.id }" <c:if test="${rolelist.id==user.roleId }">selected="selected"</c:if> >${rolelist.roleName }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${ empty  user }">
						<select name="roleId" id="roleId" class="up-form-control" style="width:260px">
							<option value="">请选择</option>
							<c:forEach var="rolelist" items="${rolelist }">
								<option value="${rolelist.id }">${rolelist.roleName }</option>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>
			
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>部门
				</label>
				<div class="up-col-sm-4">
					<c:if test="${not empty  user }">
						<select name="deptKey" id="deptKey" class="up-form-control"   style="width:260px">
							<%-- <option value="${user.deptKey }" >${user.dept }</option> --%>
							<c:forEach var="departlist" items="${departlist }">
								<option value="${departlist.dicKey }" <c:if test="${departlist.dicKey==user.deptKey}">selected="selected"</c:if> >${departlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${ empty  user }">
						<select name="deptKey" id="deptKey" class="up-form-control" style="width:260px">
							<option value="">请选择</option>
							<c:forEach var="departlist" items="${departlist }">
								<option value="${departlist.dicKey }">${departlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>
			
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>工作
				</label>
				<div class="up-col-sm-4">
					<c:if test="${not empty  user }">
						<select name="jobKey" id="jobKey" class="up-form-control"   style="width:260px">
							<%-- <option value="${user.jobKey }" >${user.job }</option> --%>
							<c:forEach var="jobtlist" items="${jobtlist }">
								<option value="${jobtlist.dicKey }" <c:if test="${jobtlist.dicKey==user.jobKey}">selected="selected"</c:if> >${jobtlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${ empty  user }">
						<select name="jobKey" id="jobKey" class="up-form-control" style="width:260px">
							<option value="">请选择</option>
							<c:forEach var="jobtlist" items="${jobtlist }">
								<option value="${jobtlist.dicKey }">${jobtlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>
			
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>职位
				</label>
				<div class="up-col-sm-4">
					<c:if test="${not empty  user }">
						<select name="positionKey" id="positionKey" class="up-form-control"   style="width:260px">
							<%-- <option value="${user.positionKey }" >${user.posi }</option> --%>
							<c:forEach var="positionlist" items="${positionlist }">
								<option value="${positionlist.dicKey }" <c:if test="${positionlist.dicKey==user.positionKey}">selected="selected"</c:if> >${positionlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${ empty  user }">
						<select name="positionKey" id="positionKey" class="up-form-control" style="width:260px">
							<option value="">请选择</option>
							<c:forEach var="positionlist" items="${positionlist }">
								<option value="${positionlist.dicKey }">${positionlist.dicValue }</option>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="saveUser('${user.id }')">保存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
	
</body>

</html>
