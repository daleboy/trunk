<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp" %>
<head>
    <link rel="stylesheet" href="${basePath}/resources/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript">

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
            {id: 1, pId: 0, name: "会议申请管理",open: true,checked :true},
            {id: 11, pId: 1, name: "会议室列表",open: true,checked :true},
            {id: 111, pId: 11, name: "操作会议室",open: true},
            {id: 12, pId: 1, name: "申请审批",open: true},
            {id: 121, pId: 12, name: "申请详情"},
            {id: 13, pId: 1, name: "会议查询",open: true,checked :true},
            {id: 131, pId: 13, name: "会议详情",checked :true},
            {id: 14, pId: 1, name: "我的申请", open: true,checked :true},
            {id: 141, pId: 14, name: "申请会议详情-编辑",checked :true},
            {id: 142, pId: 14, name: "申请会议室详情-查看",checked :true},
            {id: 143, pId: 14, name: "会议室使用情况",checked :true},
            {id: 2, pId: 0, name: "系统设置",open: true,checked :true},
            {id: 21, pId: 2, name: "修改个人密码",checked :true},
            {id: 22, pId: 2, name: "修改个人信息",checked :true},
            {id: 23, pId: 2, name: "用户列表",open: true},
            {id: 231, pId: 23, name: "编辑用户"},
            {id: 24, pId: 2, name: "角色管理",open: true},
            {id: 241, pId: 24, name: "编辑角色"},
            {id: 25, pId: 2, name: "参数配置",open: true},
            {id: 251, pId: 25, name: "参数详情"}
        ];


        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });


        function save(){
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var  ids=[];
            for (var i=0;i<nodes.length;i++)
            {
                console.log(nodes[i].pid);
                ids[i]=nodes[i].id;
            }
            console.log("ids - > "+ ids+ " len -> "+ids.length);



        }
    </script>
</head>

<body>

<ul id="treeDemo" class="ztree"></ul>

<button onclick="save()"> save </button>

</body>
</html>