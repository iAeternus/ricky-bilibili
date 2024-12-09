package org.ricky.core.common.context;

import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/24
 * @className RoleEnum
 * @desc 用户角色
 */
@Getter
public enum RoleEnum {

    SYSTEM_ADMIN("系统管理员"),
    JUDGEMENT("风纪委员"),
    ORDINARY_USER("普通用户"),
    VIP("大会员"),
    ROBOT("机器人"),
    ;

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

}
