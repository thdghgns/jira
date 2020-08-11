package xyz.hohoon.jira.exception;

public class ApplicationException {
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {

        }
    }
}
