package communication;

public class Message {
    private String body;
    private String sender;
    private String receiver;

    public Message(String body, String sender, String receiver) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
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

    public String displayMessage() {
        return "From: " + sender + " | To: " + receiver + " | Message: " + body;
    }


    @Override
    public String toString() {
        return displayMessage();
    }
}
