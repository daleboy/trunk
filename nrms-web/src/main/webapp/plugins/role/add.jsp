<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp" %>
<link rel="stylesheet" href="../../resources/lib/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../../resources/lib/js/jquery.ztree.all-3.5.min.js"></script>


<html>
<head>
    <%@include file="/common/common-ui.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">

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

            /*
             if( !checkBlank($("#ipSegmentId").val()) ){
             $("#msgBoxInfo").html("请选择IP段");
             $("#msgBox").modal('show');
             return;
             }

             if( !checkBlank($("#ipNum").val()) || !checkNum($("#ipNum").val())){
             $("#msgBoxInfo").html("请输入合法IP");
             $("#msgBox").modal('show');
             return;
             }

             var ipSegment = $("#ipSegmentId").find("option:selected");
             var begin = Number(ipSegment.attr("begin"));
             var end = Number(ipSegment.attr("end"));
             var ip = Number($("#ipNum").val());
             if( ip < begin || ip > end){
             $("#msgBoxInfo").html("IP必须在IP段范围内");
             $("#msgBox").modal('show');
             return;
             }

             if( !checkBlank($("#site").val()) ){
             $("#msgBoxInfo").html("请选择位置");
             $("#msgBox").modal('show');
             return;
             }

             if( !checkEmail($("#email").val()) || !checkLengthBetween($("#email").val() , 1, 50) ){
             $("#msgBoxInfo").html("请输入合法邮箱地址");
             $("#msgBox").modal('show');
             return;
             }

             if(  !checkLengthBetween($("#userCode").val() , 0, 10) ){
             $("#msgBoxInfo").html("工号长度在10以内");
             $("#msgBox").modal('show');
             return;
             }

             if(  !checkLengthBetween($("#seatNum").val() , 0, 10) ){
             $("#msgBoxInfo").html("工位号长度在10以内");
             $("#msgBox").modal('show');
             return;
             }*/
          /*  $.ajax({
                url : '${basePath}/role/add',
                dataType : 'json' ,
                type : "post",
                data : $("#dataForm").serialize() ,
                success : function(data) {
                    if (data.success) {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                        $("#msgBoxOKButton").on('click' , function(){
                            parent.window.location.reload();
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
*/
        $.ajax({

                url: '${basePath}/role/add',
                dataType:"json",
                type:"post",
                data: $("#dataForm").serialize(),
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
                    }
                },
                error: function (data) {
                    alert(data.success);
                    $("#msgBoxInfo").html("程序执行出错");
                    $("#msgBox").modal('show');
                }
            });

        }

    </script>

</head>
<body>



----------------------------------------------
<div class="up-modal-body">

    <div class="content_wrap">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
    </div>

    ----------------------------------------------

    <form id="dataForm" class="up-form-horizontal" style="margin-top: 100px;">
        <input type="hidden" id="id" name="id" value="${role.id }"/>
        <div class="up-form-group">
            <label class="up-col-sm-2 up-control-label">
                <span class="up-cq-red-star">*</span>角色名
            </label>
            <div class="up-col-sm-7">
                <input type="text" class="up-form-control" id="roleName" style="width: 500px;" name="roleName"
                       placeholder=""  value="${role.roleName }">
            </div>
        </div>
        <div class="up-form-group">
            <label  class="up-col-sm-2 up-control-label">
                角色描述
            </label>
            <div class="up-col-sm-7">
                <input type="text" class="up-form-control" id="roleDesc" name="roleDesc" placeholder=""
                       value="${role.roleDesc }">
            </div>
        </div>

















    </form>
</div>














<script type="text/javascript">
    <!--
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

    var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", open:true},
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
    ];

    var code;

    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            py = $("#py").attr("checked")? "p":"",
            sy = $("#sy").attr("checked")? "s":"",
            pn = $("#pn").attr("checked")? "p":"",
            sn = $("#sn").attr("checked")? "s":"",
            type = { "Y":py + sy, "N":pn + sn};
        zTree.setting.check.chkboxType = type;
        showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
    }
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        setCheck();
        $("#py").bind("change", setCheck);
        $("#sy").bind("change", setCheck);
        $("#pn").bind("change", setCheck);
        $("#sn").bind("change", setCheck);
    });
    //-->
</script>
<div class="up-modal-footer up-modal-footer1">
    <button type="button" class="up-btn up-btn-primary" onClick="saveUser()">保存</button>
    <button type="button" class="up-btn up-btn-default" onClick="parent.window.hideDialog()">取消</button>
</div>

<!--    提示框 start -->
<%@include file="/common/msgBox.jsp" %>
<!--    提示框 -->
</body>
</html>