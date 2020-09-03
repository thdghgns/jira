package xyz.hohoon.jira.service;

import xyz.hohoon.jira.dto.ComponentDto;
import xyz.hohoon.jira.entity.Component;

import java.util.List;

public interface ComponentService {
    List<ComponentDto.Response> getAllComponents();
    ComponentDto.Response findComponent(String componentKey);
    Component getComponentOrThrowException(String componentKey);
    ComponentDto.Response createComponent(ComponentDto.Request request);
    ComponentDto.Response updateComponent(ComponentDto.Request request);
    void deleteComponent(String componentKey);
}
