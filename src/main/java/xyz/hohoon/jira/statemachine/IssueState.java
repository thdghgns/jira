package xyz.hohoon.jira.statemachine;

public enum IssueState {
    OPEN,
    IN_PROGRESS,
    WAITING_QA,
    IN_QA,
    APPROVED,
    CANCELLED,
    REJECTED,
    DONE
}