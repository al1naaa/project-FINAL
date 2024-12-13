package communication;

public class Message {
    private String body;
    private String sender;
    private String receiver;
    private boolean isRead;

    public Message(String body, String sender, String receiver) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.isRead = false; // Default to unread
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    // Methods
    public void markAsRead() {
        this.isRead = true;
    }

    public void markAsUnread() {
        this.isRead = false;
    }

    public String displayMessage() {
        return (isRead ? "[Read] " : "[Unread] ") + "From: " + sender + " | To: " + receiver + " | " + body;
    }

    @Override
    public String toString() {
        return displayMessage();
    }
}
