package com.dseagull.taskmanagement.shared.model;

import com.dseagull.taskmanagement.user.dto.AuditUserDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Builder
public class AuditMetadata {

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    private AuditUserDto createdByUser;

    @LastModifiedBy
    private AuditUserDto modifiedByUser;

}
