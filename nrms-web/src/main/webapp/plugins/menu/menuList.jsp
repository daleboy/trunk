<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp" %>
<%@include file="/common/common-ui.jsp" %>
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

    function deleteUser(id) {

        $("#msgBoxConfirmInfo").html("确定要删除该角色吗");
        $("#msgBoxConfirm").modal('show');
        $("#msgBoxConfirmButton").on('click', function () {
            $("#msgBoxConfirm").modal('hide');
            $.ajax({
                type: 'POST',
                url: '${basePath}/role/delete',
                data: {
                    'id': id
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                        $("#msgBoxOKButton").on('click', function () {
                            window.location.reload();
                        });
                    } else {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                    }
                },
                error: function (data) {
                    $("#msgBoxInfo").html("程序执行出错");
                    $("#msgBox").modal('show');
                }
            });
        });

    }

    function resetUserPwd(userId) {

        $("#msgBoxConfirm").modal('show');
        $("#msgBoxConfirmButton").on('click', function () {
            $("#msgBoxConfirm").modal('hide');
            $.ajax({
                type: 'POST',
                url: '${basePath}/role/resetpwd',
                data: {
                    'id': id
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                        $("#msgBoxOKButton").on('click', function () {
                            window.location.reload();
                        });
                    } else {
                        $("#msgBoxInfo").html(data.msg);
                        $("#msgBox").modal('show');
                    }
                },
                error: function (data) {
                    $("#msgBoxInfo").html("程序执行出错");
                    $("#msgBox").modal('show');
                }
            });
        });

    }

    function showDialog(title, url, height) {
        $("#modalDialogTitle").html(title);
        $("#modalDialogFrame").attr("height", height);
        $("#modalDialogFrame").attr("src", url);
        $("#modalDialog").modal('show');
    }

    function hideDialog() {
        $("#modalDialog").modal('hide');
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
                        <span class="icon-right-dir"></span> 菜单管理
                    </h4>
                </div>
                <div class="up_page_con">
                    <div class="ex_tab2" role="tabpanel" data-example-id="togglable-tabs">
                        <div id="" class="up-tab-content">
                            <div role="tabpanel" class="up-tab-pane up-active" id="mytab11"
                                 aria-labelledby="mytab11-tab">
                                <div class="up-table-responsive">
                                    <div class="up-cq-table">
                                        <div class="up-cq-table-outer">
                                            <div class="up-cq-table-inner">
                                                <div class="up-clearfix table_head margin_bottom10">
                                                    <div class="reference_search">

                                                        <form class="up-form-inline" id="searchForm"
                                                              action="${basePath}/menu/menulist">

                                                            <div class="up-form-group">
                                                                <label for="" class="up-control-label">菜单名:</label>
                                                                <input type="text" class="up-form-control" id="key"
                                                                       name="menuName" value="${searchParam.menuName}">
                                                            </div>

                                                            <select name="isLeaf" id="isLeaf" class="up-form-control" style="width: 171px">
                                                                <option value="1" <c:if test="${searchParam.isLeaf == 1 }">selected="selected"</c:if> >子菜单</option>
                                                                <option value="0"  <c:if test="${searchParam.isLeaf == 0 }">selected="selected"</c:if>>父菜单</option>
                                                            </select>



                                                            <div class="up-form-group">
                                                                <button type="submit"  class="up-btn up-btn-primary">查询
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                                <table
                                                        class="up-table up-table-bordered up-table-hover margin_bottom10 up-text-center">
                                                    <thead>
                                                    <tr class="up-active">
                                                        <th>编号</th>
                                                        <th>菜单名</th>
                                                        <th>菜单链接</th>
                                                        <th>菜单顺序</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%
                                                        Integer i=1;
                                                    %>
                                                    <c:forEach var="menu" items="${page.dataList}">
                                                        <tr>
                                                            <td><%out.print(i++); %></td>
                                                            <td>${menu.menuName}</td>
                                                            <td>${menu.menuUrl}</td>
                                                            <td>${menu.menuIndex}</td>
                                                            <td>

                                                                    <a href="javascript:void(0)"
                                                                       onClick="showDialog('编辑' , '${basePath}/menu/toedit?id=${menu.id }' , '470px')">修改</a>

                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>

                                                <div class="up-clearfix">
                                                    <div class="up-pull-right">
                                                        <%@include file="/common/page.jsp" %>
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
    <%@include file="/common/msgBox.jsp" %>
    <!--    提示框 -->

    <div class="up-modal up-fade" id="modalDialog" data-drag="true" data-backdrop="static">
        <div class="up-modal-dialog up-modal-lg">
            <div class="up-modal-content">
                <div class="up-modal-header">
                    <button type="button" class="up-close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="up-modal-title" id="modalDialogTitle">新增角色</h4>
                </div>
                <iframe id="modalDialogFrame" width="100%" height="420px" frameborder="0"></iframe>
            </div>
        </div>
    </div>

</div>
</body>

</html>
