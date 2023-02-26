package guru.qa.data;

import guru.qa.domain.User;
import guru.qa.service.SecurityService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class MockUserRepository implements UserRepository {
    @Override
    public Optional<User> findUserByUserName(String userName) {
        final String password = "12345";
        try {
            User user0 = new User("qwerty", 5555, new SecurityService().calculateHash(password));
            User user1 = new User("slutikof", 7777, new SecurityService().calculateHash(password));
            User user2 = new User("artem", 1111, new SecurityService().calculateHash(password));
            user0.addUsersToContactList(user1, user2);
            user1.addUsersToContactList(user0, user2);
            user2.addUsersToContactList(user0, user1);

            if (userName.equals("qwerty")) {
                return Optional.of(user0);
            } else if (userName.equals("slutikof")) {
                return Optional.of(user1);
            } else if (userName.equals("artem")) {
                return Optional.of(user2);
            } else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findUserByIcqNumber(String userName) {
        return null;
    }
}
