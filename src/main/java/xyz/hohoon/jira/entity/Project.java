package xyz.hohoon.jira.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @Column(name = "key", nullable = false, updatable = false, length = 10)
    private String key;
    @Column(name = "name", columnDefinition = "nvarchar(30)")
    private String name;
    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;
    @Column(name = "count", nullable = false, columnDefinition = "integer default 0")
    private Long count;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
