package xyz.hohoon.jira.service;

import org.springframework.data.domain.Pageable;
import xyz.hohoon.jira.dto.ComponentDto;

import java.util.List;

public interface ComponentService {
    List<ComponentDto.Response> getAllComponents(Pageable pageable);
    ComponentDto.Response findComponent(String componentKey);
    ComponentDto.Response createComponent(ComponentDto.Request request);
    ComponentDto.Response updateComponent(ComponentDto.Request request);
    void deleteComponent(String componentKey);
}
