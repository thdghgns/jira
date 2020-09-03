package xyz.hohoon.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.hohoon.jira.dto.VersionDto;
import xyz.hohoon.jira.service.VersionService;

@RestController
public class VersionController {
    @Autowired
    VersionService versionService;

    @PostMapping("/api/version")
    public VersionDto.Response createVersion(@RequestParam(value = "name", required = true) String name) {
        return versionService.createVersion(name);
    }

    @GetMapping("/api/version")
    public VersionDto.Response getVersion(@RequestParam("id") Long versionId) {
        return versionService.findVersion(versionId);
    }

    @DeleteMapping("/api/version")
    public void deleteVersion(@RequestParam("id") Long versionId) {
        versionService.deleteVersion(versionId);
    }
}
