package xyz.hohoon.jira.service;

import xyz.hohoon.jira.dto.ComponentDto;

import java.util.List;

public interface ComponentService {
    List<ComponentDto.Response> getAllComponents();
    ComponentDto.Response findComponent(String componentKey);
    ComponentDto.Response createComponent(ComponentDto.Request request);
    ComponentDto.Response updateComponent(ComponentDto.Request request);
    void deleteComponent(String componentKey);
}
