package guru.qa.data;

import guru.qa.domain.Message;
import guru.qa.domain.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileMessageRepository implements MessageRepository {


    private static List<Message> STORE = new ArrayList<>();

    static {
        STORE.add(new Message("Привет от Стаса", 7777, 5555, yesterdayDate()));
        STORE.add(new Message("Как дела?", 7777, 5555, yesterdayDate()));
        STORE.add(new Message("Как настроение? ", 1111, 5555, yesterdayDate()));

    }

    @Override
    public void sendMessages(Message message) {
        STORE.add(message);
    }

    @Override
    public List<Message> getAllMessages(User user) {
//        qwerty  5555
//        Stanislav 7777
//        Artem 1111

        return STORE.stream()
                .filter(msg -> msg.getFrom() == user.getIcqNumber() || msg.getTo() == user.getIcqNumber())
                .collect(Collectors.toList());
    }

    private static Date yesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK, -1);
        return cal.getTime();

    }


//    public static void main(String[] args) {
//        MockMessageRepository mr=new MockMessageRepository();
//        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        System.out.println(sdf.format(mr.yesterdayDate()));
//    }
}
