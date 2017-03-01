<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 重置密码 */
	function resetEmail(id){
		//alert("test: id="+id);
		var newEmail = $("#newEmail").val();
		var newLoginPw = $("#reNewPwd").val();
		if( !checkBlank(newEmail)){
			$("#msgBoxInfo").html("请填写新的邮箱");
			$("#msgBox").modal('show');
			return;
		}
		var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		if(!reg.test(newEmail)){
			$("#msgBoxInfo").html("邮箱格式不正确，请填写正确的邮箱");
			$("#msgBox").modal('show');
			return;
		}
		$("#msgBoxConfirmInfo").html("确定要修改邮箱吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/user/resetUserEmail',
				data : {
					'id' : id,
					'newEmail' : newEmail
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
	
	
	function checkEmail(){
		var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	   var emailValue=document. getElementById_r("email").value;
	   if (!isEmail(emailValue)){
		   // alert("您输入的邮箱有误,请重新核对后再输入!");
		 //   document. getElementById_r("email").focus();
		    return false;
	   }
	   return true;
	}
	  
	  function isEmail(str){
	        var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	        return reg.test(str);
	  }
	
	
</script>

<head>
	<!-- moban -->
	<script type="text/html" id="departmpl">
		{{#key}}
			<option value="{{dicKey }}">{{dicValue}}</option>	
		{{/key}}
	</script>
</head>

<body>
	
	<div id="wrap" class="">
		<!--    头部 和  菜单 start -->
		<%@include file="/common/headAndLeft.jsp"%>
		<!--    头部 和  菜单 end -->
		
		<!-- 内容start -->
		<main class="main up-container-fluid">
		<div class="up-tab-content">
			<div class="up-tab-pane up-active" id="tabs1">
				<div class="border_btm first_title">
					<h4 class="up-top-cq-color">
						<span class="icon-right-dir"></span>修改密码
					</h4>
				</div>
				<div class="up_page_con">
					<div class="ex_tab2" role="tabpanel" data-example-id="togglable-tabs">
						<div id="" class="up-tab-content">
							<div role="tabpanel" class="up-tab-pane up-active" id="mytab11" aria-labelledby="mytab11-tab">
								<div class="up-table-responsive">
									<div class="up-cq-table">
										<div class="up-cq-table-outer">
											<div class="up-cq-table-inner">
												<div class="up-clearfix table_head margin_bottom10">
													<div class="reference_search">
														<form class="up-form-inline" id="searchForm" method="post" action="${basePath }/place/placeList">
															<input type="hidden" id="pageNum" name="pageNum" value="1">
															<div class="up-form-group">
																<label  for="" class="up-control-label">登录名:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.loginName }"><br><br>
																<label  for="" class="up-control-label">姓名:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.uname }"><br><br>
																<label  for="" class="up-control-label">部门:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.dept }"><br><br>
																<label  for="" class="up-control-label">职位:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.posi }"><br><br>
																<label  for="" class="up-control-label">工作:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.job }"><br><br>
																<label  for="" class="up-control-label">角色:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.role }"><br><br>
																<label  for="" class="up-control-label">原始邮箱:</label> 
																<input id="oldPwd" disabled="true" type="text" class="up-form-control" id="placeName" name="placeName" value="${user.email }"><br><br>
																<label  for="" class="up-control-label">新的邮箱:</label> 
																<input id="newEmail" type="text" class="up-form-control" id="placeName" name="placeName" value=""><br><br>
																
															</div>
															<br>
															<div class="up-form-group">
																<button onclick="resetEmail('${id}')" type="button" style="margin-left: 116px" class="up-btn up-btn-primary">确定</button>
																
																<button onclick="resetEmail('${id}')" type="button" style="margin-left: 20px" class="up-btn up-btn-primary">取消</button>
															</div>
															
														</form>
														
													</div>
												</div>
												<%-- <c:if test="${sessionScope.LOGIN_USER.userRole eq '1' }"> --%>
												<div class="up-clearfix table_head">
													<div class="reference_search">
														<%-- <div class="up-form-group">
															<button type="submit" class="up-btn up-btn-primary up-btn-primary-red" data-toggle="modal"
																 onClick="showDialog('新增会议室' , '${basePath}/place/toAddOrEditPlace' , '470px')">新增</button>
														</div> --%>
													</div>
												</div>
												<%-- </c:if> --%>
												

												<div class="up-clearfix">
													<%--分页部分 <div class="up-pull-right">
														<%@include file="/common/page.jsp"%>
													</div> --%>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
		
		<!-- 侧栏提示工具容器 -->
		<div id="tooltip_box"></div>
		<!-- 侧栏提示工具容器 -->

		<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
		
		 <div class="up-modal up-fade" id="modalDialog"  data-drag="true" data-backdrop="static">
          <div class="up-modal-dialog up-modal-lg">
            <div class="up-modal-content">
              <div class="up-modal-header">
                <button type="button" class="up-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="up-modal-title" id="modalDialogTitle">新增角色</h4>
              </div>
              <iframe id="modalDialogFrame"  width="100%" height="420px" frameborder="0"></iframe>
            </div>
          </div>
        </div>

	</div>
	
</body>

</html>
