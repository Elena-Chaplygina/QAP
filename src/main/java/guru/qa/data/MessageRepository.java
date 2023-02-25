package guru.qa.data;

import guru.qa.domain.Message;
import guru.qa.domain.User;

import java.util.List;

public interface MessageRepository {
    void sendMessages(User user, Message message);

    List<Message> getAllMessages(User user);


}
