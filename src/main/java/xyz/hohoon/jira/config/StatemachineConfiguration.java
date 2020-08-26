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
                .states(EnumSet.allOf(IssueState.class))
                .end(IssueState.DONE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<IssueState, IssueEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(IssueState.OPEN).target(IssueState.IN_PROGRESS)
                    .event(IssueEvent.START_DEVELOP)
                    .and()
                .withExternal()
                    .source(IssueState.OPEN).target(IssueState.REJECTED)
                    .event(IssueEvent.REJECT)
                    .and()
                .withExternal()
                    .source(IssueState.IN_PROGRESS).target(IssueState.WAITING_QA)
                    .event(IssueEvent.DONE_DEVELOP)
                    .and()
                .withExternal()
                    .source(IssueState.WAITING_QA).target(IssueState.IN_QA)
                    .event(IssueEvent.START_QA)
                    .and()
                .withExternal()
                    .source(IssueState.IN_QA).target(IssueState.APPROVED)
                    .event(IssueEvent.QA_PASSED)
                    .and()
                .withExternal()
                    .source(IssueState.IN_QA).target(IssueState.OPEN)
                    .event(IssueEvent.QA_FAILED)
                    .and()
                .withExternal()
                    .source(IssueState.OPEN).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.IN_PROGRESS).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.WAITING_QA).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.IN_QA).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.APPROVED).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.CANCELLED).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.REJECTED).target(IssueState.DONE)
                    .event(IssueEvent.CLOSE)
                    .and()
                .withExternal()
                    .source(IssueState.OPEN).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                    .and()
                .withExternal()
                    .source(IssueState.IN_PROGRESS).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                    .and()
                .withExternal()
                    .source(IssueState.WAITING_QA).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                    .and()
                .withExternal()
                    .source(IssueState.IN_QA).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                    .and()
                .withExternal()
                    .source(IssueState.APPROVED).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                    .and()
                .withExternal()
                    .source(IssueState.REJECTED).target(IssueState.CANCELLED)
                    .event(IssueEvent.CANCEL)
                ;
    }
}