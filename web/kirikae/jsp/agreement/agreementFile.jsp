<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: 梁志福
  Date: 2017/4/19
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>立合附件</title>
    <%@include file="../common/js.jsp"%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/multiselect/jquery.multiselect.css" >
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/ace-responsive.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/button.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/ace-fonts.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/jquery-ui.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/uniform.default.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/bootstrap/datetimepicker-xdan/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/jBox/jBox.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/js/treeTable/themes/vsStyle/treeTable.min.css"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/back-end/style.css" >
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/common/common.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/jqueryui.datetimepicker/jquery.datetimepicker.css" />

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ext/resources/css/ext-zte.css">
</head>
<body ng-controller="agreementFileController" ng-cloak>
    <div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
        <div class="main-content" >
            <div class="page-content" >
                <form method="post" id="excelForm" enctype="multipart/form-data" action="<%=request.getContextPath()%>/agreementFile/uploadFile.do">
                    <input type="file" onchange="updateFileExcelChange()" name="uploadFile" id="uploadFile"  ng-show="buttonShow"/>
                    <input type="hidden" name="agreementId" value="{{id}}"/>
                </form>
                <div id="listTable" style="overflow:auto;">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="40px" class="x-grid3-header" style="padding: 0px">附件名称</th>
                            <th width="35px" class="x-grid3-header" style="padding: 0px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="fileData in agreementFileList">
                            <td>{{fileData.fileName}}</td>
                            <td>
                                <input type="button" value="删除" ng-click="deleteAgreementFile(fileData.id)" ng-show="buttonShow">
                                <input type="button" value="查看" ng-click="downloadFile(fileData.fileId)">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="<%=request.getContextPath()%>/kirikae/js/agreement/agreementFile.js?version=1"></script>
</html>