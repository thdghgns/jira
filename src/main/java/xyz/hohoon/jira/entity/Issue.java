package xyz.hohoon.jira.entity;

import xyz.hohoon.jira.code.IssuePriority;
import xyz.hohoon.jira.security.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "issue")
public class Issue extends BaseEntity{
    @Id
    @Column(updatable = false)
    private String key;
    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;
    @Column(name = "summary", columnDefinition = "nvarchar(200)")
    private String summary;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private IssuePriority priority;
    @Column(name = "due_date")
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "component")
    private Component component;
    @ManyToOne
    @JoinColumn(name = "fix_version")
    private Version fixVersion;
    @ManyToOne
    @JoinColumn(name = "assignee")
    private User assignee;
    @ManyToOne
    @JoinColumn(name = "reporter")
    private User reporter;
    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;
}
