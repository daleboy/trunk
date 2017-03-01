<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 新增用户 */
	function addUser(){
		debugger;
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
		debugger;
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
		//alert(roleId);
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
						<span class="icon-right-dir"></span>新增用户
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
																<label  for="" class="up-control-label">登录账号:</label> 
																<input id="loginName"  type="text" class="up-form-control" id="loginName" name="placeName" value="${user.loginName }"><br><br>
																<label  for="" class="up-control-label">用户姓名:</label> 
																<input id="uname"  type="text" class="up-form-control" name="uname" value=""><br><br>
																<label  style="margin-left: 23px" for="" class="up-control-label">邮 箱:</label> 
																<input id="email"  type="text" class="up-form-control"  name="email" value=""><br><br>
																<label style="margin-left: 23px" for="" class="up-control-label">角 色:</label> 
																<select name="roleId" id="roleId" class="up-form-control" style="width:171px">
																		<option value="">请选择</option>
																	<c:forEach var="rolelist" items="${rolelist }">
																		<option value="${rolelist.id }">${rolelist.roleName }</option>
																	</c:forEach>
																</select><br><br>
																<label style="margin-left: 23px" for="" class="up-control-label">部 门:</label> 
																<select name="deptKey" id="deptKey" class="up-form-control" style="width:171px">
																		<option value="">请选择</option>
																	<c:forEach var="departlist" items="${departlist }">
																		<option value="${departlist.dicKey }">${departlist.dicValue }</option>
																	</c:forEach>
																</select><br><br>
																<label style="margin-left: 23px" for="" class="up-control-label">工 作:</label> 
																<select name="jobKey" id="jobKey" class="up-form-control" style="width:171px">
																        <option value="">请选择</option>
																	<c:forEach var="jobtlist" items="${jobtlist }">
																		<option value="${jobtlist.dicKey }">${jobtlist.dicValue }</option>
																	</c:forEach>
																</select>
																<br><br>
																<label style="margin-left: 23px" for="" class="up-control-label">职 位:</label> 
																<select name="positionKey" id="positionKey" class="up-form-control" style="width:171px">
																        <option value="">请选择</option>
																	<c:forEach var="positionlist" items="${positionlist }">
																		<option value="${positionlist.dicKey }">${positionlist.dicValue }</option>
																	</c:forEach>
																</select><br><br>
															</div>
															<br>
															<div class="up-form-group">
																<button onclick="addUser()" type="button" style="margin-left: 90px" class="up-btn up-btn-primary">确定</button>
																
																<button onclick="addUser()" type="button" style="margin-left: 20px" class="up-btn up-btn-primary">返回</button>
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
