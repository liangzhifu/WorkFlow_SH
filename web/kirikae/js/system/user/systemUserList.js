var systemUserListApp = angular.module("systemUserList", []);
systemUserListApp.controller("systemUserListController", function ($scope) {
    $scope.systemUserList = [];
    $scope.pageTool = {
        "url" : BASE_URL + "/system/user/queryPageList.do"
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
                    $scope.systemUserList = result.dataMapList;
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
    $scope.systemUser = {};

    $scope.systemUserRoleList = [];
    //查询用户角色关联
    $scope.searchSystemUserRole = function (userId) {
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/userRole/queryList.do",
            data: {"userId":userId},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.systemUserRoleList = result.dataMapList;
                }else {
                    alert(result.message);
                }
            }
        });
    };
    //获取用户角色列表对话框
    $scope.grantRoleDialog = function (userId) {
        for(var i = 0; i < $scope.systemUserList.length; i++){
            var data = $scope.systemUserList[i];
            if(data.id == userId){
                $scope.systemUser.id = data.id;
                $scope.systemUser.userName = data.userName;
            }
        }
        $scope.searchSystemUserRole(userId);
        $("#userRoleModal").modal("show");
    };
    //删除用户角色关联
    $scope.deleteSystemUserRole = function(id){
        var con = confirm("确定删除用户！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/userRole/delete.do",
                data: {"id":id},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.searchSystemUserRole($scope.systemUser.id);
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };
    //显示未加入角色的权限列表
    $scope.roleList = {};
    $scope.addSystemUserRoleDialog = function () {
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/userRole/add/queryList.do",
            data: {"userId":$scope.systemUser.id},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.roleList = result.dataMapList;
                    $("#roleListModal").modal("show");
                }else {
                    alert(result.message);
                }
            }
        });
    };
    $scope.selectAllRole = function () {
        var checked = $('#roleSelectAll').is(':checked');
        $('input[name="roleCheck"]').each(function(){
            $(this).prop('checked', checked);
        });
    };
    $scope.addSystemUserRole = function () {
        var con = confirm("确定增加用户（" + $scope.systemUser.userName + "）的角色！");
        if (con == true){
            var roleIdStr = "";
            $('input[name="roleCheck"]:checked').each(function() {
                roleIdStr += "," + $(this).val();
            });
            if(roleIdStr == ""){
                alert("请至少选择一个角色！");
                return false;
            }else {
                roleIdStr = roleIdStr.substring(1);
                $.ajax({
                    method: 'post',
                    url: BASE_URL + "/system/userRole/add.do",
                    data: {"userId":$scope.systemUser.id, "roleIdStr":roleIdStr},
                    async: false,
                    success: function (resultJson) {
                        var result = angular.fromJson(resultJson);
                        if (result.success) {
                            $scope.searchSystemUserRole($scope.systemUser.id);
                            $("#roleListModal").modal("hide");
                        }else {
                            alert(result.message);
                        }
                    }
                });
            }
        }
    };


    //获取用户组织
    $scope.grantOrgDialog = function (userId) {
        for(var i = 0; i < $scope.systemUserList.length; i++){
            var data = $scope.systemUserList[i];
            if(data.id == userId){
                $scope.systemUser.id = data.id;
                $scope.systemUser.userName = data.userName;
            }
        }

        var ztree = $.fn.zTree.getZTreeObj("orgTree");
        ztree.checkAllNodes(false);
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/userOrg/querySystemUserRoleList.do",
            data: {"userId": $scope.systemUser.id},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    var dataMapList = result.dataMapList;
                    if(!(dataMapList == undefined || dataMapList == null)){
                        for(var i = 0; i < dataMapList.length; i++){
                            var orgId = dataMapList[i].orgId;
                            var node = ztree.getNodeByParam("id", orgId);
                            ztree.checkNode(node, true, true);
                        }
                    }
                    $("#orgModal").modal("show");
                }else {
                    alert(result.message);
                }
            }
        });
    };

    //修改用户组织
    $scope.addSystemUserOrg = function () {
        var ztree = $.fn.zTree.getZTreeObj("orgTree");
        var nodes = ztree.getCheckedNodes(true);
        var orgIdStr = "";
        for(var i = 0; i < nodes.length; i++){
            var node = nodes[i];
            orgIdStr += "," + node.id;
        }
        if(orgIdStr != ""){
            orgIdStr = orgIdStr.substring(1);
        }
        var con = confirm("确定修改用户的组织！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/userOrg/edit.do",
                data: {"userId":$scope.systemUser.id,"orgIdStr":orgIdStr},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $("#orgModal").modal("hide");
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };

    $scope.deleteSystemUser = function (id) {
        var con = confirm("确定删除用户！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/user/delete.do",
                data: {"id":id},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };

    $scope.editSystemUser = {};
    $scope.addSystemUserDialog = function () {
        $scope.editSystemUser.userCode = "";
        $("#userAddModal").modal("show");
    };
    $scope.addSystemUserFun = function () {
        var con = confirm("确定新增用户！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/user/add.do",
                data: {"userCode" : $scope.editSystemUser.userCode},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                        $("#userAddModal").modal("hide");
                    }else {
                        alert(result.message);
                    }
                }
            });
        }
    };
    $scope.editSystemRoleDialog = function (id) {
        for(var i = 0; i < $scope.systemUserList.length; i++){
            if(id == $scope.systemUserList[i].id){
                $scope.editSystemUser = $scope.systemUserList[i];
            }
        }
        $("#userEditModal").modal("show");
    };

    $scope.editSystemUserFun = function () {
        var con = confirm("确定修改用户！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/user/edit.do",
                data: $scope.editSystemUser,
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                        $("#userEditModal").modal("hide");
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
                    name : "orgName",
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
                enable : true,
                chkboxType: { "Y": "", "N": "" }
            }

        };
        //获取组织树
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/org/queryUserOrgTree.do",
            data: {},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $.fn.zTree.init($("#orgTree"), zSetting, result.dataMapList);
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
angular.bootstrap(document, [ 'systemUserList' ]);