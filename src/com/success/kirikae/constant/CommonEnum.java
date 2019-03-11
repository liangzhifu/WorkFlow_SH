package com.success.kirikae.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzf
 **/

public class CommonEnum {
    /**
     * 删除状态枚举类
     */
    public enum DeleteStateEnum {

        /**
         * 未删除
         */
        DELETE_STATE_NO(0, "未删除"),
        /**
         * 已删除
         */
        DELETE_STATE_YES(1, "已删除");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        DeleteStateEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static List<DeleteStateEnum> getAll() {
            return Arrays.asList(CommonEnum.DeleteStateEnum.values());
        }

    }
}
