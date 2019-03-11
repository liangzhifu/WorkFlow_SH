package com.success.kirikae.constant;

public interface Url {
    String BASE = "/kirikae";

    String ORDER = "/order";
    String QUESTION = "/question";
    String ORGQUESTION= "/orgQuestion";
    String STAND = "/stand";
    String INSTRUCTION  = "/instruction";
    String CONFIRMATION = "/confirmation";
    String WOORDER = "/woOrder";
    String WOORDERATTR = "/woOrderAttr";
    String AGENCY = "/agency";
    String HISTORY = "/history";
    String PROCEDURE = "/procedure";

    //定单
    String ORDER_ADD_DIALOG = BASE + ORDER + "/getAddDialog.do";
    String ORDER_ADD = BASE + ORDER + "/add.do";
    String ORDER_EDIT = BASE + ORDER + "/edit.do";
    String ORDER_DETAIL = BASE + ORDER + "/detail.do";
    String ORDER_PAGEINOF = BASE + ORDER + "/queryPageInfo.do";
    String ORDER_PAGEINOF_DIALOG = BASE + ORDER + "/getPageInfoDialog.do";
    String ORDER_VALID_REPEAT = BASE + ORDER + "/validRepeat.do";
    String ORDER_VALID_DESIGNCHANGETIMING_DIALOG = BASE + ORDER + "/getDesignChangeTimingDialog.do";
    String ORDER_VALID_DESIGNCHANGETIMING = BASE + ORDER + "/designChangeTiming.do";
    String ORDER_EXPORT_CONFIRMBOOK = BASE + ORDER + "/doExportConfirmBookPDF.do";
    String ORDER_EXPORT_HANDMATCH = BASE + ORDER + "/doExportHandMatchPDF.do";
    String ORDER_GENERATE_FORU_DIALOG = BASE + ORDER + "/getGenerateFourDialog.do";
    String ORDER_GENERATE_FORU = BASE + ORDER + "/generateFour.do";
    String ORDER_INSTRUCTION_CONFIRMATION_DIALOG = BASE + ORDER + "/getInstructionConfirmationDialog.do";
    //历史记录
    String HISTORY_PAGEINOF = BASE + HISTORY + "/queryPageInfo.do";
    String HISTORY_PAGEINOF_DIALOG = BASE + HISTORY + "/getPageInfoDialog.do";

    //待办
    String ORDER_AGENCY_PAGEINOF = BASE + AGENCY + "/queryPageInfo.do";
    String ORDER_AGENCY_DIALOG = BASE + AGENCY + "/getDialog.do";

    //工单
    String WOORDER_STAND_CLOSE_ORG = BASE + WOORDER + "/getStandCloseOrg.do";

    //工单项目
    String WOORDERATTR_ADD_DIALOG = BASE + WOORDERATTR + "/getAddDialog.do";
    String WOORDERATTR_CONFIRM_DIALOG = BASE + WOORDERATTR + "/getConfirmDialog.do";
    String WOORDERATTR_REVIEW_DIALOG = BASE + WOORDERATTR + "/getReviewDialog.do";
    String WOORDERATTR_UPLOAD_DIALOG = BASE + WOORDERATTR + "/getUploadDialog.do";
    String WOORDERATTR_STANDCLOSE_DIALOG = BASE + WOORDERATTR + "/getStandCloseDialog.do";
    String WOORDERATTR_STANDCLOSE_VALID_DIALOG = BASE + WOORDERATTR + "/getStandCloseValidDialog.do";
    String WOORDERATTR_CHILD_UPLOAD_DIALOG = BASE + WOORDERATTR + "/getChildUploadDialog.do";
    String WOORDERATTR_VIEW_DIALOG = BASE + WOORDERATTR + "/getViewDialog.do";
    String WOORDERATTR_LIST_ADD = BASE + WOORDERATTR + "/getAddList.do";
    String WOORDERATTR_LIST_BYORDERID = BASE + WOORDERATTR + "/getListByOrderId.do";
    String WOORDERATTR_LIST_BYUSERID = BASE + WOORDERATTR + "/getListByUserId.do";
    String WOORDERATTR_LIST_AGREEMENT = BASE + WOORDERATTR + "/getAgreementList.do";
    String WOORDERATTR_ADD = BASE + WOORDERATTR + "/add.do";
    String WOORDERATTR_CONFIRM = BASE + WOORDERATTR + "/confirm.do";
    String WOORDERATTR_REVIEW = BASE + WOORDERATTR + "/review.do";
    String WOORDERATTR_UPLOAD = BASE + WOORDERATTR + "/upload.do";
    String WOORDERATTR_STANDCLOSE = BASE + WOORDERATTR + "/standClose.do";
    String WOORDERATTR_STANDCLOSE_VALID = BASE + WOORDERATTR + "/standCloseValid.do";
    String WOORDERATTR_REVIEW_ADD = BASE + WOORDERATTR + "/reviewAdd.do";
    String WOORDERATTR_REVIEW_DELETE = BASE + WOORDERATTR + "/reviewDelete.do";

    //切替问题点
    String QUESTION_DIALOG = BASE + QUESTION + "/getDialog.do";
    String QUESTION_ADD = BASE + QUESTION + "/add.do";
    String QUESTION_DELETE = BASE + QUESTION + "/delete.do";
    String QUESTION_PAGEINFO = BASE + QUESTION + "/queryPageInfo.do";

    //组织关联问题点
    String ORGQUESTION_DIALOG = BASE + ORGQUESTION + "/getDialog.do";
    String ORGQUESTION_ADD = BASE + ORGQUESTION + "/add.do";
    String ORGQUESTION_DELETE = BASE + ORGQUESTION + "/delete.do";
    String ORGQUESTION_LIST = BASE + ORGQUESTION + "/queryList.do";
    String ORGQUESTION_ADDLIST = BASE + ORGQUESTION + "/queryAddList.do";
    String ORGQUESTION_ADDWOLIST = BASE + ORGQUESTION + "/queryAddWoOrderOrgList.do";

    //立合
    String STAND_DIALOG = BASE + STAND + "/getDialog.do";
    String STAND_CHECKED_DIALOG = BASE + STAND + "/getCheckedDialog.do";
    String STAND_APPROVED_DIALOG = BASE + STAND + "/getApprovedDialog.do";
    String STAND_ADD = BASE + STAND + "/add.do";
    String STAND_CHECKED = BASE + STAND + "/checked.do";
    String STAND_REFUSE = BASE + STAND + "/refuse.do";
    String STAND_APPROVED = BASE + STAND + "/approved.do";

    //切替指示书
    String INSTRUCTION_DIALOG = BASE + INSTRUCTION + "/getDialog.do";
    String INSTRUCTION_CHECKED_DIALOG = BASE + INSTRUCTION + "/getCheckedDialog.do";
    String INSTRUCTION_APPROVED_DIALOG = BASE + INSTRUCTION + "/getApprovedDialog.do";
    String INSTRUCTION_ADD = BASE + INSTRUCTION + "/add.do";
    String INSTRUCTION_GET = BASE + INSTRUCTION + "/get.do";
    String INSTRUCTION_CHECKED = BASE + INSTRUCTION + "/checked.do";
    String INSTRUCTION_APPROVED = BASE + INSTRUCTION + "/approved.do";

    //切替指示书
    String CONFIRMATION_DIALOG = BASE + CONFIRMATION + "/getDialog.do";
    String CONFIRMATION_CHECKED_DIALOG = BASE + CONFIRMATION + "/getCheckedDialog.do";
    String CONFIRMATION_APPROVED_DIALOG = BASE + CONFIRMATION + "/getApprovedDialog.do";
    String CONFIRMATION_ADD = BASE + CONFIRMATION + "/add.do";
    String CONFIRMATION_GET = BASE + CONFIRMATION + "/get.do";
    String CONFIRMATION_CHECKED = BASE + CONFIRMATION + "/checked.do";
    String CONFIRMATION_APPROVED = BASE + CONFIRMATION + "/approved.do";

    //流程
    String PROCEDURE_ORDER_LIST = BASE + PROCEDURE + "/getKirikaeOrderProcedureList.do";

}
