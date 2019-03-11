<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>均胜工作流系统Web主页面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/kirikae/third/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/kirikae/style/common/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/kirikae/style/mainframe/mainframe.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/kirikae/third/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/kirikae/third/angular/angular.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/kirikae/third/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/kirikae/third/jquery-html5Validate/jquery-html5Validate.js"></script>
    <script type="text/javascript">
        var BASE_URL = "<%=request.getContextPath()%>"
    </script>
</head>

<body ng-controller="mainframeController" ng-cloak>
    <div id="moduleNav" class="header">
        <div class="nav">
            <div class="m02" ng-repeat="data in moduleList" ng-click="moduleClick(data.id)">
                <table height="50">
                    <tr height="30">
                        <td>
                            <img alt="" src="<%=request.getContextPath()%>/{{data.imagePath}}">
                        </td>
                    </tr>
                    <tr height="20">
                        <td style="text-align:center;">
                            <div class="m33">{{data.moduleName}}</div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="divSubCatalog" class="subNav subc2">
            <div class="subM02 subc2" ng-repeat="data in menuList" ng-click="menuClick(data.menuUrl)">
                {{data.menuName}}
                <span ng-show="data.woOrderNum != 0" style="position:absolute;top: 0px;right: 0px;width: 20px;height: 18px;font-size: 14px;line-height: 14px;color:red;font-weight:bold;font-style:italic;background-color:blue;">{{data.woOrderNum}}</span>
            </div>
        </div>
        <ul class="password_menu">
            <li>
                <div class="m02" id="div_password" ng-click="editUserPasswordDialog()">
                    <table height="50">
                        <tr height="30">
                            <td><img alt="" src="<%=request.getContextPath()%>/images/per_icon02.png"></td>
                        </tr>
                        <tr height="20">
                            <td style="text-align:center;"><div class="m33">修改密码</div></td>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
        <ul class="exit_menu">
            <li>
                <div class="m02" id="div_exit" ng-click="logOut()">
                    <table height="50">
                        <tr height="30">
                            <td><img alt="" src="<%=request.getContextPath()%>/images/ext_icon02.png"></td>
                        </tr>
                        <tr height="20">
                            <td style="text-align:center;"><div class="m33">退出</div></td>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
    </div>

    <div id="bodyContent" class="content">
        <iframe id="mainIframe" src="<%=request.getContextPath()%>/kirikae/jsp/firstPage/firstPage.jsp" width="100%"></iframe>
    </div>

    <!--修改密码-->
    <div id="userPasswordModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="userPasswordModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h3 id="userPasswordModalLabel">修改密码</h3>
            </div>
            <form class="form-inline">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label class="control-label" for="oldPassword">原密码：</label>
                            <input type="text" id="oldPassword" name="oldPassword" style="width: 80%"
                                   class="form-control-order form-control clean" required="required">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="control-label" for="newPassword">新密码：</label>
                            <input type="text" id="newPassword" name="newPassword" style="width: 80%"
                                   class="form-control-order form-control clean" required="required">
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-footer">
                        <button type="button" ng-click="editUserPassword()"
                                class="btn btn-small btn-primary">确定</button>
                        <button type="button"
                                class="btn btn-small btn-primary" data-dismiss="modal">取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--修改密码-->
</body>
<script src="<%=request.getContextPath()%>/kirikae/js/mainframe/mainframe.js"></script>
</html>
