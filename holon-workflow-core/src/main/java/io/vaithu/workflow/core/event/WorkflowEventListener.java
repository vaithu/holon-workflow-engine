package io.vaithu.workflow.core.event;

/**
 * Interface for listening to workflow events.
 * 
 * Implementations of this interface can be registered to receive notifications
 * when workflow events occur. This enables loose coupling between the workflow
 * engine and event handlers.
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
public interface WorkflowEventListener {

    /**
     * Called when a workflow event occurs.
     * 
     * @param event the workflow event
     */
    void onEvent(WorkflowEvent event);
}
