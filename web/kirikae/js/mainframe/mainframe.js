var mainframeApp = angular.module("mainframe", []);
mainframeApp.controller("mainframeController", function ($scope) {
    $scope.moduleList = [];
    $scope.menuList = [];
    $scope.moduleClick = function (id) {
        //获取菜单
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/menu/queryActiveUserMenuByModule.do",
            data: {"moduleId" : id},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.menuList = result.dataMapList;
                }else {
                    alert(result.message);
                }
            }
        });
    };
    $scope.menuClick = function (url) {
        $("#mainIframe").attr("src",BASE_URL + url);
    };
    $scope.logOut = function () {
        window.location.href = BASE_URL + "/logout.do";
    };
    $scope.editUserPasswordDialog = function () {
        $("#oldPassword").val("");
        $("#newPassword").val("")
        $("#userPasswordModal").modal("show");
    };
    $scope.editUserPassword = function () {
        if (!$.html5Validate.isAllpass($("#oldPassword"))) {
            return;
        }
        if (!$.html5Validate.isAllpass($("#newPassword"))) {
            return;
        }
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/user/editPassword.do",
            data: {"oldPassword" : $("#oldPassword").val(), "newPassword" : $("#newPassword").val()},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    alert("修改密码成功！");
                    $("#userPasswordModal").modal("hide");
                }else{
                    alert(result.message);
                }
            }
        });
    };
    $(document).ready(function() {
        var winHeight = $(window).height() - 86; //基于屏幕的高度， 54是头部导航栏的高度
        $("#mainIframe").height(winHeight - 10);

        //获取模块
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/menu/queryActiveUserModule.do",
            data: {},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.moduleList = result.dataMapList;
                    if($scope.moduleList.length > 0){
                        //获取菜单
                        $.ajax({
                            method: 'post',
                            url: BASE_URL + "/system/menu/queryActiveUserMenuByModule.do",
                            data: {"moduleId" : $scope.moduleList[0].id},
                            async: false,
                            success: function (resultJson) {
                                var result = angular.fromJson(resultJson);
                                if (result.success) {
                                    $scope.menuList = result.dataMapList;
                                    $scope.$apply();
                                }else {
                                    alert(result.message);
                                }
                            }
                        });
                    }else {
                        $scope.menuList = [];
                    }
                }else {
                    alert(result.message);
                }
            }
        });
    });
});
angular.bootstrap(document, [ 'mainframe' ]);