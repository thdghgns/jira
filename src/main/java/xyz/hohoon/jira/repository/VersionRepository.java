package xyz.hohoon.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hohoon.jira.entity.Version;

public interface VersionRepository extends JpaRepository<Version, Long> {
}
