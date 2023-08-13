package com.dseagull.taskmanagement.user.controller;

import com.dseagull.taskmanagement.shared.util.PageUtils;
import com.dseagull.taskmanagement.user.controller.swagger.UserSwagger;
import com.dseagull.taskmanagement.user.dto.UserInputDto;
import com.dseagull.taskmanagement.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.user.dto.UsersOutputDto;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.usecase.DisableUserUseCase;
import com.dseagull.taskmanagement.user.usecase.EditUserUseCase;
import com.dseagull.taskmanagement.user.usecase.GetUsersUseCase;
import com.dseagull.taskmanagement.user.usecase.InviteUserUseCase;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController implements UserSwagger {

    private final GetUsersUseCase getUsersUseCase;
    private final DisableUserUseCase disableUserUseCase;
    private final InviteUserUseCase inviteUserUseCase;
    private final EditUserUseCase editUserUseCase;

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
                getUsersUseCase.getMany(
                        PageUtils.createPageable(page, size, sortBy, sortDir),
                        UserUtils.getFilters(username, email, role)
                )
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> getUser(String id) {
        return ResponseEntity.ok(getUsersUseCase.getOne(id));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<String> disableUser(String id) {
        disableUserUseCase.disableOne(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/invite/{email}")
    public ResponseEntity<String> inviteUser(String email, Role role) {
        inviteUserUseCase.inviteOne(email, role);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDto> editUser(String id, UserInputDto user) {
        return ResponseEntity.ok(editUserUseCase.editOne(id, user));
    }
}
