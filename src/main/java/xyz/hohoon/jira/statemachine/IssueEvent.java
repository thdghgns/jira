package xyz.hohoon.jira.statemachine;

public enum IssueEvent {
    START_DEVELOP,
    DONE_DEVELOP,
    START_QA,
    QA_PASSED,
    QA_FAILED,
    CANCEL,
    REJECT,
    CLOSE
}