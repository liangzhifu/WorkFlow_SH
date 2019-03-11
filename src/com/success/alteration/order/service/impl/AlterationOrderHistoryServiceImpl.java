package com.success.alteration.order.service.impl;

import com.success.alteration.order.dao.AlterationOrderHistoryDao;
import com.success.alteration.order.domain.AlterationOrderHistory;
import com.success.alteration.order.service.AlterationOrderHistoryService;
import com.success.four.order.dao.FourOrderAttrHistoryDao;
import com.success.four.order.dao.FourOrderHistoryDao;
import com.success.four.order.domain.FourOrder;
import com.success.four.order.domain.FourOrderAttr;
import com.success.kirikae.order.dao.KirikaeOrderChangeContentHistoryDao;
import com.success.kirikae.order.dao.KirikaeOrderHistoryDao;
import com.success.kirikae.order.dao.KirikaeOrderPartsNumberHistoryDao;
import com.success.kirikae.order.dao.KirikaeResumeHistoryDao;
import com.success.kirikae.order.domain.KirikaeOrder;
import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.domain.KirikaeOrderPartsNumber;
import com.success.kirikae.order.domain.KirikaeResume;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzf
 **/
@Service("alterationOrderHistoryService")
public class AlterationOrderHistoryServiceImpl implements AlterationOrderHistoryService {

    @Resource(name = "alterationOrderHistoryDao")
    private AlterationOrderHistoryDao alterationOrderHistoryDao;

    @Resource(name = "fourOrderHistoryDao")
    private FourOrderHistoryDao fourOrderHistoryDao;

    @Resource(name = "fourOrderAttrHistoryDao")
    private FourOrderAttrHistoryDao fourOrderAttrHistoryDao;

    @Resource(name = "kirikaeOrderHistoryDao")
    private KirikaeOrderHistoryDao kirikaeOrderHistoryDao;

    @Resource(name = "kirikaeOrderChangeContentHistoryDao")
    private KirikaeOrderChangeContentHistoryDao kirikaeOrderChangeContentHistoryDao;

    @Resource(name = "kirikaeOrderPartsNumberHistoryDao")
    private KirikaeOrderPartsNumberHistoryDao kirikaeOrderPartsNumberHistoryDao;

    @Resource(name = "kirikaeResumeHistoryDao")
    private KirikaeResumeHistoryDao kirikaeResumeHistoryDao;

    @Override
    public AlterationOrderHistory getAlterationOrderHistory(AlterationOrderHistory alterationOrderHistory) throws Exception {
        alterationOrderHistory = this.alterationOrderHistoryDao.selectByPrimaryKey(alterationOrderHistory);

        FourOrder fourOrder = this.fourOrderHistoryDao.selectFourOrderHistoryByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setFourOrder(fourOrder);

        List<FourOrderAttr> fourOrderAttrList = this.fourOrderAttrHistoryDao.selectFourOrderAttrHistoryListByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setFourOrderAttrList(fourOrderAttrList);

        KirikaeOrder kirikaeOrder = this.kirikaeOrderHistoryDao.selectKirikaeOrderHistoryByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setKirikaeOrder(kirikaeOrder);

        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentHistoryDao.selectKirikaeOrderChangeContentHistoryListByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setKirikaeOrderChangeContentList(kirikaeOrderChangeContentList);

        List<KirikaeOrderPartsNumber> kirikaeOrderPartsNumberList = this.kirikaeOrderPartsNumberHistoryDao.selectKirikaeOrderPartsNumberHistoryListByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setKirikaeOrderPartsNumberList(kirikaeOrderPartsNumberList);

        List<KirikaeResume> kirikaeResumeList = this.kirikaeResumeHistoryDao.selectKirikaeResumeHistoryListByOrderId(alterationOrderHistory.getId());
        alterationOrderHistory.setKirikaeResumeList(kirikaeResumeList);

        return alterationOrderHistory;
    }
}
