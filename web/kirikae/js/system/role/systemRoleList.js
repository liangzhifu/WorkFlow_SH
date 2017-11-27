var systemRoleListApp = angular.module("systemRoleList", []);
systemRoleListApp.controller("systemRoleListController", function ($scope) {
    $scope.systemRoleList = [];
    $scope.pageTool = {
        "url" : BASE_URL + "/system/role/queryPageList.do"
    };
    $scope.pageTool.searchForm = {};
    $scope.pageTool.firstPage = function () {
        $scope.pageTool.page = 1;
        $scope.pageTool.search();
    };
    $scope.pageTool.prevPage = function () {
        $scope.pageTool.page = $scope.pageTool.page - 1;
        $scope.pageTool.search();
    };
    $scope.pageTool.nextPage = function () {
        $scope.pageTool.page = $scope.pageTool.page + 1;
        $scope.pageTool.search();
    };
    $scope.pageTool.lastPage = function () {
        $scope.pageTool.page = $scope.pageTool.totalPage;
        $scope.pageTool.search();
    };
    $scope.pageTool.search = function () {
        $scope.pageTool.searchForm.size = 10;
        $scope.pageTool.searchForm.start = ($scope.pageTool.page-1)*$scope.pageTool.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.pageTool.url,
            data:$scope.pageTool.searchForm,
            async:false,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.systemRoleList = result.dataMapList;
                    $scope.pageTool.totalCount = result.totalCount;//共几条
                    $scope.pageTool.totalPage = result.totalPage;//共几页
                    if ($scope.pageTool.page <= 1) {
                        $scope.pageTool.firstPageDisabled = true;
                        $scope.pageTool.prevPageDisabled = true;
                    } else {
                        $scope.pageTool.firstPageDisabled = false;
                        $scope.pageTool.prevPageDisabled = false;
                    }
                    if ($scope.pageTool.page >= $scope.pageTool.totalPage) {
                        $scope.pageTool.nextPageDisabled = true;
                        $scope.pageTool.lastPageDisabled = true;
                    } else {
                        $scope.pageTool.nextPageDisabled = false;
                        $scope.pageTool.lastPageDisabled = false;
                    }
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            }
        });
    };

    $scope.systemRole = {};
    //增加、修改角色
    $scope.addSystemRole = function () {
        var confrimStr = "";
        var url = "";
        if($scope.systemRole.id == null){
            confrimStr = "确定新增角色！";
            url = BASE_URL + "/system/role/add.do";
        }else {
            confrimStr = "确定修改角色！";
            url = BASE_URL + "/system/role/edit.do";
        }
        var con = confirm(confrimStr);
        if (con == true){
            $.ajax({
                method: 'post',
                url: url,
                data: $scope.systemRole,
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.firstPage();
                        $("#addOrEditRoleModal").modal("hide");
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };
    $scope.addSystemRoleDialog = function () {
        $scope.systemRole.id = null;
        $scope.systemRole.roleName = "";
        $scope.systemRole.explaination = "";
        $("#addOrEditRoleModalLabel").html("增加角色");
        $("#addOrEditRoleModal").modal("show");
    };
    $scope.editSystemRoleDialog = function (id) {
        for(var i = 0; i < $scope.systemRoleList.length; i++){
            var data = $scope.systemRoleList[i];
            if(data.id == id){
                $scope.systemRole.id = data.id;
                $scope.systemRole.roleName = data.roleName;
                $scope.systemRole.explaination = data.explaination;
            }
        }
        $("#addOrEditRoleModalLabel").html("修改角色");
        $("#addOrEditRoleModal").modal("show");
    };
    //删除角色
    $scope.deleteSystemRole = function (id) {
        var con = confirm("确定删除角色！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/role/delete.do",
                data: {"id":id},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.firstPage();
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };
    $scope.systemRolePermissionList = [];
    //查询角色权限关联
    $scope.searchSystemRolePermission = function (roleId) {
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/rolePermission/queryPageList.do",
            data: {"roleId":roleId},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.systemRolePermissionList = result.dataMapList;
                }else {
                    alert(result.message);
                }
            }
        });
    };
    $scope.grantPermissionDialog = function (id) {
        for(var i = 0; i < $scope.systemRoleList.length; i++){
            var data = $scope.systemRoleList[i];
            if(data.id == id){
                $scope.systemRole.id = data.id;
                $scope.systemRole.roleName = data.roleName;
                $scope.systemRole.explaination = data.explaination;
            }
        }
        $scope.searchSystemRolePermission(id);
        $("#rolePermissionModal").modal("show");
    };
    //删除角色权限关联
    $scope.deleteSystemRolePermission = function(id){
        var con = confirm("确定删除权限！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/rolePermission/delete.do",
                data: {"id":id},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.searchSystemRolePermission($scope.systemRole.id);
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };
    //显示未加入角色的权限列表
    $scope.permissionList = {};
    $scope.addSystemRolePermissionDialog = function () {
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/rolePermission/add/queryPageList.do",
            data: {"roleId":$scope.systemRole.id},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.permissionList = result.dataMapList;
                    $("#permissionListModal").modal("show");
                }else {
                    alert(result.message);
                }
            }
        });
    };
    $scope.selectAllPermission = function () {
        var checked = $('#permissionSelectAll').is(':checked');
        $('input[name="permissionCheck"]').each(function(){
            $(this).prop('checked', checked);
        });
    };
    $scope.addSystemRolePermission = function () {
        var con = confirm("确定增加角色（" + $scope.systemRole.roleName + "）的权限！");
        if (con == true){
            var permissionIdStr = "";
            $('input[name="permissionCheck"]:checked').each(function() {
                permissionIdStr += "," + $(this).val();
            });
            if(permissionIdStr == ""){
                alert("请至少选择一个权限！");
                return false;
            }else {
                permissionIdStr = permissionIdStr.substring(1);
                $.ajax({
                    method: 'post',
                    url: BASE_URL + "/system/rolePermission/add.do",
                    data: {"roleId":$scope.systemRole.id, "permissionIdStr":permissionIdStr},
                    async: false,
                    success: function (resultJson) {
                        var result = angular.fromJson(resultJson);
                        if (result.success) {
                            $scope.searchSystemRolePermission($scope.systemRole.id);
                            $("#permissionListModal").modal("hide");
                        }else {
                            alert(result.message);
                        }
                    }
                });
            }
        }
    };

    //获取角色的菜单列表对话框
    $scope.grantMenuDialog = function (id) {
        for(var i = 0; i < $scope.systemRoleList.length; i++){
            var data = $scope.systemRoleList[i];
            if(data.id == id){
                $scope.systemRole.id = data.id;
                $scope.systemRole.roleName = data.roleName;
                $scope.systemRole.explaination = data.explaination;
            }
        }
        var ztree = $.fn.zTree.getZTreeObj("menuTree");
        ztree.checkAllNodes(false);
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/roleMenu/querySystemRoleMenuList.do",
            data: {"roleId": $scope.systemRole.id},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    var dataMapList = result.dataMapList;
                    if(!(dataMapList == undefined || dataMapList == null)){
                        for(var i = 0; i < dataMapList.length; i++){
                            var menuId = dataMapList[i].menuId;
                            var node = ztree.getNodeByParam("id", menuId);
                            ztree.checkNode(node, true, true);
                        }
                    }
                    $("#menuModal").modal("show");
                }else {
                    alert(result.message);
                }
            }
        });
    };

    //修改角色菜单权限
    $scope.addSystemRoleMenu = function () {
        var ztree = $.fn.zTree.getZTreeObj("menuTree");
        var nodes = ztree.getCheckedNodes(true);
        var menuIds = "";
        for(var i = 0; i < nodes.length; i++){
            var node = nodes[i];
            if(!node.isParent){
                menuIds += "," + node.id;
            }
        }
        if(menuIds != ""){
            menuIds = menuIds.substring(1);
        }
        var con = confirm("确定修改角色的菜单！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/roleMenu/add.do",
                data: {"roleId":$scope.systemRole.id,"menuIdStr":menuIds},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $("#menuModal").modal("hide");
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };

    $(document).ready(function() {
        $scope.pageTool.firstPage();

        var zSetting = {
            data : {
                keep : {
                    leaf : false,
                    parent : false
                },
                key : {
                    checked : "checked",
                    children : "children",
                    name : "menuName",
                    title : "",
                    url : "url"
                },
                simpleData : {
                    enable : true,
                    idKey : "id",
                    pIdKey : "parentId",
                    rootPId : null
                }
            },

            view : {
                showLine : true,
                dblClickExpand : true,
                selectedMulti: true
            },

            check : {
                enable : true
            }

        };
        //获取菜单树
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/menu/queryTreeList.do",
            data: {},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $.fn.zTree.init($("#menuTree"), zSetting, result.dataMapList);
                }else {
                    alert(result.message);
                }
            }
        });

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        height = height - searchTableHeight - footTableHeight - 48;
        $("#listTable").css("height", height);
    });

});
angular.bootstrap(document, [ 'systemRoleList' ]);