var addTreeNode = null;

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

    callback : {
        beforeDrag: zTreeBeforeDrag,
        beforeRemove : zTreeBeforeRemove,
        beforeRename : zTreeBeforeRename
    },

    view : {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        showLine : true,
        dblClickExpand : true,
        selectedMulti: false
    },

    edit : {
        enable : true,
        removeTitle : "删除",
        renameTitle : "修改名称",
        showRemoveBtn : zTreeShowRemoveBtn,
        showRenameBtn : true
    }
};

function zTreeBeforeDrag(treeId, treeNodes) {
    return false;
}

function zTreeShowRemoveBtn(treeId, treeNode) {
    if(treeNode.orgType == 0 || treeNode.orgType == 1){
        return false;
    }else {
        return true;
    }
}

function zTreeBeforeRemove(treeId, treeNode) {
    var con = confirm("确定删除组织：（"+treeNode.orgName+"）！");
    if (con == true){
        $.ajax({
            type : "POST",
            async : false,
            url : BASE_URL + "/system/org/delete.do",
            data :{"id":treeNode.id},
            success:function(result){
                result = angular.fromJson(result);
                if(result.success){
                    return true;
                }else{
                    return false;
                }
            }
        });
    }else {
        return false;
    }
}

function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
    if(newName.length == 0){
        var zTree = $.fn.zTree.getZTreeObj("ztree");
        zTree.cancelEditName();
        alert("组织名称不能为空.");
        return false;
    }
    var con = confirm("确定修改组织名称！");
    if (con == true){
        $.ajax({
            type : "POST",
            async : false,
            url : BASE_URL + "/system/org/edit.do",
            data :{orgName : newName, "id":treeNode.id},
            success:function(result){
                result = angular.fromJson(result);
                if(result.success){
                    return true;
                }else{
                    var zTree = $.fn.zTree.getZTreeObj("ztree");
                    zTree.cancelEditName();
                    alert("无法修改组织名称，请联系管理员！");
                    return false;
                }
            }
        });
    }else {
        return false;
    }
}

function zTreeOnRename(event, treeId, treeNode, isCancel) {
    alert(treeNode.tId + ", " + treeNode.name);
}

function addHoverDom(treeId, treeNode) {
    if(treeNode.orgType == 1 || treeNode.orgType == 2) {
        addTreeNode = treeNode;
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加子部门' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);

        if (btn) btn.bind("click", function () {
            $("#orgName").val("");
            $("#addModal").modal("show");
            return false;
        });
    }
}

$("#btnAddOrg").click(function () {
    if (!$.html5Validate.isAllpass($("#orgName"))) {
        return false;
    }
    var con = confirm("确定增加组织！");
    if (con == true){
        $.ajax({
            type : "POST",
            async : false,
            url : BASE_URL + "/system/org/add.do",
            data :{orgName : $("#orgName").val(), "parentId":addTreeNode.id, orgType:addTreeNode.orgType+1},
            success:function(result){
                result = angular.fromJson(result);
                if(result.success){
                    var zTree = $.fn.zTree.getZTreeObj("ztree");
                    zTree.addNodes(addTreeNode, {id:result.id, parentId:addTreeNode.id, orgName:$("#orgName").val(), orgType:addTreeNode.orgType+1});
                    $("#addModal").modal("hide");
                }else{
                    alert("无法添加新部门，请联系管理员！");
                }
            }
        });
    }
});

// 用于当鼠标移出节点时，隐藏用户自定义控件
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
}

$(document).ready(function(){
    $.ajax({
        method: 'post',
        url: BASE_URL + "/system/org/queryUserOrgTree.do",
        data: {},
        async: false,
        success: function (zNodesJson) {
            var zNodes = angular.fromJson(zNodesJson);
            $.fn.zTree.init($("#ztree"), zSetting, zNodes.dataMapList);
        }
    });
});
