package com.worka.worka.department.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
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
@SQLDelete(sql = "UPDATE department SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "deletedAt", type = LocalDateTime.class))
@Filter(name = "deletedFilter", condition = "deleted_at IS NULL")
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

    public static Department testCreate(Long id, String name, Optional<Department> parentDepartment) {
        Integer depth = parentDepartment.map(parent -> parent.getDepth() + 1).orElse(1);
        return new Department(id, name, depth, parentDepartment.orElse(null));
    }

    private Department(String name, Integer depth, Department parent) {
        this.name = name;
        this.depth = depth;
        this.parentDepartment = parent;
    }

    private Department(Long id, String name, Integer depth, Department parent) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.parentDepartment = parent;
    }

    public void updateName(String updateName) {
        this.name = updateName;
    }

    public void updateParentDepartment(Optional<Department> parentDepartment) {
        parentDepartment.filter(department -> !this.getId().equals(department.getId()))
            .orElseThrow(() -> new RuntimeException("부서는 자기 자신을 부모로 설정할 수 없습니다."));

        this.depth = parentDepartment.map(department -> department.getDepth() + 1).orElse(1);
        this.parentDepartment = parentDepartment.orElse(null);
    }
}