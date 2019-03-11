package com.success.alteration.constant;

public interface Url {

    String BASE = "/alteration";

    String ORDER = "/order";
    String HISTORY = "/history";

    String ORDER_ADDOREDITDIALOG = BASE + ORDER + "/getAddOrEditDialog.do";
    String ORDER_DETAILDIALOG = BASE + ORDER + "/getDetailDialog.do";
    String ORDER_GET = BASE + ORDER + "/getOrder.do";
    String ORDER_ADD = BASE + ORDER + "/add.do";
    String ORDER_EDIT = BASE + ORDER + "/edit.do";
    String ORDER_TODODIALOG = BASE + ORDER + "/getTodoDialog.do";
    String ORDER_TOVOID = BASE + ORDER + "/toVoid.do";

    //历史
    String HISTORY_DETAILDIALOG = BASE + HISTORY + "/getDetailDialog.do";
    String HISTORY_GET = BASE + HISTORY + "/getOrder.do";

}
