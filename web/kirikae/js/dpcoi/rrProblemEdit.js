var myModal;

updateFileExcelChange = function (fileTimeInput, fileIdInput){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                alert(data.message);
                $("#"+fileTimeInput).val(data.fileDate);
                $("#"+fileIdInput).val(data.fileId);
                myModal.destroy();
            }
        });
        $("#uploadFile").val('');
    }
};

var rrProblemEditApp = angular.module("rrProblemEdit", []);
rrProblemEditApp.controller("rrProblemEditController", function ($scope) {
    $scope.action = action;
    $scope.rrProblemEdit = {};
    $scope.rrProblemEdit.persionLiableList = [{
        "userId" : "",
        "userName" : ""
    }]
    $scope.rrProblemEdit.dpcoiConfigList = [{
        "configId" : "",
        "configCodeId" : "",
        "configValue" : ""
    }];
    $scope.rrProblemEdit.rrProblem = {
        "id":"",
        "problemStatus" : "",
        "problemNo" : "",
        "problemType" : "",
        "engineering" : "",
        "problemProgress" : "",
        "trackingLevel" : "",
        "happenDate" : "",
        "happenDateStr" : "",
        "customer" : "",
        "vehicle" : "",
        "productNo" : "",
        "badContent" : "",
        "persionLiable" : "",
        "reportDate" : "",
        "reportDateStr" : "",
        "speedOfProgress" : "",
        "reasonForDelay" : "",
        "firstDate" : "",
        "secondDate" : "",
        "thirdDate" : "",
        "fourthDate" : "",
        "firstDateStr" : "",
        "secondDateStr" : "",
        "thirdDateStr" : "",
        "fourthDateStr" : "",
        "closeConfirm" : "",
        "productLine" : "",
        "severity" : "",
        "occurrenceFrequency" : "",
        "badQuantity" : "",
        "batch" : "",
        "happenShift" : "白班",
        "responsibleDepartment" : "",
        "recordPpm" : "",
        "recordNum" : "N/A",
        "temporary" : "",
        "rootCause" : "",
        "permanentGame" : "",
        "effectVerification" : "",
        "serialNumberFileId" : "",
        "serialNumber" : "N/A",
        "qualityWarningCardNumberFileId" : "",
        "qualityWarningCardNumber" : "N/A",
        "productScaleFileId" : "",
        "productScale" : "N/A",
        "pfmea" : "",
        "cp" : "",
        "standardBook" : "",
        "equipmentChecklistFileId" : "",
        "equipmentChecklist" : "",
        "alwaysList" : "",
        "inspectionReferenceBookFileId" : "",
        "inspectionReferenceBook" : "",
        "inspectionBookFileId" : "",
        "inspectionBook" : "",
        "educationFileId" : "",
        "education" : "",
        "changePoint" : "N/A",
        "expandTrace" : "N/A",
        "artificial" : "N/A",
        "materiel" : "N/A",
        "dpcoi4M" : "",
        "analyticReport" : "",
        "layeredAudit" : "",
        "checkResult" : "",
        "naPending" : "",
        "otherInformation" : "",
        "analyticReportFileId" : "",
        "layeredAuditFileId" : "",
        "checkResultFileId" : "",
        "naPendingFileId" : "",
        "otherInformationFileId" : "",
        "firstDelay" : "0",
        "secondDelay" : "0",
        "thirdDelay" : "0",
        "fourthDelay" : "0",
        "delayLevel" : "0",
        "isDelay" : "0",
        "delayApplication" : "0"
    };

    $scope.validData = function(){
        var flag = true;
        if(!$.html5Validate.isAllpass($("#problemStatus"))){//状态
            return false;
        }
        if(!$.html5Validate.isAllpass($("#problemType"))){//问题类型
            return false;
        }
        if(!$.html5Validate.isAllpass($("#engineering"))){//工程
            return false;
        }
        if(!$.html5Validate.isAllpass($("#problemProgress"))){//问题进展
            return false;
        }
        if(!$.html5Validate.isAllpass($("#happenDate"))){//发生日期
            return false;
        }
        if(!$.html5Validate.isAllpass($("#vehicle"))){//车型
            return false;
        }
        if(!$.html5Validate.isAllpass($("#productNo"))){//品名
            return false;
        }
        if(!$.html5Validate.isAllpass($("#badContent"))){//不良内容
            return false;
        }
        if(!$.html5Validate.isAllpass($("#reportDate"))){//下次汇报时间
            return false;
        }
        var trackingLevel = $("#trackingLevel").val();
        if(!(trackingLevel == undefined || trackingLevel == null || trackingLevel == ""|| trackingLevel == "V")){
            if(!$.html5Validate.isAllpass($("#reasonForDelay"))){//延期原因及进展
                return false;
            }
        }
        if(!$.html5Validate.isAllpass($("#productLine"))){//生产线
            return false;
        }
        if(!$.html5Validate.isAllpass($("#severity"))){//严重度
            return false;
        }
        if(!$.html5Validate.isAllpass($("#occurrenceFrequency"))){//occurrenceFrequency
            return false;
        }
        if(!$.html5Validate.isAllpass($("#badQuantity"))){//不良数量
            return false;
        }
        if(!$.html5Validate.isAllpass($("#batch"))){//批次
            return false;
        }
        if(!$.html5Validate.isAllpass($("#happenShift"))){//发生班次
            return false;
        }
        if(!$.html5Validate.isAllpass($("#responsibleDepartment"))){//责任部门
            return false;
        }
        if(!$.html5Validate.isAllpass($("#recordPpm"))){//客户是否记录PPM
            return false;
        }
        if(!$.html5Validate.isAllpass($("#recordNum"))){//记录数量
            return false;
        }
        var persionLiableNames = $("#persionLiable").multiselect("MyValues");
        if(persionLiableNames == ""){
            alert("责任人不能为空！");
            return false;
        }
        return flag;
    };

    $("#rrProblemEditConfirm").click(function () {
        var flag = $scope.validData();
        if(!flag){
            return ;
        }

        var url = "";
        if($scope.action == "add"){
            url = BASE_URL+"/rrProblem/addRRProblem.do";
        }else {
            url = BASE_URL+"/rrProblem/updateRRProblem.do";
        }
        var persionLiableNames = $("#persionLiable").multiselect("MyValues");
        $scope.rrProblemEdit.rrProblem.persionLiable = persionLiableNames;
        $scope.rrProblemEdit.rrProblem.vehicle = $("#vehicle").val();
        $scope.rrProblemEdit.rrProblem.happenDate = $("#happenDate").val();
        $scope.rrProblemEdit.rrProblem.reportDate = $("#reportDate").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklist = $("#equipmentChecklist").val();
        $scope.rrProblemEdit.rrProblem.alwaysList = $("#alwaysList").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBook = $("#inspectionReferenceBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionBook = $("#inspectionBook").val();
        $scope.rrProblemEdit.rrProblem.education = $("#education").val();
        $scope.rrProblemEdit.rrProblem.firstDate = $("#firstDate").val();
        $scope.rrProblemEdit.rrProblem.secondDate = $("#secondDate").val();
        $scope.rrProblemEdit.rrProblem.thirdDate = $("#thirdDate").val();
        $scope.rrProblemEdit.rrProblem.fourthDate = $("#fourthDate").val();
        $scope.rrProblemEdit.rrProblem.closeConfirmTime = "";
        $scope.rrProblemEdit.rrProblem.badContent = $("#badContent").val();
        $scope.rrProblemEdit.rrProblem.reasonForDelay = $("#reasonForDelay").val();
        $scope.rrProblemEdit.rrProblem.temporary = $("#temporary").val();
        $scope.rrProblemEdit.rrProblem.rootCause = $("#rootCause").val();
        $scope.rrProblemEdit.rrProblem.permanentGame = $("#permanentGame").val();
        $scope.rrProblemEdit.rrProblem.effectVerification = $("#effectVerification").val();
        $scope.rrProblemEdit.rrProblem.productLine = $("#productLine").val().toUpperCase();

        $scope.rrProblemEdit.rrProblem.serialNumber = $("#serialNumber").val();
        $scope.rrProblemEdit.rrProblem.serialNumberFileId = $("#serialNumberFileId").val();
        $scope.rrProblemEdit.rrProblem.qualityWarningCardNumber = $("#qualityWarningCardNumber").val();
        $scope.rrProblemEdit.rrProblem.qualityWarningCardNumberFileId = $("#qualityWarningCardNumberFileId").val();
        $scope.rrProblemEdit.rrProblem.productScale = $("#productScale").val();
        $scope.rrProblemEdit.rrProblem.productScaleFileId = $("#productScaleFileId").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklist = $("#equipmentChecklist").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklistFileId = $("#equipmentChecklistFileId").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBook = $("#inspectionReferenceBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBookFileId = $("#inspectionReferenceBookFileId").val();
        $scope.rrProblemEdit.rrProblem.inspectionBook = $("#inspectionBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionBookFileId = $("#inspectionBookFileId").val();
        $scope.rrProblemEdit.rrProblem.education = $("#education").val();
        $scope.rrProblemEdit.rrProblem.educationFileId = $("#educationFileId").val();
        $scope.rrProblemEdit.rrProblem.analyticReport = $("#analyticReport").val();
        $scope.rrProblemEdit.rrProblem.analyticReportFileId = $("#analyticReportFileId").val();
        $scope.rrProblemEdit.rrProblem.layeredAudit = $("#layeredAudit").val();
        $scope.rrProblemEdit.rrProblem.layeredAuditFileId = $("#layeredAuditFileId").val();
        $scope.rrProblemEdit.rrProblem.checkResult = $("#checkResult").val();
        $scope.rrProblemEdit.rrProblem.checkResultFileId = $("#checkResultFileId").val();
        $scope.rrProblemEdit.rrProblem.naPending = $("#naPending").val();
        $scope.rrProblemEdit.rrProblem.naPendingFileId = $("#naPendingFileId").val();
        $scope.rrProblemEdit.rrProblem.otherInformation = $("#otherInformation").val();
        $scope.rrProblemEdit.rrProblem.otherInformationFileId = $("#otherInformationFileId").val();
        $scope.rrProblemEdit.rrProblem.pfmea = $("#pfmea").val();
        $scope.rrProblemEdit.rrProblem.pfmeaFileId = $("#pfmeaFileId").val();
        $scope.rrProblemEdit.rrProblem.cp = $("#cp").val();
        $scope.rrProblemEdit.rrProblem.cpFileId = $("#cpFileId").val();
        $scope.rrProblemEdit.rrProblem.standardBook = $("#standardBook").val();
        $scope.rrProblemEdit.rrProblem.standardBookFileId = $("#standardBookFileId").val();

        $.ajax({
            method:'post',
            url:url,
            data:$scope.rrProblemEdit.rrProblem,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    var message = result.message;
                    if(!(message == undefined || message == null || message == "")){
                        alert(result.message);
                    }
                    window.location.href = BASE_URL+"/rrProblem/getRRProblemListDlg.do?"+searchStr;
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    });

    $("#rrProblemDelayConfirm").click(function () {
        if($scope.rrProblemEdit.rrProblem.isDelay == "1"){
            alert("已延期，不能再次延期申请！");
            return ;
        }
        var flag = $scope.validData();
        if(!flag){
            return ;
        }

        var url = BASE_URL+"/rrProblem/updateDelayRRProblem.do";
        var persionLiableNames = $("#persionLiable").multiselect("MyValues");
        $scope.rrProblemEdit.rrProblem.persionLiable = persionLiableNames;
        $scope.rrProblemEdit.rrProblem.vehicle = $("#vehicle").val();
        $scope.rrProblemEdit.rrProblem.happenDate = $("#happenDate").val();
        $scope.rrProblemEdit.rrProblem.reportDate = $("#reportDate").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklist = $("#equipmentChecklist").val();
        $scope.rrProblemEdit.rrProblem.alwaysList = $("#alwaysList").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBook = $("#inspectionReferenceBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionBook = $("#inspectionBook").val();
        $scope.rrProblemEdit.rrProblem.education = $("#education").val();
        $scope.rrProblemEdit.rrProblem.firstDate = $("#firstDate").val();
        $scope.rrProblemEdit.rrProblem.secondDate = $("#secondDate").val();
        $scope.rrProblemEdit.rrProblem.thirdDate = $("#thirdDate").val();
        $scope.rrProblemEdit.rrProblem.fourthDate = $("#fourthDate").val();
        $scope.rrProblemEdit.rrProblem.closeConfirmTime = "";
        $scope.rrProblemEdit.rrProblem.badContent = $("#badContent").val();
        $scope.rrProblemEdit.rrProblem.reasonForDelay = $("#reasonForDelay").val();
        $scope.rrProblemEdit.rrProblem.temporary = $("#temporary").val();
        $scope.rrProblemEdit.rrProblem.rootCause = $("#rootCause").val();
        $scope.rrProblemEdit.rrProblem.permanentGame = $("#permanentGame").val();
        $scope.rrProblemEdit.rrProblem.effectVerification = $("#effectVerification").val();
        $scope.rrProblemEdit.rrProblem.productLine = $("#productLine").val().toUpperCase();

        $scope.rrProblemEdit.rrProblem.serialNumber = $("#serialNumber").val();
        $scope.rrProblemEdit.rrProblem.serialNumberFileId = $("#serialNumberFileId").val();
        $scope.rrProblemEdit.rrProblem.qualityWarningCardNumber = $("#qualityWarningCardNumber").val();
        $scope.rrProblemEdit.rrProblem.qualityWarningCardNumberFileId = $("#qualityWarningCardNumberFileId").val();
        $scope.rrProblemEdit.rrProblem.productScale = $("#productScale").val();
        $scope.rrProblemEdit.rrProblem.productScaleFileId = $("#productScaleFileId").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklist = $("#equipmentChecklist").val();
        $scope.rrProblemEdit.rrProblem.equipmentChecklistFileId = $("#equipmentChecklistFileId").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBook = $("#inspectionReferenceBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionReferenceBookFileId = $("#inspectionReferenceBookFileId").val();
        $scope.rrProblemEdit.rrProblem.inspectionBook = $("#inspectionBook").val();
        $scope.rrProblemEdit.rrProblem.inspectionBookFileId = $("#inspectionBookFileId").val();
        $scope.rrProblemEdit.rrProblem.education = $("#education").val();
        $scope.rrProblemEdit.rrProblem.educationFileId = $("#educationFileId").val();
        $scope.rrProblemEdit.rrProblem.analyticReport = $("#analyticReport").val();
        $scope.rrProblemEdit.rrProblem.analyticReportFileId = $("#analyticReportFileId").val();
        $scope.rrProblemEdit.rrProblem.layeredAudit = $("#layeredAudit").val();
        $scope.rrProblemEdit.rrProblem.layeredAuditFileId = $("#layeredAuditFileId").val();
        $scope.rrProblemEdit.rrProblem.checkResult = $("#checkResult").val();
        $scope.rrProblemEdit.rrProblem.checkResultFileId = $("#checkResultFileId").val();
        $scope.rrProblemEdit.rrProblem.naPending = $("#naPending").val();
        $scope.rrProblemEdit.rrProblem.naPendingFileId = $("#naPendingFileId").val();
        $scope.rrProblemEdit.rrProblem.otherInformation = $("#otherInformation").val();
        $scope.rrProblemEdit.rrProblem.otherInformationFileId = $("#otherInformationFileId").val();

        $.ajax({
            method:'post',
            url:url,
            data:$scope.rrProblemEdit.rrProblem,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    window.location.href = BASE_URL+"/rrProblem/getRRProblemListDlg.do?"+searchStr;
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    });

    $("#rrProblemEditCancle").click(function () {
        window.location.href = BASE_URL+"/rrProblem/getRRProblemListDlg.do?"+searchStr;
    });

    $scope.editInput = function(id){
        $("#inputModal").modal("show");
        $("#textAreaId").val(id);
        $("#inputText").val($("#"+id).val());
        $('#inputText').foucs();
    };

    $scope.completTextArea = function () {
        var id = $("#textAreaId").val();
        $("#"+id).val($("#inputText").val());
        $("#inputModal").modal("hide");
        $('#inputText').blur();
    };

    $scope.uploadFile = function (fileTimeInput, fileIdInput) {
        var path=$("#path").val();
        var html="<form method='post' id='excelForm' enctype='multipart/form-data' action='"+BASE_URL+"/rrProblem/uploadFile.do'>"+
            "<a class='uploadFile button button-primary button-rounded button-small' href='#'>" +
            "<input type='file' onchange='updateFileExcelChange(\""+fileTimeInput+"\",\""+fileIdInput+"\")' name='uploadFile' id='uploadFile'/><i class='glyphicon glyphicon-search'></i>浏览" +
            "</a>"+
            "<input type='hidden' name='rrProblemId' value='"+rrProblemId+"'/>"+
            "<input type='hidden' name='fileAttr' value='"+fileTimeInput+"'/>"+
        "</form>";
        myModal = new jBox('Modal', {
            width: 150,
            title: '上传文件',
            content: html,
            onCloseComplete:function(){
                myModal.destroy();
            }
        }).open();
    };

    $(document).ready(function() {

        $.ajax({
            method: 'post',
            url: BASE_URL+"/system/config/queryList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemEdit.dpcoiConfigList = result.dataMapList;
                    $scope.$apply();
                }
            }
        });

        $.ajax({
            method: 'post',
            url: BASE_URL+"/rrProblem/getPersionLiableList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemEdit.persionLiableList = result.persionLiableList;
                    for(var i = 0; i < $scope.rrProblemEdit.persionLiableList.length; i++){
                        var obj = $scope.rrProblemEdit.persionLiableList[i];
                        $("#persionLiable").append("<option value='"+obj.userName+"'>"+obj.userName+"</option>");
                    }
                    $("#persionLiable").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if($scope.action == "edit"){
                        $.ajax({
                            method: 'post',
                            url: BASE_URL+"/rrProblem/getRRProblem.do?id="+rrProblemId,
                            success: function (resultJson) {
                                var result = angular.fromJson(resultJson);
                                if (result.success) {
                                    $scope.rrProblemEdit.rrProblem = result.rrProblem;
                                    var persionLiableStr = $scope.rrProblemEdit.rrProblem.persionLiable;
                                    var arr = persionLiableStr.split(",");
                                    if (arr != null) {
                                        $('#persionLiable option').each(function(i,content){
                                            //alert(i+"***"+content.value);
                                            if($.inArray($.trim(content.value),arr)>=0){
                                                this.selected=true;
                                            }
                                        });
                                        $('#persionLiable').multiselect("refresh");
                                    }
                                    $scope.$apply();
                                }
                            }
                        });
                    }
                }
            }
        });

        $("input[data-type='dateType1']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                format: 'Y-m-d'
            });
        });

        $("input[data-type='dateType2']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                minDate : endDate,
                format: 'Y-m-d'
            });
        });

        $("input[data-type='dateType3']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                scrollMonth:false,
                showApplyButton: true,
                prevButton : true,
                applyButtonName:'N/A',
                format: 'Y-m-d'
            });
        });

        $.ajax({
            type : "POST",
            url : BASE_URL+"/dpcoiConfigVehicle/getDpcoiConfigVehicleList.do",
            success : function(result) {
                $('#vehicle').autocomplete({
                    minLength : 0,
                    source : result.dpcoiConfigVehicleList,
                    focus : function(event,	ui) {
                        $("#vehicle").val(ui.item.value);
                        return false;
                    },
                    select : function(event, ui) {
                        $('#vehicle').val(ui.item.value);
                        return false;
                    },
                    // 当智能提示框关闭后会触发此事件,ui是空对象
                    close : function(event,	ui) {

                    }
                });
            },
            error : function(jqXHR, textStatus,
                             errorThrown) {
                alert("系统出现异常!!");
            }
        });

    });
});

rrProblemEditApp.filter('myFilter', function() {
    return function(inputArray, configCodeId) {
        var array = [];
        for(var i = 0; i < inputArray.length ; i++){
            var obj = inputArray[i];
            var id = obj.configCodeId;
            if(id == configCodeId){
                array.push(obj);
            }
        }
        return array;
    };
});

angular.bootstrap(document, [ 'rrProblemEdit' ]);