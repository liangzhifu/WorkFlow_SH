package com.success.kirikae.order.service.impl;

import com.success.kirikae.constant.CommonEnum;
import com.success.kirikae.order.dao.KirikaeOrderChangeContentDao;
import com.success.kirikae.order.domain.KirikaeOrderChangeContent;
import com.success.kirikae.order.query.KirikaeOrderChangeContentQuery;
import com.success.kirikae.order.service.KirikaeOrderChangeContentService;
import com.success.sys.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 **/
@Service("kirikaeOrderChangeContentService")
public class KirikaeOrderChangeContentServiceImpl implements KirikaeOrderChangeContentService {

    @Resource(name = "kirikaeOrderChangeContentDao")
    private KirikaeOrderChangeContentDao kirikaeOrderChangeContentDao;

    @Override
    public void addKirikaeOrderChangeContentList(List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList, User user) throws Exception {
        for(KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList){
            kirikaeOrderChangeContent.setCreateBy(user.getUserId());
            kirikaeOrderChangeContent.setCreateTime(new Date());
            kirikaeOrderChangeContent.setUpdateBy(user.getUserId());
            kirikaeOrderChangeContent.setUpdateTime(new Date());
            kirikaeOrderChangeContent.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_NO.getCode());
            Integer num = this.kirikaeOrderChangeContentDao.insertSelective(kirikaeOrderChangeContent);
            if(num == 0){
                throw new Exception("新增切替单变更内容异常！");
            }
        }
    }

    @Override
    public void deleteKirikaeOrderChangeContentByOrderId(Integer orderId, User user) throws Exception {
        List<KirikaeOrderChangeContent> kirikaeOrderChangeContentList = this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(orderId);
        for(KirikaeOrderChangeContent kirikaeOrderChangeContent : kirikaeOrderChangeContentList){
            kirikaeOrderChangeContent.setUpdateBy(user.getUserId());
            kirikaeOrderChangeContent.setUpdateTime(new Date());
            kirikaeOrderChangeContent.setDeleteState(CommonEnum.DeleteStateEnum.DELETE_STATE_YES.getCode());
            Integer num = this.kirikaeOrderChangeContentDao.updateByPrimaryKeySelective(kirikaeOrderChangeContent);
            if(num == 0){
                throw new Exception("更新切替单变更内容异常！");
            }
        }
    }

    @Override
    public List<KirikaeOrderChangeContent> listKirikaeOrderChangeContentByOrderId(Integer orderId) throws Exception {
        return this.kirikaeOrderChangeContentDao.selectKirikaeOrderChangeContentListByOrderId(orderId);
    }

}
