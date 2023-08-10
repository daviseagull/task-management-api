package com.dseagull.taskmanagement.user.controller;

import com.dseagull.taskmanagement.shared.util.PageUtils;
import com.dseagull.taskmanagement.user.controller.swagger.UserSwagger;
import com.dseagull.taskmanagement.user.service.UserService;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService service;

    @Override
    @GetMapping
    public ResponseEntity<Object> getUsers(
            String email,
            String username,
            String role,
            int page,
            int size,
            String sortBy,
            String sortDir
    ) {
        return ResponseEntity.ok(
                service.getUsers(
                        PageUtils.createPageable(page, size, sortBy, sortDir),
                        UserUtils.getFilters(username, email, role)
                )
        );
    }
}
