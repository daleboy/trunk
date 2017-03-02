<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="/com.eshore.InitDataTag" prefix="i" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<script>
    var basePath = '${basePath}';
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>网络资源管理系统</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="${basePath }/resources/css/uplan.min.css">
    <link href="${basePath }/resources/css/style.css" rel="stylesheet">

    <script src="${basePath }/resources/js/require.js"></script>
    <script src="${basePath }/resources/js/main.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }/resources/js/common-check.js"></script>
    <style type="text/css">
        html, body {
            height: 100%;
            position: relative;
        }
    </style>

    <link rel="stylesheet" href="${basePath}/resources/css/zTreeStyle.css" type="text/css">
    <script src="${basePath }/resources/js/main.js"></script>

    <%--冲突的js--%>
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.excheck.js"></script>
    <%--冲突的js--%>

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

        //         --------------------初始化ztree-------------

        //dynamic setting
        var setting1 = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "", "N": ""}
            },
            //获取json数据
            async: {
                enable: true,
                url: "${basePath}/menu/getJson",// Ajax 获取数据的 URL 地址
            },
            data: { // 必须使用data
                simpleData: {
                    enable: true,
                    idKey: "id", // id编号命名
                    pIdKey: "pId", // 父id编号命名
                    rootId: 0
                }
            },
            // 回调函数
            callback: {
                onClick: function (event, treeId, treeNode, clickFlag) {
                    if (true) {
                        console.log(" 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);
                    }
                },
                //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                onAsyncSuccess: function (event, treeId, treeNode, msg) {
                      console.log("调用成功！");
                    //var nodes=getCheckedNodes(true));
                    //alert(nodes);
                }
            }
        };


        //statis setting



        var obj;

        $(document).ready(function () {
            obj = $.fn.zTree.init($("#treeDemo"), setting1);
        });
        //      ------------------结束初始化ztree----------------------

        //--------------------保存按钮点击事件-----------------
        function saveUser() {
            if (!checkLengthBetween($("#roleName").val(), 1, 20)) {
                $("#msgBoxInfo").html("角色名长度1-20字");
                $("#msgBox").modal('show');
                return;
            }
            if (!checkBlank($("#roleName").val())) {
                $("#msgBoxInfo").html("请填写角色名");
                $("#msgBox").modal('show');
                return;
            }

            var id = $("#id").val();
            var roleName = $("#roleName").val();
            var roleDesc = $("#roleDesc").val();

            //获取被选中的菜单项id
            var nodes = obj.getCheckedNodes();
            console.log(obj);
            var ids = [];
            for (var i = 0; i < nodes.length; i++) {
                ids[i] = nodes[i].id;
                console.log(i);
            }
            console.log("ids - > " + ids + " len -> " + ids.length);
            $.ajax({
                url: '${basePath}/role/add',
                type: "post",
                data: {
                    "id": id,
                    "roleName": roleName,
                    "roleDesc": roleDesc,
                    "ids": ids.toString()
                },
                success: function (data) {
                    if (data.success) {
                        parent.window.location.href = "${basePath}/role/list";
//                        $("#msgBoxInfo").html(data.msg);
//                        $("#msgBox").modal('show');
                        window.location.reload();
                        $("#msgBoxOKButton").on('click', function () {
                            window.location.reload();
                        });
                    } else {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                    }
                },
                error: function (data) {
                    alert(data.success);
                    $("#msgBoxInfo").html("程序执行出错");
                    $("#msgBox").modal('show');
                }
            });
        }
        //--------------------保存按钮点击事件-----------------
    </script>

</head>
<body>


<form id="dataForm" class="up-form-horizontal" style="margin-top: 10px;">
    <input type="hidden" id="id" name="id" value="${role.id }"/>
    <div class="up-form-group">
        <label class="up-col-sm-2 up-control-label">
            <span class="up-cq-red-star">*</span>角色名
        </label>
        <div class="up-col-sm-7">
            <input type="text" class="up-form-control" id="roleName" style="width: 500px;" name="roleName"
                   placeholder="" value="${role.roleName }">
        </div>
    </div>
    <div class="up-form-group">
        <label class="up-col-sm-2 up-control-label">
            角色描述
        </label>
        <div class="up-col-sm-7">
            <input type="text" class="up-form-control" id="roleDesc" name="roleDesc" placeholder=""
                   value="${role.roleDesc }">

        </div>
    </div>
    <div class="menulist" style="margin-left: 200px;">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</form>
</div>

<div class="up-modal-footer up-modal-footer1">
    <button type="button" class="up-btn up-btn-primary" onClick="saveUser()">保存</button>
    <button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
</div>

<%@include file="/common/msgBox.jsp" %>
</body>
</html>