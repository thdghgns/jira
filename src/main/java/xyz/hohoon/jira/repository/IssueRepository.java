package xyz.hohoon.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hohoon.jira.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, String> {
}
