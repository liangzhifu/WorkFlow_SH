package com.dpcoi.statistics.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/7/20.
 */

import com.dpcoi.statistics.dao.RRDelayStatistics2Dao;
import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.query.RRDelayStatisticsQuery;
import com.dpcoi.statistics.service.RRDelayStatistics2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/
@Service("rRDelayStatistics2Service")
public class RRDelayStatistics2ServiceImpl implements RRDelayStatistics2Service {

    @Resource(name="rRDelayStatistics2Dao")
    private RRDelayStatistics2Dao rRDelayStatisticsDao;

    @Override
    public Integer addRRDelayStatistics(RRDelayStatistics rrDelayStatistics) {
        return this.rRDelayStatisticsDao.insertRRDelayStatistics(rrDelayStatistics);
    }

    @Override
    public List<Map<String, Object>> queryStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery) {
        return this.rRDelayStatisticsDao.selectStatisticsList(rrDelayStatisticsQuery);
    }

    @Override
    public List<Map<String, Object>> queryRRDelayStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery) {
        return this.rRDelayStatisticsDao.selectRRDelayStatisticsList(rrDelayStatisticsQuery);
    }

    @Override
    public Integer queryStatisticsCount(RRDelayStatisticsQuery rrDelayStatisticsQuery) {
        return this.rRDelayStatisticsDao.selectStatisticsCount(rrDelayStatisticsQuery);
    }
}
