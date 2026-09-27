package io.vaithu.workflow.core.exception;

/**
 * Base exception for all workflow-related errors.
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
public class WorkflowException extends RuntimeException {
    
    /**
     * Constructs a new WorkflowException with the specified detail message.
     *
     * @param message the detail message
     */
    public WorkflowException(String message) {
        super(message);
    }

    /**
     * Constructs a new WorkflowException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
