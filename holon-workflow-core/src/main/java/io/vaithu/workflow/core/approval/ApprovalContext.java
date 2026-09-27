package io.vaithu.workflow.core.approval;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Tracks the approval status and requirements for a document.
 * 
 * Maintains information about which rules apply, who needs to approve,
 * and which approvers have already provided approval.
 * 
 * @author Vaithu Team
 * @since 1.0.0
 */
public final class ApprovalContext {
    private final String documentId;
    private final String tenantId;
    private final Set<String> applicableRules;
    private final Set<String> requiredApprovers;
    private final Map<String, Instant> receivedApprovals;
    private final Map<String, Instant> rejections;
    private final Instant createdAt;

    /**
     * Constructor for ApprovalContext.
     */
    public ApprovalContext(String documentId, String tenantId, Set<String> applicableRules,
                          Set<String> requiredApprovers, Map<String, Instant> receivedApprovals,
                          Map<String, Instant> rejections, Instant createdAt) {
        this.documentId = Objects.requireNonNull(documentId, "documentId cannot be null");
        this.tenantId = Objects.requireNonNull(tenantId, "tenantId cannot be null");
        this.applicableRules = applicableRules != null ? new HashSet<>(applicableRules) : new HashSet<>();
        this.requiredApprovers = requiredApprovers != null ? new HashSet<>(requiredApprovers) : new HashSet<>();
        this.receivedApprovals = receivedApprovals != null ? new HashMap<>(receivedApprovals) : new HashMap<>();
        this.rejections = rejections != null ? new HashMap<>(rejections) : new HashMap<>();
        this.createdAt = createdAt != null ? createdAt : Instant.now();
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Set<String> getApplicableRules() {
        return Collections.unmodifiableSet(applicableRules);
    }

    public Set<String> getRequiredApprovers() {
        return Collections.unmodifiableSet(requiredApprovers);
    }

    public Map<String, Instant> getReceivedApprovals() {
        return Collections.unmodifiableMap(receivedApprovals);
    }

    public Map<String, Instant> getRejections() {
        return Collections.unmodifiableMap(rejections);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Checks if all required approvals have been received.
     * 
     * @return true if all required approvers have approved
     */
    public boolean isFullyApproved() {
        return !requiredApprovers.isEmpty() && 
               receivedApprovals.keySet().containsAll(requiredApprovers);
    }

    /**
     * Checks if any rejections have been received.
     * 
     * @return true if any approver has rejected
     */
    public boolean hasRejections() {
        return !rejections.isEmpty();
    }

    /**
     * Records an approval from an approver.
     * 
     * @param approverId the ID of the approver
     * @param timestamp the timestamp of approval
     */
    public void recordApproval(String approverId, Instant timestamp) {
        receivedApprovals.put(approverId, timestamp);
    }

    /**
     * Records a rejection from an approver.
     * 
     * @param rejectorId the ID of the rejector
     * @param timestamp the timestamp of rejection
     */
    public void recordRejection(String rejectorId, Instant timestamp) {
        rejections.put(rejectorId, timestamp);
    }

    /**
     * Gets the pending approvers (required but not yet approved or rejected).
     * 
     * @return set of pending approver IDs
     */
    public Set<String> getPendingApprovers() {
        if (requiredApprovers.isEmpty()) {
            return new HashSet<>();
        }
        Set<String> pending = new HashSet<>(requiredApprovers);
        pending.removeAll(receivedApprovals.keySet());
        pending.removeAll(rejections.keySet());
        return pending;
    }

    /**
     * Builder for ApprovalContext.
     */
    public static class Builder {
        private String documentId;
        private String tenantId;
        private Set<String> applicableRules = new HashSet<>();
        private Set<String> requiredApprovers = new HashSet<>();
        private Map<String, Instant> receivedApprovals = new HashMap<>();
        private Map<String, Instant> rejections = new HashMap<>();
        private Instant createdAt = Instant.now();

        public Builder documentId(String documentId) {
            this.documentId = documentId;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder applicableRules(Set<String> applicableRules) {
            this.applicableRules = applicableRules;
            return this;
        }

        public Builder requiredApprovers(Set<String> requiredApprovers) {
            this.requiredApprovers = requiredApprovers;
            return this;
        }

        public Builder receivedApprovals(Map<String, Instant> receivedApprovals) {
            this.receivedApprovals = receivedApprovals;
            return this;
        }

        public Builder rejections(Map<String, Instant> rejections) {
            this.rejections = rejections;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ApprovalContext build() {
            return new ApprovalContext(documentId, tenantId, applicableRules, requiredApprovers,
                    receivedApprovals, rejections, createdAt);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
