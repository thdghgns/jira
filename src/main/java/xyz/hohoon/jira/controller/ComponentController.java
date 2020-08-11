package xyz.hohoon.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import xyz.hohoon.jira.dto.ComponentDto;
import xyz.hohoon.jira.service.ComponentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @GetMapping("/api/component")
    public List<ComponentDto.Response> getAllComponents(final Pageable pageable) {
        return componentService.getAllComponents(pageable);
    }

    @GetMapping("/api/component/{componentKey}")
    public ComponentDto.Response findComponent(@PathVariable(name = "componentKey") String componentKey) {
        return componentService.findComponent(componentKey);
    }

    @PostMapping("/api/component")
    public ComponentDto.Response createComponent(@Valid ComponentDto.Request request) {
        return componentService.createComponent(request);
    }

    @PutMapping("/api/component")
    public ComponentDto.Response updateComponent(@Valid ComponentDto.Request request) {
        return componentService.updateComponent(request);
    }

    @DeleteMapping("/api/component/{componentKey}")
    public void deleteComponent(@PathVariable(name = "componentKey") String componentKey) {
        componentService.deleteComponent(componentKey);
    }
}
