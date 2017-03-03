<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">

	function showDialog(title , url , height){
		$("#modalDialogTitle").html(title);
		$("#modalDialogFrame").attr("height" , height);
		$("#modalDialogFrame").attr("src" , url);
		$("#modalDialog").modal('show');
	}
	
	function hideDialog(){
		$("#modalDialog").modal('hide');
	}
	
	function deleteApplication(id){
		$("#msgBoxConfirmInfo").html("确定要删除该申请吗");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/application/deleteApplication',
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
	
	function cancelApplication(id){
		$("#msgBoxConfirmInfo").html("确定要取消该申请吗");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/application/cancelApplication',
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
</script>

<head>
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
						<span class="icon-right-dir"></span>我的申请
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
														<form class="up-form-inline" id="searchForm" method="post" action="${basePath }/application/applicationList">
															<input type="hidden" id="pageNum" name="pageNum" value="1">
															<div class="up-form-group">
																<label for="" class="up-control-label">会议室名:</label> 
																<select class="up-form-control" name="placeId">
																	<option value=""></option>
																	<c:forEach var="p" items="${places }">
																		<option value="${p.id }" <c:if test="${p.id eq searchParam.placeId }">selected</c:if> >${p.placeName}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="up-form-group">
																<label for="" class="up-control-label">主题:</label> 
																<input type="text" class="up-form-control" id="subject" name="subject" value="${searchParam.subject }">
															</div>
															<div class="up-form-group">
																<label for="" class="up-control-label">状态:</label> 
																<select class="up-form-control" name="appState">
																	<option value=""></option>
																	<option value="0" <c:if test="${ searchParam.appState == 0 }">selected</c:if> >暂存</option>
																	<option value="1" <c:if test="${ searchParam.appState == 1 }">selected</c:if> >待审核</option>
																	<option value="2" <c:if test="${ searchParam.appState == 2 }">selected</c:if> >审核通过</option>
																	<option value="3" <c:if test="${ searchParam.appState == 3 }">selected</c:if> >审核未通过</option>
																</select>
															</div>
															<div class="up-form-group">
																<button type="submit"  class="up-btn up-btn-primary">查询</button>
															</div>
															<div class="up-form-group">
																<a class="up-btn up-btn-primary up-btn-primary-red"  href="${basePath}/application/addOrEditApplication" >新建</a>
															</div>
														</form>
													</div>
												</div>
												<table
													class="up-table up-table-bordered up-table-hover margin_bottom10 up-text-center">
													<thead>
														<tr class="up-active">
															<th>编号</th>
															<th>会议室名</th>
															<th>主题</th>
															<th>状态</th>
															<th>会议日期</th>
															<th>会议时段</th>
															<th>审核时间</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="application" items="${page.dataList }" varStatus="status">
															<tr>
																<td>${status.index + 1}</td>
																<td>${application.placeName }</td>
																<td>${application.subject}</td>
																<td>
																	<c:if test="${application.appState == 0 }">暂存</c:if>
																	<c:if test="${application.appState == 1 }">待审核</c:if>
																	<c:if test="${application.appState == 2 }">申请通过</c:if>
																	<c:if test="${application.appState == 3 }">申请未通过</c:if>
																</td>
																<td>${application.startDate}</td>
																<td>${application.startTime} -- ${application.endTime }</td>
																<td>${application.auditingDate}</td>
																<td>
																	<c:if test="${application.appState == 0 || application.appState == 3}">
																		<a href="javascript:void(0)" onClick="showDialog('编辑申请' , '${basePath}/application/addOrEditApplication?id=${application.id }' , '470px')">编辑</a>
																		<a href="javascript:void(0)" onClick="deleteApplication('${application.id}')">删除</a>
																	</c:if>
																	<c:if test="${application.appState == 1 || application.appState == 2}">
																		<a href="javascript:void(0)" onClick="showDialog('查看申请' , '${basePath}/application/applicationInfo?id=${application.id }' , '470px')">查看</a>
																		<a href="javascript:void(0)" onClick="cancelApplication('${application.id}')">取消申请</a>
																	</c:if>
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
                <h4 class="up-modal-title" id="modalDialogTitle">新增申请</h4>
              </div>
              <iframe id="modalDialogFrame"  width="100%" height="420px" frameborder="0"></iframe>
            </div>
          </div>
        </div>
	</div>
</body>

</html>
