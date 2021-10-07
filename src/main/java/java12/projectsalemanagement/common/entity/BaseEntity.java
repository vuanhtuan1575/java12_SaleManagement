package java12.projectsalemanagement.common.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import java12.projectsalemanagement.common.util.DateUtils;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    protected Long id;

    @Version
    protected int version;

    @CreatedBy
    protected String createdBy;

    @CreatedDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    protected LocalDateTime createAt;

    @LastModifiedBy
    protected String updatedBy;

    @LastModifiedDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    protected LocalDateTime updatedAt;
}
