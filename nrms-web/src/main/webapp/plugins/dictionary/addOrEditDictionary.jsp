<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/common-ui.jsp"%>
<html>

<script type="text/javascript">
	/* 新增用户 */
	function saveDictionary(id){
		//alert("dic的id："+id);
		if(checkBlank(id)){
			//alert("这里是修改dictionary")
			resetDicValue(id);
			return;
		}
		debugger;
		var dicType = $("#dicType").val();
		if(dicType==0){
			$("#msgBoxInfo").html("请选择类型");
			$("#msgBox").modal('show');
			return;
		}
		var dicKey = $("#dicKey").val();
		if(!checkBlank(dicKey)){
			$("#msgBoxInfo").html("请填写编码");
			$("#msgBox").modal('show');
			return;
		}
		var dicValue = $("#dicValue").val();
		if(!checkBlank(dicValue)){
			$("#msgBoxInfo").html("请填写名称");
			$("#msgBox").modal('show');
			return;
		}
		var dicDesc = $("#dicDesc").val();
		if(!checkBlank(dicDesc)){
			$("#msgBoxInfo").html("请填写名称描述信息");
			$("#msgBox").modal('show');
			return;
		}
		$("#msgBoxConfirmInfo").html("确定要新增类型吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/dictionary/addDictionary',
				data : {
					'dicType' : dicType,
					'dicKey' : dicKey,
					'dicValue' : dicValue,
					'dicDesc' : dicDesc
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
	
	function resetDicValue(id){
		//alert("修改dicvalue:test: id="+id);
		var dicValue = $("#dicValue").val();
		if(!checkBlank(dicValue)){
			$("#msgBoxInfo").html("请填写修改名称");
			$("#msgBox").modal('show');
			return;
		}
		var dicDesc = $("#dicDesc").val();
		if(!checkBlank(dicDesc)){
			$("#msgBoxInfo").html("请填写修改描述信息");
			$("#msgBox").modal('show');
			return;
		}
		$("#msgBoxConfirmInfo").html("确定要修改类型吗?");
		$("#msgBoxConfirm").modal('show');
		$("#msgBoxConfirmButton").on('click' , function(){
			$("#msgBoxConfirm").modal('hide');
			$.ajax({
				type : 'POST',
				url : '${basePath}/dictionary/updateDictonary',
				data : {
					'dicValue' : dicValue,
					'id' : id,
					'dicDesc' : dicDesc
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
					<span class="up-cq-red-star">*</span>类型
				</label>
				<div class="up-col-sm-4">
				 		<c:if test="${not empty dictionary.id }">
				 			<select name="dicType1" id=dicType class="up-form-control" <c:if test="${not empty dictionary.id }">disabled="disabled"</c:if> style="width:260px">
				 				<option value="${dictionary.dicType }" >${dictionary.dictype }</option>
				 			</select>
				 		</c:if>
				 		<c:if test="${empty dictionary.id }">
							<select name="dicType1" id=dicType class="up-form-control" style="width:260px">
								<option value="0">请选择</option>
								<option <c:if test="${dit ==1 }">selected="selected"</c:if> value="1">部门</option>
								<option <c:if test="${dit ==2 }">selected="selected"</c:if>  value="2">工作</option>
								<option <c:if test="${dit ==3 }">selected="selected"</c:if> value="3">职位</option>
							</select>
						</c:if>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>编码
				</label>
				<div class="up-col-sm-7">
					<c:if test="${ not empty  dictionary.id }">
						<input type="text" class="up-form-control" id="dicKey" name="dicKey" placeholder="请输入编码" <c:if test="${not empty dictionary.id }">readOnly="true"</c:if> value="${dictionary.dicKey }">
					</c:if>
					<c:if test="${  empty  dictionary.id }">
						<input type="text" class="up-form-control" id="dicKey" name="dicKey" placeholder="请输入编码"  value="">
					</c:if>
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>名称
				</label>
				<div class="up-col-sm-7">
					<input type="text" class="up-form-control" id="dicValue" name="dicValue" placeholder="请输入名称" <c:if test="${not empty  dictionary.id }"></c:if>  value="${dictionary.dicValue }">
				</div>
			</div>
			<div class="up-form-group">
				<label for="" class="up-col-sm-2 up-control-label">
					<span class="up-cq-red-star">*</span>描述
				</label>
				<div class="up-col-sm-7">
					<textarea rows="8" class="up-form-control" id="dicDesc" name="dicDesc">${dictionary.dicDesc }</textarea>
				</div>
			</div>
		</form>
	</div>
	<div class="up-modal-footer up-modal-footer1">
		<button type="button" class="up-btn up-btn-primary" onClick="saveDictionary('${dictionary.id }')">保存</button>
		<button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">返回</button>
	</div>
	
	<!--    提示框 start -->
		<%@include file="/common/msgBox.jsp"%>
		<!--    提示框 -->
	
</body>

</html>
