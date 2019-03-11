package com.success.task.detail.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.web.framework.exception.ServiceException;

public interface TaskOrderService {

	public Integer addTaskOrder(TaskOrder taskOrder) throws ServiceException;
	
	public Integer editTaskOrder(TaskOrder taskOrder) throws ServiceException;
	
	public Integer deleteTaskOrder(TaskOrder taskOrder) throws ServiceException;
	
	public TaskOrder getTaskOrderDetail(TaskOrderQuery query) throws ServiceException;
	
	public TaskOrder getStaffTaskOrderDetail(TaskOrderQuery query, Integer userId) throws ServiceException;
	
	public TaskOrder selectTaskOrderByIdQuery(TaskOrderQuery query) throws ServiceException;
	
	public void editTaskChangeTime(Map<String, Object> map) throws Exception;
	
	public Integer updateTaskOrder(TaskOrder taskOrder) throws ServiceException;
	
	public Integer updateTaskOrderContract(TaskOrder taskOrder) throws ServiceException;

	/**
	 * 判断4M变更单是否需要立合
	 * @param query 查询条件
	 * @return 返回结果
	 * @throws ServiceException 异常
	 */
	public Integer getAgreementNum(TaskOrderQuery query) throws ServiceException;

	/**
	 * 查询未填写真实变更时间的订单列表
	 * @return 返回结果
	 * @throws ServiceException 异常
	 */
	public List<Map<String, Object>> queryChangeTimeAlarmList() throws ServiceException;

	/**
	 * 修改DPCOI订单的实际变更时间
	 * @param orderId 订单ID
	 * @throws Exception 异常
	 */
	public void updateDpcoiOrderChangeTime(Integer orderId, String realCuttingDate) throws Exception;
	
}
