package com.success.task.base.service.serviceImpl;

import com.success.sys.config.dao.SysConfigDao;
import com.success.sys.config.domain.SysConfig;
import com.success.sys.email.dao.TimeTask2Dao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.staff.dao.StaffDao;
import com.success.sys.staff.query.StaffQuery;
import com.success.sys.user.dao.UserDao;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.task.base.dao.TaskConfirmOrderDao;
import com.success.task.base.dao.TaskOrderInfoDao;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.query.TaskConfirmOrderQuery;
import com.success.task.base.query.TaskOrderInfoQuery;
import com.success.task.base.service.TaskConfirmOrderStartService;
import com.success.task.detail.dao.TaskOrderDao;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
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
@Service("taskConfirmOrderStartService")
public class TaskConfirmOrderStartServiceImpl implements TaskConfirmOrderStartService {

    @Resource(name = "taskConfirmOrderDao")
    private TaskConfirmOrderDao taskConfirmOrderDao;

    @Resource(name = "taskOrderDao")
    private TaskOrderDao taskOrderDao;

    @Resource(name = "staffDao")
    private StaffDao staffDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "sysConfigDao")
    private SysConfigDao sysConfigDao;

    @Resource(name = "timeTask2Dao")
    private TimeTask2Dao timeTaskDao;

    @Resource(name = "taskTypeDao")
    private TaskTypeDao taskTypeDao;

    @Resource(name = "taskOrderInfoDao")
    private TaskOrderInfoDao taskOrderInfoDao;

    @Resource(name = "taskWoOrderStartService")
    private TaskWoOrderStartService taskWoOrderStartService;

    @Override
    public void startTaskConfirmOrder(Integer orderId, Integer confirmUserSeq) throws ServiceException {
        TaskConfirmOrderQuery query = new TaskConfirmOrderQuery();
        query.setOrderId(orderId);
        query.setConfirmUserSeq(confirmUserSeq);
        List<TaskConfirmOrder> TaskConfirmOrderList = this.taskConfirmOrderDao.selectTaskConfirmOrder(query);
        if(TaskConfirmOrderList == null || (TaskConfirmOrderList.size() == 0)){
            TaskOrder taskOrder = new TaskOrder();
            taskOrder.setOrderId(orderId);
            taskOrder.setConfirmOrderStateCode("10C");
            taskOrder.setRealCompleteTime(new Date());
            this.taskOrderDao.updateTaskOrder(taskOrder);
        }else if(TaskConfirmOrderList.size() > 1){
            throw new ServiceException("查询确认单"+orderId+","+confirmUserSeq+"出错");
        }else {
            TaskConfirmOrder taskConfirmOrder = TaskConfirmOrderList.get(0);
            TaskConfirmOrder newTaskConfirmOrder = new TaskConfirmOrder();

            String runType = taskConfirmOrder.getRunType();
            if ("input".equals(runType)) {
                newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
                newTaskConfirmOrder.setConfirmOrderStateCode("10B");
                this.taskConfirmOrderDao.updateTaskConfirmOrder(newTaskConfirmOrder);
                this.taskWoOrderStartService.startWoOrder(orderId);
            } else {
                String runCode = taskConfirmOrder.getRunCode();
                if ("accept_confirm".equals(runCode)) {
                    newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
                    newTaskConfirmOrder.setConfirmOrderStateCode("10C");
                    this.taskConfirmOrderDao.updateTaskConfirmOrder(newTaskConfirmOrder);
                    //发送邮件通知所有领导，变更单已经做成
                    TimeTask timeTask = new TimeTask();
                    UserQuery userQuery = new UserQuery();
                    userQuery.setIsHeader("1");
                    List<User> userList = this.userDao.selectUser(userQuery);
                    String userEmain = "";
                    for (int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);
                        userEmain += "," + user.getEmail();
                    }
                    if (userEmain.length() > 0) {
                        userEmain = userEmain.substring(1);
                    }
                    timeTask.setUserEmail(userEmain);

                    timeTask.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());

                    TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
                    taskOrderQuery.setOrderId(orderId);
                    TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
                    Integer taskTypeId = taskOrder.getTaskTypeId();
                    timeTask.setOrderId(taskOrder.getOrderId());
                    timeTask.setNoticeColor(1);
                    timeTask.setNoticeType(9);

                    TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
                    taskOrderInfoQuery.setOrderId(orderId);
                    List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
                    String publishCode = "";
                    String productionLine = "";
                    String changeTime = "";
                    String carType = "";
                    String mountingMat = "";
                    String changeContent = "";
                    for (int h = 0; h < TaskOrderInfoList.size(); h++) {
                        TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
                        Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
                        if (taskTypeInfoId == 1) {
                            publishCode = taskOrderInfo.getTaskInfoValue();
                        } else if (taskTypeInfoId == 2) {
                            productionLine = taskOrderInfo.getTaskInfoValue();
                        } else if (taskTypeInfoId == 11) {
                            changeTime = taskOrderInfo.getTaskInfoValue();
                        } else if (taskTypeInfoId == 3) {
                            carType = taskOrderInfo.getTaskInfoValue();
                        } else if (taskTypeInfoId == 4) {
                            mountingMat = taskOrderInfo.getTaskInfoValue();
                        } else if (taskTypeInfoId == 9) {
                            changeContent = taskOrderInfo.getTaskInfoValue();
                        }
                    }
                    timeTask.setPublishCode(publishCode);
                    String comment = "";
                    comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
                    comment += changeContent + "<br>此变更单已作成，请知晓。";
                    timeTask.setComment(comment);

                    TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
                    taskTypeQuery.setTaskTypeId(taskTypeId);
                    TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
                    timeTask.setTaskTypeName(taskType.getTaskTypeName());

                    timeTask.setFailedNum(0);
                    this.timeTaskDao.insertTimeTask(timeTask);

                    this.startTaskConfirmOrder(orderId, confirmUserSeq + 1);
                } else {
                    Integer userId = taskConfirmOrder.getConfirmUserId();
                    if (userId == null) {
                        if ("accept2_confirm".equals(runCode)) {
                            newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
                            newTaskConfirmOrder.setConfirmOrderStateCode("10B");
                            this.taskConfirmOrderDao.updateTaskConfirmOrder(newTaskConfirmOrder);
                        } else {
                            newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
                            newTaskConfirmOrder.setConfirmOrderStateCode("10C");
                            this.taskConfirmOrderDao.updateTaskConfirmOrder(newTaskConfirmOrder);
                            this.startTaskConfirmOrder(orderId, confirmUserSeq + 1);
                        }
                    } else {
                        newTaskConfirmOrder.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());
                        newTaskConfirmOrder.setConfirmOrderStateCode("10B");
                        this.taskConfirmOrderDao.updateTaskConfirmOrder(newTaskConfirmOrder);

                        //发送邮件通知
                        SysConfig SysConfig = this.sysConfigDao.selectSysConfig("config_notice");
                        String configValue = SysConfig.getConfigValue();
                        if ("1".equals(configValue)) {
                            TimeTask timeTask = new TimeTask();
                            StaffQuery staffQuery = new StaffQuery();
                            staffQuery.setId(userId);
                            User user = this.staffDao.selectStaffByIdQuery(staffQuery);
                            timeTask.setUserId(user.getUserId());
                            timeTask.setUserName(user.getUserName());
                            timeTask.setUserEmail(user.getEmail());

                            timeTask.setConfirmOrderId(taskConfirmOrder.getConfirmOrderId());

                            TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
                            taskOrderQuery.setOrderId(orderId);
                            TaskOrder taskOrder = this.taskOrderDao.selectTaskOrderByIdQuery(taskOrderQuery);
                            Integer taskTypeId = taskOrder.getTaskTypeId();
                            timeTask.setOrderId(taskOrder.getOrderId());
                            timeTask.setNoticeColor(1);
                            timeTask.setNoticeType(7);

                            TaskOrderInfoQuery taskOrderInfoQuery = new TaskOrderInfoQuery();
                            taskOrderInfoQuery.setOrderId(orderId);
                            List<TaskOrderInfo> TaskOrderInfoList = this.taskOrderInfoDao.selectTaskOrderInfo(taskOrderInfoQuery);
                            String publishCode = "";
                            String productionLine = "";
                            String changeTime = "";
                            String carType = "";
                            String mountingMat = "";
                            String changeContent = "";
                            for (int h = 0; h < TaskOrderInfoList.size(); h++) {
                                TaskOrderInfo taskOrderInfo = TaskOrderInfoList.get(h);
                                Integer taskTypeInfoId = taskOrderInfo.getTaskTypeInfoId();
                                if (taskTypeInfoId == 1) {
                                    publishCode = taskOrderInfo.getTaskInfoValue();
                                } else if (taskTypeInfoId == 2) {
                                    productionLine = taskOrderInfo.getTaskInfoValue();
                                } else if (taskTypeInfoId == 11) {
                                    changeTime = taskOrderInfo.getTaskInfoValue();
                                } else if (taskTypeInfoId == 3) {
                                    carType = taskOrderInfo.getTaskInfoValue();
                                } else if (taskTypeInfoId == 4) {
                                    mountingMat = taskOrderInfo.getTaskInfoValue();
                                } else if (taskTypeInfoId == 9) {
                                    changeContent = taskOrderInfo.getTaskInfoValue();
                                }
                            }
                            timeTask.setPublishCode(publishCode);
                            String comment = "";
                            comment = "你好：" + productionLine + " " + changeTime + " " + carType + " " + mountingMat + " ";
                            comment += changeContent + "<br>此变更单需要你的确认，请知晓。";
                            timeTask.setComment(comment);

                            TaskTypeQuery taskTypeQuery = new TaskTypeQuery();
                            taskTypeQuery.setTaskTypeId(taskTypeId);
                            TaskType taskType = this.taskTypeDao.selectTaskTypeByIdQuery(taskTypeQuery);
                            timeTask.setTaskTypeName(taskType.getTaskTypeName());

                            timeTask.setFailedNum(0);
                            this.timeTaskDao.insertTimeTask(timeTask);
                        }
                    }
                }
            }
        }
    }
}
