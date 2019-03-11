package com.success.task.detail.service.serviceImpl;

import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.dao.TaskWoOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.query.TaskWoOrderQuery;
import com.success.task.detail.service.TaskWoOrderStartService;
import com.success.templet.task.dao.TaskTypeDao;
import com.success.templet.task.domain.TaskType;
import com.success.templet.task.query.TaskTypeQuery;
import com.success.web.framework.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("taskWoOrderStartService")
public class TaskWoOrderStartServiceImpl implements TaskWoOrderStartService {

    @Resource(name = "timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name = "taskOrderDao")
    private TaskOrderDao taskOrderDao;

    @Resource(name = "taskWoOrderDao")
    private TaskWoOrderDao taskWoOrderDao;

    @Resource(name = "taskTypeDao")
    private TaskTypeDao taskTypeDao;

    @Resource(name = "taskOrderInfoDao")
    private TaskOrderInfoDao taskOrderInfoDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public void startWoOrder(int orderId) throws ServiceException {
        // TODO Auto-generated method stub
        TaskWoOrderQuery query = new TaskWoOrderQuery();
        query.setOrderId(orderId);
        TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
        taskOrderQuery.setOrderId(orderId);
        TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
        List<TaskWoOrder> taskWoOrderList = this.taskWoOrderDao.selectTaskWoOrder(query);
        if(taskWoOrderList == null){
            throw new ServiceException("通过变更单主键"+orderId+"查询工单为空，报错！");
        }else {
            TaskOrder newTaskOrder = new TaskOrder();
            newTaskOrder.setOrderId(orderId);
            newTaskOrder.setOrderStateCode("10B");
            newTaskOrder.setNoticeTime(new Date());
            this.taskOrderDao.updateTaskOrder(newTaskOrder);
            for(int i = 0; i < taskWoOrderList.size(); i++){
                TaskWoOrder taskWoOrder = taskWoOrderList.get(i);
                TaskWoOrder newTaskWoOrder = new TaskWoOrder();
                newTaskWoOrder.setWoOrderId(taskWoOrder.getWoOrderId());
                newTaskWoOrder.setWoOrderStateCode("10B");
                newTaskWoOrder.setCreateTime(new Date());
                this.taskWoOrderDao.updateTaskWoOrder(newTaskWoOrder);
            }

            //发邮件通知
            //List<WoOrderEmailUser> woOrderEmailUserList = this.taskWoOrderDao.selectWoOrderEmailUsers(query);
            Integer taskTypeId = taskOrder.getTaskTypeId();
            TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
            taskOrderInfoQuery.setOrderId(orderId);
            List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
            String publishCode = "";
            String productionLine = "";
            String changeTime = "";
            String carType = "";
            String mountingMat = "";
            String changeContent = "";
            for(int h = 0; h < TaskOrderInfoList.size(); h++){
                TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
                Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
                if(taskTypeInfoId == 1){
                    publishCode = taskOrderInfo.getTaskInfoValue();
                }else if(taskTypeInfoId == 2){
                    productionLine = taskOrderInfo.getTaskInfoValue();
                }else if(taskTypeInfoId == 11){
                    changeTime = taskOrderInfo.getTaskInfoValue();
                }else if(taskTypeInfoId == 3){
                    carType = taskOrderInfo.getTaskInfoValue();
                }else if(taskTypeInfoId == 4){
                    mountingMat = taskOrderInfo.getTaskInfoValue();
                }else if(taskTypeInfoId == 9){
                    changeContent = taskOrderInfo.getTaskInfoValue();
                }
            }
            TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
            taskTypeQuery.setTaskTypeId(taskTypeId);
            TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
            String comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
            comment += changeContent + "<br>收到此提醒后，请相关负责担当准时在系统中，需要确认的项目前打勾并签字（电子签字）及预计完成日期";
//			if(woOrderEmailUserList != null){
//				TimeTask timeTask = new TimeTask();
//				String email = "";
//				int len = woOrderEmailUserList.size();
//				for(int j = 0; j < len; j++){
//					WoOrderEmailUser woOrderEmailUser = woOrderEmailUserList.get(j);
//					if(j % 50 == 0){
//						timeTask.setOrderId(orderId);
//						timeTask.setNoticeColor(0);
//						timeTask.setNoticeType(1);
//						timeTask.setPublishCode(publishCode);
//						timeTask.setTaskTypeName(taskType.getTaskTypeName());
//						timeTask.setFailedNum(0);
//						timeTask.setComment(comment);
//						if(j != 0){
//							email = email.substring(1);
//							timeTask.setUserEmail(email);
//							this.timeTaskDao.insertTimeTask(timeTask);
//							email = "";
//							timeTask = new TimeTask();
//							timeTask.setOrderId(orderId);
//							timeTask.setNoticeColor(0);
//							timeTask.setNoticeType(1);
//							timeTask.setPublishCode(publishCode);
//							timeTask.setTaskTypeName(taskType.getTaskTypeName());
//							timeTask.setFailedNum(0);
//							timeTask.setComment(comment);
//						}
//					}
//					email += "," + woOrderEmailUser.getUserEmail();
//				}
//				if((len % 50) != 0){
//					email = email.substring(1);
//					timeTask.setUserEmail(email);
//					this.timeTaskDao.insertTimeTask(timeTask);
//				}
//			}

            //发邮件通知审核人
//			List<User> userList = this.userDao.selectVerifyUser();
//			if(userList != null){
//				String email = "";
//				for(int j = 0; j < userList.size(); j++){
//					email += "," + userList.get(j).getEmail();
//				}
//				if(!"".equals(email)){
//					email = email.substring(1);
//					TimeTask timeTask = new TimeTask();
//					timeTask.setOrderId(orderId);
//					timeTask.setNoticeColor(0);
//					timeTask.setNoticeType(1);
//					timeTask.setPublishCode(publishCode);
//					timeTask.setTaskTypeName(taskType.getTaskTypeName());
//					timeTask.setFailedNum(0);
//					timeTask.setUserEmail(email);
//					timeTask.setComment(comment);
//					this.timeTaskDao.insertTimeTask(timeTask);
//				}
//			}

            //发邮件通知所有人
            UserQuery userQuery = new UserQuery();
            List<User> userList = this.userDao.selectUser(userQuery);
            if(userList != null){
                TimeTask timeTask = new TimeTask();
                String email = "";
                int len = userList.size();
                for(int j = 0; j < len; j++){
                    User user = userList.get(j);
                    if(j % 50 == 0){
                        timeTask.setOrderId(orderId);
                        timeTask.setNoticeColor(0);
                        timeTask.setNoticeType(1);
                        timeTask.setPublishCode(publishCode);
                        timeTask.setTaskTypeName(taskType.getTaskTypeName());
                        timeTask.setFailedNum(0);
                        timeTask.setComment(comment);
                        if(j != 0){
                            email = email.substring(1);
                            timeTask.setUserEmail(email);
                            this.timeTaskDao.insertTimeTask(timeTask);
                            email = "";
                            timeTask = new TimeTask();
                            timeTask.setOrderId(orderId);
                            timeTask.setNoticeColor(0);
                            timeTask.setNoticeType(1);
                            timeTask.setPublishCode(publishCode);
                            timeTask.setTaskTypeName(taskType.getTaskTypeName());
                            timeTask.setFailedNum(0);
                            timeTask.setComment(comment);
                        }
                    }
                    email += "," + user.getEmail();
                }
                if((len % 50) != 0){
                    email = email.substring(1);
                    timeTask.setUserEmail(email);
                    this.timeTaskDao.insertTimeTask(timeTask);
                }
            }
        }
    }
}
