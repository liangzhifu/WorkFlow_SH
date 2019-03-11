package com.dpcoi.rr.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */

import com.dpcoi.config.dao.DpcoiConfigVehicle2Dao;
import com.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.dpcoi.file.dao.FileUpload2Dao;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.holiday.dao.HolidayDao;
import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.rr.dao.RRDelayLeader2Dao;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.dao.RRProblem2Dao;
import com.dpcoi.rr.query.RRProblemQuery;
import com.dpcoi.rr.service.RRProblem2Service;
import com.dpcoi.woOrder.dao.DpcoiWoOrderDao;
import com.dpcoi.woOrder.dao.DpcoiWoOrderFileDao;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author lzf
 * @create 2017-06-28
 **/
@Service("rRProblem2Service")
public class RRProblem2ServiceImpl implements RRProblem2Service {

    @Resource(name = "rRProblem2Dao")
    private RRProblem2Dao rRProblemDao;

    @Resource(name = "holidayDao")
    private HolidayDao holidayDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name="fileUpload2Dao")
    private FileUpload2Dao fileUploadDao;

    @Resource(name = "dpcoiConfigVehicle2Dao")
    private DpcoiConfigVehicle2Dao dpcoiConfigVehicleDao;

    @Resource(name="timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name="rRDelayLeader2Dao")
    private RRDelayLeader2Dao rRDelayLeaderDao;

    @Resource(name="dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Resource(name = "dpcoiWoOrderDao")
    private DpcoiWoOrderDao dpcoiWoOrderDao;

    @Resource(name = "dpcoiWoOrderFileDao")
    private DpcoiWoOrderFileDao dpcoiWoOrderFileDao;

    @Override
    public Integer addRRProblem(RRProblem rrProblem) throws Exception {
        rrProblem.setState(1);
        //获得问题编号
        String problemType = rrProblem.getProblemType();
        String problemNo = "";
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String shortYear = String.valueOf(year).substring(2);
        cal.setTime(nowDate);
        if("客诉".equals(problemType)){
            problemNo = "TT-KS-"+shortYear+"-";
        }else if("内诉（全检）".equals(problemType)) {
            problemNo = "TT-NS-"+shortYear+"-";
        }else if("内诉（抽检）".equals(problemType)) {
            problemNo = "TT-NS-"+shortYear+"-";
        }else if("工程".equals(problemType)) {
            problemNo = "TT-GC-"+shortYear+"-";
        }else if("市场投诉".equals(problemType)) {
            problemNo = "TT-SC-"+shortYear+"-";
        }else if("部品".equals(problemType)) {
            problemNo = "TT-BP-"+shortYear+"-";
        }else{
            throw new ServiceException("问题类型不存在！");
        }
        Integer max = this.rRProblemDao.selectProblemNoMax(problemNo);
        if(max == null){
            problemNo += "001";
        }else {
            max += 1;
            if(max < 10){
                problemNo += "00" + max;
            }else if(max < 100){
                problemNo += "0" + max;
            }else{
                problemNo += "" + max;
            }
        }
        rrProblem.setProblemNo(problemNo);

        //获取客户
        DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery = new DpcoiConfigVehicleQuery();
        dpcoiConfigVehicleQuery.setConfigVehicleValue(rrProblem.getVehicle());
        List<Map<String, Object>> mapList = this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicle(dpcoiConfigVehicleQuery);
        if(mapList == null || mapList.size() == 0){
            throw new ServiceException("车型不存在！");
        }else if(mapList.size() > 1){
            throw new ServiceException("车型存在多个！");
        }else {
            Map<String, Object> map = mapList.get(0);
            rrProblem.setCustomer((String)map.get("configValue"));
        }

        Date happenDate = rrProblem.getHappenDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Calendar> calendarList = this.queryHolidayList(formatter.format(happenDate));
        //第一次
        Date date = this.calculationWorkingDay(happenDate, 2, calendarList);
        rrProblem.setFirstDate(date);

        //第二次
        date = this.calculationWorkingDay(happenDate, 14, calendarList);
        rrProblem.setSecondDate(date);

        //第三次
        date = this.calculationWorkingDay(happenDate, 34, calendarList);
        rrProblem.setThirdDate(date);

        //第四次
        date = this.calculationWorkingDay(happenDate, 40, calendarList);
        rrProblem.setFourthDate(date);

        //问题进度
        this.updateSpeedOfProgress(rrProblem);

        //追踪等级
        this.updateTrackingLevel(rrProblem);

        // 发邮件--通知除发起人之外的责任人，以及所有责任人的一级领导
        TimeTask timeTask = new TimeTask();
        timeTask.setNoticeType(45);
        StringBuffer comment = new StringBuffer();
        comment.append("状态:").append(rrProblem.getProblemStatus())
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
                .append("<br>").append("永久对策:").append(rrProblem.getPermanentGame())
                .append("<br>").append("进度").append(rrProblem.getSpeedOfProgress());
        timeTask.setComment(comment.toString());
        String emailUser = this.rRDelayLeaderDao.selectAddRREmail(rrProblem.getCreateBy(), rrProblem.getPersionLiable());
        timeTask.setUserEmail(emailUser);
        timeTask.setDeleteState(0);
        timeTask.setEmailTitle(rrProblem.getProblemNo());
        this.timeTaskDao.insertTimeTask(timeTask);

        return this.rRProblemDao.insertRRProblem(rrProblem);
    }

    @Override
    public Integer updateRRProblem(RRProblem rrProblem) throws ServiceException {
        return this.rRProblemDao.updateRRProblem(rrProblem);
    }

    @Override
    public List<Map<String, Object>> queryRRProblemPageList(RRProblemQuery rrProblemQuery) throws ServiceException {
        return this.rRProblemDao.selectRRProblemListPage(rrProblemQuery);
    }

    @Override
    public Integer queryRRProblemCount(RRProblemQuery rrProblemQuery) throws ServiceException {
        return this.rRProblemDao.selectRRProblemCount(rrProblemQuery);
    }

    @Override
    public Map<String, Object> getHappenDateRandom() throws ServiceException {
        Map<String, Object> map = new HashedMap();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        map.put("endDate", formatter.format(nowDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        while(Calendar.SATURDAY == weekDay || Calendar.SUNDAY == weekDay){
            cal.add(Calendar.DAY_OF_MONTH, -1);
            weekDay = cal.get(Calendar.DAY_OF_WEEK);
        }
        nowDate = cal.getTime();
        map.put("startDate", formatter.format(nowDate));
        return map;
    }

    @Override
    public List<Map<String, Object>> queryPersionLiableList() throws ServiceException {
        return this.rRProblemDao.selectPersionLiableList();
    }

    @Override
    public RRProblem queryRRProblem(RRProblem rrProblem) throws ServiceException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
        rrProblem.setHappenDateStr(formatter.format(rrProblem.getHappenDate()));
        rrProblem.setReportDateStr(formatter.format(rrProblem.getReportDate()));
        rrProblem.setFirstDateStr(formatter.format(rrProblem.getFirstDate()));
        rrProblem.setSecondDateStr(formatter.format(rrProblem.getSecondDate()));
        rrProblem.setThirdDateStr(formatter.format(rrProblem.getThirdDate()));
        rrProblem.setFourthDateStr(formatter.format(rrProblem.getFourthDate()));
        return rrProblem;
    }

    @Override
    public Integer queryMinisterJurisdiction(User user) throws ServiceException {
        return this.userDao.selectMinisterJurisdiction(user);
    }

    @Override
    public List<RRProblem> queryJobRRProblemList() throws ServiceException {
        return this.rRProblemDao.selectJobRRProblemList();
    }

    @Override
    public void updateSpeedOfProgress(RRProblem rrProblem) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String speedOfProgress = "follow";
        String problemProgress = rrProblem.getProblemProgress();
        Date reportDate = rrProblem.getReportDate();
        String reportDateStr = df.format(reportDate);
        Date nowDate = new Date();
        String nowDateStr = df.format(nowDate);
        if("2/5".equals(problemProgress)){
            Date firstDate = rrProblem.getFirstDate();
            String firstDateStr = df.format(firstDate);
            if(reportDateStr.compareTo(nowDateStr) < 0){
                speedOfProgress = "delayIV";
            }
            if(reportDateStr.compareTo(firstDateStr) > 0){
                speedOfProgress = "delayIV";
                List<Calendar> calendarList = this.queryHolidayList(firstDateStr);
                int day = this.daysBetween(firstDateStr, reportDateStr, calendarList);
                if(day > 1){
                    speedOfProgress = "delayII";
                }
            }
        }else if("3/5".equals(problemProgress)){
            Date secondDate = rrProblem.getSecondDate();
            String secondDateStr = df.format(secondDate);
            if(reportDateStr.compareTo(nowDateStr) < 0){
                speedOfProgress = "delayIV";
            }
            if(reportDateStr.compareTo(secondDateStr) > 0){
                speedOfProgress = "delayIV";
                List<Calendar> calendarList = this.queryHolidayList(secondDateStr);
                int day = this.daysBetween(secondDateStr, reportDateStr, calendarList);
                if(day > 7){
                    speedOfProgress = "delayII";
                }else if(day > 4){
                    speedOfProgress = "delayIII";
                }else {
                    speedOfProgress = "delayIV";
                }
            }
        }else if("4/5".equals(problemProgress)){
            Date thirdDate = rrProblem.getThirdDate();
            String thirdDateStr = df.format(thirdDate);
            if(reportDateStr.compareTo(nowDateStr) < 0){
                speedOfProgress = "delayII";
            }
            if(reportDateStr.compareTo(thirdDateStr) > 0){
                speedOfProgress = "delayII";
            }
        }else if("5/5".equals(problemProgress)){
            Date fourthDate = rrProblem.getFourthDate();
            String fourthDateStr = df.format(fourthDate);
            if(reportDateStr.compareTo(nowDateStr) < 0){
                speedOfProgress = "delayI";
            }
            if(reportDateStr.compareTo(fourthDateStr) > 0){
                speedOfProgress = "delayI";
            }
        }else {

        }
        rrProblem.setSpeedOfProgress(speedOfProgress);
    }

    @Override
    public void updateTrackingLevel(RRProblem rrProblem) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String problemProgress = rrProblem.getProblemProgress();
        Date nowDate = new Date();
        String nowDateStr = df.format(nowDate);
        Date hanppenDate = rrProblem.getHappenDate();
        String hanppenDateStr = df.format(hanppenDate);
        List<Calendar> calendarList = this.queryHolidayList(hanppenDateStr);
        String trackingLevel = "V";
        int day = this.daysBetween(hanppenDateStr, nowDateStr, calendarList);
        Integer isDelay = rrProblem.getIsDelay();
        day = day - 1;
        if("1/5".equals(problemProgress)){
            Date createTime = null;
            Integer rrProblemId = rrProblem.getId();
            if(rrProblemId == null){
                createTime = rrProblem.getCreateTime();
            }else {
                RRProblem oldRRProblem = new RRProblem();
                oldRRProblem.setId(rrProblemId);
                oldRRProblem = this.queryRRProblem(oldRRProblem);
                createTime = oldRRProblem.getCreateTime();
            }
            if(createTime == null){
                String oldTrackingLevel = rrProblem.getTrackingLevel();
                if(oldTrackingLevel == null){

                }else {
                    trackingLevel = oldTrackingLevel;
                }
            }else {
               Long intervalHour = (nowDate.getTime() - createTime.getTime())/1000/60/60;
               if(intervalHour.intValue() > 24){
                   trackingLevel = "II";
               }else if(intervalHour.intValue() > 12){
                   trackingLevel = "III";
               }else if(intervalHour.intValue() > 4){
                   trackingLevel = "IV";
               }else {
                   trackingLevel = "V";
               }
            }
        }else if("2/5".equals(problemProgress)){
            if(isDelay == 1){
                if(day <= 7){
                    trackingLevel = "V";
                }else {
                    trackingLevel = "II";
                }
            }else {
                if(day <= 2){
                    trackingLevel = "V";
                }else if(day <= 4){
                    trackingLevel = "IV";
                }else if(day <= 7){
                    trackingLevel = "III";
                }else {
                    trackingLevel = "II";
                }
            }

        }else if("3/5".equals(problemProgress)){
            if(isDelay == 1){
                if(day <= 14){
                    trackingLevel = "V";
                }else {
                    trackingLevel = "II";
                }
            }else {
                if(day <= 7){
                    trackingLevel = "V";
                }else if(day <= 9){
                    trackingLevel = "IV";
                }else if(day <= 14){
                    trackingLevel = "III";
                }else {
                    trackingLevel = "II";
                }
            }
        }else if("4/5".equals(problemProgress)){
            if(isDelay == 1){
                if(day <= 34){
                    trackingLevel = "V";
                }else {
                    trackingLevel = "II";
                }
            }else {
                if(day <= 17){
                    trackingLevel = "V";
                }else if(day <= 21){
                    trackingLevel = "IV";
                }else if(day <= 34){
                    trackingLevel = "III";
                }else {
                    trackingLevel = "II";
                }
            }
        }else if("5/5".equals(problemProgress)){
            if(isDelay == 1){
                if(day <= 40){
                    trackingLevel = "V";
                }else {
                    trackingLevel = "I";
                }
            }else {
                if(day <= 20){
                    trackingLevel = "V";
                }else if(day <= 24){
                    trackingLevel = "IV";
                }else if(day <= 40){
                    trackingLevel = "III";
                }else {
                    trackingLevel = "I";
                }
            }
        }
        rrProblem.setTrackingLevel(trackingLevel);
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    private int daysBetween(String smdate, String bdate, List<Calendar> calendarList) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Integer num = 0;
        Calendar cal = Calendar.getInstance();
        while (bdate.compareTo(smdate) >= 0){
            cal.setTime(sdf.parse(smdate));
            boolean holidayFlag = checkHoliday(cal, calendarList);
            if(!holidayFlag){
                num ++;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
            smdate = sdf.format(cal.getTime());
        }
        return num;
    }

    @Override
    public String queryDelayEmails(RRProblem rrProblem) throws ServiceException {
        return this.rRProblemDao.selectDelayEmails(rrProblem);
    }

    @Override
    public List<Map<String, Object>> queryRRProblemScreenShowList() throws ServiceException {
        return this.rRProblemDao.selectRRProblemScreenShowList();
    }

    @Override
    public FileUpload addUploadFile(Integer rrProblemId, String fileAttr, MultipartFile file, String path, User user) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        int index = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(index);
        String fileAlias = UUID.randomUUID().toString() + fileSuffix;
        String filePath = path + "fileupload/" + fileAlias;
        file.transferTo(new File(filePath));

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileAlias(fileAlias);
        fileUpload.setFileType(fileType);
        fileUpload.setExcelPdfName("");
        fileUpload.setCreateDate(new Date());
        fileUpload.setCreateBy(user.getUserId());
        this.fileUploadDao.insertFileUpload(fileUpload);

        if(rrProblemId != null){
            RRProblem rrProblem = new RRProblem();
            rrProblem.setId(rrProblemId);
            if("serialNumber".equals(fileAttr)){//品情联编号
                rrProblem.setSerialNumberFileId(fileUpload.getFileId());
                rrProblem.setSerialNumber(formatter.format(new Date()));
            }else if("qualityWarningCardNumber".equals(fileAttr)){
                rrProblem.setQualityWarningCardNumber(formatter.format(new Date()));
                rrProblem.setQualityWarningCardNumberFileId(fileUpload.getFileId());
            }else if("productScale".equals(fileAttr)){
                rrProblem.setProductScale(formatter.format(new Date()));
                rrProblem.setProductScaleFileId(fileUpload.getFileId());
            }else if("equipmentChecklist".equals(fileAttr)){
                rrProblem.setEquipmentChecklist(formatter.format(new Date()));
                rrProblem.setEquipmentChecklistFileId(fileUpload.getFileId());
            }else if("inspectionReferenceBook".equals(fileAttr)){
                rrProblem.setInspectionReferenceBook(formatter.format(new Date()));
                rrProblem.setInspectionReferenceBookFileId(fileUpload.getFileId());
            }else if("inspectionBook".equals(fileAttr)){
                rrProblem.setInspectionBook(formatter.format(new Date()));
                rrProblem.setInspectionBookFileId(fileUpload.getFileId());
            }else if("education".equals(fileAttr)){
                rrProblem.setEducation(formatter.format(new Date()));
                rrProblem.setEducationFileId(fileUpload.getFileId());
            }else if("analyticReport".equals(fileAttr)){
                rrProblem.setAnalyticReport(formatter.format(new Date()));
                rrProblem.setAnalyticReportFileId(fileUpload.getFileId());
            }else if("layeredAudit".equals(fileAttr)){
                rrProblem.setLayeredAudit(formatter.format(new Date()));
                rrProblem.setLayeredAuditFileId(fileUpload.getFileId());
            }else if("checkResult".equals(fileAttr)){
                rrProblem.setCheckResult(formatter.format(new Date()));
                rrProblem.setCheckResultFileId(fileUpload.getFileId());
            }else if("naPending".equals(fileAttr)){
                rrProblem.setNaPending(formatter.format(new Date()));
                rrProblem.setNaPendingFileId(fileUpload.getFileId());
            }else if("otherInformation".equals(fileAttr)){
                rrProblem.setOtherInformation(formatter.format(new Date()));
                rrProblem.setOtherInformationFileId(fileUpload.getFileId());
            }else if("containmentWorksheet".equals(fileAttr)){
                rrProblem.setContainmentWorksheet(formatter.format(new Date()));
                rrProblem.setContainmentWorksheetFileId(fileUpload.getFileId());
            }
            this.rRProblemDao.updateRRProblem(rrProblem);
        }
        return fileUpload;
    }

    @Override
    public FileUpload addUploadFile2(Integer rrProblemId, String fileAttr, MultipartFile file, String path, User user) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        int index = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(index);
        String fileAlias = UUID.randomUUID().toString() + fileSuffix;
        String filePath = path + "fileupload/" + fileAlias;
        file.transferTo(new File(filePath));

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileAlias(fileAlias);
        fileUpload.setFileType(fileType);
        fileUpload.setExcelPdfName("");
        fileUpload.setCreateDate(new Date());
        fileUpload.setCreateBy(user.getUserId());
        this.fileUploadDao.insertFileUpload(fileUpload);
        if (rrProblemId != null) {
            //查询对应的pfmea\cp\作业指导书
            DpcoiOrder dpcoiOrder = this.dpcoiOrderDao.selectDpcoiOrderByRRProblem(rrProblemId);
            DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
            dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
            List<DpcoiWoOrder> dpcoiWoOrderList = this.dpcoiWoOrderDao.selectDpcoiWoOrderOfDpcoiOrder(dpcoiWoOrderQuery);
            Integer dpcoiWoOrderId = null;
            for (DpcoiWoOrder dpcoiWoOrder : dpcoiWoOrderList){
                if ("pfmea".equals(fileAttr) && dpcoiWoOrder.getDpcoiWoOrderType() == 1){
                    dpcoiWoOrderId = dpcoiWoOrder.getDpcoiWoOrderId();
                } else if ("cp".equals(fileAttr) && dpcoiWoOrder.getDpcoiWoOrderType() == 2) {
                    dpcoiWoOrderId = dpcoiWoOrder.getDpcoiWoOrderId();
                } else if ("standardBook".equals(fileAttr) && dpcoiWoOrder.getDpcoiWoOrderType() == 3) {
                    dpcoiWoOrderId = dpcoiWoOrder.getDpcoiWoOrderId();
                }
            }
            if (dpcoiWoOrderId != null) {
                DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery = new DpcoiWoOrderFileQuery();
                dpcoiWoOrderFileQuery.setDpcoiWoOrderId(dpcoiWoOrderId);
                List<Map<String, Object>> mapList  = this.dpcoiWoOrderFileDao.selectDpcoiWoOrderFileList(dpcoiWoOrderFileQuery);
                for (Map<String, Object> map : mapList) {
                    DpcoiWoOrderFile dpcoiWoOrderFileTemp = new DpcoiWoOrderFile();
                    Integer id = (Integer) map.get("dpcoiWoOrderFileId");
                    dpcoiWoOrderFileTemp.setId(id);
                    dpcoiWoOrderFileTemp.setUpdateDate(new Date());
                    dpcoiWoOrderFileTemp.setUpdateBy(user.getUserId());
                    dpcoiWoOrderFileTemp.setDelFlag("1");
                    this.dpcoiWoOrderFileDao.updateDpcoiWoOrderFile(dpcoiWoOrderFileTemp);
                }

                DpcoiWoOrderFile dpcoiWoOrderFile = new DpcoiWoOrderFile();
                dpcoiWoOrderFile.setFileId(fileUpload.getFileId());
                dpcoiWoOrderFile.setDpcoiWoOrderId(dpcoiWoOrderId);
                dpcoiWoOrderFile.setDelFlag("0");
                dpcoiWoOrderFile.setWoFileState(1);
                dpcoiWoOrderFile.setCreateBy(user.getUserId());
                dpcoiWoOrderFile.setCreateDate(new Date());
                dpcoiWoOrderFile.setUpdateDate(new Date());
                dpcoiWoOrderFile.setUpdateBy(user.getUserId());
                this.dpcoiWoOrderFileDao.insertDpcoiWoOrderFile(dpcoiWoOrderFile);
            }
        }
        return fileUpload;
    }

    @Override
    public void addSendMinisterEmail(RRProblem rrProblem) throws ServiceException {
        TimeTask timeTask = new TimeTask();
        timeTask.setNoticeType(39);
        StringBuffer comment = new StringBuffer();
        comment.append("状态:").append(rrProblem.getProblemStatus())
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
                .append("<br>").append("永久对策:").append(rrProblem.getPermanentGame())
                .append("<br>").append("进度").append(rrProblem.getSpeedOfProgress());
        timeTask.setComment(comment.toString());
        String emailUser = this.rRDelayLeaderDao.selectMinisteEmail();
        timeTask.setUserEmail(emailUser);
        timeTask.setDeleteState(0);
        timeTask.setEmailTitle(rrProblem.getProblemNo());
        this.timeTaskDao.insertTimeTask(timeTask);
    }

    @Override
    public List<RRProblem> queryJobRRProblemTrackingLevelList() throws ServiceException {
        return this.rRProblemDao.selectJobRRProblemTrackingLevelList();
    }

    @Override
    public Map<String, Object> getFourDate(String happenDate) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Calendar> calendarList = this.queryHolidayList(happenDate);
        //第一次
        Date date = this.calculationWorkingDay(formatter.parse(happenDate), 2, calendarList);
        map.put("firstDate", formatter.format(date));

        //第二次
        date = this.calculationWorkingDay(formatter.parse(happenDate), 14, calendarList);
        map.put("secondDate", formatter.format(date));

        //第三次
        date = this.calculationWorkingDay(formatter.parse(happenDate), 34, calendarList);
        map.put("thirdDate", formatter.format(date));

        //第四次
        date = this.calculationWorkingDay(formatter.parse(happenDate), 40, calendarList);
        map.put("fourthDate", formatter.format(date));
        return map;
    }


    /**
     * 计算公式
     * @param startDay 开始日期
     * @param IntervalDays 间隔天数
     */
    private Date calculationWorkingDay(Date startDay, Integer IntervalDays, List<Calendar> holidayList){
        Calendar src = Calendar.getInstance();
        src.setTime(startDay);

        boolean holidayFlag;
        for (int i = 0; i < IntervalDays; i++) {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag =checkHoliday(src, holidayList);
            if(holidayFlag) {
                i--;
            }
        }

        return src.getTime();
    }

    /**
     * 校验指定的日期是否在节日列表中
     * 具体节日包含哪些,可以在HolidayMap中修改
     * @param src 要检验的日期
     */
    private boolean checkHoliday(Calendar src, List<Calendar> holidayList){
        boolean result = false;

        if (holidayList == null) {
            return false;
        }

        for (Calendar c : holidayList) {
            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)
                    && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)
                    && src.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * 获取所有的节假日
     * @return 返回结果
     */
    private List<Calendar> queryHolidayList(String beginDate){
        List<Calendar> calendarList = new LinkedList<Calendar>();
        List<Date> dateList = new LinkedList<Date>();
        if(beginDate == null || "".equals(beginDate)){
            dateList = this.holidayDao.selectHolidayList();
        }else {
            dateList = this.holidayDao.selectHolidayListFromBeginDate(beginDate);
        }
        for(Date date : dateList){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            calendarList.add(cal);
        }
        return calendarList;
    }
}
