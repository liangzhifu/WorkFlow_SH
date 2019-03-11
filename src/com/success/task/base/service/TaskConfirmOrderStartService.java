package com.success.task.base.service;

import com.success.web.framework.exception.ServiceException;

public interface TaskConfirmOrderStartService {

    public void startTaskConfirmOrder(Integer orderId, Integer confirmUserSeq) throws ServiceException;

}
