package com.success.kirikae.order.constant;

/**
 * @author lzf
 **/

public class KirikaeOrderEnum {

    public enum KirikaeOrderTypeEnum {
        ORDER_TYPE_ONE(1, "量产前"),
        ORDER_TYPE_TWO(2, "量产后");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        KirikaeOrderTypeEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static KirikaeOrderEnum.KirikaeOrderTypeEnum getKirikaeOrderTypeEnumByCode(Integer code) {
            for (KirikaeOrderEnum.KirikaeOrderTypeEnum kirikaeOrderTypeEnum : KirikaeOrderEnum.KirikaeOrderTypeEnum.values()) {
                if (code.intValue() == kirikaeOrderTypeEnum.getCode().intValue()) {
                    return kirikaeOrderTypeEnum;
                }
            }
            return null;
        }

    }

    public enum KirikaeOrderStateEnum {
        ORDER_STATE_ONE(1, "已切替"),
        ORDER_STATE_TWO(2, "切替注意"),
        ORDER_STATE_THREE(3, "切替日已过"),
        ORDER_STATE_FOUR(4, "随切/特定切"),
        ORDER_STATE_FIVE(5, "废除");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        KirikaeOrderStateEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static KirikaeOrderEnum.KirikaeOrderStateEnum getKirikaeOrderStateEnumByCode(Integer code) {
            for (KirikaeOrderEnum.KirikaeOrderStateEnum kirikaeOrderStateEnum : KirikaeOrderEnum.KirikaeOrderStateEnum.values()) {
                if (code.intValue() == kirikaeOrderStateEnum.getCode().intValue()) {
                    return kirikaeOrderStateEnum;
                }
            }
            return null;
        }

    }

}
