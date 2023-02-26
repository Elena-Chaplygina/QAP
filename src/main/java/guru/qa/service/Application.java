package guru.qa.service;

import guru.qa.domain.User;
import guru.qa.exception.AuthenticateExcepton;
import guru.qa.io.LoginView;
import guru.qa.io.MainView;

public class Application {
    private static final int DEFAULT_SELECTED_USER_INDEX=0;
    private final LoginView loginView;
    private final MainView mainView;

    public Application(LoginView loginView, MainView mainView) {


        this.loginView = loginView;
        this.mainView = mainView;
    }

    public void run(){
        try {
            User user=loginView.doLogin();
            mainView.showMainFrame(user,DEFAULT_SELECTED_USER_INDEX);
            mainView.startMessaging(user);

        } catch (AuthenticateExcepton e) {
            mainView.showError(e);
            run();
        }

    }
}
