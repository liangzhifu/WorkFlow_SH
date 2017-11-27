<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理页面</title>
    <%@include file="../../common/css.jsp"%>
    <%@include file="../../common/js.jsp"%>
</head>
<body ng-controller="systemRoleListController" ng-cloak>
    <form class="form-inline">
        <div class="main-container container-fluid">
            <div class="main-content" >
                <div class="page-content" >

                    <div class="modal-body" id="searchTable">
                        <div class="row">
                            <div class="col-md-2">
                                <label class="control-label" for="roleName">角色名称：</label>
                                <input class="form-control-order form-control clean" id="roleName" ng-model="pageTool.searchForm.roleName">
                            </div>
                            <div class="col-md-8">
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-small btn-purple" type="button" ng-click="pageTool.firstPage()">
                                    <i class="icon-search icon-on-right bigger-110"></i>查找
                                </button>

                                <button class="btn btn-small btn-success" type="button" ng-click="addSystemRoleDialog()">
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
                                    <td width="20%" class="common-table-td">角色名称</td>
                                    <td width="40%" class="common-table-td">角色说明</td>
                                    <td width="40%" class="common-table-td">操作</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="data in systemRoleList">
                                    <td>{{data.roleName}}</td>
                                    <td>{{data.explaination}}</td>
                                    <td>
                                        <button class="btn btn-mini btn-info" type="button" ng-click="editSystemRoleDialog(data.id)">
                                            <i class="icon-edit icon-on-right bigger-110"></i>修改
                                        </button>
                                        <button class="btn btn-mini btn-danger" type="button" ng-click="deleteSystemRole(data.id)">
                                            <i class="icon-remove icon-on-right bigger-110"></i>删除
                                        </button>
                                        <button class="btn btn-mini btn-purple" type="button" ng-click="grantPermissionDialog(data.id)">
                                            <i class="icon-edit icon-on-right bigger-110"></i>分配权限
                                        </button>
                                        <button class="btn btn-mini btn-purple" type="button" ng-click="grantMenuDialog(data.id)">
                                            <i class="icon-edit icon-on-right bigger-110"></i>分配菜单
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

    <!--角色增加、修改-->
    <div id="addOrEditRoleModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="addOrEditRoleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h3 id="addOrEditRoleModalLabel"></h3>
            </div>
            <form class="form-inline" id="addOrEditRole" >
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label class="control-label" title="">角色名称：</label>
                            <input type="text" title="" name="parentCompanyName" style="width: 80%"
                                   class="form-control-order form-control clean" ng-model="systemRole.roleName" required="required">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="control-label" title="">角色说明：</label>
                            <textarea title="" ng-model="systemRole.explaination" rows="3" style="width: 80%"
                                      class="form-control-order form-control clean"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-footer">
                        <button type="button" ng-click="addSystemRole()"
                                class="btn btn-small btn-primary">确定</button>
                        <button type="button"
                                class="btn btn-small btn-primary" data-dismiss="modal">取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--角色增加、修改-->

    <!--角色权限关联-->
    <div id="rolePermissionModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="rolePermissionModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <span id="rolePermissionModalLabel">所有权限</span>
                <button class="btn btn-small btn-success" type="button" ng-click="addSystemRolePermissionDialog()">
                    <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
                </button>
            </div>
            <form class="form-inline" id="rolePermission" >
                <div style="overflow:auto;width: 100%">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <td width="25%" class="common-table-td">权限名称</td>
                            <td width="50%" class="common-table-td">权限说明</td>
                            <td width="25%" class="common-table-td">操作</td>
                        </tr>
                        </thead>
                            <tr ng-repeat="data in systemRolePermissionList">
                                <td>{{data.permissionName}}</td>
                                <td>{{data.explaination}}</td>
                                <td>
                                    <button class="btn btn-mini btn-danger" type="button" ng-click="deleteSystemRolePermission(data.id)">
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
    <!--角色权限关联-->

    <!--可以加入的权限-->
    <div id="permissionListModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="permissionListModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h3 id="permissionListModalLabel">权限列表</h3>
            </div>
            <form class="form-inline" id="permissionList" >
                <div style="overflow:auto;width: 100%">
                    <table class="table table-striped table-bordered table-hover" style="width: 100%">
                        <thead>
                        <tr>
                            <td width="25%" class="common-table-td">
                                <input type="checkbox" style="width: 18px;height: 18px;" id="permissionSelectAll" ng-click="selectAllPermission()" />
                                <div style="float:left; margin-left:20%;">全选</div>
                            </td>
                            <td width="25%" class="common-table-td">权限名称</td>
                            <td width="50%" class="common-table-td">权限说明</td>
                        </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="data in permissionList">
                                <td>
                                    <input type="checkbox" style="width: 18px;height: 18px;"
                                           name="permissionCheck" value={{data.id}} />
                                </td>
                                <td>{{data.permissionName}}</td>
                                <td>{{data.explaination}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-body">
                    <div class="modal-footer">
                        <button type="button" ng-click="addSystemRolePermission()"
                                class="btn btn-small btn-primary">确定</button>
                        <button type="button"
                                class="btn btn-small btn-primary" data-dismiss="modal">取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--可以加入的权限-->

    <!-- 菜单树 -->
    <div id="menuModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="menuModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h3 id="menuModalLabel">菜单列表</h3>
            </div>
            <form class="form-inline" >
                <div>
                    <ul id="menuTree" class="ztree"></ul>
                </div>
                <div class="modal-body">
                    <div class="modal-footer">
                        <button type="button" ng-click="addSystemRoleMenu()"
                                class="btn btn-small btn-primary">确定</button>
                        <button type="button"
                                class="btn btn-small btn-primary" data-dismiss="modal">取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- 菜单树 -->

</body>
    <script src="<%=request.getContextPath()%>/kirikae/js/system/role/systemRoleList.js"></script>
</html>
