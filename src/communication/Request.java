package communication;

public class Request {
    private String description;
    private String requestedBy;
    private boolean isApproved;

    public Request(String description, String requestedBy) {
        this.description = description;
        this.requestedBy = requestedBy;
        this.isApproved = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    // Methods
    public void approveRequest(String signer) {
        this.isApproved = true;
    }

    public String displayRequest() {
        return "Request: " + description + " | Requested by: " + requestedBy +
                " | Approved: " + (isApproved ? "Yes" : "No");
    }

    @Override
    public String toString() {
        return displayRequest();
    }
}
