<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 重置密码 */
	function resetPwd(id){
		//alert("test: id="+id);
		var loginPw = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var newLoginPw = $("#reNewPwd").val();
		if( !checkBlank(loginPw)){
			$("#msgBoxInfo").html("请填写旧密码");
			$("#msgBox").modal('show');
			return;
		}
		if( !checkBlank(newPwd)){
			$("#msgBoxInfo").html("请填写新的密码");
			$("#msgBox").modal('show');
			return;
		}
		if( !checkBlank(newLoginPw)){
			$("#msgBoxInfo").html("请再次填写新的密码");
			$("#msgBox").modal('show');
			return;
		}
			
		if( !checkLengthBetween(newPwd , 6, 20)|| !checkLengthBetween(newLoginPw , 6, 20)){
			$("#msgBoxInfo").html("密码只允许长度为6-20位");
			$("#msgBox").modal('show');
			return;
		}
		
		if( newPwd!= newLoginPw){
			$("#msgBoxInfo").html("两次输入的新密码不同，请检查重新输入！！");
			$("#msgBox").modal('show');
			return;
		}
		
		
		$("#msgBoxConfirmInfo").html("确定要修改密码吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/user/resetUserPwd',
				data : {
					'id' : id,
					'loginPw' : loginPw,
					'newLoginPw' : newLoginPw
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$("#msgBoxInfo").html(data.msg);
						$("#msgBox").modal('show');
						$("#msgBoxOKButton").on('click' , function(){
							window.location.reload();
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
					<span class="up-cq-red-star">*</span>输入旧密码
				</label>
				<div class="up-col-sm-7">
					<input type="password" class="up-form-control" id="oldPwd" name="oldPwd" placeholder="请输入旧密码"  value="">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>输入新密码
				</label>
				<div class="up-col-sm-7">
					<input type="password" class="up-form-control" id="newPwd" name="newPwd" placeholder="请输入新密码" value="">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>重复新密码
				</label>
				<div class="up-col-sm-7">
					<input type="password" class="up-form-control" id="reNewPwd" name="reNewPwd" placeholder="请输入新密码" value="">
				</div>
			</div>
		
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="resetPwd('${id}')">保存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
	
</body>

</html>
