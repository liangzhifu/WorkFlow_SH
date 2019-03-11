package com.success.alteration.order.constant;

/**
 * @author lzf
 **/

public class AlterationOrderEnum {

    public enum OrderChannelEnum{
        /**
         * 4M
         */
        ORDER_CHANNEL_FOUR(1, "4M"),
        /**
         * 切替
         */
        ORDER_CHANNEL_KIRIKAE(2, "切替");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        OrderChannelEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public enum OrderStateEnum{
        /**
         * 处理中
         */
        ORDER_STATE_INITIALIZATION("10A", "初始化"),
        /**
         * 处理中
         */
        ORDER_STATE_PROCESS("10B", "处理中"),
        /**
         * 已完成
         */
        ORDER_STATE_COMPLETE("10C", "已完成"),
        /**
         * 已作废
         */
        ORDER_STATE_VOID("10X", "已作废");

        private String code;

        private String msg;

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        OrderStateEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public enum GenerateFourEnum{
        /**
         * 不需要建立4M变化单
         */
        GENERATE_FOUR_ZERO(0, "不需要"),
        /**
         * 需要建立4M变化单
         */
        GENERATE_FOUR_ONE(1, "需要");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        GenerateFourEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
