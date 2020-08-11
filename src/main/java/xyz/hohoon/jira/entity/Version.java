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
public class Version extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fixVersion", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
