package xyz.hohoon.jira.statemachine;

import org.springframework.statemachine.StateMachine;

import java.util.List;
import java.util.stream.Collectors;

public class StatemachineUtils {
    public static List<IssueEvent> getAvailableEvents(StateMachine<IssueState, IssueEvent> stateMachine) {
        IssueState issueState = stateMachine.getState().getId();
        List<IssueEvent> issueEvents = stateMachine.getTransitions().stream()
                                            .filter(t -> t.getSource().equals(issueState))
                                            .map(t -> t.getTrigger().getEvent())
                                            .collect(Collectors.toList());

        return issueEvents;
    }
}
