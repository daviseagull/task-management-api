package com.dseagull.taskmanagement.domain.user.controller;

import com.dseagull.taskmanagement.domain.user.controller.swagger.UserSwagger;
import com.dseagull.taskmanagement.domain.user.dto.UsersOutputDto;
import com.dseagull.taskmanagement.domain.user.service.UserService;
import com.dseagull.taskmanagement.domain.user.util.UserUtils;
import com.dseagull.taskmanagement.shared.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService service;

    @Override
    @GetMapping
    public ResponseEntity<UsersOutputDto> getUsers(
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

    @Override
    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> disableUser(String id) {
        service.disableUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(value = "/invite/{email}")
    public ResponseEntity<String> inviteUser(String email) {
        service.inviteUser(email);
        return ResponseEntity.noContent().build();
    }
}
