<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="/com.eshore.InitDataTag" prefix="i"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<script>
    var basePath='${basePath}';
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

    <script type="text/javascript" charset="utf-8" src="${basePath }/resources/js/common-check.js"></script>
    <style type="text/css">
        html, body {
            height: 100%;
            position: relative;
        }
    </style>

    <link rel="stylesheet" href="${basePath}/resources/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.excheck.js"></script>

    <script type="text/javascript">
        //         --------------------初始化ztree-------------
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };




        var zNodes = [
            {id: "1asdwervbtrbrtgcwvwef", pId: "0sabdvldkjchgbeiubjkn", name: "会议申请管理", open: true, checked: true},
            {id: "1azdsfverbrtb", pId: "1asdwervbtrbrtgcwvwef", name: "会议室列表", open: true, checked: true},
            {id: "3sdverhbtbdfv87dyv8s", pId: "1asdwervbtrbrtgcwvwef", name: "申请审批", open: true},
            {id: "4dazvraaserverver", pId: "1asdwervbtrbrtgcwvwef", name: "会议查询", open: true, checked: true},
            {id: "5sdvfbnkkkjbhghcfg", pId: "1asdwervbtrbrtgcwvwef", name: "我的申请", open: true, checked: true},
            {id: "8acvwececwevw", pId: "0sabdvldkjchgbeiubjkn", name: "系统设置", open: true, checked: true},
            {id: "8basdsvverv", pId: "8acvwececwevw", name: "修改个人密码", checked: true},
            {id: "8coiuyuyuityutyitiy", pId: "8acvwececwevw", name: "修改个人信息", checked: true},
            {id: "8dvcerbytnumujhgbfvdcdvgbhgbfvdc", pId: "8acvwececwevw", name: "用户列表", open: true},
            {id: "8fasvsdvscacwevnhnhnth", pId: "8acvwececwevw", name: "角色管理", open: true},
            {id: "92asvdvmlmkmiuuytytyt", pId: "8acvwececwevw", name: "参数配置", open: true}
        ];


        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
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
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var  ids=[];
            for (var i=0;i<nodes.length;i++)
            {
                ids[i]=nodes[i].id;
            }
            console.log("ids - > " + ids+ " len -> "+ids.length);

            $.ajax({
                url: '${basePath}/role/add',
                type: "post",
                data: {
                    "id":id,
                    "roleName":roleName,
                    "roleDesc":roleDesc,
                    "ids":ids.toString()
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
                    alert(data.success);
                    $("#msgBoxInfo").html("程序执行出错");
//                    $("#msgBox").modal('show');
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