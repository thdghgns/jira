package xyz.hohoon.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.hohoon.jira.dto.VersionDto;
import xyz.hohoon.jira.entity.Issue;
import xyz.hohoon.jira.entity.Version;
import xyz.hohoon.jira.exception.ApplicationException;
import xyz.hohoon.jira.repository.VersionRepository;

import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    VersionRepository versionRepository;

    @Override
    public VersionDto.Response createVersion(String name) {
        Version version = new Version(name);
        versionRepository.save(version);
        return new VersionDto.Response(version.getId(), version.getName());
    }

    @Override
    public VersionDto.Response findVersion(Long versionId) {
        Version version = getVersionOrThrowException(versionId);
        return new VersionDto.Response(version.getId(), version.getName());
    }

    @Override
    public Version getVersionOrThrowException(Long versionId) {
        Version version = versionRepository.findById(versionId)
            .orElseThrow(() -> new ApplicationException.ResourceNotFoundException("version not found"));
        return version;
    }

    @Override
    public void deleteVersion(Long versionId) {
        Version version = getVersionOrThrowException(versionId);
        List<Issue> issues = version.getIssues();

        if (!ObjectUtils.isEmpty(issues)) {
            throw new ApplicationException.DeleteFailedException("some issues which using this version exists");
        }

        versionRepository.delete(version);
    }
}