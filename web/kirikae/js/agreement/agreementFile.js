updateFileExcelChange = function (){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                alert(data.message);
                window.location.reload();
            }
        });
        $("#uploadFile").val('');
    }
};

var agreementFileApp = angular.module("agreementFile", []);
agreementFileApp.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true);
}]);
agreementFileApp.controller("agreementFileController",["$scope", "$location", function ($scope, $location) {
    $scope.id = '';
    $scope.buttonShow = true;
    if(!($location.search().id == undefined || $location.search().id == null)){
        $scope.id = $location.search().id;
    }
    if(!($location.search().closeState == undefined || $location.search().closeState == null)){
        if (!($location.search().closeState == 1 || $location.search().closeState == 3)) {
            $scope.buttonShow = false;
        }
    }
    $scope.agreementFileList = [];

    $scope.deleteAgreementFile = function (id) {
        $.ajax({
            method: 'post',
            data: {"id" : id},
            url: BASE_URL + "/agreementFile/deleteAgreementFile.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $.ajax({
                        method: 'post',
                        data: {"agreementId" : $scope.id},
                        url: BASE_URL + "/agreementFile/getAgreementFileList.do",
                        success: function (resultJson) {
                            var result = angular.fromJson(resultJson);
                            if (result.success) {
                                $scope.agreementFileList = result.agreementFileList;
                                $scope.$apply();
                                alert("删除成功！");
                            }
                        }
                    });
                }
            }
        });
    };

    $scope.downloadFile = function (fileId) {
        window.open(BASE_URL+"/fileUpload/download.do?fileId="+fileId);
    };

    $(document).ready(function () {
        $.ajax({
            method: 'post',
            data: {"agreementId" : $scope.id},
            url: BASE_URL + "/agreementFile/getAgreementFileList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.agreementFileList = result.agreementFileList;
                    $scope.$apply();
                }
            }
        });
    });
}]);
angular.bootstrap(document, [ 'agreementFile' ]);