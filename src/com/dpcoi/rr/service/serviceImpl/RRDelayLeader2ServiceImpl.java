package com.dpcoi.rr.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/7/28.
 */

import com.dpcoi.rr.dao.RRDelayLeader2Dao;
import com.dpcoi.rr.domain.RRDelayLeader;
import com.dpcoi.rr.query.RRDelayLeaderQuery;
import com.dpcoi.rr.service.RRDelayLeader2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-28
 **/
@Service("rRDelayLeader2Service")
public class RRDelayLeader2ServiceImpl implements RRDelayLeader2Service {

    @Resource(name = "rRDelayLeader2Dao")
    private RRDelayLeader2Dao rRDelayLeaderDao;

    @Override
    public Integer addRRDelayLeader(RRDelayLeader rrDelayLeader) {
        return this.rRDelayLeaderDao.insertRRDelayLeader(rrDelayLeader);
    }

    @Override
    public Integer updateRRDelayLeader(RRDelayLeader rrDelayLeader) {
        return this.rRDelayLeaderDao.updateRRDelayLeader(rrDelayLeader);
    }

    @Override
    public Integer quereyRRDelayLeaderCount(RRDelayLeaderQuery rrDelayLeaderQuery) {
        return this.rRDelayLeaderDao.selectRRDelayLeaderCount(rrDelayLeaderQuery);
    }

    @Override
    public List<Map<String, Object>> queryRRDelayLeaderPageList(RRDelayLeaderQuery rrDelayLeaderQuery) {
        return this.rRDelayLeaderDao.selectRRDelayLeaderPageList(rrDelayLeaderQuery);
    }

    @Override
    public String queryDelay4Email(String userNames) {
        return this.rRDelayLeaderDao.selectDelay4Email(userNames);
    }

    @Override
    public String queryDelay3Email(String userNames) {
        return this.rRDelayLeaderDao.selectDelay3Email(userNames);
    }

    @Override
    public String queryDelay2Email(String userNames) {
        return this.rRDelayLeaderDao.selectDelay2Email(userNames);
    }
}
