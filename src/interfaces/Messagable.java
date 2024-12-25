package interfaces;

import communication.Message;

import java.util.List;

public interface Messagable {
    void sendMessage(String receiverEmail, String body);
    List<Message> viewMessages();
}
