package org.ricky.core.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import org.ricky.core.common.context.UserContext;
import org.ricky.core.common.domain.event.DomainEvent;
import org.ricky.core.common.domain.marker.Identified;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.time.Instant.now;
import static org.ricky.core.common.utils.ValidationUtils.requireNonBlank;
import static org.ricky.core.common.utils.ValidationUtils.requireNonNull;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/5
 * @className AggregateRoot
 * @desc 聚合根
 */
@Getter
public abstract class AggregateRoot implements Identified {

    /**
     * 最多保留的操作日志数量
     */
    public static final int MAX_OPS_LOG_SIZE = 100;

    /**
     * 标识符，通过Snowflake算法生成
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Instant createdAt;

    /**
     * 创建人的UID
     */
    private String createdBy;

    /**
     * 创建人姓名
     */
    private String creator;

    /**
     * 更新时间
     */
    private Instant updatedAt;

    /**
     * 更新人UID
     */
    private String updatedBy;

    /**
     * 更新人姓名
     */
    private String updater;

    /**
     * 领域事件列表，用于临时存放完成某个业务流程中所发出的事件，会被BaseRepository保存到事件表中
     */
    private List<DomainEvent> events;

    /**
     * 操作日志
     */
    private LinkedList<OpsLog> opsLogs;

    /**
     * 版本号，实现乐观锁
     */
    @Version
    @Getter(AccessLevel.PRIVATE)
    private Long _version;

    protected AggregateRoot() {
        this.clearEvents();
    }

    protected AggregateRoot(String id, UserContext userContext) {
        requireNonBlank(id, "ID must not be blank.");
        requireNonNull(userContext, "UserContext must not be null.");
        requireNonBlank(userContext.getUid(), "UID must not be blank.");

        this.id = id;
        this.userId = userContext.getUid();
        this.createdAt = now();
        this.createdBy = userContext.getUid();
        this.creator = userContext.getName();
    }

    protected AggregateRoot(String id, String userId, UserContext userContext) {
        requireNonBlank(id, "AR ID must not be blank.");
        requireNonBlank(userId, "User ID must not be blank.");
        requireNonNull(userContext, "User Context must not be null.");

        this.id = id;
        this.userId = userId;
        this.createdAt = now();
        this.createdBy = userContext.getUid();
        this.creator = userContext.getName();
    }

    /**
     * 添加操作日志
     *
     * @param note        记录
     * @param userContext 用户
     */
    protected void addOpsLog(String note, UserContext userContext) {
        if (!userContext.isLoggedIn()) {
            return;
        }

        OpsLog log = OpsLog.builder()
                .note(note)
                .optAt(Instant.now())
                .optBy(userContext.getUid())
                .obn(userContext.getName())
                .build();
        List<OpsLog> opsLogs = allOpsLog();

        opsLogs.add(log);
        // 最多保留最近MAX_OPS_LOG_SIZE条
        if (opsLogs.size() > MAX_OPS_LOG_SIZE) {
            this.opsLogs.remove();
        }

        this.updatedAt = Instant.now();
        this.updatedBy = userContext.getUid();
        this.updater = userContext.getName();
    }

    /**
     * 获取所有操作日志
     *
     * @return 操作日志集合，如果没有则返回空集合
     */
    private List<OpsLog> allOpsLog() {
        if (opsLogs == null) {
            this.opsLogs = new LinkedList<>();
        }

        return opsLogs;
    }

    /**
     * 触发事件
     *
     * @param event 事件
     */
    protected void raiseEvent(DomainEvent event) {
        event.setArInfo(this);
        allEvents().add(event);
    }

    /**
     * 清除领域事件
     * 为了方便传输和持久化，需要清除领域事件给聚合根瘦身
     */
    public void clearEvents() {
        this.events = null;
    }

    /**
     * 获取所有领域事件
     *
     * @return 领域事件集合，没有就返回空集合
     */
    private List<DomainEvent> allEvents() {
        if (events == null) {
            this.events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public String getIdentifier() {
        return id;
    }

}
