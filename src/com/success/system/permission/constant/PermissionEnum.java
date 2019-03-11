package com.success.system.permission.constant;

/**
 * @author lzf
 **/

public class PermissionEnum {

    public enum ConstantEnum {

        PERMISSION_ONE(1, "切替--切替新增"),
        PERMISSION_TWO(2, "切替--切替修改"),
        PERMISSION_THREE(3, "切替--切替作废"),
        PERMISSION_FOUR(4, "切替--邮件"),
        PERMISSION_FIVE(5, "切替--科长"),
        PERMISSION_SIX(6, "切替--立合--确认"),
        PERMISSION_SEVEN(7, "切替--立合--承认"),
        PERMISSION_EIGHT(8, "切替--指示书--当担"),
        PERMISSION_NINE(9, "切替--指示书--确认"),
        PERMISSION_TEN(10, "切替--指示书--承认"),
        PERMISSION_ELEVEN(11, "切替--确认书--当担"),
        PERMISSION_TWELVE(12, "切替--确认书--确认"),
        PERMISSION_THIRTEEN(13, "切替--确认书--承认"),
        PERMISSION_FOURTEEN(14, "切替--确认切替日期"),
        PERMISSION_FIFTEEN(15, "5"),
        PERMISSION_SIXTEEN(16, "6");

        private Integer code;

        private String msg;

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        ConstantEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
