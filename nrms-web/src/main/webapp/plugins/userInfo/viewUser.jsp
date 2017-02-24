<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<html>
<head>
<%@include file="/common/common-ui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="up-modal-body">
		<form id="dataForm" class="up-form-horizontal">
			<c:if test="${sessionScope.LOGIN_USER.userRole ne '3' }">
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>帐号:
				</label>
				<div class="up-col-sm-7">${user.loginName }</div>
			</div>
			</c:if>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>姓名:
				</label>
				<div class="up-col-sm-7">${user.userName }</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>角色:
				</label>
				<div class="up-col-sm-4">
					<c:if test="${user.userRole eq '3' }">员工</c:if>
					<c:if test="${user.userRole eq '2' }">中心经理</c:if>
					<c:if test="${user.userRole eq '1' }">管理员</c:if>
				</div>
			</div>
			<c:if test="${sessionScope.LOGIN_USER.userRole ne '3' }">
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>IP:
				</label>
				<div class="up-col-sm-4">${user.fullIp }</div>
			</div>
			</c:if>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>位置:
				</label>
				<div class="up-col-sm-4">
					<i:getValue dataType="site" dataValue="${user.site}"></i:getValue>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<span class="up-cq-red-star"></span>邮箱:
				</label>
				<div class="up-col-sm-7">${user.email }</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<spanclass="up-cq-red-star"></span>工位号:
				</label>
				<div class="up-col-sm-7">${user.seatNum }</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label up-control-label2">
					<spanclass="up-cq-red-star"></span>工号:
				</label>
				<div class="up-col-sm-7">${user.userCode }</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">关闭</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
</body>
</html>