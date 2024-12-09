package org.ricky.core.common.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.ricky.core.common.exception.MyException;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.ricky.core.common.exception.ErrorCodeEnum.WRONG_USER;
import static org.ricky.core.common.exception.MyException.accessDeniedException;
import static org.ricky.core.common.exception.MyException.authenticationException;
import static org.ricky.core.common.utils.ValidationUtils.requireNonBlank;
import static org.ricky.core.common.utils.ValidationUtils.requireNonNull;
import static org.ricky.management.BcrManager.ADMIN_USER_UID;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/24
 * @className UserContext
 * @desc 用户上下文
 */
@Getter
@EqualsAndHashCode
public class UserContext {

    /**
     * 无信息用户
     */
    public static final UserContext NOUSER = new UserContext(null, null, null);

    /**
     * 匿名用户
     */
    public static final UserContext ANONYMOUS_USER_CONTEXT = NOUSER;

    /**
     * uid，与userId相同
     */
    private final String uid;

    /**
     * 用户名
     */
    private final String name;

    /**
     * 用户身份
     */
    private final RoleEnum role;

    private UserContext(String uid, String name, RoleEnum role) {
        this.uid = uid;
        this.name = name;
        this.role = role;
    }

    /**
     * 构造一个人类用户
     *
     * @param uid  ID
     * @param name 用户名
     * @param role 用户身份
     * @return 返回构造好的人类用户
     */
    public static UserContext humanUser(String uid, String name, RoleEnum role) {
        requireNonBlank(uid, "UID must not be blank.");
        requireNonBlank(name, "Name must not be blank.");
        requireNonNull(role, "Role must not be null.");

        if (role == RoleEnum.ROBOT) {
            throw new IllegalStateException("Human user should not have ROBOT role.");
        }

        return new UserContext(uid, name, role);
    }

    /**
     * 构造机器人用户
     *
     * @param uid 用户id
     * @return 返回构造好的机器人用户
     */
    public static UserContext robotUser(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        return new UserContext(null, null, RoleEnum.ROBOT);
    }

    /**
     * 判断是否登录
     *
     * @return true=已登录 false=未登录
     */
    public boolean isLoggedIn() {
        return internalIsLoggedIn();
    }

    /**
     * 判断是否为管理员用户
     *
     * @return true=是 false=否
     */
    public boolean isManager() {
        return Objects.equals(this.uid, ADMIN_USER_UID);
    }

    /**
     * 校验是否登录，若未登录则抛出异常
     */
    public void checkIsLoggedIn() {
        internalCheckLoggedIn();
    }

    /**
     * 判断用户是否登录
     *
     * @param uid 用户
     * @return true=是 false=否
     */
    public boolean isLoggedInFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        if (!internalIsLoggedIn()) {
            return false;
        }

        return isUserFor(uid);
    }

    /**
     * 校验用户是否登录，若不是则抛出异常
     *
     * @param uid 用户
     */
    public void checkIsLoggedInFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        internalCheckLoggedIn();
        internalCheckUserFor(uid);
    }

    /**
     * 判断是否为系统管理员
     *
     * @return true=是 false=否
     */
    public boolean isSystemAdmin() {
        if (!internalIsLoggedIn()) {
            return false;
        }

        return internalIsSystemAdmin();
    }

    /**
     * 校验是否为系统管理员，若不是则抛出异常
     */
    public void checkIsSystemAdmin() {
        internalCheckLoggedIn();
        internalCheckUserAdmin();
    }

    /**
     * 判断是否以给定系统管理员身份登录
     *
     * @param uid 用户ID
     * @return true=是 false=否
     */
    public boolean isSystemAdminFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        if (!internalIsLoggedIn() || isWrongUserFor(uid)) {
            return false;
        }

        return internalIsSystemAdmin();
    }

    /**
     * 判断是否以给定系统管理员身份登录，若不是则抛出异常
     *
     * @param uid 用户ID
     */
    public void checkIsSystemAdminFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        internalCheckLoggedIn();
        internalCheckUserFor(uid);
        internalCheckUserAdmin();
    }

    /**
     * 判断是否为系统管理员或机器人，若未登录，返回false
     *
     * @return true=是 false=否
     */
    public boolean isUserRoot() {
        if (!internalIsLoggedIn()) {
            return false;
        }

        return internalIsUserRoot();
    }

    /**
     * 校验是否为系统管理员或机器人，若不是或未登录则抛出异常
     */
    public void checkIsUserRoot() {
        internalCheckLoggedIn();
        internalCheckUserRoot();
    }

    /**
     * 判断是否以给定系统管理员或机器人身份登录
     *
     * @param uid 用户ID
     * @return true=是 false=否
     */
    public boolean isUserRootFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        if (!internalIsLoggedIn() || isWrongUserFor(uid)) {
            return false;
        }

        return internalIsUserRoot();
    }

    /**
     * 校验是否以给定系统管理员或机器人身份登录，若不是则抛出异常
     *
     * @param uid 用户ID
     */
    public void checkIsUserRootFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        internalCheckLoggedIn();
        internalCheckUserFor(uid);
        internalCheckUserRoot();
    }

    /**
     * 判断是否为人类用户
     *
     * @return true=是 false=否
     */
    public boolean isHumanUser() {
        if (!internalIsLoggedIn()) {
            return false;
        }

        return internalIsHumanUser();
    }

    /**
     * 校验是否为人类用户，若不是则抛出异常
     */
    public void checkIsHumanUser() {
        internalCheckLoggedIn();
        internalCheckHumanUser();
    }

    /**
     * 判断是否以人类用户身份登录
     *
     * @param uid 用户ID
     * @return true=是 false=否
     */
    public boolean isHumanUserFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        if (!internalIsLoggedIn() || isWrongUserFor(uid)) {
            return false;
        }

        return internalIsHumanUser();
    }

    /**
     * 校验是否以人类用户身份登录，若不是则抛出异常
     *
     * @param uid 用户ID
     */
    public void checkIsHumanUserFor(String uid) {
        requireNonBlank(uid, "UID must not be blank.");

        internalCheckLoggedIn();
        internalCheckUserFor(uid);
        internalCheckHumanUser();
    }

    /**
     * 判断用户ID是否错误
     *
     * @param uid 用户ID
     * @return true=错误 false=正确
     */
    private boolean isWrongUserFor(String uid) {
        return !Objects.equals(this.uid, uid);
    }

    /**
     * 判断当前用户是否为给定用户
     *
     * @param uid 用户ID
     * @return true=是 false=否
     */
    private boolean isUserFor(String uid) {
        return Objects.equals(this.uid, uid);
    }

    /**
     * 判断是否为系统管理员或机器人
     *
     * @return true=是 false=否
     */
    private boolean internalIsUserRoot() {
        return role == RoleEnum.SYSTEM_ADMIN || role == RoleEnum.ROBOT;
    }

    /**
     * 判断是否为系统管理员
     *
     * @return true=是 false=否
     */
    private boolean internalIsSystemAdmin() {
        return role == RoleEnum.SYSTEM_ADMIN;
    }

    /**
     * 判断是否为系统管理员或普通成员
     *
     * @return true=是 false=否
     */
    private boolean internalIsHumanUser() {
        return role == RoleEnum.SYSTEM_ADMIN || role == RoleEnum.ORDINARY_USER;
    }

    /**
     * 判断是否登录
     *
     * @return true=登录 false=未登录
     */
    private boolean internalIsLoggedIn() {
        return isNotBlank(uid) && role != null;
    }

    /**
     * 校验是否登录，若未登录则抛出认证异常
     */
    private void internalCheckLoggedIn() {
        if (!internalIsLoggedIn()) {
            throw authenticationException();
        }
    }

    /**
     * 校验是否为系统管理员或机器人，若不是系统管理员或机器人则抛出拒绝访问异常
     */
    private void internalCheckUserRoot() {
        if (!internalIsUserRoot()) {
            throw accessDeniedException();
        }
    }

    /**
     * 校验是否为系统管理员，若不是系统管理员则抛出拒绝访问异常
     */
    private void internalCheckUserAdmin() {
        if (!internalIsSystemAdmin()) {
            throw accessDeniedException();
        }
    }

    /**
     * 校验系统管理员或普通成员，若不是系统管理员或普通成员则抛出拒绝访问异常
     */
    private void internalCheckHumanUser() {
        if (!internalIsHumanUser()) {
            throw accessDeniedException();
        }
    }

    /**
     * 校验用户ID，若用户ID错误抛出异常
     *
     * @param uid 用户ID
     */
    private void internalCheckUserFor(String uid) {
        if (isWrongUserFor(uid)) {
            throw new MyException(WRONG_USER, "用户错误。", "userTenantId", this.getUid(), "uid", uid);
        }
    }

    @Override
    public String toString() {
        return "UserContext[" + this.uid + "]";
    }

}
