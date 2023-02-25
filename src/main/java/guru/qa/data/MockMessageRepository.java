package guru.qa.data;

import guru.qa.domain.Message;
import guru.qa.domain.User;
import guru.qa.service.SecurityService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockMessageRepository implements MessageRepository {
    @Override
    public void sendMessages(User user, Message message) {

    }

    @Override
    public List<Message> getAllMessages(User user) {
//        new User("qwerty",5555, new SecurityService().calculateHash(password));
//        Stanislav 7777
//        Artem 1111


        Message msgFromStanislav0=new Message("Привет от Стаса",7777,5555,yesterdayDate());
        Message msgFromStanislav1=new Message("Как дела?",7777,5555,yesterdayDate());

        return List.of(msgFromStanislav0,msgFromStanislav1);
    }

    private Date yesterdayDate (){
        Calendar cal =Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK,-1);
        return cal.getTime();

    }


//    public static void main(String[] args) {
//        MockMessageRepository mr=new MockMessageRepository();
//        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        System.out.println(sdf.format(mr.yesterdayDate()));
//    }
}
