<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>节假日列表</title>
    <%@include file="../../common/css.jsp"%>
    <%@include file="../../common/js.jsp"%>
</head>
<body ng-controller="systemConfigListController" ng-cloak>
<form class="form-inline">
    <div class="main-container container-fluid">
        <div class="main-content" >
            <div class="page-content" >

                <div class="modal-body" id="searchTable">
                    <div class="row">
                        <div class="col-md-3">
                            <label  class="control-label" >下拉菜单类型：</label>
                            <select ng-model="pageTool.searchForm.configCodeId"
                                    class="form-control-order form-control">
                                <option ng-repeat="codeData in systemConfigCodeList" value="{{codeData.id}}">{{codeData.configCodeName}}</option>
                            </select>
                        </div>
                        <div class="col-md-7"></div>
                        <div class="col-md-2">
                            <button class="btn btn-small btn-purple" type="button" ng-click="pageTool.firstPage()">
                                <i class="icon-search icon-on-right bigger-110"></i>查找
                            </button>
                            <button class="btn btn-small btn-success" type="button" ng-click="addSystemConfigDialog()">
                                <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
                            </button>
                        </div>
                    </div>
                </div>

                <div class="common-table-header">
                    <span>详细数据</span>
                </div>

                <div id="listTable" style="overflow:auto;">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="33%" class="common-table-td">下拉菜单类型</th>
                            <th width="33%" class="common-table-td">下拉菜单选项</th>
                            <th width="33%" class="common-table-td">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="data in systemConfigList">
                            <td>
                                {{data.configCodeName}}
                            </td>
                            <td>
                                {{data.configValue}}
                            </td>
                            <td>
                                <button class="btn btn-mini btn-danger" type="button" ng-click="deleteSystemConfig(data.id)">
                                    <i class="icon-remove icon-on-right bigger-110"></i>删除
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class = "tfoot" id="footTable">
                    <div class="table-foot-center">
                        <button class="" ng-disabled="pageTool.firstPageDisabled"
                                ng-click="pageTool.firstPage()">
                            <span class="glyphicon glyphicon-step-backward "></span>
                        </button>
                        <button class="" ng-disabled="pageTool.prevPageDisabled"
                                ng-click="pageTool.prevPage()">
                            <span class="glyphicon glyphicon-backward"></span>
                        </button>
                        <span class="separator"></span> <span class="page">
								{{pageTool.page}}&nbsp;/&nbsp;{{pageTool.totalPage}}&nbsp; </span> <span
                            class="separator"></span>
                        <button class="" ng-disabled="pageTool.nextPageDisabled"
                                ng-click="pageTool.nextPage()">
                            <span class="glyphicon glyphicon-forward "></span>
                        </button>
                        <button class="" ng-disabled="pageTool.lastPageDisabled"
                                ng-click="pageTool.lastPage()">
                            <span class="glyphicon glyphicon-step-forward  "></span>
                        </button>
                        <span class="separator"></span> <span>共{{pageTool.totalCount}}条</span>
                    </div>
                </div>

            </div>
        </div>
    </div>
</form>

<!-- 新增节假日-->
<div id="systemConfigAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="systemConfigAddModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×
            </button>
            <h3 id="systemConfigAddModalLabel">添加下拉选项</h3>
        </div>
        <form class="form-inline">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label  class="control-label" title="">下拉类型：</label>
                        <select ng-model="systemConfig.configCodeId" required="required" id="configCodeId"
                                class="form-control-order form-control">
                            <option ng-repeat="codeData in systemConfigCodeList" value="{{codeData.id}}">{{codeData.configCodeName}}</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label  class="control-label" title="">下拉选项：</label>
                        <input type="text" ng-model="systemConfig.configValue" id="configValue"
                               class="form-control-order form-control" required="required">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" ng-click="addSystemConfig()"
                            class="btn btn-small btn-primary">确定
                    </button>
                    <button type="button" id="holidayAddReturn"
                            class="btn btn-small btn-primary" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
<script src="<%=request.getContextPath()%>/kirikae/js/system/config/systemConfigList.js"></script>
</html>
