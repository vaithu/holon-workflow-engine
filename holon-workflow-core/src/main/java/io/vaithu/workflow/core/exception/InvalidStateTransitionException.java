package io.vaithu.workflow.core.exception;

import io.vaithu.workflow.core.model.WorkflowState;

/**
 * Thrown when attempting an invalid state transition.
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
public class InvalidStateTransitionException extends WorkflowException {

    /**
     * Constructs a new InvalidStateTransitionException for the given states.
     *
     * @param fromState the current state
     * @param toState the target state
     */
    public InvalidStateTransitionException(WorkflowState fromState, WorkflowState toState) {
        super(String.format("Cannot transition from %s to %s", fromState, toState));
    }
}
