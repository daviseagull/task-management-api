package com.dseagull.taskmanagement.user.usecase;

import com.dseagull.taskmanagement.shared.service.EmailService;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.model.Status;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;

@Slf4j
@Service
@RequiredArgsConstructor
public class InviteUserUseCase {

    private final UserUtils utils;
    private final UserRepository repository;
    private final EmailService emailService;

    public void inviteOne(String email, Role role) {
        log.info("Inviting user to app: {}", email);

        String message =
                String.format(
                        "Hi %s, \nYou receive an invite to the task management app. Click here to finish you profile", email
                );

        emailService.sendEmail(email, "You receive an invite to task management app", message);

        User user = User.builder()
                .email(email)
                .role(role)
                .status(Status.builder().enabled(FALSE).build())
                .build();

        repository.save(user);
    }

}
