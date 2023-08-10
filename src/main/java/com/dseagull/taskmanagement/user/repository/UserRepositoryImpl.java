package com.dseagull.taskmanagement.user.repository;

import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<User> getUsers(Pageable pageable, UserFilters filters) {

        Query query = new Query();

        if (filters.role().isPresent()) {
            query.addCriteria(Criteria.where("role").is(filters.role().get().name()));
        }

        if (!isBlank(filters.email())) {
            query.addCriteria(Criteria.where("email").is(filters.email()));
        }

        if (!isBlank(filters.username())) {
            query.addCriteria(Criteria.where("username").is(filters.username()));
        }

        long count = mongoTemplate.count(query, User.class);

        query.with(pageable);
        List<User> filteredUsers =
                mongoTemplate.find(query, User.class, "user");

        return PageableExecutionUtils.getPage(
                filteredUsers,
                pageable,
                () -> count);
    }
}
