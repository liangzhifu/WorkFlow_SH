<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理页面</title>
    <%@include file="../../common/css.jsp"%>
    <%@include file="../../common/js.jsp"%>
</head>
<body ng-controller="systemUserListController" ng-cloak>
<form class="form-inline">
    <div class="main-container container-fluid">
        <div class="main-content" >
            <div class="page-content" >

                <div class="modal-body" id="searchTable">
                    <div class="row">
                        <div class="col-md-2">
                            <label class="control-label" for="userCode">工号：</label>
                            <input class="form-control-order form-control clean" style="width: 75%"
                                   id="userCode" ng-model="pageTool.searchForm.userCode">
                        </div>
                        <div class="col-md-2">
                            <label class="control-label" for="userName">姓名：</label>
                            <input class="form-control-order form-control clean" style="width: 75%"
                                   id="userName" ng-model="pageTool.searchForm.userName">
                        </div>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-small btn-purple" type="button" ng-click="pageTool.firstPage()">
                                <i class="icon-search icon-on-right bigger-110"></i>查找
                            </button>
                            <button class="btn btn-small btn-success" type="button" ng-click="addSystemUserDialog()">
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
                            <td width="20%" class="common-table-td">工号</td>
                            <td width="20%" class="common-table-td">姓名</td>
                            <td width="20%" class="common-table-td">邮箱</td>
                            <td width="40%" class="common-table-td">操作</td>
                        </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="data in systemUserList">
                                <td>{{data.userCode}}</td>
                                <td>{{data.userName}}</td>
                                <td>{{data.userEmail}}</td>
                                <td>
                                    <button class="btn btn-mini btn-info" type="button" ng-click="editSystemRoleDialog(data.id)">
                                        <i class="icon-edit icon-on-right bigger-110"></i>修改
                                    </button>
                                    <button class="btn btn-mini btn-danger" type="button" ng-click="deleteSystemUser(data.id)">
                                        <i class="icon-remove icon-on-right bigger-110"></i>删除
                                    </button>
                                    <button class="btn btn-small btn-purple" type="button" ng-click="grantRoleDialog(data.id)">
                                        <i class="icon-edit icon-on-right bigger-110"></i>分配角色
                                    </button>
                                    <button class="btn btn-small btn-purple" type="button" ng-click="grantOrgDialog(data.id)">
                                        <i class="icon-edit icon-on-right bigger-110"></i>修改组织
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

<!--用户新增-->
<div id="userAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="userAddModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            <h3 id="userAddModalLabel">用户新增</h3>
        </div>
        <form class="form-inline">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label class="control-label" title="">工号：</label>
                        <input type="text" title="" style="width: 75%"
                               class="form-control-order form-control clean" ng-model="editSystemUser.userCode" required="required">
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <div class="modal-footer">
                    <button type="button" ng-click="addSystemUserFun()"
                            class="btn btn-small btn-primary">确定</button>
                    <button type="button"
                            class="btn btn-small btn-primary" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!--用户新增-->

<!--用户修改-->
<div id="userEditModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="userEditModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            <h3 id="userEditModalLabel">用户修改</h3>
        </div>
        <form class="form-inline">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label class="control-label" title="">工号：</label>
                        <input type="text" title="" style="width: 75%" ng-disabled="true"
                               class="form-control-order form-control clean" ng-model="editSystemUser.userCode" required="required">
                    </div>
                    <div class="col-md-6">
                        <label class="control-label" title="">姓名：</label>
                        <input type="text" title="" style="width: 75%"
                               class="form-control-order form-control clean" ng-model="editSystemUser.userName" required="required">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label class="control-label" title="">EMAIL：</label>
                        <input type="text" title="" style="width: 75%"
                               class="form-control-order form-control clean" ng-model="editSystemUser.userEmail" required="required">
                    </div>
                </div>
            </div>
            <div class="modal-body">
                <div class="modal-footer">
                    <button type="button" ng-click="editSystemUserFun()"
                            class="btn btn-small btn-primary">确定</button>
                    <button type="button"
                            class="btn btn-small btn-primary" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!--用户修改-->

<!--用户角色关联-->
<div id="userRoleModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="userRoleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            <span id="userRoleModalLabel">所有角色</span>
            <button class="btn btn-small btn-success" type="button" ng-click="addSystemUserRoleDialog()">
                <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
            </button>
        </div>
        <form class="form-inline">
            <div style="overflow:auto;width: 100%">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <td width="25%" class="common-table-td">角色名称</td>
                        <td width="50%" class="common-table-td">角色说明</td>
                        <td width="25%" class="common-table-td">操作</td>
                    </tr>
                    </thead>
                    <tr ng-repeat="data in systemUserRoleList">
                        <td>{{data.roleName}}</td>
                        <td>{{data.explaination}}</td>
                        <td>
                            <button class="btn btn-mini btn-danger" type="button" ng-click="deleteSystemUserRole(data.id)">
                                <i class="icon-remove icon-on-right bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-body">
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-small btn-primary" data-dismiss="modal">关闭
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!--用户角色关联-->

<!--可以加入的角色-->
<div id="roleListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="roleListModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            <h3 id="roleListModalLabel">角色列表</h3>
        </div>
        <form class="form-inline">
            <div style="overflow:auto;width: 100%">
                <table class="table table-striped table-bordered table-hover" style="width: 100%">
                    <thead>
                    <tr>
                        <td width="25%" class="common-table-td">
                            <input type="checkbox" style="width: 18px;height: 18px;" id="roleSelectAll" ng-click="selectAllRole()" />
                            <div style="float:left; margin-left:20%;">全选</div>
                        </td>
                        <td width="25%" class="common-table-td">角色名称</td>
                        <td width="50%" class="common-table-td">角色说明</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="data in roleList">
                        <td>
                            <input type="checkbox" style="width: 18px;height: 18px;"
                                   name="roleCheck" value={{data.id}} />
                        </td>
                        <td>{{data.roleName}}</td>
                        <td>{{data.explaination}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-body">
                <div class="modal-footer">
                    <button type="button" ng-click="addSystemUserRole()"
                            class="btn btn-small btn-primary">确定</button>
                    <button type="button"
                            class="btn btn-small btn-primary" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!--可以加入的角色-->

<!-- 组织树 -->
<div id="orgModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="orgModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            <h3 id="orgModalLabel">组织列表</h3>
        </div>
        <form class="form-inline" >
            <div>
                <ul id="orgTree" class="ztree"></ul>
            </div>
            <div class="modal-body">
                <div class="modal-footer">
                    <button type="button" ng-click="addSystemUserOrg()"
                            class="btn btn-small btn-primary">确定</button>
                    <button type="button"
                            class="btn btn-small btn-primary" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 组织树 -->
</body>
<script src="<%=request.getContextPath()%>/kirikae/js/system/user/systemUserList.js"></script>
</html>
