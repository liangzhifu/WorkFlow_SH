package com.success.kirikae.order.service.impl;

import com.success.kirikae.order.dao.KirikaeOrderHistoryDao;
import com.success.kirikae.order.query.KirikaeOrderHistoryQuery;
import com.success.kirikae.order.service.KirikaeOrderHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeOrderHistoryService")
public class KirikaeOrderHistoryServiceImpl implements KirikaeOrderHistoryService {

    @Resource(name = "kirikaeOrderHistoryDao")
    private KirikaeOrderHistoryDao kirikaeOrderHistoryDao;

    @Override
    public List<Map<String, Object>> listKirikaeOrderHistoryPage(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery) throws Exception {
        return this.kirikaeOrderHistoryDao.selectKirikaeOrderHistoryPageList(kirikaeOrderHistoryQuery);
    }

    @Override
    public Integer countKirikaeOrderHistory(KirikaeOrderHistoryQuery kirikaeOrderHistoryQuery) throws Exception {
        return this.kirikaeOrderHistoryDao.selectKirikaeOrderHistoryCount(kirikaeOrderHistoryQuery);
    }
}
