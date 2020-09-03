package xyz.hohoon.jira.controller;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.web.bind.annotation.*;
import xyz.hohoon.jira.dto.IssueDto;
import xyz.hohoon.jira.service.IssueService;
import xyz.hohoon.jira.statemachine.IssueEvent;
import xyz.hohoon.jira.statemachine.IssueState;
import xyz.hohoon.jira.statemachine.StatemachineUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IssueController {
    @Autowired
    Graphviz graphviz;
    @Autowired
    StateMachineFactory<IssueState, IssueEvent> stateMachineFactory;
    @Autowired
    StateMachineService<IssueState, IssueEvent> stateMachineService;
    @Autowired
    IssueService issueService;

    @ResponseBody
    @GetMapping(value = "/api/issue/flow", consumes = MediaType.ALL_VALUE, produces = "image/svg+xml")
    public String getIssueFlow() {
        return graphviz.render(Format.SVG).toString();
    }

    //TODO: create issue
    public String createIssue(@RequestBody IssueDto.Create request) {
        String issueKey = issueService.createIssue(request.getProjectKey(), request.getSummary(), request.getPriority(), request.getDueDate(),
                                request.getComponentKey(), request.getFixVersionId(), request.getAssigneeUsername(), request.getDescription());
        StateMachine<IssueState, IssueEvent> issueStateMachine = stateMachineService.acquireStateMachine(issueKey);
        return "";
    }

    //TODO: get issue
    //TODO: get all issue
    //TODO: find all issue by user and state

    @GetMapping("/issue/{issueKey}/transition")
    public List<IssueEvent> getAvailableIssueFlow(@PathVariable("issueKey") String issueKey) {
        StateMachine<IssueState, IssueEvent> issueStateMachine = stateMachineService.acquireStateMachine(issueKey);

        return StatemachineUtils.getAvailableEvents(issueStateMachine);
    }
}
