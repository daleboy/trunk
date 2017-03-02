<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 自动加载查询下拉框的角色 ，职业，部门，工作    
		1：部门
 		2：工作（web工程师）  
		3:职位(普通员工）*/
	
	/* 修改，删除，重置密码 */

	
	function deleteUser(id){
		$("#msgBoxConfirmInfo").html("确定要删除该用户吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/user/deleteUser',
				data : {
					'id' : id
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
	
	
	function showDialog(title , url , height){
		$("#modalDialogTitle").html(title);
		$("#modalDialogFrame").attr("height" , height);
		$("#modalDialogFrame").attr("src" , url);
		$("#modalDialog").modal('show');
	}
	
	function hideDialog(){
		$("#modalDialog").modal('hide');
	}
	
</script>

<head>
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/userlist.css"/>
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
						<span class="icon-right-dir"></span>用户列表
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
													<!-- 模糊查询用户 -->
														<form class="up-form-inline" id="searchForm" method="post" action="${basePath }/user/userList">
															<input type="hidden" id="pageNum" name="pageNum" value="1">
															<div class="up-form-group">
																<label for="" class="up-control-label">部门:</label> 
																<select name="deptKey" id="dicValueP" class="up-form-control" style="width: 171px">
																		<option value="">请选择</option>
																	<c:forEach var="departlist" items="${departlist }">
																		<option value="${departlist.dicKey }">${departlist.dicValue }</option>
																	</c:forEach>
																</select>
																<label for="" class="up-control-label">职位:</label> 
																<select name="positionKey" id="dicValueJ" class="up-form-control" style="width: 171px">
																        <option value="">请选择</option>
																	<c:forEach var="positionlist" items="${positionlist }">
																		<option value="${positionlist.dicKey }">${positionlist.dicValue }</option>
																	</c:forEach>
																</select>
																<label for="" class="up-control-label">角色:</label> 
																<select name="roleId" id="role" class="up-form-control" style="width: 171px">
																		<option value="">请选择</option>
																	<c:forEach var="rolelist" items="${rolelist }">
																		<option value="${rolelist.id }">${rolelist.roleName }</option>
																	</c:forEach>
																</select>
																<label for="" class="up-control-label">姓名:</label> 
																<input type="text" class="up-form-control" id="placeName" name="uname" value="">
															</div>
															<div class="up-form-group">
																<button type="submit"   class="up-btn up-btn-primary">搜索</button>
															</div>
															<div class="up-form-group">
															<!-- 新增用户 -->
															<!-- <button type="button" id="addUser" onClick="handleUser(4,'4')" class="up-btn up-btn-primary up-btn-primary-red" data-toggle="modal" >新增</button> -->
															</div>
														</form>
													<!-- 模糊查询用户 -->	
													</div>
												</div>
												<%-- <c:if test="${sessionScope.LOGIN_USER.userRole eq '1' }"> --%>
												<div class="up-clearfix table_head">
													<div class="reference_search">
														 <div class="up-form-group">
															<button type="submit" class="up-btn up-btn-primary up-btn-primary-red" data-toggle="modal"
																 onClick="showDialog('新增用户' , '${basePath}/user/toAddOrEditUsers?id=${user.id }&oper=4' , '470px')">新增</button>
														</div> 
													</div>
												</div>
												<%-- </c:if> --%>
												<table
													class="up-table up-table-bordered up-table-hover margin_bottom10 up-text-center">
													<thead>
														<tr class="up-active">
															<th>编号</th>
															<th>登录名</th>
															<th>姓名</th>
															<th>部门</th>
															<th>职位</th>
															<th>工作</th>
															<th>角色</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														 <c:forEach var="user" items="${page.dataList }" varStatus="status">
															<tr>
																<td>${status.index + 1}</td>
																<td>${user.loginName}</td>
																<td>${user.uname}</td>
																<td>${user.dept}</td>
																<td>${user.posi}</td>
																<td>${user.job}</td>
																<td>${user.role}</td>
																<td style="width: 408px;">
																<!-- 修改，删除，重置密码 -->
																<a href="javascript:void(0)" onClick="showDialog('修改' , '${basePath}/user/toAddOrEditUsers?id=${user.id }&oper=1' , '470px')">修改</a>
																<a href="javascript:void(0)" onClick="deleteUser('${user.id }')">删除</a>
																<a href="javascript:void(0)" onClick="showDialog('重置密码' , '${basePath}/user/toAddOrEditUsers?id=${user.id }&oper=3' , '470px')">重置密码</a>
														<%-- 			<a href="javascript:void(0)" onClick="showDialog('编辑会议室' , '${basePath}/place/toAddOrEditPlace?id=${user.id }' , '470px')">编辑</a>
																	<a href="javascript:void(0)" onClick="deletePlace('${user.id}')">删除</a> --%>
																</td>
															</tr>
														</c:forEach> 
													</tbody>
												</table>

												<div class="up-clearfix">
													<div class="up-pull-right">
														<%@include file="/common/page.jsp"%>
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
