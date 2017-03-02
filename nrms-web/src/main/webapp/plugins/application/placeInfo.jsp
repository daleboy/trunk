<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<head>
<script type="text/javascript">
	function checkInfo(){
		if( !checkBlank($("#startDate").val()) ){
				$("#msgBoxInfo").html("请选择时间");
				$("#msgBox").modal('show');
				return true;
			}
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
</head>

<body>
	<!--    头部 和  菜单 start -->
	<%@include file="/common/headAndLeft.jsp"%>
	<!--    头部 和  菜单 end -->
	<div id="wrap" class="">
	
		<!-- 内容start -->
		<main class="main up-container-fluid">
		<div class="up-tab-content">
			<div class="up-tab-pane up-active" id="tabs1">
				<div class="border_btm first_title">
					<h4 class="up-top-cq-color">
						<span class="icon-right-dir"></span>会议室使用情况
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
														<form class="up-form-inline" id="searchForm" method="post" action="${basePath }/application/placeInfo">
															<input type="hidden" id="pageNum" name="pageNum" value="1">
															<div class="up-form-group">
																<label for="" class="up-control-label">日期:</label> 
																<input type="date" class="up-form-control" id="startDate" name="startDate" value="${searchParam.startDate }">
															</div>
															<div class="up-form-group">
																<button type="submit"  class="up-btn up-btn-primary">搜索</button> &nbsp;&nbsp;
																<a class="up-pull-right up-btn up-btn-primary up-btn-sm" href="${basePath }/application/applicationList">返回</a>
															</div>
														</form>
													</div>
												</div>
												<table
													class="up-table up-table-bordered up-table-hover margin_bottom10 up-text-center">
													<thead>
														<tr class="up-active">
															<th>会议室名</th>
															<th>使用情况</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="application" items="${page.dataList }" varStatus="status">
															<tr>
																<td>${application.placeName }</td>
																<td>
																	<c:if test="${application.appState == 2 }">
																		${application.startDate}&nbsp;&nbsp;&nbsp;${application.startTime}-${application.endTime}
																	</c:if>
																</td>
																<td>
																	<a href="javascript:void(0)" onClick="showDialog('申请' , '${basePath}/application/addOrEditApplication' , '470px')">申请</a>
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
