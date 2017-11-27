package com.takata.dpcoi.statistics.service.serviceImpl;

import com.takata.dpcoi.statistics.dao.RRDelayStatisticsDao;
import com.takata.dpcoi.statistics.domain.RRDelayStatistics;
import com.takata.dpcoi.statistics.query.RRDelayStatisticsQuery;
import com.takata.dpcoi.statistics.service.RRDelayStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/
@Service("rRDelayStatisticsService")
public class RRDelayStatisticsServiceImpl implements RRDelayStatisticsService {

    @Resource(name="rRDelayStatisticsDao")
    private RRDelayStatisticsDao rRDelayStatisticsDao;

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
