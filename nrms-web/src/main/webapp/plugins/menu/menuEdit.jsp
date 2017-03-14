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
    <link rel="stylesheet" href="${basePath}/resources/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.exedit.js"></script>
    <script src="${basePath }/resources/js/require.js"></script>
    <script src="${basePath }/resources/js/main.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }/resources/js/common-check.js"></script>
    <style type="text/css">
        html, body {
            height: 100%;
            position: relative;
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


        //---------------------------------------------------



        var setting= {
            edit: {
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            async: {
                enable: true,
                url: "${basePath}/menu/getJson",
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootId: 0
                }
            },
            callback: {
                beforeDrag: beforeDrag,
                beforeDrop: beforeDrop
            }
        };


        function beforeDrag(treeId, treeNodes) {
            for (var i = 0, l = treeNodes.length; i < l; i++) {
                if (treeNodes[i].drag === false) {
                    return false;
                }
            }
            return true;
        }
        function beforeDrop(treeId, treeNodes, targetNode, moveType) {
            return targetNode ? targetNode.drop !== false : true;
        }

        var obj;

        function setCheck() {
            var zTree = $obj,
                isCopy = $("#copy").attr("checked"),
                isMove = $("#move").attr("checked"),
                prev = $("#prev").attr("checked"),
                inner = $("#inner").attr("checked"),
                next = $("#next").attr("checked");
            zTree.setting.edit.drag.isCopy = isCopy;
            zTree.setting.edit.drag.isMove = isMove;
            showCode(1, ['setting.edit.drag.isCopy = ' + isCopy, 'setting.edit.drag.isMove = ' + isMove]);

            zTree.setting.edit.drag.prev = prev;
            zTree.setting.edit.drag.inner = inner;
            zTree.setting.edit.drag.next = next;
            showCode(2, ['setting.edit.drag.prev = ' + prev, 'setting.edit.drag.inner = ' + inner, 'setting.edit.drag.next = ' + next]);
        }
        function showCode(id, str) {
            var code = $("#code" + id);
            code.empty();
            for (var i=0, l=str.length; i<l; i++) {
                code.append("<li>"+str[i]+"</li>");
            }
        }

        $(document).ready(function(){
            $obj = $.fn.zTree.init($("#treeDemo"), setting);

//            setCheck();
//            $("#copy").bind("change", setCheck);
//            $("#move").bind("change", setCheck);
//            $("#prev").bind("change", setCheck);
//            $("#inner").bind("change", setCheck);
//            $("#next").bind("change", setCheck);

        });

        //-----------------------------------------------------

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
            var isLeaf = $("#isLeaf").val();
            var pid = $("#pid").val();
            var menuIndex = $("#menuIndex").val();

            var nodes = $obj.getNodes();
            var array=$obj.transformToArray(nodes);
            var ids = [];
            for (var i = 0; i < array.length; i++) {
                ids[i] = array[i].id;
            }

            $.ajax({
                url: '${basePath}/menu/menuedit',
                type: "post",
                data: {
                    "id": id,
                    "menuName": menuName,
                    "isLeaf": isLeaf,
                    "pid": pid,
                    "menuUrl": menuUrl,
                    "menuIndex": menuIndex,
                    "ids":ids.toString()
                },
                dataType: "json",
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
                        $("#msgBoxOKButton").on('click', function () {
                            parent.window.location.reload();
                        });
                    }
                },
                error: function (data) {
                    $("#msgBoxInfo").html("程序运行出错");
                    $("#msgBox").modal('show');
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
            <input type="hidden" class="up-form-control" id="menuIndex" name="menuIndex" placeholder=""
                   value="${menu.menuIndex}">
        </div>
    </div>
    <div class="menulist" style="margin-left: 200px;">
        <ul id="treeDemo" class="ztree"></ul>
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