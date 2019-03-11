package com.success.system.org.constant;

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

    public enum OrgTypeEnum{
        /**
         * 总公司
         */
        ORG_TYPE_ZERO(0, "总公司"),
        /**
         * 分公司
         */
        ORG_TYPE_ONE(1, "分公司"),
        /**
         * 部门
         */
        ORG_TYPE_TWO(2, "部门"),
        /**
         * 科室
         */
        ORG_TYPE_THREE(3, "科室");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        OrgTypeEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

}
