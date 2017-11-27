var systemConfigListApp = angular.module("systemConfigList", []);
systemConfigListApp.controller("systemConfigListController", function ($scope) {
    $scope.systemConfigList = [];
    $scope.systemConfigCodeList = [];
    $scope.pageTool = {
        "url" : BASE_URL + "/system/config/queryPageList.do"
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
                    $scope.systemConfigList = result.dataMapList;
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

    $scope.deleteSystemConfig = function (id) {
        var con = confirm("确定删除！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/config/delete.do",
                data: {"id" : id},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                    } else {
                        alert(result.mesage);
                    }
                }
            });
        }
    };

    $scope.systemConfig = {};
    $scope.addSystemConfigDialog = function () {
        $scope.systemConfig.configCodeId = "";
        $scope.systemConfig.configValue = "";
        $("#systemConfigAddModal").modal("show");
    };

    $scope.addSystemConfig = function () {
        var con = confirm("确定增加！");
        if (con == true){
            if (!$.html5Validate.isAllpass($("#configCodeId"))) {
                return;
            }
            if (!$.html5Validate.isAllpass($("#configValue"))) {
                return;
            }
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/config/add.do",
                data: $scope.systemConfig,
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                        $("#systemConfigAddModal").modal("hide");
                    } else {
                        alert(result.mesage);
                    }
                }
            });
        }
    };

    $(document).ready(function() {
        $.ajax({
            method: 'post',
            url: BASE_URL + "/system/config/queryCodeList.do",
            data: {},
            async: false,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.systemConfigCodeList = resultJson.dataMapList;
                } else {
                    alert(result.mesage);
                }
            }
        });

        $scope.pageTool.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'systemConfigList' ]);