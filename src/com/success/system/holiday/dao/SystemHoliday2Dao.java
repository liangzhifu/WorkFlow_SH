package com.success.system.holiday.dao;

import com.success.system.holiday.domain.SystemHoliday;
import com.success.system.holiday.query.SystemHolidayQuery;
import com.success.web.framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf
 **/
@Repository
public class SystemHoliday2Dao extends BaseDao {

    /**
     * 插入一个节假日
     * @param systemHoliday 订单数据
     * @return 1成功，0不成功
     */
    public Integer insertSystemHoliday(SystemHoliday systemHoliday){
        return this.sqlSession.insert("SystemHolidayMapper2.insertSelective", systemHoliday);
    }

    /**
     * 删除一个节假日
     * @param systemHoliday 订单数据
     * @return 1成功，0不成功
     */
    public Integer deleteSystemHoliday(SystemHoliday systemHoliday){
        return this.sqlSession.delete("SystemHolidayMapper2.deleteByPrimaryKey", systemHoliday);
    }


    /**
     * 获取节假日数量
     * @param systemHolidayQuery 查询条件
     * @return 返回结果
     */
    public Integer selectSystemHolidayCount(SystemHolidayQuery systemHolidayQuery){
        return this.sqlSession.selectOne("SystemHolidayMapper2.selectSystemHolidayCount", systemHolidayQuery);
    }

    /**
     * 获取节假日列表--分页
     * @param systemHolidayQuery 查询条件
     * @return 返回结果
     */
    public List<SystemHoliday> selectSystemHolidayPageList(SystemHolidayQuery systemHolidayQuery){
        return this.sqlSession.selectList("SystemHolidayMapper2.selectSystemHolidayListPage", systemHolidayQuery);
    }

    /**
     * 获取节假日列表
     * @param systemHolidayQuery 查询条件
     * @return 返回结果
     */
    public List<SystemHoliday> selectSystemHolidayList(SystemHolidayQuery systemHolidayQuery){
        return this.sqlSession.selectList("SystemHolidayMapper2.selectSystemHolidayList", systemHolidayQuery);
    }

}
