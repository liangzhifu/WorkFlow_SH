package com.takata.dpcoi.config.service.serviceImpl;

import com.takata.dpcoi.config.dao.DpcoiConfigVehicleDao;
import com.takata.dpcoi.config.domain.DpcoiConfigVehicle;
import com.takata.dpcoi.config.query.DpcoiConfigVehicleQuery;
import com.takata.dpcoi.config.service.DpcoiConfigVehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/
@Service("dpcoiConfigVehicleService")
public class DpcoiConfigVehicleServiceImpl implements DpcoiConfigVehicleService {

    @Resource(name="dpcoiConfigVehicleDao")
    private DpcoiConfigVehicleDao dpcoiConfigVehicleDao;

    @Override
    public Integer addDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws Exception {
        return this.dpcoiConfigVehicleDao.insertDpcoiConfigVehicle(dpcoiConfigVehicle);
    }

    @Override
    public Integer deleteDpcoiConfigVehicle(DpcoiConfigVehicle dpcoiConfigVehicle) throws Exception {
        dpcoiConfigVehicle.setDeleteState("1");
        return this.dpcoiConfigVehicleDao.updateDpcoiConfigVehicle(dpcoiConfigVehicle);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigVehicleList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleList(dpcoiConfigVehicleQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiConfigVehiclePageList(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleListPage(dpcoiConfigVehicleQuery);
    }

    @Override
    public Integer queryDpcoiConfigVehicleCount(DpcoiConfigVehicleQuery dpcoiConfigVehicleQuery) throws Exception {
        return this.dpcoiConfigVehicleDao.selectDpcoiConfigVehicleCount(dpcoiConfigVehicleQuery);
    }
}
