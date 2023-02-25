package guru.qa.data;

import guru.qa.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByIcqNumber(String userName);




}
