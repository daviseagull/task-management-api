package com.dseagull.taskmanagement.shared.util;

import com.dseagull.taskmanagement.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {

    public static User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
