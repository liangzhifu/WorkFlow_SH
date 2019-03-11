package com.success.kirikae.order.service.impl;

import com.dpcoi.file.dao.FileUpload2Dao;
import com.dpcoi.file.domain.FileUpload;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.success.kirikae.confirmation.dao.KirikaeConfirmationDao;
import com.success.kirikae.confirmation.domain.KirikaeConfirmation;
import com.success.kirikae.instruction.dao.KirikaeInstructionDao;
import com.success.kirikae.instruction.domain.KirikaeInstruction;
import com.success.kirikae.order.constant.KirikaeOrderEnum;
import com.success.kirikae.order.dao.KirikaeOrderChangeContentDao;
import com.success.kirikae.order.dao.KirikaeOrderDao;
import com.success.kirikae.order.dao.KirikaeOrderPartsNumberDao;
import com.success.kirikae.order.dao.KirikaeResumeDao;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.domain.KirikaeResume;
import com.success.kirikae.order.service.ExportKirikaeOrderPdfService;
import com.success.kirikae.procedure.constant.ProcedureEnum;
import com.success.kirikae.procedure.dao.KirikaeOrderProcedureDao;
import com.success.kirikae.stand.dao.KirikaeStandCloseDao;
import com.success.kirikae.wo.dao.KirikaeWoOrderAttrDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author lzf
 **/
@Service("exportKirikaeOrderPdfService")
public class ExportKirikaeOrderPdfServiceImpl implements ExportKirikaeOrderPdfService {

    @Resource(name = "kirikaeOrderDao")
    private KirikaeOrderDao kirikaeOrderDao;

    @Resource(name = "kirikaeWoOrderAttrDao")
    private KirikaeWoOrderAttrDao kirikaeWoOrderAttrDao;

    @Resource(name = "kirikaeStandCloseDao")
    private KirikaeStandCloseDao kirikaeStandCloseDao;

    @Resource(name = "kirikaeOrderProcedureDao")
    private KirikaeOrderProcedureDao kirikaeOrderProcedureDao;

    @Resource(name = "kirikaeOrderChangeContentDao")
    private KirikaeOrderChangeContentDao kirikaeOrderChangeContentDao;

    @Resource(name = "kirikaeOrderPartsNumberDao")
    private KirikaeOrderPartsNumberDao kirikaeOrderPartsNumberDao;

    @Resource(name = "kirikaeInstructionDao")
    private KirikaeInstructionDao kirikaeInstructionDao;

    @Resource(name = "kirikaeConfirmationDao")
    private KirikaeConfirmationDao kirikaeConfirmationDao;

    @Resource(name = "kirikaeResumeDao")
    private KirikaeResumeDao kirikaeResumeDao;

    @Resource(name = "fileUpload2Dao")
    private FileUpload2Dao fileUploadDao;

    @Override
    public String exportConfirmBook(Integer orderId, String path) throws Exception {
        String pdfName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String randNum = UUID.randomUUID().toString();
        pdfName = sdf.format(date)+"_"+randNum+".pdf";

        float marginLeft = 10f;
        float marginRight = 10f;
        float marginTop = 40f;
        float marginBottom = 10f;
        //问题+结论的高度
        float totalHeigth = 540f;

        KirikaeOrder kirikaeOrder = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);

        //A4 宽595
        Document document = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
        PdfWriter.getInstance(document, new FileOutputStream(path + "stdout/" + pdfName));
        document.open();

        List<Map<String, Object>> procedureList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureMapListByOrderId(orderId);
        PdfPTable titleTable = this.generateConfirmBookTitle(kirikaeOrder, procedureList);
        document.add(titleTable);

        List<Map<String, Object>> projectList = this.kirikaeWoOrderAttrDao.selectKirikaeWoOrderAttrMapListByOrderId(orderId);
        Table projetTable = this.geterateProjectTable(projectList, procedureList);
        projetTable.setWidth(90);
        projetTable.setAlignment(Element.ALIGN_CENTER);
        document.add(projetTable);

        //加入空行
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese18 = new Font(bfChinese, 2, Font.BOLD);
        Paragraph blankRow2 = new Paragraph(2f, " ", FontChinese18);
        document.add(blankRow2);

        List<Map<String, Object>> agreementList = this.kirikaeStandCloseDao.selectKirikaeStandCloseMapListByOrderId(orderId);
        Table agreementTable = this.geterateAgreementTable(agreementList);
        agreementTable.setWidth(90);
        agreementTable.setAlignment(Element.ALIGN_CENTER);
        document.add(agreementTable);

        document.close();
        return pdfName;
    }

    /**
     * 做成、承认、确认表格
     * @param procedureList 流程列表
     * @return 返回结果
     * @throws Exception 异常
     */
    private PdfPTable generateOperateTable(List<Map<String, Object>> procedureList) throws Exception {
        //担当
        String prepared = "";
        //确认
        String checked = "";
        //承认
        String apprvoed = "";
        if (procedureList != null && procedureList.size() > 0){
            for(Map<String, Object> map : procedureList) {
                Integer procedureCode = (Integer) map.get("procedureCode");
                if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_REVIEW.getCode().intValue()){
                    prepared = (String)map.get("userName");
                }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_CHECKED.getCode().intValue()){
                    checked = (String)map.get("userName");
                }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_STAND_CLOSE_APPROVED.getCode().intValue()){
                    apprvoed = (String)map.get("userName");
                }
            }
        }

        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);

        float trHeight1 = 25f;
        float trHeight2 = 40f;

        float[] columnWidth = {35f, 35f, 35f};
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);

        Paragraph paragraph_1_1 = new Paragraph("承认", font);
        paragraph_1_1.setAlignment(1);
        PdfPCell cell_1_1 = new PdfPCell();
        cell_1_1.setUseAscender(true);
        cell_1_1.setUseDescender(true);
        cell_1_1.addElement(paragraph_1_1);
        cell_1_1.setFixedHeight(trHeight1);
        cell_1_1.setVerticalAlignment(5);
        cell_1_1.setHorizontalAlignment(1);
        table.addCell(cell_1_1);

        Paragraph paragraph_1_2 = new Paragraph("确认", font);
        paragraph_1_2.setAlignment(1);
        PdfPCell cell_1_2 = new PdfPCell();
        cell_1_2.setUseAscender(true);
        cell_1_2.setUseDescender(true);
        cell_1_2.addElement(paragraph_1_2);
        cell_1_2.setFixedHeight(trHeight1);
        cell_1_2.setVerticalAlignment(5);
        cell_1_2.setHorizontalAlignment(1);
        table.addCell(cell_1_2);

        Paragraph paragraph_1_3 = new Paragraph("做成", font);
        paragraph_1_3.setAlignment(1);
        PdfPCell cell_1_3 = new PdfPCell();
        cell_1_3.setUseAscender(true);
        cell_1_3.setUseDescender(true);
        cell_1_3.addElement(paragraph_1_3);
        cell_1_3.setFixedHeight(trHeight1);
        cell_1_3.setVerticalAlignment(5);
        cell_1_3.setHorizontalAlignment(1);
        table.addCell(cell_1_3);

        Paragraph paragraph_2_1 = new Paragraph(prepared, font);
        paragraph_2_1.setAlignment(1);
        PdfPCell cell_2_1 = new PdfPCell();
        cell_2_1.setUseAscender(true);
        cell_2_1.setUseDescender(true);
        cell_2_1.addElement(paragraph_2_1);
        cell_2_1.setFixedHeight(trHeight2);
        cell_2_1.setVerticalAlignment(5);
        cell_2_1.setHorizontalAlignment(1);
        table.addCell(cell_2_1);

        Paragraph paragraph_2_2 = new Paragraph("/", font);
        paragraph_2_2.setAlignment(1);
        PdfPCell cell_2_2 = new PdfPCell();
        cell_2_2.setUseAscender(true);
        cell_2_2.setUseDescender(true);
        cell_2_2.addElement(paragraph_2_2);
        cell_2_2.setFixedHeight(trHeight2);
        cell_2_2.setVerticalAlignment(5);
        cell_2_2.setHorizontalAlignment(1);
        table.addCell(cell_2_2);

        Paragraph paragraph_2_3 = new Paragraph(apprvoed, font);
        paragraph_2_3.setAlignment(1);
        PdfPCell cell_2_3 = new PdfPCell();
        cell_2_3.setUseAscender(true);
        cell_2_3.setUseDescender(true);
        cell_2_3.addElement(paragraph_2_3);
        cell_2_3.setFixedHeight(trHeight2);
        cell_2_3.setVerticalAlignment(5);
        cell_2_3.setHorizontalAlignment(1);
        table.addCell(cell_2_3);

        return table;
    }

    /**
     * 获取切替单信息表格
     * @param kirikaeOrder 切替单实体
     * @return 返回结果
     * @throws Exception 异常
     */
    private PdfPTable geterateLeftHeaderTable(KirikaeOrder kirikaeOrder) throws Exception{
        float[] columnWidth = {400f};
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);

        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);
        Font fontHeader = new Font(bfChinese, 14, Font.BOLD);

        Integer orderType = kirikaeOrder.getKirikaeOrderType();
        String title = "";
        if(orderType.intValue() == KirikaeOrderEnum.KirikaeOrderTypeEnum.ORDER_TYPE_ONE.getCode().intValue()){
            title = "<<设变切替确认书>>-(量产前) Check list";
        }else if (orderType.intValue() == KirikaeOrderEnum.KirikaeOrderTypeEnum.ORDER_TYPE_TWO.getCode().intValue()){
            title = "<<设变切替确认书>>-(量产后) Check list";
        }
        Paragraph paragraph1 = new Paragraph(title, fontHeader);
        paragraph1.setAlignment(1);
        PdfPCell cell1 = new PdfPCell();
        cell1.setUseAscender(true);
        cell1.setUseDescender(true);
        cell1.setHorizontalAlignment(1);
        cell1.addElement(paragraph1);
        cell1.setBorder(0);
        table.addCell(cell1);

        String designChangeNo = kirikaeOrder.getDesignChangeNo();
        if (designChangeNo == null) {
            designChangeNo = "";
        }
        Paragraph paragraph2 = new Paragraph("信息来源："+designChangeNo, font);
        PdfPCell cell2 = new PdfPCell();
        cell2.setUseAscender(true);
        cell2.setUseDescender(true);
        cell2.addElement(paragraph2);
        cell2.setBorder(0);
        table.addCell(cell2);

        String vehicleName = kirikaeOrder.getVehicleName();
        if (vehicleName == null) {
            vehicleName = "";
        }
        Paragraph paragraph3 = new Paragraph("变更事项："+vehicleName, font);
        PdfPCell cell3 = new PdfPCell();
        cell3.setUseAscender(true);
        cell3.setUseDescender(true);
        cell3.addElement(paragraph3);
        cell3.setBorder(0);
        table.addCell(cell3);

        Paragraph paragraph4 = new Paragraph("设变前后的客户零件作品号及名称：", font);
        PdfPCell cell4 = new PdfPCell();
        cell4.setUseAscender(true);
        cell4.setUseDescender(true);
        cell4.addElement(paragraph4);
        cell4.setBorder(0);
        table.addCell(cell4);

        Paragraph paragraph5 = new Paragraph("设变前后的单品零件作品号及名称：", font);
        PdfPCell cell5 = new PdfPCell();
        cell5.setUseAscender(true);
        cell5.setUseDescender(true);
        cell5.addElement(paragraph5);
        cell5.setBorder(0);
        table.addCell(cell5);

        Paragraph paragraph6 = new Paragraph("变更时间：", font);
        PdfPCell cell6 = new PdfPCell();
        cell6.setUseAscender(true);
        cell6.setUseDescender(true);
        cell6.addElement(paragraph6);
        cell6.setBorder(0);
        table.addCell(cell6);
        return table;
    }

    /**
     * 获取确认项目表格
     * @param projectList 确认项目List
     * @param procedureList 切替单流程List
     * @return 返回结果
     * @throws Exception 异常
     */
    private Table geterateProjectTable(List<Map<String, Object>> projectList, List<Map<String, Object>> procedureList) throws Exception{
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);

        Table table = new Table(10);
        float[] columnWidth = {25,45,20,60,185,25,60,50,50,30};
        table.setWidths(columnWidth);
        table.setPadding(3f);

        Paragraph paragraph2 = new Paragraph("当担部门/科", font);
        Cell cell2 = new Cell();
        cell2.setUseAscender(true);
        cell2.setUseDescender(true);
        cell2.addElement(paragraph2);
        cell2.setColspan(2);
        cell2.setHorizontalAlignment(1);
        cell2.setVerticalAlignment(5);
        table.addCell(cell2);

        Paragraph paragraph3 = new Paragraph("No", font);
        Cell cell3 = new Cell();
        cell3.setUseAscender(true);
        cell3.setUseDescender(true);
        cell3.addElement(paragraph3);
        cell3.setHorizontalAlignment(1);
        cell3.setVerticalAlignment(5);
        table.addCell(cell3);

        Paragraph paragraph4 = new Paragraph("确认项目", font);
        Cell cell4 = new Cell();
        cell4.setUseAscender(true);
        cell4.setUseDescender(true);
        cell4.addElement(paragraph4);
        cell4.setHorizontalAlignment(1);
        cell4.setVerticalAlignment(5);
        table.addCell(cell4);

        Paragraph paragraph5 = new Paragraph("确认内容", font);
        Cell cell5 = new Cell();
        cell5.setUseAscender(true);
        cell5.setUseDescender(true);
        cell5.addElement(paragraph5);
        cell5.setHorizontalAlignment(1);
        cell5.setVerticalAlignment(5);
        table.addCell(cell5);

        Paragraph paragraph6 = new Paragraph("评审结果", font);
        Cell cell6 = new Cell();
        cell6.setUseAscender(true);
        cell6.setUseDescender(true);
        cell6.addElement(paragraph6);
        cell6.setHorizontalAlignment(1);
        cell6.setVerticalAlignment(5);
        table.addCell(cell6);

        Paragraph paragraph7 = new Paragraph("评审依据（判断评审结果的信息来源）", font);
        Cell cell7 = new Cell();
        cell7.setUseAscender(true);
        cell7.setUseDescender(true);
        cell7.addElement(paragraph7);
        cell7.setHorizontalAlignment(1);
        cell7.setVerticalAlignment(5);
        table.addCell(cell7);

        Paragraph paragraph8 = new Paragraph("评审时间", font);
        Cell cell8 = new Cell();
        cell8.setUseAscender(true);
        cell8.setUseDescender(true);
        cell8.addElement(paragraph8);
        cell8.setHorizontalAlignment(1);
        cell8.setVerticalAlignment(5);
        table.addCell(cell8);

        Paragraph paragraph9 = new Paragraph("变更预定完成时间", font);
        Cell cell9 = new Cell();
        cell9.setUseAscender(true);
        cell9.setUseDescender(true);
        cell9.addElement(paragraph9);
        cell9.setHorizontalAlignment(1);
        cell9.setVerticalAlignment(5);
        table.addCell(cell9);

        Paragraph paragraph10 = new Paragraph("立合时结果确认", font);
        Cell cell10 = new Cell();
        cell10.setUseAscender(true);
        cell10.setUseDescender(true);
        cell10.addElement(paragraph10);
        cell10.setHorizontalAlignment(1);
        cell10.setVerticalAlignment(5);
        table.addCell(cell10);

        if(projectList != null && projectList.size() > 0){
            //mapList 计算跨行
            Integer[] parentRowSpan = new Integer[projectList.size()];
            int index = 0;
            Integer preParentOrgId = null;
            for(int i = 0; i < projectList.size(); i++){
                Map<String, Object> map = projectList.get(i);
                Integer parentOrgId = (Integer)map.get("parentOrgId");
                if(preParentOrgId == null){
                    preParentOrgId = parentOrgId;
                    index = i;
                    parentRowSpan[i] = 1;
                }else {
                    if(parentOrgId.intValue() == preParentOrgId.intValue()){
                        parentRowSpan[index] += 1;
                        parentRowSpan[i] = 0;
                    }else {
                        preParentOrgId = parentOrgId;
                        index = i;
                        parentRowSpan[index] = 1;
                    }
                }
            }

            Integer[] rowSpan = new Integer[projectList.size()];
            index = 0;
            Integer preOrgId = null;
            for(int i = 0; i < projectList.size(); i++){
                Map<String, Object> map = projectList.get(i);
                Integer orgId = (Integer) map.get("orgId");
                if(preOrgId == null){
                    preOrgId = orgId;
                    index = i;
                    rowSpan[index] = 1;
                }else {
                    if(orgId.intValue() == preOrgId.intValue()){
                        rowSpan[index] += 1;
                        rowSpan[i] = 0;
                    }else {
                        preOrgId = orgId;
                        index = i;
                        rowSpan[index] = 1;
                    }
                }
            }

            Float spacingBefore = 100f;
            Float spacingAfter = 100f;
            for(int i = 0; i < projectList.size(); i++){
                Map<String, Object> map = projectList.get(i);
                if(parentRowSpan[i].intValue() != 0){
                    //部门
                    Paragraph paragraph_1 = new Paragraph((String)map.get("parentOrgName"), font);
                    Cell cell_1 = new Cell();
                    cell_1.setUseAscender(true);
                    cell_1.setUseDescender(true);
                    cell_1.addElement(paragraph_1);
                    cell_1.setRowspan(parentRowSpan[i]);
                    cell_1.setHorizontalAlignment(1);
                    cell_1.setVerticalAlignment(5);
                    table.addCell(cell_1);
                }

                if(rowSpan[i].intValue() != 0){
                    //科室
                    Paragraph paragraph_2 = new Paragraph((String)map.get("orgName"), font);
                    Cell cell_2 = new Cell();
                    cell_2.setUseAscender(true);
                    cell_2.setUseDescender(true);
                    cell_2.addElement(paragraph_2);
                    cell_2.setRowspan(rowSpan[i]);
                    cell_2.setHorizontalAlignment(1);
                    cell_2.setVerticalAlignment(5);
                    table.addCell(cell_2);
                }

                //No
                Paragraph paragraph_3 = new Paragraph(String.valueOf(i+1), font);
                Cell cell_3 = new Cell();
                cell_3.setUseAscender(true);
                cell_3.setUseDescender(true);
                cell_3.addElement(paragraph_3);
                cell_3.setHorizontalAlignment(1);
                cell_3.setVerticalAlignment(5);
                table.addCell(cell_3);

                //确认项目
                Paragraph paragraph_4 = new Paragraph((String)map.get("confirmProject"), font);
                Cell cell_4 = new Cell();
                cell_4.setUseAscender(true);
                cell_4.setUseDescender(true);
                cell_4.addElement(paragraph_4);
                cell_4.setHorizontalAlignment(1);
                cell_4.setVerticalAlignment(5);
                table.addCell(cell_4);

                //确认内容
                Paragraph paragraph_5 = new Paragraph((String)map.get("confirmContent"), font);
                Cell cell_5 = new Cell();
                cell_5.setUseAscender(true);
                cell_5.setUseDescender(true);
                cell_5.addElement(paragraph_5);
                cell_5.setHorizontalAlignment(1);
                cell_5.setVerticalAlignment(5);
                table.addCell(cell_5);

                //评审结果
                Paragraph paragraph_6 = new Paragraph((String)map.get("reviewResult"), font);
                Cell cell_6 = new Cell();
                cell_6.setUseAscender(true);
                cell_6.setUseDescender(true);
                cell_6.addElement(paragraph_6);
                cell_6.setHorizontalAlignment(1);
                cell_6.setVerticalAlignment(5);
                table.addCell(cell_6);

                //评审依据
                Paragraph paragraph_7 = new Paragraph((String)map.get("reviewPrinciple"), font);
                Cell cell_7 = new Cell();
                cell_7.setUseAscender(true);
                cell_7.setUseDescender(true);
                cell_7.addElement(paragraph_7);
                cell_7.setHorizontalAlignment(1);
                cell_7.setVerticalAlignment(5);
                table.addCell(cell_7);

                //评审时间
                Paragraph paragraph_8 = new Paragraph((String)map.get("reviewTime"), font);
                Cell cell_8 = new Cell();
                cell_8.setUseAscender(true);
                cell_8.setUseDescender(true);
                cell_8.addElement(paragraph_8);
                cell_8.setHorizontalAlignment(1);
                cell_8.setVerticalAlignment(5);
                table.addCell(cell_8);

                //预定完成时间
                Paragraph paragraph_9 = new Paragraph((String)map.get("changeCompleteTime"), font);
                Cell cell_9 = new Cell();
                cell_9.setUseAscender(true);
                cell_9.setUseDescender(true);
                cell_9.addElement(paragraph_9);
                cell_9.setHorizontalAlignment(1);
                cell_9.setVerticalAlignment(5);
                table.addCell(cell_9);

                //立合结果
                Paragraph paragraph_10 = new Paragraph((String)map.get("agreementResult"), font);
                Cell cell_10 = new Cell();
                cell_10.setUseAscender(true);
                cell_10.setUseDescender(true);
                cell_10.addElement(paragraph_10);
                cell_10.setHorizontalAlignment(1);
                cell_10.setVerticalAlignment(5);
                table.addCell(cell_10);
            }
        }
        String remark = "";
        if(procedureList != null && procedureList.size() > 0){
            for(Map<String, Object> map : procedureList){
                Integer procedureCode = (Integer) map.get("procedureCode");
                if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_REVIEW.getCode().intValue()){
                    remark = (String)map.get("remark");
                }
            }
        }
        if(remark == null){
            remark = "";
        }
        Paragraph paragraph = new Paragraph("评审结论："+remark, font);
        Cell cell = new Cell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.addElement(paragraph);
        cell.setColspan(10);
        table.addCell(cell);
        return table;
    }

    /**
     * 获取立合结果表格
     * @param agreemntList 立合列表
     * @return 返回结果
     * @throws Exception 异常
     */
    private Table geterateAgreementTable(List<Map<String, Object>> agreemntList) throws Exception{
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);

        Integer num = agreemntList.size();
        float[] columnWidth = new float[num + 1];
        columnWidth[0] = 35f;
        for(int i = 1; i <= num ; i++){
            columnWidth[i] = 640f / num;
        }
        Table table = new Table(num + 1);
        table.setWidths(columnWidth);
        table.setPadding(3f);

        Paragraph paragraph = new Paragraph("会签", font);
        Cell cell = new Cell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setVerticalAlignment(5);
        cell.setHorizontalAlignment(1);
        cell.addElement(paragraph);
        cell.setRowspan(3);
        table.addCell(cell);

        if(agreemntList != null && agreemntList.size() > 0){
            Integer[] colspan = new Integer[agreemntList.size()];
            //计算跨列
            int index = 0;
            Integer preParentOrgId = null;
            for(int i = 0; i < num; i++){
                Map<String, Object> map = agreemntList.get(i);
                Integer parentOrgId = (Integer)map.get("parentOrgId");
                if(preParentOrgId == null){
                    preParentOrgId = parentOrgId;
                    index = i;
                    colspan[i] = 1;
                }else {
                    if(parentOrgId.intValue() == preParentOrgId.intValue()){
                        colspan[index] += 1;
                        colspan[i] = 0;
                    }else {
                        preParentOrgId = parentOrgId;
                        index = i;
                        colspan[index] = 1;
                    }
                }
            }
            //部门
            for(int i = 0; i < num; i++){
                Integer cols = colspan[i];
                if(cols > 0){
                    Map<String, Object> map = agreemntList.get(i);
                    paragraph = new Paragraph((String)map.get("parentOrgName"), font);
                    cell = new Cell();
                    cell.setUseAscender(true);
                    cell.setUseDescender(true);
                    cell.setVerticalAlignment(5);
                    cell.setHorizontalAlignment(1);
                    cell.setColspan(cols);
                    cell.addElement(paragraph);
                    table.addCell(cell);
                }
            }
            //科室
            for(int i = 0; i < num; i++){
                Map<String, Object> map = agreemntList.get(i);
                paragraph = new Paragraph((String) map.get("orgName"), font);
                cell = new Cell();
                cell.setUseAscender(true);
                cell.setUseDescender(true);
                cell.setVerticalAlignment(5);
                cell.setHorizontalAlignment(1);
                cell.addElement(paragraph);
                table.addCell(cell);
            }
            //人员
            for(int i = 0; i < num; i++){
                String userName = "";
                Map<String, Object> map = agreemntList.get(i);
                Integer isNeedAgreement = (Integer) map.get("isNeedAgreement");
                if(1 == isNeedAgreement){
                    userName = (String)map.get("userName");
                }else {
                    userName = "/";
                }
                paragraph = new Paragraph(userName, font);
                cell = new Cell();
                cell.setUseAscender(true);
                cell.setUseDescender(true);
                cell.setVerticalAlignment(5);
                cell.setHorizontalAlignment(1);
                cell.addElement(paragraph);
                table.addCell(cell);
            }
        }

        return table;
    }

    /**
     * 获取表头表格
     * @param kirikaeOrder 切替单实体
     * @param procedureList 流程列表
     * @return 返回结果
     * @throws Exception 异常
     */
    private PdfPTable generateConfirmBookTitle(KirikaeOrder kirikaeOrder, List<Map<String, Object>> procedureList) throws Exception{
        float[] columnWidth = {400f, 120f};
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10f);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);

        PdfPTable leftHeaderTable = this.geterateLeftHeaderTable(kirikaeOrder);
        PdfPCell cell_1_1 = new PdfPCell();
        cell_1_1.setUseAscender(true);
        cell_1_1.setUseDescender(true);
        cell_1_1.addElement(leftHeaderTable);
        cell_1_1.setBorder(0);
        table.addCell(cell_1_1);

        PdfPTable operateTable = this.generateOperateTable(procedureList);
        PdfPCell cell_1_2 = new PdfPCell();
        cell_1_2.setUseAscender(true);
        cell_1_2.setUseDescender(true);
        cell_1_2.addElement(operateTable);
        cell_1_2.setBorder(0);
        table.addCell(cell_1_2);

        return table;
    }

    @Override
    public String exportHandMatch(Integer orderId, String path) throws Exception {
        String pdfName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String randNum = UUID.randomUUID().toString();
        pdfName = sdf.format(date)+"_"+randNum+".pdf";

        String templatePDF = path + "templet/alteration-1.pdf";
        FileOutputStream fileOutputStream = new FileOutputStream(path+"stdout/"+pdfName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfReader pdfReader = new PdfReader(templatePDF);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
        AcroFields acroFields = pdfStamper.getAcroFields();

        //履历
        String[] resumeDescription = {"", "", "", ""};
        String[] resumePrepared = {"", "", "", ""};
        List<KirikaeResume> kirikaeResumeList = this.kirikaeResumeDao.selectKirikaeResumeListByOrderId(orderId);
        if (kirikaeResumeList != null){
            int i = 0;
            for (KirikaeResume kirikaeResume : kirikaeResumeList){
                resumeDescription[i] = kirikaeResume.getDescription();
                resumePrepared[i] = kirikaeResume.getPrepared();
                i++;
            }
        }
        acroFields.setField("resume-description-1", resumeDescription[0]);
        acroFields.setField("resume-description-2", resumeDescription[1]);
        acroFields.setField("resume-description-3", resumeDescription[2]);
        acroFields.setField("resume-description-4", resumeDescription[3]);
        acroFields.setField("resume-prepared-1", resumePrepared[0]);
        acroFields.setField("resume-prepared-2", resumePrepared[1]);
        acroFields.setField("resume-prepared-3", resumePrepared[2]);
        acroFields.setField("resume-prepared-4", resumePrepared[3]);

        KirikaeOrder kirikaeOrder  = this.kirikaeOrderDao.selectKirikaeOrderByOrderId(orderId);
        //设变号
        String tkNo = kirikaeOrder.getTkNo();
        acroFields.setField("tk-no", tkNo);

        //客户设变号
        String designChangeNo = kirikaeOrder.getDesignChangeNo();
        if(designChangeNo == null){
            designChangeNo = "";
        }
        acroFields.setField("design-change-no", designChangeNo);

        //营业日期
        Date salesDate = kirikaeOrder.getSalesDate();
        String salesDateYear = "";
        String salesDateMonth = "";
        String salesDateDay = "";
        if(salesDate!= null){
            Calendar cld = Calendar.getInstance();
            cld.setTime(salesDate);
            salesDateYear = String.valueOf(cld.get(Calendar.YEAR));
            salesDateMonth = String.valueOf(cld.get(Calendar.MONTH)+1);
            salesDateDay = String.valueOf(cld.get(Calendar.DAY_OF_MONTH));
        }
        acroFields.setField("sales-date-year", salesDateYear);
        acroFields.setField("sales-date-month", salesDateMonth);
        acroFields.setField("sales-date-day", salesDateDay);

        //营业承认
        String salesApproved = kirikaeOrder.getSalesApproved();
        if(salesApproved == null){
            salesApproved = "";
        }
        acroFields.setField("sales-approved", salesApproved);

        //营业确认
        String salesChecked = kirikaeOrder.getSalesChecked();
        if(salesApproved == null){
            salesChecked = "";
        }
        acroFields.setField("sales-checked", salesChecked);

        //营业担当
        String salesPrepared = kirikaeOrder.getSalesPrepared();
        if(salesPrepared == null){
            salesApproved = "";
        }
        acroFields.setField("sales-prepared", salesPrepared);

        //设计日期
        Date engineeringData = kirikaeOrder.getEngineeringData();
        String engineeringDataYear = "";
        String engineeringDataMonth = "";
        String engineeringDataDay = "";
        if(engineeringData!= null){
            Calendar cld = Calendar.getInstance();
            cld.setTime(engineeringData);
            engineeringDataYear = String.valueOf(cld.get(Calendar.YEAR));
            engineeringDataMonth = String.valueOf(cld.get(Calendar.MONTH)+1);
            engineeringDataDay = String.valueOf(cld.get(Calendar.DAY_OF_MONTH));
        }
        acroFields.setField("engineering-data-year", engineeringDataYear);
        acroFields.setField("engineering-data-month", engineeringDataMonth);
        acroFields.setField("engineering-data-day", engineeringDataDay);

        //设计承认
        String engineeringApproved = kirikaeOrder.getEngineeringApproved();
        if(engineeringApproved == null){
            engineeringApproved = "";
        }
        acroFields.setField("engineering-approved", engineeringApproved);

        //设计确认
        String engineeringChecked = kirikaeOrder.getEngineeringChecked();
        if(engineeringChecked == null){
            engineeringChecked = "";
        }
        acroFields.setField("engineering-checked", engineeringChecked);

        //设计担当
        String engineeringPrepared = kirikaeOrder.getEngineeringPrepared();
        if(engineeringPrepared == null){
            engineeringPrepared = "";
        }
        acroFields.setField("engineering-prepared", engineeringPrepared);

        //客户
        String customer = kirikaeOrder.getCustomer();
        if(customer == null){
            customer = "";
        }
        acroFields.setField("customer", customer);

        //车种名
        String vehicleName = kirikaeOrder.getVehicleName();
        if(vehicleName == null){
            vehicleName = "";
        }
        acroFields.setField("vehicle-name", vehicleName);

        //销售地
        String destination = kirikaeOrder.getDestination();
        if(destination == null){
            destination = "";
        }
        acroFields.setField("destination", destination);

        //客户初回品处理
        Integer isirProcessing = kirikaeOrder.getIsirProcessing();
        if(isirProcessing != null){
            if(1 == isirProcessing.intValue()){
                acroFields.setField("isir-processing-yes", "true");
            }else if(2 == isirProcessing.intValue()){
                acroFields.setField("isir-processing-no", "true");
            }
        }
        //客户技术承认
        Integer customerEngineering = kirikaeOrder.getCustomerEngineering();
        if(customerEngineering != null) {
            if (1 == customerEngineering) {
                acroFields.setField("customer-engineering-yes", "true");
            } else if (2 == customerEngineering) {
                acroFields.setField("customer-engineering-no", "true");
            }
        }
        Integer customerEngineeringApproval = kirikaeOrder.getCustomerEngineeringApproval();
        if(customerEngineeringApproval != null){
            if(1 == customerEngineeringApproval){
                acroFields.setField("customer-engineering-approval-yes", "true");
            }else if(2 == customerEngineeringApproval){
                acroFields.setField("customer-engineering-approval-no", "true");
            }
        }
        //法规关系
        Integer regulation = kirikaeOrder.getRegulation();
        if(regulation != null){
            if(1 == regulation.intValue()){
                acroFields.setField("regulation-yes", "true");
            }else if(2 == regulation.intValue()){
                acroFields.setField("regulation-no", "true");
            }
        }
        Integer regulationApproval = kirikaeOrder.getRegulationApproval();
        if(regulationApproval != null){
            if(1 == regulationApproval.intValue()){
                acroFields.setField("regulation-approval-yes", "true");
            }else if(2 == regulationApproval.intValue()){
                acroFields.setField("regulation-approval-no", "true");
            }
        }
        //互换性-旧
        String interchangeabilityOld = kirikaeOrder.getInterchangeabilityOld();
        if(interchangeabilityOld != null){
            if("A".equals(interchangeabilityOld)){
                acroFields.setField("interchangeability-old-a", "true");
            }else if("B".equals(interchangeabilityOld)){
                acroFields.setField("interchangeability-old-b", "true");
            }
        }
        //互换性-新
        String interchangeabilityNew = kirikaeOrder.getInterchangeabilityNew();
        if(interchangeabilityNew != null){
            if("C".equals(interchangeabilityNew)){
                acroFields.setField("interchangeability-new-c", "true");
            }else if("D".equals(interchangeabilityNew)){
                acroFields.setField("interchangeability-new-d", "true");
            }
        }
        //售后服务
        Integer serviceSuplied = kirikaeOrder.getServiceSuplied();
        if(serviceSuplied != null){
            if(1 == serviceSuplied.intValue()){
                acroFields.setField("service-suplied-yes", "true");
            }else if(2 == serviceSuplied.intValue()){
                acroFields.setField("service-suplied-no", "true");
            }
        }
        //模具设变费
        Integer designCosts = kirikaeOrder.getDesignCosts();
        if(isirProcessing != null){
            if(1 == designCosts.intValue()){
                acroFields.setField("design-costs-yes", "true");
            }else if(2 == designCosts.intValue()){
                acroFields.setField("design-costs-no", "true");
            }
        }
        Integer designCostsPay = kirikaeOrder.getDesignCostsPay();
        if (designCostsPay != null) {
            if (1 == designCostsPay) {
                acroFields.setField("design-costs-pay-1", "true");
            } else if (2 == designCostsPay) {
                acroFields.setField("design-costs-pay-2", "true");
            } else if (3 == designCostsPay) {
                acroFields.setField("design-costs-pay-3", "true");
            }
        }

        Integer customerEo = kirikaeOrder.getCustomerEo();
        if (customerEo != null) {
            if (1 == customerEo) {
                acroFields.setField("design-change-contests-yes", "true");
            } else if (2 == customerEo) {
                acroFields.setField("design-change-contests-no", "true");
            }
        }
        //设变内容变更前
        String beforeChangeContent = "";
        //设变内容变更后
        String afterChangeContent = "";
        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(orderId);
        for (KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList) {
            if ("".equals(beforeChangeContent)) {
                beforeChangeContent += kirikaeOrderChangeContent.getBeforeChange();
                afterChangeContent += kirikaeOrderChangeContent.getAfterChange();
            } else {
                beforeChangeContent += "\n" + kirikaeOrderChangeContent.getBeforeChange();
                afterChangeContent += "\n" + kirikaeOrderChangeContent.getAfterChange();
            }
        }
        acroFields.setField("before-change-content", beforeChangeContent);
        acroFields.setField("after-change-content", afterChangeContent);

        Integer partsNumberChange = kirikaeOrder.getPartsNumberChange();
        if (partsNumberChange != null) {
            if (1 == partsNumberChange) {
                acroFields.setField("parts-num-yes", "true");
            } else if (2 == partsNumberChange) {
                acroFields.setField("parts-num-no", "true");
            }
        }
        //对象部品及品号变更前
        String beforePartsNum = "变更前";
        //对象部品及品号变更后
        String afterPartsNum = "变更后";
        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberDao.selectKirikaeOrderPartsNumberListByOrderId(orderId);
        for(KirikaeOrderPartsNumber kirikaeOrderPartsNumber : kirikaeOrderPartsNumberList){
            beforePartsNum += "\n" + kirikaeOrderPartsNumber.getOldPartsNumber();
            afterPartsNum += "\n" + kirikaeOrderPartsNumber.getNewPattsNumber();
        }
        acroFields.setField("before-parts-num", beforePartsNum);
        acroFields.setField("after-parts-num", afterPartsNum);

        //切替现场确认
        Integer presenceRequired = kirikaeOrder.getPresenceRequired();
        if (presenceRequired != null) {
            if (1 == presenceRequired) {
                acroFields.setField("presence-required-yes", "true");
            } else if (2 == presenceRequired) {
                acroFields.setField("presence-required-no", "true");
            }
        }
        //切替类型
        Integer desingChangeType = kirikaeOrder.getDesingChangeType();
        if (desingChangeType != null) {
            if (1 == desingChangeType) {
                acroFields.setField("desing-change-type-1", "true");
            } else if (2 == desingChangeType) {
                acroFields.setField("desing-change-type-2", "true");
            }
        }
        //制造内线
        String manufactureInternal = kirikaeOrder.getManufactureInternal();
        if (manufactureInternal == null) {
            manufactureInternal = "";
        }
        acroFields.setField("manufacture-internal", manufactureInternal);
        //制造担当
        String manufacturePrepared = kirikaeOrder.getManufacturePrepared();
        if (manufacturePrepared == null) {
            manufacturePrepared = "";
        }
        acroFields.setField("manufacture-prepared", manufacturePrepared);
        //切替时间
        Date designChangeTiming = kirikaeOrder.getDesignChangeTiming();
        String designChangeTimingDay = "";
        String designChangeTimingMonth = "";
        if (designChangeTiming != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(designChangeTiming);
            designChangeTimingDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            designChangeTimingMonth = String.valueOf((cal.get(Calendar.MONTH) + 1));
        }
        acroFields.setField("design-change-timing-day", designChangeTimingDay);
        acroFields.setField("design-change-timing-month", designChangeTimingMonth);

        //指示书承认
        String instructionApproved = "";
        //指示书确认
        String instructionChecked = "";
        //指示书担当
        String instructionPrepared = "";
        //确认书承认
        String confirmationApproved = "";
        //确认书确认
        String confirmationChecked = "";
        //确认书担当
        String confirmationPrepared = "";
        List<Map<String, Object>> mapList = this.kirikaeOrderProcedureDao.selectKirikaeOrderProcedureMapListByOrderId(orderId);
        for(Map<String, Object> map : mapList){
            Integer procedureCode = (Integer)map.get("procedureCode");
            if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_PREPARED.getCode().intValue()){
                instructionPrepared = (String)map.get("userName");
            }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_CHECKED.getCode().intValue()){
                instructionChecked = (String)map.get("userName");
            }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_INSTRUCTIONS_APPROVED.getCode().intValue()){
                instructionApproved = (String)map.get("userName");
            }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_PREPARED.getCode().intValue()){
                confirmationPrepared = (String)map.get("userName");
            }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_CHECKED.getCode().intValue()){
                confirmationChecked = (String)map.get("userName");
            }else if(procedureCode.intValue() == ProcedureEnum.ProcedureCodeEnum.PROCEDURE_CONFIRM_APPROVED.getCode().intValue()){
                confirmationApproved = (String)map.get("userName");
            }
        }
        if(instructionApproved == null){
            instructionApproved = "";
        }
        acroFields.setField("instruction-approved", instructionApproved);
        if(instructionChecked == null){
            instructionChecked = "";
        }
        acroFields.setField("instruction-checked", instructionChecked);
        if(instructionPrepared == null){
            instructionPrepared = "";
        }
        acroFields.setField("instruction-prepared", instructionPrepared);
        if(confirmationApproved == null){
            confirmationApproved = "";
        }
        acroFields.setField("confirmation-approved", confirmationApproved);
        if(confirmationChecked == null){
            confirmationChecked = "";
        }
        acroFields.setField("confirmation-checked", confirmationChecked);
        if(confirmationPrepared == null){
            confirmationPrepared = "";
        }
        acroFields.setField("confirmation-prepared", confirmationPrepared);

        //指示书工厂
        String instructionFactory = "";
        //客户设变通知书
        Integer customerDesignChangeNotification = null;
        //客户技术承认
        Integer customerTechnologyApproval = null;
        //法规认可
        Integer certificationApproval = null;
        //初回品处理
        Integer isirProcession = null;
        //初回品标识添附
        Integer isirMarking = null;
        //初回品通知书发行
        Integer isirNotificationIssued = null;
        KirikaeInstruction kirikaeInstruction = this.kirikaeInstructionDao.selectKirikaeInstructionByOrderId(orderId);
        if(kirikaeInstruction != null){
            instructionFactory = kirikaeInstruction.getFactory();
            customerDesignChangeNotification = kirikaeInstruction.getCustomerDesignChangeNotification();
            customerTechnologyApproval = kirikaeInstruction.getCustomerTechnologyApproval();
            certificationApproval = kirikaeInstruction.getCertificationApproval();
            isirProcession = kirikaeInstruction.getIsirProcession();
            isirMarking = kirikaeInstruction.getIsirMarking();
            isirNotificationIssued = kirikaeInstruction.getIsirNotificationIssued();
        }
        if(instructionFactory == null){
            instructionFactory = "";
        }
        acroFields.setField("instruction-factory", instructionFactory);
        if(customerDesignChangeNotification != null){
            if(1 == customerDesignChangeNotification){
                acroFields.setField("customer-design-change-notification", "true");
            }
        }
        if(customerTechnologyApproval != null){
            if(1 == customerTechnologyApproval){
                acroFields.setField("customer-technology-approval", "true");
            }
        }
        if(certificationApproval != null){
            if(1 == certificationApproval){
                acroFields.setField("certification-approval", "true");
            }
        }
        if (isirProcession != null) {
            if (1 == isirProcession) {
                acroFields.setField("instruction-isir-procession-yes", "true");
            } else if (0 == isirProcession) {
                acroFields.setField("instruction-isir-procession-no", "true");
            }
        }
        if (isirMarking != null) {
            if (1 == isirMarking) {
                acroFields.setField("instruction-isir-marking-yes", "true");
            } else if (0 == isirMarking) {
                acroFields.setField("instruction-isir-marking-no", "true");
            }
        }
        if (isirNotificationIssued != null) {
            if (1 == isirNotificationIssued) {
                acroFields.setField("instruction-isir-notification-issued-yes", "true");
            } else if (0 == isirNotificationIssued) {
                acroFields.setField("instruction-isir-notification-issued-no", "true");
            }
        }

        //确认书工厂
        String confirmationFactory = "";
        String confirmationProductionBatch = "";
        String confirmationReleaseDateStr = "";
        KirikaeConfirmation kirikaeConfirmation = this.kirikaeConfirmationDao.selectKirikaeConfirmationByOrderId(orderId);
        if (kirikaeConfirmation != null) {
            confirmationFactory = kirikaeConfirmation.getFactory();
            confirmationProductionBatch = kirikaeConfirmation.getProductionBatch();
            Date releaseDate = kirikaeConfirmation.getReleaseDate();
            if (releaseDate != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                confirmationReleaseDateStr = formatter.format(releaseDate);
            }
        }
        if (confirmationFactory == null) {
            confirmationFactory = "";
        }
        if (confirmationProductionBatch == null) {
            confirmationProductionBatch = "";
        }
        acroFields.setField("confirmation-factory", confirmationFactory);
        acroFields.setField("confirmation-production-batch", confirmationProductionBatch);
        acroFields.setField("confirmation-release-date", confirmationReleaseDateStr);

        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
        Document document = new Document();
        PdfCopy pdfCopy = new PdfCopy(document, fileOutputStream);
        document.open();
        PdfImportedPage impPage = pdfCopy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
        pdfCopy.addPage(impPage);
        document.close();

        return pdfName;
    }

    @Override
    public String exportHandMatchAttachment(Integer orderId, String path) throws Exception {
        String handMatchAttachmentName = "";
        //判断是否待附件
        Boolean flag = false;
        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(orderId);
        List<Integer> fileIdList = new LinkedList<Integer>();
        for (KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList) {
            Integer beforeFileId = kirikaeOrderChangeContent.getBeforeFileId();
            if (beforeFileId != null) {
                fileIdList.add(beforeFileId);
            }
            Integer newFileId = kirikaeOrderChangeContent.getNewFileId();
            if (newFileId != null) {
                fileIdList.add(newFileId);
            }
        }
        //查询PDF文件
        List<FileUpload> fileUploadList = new LinkedList<FileUpload>();
        if (fileIdList.size() > 0){
            for (Integer fileId : fileIdList) {
                FileUpload fileUpload = new FileUpload();
                fileUpload.setFileId(fileId);
                fileUpload = this.fileUploadDao.selectBySelf(fileUpload);
                String fileType = fileUpload.getFileType();
                if ("application/pdf".equals(fileType)) {
                    flag = true;
                    fileUploadList.add(fileUpload);
                }
            }
        }
        String handMatchName = this.exportHandMatch(orderId, path);
        if (flag) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String randNum = UUID.randomUUID().toString();
            handMatchAttachmentName = sdf.format(date)+"_"+randNum+".pdf";

            List<PdfReader> pdfReaderList = new LinkedList<PdfReader>();
            PdfReader reader = new PdfReader(path+"stdout/" + handMatchName);
            pdfReaderList.add(reader);
            for (FileUpload fileUpload : fileUploadList) {
                PdfReader temp = new PdfReader(path+"fileupload/" + fileUpload.getFileAlias());
                pdfReaderList.add(temp);
            }

            FileOutputStream out = new FileOutputStream(path+"stdout/"+handMatchAttachmentName);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();
            PdfContentByte cb = writer.getDirectContent();

            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = pdfReaderList.iterator();
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
                }
                pageOfCurrentReaderPDF = 0;
            }
            out.flush();
            document.close();
            out.close();
        } else {
            handMatchAttachmentName = handMatchName;
        }
        return handMatchAttachmentName;
    }

}
