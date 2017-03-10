<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp" %>

<html>
<head>
    <%@include file="/common/common-ui.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css">
        textarea {
            resize: none;
        }
    </style>

    <script type="text/javascript">
        function showDialog(title, url, height) {
            $("#modalDialogTitle").html(title);
            $("#modalDialogFrame").attr("height", height);
            $("#modalDialogFrame").attr("src", url);
            $("#modalDialog").modal('show');
        }

        function hideDialog() {
            $("#modalDialog").modal('hide');
        }


        //--------------------保存按钮点击事件-----------------
        function saveUser() {
            if (!checkLengthBetween($("#menuName").val(), 1, 20)) {
                $("#msgBoxInfo").html("菜单名称长度1-20字");
                $("#msgBox").modal('show');
                return;
            }

            if (!checkBlank($("#menuIndex").val())) {
                $("#msgBoxInfo").html("请填写菜单顺序");
                $("#msgBox").modal('show');
                return;
            }

            var id = $("#id").val();
            var menuName = $("#menuName").val();
            var menuUrl = $("#menuUrl").val();
            var isLeaf= $("#isLeaf").val();
            var pid = $("#pid").val();
            var menuIndex = $("#menuIndex").val();

            $.ajax({
                url: '${basePath}/menu/menuedit',
                type: "post",
                data: {
                    "id": id,
                    "menuName": menuName,
                    "isLeaf": isLeaf,
                    "pid":pid,
                    "menuUrl": menuUrl,
                    "menuIndex": menuIndex
                },
                success: function (data) {
                    if (data.success) {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                        $("#msgBoxOKButton").on('click', function () {
                            parent.window.location.reload();
                        });
                    } else {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                        parent.window.location.reload();
                    }
                },
                error: function (data) {
//                    alert(data.success);
                    $("#msgBoxInfo").html("程序执行出错");
                    parent.window.location.reload();
//                    $("#msgBox").modal('show');
                }
            });

        }

        //--------------------保存按钮点击事件-----------------
    </script>

</head>
<body>
<form id="dataForm" class="up-form-horizontal" style="margin-top: 10px;">
    <input type="hidden" id="id" name="id" value="${menu.id }"/>
    <input type="hidden" id="isLeaf" name="isLeaf" value="${menu.isLeaf }"/>
    <input type="hidden" id="pid" name="pid" value="${menu.pid }"/>
    <input type="hidden" id="menuUrl" name="menuUrl" value="${menu.menuUrl}"/>
    <div class="up-form-group">
        <label class="up-col-sm-2 up-control-label">
            <span class="up-cq-red-star">*</span>菜单名
        </label>
        <div class="up-col-sm-7">
            <input type="text" class="up-form-control" id="menuName" style="width: 500px;" name="menuName"
                   placeholder="" value="${menu.menuName }">
        </div>
    </div>

    <div class="up-form-group">
        <label class="up-col-sm-2 up-control-label">
            <span class="up-cq-red-star">*</span>菜单顺序
        </label>
        <div class="up-col-sm-7">
            <input type="text" class="up-form-control" id="menuIndex" name="menuIndex" placeholder=""   value="${menu.menuIndex}">
        </div>
    </div>


</form>
</div>

<div class="up-modal-footer up-modal-footer1">
    <button type="button" class="up-btn up-btn-primary" onClick="saveUser()">保存更新</button>
    <button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
</div>
<%@include file="/common/msgBox.jsp" %>
</body>
</html>