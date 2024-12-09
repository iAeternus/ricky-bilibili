package org.ricky.core.common.constants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/12/9
 * @className MyConstants
 * @desc
 */
public interface MyConstants {

    String CHINA_TIME_ZONE = "Asia/Shanghai";
    String AUTH_COOKIE_NAME = "bilibili_token";

    String EVENT_COLLECTION = "event";

    String USER_CACHE = "USER";

    String REDIS_DOMAIN_EVENT_CONSUMER_GROUP = "domain.event.group";
    String REDIS_WEBHOOK_CONSUMER_GROUP = "webhook.group";
    String REDIS_NOTIFICATION_CONSUMER_GROUP = "notification.group";

    int MAX_URL_LENGTH = 1024;
    int MAX_ENTER_LENGTH = 1024;

}
