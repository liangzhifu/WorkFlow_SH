package com.takata.system.org.constant;

/**
 * @author lzf
 **/

public class SystemOrgEnum {

    public enum companyEnum{
        /**
         * 天津分公司
         */
        COMPANY_FIRST("1", "天津分公司"),
        /**
         * 荆州分公司
         */
        COMPANY_SECOND("2", "荆州分公司"),
        /**
         * 上海分公司
         */
        COMPANY_THIRD("3", "上海分公司");

        private String code;

        private String msg;

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        companyEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

}
