package com.takata.dpcoi.config.query;

/**
 *
 * @author lzf
 * @create 2017-06-17
 **/

public class DpcoiConfigVehicleQuery {

    private Integer start;

    private Integer size;

    private Integer configId;

    private String configVehicleValue;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigVehicleValue() {
        return configVehicleValue;
    }

    public void setConfigVehicleValue(String configVehicleValue) {
        this.configVehicleValue = configVehicleValue;
    }
}
