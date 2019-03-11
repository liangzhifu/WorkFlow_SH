package com.success.kirikae.order.service.impl;

import com.success.kirikae.order.dao.KirikaeOrderAgencyDao;
import com.success.kirikae.order.query.KirikaeOrderAgencyQuery;
import com.success.kirikae.order.service.KirikaeOrderAgencyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Service("kirikaeOrderAgencyService")
public class KirikaeOrderAgencyServiceImpl implements KirikaeOrderAgencyService {

    @Resource(name = "kirikaeOrderAgencyDao")
    private KirikaeOrderAgencyDao kirikaeOrderAgencyDao;

    @Override
    public List<Map<String, Object>> listKirikaeOrderAgencyPage(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery) throws Exception {
        return this.kirikaeOrderAgencyDao.selectKirikaeOrderAgencyPageList(kirikaeOrderAgencyQuery);
    }

    @Override
    public Integer countKirikaeOrderAgency(KirikaeOrderAgencyQuery kirikaeOrderAgencyQuery) throws Exception {
        return this.kirikaeOrderAgencyDao.selectKirikaeOrderAgencyCount(kirikaeOrderAgencyQuery);
    }

}
