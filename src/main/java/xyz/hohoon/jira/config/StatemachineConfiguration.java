package xyz.hohoon.jira.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import xyz.hohoon.jira.statemachine.IssueEvent;
import xyz.hohoon.jira.statemachine.IssueState;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StatemachineConfiguration extends EnumStateMachineConfigurerAdapter<IssueState, IssueEvent> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<IssueState, IssueEvent> config) throws Exception {
        config.withPersistence();
    }

    @Override
    public void configure(StateMachineStateConfigurer<IssueState, IssueEvent> states) throws Exception {
        states
                .withStates()
                .initial(IssueState.OPEN)
                .states(EnumSet.allOf(IssueState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<IssueState, IssueEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(IssueState.OPEN).target(IssueState.IN_PROGRESS)
                    .event(IssueEvent.START_DEVELOP);
    }
}
