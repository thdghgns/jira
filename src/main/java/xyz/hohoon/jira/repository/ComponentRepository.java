package xyz.hohoon.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hohoon.jira.entity.Component;

public interface ComponentRepository extends JpaRepository<Component, String> {
}
