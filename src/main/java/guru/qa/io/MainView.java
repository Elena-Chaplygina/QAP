package guru.qa.io;

import guru.qa.domain.User;

public interface MainView extends View{


    void showError(Throwable t);

    void showMainFrame(User user, int initContactPosition);
    void startMessaging(User user);
}
