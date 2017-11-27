package com.takata.system.constant;

import javax.management.relation.Role;

/**
 * @author lzf
 **/

public interface Url {

    String BASE = "/system";

    String ORG = "/org";
    String MENU = "/menu";
    String CONFIG = "/config";
    String USER = "/user";
    String USERROLE = "/userRole";
    String USERORG = "/userOrg";
    String ROLE = "/role";
    String ROLEMENU = "/roleMenu";
    String ROLEPERMISSION = "/rolePermission";
    String HOLIDAY = "/holiday";

    //角色
    String ROLE_DIALOG = BASE + ROLE + "/getDialog.do";
    String ROLE_ADD = BASE + ROLE + "/add.do";
    String ROLE_EDIT = BASE + ROLE + "/edit.do";
    String ROLE_DELETE = BASE + ROLE + "/delete.do";
    String ROLE_QUERYPAGELIST = BASE + ROLE + "/queryPageList.do";
    //角色权限关联
    String ROLEPERMISSION_ADD = BASE + ROLEPERMISSION + "/add.do";
    String ROLEPERMISSION_DELETE = BASE + ROLEPERMISSION + "/delete.do";
    String ROLEPERMISSION_QUERYPAGELIST = BASE + ROLEPERMISSION + "/queryPageList.do";
    String ROLEPERMISSION_ADD_QUERYPAGELIST = BASE + ROLEPERMISSION + "/add/queryPageList.do";
    //角色菜单关联
    String ROLEMENU_QUERYROLEMENULIST = BASE + ROLEMENU + "/querySystemRoleMenuList.do";
    String ROLEMENU_ADD = BASE + ROLEMENU + "/add.do";

    //用户URL
    String USER_DIALOG = BASE + USER + "/getDialog.do";
    String USER_ADD = BASE + USER + "/add.do";
    String USER_EDIT = BASE + USER + "/edit.do";
    String USER_DELETE = BASE + USER + "/delete.do";
    String USER_QUERYCOMPLETELIST = BASE + USER + "/queryCompleteList.do";
    String USER_QUERYPAGELIST = BASE + USER + "/queryPageList.do";
    String USER_EDITPASSWORD = BASE + USER + "/editPassword.do";
    //用户角色关联
    String USERROLE_ADD = BASE + USERROLE + "/add.do";
    String USERROLE_DELETE = BASE + USERROLE + "/delete.do";
    String USERROLE_QUERYLIST = BASE + USERROLE + "/queryList.do";
    String USERROLE_ADD_QUERYLIST = BASE + USERROLE + "/add/queryList.do";
    //用户组织关联
    String USERORG_QUERYUSERROLELIST = BASE + USERORG + "/querySystemUserRoleList.do";
    String USERORG_ADD = BASE + USERORG + "/edit.do";

    //组织RUL
    String ORG_DIALOG = BASE + ORG + "/getDialog.do";
    String ORG_ADD = BASE + ORG + "/add.do";
    String ORG_EDIT = BASE + ORG + "/edit.do";
    String ORG_DELETE = BASE + ORG + "/delete.do";
    String ORG_QUERYAllLIST = BASE + ORG + "/queryAllList.do";
    String ORG_QUERYUSERORGTREE = BASE + ORG + "/queryUserOrgTree.do";
    //菜单URL
    String MENU_QUERYTREELIST = BASE + MENU + "/queryTreeList.do";
    String MENU_QUERACTIVEUSERMODULE = BASE + MENU + "/queryActiveUserModule.do";
    String MENU_QUERACTIVEUSERMENUBYMODULE = BASE + MENU + "/queryActiveUserMenuByModule.do";

    //节假日URL
    String HOLIDAY_DIALOG = BASE + HOLIDAY + "/getDialog.do";
    String HOLIDAY_ADD = BASE + HOLIDAY + "/add.do";
    String HOLIDAY_DELETE = BASE + HOLIDAY + "/delete.do";
    String HOLIDAY_QUERYPAGELIST = BASE + HOLIDAY + "/queryPageList.do";

    //节假日URL
    String CONFIG_DIALOG = BASE + CONFIG + "/getDialog.do";
    String CONFIG_ADD = BASE + CONFIG + "/add.do";
    String CONFIG_DELETE = BASE + CONFIG + "/delete.do";
    String CONFIG_QUERYLIST = BASE + CONFIG + "/queryList.do";
    String CONFIG_QUERYPAGELIST = BASE + CONFIG + "/queryPageList.do";
    String CONFIG_QUERYCODELIST = BASE + CONFIG + "/queryCodeList.do";

}
