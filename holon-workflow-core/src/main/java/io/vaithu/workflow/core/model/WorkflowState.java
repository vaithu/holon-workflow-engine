package io.vaithu.workflow.core.model;

/**
 * Defines all possible states in a workflow lifecycle.
 * 
 * States represent distinct phases in the document approval workflow:
 * - DRAFT: Initial state, not yet submitted
 * - SUBMITTED: Submitted for approval, awaiting reviewer action
 * - APPROVED: All required approvals obtained
 * - REJECTED: Approval was denied by a reviewer
 * - CANCELLED: Workflow was cancelled before completion
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
public enum WorkflowState {
    /**
     * Initial state - document is being prepared but not yet submitted for approval.
     */
    DRAFT,

    /**
     * Document has been submitted for approval and is awaiting reviewer action.
     */
    SUBMITTED,

    /**
     * All required approval rules have been satisfied.
     */
    APPROVED,

    /**
     * Approval was rejected by one or more reviewers.
     */
    REJECTED,

    /**
     * Workflow was cancelled before completion.
     */
    CANCELLED;

    /**
     * Checks if this state allows transitions to the given target state.
     * 
     * @param targetState the target state to transition to
     * @return true if the transition is valid, false otherwise
     */
    public boolean canTransitionTo(WorkflowState targetState) {
        if (targetState == null) {
            return false;
        }

        return switch (this) {
            case DRAFT -> targetState == SUBMITTED || targetState == CANCELLED;
            case SUBMITTED -> targetState == APPROVED || targetState == REJECTED || targetState == CANCELLED;
            case APPROVED, REJECTED, CANCELLED -> false;
        };
    }
}
