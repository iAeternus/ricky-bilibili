package org.ricky.management;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ricky.core.common.context.UserContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BcrManager {

    public static final String ADMIN_USER_UID = "USR000000000000000001";
    public static final String ADMIN_USER_NAME = "Ricky";
    public static final String ADMIN_INIT_MOBILE = "15111111111";
    public static final String ADMIN_INIT_PASSWORD = "11111111";
    public static final UserContext MANAGE_ROBOT_USER_CONTEXT = UserContext.robotUser(ADMIN_USER_UID);

}
