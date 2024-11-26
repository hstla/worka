package com.worka.worka.department.domain;

import java.time.LocalDateTime;
import java.util.Optional;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
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
    private Department parentDepartment;
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

    public static Department create(String name, Optional<Department> parentDepartment) {
        Integer depth = parentDepartment.map(parent -> parent.getDepth() + 1).orElse(1);
        return new Department(name, depth, parentDepartment.orElse(null));
    }

    public Department(String name, Integer depth, Department parentId) {
        this.name = name;
        this.depth = depth;
        this.parentDepartment = parentId;
    }

    public void updateName(String updateName) {
        this.name = updateName;
    }

    public void updateParentId(Optional<Department> parentDepartment) {
        this.depth = parentDepartment.map(Department::getDepth).orElse(1);
        this.parentDepartment = parentDepartment.orElse(null);
    }
}