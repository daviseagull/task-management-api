package com.dseagull.taskmanagement.user.usecase;

import com.dseagull.taskmanagement.shared.exception.BusinessLogicException;
import com.dseagull.taskmanagement.shared.util.ContextUtils;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.model.Status;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisableUserUseCase {

    private final UserUtils utils;
    private final UserRepository repository;

    public void disableOne(String id) {
        log.info("Disabling user with id {}", id);
        User loggedUser = ContextUtils.getUser();

        isEqualToLoggedUser(id);
        User user = utils.findUserById(id);
        Status status = Status.builder()
                .enabled(Boolean.FALSE)
                .expired(user.getStatus().expired())
                .locked(user.getStatus().locked())
                .credentialsExpired(user.getStatus().credentialsExpired())
                .build();

        user.setStatus(status);

        repository.save(user);
    }

    private static void isEqualToLoggedUser(String userToDisableId) {
        if (StringUtils.equals(ContextUtils.getUser().getId(), userToDisableId)) {
            throw new BusinessLogicException("Logged user can't disable yourself", HttpStatus.CONFLICT);
        }
    }
}
