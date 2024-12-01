package com.worka.worka.department.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name = "department_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer depth;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parentDepartment;
    @OneToMany(mappedBy = "parentDepartment")
    private List<Department> children = new ArrayList<>();
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

    public Department(Long id, String name, Integer depth, Department parent) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.parentDepartment = parent;
    }

    private Department(String name, Integer depth, Department parent) {
        this.name = name;
        this.depth = depth;
        this.parentDepartment = parent;
    }

    public void updateName(String updateName) {
        this.name = updateName;
    }

    // null 일때
    public void updateParentDepartment(Department parentDepartment) {
        if (isSelfParent(parentDepartment)) {
            throw new RuntimeException("부서는 자기 자신을 부모로 설정할 수 없습니다.");
        }

        this.parentDepartment = parentDepartment;
        this.depth = calculateDepth(parentDepartment);
    }

    private boolean isSelfParent(Department parentDepartment) {
        return parentDepartment != null && parentDepartment.getId().equals(this.getId());
    }

    private Integer calculateDepth (Department parentDepartment) {
        return parentDepartment == null ? 1 : parentDepartment.getDepth() + 1;
    }
}