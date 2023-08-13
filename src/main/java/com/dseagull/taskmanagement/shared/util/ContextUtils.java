package com.dseagull.taskmanagement.shared.util;

import com.dseagull.taskmanagement.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtils {

    public static User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
