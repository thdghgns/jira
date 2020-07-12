package xyz.hohoon.jira.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "component")
public class Component extends BaseEntity{
    @Id
    private String key;
    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(30)")
    private String name;
    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;

    @OneToMany(mappedBy = "component", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
