package guru.qa;

import guru.qa.data.MockMessageRepository;
import guru.qa.data.MockUserRepository;
import guru.qa.io.SwingLoginView;
import guru.qa.io.SwingMainView;
import guru.qa.service.Application;
import guru.qa.service.MainUserService;
import guru.qa.service.SecurityService;

public class Main {
    public static void main(String[] args) {

        new Application(
                new SwingLoginView(
                        new MainUserService(
                                new SecurityService(),
                                new MockUserRepository()
                        )),
                new SwingMainView(
                        new MockMessageRepository()
                )

        ).run();


    }
}
