package xyz.hohoon.jira.service;

import xyz.hohoon.jira.dto.VersionDto;
import xyz.hohoon.jira.entity.Version;

public interface VersionService {
    VersionDto.Response createVersion(String name);

    VersionDto.Response findVersion(Long versionId);
    Version getVersionOrThrowException(Long versionId);

    void deleteVersion(Long versionId);
}
