<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<div class="sidebar">
    <nav class="sidenav">
        <ul class="nav_ul" id="accordion">
            <li>
                <a class="no_link sidebtn" href="javascript:void(0);">
                    <span class="icon-menu"></span>
                </a>
            </li>
            <li>
                <a class="bor-left-nav-btm" href="${basePath }/" data-toggle="tooltip" data-container="#tooltip_box" data-placement="right" title="按钮">
                    <div  class="up-pull-left">
                        <span class="icon-shouye"></span>
                        首页
                    </div>
                </a>
            </li>
            <c:forEach var="md" items="${USER_MENUS }">
                <li>
                    <a class="bor-left-nav-btm" href="#collapse_form${md.parentMenu.url }" data-toggle="collapse" aria-expanded="false">
                        <div  class="up-pull-left">
                            <span class="icon-th-large" data-toggle="tooltip" data-container="#tooltip_box" data-placement="right" title="表单"></span>
                                ${md.parentMenu.mname }
                        </div>
                        <div  class="up-pull-right">
                            <span class="icon-down-open"></span>
                        </div>
                    </a>
                    <ul class="nav_ul2 up-collapse" id="collapse_form${md.parentMenu.url }" aria-expanded="false">
                        <c:forEach var="m" items="${md.childMenus }">
                            <li>
                                <a class="no_link" href="${basePath}${m.url}" data-toggle="tooltip" data-container="#tooltip_box" data-placement="right" title="多列内联表单">
                                    <div  class="up-pull-left">
                                        <span class="icon-right-dir"></span>
                                            ${m.mname }
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>

                </li>
            </c:forEach>

        </ul>
    </nav>
</div>