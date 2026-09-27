package io.vaithu.workflow.core.event;

import io.vaithu.workflow.core.model.WorkflowState;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an event that occurs during a workflow lifecycle.
 * 
 * Every state change and significant action in a workflow is recorded as an event.
 * Events are immutable and can be used for auditing, notifications, and analytics.
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
@Getter
@Builder
public class WorkflowEvent {

    /**
     * Unique identifier for the event.
     */
    @NonNull
    private String eventId;

    /**
     * Tenant identifier for multi-tenancy isolation.
     */
    @NonNull
    private String tenantId;

    /**
     * Document/workflow identifier.
     */
    @NonNull
    private String documentId;

    /**
     * Type of event (e.g., STATE_CHANGED, APPROVAL_RECEIVED, REJECTED).
     */
    @NonNull
    private WorkflowEventType eventType;

    /**
     * The previous state before this event.
     */
    private WorkflowState fromState;

    /**
     * The new state after this event.
     */
    private WorkflowState toState;

    /**
     * User who triggered the event.
     */
    @NonNull
    private String userId;

    /**
     * Timestamp when the event occurred.
     */
    @NonNull
    @Builder.Default
    private Instant timestamp = Instant.now();

    /**
     * Optional message or description of the event.
     */
    @Builder.Default
    private String message = "";

    /**
     * Additional metadata as key-value pairs.
     */
    @Builder.Default
    private Map<String, String> metadata = new HashMap<>();

    /**
     * Event type enumeration.
     */
    public enum WorkflowEventType {
        STATE_CHANGED,
        APPROVAL_RECEIVED,
        APPROVAL_REJECTED,
        APPROVAL_EXPIRED,
        WORKFLOW_CANCELLED,
        RULE_EVALUATED
    }
}
