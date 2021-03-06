//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskDetail.initBasicPanel(); 
	var mainPanel = taskDetail.initMainPanel();
	var buttonPanel = taskDetail.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskDetail = (function() {
	 
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
            var taskOrderId = taskOrder.orderId;
            if (taskOrderId > 1044){
                var taskTypeInfoArray2 = new Array();
                var taskTypeInfo5 = null;
                for (var i = 0; i < taskTypeInfoArray.length; i++) {
                    var taskTypeInfo = taskTypeInfoArray[i];
                    var taskTypeInfoId = taskTypeInfo.taskTypeInfoId;
                    if (taskTypeInfoId == 5) {
                        taskTypeInfo5 = taskTypeInfo;
                    } else {
                        taskTypeInfoArray2.push(taskTypeInfo);
                    }
                }
                var taskTypeInfoArray3 = new Array();
                for (var i = 0; i < taskTypeInfoArray2.length; i++) {
                    var taskTypeInfo = taskTypeInfoArray2[i];
                    var taskTypeInfoId = taskTypeInfo.taskTypeInfoId;
                    if (taskTypeInfoId == 6) {
                        var infoValueList = taskTypeInfo5.infoValueList;
                        for(var j = 0; j < infoValueList.length; j++){
                            taskTypeInfo.infoValueList.push(infoValueList[j]);
                        }
                        taskTypeInfoArray3.push(taskTypeInfo);
                    } else {
                        taskTypeInfoArray3.push(taskTypeInfo);
                    }
                }
                taskTypeInfoArray = taskTypeInfoArray3;
            }
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
			var taskDetailOrderArray = taskOrder.taskConfirmOrderList;
			for(var i = 0; i < taskDetailOrderArray.length; i++) {
				var taskDetailOrder = taskDetailOrderArray[i];
				if(taskDetailOrder.runCode=="quality_confirm"){
					quality_confirm = taskDetailOrder.confirmUserName;
				}else if(taskDetailOrder.runCode=="accept_confirm"){
					accept_confirm = taskDetailOrder.confirmUserName;
				}else if(taskDetailOrder.runCode=="group_confirm"){
					group_confirm = taskDetailOrder.confirmUserName;
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
                labelWidth: 120,
                items:[{
                    xtype:'displayfield',
                    fieldLabel:'实际变更时间',
                    name: 'order_real_change_time',
                    id: 'order_real_change_time',
                    value : taskOrder.realChangeTime,
                    anchor:'95%'
                }]
            };
            len = len + 2;
			items[len] = { 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'承认',
			            value:quality_confirm,
			            anchor:'100%'
		            }]
            	};
			items[len+1] = { 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'确认',
			            value:group_confirm,
			            anchor:'100%'
		            }]
            	};
			items[len+2] = { 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'作成', 
			            value:accept_confirm,
			            anchor:'100%'
		            }]
            	};
	        var basicPanel = new Ext.FormPanel({
	        	region:'north',
	        	id:'basicForm',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,  
	            title: '基本信息',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:220,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: items,
	            autoScroll:true
	        });
	        return basicPanel;
	    },

	    initMainPanel : function(){
	    	var dataArray = new Array();
	    	var taskOrderJSON2 = taskOrderJSON.replace("\\", '\\\\');
			taskOrderJSON2 = taskOrderJSON2.replace(/\r\n/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n\r/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n/g,'</br>');
			var taskOrder = Ext.decode(taskOrderJSON2);
			var taskType  = taskOrder.taskType;
	    	var taskTachArray = taskType.taskTache;
			for(var i = 0; i < taskTachArray.length; i++){
				var taskTache = taskTachArray[i];
				var data = generMainObj(taskTache, taskOrder.taskWoOrderList);
				dataArray[i] = data;
			}
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "taskTacheId", "orgName", "detail", "staffName", "completeDate" ],
	    		data : dataArray
	    	});
			var cm = new Ext.grid.ColumnModel([ {
				header : "部门/科室",
				dataIndex : "taskTacheId",
				hidden : true
			},{
				header : "部门/科室",
				dataIndex : "orgName",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "根据变化内容确认本部门/科室对应项目清单",
				dataIndex : "detail",
				width : Ext.getBody().getSize().width * 0.74,
			    renderer: function(value, meta, record) {    
			         meta.attr = 'style="white-space:normal;"';     
			         return value;     
			    }  
			}, {
				header : "担当",
				dataIndex : "staffName",
				width : Ext.getBody().getSize().width * 0.06
			}, {
				header : "预计完成日期",
				dataIndex : "completeDate",
				width : Ext.getBody().getSize().width * 0.08
			} ]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "mainDataGrid",
				region : 'center',
				title : "详细数据",
				cm : cm,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				border : false,
				sortable : false,
				store : dataStore
			});
			return mainGrid;
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
                         text:'取消',
                         columnWidth:0.05,
                         icon: contextPath  + '/images/close.png',
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

function generMainObj(taskTache, taskWoOrderArray){
	var taskWoOrder = null;
	var readOnly = true;
	for(var m = 0; m < taskWoOrderArray.length; m++){
		var temptaskWoOrder = taskWoOrderArray[m];
		if(taskTache.tacheId == temptaskWoOrder.tacheId){
			taskWoOrder = temptaskWoOrder;
		}
	}
	var obj = new Array();
	obj[0] = taskTache.tacheId;
	obj[1] = taskTache.orgName;
	var htmlStr = "";
	var taskTacheInfoArray = taskTache.taskTacheInfo;
	for(var i = 0; i < taskTacheInfoArray.length; i++){
		var taskTacheInfo = taskTacheInfoArray[i];
		var infoTypeId = taskTacheInfo.infoTypeId;
		
		var taskWoOrderInfoArray = taskWoOrder.taskWoOrderInfoList;
		var taskWoOrderInfo = null;
		for(var n = 0; n < taskWoOrderInfoArray.length; n++){
			var tempTaskWoOrderInfo = taskWoOrderInfoArray[n];
			if(taskTacheInfo.taskTacheInfoId == tempTaskWoOrderInfo.taskTacheInfoId){
				taskWoOrderInfo = tempTaskWoOrderInfo;
			}
		}
		
		if(infoTypeId == 1){
			
		}else if(infoTypeId == 2){
			
		}else if(infoTypeId == 3){
			var infoValueList = taskTacheInfo.infoValueList;
			var flag = false;
			var j = 0;
			for(j = 0; j < infoValueList.length; j++){
				var infoValue = infoValueList[j];
				var infoKey = infoValue.infoKey;
				if(infoKey == 1) flag = true;
			}
			var woInfoValue = taskWoOrderInfo.woInfoValue
			var checkedArray = new Array();
			var checkedInputValue = "";
			if(flag){
				var woInfoValueArray = woInfoValue.split("<<?><?>>");
				var checkedStr = woInfoValueArray[0];
				if(!(checkedStr == undefined || checkedStr == null || checkedStr == "")){
					checkedArray = checkedStr.split(",");
				}
				checkedInputValue = woInfoValueArray[1];
			}else {
				if(!(woInfoValue == undefined || woInfoValue == null || woInfoValue == "")){
					checkedArray = woInfoValue.split(",");
				}
			}
			for(j = 0; j < infoValueList.length; j++){
				var infoValue = infoValueList[j];
				var infoKey = infoValue.infoKey;
				var checked = "";
				for(var l = 0; l < checkedArray.length; l++){
					if(infoValue.infoId == checkedArray[l]){
						checked = "checked";
					}
				}
				if(infoKey == 1){
					htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.id+'">'+infoValue.infoName+'&nbsp;';
					htmlStr += '（'+checkedInputValue+'）&nbsp;&nbsp;';
				}else {
					htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.id+'">'+infoValue.infoName+'&nbsp;&nbsp;';
				}
			}
		}else {
			
		}	
	}
	obj[2] = htmlStr;
	if(taskWoOrder.replyUserName){
		obj[3] = taskWoOrder.replyUserName;
	}else {
		obj[3] = "";
	}
	obj[4] = taskWoOrder.requireCompleteTimeStr;
	return obj;
}

function doClose(){
	window.close();
}
