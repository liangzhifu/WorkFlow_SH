package com.success.task.quartz.service.serviceImpl;

import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.task.detail.service.TaskOrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 * @create 2017-10-30
 **/
//@Component("changeTimeAlarmImpl")
public class ChangeTimeAlarmImpl {

    @Resource(name = "timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name = "taskOrderService")
    private TaskOrderService taskOrderService;

    @Scheduled(cron = "0 30 5 * * ?")
    public void job() {
        try {
            List<Map<String, Object>> mapList = this.taskOrderService.queryChangeTimeAlarmList();
            for(Map map : mapList){
                String productionLine = (String) map.get("productionLine");
                String changeTime = (String) map.get("changeTime");
                String carType = (String) map.get("carType");
                String mountingMat = (String) map.get("mountingMat");
                String changeContent = (String) map.get("changeContent");
                String comment = "";
                comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
                comment += changeContent + "<br>此变更单需要填写实际变更时间，请知晓。";
                String email = (String)map.get("email");
                Integer orderId = (Integer)map.get("orderId");
                String publishCode = (String)map.get("publishCode");
                TimeTask timeTask = new TimeTask();
                timeTask.setOrderId(orderId);
                timeTask.setNoticeColor(0);
                timeTask.setNoticeType(40);
                timeTask.setPublishCode(publishCode);
                timeTask.setEmailTitle(publishCode);
                timeTask.setTaskTypeName("变更确认通知");
                timeTask.setFailedNum(0);
                timeTask.setComment(comment);
                timeTask.setUserEmail(email);
                this.timeTaskDao.insertTimeTask(timeTask);
            }
        } catch (Exception e) {

        }
    }
}
