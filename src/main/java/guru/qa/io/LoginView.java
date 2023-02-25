package guru.qa.io;

import guru.qa.domain.User;
import guru.qa.exception.AuthenticateExcepton;

public interface LoginView  extends View{

    User doLogin() throws AuthenticateExcepton;
}
