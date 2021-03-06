package com.dpcoi.quartz.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/5/8.
 */

import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.rr.dao.RRProblem2Dao;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.woOrder.dao.DpcoiWoOrderDao;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-08
 **/

//@Component("createDpcoiEmailServiceImpl")
public class CreateDpcoiEmailServiceImpl {

    @Resource(name="dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Resource(name="dpcoiWoOrderDao")
    private DpcoiWoOrderDao dpcoiWoOrderDao;

    @Resource(name="timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name="rRProblem2Dao")
    private RRProblem2Dao rRProblemDao;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void job(){
        try{
            //查找DPCOI待确认变更超时
            List<Map<String, Object>> confirmDelayList = this.dpcoiWoOrderDao.selectConfirmDelayWoOrderList();
            for(Map<String, Object> map : confirmDelayList){
                Integer dpcoiOrderId = (Integer)map.get("dpcoiOrderId");
                Integer dpcoiWoOrderId = (Integer)map.get("dpcoiWoOrderId");
                Integer dpcoiWoOrderType = (Integer)map.get("dpcoiWoOrderType");

                String issuedNo = (String)map.get("issuedNo");
                String designChangeNo = (String)map.get("designChangeNo");
                String vehicleName = (String)map.get("vehicleName");
                String productNo = (String)map.get("productNo");
                String hopeCuttingDate = (String)map.get("hopeCuttingDate");
                String realCuttingDateStr = (String)map.get("realCuttingDateStr");
                String changeContent = (String)map.get("changeContent");
                String releaseDateStr = (String)map.get("releaseDateStr");
                String returnDateStr = (String)map.get("returnDateStr");
                String designAct = (String)map.get("designAct");
                String quaisActName = (String)map.get("quaisActName");
                String remark = (String)map.get("remark");
                String taskOrderNo = (String)map.get("taskOrderNo");
                String productLine = (String)map.get("productLine");

                StringBuffer contentBuffer = new StringBuffer();
                Integer rrProbleId = (Integer)map.get("rrProblemId");
                String emailTitle = "";
                if(rrProbleId == null){
                    contentBuffer.append("《设变通知书》编号:").append(issuedNo)
                            .append("<br>").append("设变号:").append(designChangeNo)
                            .append("<br>").append("车种:").append(vehicleName)
                            .append("<br>").append("品号:").append(productNo)
                            .append("<br>").append("希望切替日:").append(hopeCuttingDate)
                            .append("<br>").append("实际切替日:").append(realCuttingDateStr)
                            .append("<br>").append("变更内容:").append(changeContent)
                            .append("<br>").append("发行日期:").append(releaseDateStr)
                            .append("<br>").append("《设变切替手配书》返回日:").append(returnDateStr)
                            .append("<br>").append("设计担当:").append(designAct)
                            .append("<br>").append("量准担当:").append(quaisActName)
                            .append("<br>").append("备注:").append(remark)
                            .append("<br>").append("4M发行编号:").append(taskOrderNo)
                            .append("<br>").append("生产线:").append(productLine);
                    if(issuedNo == null || "".equals(issuedNo)){
                        emailTitle = issuedNo;
                    }else {
                        emailTitle = taskOrderNo;
                    }
                }else {
                    RRProblem rrProblem = new RRProblem();
                    rrProblem.setId(rrProbleId);
                    rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
                    contentBuffer.append("状态:").append(rrProblem.getProblemStatus())
                            .append("<br>").append("问题编号:").append(rrProblem.getProblemNo())
                            .append("<br>").append("问题类型:").append(rrProblem.getProblemType())
                            .append("<br>").append("工程:").append(rrProblem.getEngineering())
                            .append("<br>").append("客户:").append(rrProblem.getCustomer())
                            .append("<br>").append("车型:").append(rrProblem.getVehicle())
                            .append("<br>").append("品名:").append(rrProblem.getProductNo())
                            .append("<br>").append("不良内容:").append(rrProblem.getBadContent())
                            .append("<br>").append("生产线:").append(rrProblem.getProductLine())
                            .append("<br>").append("严重度:").append(rrProblem.getSeverity())
                            .append("<br>").append("根本原因:").append(rrProblem.getRootCause())
                            .append("<br>").append("永久对策:").append(rrProblem.getPermanentGame());
                    emailTitle = rrProblem.getProblemNo();
                }

                DpcoiOrder dpcoiOrder = new DpcoiOrder();
                dpcoiOrder.setDpcoiOrderId(dpcoiOrderId);
                if(dpcoiWoOrderType.intValue() == 1){
                    dpcoiOrder.setPfmeaEmailDate(new Date());
                }else if(dpcoiWoOrderType.intValue() == 2){
                    dpcoiOrder.setCpEmailDate(new Date());
                }else if(dpcoiWoOrderType.intValue() == 3){
                    dpcoiOrder.setStandardBookEmailDate(new Date());
                }
                this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);

                Integer noticeType = 29;
                if(dpcoiWoOrderType.intValue() == 1){
                    noticeType = 29;
                }else if(dpcoiWoOrderType.intValue() == 2){
                    noticeType = 30;
                }else if(dpcoiWoOrderType.intValue() == 3){
                    noticeType = 31;
                }
                DpcoiWoOrder dpcoiWoOrder = new DpcoiWoOrder();
                dpcoiWoOrder.setDpcoiWoOrderId(dpcoiWoOrderId);
                String emailStr = this.dpcoiWoOrderDao.selectWoOrderAllEmailUsers(dpcoiWoOrder);
                TimeTask timeTask = new TimeTask();
                timeTask.setNoticeType(noticeType);
                timeTask.setComment(contentBuffer.toString());
                timeTask.setUserEmail(emailStr);
                timeTask.setEmailTitle(emailTitle);
                timeTask.setDeleteState(0);
                //判断是否节假日
                Integer holidayNum = this.dpcoiWoOrderDao.selectHoliday(new Date());
                if(holidayNum.intValue() == 0){
                    this.timeTaskDao.insertTimeTask(timeTask);
                }
            }

            //查找DPCOI变更超时
            List<Map<String, Object>> changeDelayList = this.dpcoiWoOrderDao.selectChangeDelayWoOrderList();
            for(Map<String, Object> map : changeDelayList){
                Integer dpcoiOrderId = (Integer)map.get("dpcoiOrderId");
                Integer dpcoiWoOrderId = (Integer)map.get("dpcoiWoOrderId");
                Integer dpcoiWoOrderType = (Integer)map.get("dpcoiWoOrderType");

                DpcoiOrder dpcoiOrder = new DpcoiOrder();
                dpcoiOrder.setDpcoiOrderId(dpcoiOrderId);
                if(dpcoiWoOrderType.intValue() == 1){
                    dpcoiOrder.setPfmeaDelay(1);
                }else if(dpcoiWoOrderType.intValue() == 2){
                    dpcoiOrder.setCpDelay(1);
                }else if(dpcoiWoOrderType.intValue() == 3){
                    dpcoiOrder.setStandardBookDelay(1);
                }
                this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);

                String issuedNo = (String)map.get("issuedNo");
                String designChangeNo = (String)map.get("designChangeNo");
                String vehicleName = (String)map.get("vehicleName");
                String productNo = (String)map.get("productNo");
                String hopeCuttingDate = (String)map.get("hopeCuttingDate");
                String realCuttingDateStr = (String)map.get("realCuttingDateStr");
                String changeContent = (String)map.get("changeContent");
                String releaseDateStr = (String)map.get("releaseDateStr");
                String returnDateStr = (String)map.get("returnDateStr");
                String designAct = (String)map.get("designAct");
                String quaisActName = (String)map.get("quaisActName");
                String remark = (String)map.get("remark");
                String taskOrderNo = (String)map.get("taskOrderNo");
                String productLine = (String)map.get("productLine");

                StringBuffer contentBuffer = new StringBuffer();
                contentBuffer.append("《设变通知书》编号:").append(issuedNo)
                        .append("<br>").append("设变号:").append(designChangeNo)
                        .append("<br>").append("车种:").append(vehicleName)
                        .append("<br>").append("品号:").append(productNo)
                        .append("<br>").append("希望切替日:").append(hopeCuttingDate)
                        .append("<br>").append("实际切替日:").append(realCuttingDateStr)
                        .append("<br>").append("变更内容:").append(changeContent)
                        .append("<br>").append("发行日期:").append(releaseDateStr)
                        .append("<br>").append("《设变切替手配书》返回日:").append(returnDateStr)
                        .append("<br>").append("设计担当:").append(designAct)
                        .append("<br>").append("量准担当:").append(quaisActName)
                        .append("<br>").append("备注:").append(remark)
                        .append("<br>").append("4M发行编号:").append(taskOrderNo)
                        .append("<br>").append("生产线:").append(productLine);
                Integer noticeType = 32;
                if(dpcoiWoOrderType.intValue() == 1){
                    noticeType = 32;
                }else if(dpcoiWoOrderType.intValue() == 2){
                    noticeType = 33;
                }else if(dpcoiWoOrderType.intValue() == 3){
                    noticeType = 34;
                }
                DpcoiWoOrder dpcoiWoOrder = new DpcoiWoOrder();
                dpcoiWoOrder.setDpcoiWoOrderId(dpcoiWoOrderId);
                String emailStr = this.dpcoiWoOrderDao.selectWoOrderAllEmailUsers(dpcoiWoOrder);
                TimeTask timeTask = new TimeTask();
                timeTask.setNoticeType(noticeType);
                timeTask.setComment(contentBuffer.toString());
                timeTask.setUserEmail(emailStr);
                if(issuedNo == null || "".equals(issuedNo)){
                    timeTask.setEmailTitle(issuedNo);
                }else {
                    timeTask.setEmailTitle(taskOrderNo);
                }
                timeTask.setDeleteState(0);
                this.timeTaskDao.insertTimeTask(timeTask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
