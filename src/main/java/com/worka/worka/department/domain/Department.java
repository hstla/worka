package com.worka.worka.department.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer depth;
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Department parentId;
    @CreatedDate
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;
    @CreatedBy
    @Column(name = "created_by")
    private String createBy;
    @LastModifiedDate
	@Column(name = "update_at")
    private LocalDateTime updateAt;
    @Column(name = "update_by")
	@LastModifiedBy
    private String updateBy;
    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    public Department(String name, Integer depth, Department parentId) {
        this.name = name;
        this.depth = depth;
        this.parentId = parentId;
    }
}