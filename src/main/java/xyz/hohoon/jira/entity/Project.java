package xyz.hohoon.jira.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity {
    @Id
    @NonNull
    @Column(name = "key", nullable = false, updatable = false, length = 10)
    private String key;
    @NonNull
    @Column(name = "name", columnDefinition = "nvarchar(30)")
    private String name;
    @NonNull
    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;
    @Column(name = "count", columnDefinition = "integer default 0")
    private Long count;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.count = this.count == null ? 0 : this.count;
    }
}
