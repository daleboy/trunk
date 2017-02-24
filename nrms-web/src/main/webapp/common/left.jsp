<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp" %>

<div class="sidebar">
    <nav class="sidenav">
        <ul class="nav_ul" id="accordion">
            <li>
                <a class="no_link sidebtn" href="javascript:void(0);">
                    <span class="icon-menu"></span>
                </a>
            </li>
            <li>
                <a class="bor-left-nav-btm" href="${basePath }" data-toggle="tooltip" data-container="#tooltip_box"
                   data-placement="right" title="按钮">
                    <div class="up-pull-left">
                        <span class="icon-shouye"></span>
                        首页
                    </div>
                </a>
            </li>
            <li>
                <a class="bor-left-nav-btm" href="#collapse_form" data-toggle="collapse" aria-expanded="false">
                    <div class="up-pull-left">
                        <span class="icon-th-large" data-toggle="tooltip" data-container="#tooltip_box"
                              data-placement="right" title="表单"></span>
                        系统管理
                    </div>
                    <div class="up-pull-right">
                        <span class="icon-down-open"></span>
                    </div>
                </a>
                <ul class="nav_ul2 up-collapse" id="collapse_form" aria-expanded="false">

                    <li>
                        <a class="no_link" href="${basePath }/user/list" data-toggle="tooltip"
                           data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                            <div class="up-pull-left">
                                <span class="icon-right-dir"></span>
                                用户管理
                            </div>
                        </a>
                    </li>


                    <li>
                        <a class="no_link" href="${basePath }/role/list" data-toggle="collapse"
                           data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                            <div class="up-pull-left">
                                <span class="icon-right-dir"></span>
                                角色管理
                            </div>
                        </a>
                    </li>


                    <li>
                        <a class="no_link" href="${basePath }/userInfo/userInfoList" data-toggle="tooltip"
                           data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                            <div class="up-pull-left">
                                <span class="icon-right-dir"></span>
                                修改个人密码
                            </div>
                        </a>
                    </li>
                    <li>
                        <a class="no_link" href="${basePath }/userInfo/userInfoList" data-toggle="tooltip"
                           data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                            <div class="up-pull-left">
                                <span class="icon-right-dir"></span>
                                修改个人信息
                            </div>
                        </a>
                    </li>
                    <li>
                        <a class="no_link" href="${basePath }/userInfo/userInfoList" data-toggle="tooltip"
                           data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                            <div class="up-pull-left">
                                <span class="icon-right-dir"></span>
                                参数配置
                            </div>
                        </a>
                    </li>


                </ul>
            </li>

        </ul>

    </nav>
</div>