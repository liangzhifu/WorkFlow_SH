package com.success.task.detail.service;

import com.success.web.framework.exception.ServiceException;

/**
 * @author lzf
 **/

public interface TaskWoOrderStartService {

    public void startWoOrder(int orderId) throws ServiceException;

}
