package xyz.hohoon.jira.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fixVersion", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
