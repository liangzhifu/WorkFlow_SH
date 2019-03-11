package com.success.kirikae.wo.constant;

/**
 * @author lzf
 **/

public class KirikaeWoOrderEnum {

    public enum WoOrderStateEnum{
        ORDER_STATE_ONE(1, "初始化"),
        ORDER_STATE_TWO(2, "待指定确认项目"),
        ORDER_STATE_THREE(3, "待确认"),
        ORDER_STATE_FOUR(4, "已完成"),
        ORDER_STATE_FIVE(5, "作废");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        WoOrderStateEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
