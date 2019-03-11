package com.success.kirikae.order.util;

import com.success.kirikae.order.constant.KirikaeOrderEnum;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.sys.email.domain.TimeTask;
import com.success.system.user.domain.SystemUser;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lzf
 **/

public class KirikaeOrderUtil {

    public static List<TimeTask> generateMail(KirikaeOrder kirikaeOrder, List<SystemUser> systemUserList, String title){
        List<TimeTask> timeTaskList = new LinkedList<TimeTask>();
        if(systemUserList != null){
            KirikaeOrderEnum.KirikaeOrderTypeEnum kirikaeOrderTypeEnum = KirikaeOrderEnum.KirikaeOrderTypeEnum.getKirikaeOrderTypeEnumByCode(kirikaeOrder.getKirikaeOrderType());
            KirikaeOrderEnum.KirikaeOrderStateEnum kirikaeOrderStateEnum = KirikaeOrderEnum.KirikaeOrderStateEnum.getKirikaeOrderStateEnumByCode(kirikaeOrder.getKirikaeOrderState());
            title = title + "：" + kirikaeOrder.getTkNo();
            StringBuffer comment = new StringBuffer();
            comment.append("设变号：").append(kirikaeOrder.getTkNo())
                    .append("<br>").append("车种：").append(kirikaeOrder.getVehicleName())
                    .append("<br>").append("客户：").append(kirikaeOrder.getCustomer())
                    .append("<br>").append("设变类型：").append(kirikaeOrderTypeEnum.getMsg())
                    .append("<br>").append("切替状态：").append(kirikaeOrderStateEnum.getMsg());
            TimeTask timeTask = new TimeTask();
            int len = systemUserList.size();
            String userEmail = "";
            for(int i = 0; i < len; i++){
                SystemUser systemUser = systemUserList.get(i);
                if((i > 0) && (i % 20 == 0)){
                    timeTask = new TimeTask();
                    timeTask.setDeleteState(0);
                    timeTask.setNoticeType(1001);
                    if (userEmail.length() > 0){
                        userEmail = userEmail.substring(1);
                    }
                    timeTask.setUserEmail(userEmail);
                    timeTask.setEmailTitle(title);
                    timeTask.setComment(comment.toString());
                    timeTaskList.add(timeTask);
                    userEmail = "";
                }else {
                    userEmail += "," + systemUser.getUserEmail();
                }
            }
            if(len % 20 != 0){
                timeTask.setDeleteState(0);
                timeTask.setNoticeType(1001);
                if (userEmail.length() > 0){
                    userEmail = userEmail.substring(1);
                }
                timeTask.setUserEmail(userEmail);
                timeTask.setEmailTitle(title);
                timeTask.setComment(comment.toString());
                timeTaskList.add(timeTask);
            }
        }

        return timeTaskList;
    }

}
