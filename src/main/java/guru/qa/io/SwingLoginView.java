package guru.qa.io;

import guru.qa.domain.User;
import guru.qa.exception.AuthenticateExcepton;
import guru.qa.service.UserService;

import javax.swing.*;

public class SwingLoginView implements LoginView {

    private final UserService userServise;

    public SwingLoginView(UserService userServise) {
        this.userServise = userServise;
    }

    @Override
    public User doLogin() throws AuthenticateExcepton {
   String userName=showInputPopup("Введите имя пользователя: ");
   String password=showInputPopup("Введите пароль: ");
   return userServise.authenticate(userName,password);
    }


    private String showInputPopup(String message){
        return (String) JOptionPane.showInputDialog(
                null,
                message,
                APP_NAME,
                JOptionPane.QUESTION_MESSAGE,
                DEER_ICON,
                null,
                null);

    }
}
