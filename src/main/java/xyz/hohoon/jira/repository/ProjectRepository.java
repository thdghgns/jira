package xyz.hohoon.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hohoon.jira.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
