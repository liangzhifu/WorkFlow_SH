var holidayListApp = angular.module("systemHolidayList", []);
holidayListApp.controller("systemHolidayListController", function ($scope) {
    $scope.systemHolidayList = [];
    $scope.pageTool = {
        "url" : BASE_URL + "/system/holiday/queryPageList.do"
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
                    $scope.systemHolidayList = result.dataMapList;
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

    $scope.deleteSystemHoliday = function (id) {
        var con = confirm("确定删除节假日！");
        if (con == true){
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/holiday/delete.do",
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

    $scope.addSystemHolidayDialog = function () {
        $("#holiday").val("");
        $("#holidayAddModal").modal("show");
    };

    $scope.addSystemHoliday = function () {
        var con = confirm("确定增加节假日！");
        if (con == true){
            if (!$.html5Validate.isAllpass($("#holiday"))) {
                return;
            }
            $.ajax({
                method: 'post',
                url: BASE_URL + "/system/holiday/add.do",
                data: {"holiday" : $("#holiday").val()},
                async: false,
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.pageTool.search();
                        $("#holidayAddModal").modal("hide");
                    } else {
                        alert(result.mesage);
                    }
                }
            });
        }
    };

    $(document).ready(function() {
        $("input[data-type='date']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                format: 'Y-m-d'
            });
        });

        $scope.pageTool.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'systemHolidayList' ]);