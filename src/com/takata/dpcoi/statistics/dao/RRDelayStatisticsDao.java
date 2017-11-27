package com.takata.dpcoi.statistics.dao;

import com.takata.common.dao.SqlDao;
import com.takata.dpcoi.statistics.domain.RRDelayStatistics;
import com.takata.dpcoi.statistics.query.RRDelayStatisticsQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/
@Repository("rRDelayStatisticsDao")
public class RRDelayStatisticsDao extends SqlDao {

    /**
     * 插入RR统计数据
     * @param rrDelayStatistics RR统计数据
     * @return 返回结果
     */
    public Integer insertRRDelayStatistics(RRDelayStatistics rrDelayStatistics){
        return this.writerSqlSession.insert("RRDelayStatisticsMapper.insertSelective", rrDelayStatistics);
    }

    /**
     * 获取统计数据
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public List<Map<String, Object>> selectStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.readSqlSession.selectList("RRDelayStatisticsMapper.selectStatisticsList", rrDelayStatisticsQuery);
    }

    /**
     * 获取RR统计数据列表
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结构
     */
    public List<Map<String, Object>> selectRRDelayStatisticsList(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.readSqlSession.selectList("RRDelayStatisticsMapper.selectRRDelayStatisticsList", rrDelayStatisticsQuery);
    }

    /**
     * 获取统计的总数
     * @param rrDelayStatisticsQuery 查询条件
     * @return 返回结果
     */
    public Integer selectStatisticsCount(RRDelayStatisticsQuery rrDelayStatisticsQuery){
        return this.readSqlSession.selectOne("RRDelayStatisticsMapper.selectStatisticsCount", rrDelayStatisticsQuery);
    }

}
