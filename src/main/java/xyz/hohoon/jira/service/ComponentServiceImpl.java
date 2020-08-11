package xyz.hohoon.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.hohoon.jira.dto.ComponentDto;
import xyz.hohoon.jira.entity.Component;
import xyz.hohoon.jira.exception.ApplicationException;
import xyz.hohoon.jira.repository.ComponentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    private ComponentRepository componentRepository;

    @Override
    public List<ComponentDto.Response> getAllComponents(Pageable pageable) {
        Page<Component> componentPage = componentRepository.findAll(pageable);
        return componentPage.getContent().stream()
                .map(c -> new ComponentDto.Response(c.getKey(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public ComponentDto.Response findComponent(String componentKey) {
        Component component = componentRepository.findById(componentKey)
                .orElseThrow(() -> new ApplicationException.ResourceNotFoundException("component not found"));
        return new ComponentDto.Response(component.getKey(), component.getName(), component.getDescription());
    }

    @Override
    public ComponentDto.Response createComponent(ComponentDto.Request request) {
        Component component = componentRepository.save(new Component(request.getKey(), request.getName(), request.getDescription()));
        return new ComponentDto.Response(component.getKey(), component.getName(), component.getDescription());
    }

    @Override
    public ComponentDto.Response updateComponent(ComponentDto.Request request) {
        ComponentDto.Response response = new ComponentDto.Response();

        componentRepository.findById(request.getKey())
                .ifPresent(c -> {
                    c.setName(request.getName());
                    c.setDescription(request.getDescription());

                    Component updatedComponent = componentRepository.save(c);
                    response.setKey(updatedComponent.getKey());
                    response.setName(updatedComponent.getName());
                    response.setDescription(updatedComponent.getDescription());
                });

        return response;
    }

    @Override
    public void deleteComponent(String componentKey) {
        if (componentRepository.existsById(componentKey)) {
            componentRepository.deleteById(componentKey);
        } else {
            throw new ApplicationException.ResourceNotFoundException("component not found");
        }
    }
}
