package com.dseagull.taskmanagement.user;

import com.dseagull.taskmanagement.shared.model.AuditMetadata;
import com.dseagull.taskmanagement.user.model.Name;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.model.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
@Document
public class User implements UserDetails {

    @Id
    private String id;

    private Name name;

    private String username;

    private String email;

    @Setter
    private String password;

    @Setter
    private Role role;

    @Setter
    private Status status;

    @Setter
    private AuditMetadata metadata;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !status.expired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.locked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !status.credentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return status.enabled();
    }
}
