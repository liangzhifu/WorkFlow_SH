//页面加载完成后，初始化页面组件
Ext.onReady(function() {
    var basicPanel = taskConfirm.initBasicPanel();
    var buttonPanel = taskConfirm.initButtonPanel();

    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,buttonPanel]
    });
});

//页面配置对象
var taskConfirm = (function() {

    return {
        initBasicPanel : function(){
            var items = new Array();
            var taskOrderJSON2 = taskOrderJSON.replace("\\", '\\\\');
            taskOrderJSON2 = taskOrderJSON2.replace(/\r\n/g,'</br>');
            taskOrderJSON2 = taskOrderJSON2.replace(/\n\r/g,'</br>');
            taskOrderJSON2 = taskOrderJSON2.replace(/\n/g,'</br>');
            var taskOrder = Ext.decode(taskOrderJSON2);
            var taskType  = taskOrder.taskType;
            var taskTypeInfoArray = taskType.taskTypeInfo;
            var len = taskTypeInfoArray.length;
            for (var i = 0; i < len; i++) {
                var taskTypeInfo = taskTypeInfoArray[i];
                var infoTypeId = taskTypeInfo.infoTypeId;
                var obj = generBasicObj(infoTypeId, taskTypeInfo, taskOrder.taskOrderInfoList);
                items[i] = obj;
            }
            var quality_confirm = "";
            var accept_confirm = "";
            var group_confirm = "";
            var taskConfirmOrderArray = taskOrder.taskConfirmOrderList;
            for(var i = 0; i < taskConfirmOrderArray.length; i++) {
                var taskConfirmOrder = taskConfirmOrderArray[i];
                if(taskConfirmOrder.runCode=="quality_confirm"){
                    quality_confirm = taskConfirmOrder.confirmUserName;
                }else if(taskConfirmOrder.runCode=="accept_confirm"){
                    accept_confirm = taskConfirmOrder.confirmUserName;
                }else if(taskConfirmOrder.runCode=="group_confirm"){
                    group_confirm = taskConfirmOrder.confirmUserName;
                }
            }
            for(var i = len; i > 4; i--){
                items[i] = items[i-1];
            }
            items[4] = {
                columnWidth:0.25,
                layout:'form',
                items:[{
                    xtype:'displayfield',
                    fieldLabel:"变更后品号",
                    name: 'order_change_after_product_no',
                    id: 'order_change_after_product_no',
                    value : taskOrder.changeAfterProductNo,
                    anchor:'95%'
                }]
            };
            items[len+1] = {
                columnWidth:0.25,
                layout:'form',
                items:[{
                    xtype:'datefield',
                    fieldLabel:'实际变更时间',
                    name: 'order_real_change_time',
                    id: 'order_real_change_time',
                    format:'Y-m-d',
                    maxValue : new Date(),
                    anchor:'95%'
                }]
            };
            var basicPanel = new Ext.FormPanel({
                region:'center',
                id:'basicForm',
                labelAlign: 'right',
                labelWidth :70,
                frame:true,
                title: '基本信息',
                bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
                layout:'column',
                items: items,
                autoScroll:true
            });
            return basicPanel;
        },

        initButtonPanel : function(){
            var buttonPanel = new Ext.FormPanel({
                    region:'south',
                    labelAlign: 'right',
                    labelWidth :70,
                    frame:true,
                    bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
                    height:40,
                    width:Ext.getBody().getSize().width-2,
                    layout:'form',
                    items: [{
                        layout:'column',
                        items:[{
                            xtype:'label',
                            columnWidth:0.4,
                            html:'&nbsp;&nbsp;'
                        },{
                            xtype: 'button',
                            text:'确定',
                            columnWidth:0.05,
                            icon: '/WorkFlow/images/submit.png',
                            listeners:{
                                "click":doConfirm
                            }
                        },{
                            xtype:'label',
                            columnWidth:0.05,
                            html:'&nbsp;&nbsp;'
                        },{
                            xtype: 'button',
                            text:'取消',
                            columnWidth:0.05,
                            icon: '/WorkFlow/images/close.png',
                            listeners:{
                                "click":doClose
                            }
                        }]
                    }]
                });

            return buttonPanel;
        }
    };
})();

function generBasicObj(infoTypeId, taskTypeInfo, taskOrderInfoArray){
    var taskTypeInfoId = taskTypeInfo.taskTypeInfoId;
    var taskInfoValue = "";
    for(var i = 0; i < taskOrderInfoArray.length; i++){
        var taskOrderInfo = taskOrderInfoArray[i];
        if(taskTypeInfoId == taskOrderInfo.taskTypeInfoId){
            taskInfoValue = taskOrderInfo.taskInfoValue;
        }
    }
    var obj = null;
    if(infoTypeId == 1){
        obj = {
            columnWidth:taskTypeInfo.infoLength,
            layout:'form',
            items:[{
                xtype:'displayfield',
                fieldLabel:taskTypeInfo.infoName,
                value: taskInfoValue,
                anchor:'95%'
            }]
        };
    }else if(infoTypeId == 2){
        obj = {
            columnWidth:taskTypeInfo.infoLength,
            layout:'form',
            items:[{
                xtype:'displayfield',
                fieldLabel:taskTypeInfo.infoName,
                value: taskInfoValue,
                anchor:'95%'
            }]
        };
    }else if(infoTypeId == 3){
        var items = new Array();
        var infoValueList = taskTypeInfo.infoValueList;
        var i = 0;
        var flag = false;
        for(i = 0; i < infoValueList.length; i++){
            var infoValue = infoValueList[i];
            var infoKey = infoValue.infoKey;
            if(infoKey == 1){
                flag = true;
            }
        }
        var checkedArray = new Array();
        var checkedInputValue = "";
        if(flag){
            var taskInfoValueArray = taskInfoValue.split("<<?><?>>");
            var checkedStr = taskInfoValueArray[0];
            if(!(checkedStr == undefined || checkedStr == null || checkedStr == "")){
                checkedArray = checkedStr.split(",");
            }
            checkedInputValue = taskInfoValueArray[1];
        }else {
            if(!(taskInfoValue == undefined || taskInfoValue == null || taskInfoValue == "")){
                checkedArray = taskInfoValue.split(",");
            }
        }
        for(i = 0; i < infoValueList.length; i++){
            var infoValue = infoValueList[i];
            var infoKey = infoValue.infoKey;
            var boxLabel = '';
            var checked = false;
            for(var j = 0; j < checkedArray.length; j++){
                if(infoValue.infoId == checkedArray[j]){
                    checked = true;
                }
            }
            if(infoKey == 1){
                boxLabel = infoValue.infoName+'（'+checkedInputValue+'）';
            }else {
                boxLabel = infoValue.infoName;
            }
            var item = {
                boxLabel : boxLabel,
                inputValue : infoValue.infoId,
                checked : checked
            };
            items[i] = item;
        }
        obj = {
            columnWidth:taskTypeInfo.infoLength,
            layout:'form',
            items:[{
                xtype:'checkboxgroup',
                disabled : true,
                fieldLabel:taskTypeInfo.infoName,
                name: 'order_'+taskTypeInfo.taskTypeInfoId,
                id: 'order_'+taskTypeInfo.taskTypeInfoId,
                items : items,
                anchor:'95%'
            }]
        };
    }else {
        obj = {
            columnWidth:taskTypeInfo.infoLength,
            layout:'form',
            items:[{
                xtype:'displayfield',
                fieldLabel:taskTypeInfo.infoName,
                value: taskInfoValue,
                anchor:'95%'
            }]
        };
    }
    return obj;
}

function doConfirm(){
    Ext.Msg.confirm("确认框", "确定提交时间变更时间！：", function (btn) {
        if (btn == 'yes') {
            Ext.Ajax.request({
                url: contextPath + '/taskOrder/confirmRealChangeTime.do',
                async: false,
                waitTitle: '提示',
                waitMsg: '请稍后,正在提交数据...',
                params: {
                    orderId: taskOrderId,
                    realChangeTime: Ext.getCmp('order_real_change_time').value
                },
                success: function (response, action) {
                    var responseText = Ext.decode(response.responseText);
                    if (responseText.success) {
                        alert("实际变更时间成功！");
                        parent.doQry();
                        parent.closeCreateWin();
                    } else {
                        alert(responseText.message);
                    }
                },
                failure: function (e) {
                    Ext.Msg.alert('提示', '确认失败！');
                }
            });
        }
    });
}

function doClose(){
    parent.closeCreateWin();
}