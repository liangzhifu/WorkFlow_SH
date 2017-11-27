<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理页面</title>
    <%@include file="../../common/css.jsp"%>
    <%@include file="../../common/js.jsp"%>
</head>
<body>
    <div>
        <ul id="ztree" class="ztree">

        </ul>
    </div>

    <!--组织增加-->
    <div id="addModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="addModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width:300px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h3 id="addModalLabel">新增组织</h3>
            </div>
            <form class="form-inline" id="addOrg" >
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label class="control-label" for="orgName">组织名称：</label>
                            <input type="text" id="orgName" name="orgName"
                                   class="form-control-order form-control clean" required="required">
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-footer">
                        <button type="button" id="btnAddOrg"
                                class="btn btn-small btn-primary">确定</button>
                        <button type="button"
                                class="btn btn-small btn-primary" data-dismiss="modal">取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--组织增加-->

</body>
    <script src="<%=request.getContextPath()%>/kirikae/js/system/org/systemOrg.js"></script>
</html>