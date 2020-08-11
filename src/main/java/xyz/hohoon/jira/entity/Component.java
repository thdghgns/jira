package xyz.hohoon.jira.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "component")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Component extends BaseEntity{
    @NonNull
    @Id
    private String key;
    @NonNull
    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(30)")
    private String name;
    @NonNull
    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;

    @OneToMany(mappedBy = "component", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
}
