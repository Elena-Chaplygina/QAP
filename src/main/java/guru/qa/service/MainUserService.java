package guru.qa.service;

import guru.qa.data.UserRepository;
import guru.qa.domain.User;
import guru.qa.exception.AuthenticateExcepton;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public class MainUserService implements UserService {

    private final SecurityService sc;
    private final UserRepository userRepository;

    public MainUserService(SecurityService sc, UserRepository userRepository) {
        this.sc = sc;
        this.userRepository = userRepository;
    }



    @Override
    public User authenticate(String userName, String password) throws AuthenticateExcepton {
        Optional<User> userByUserName = userRepository.findUserByUserName(userName);
        if (userByUserName.isPresent()) {
            User user = userByUserName.get();
            try {
                byte[] givenHash = sc.calculateHash(password);
                if(Arrays.equals(givenHash,user.getPasswordHash())){
                    return user;
                } else {
                    throw new AuthenticateExcepton("Неверный пароль");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new AuthenticateExcepton("Ошибка подсчета хэша", e);
            }
        } else {
            throw new AuthenticateExcepton("Не найден пользователь по имени: " + userName);
        }
    }
}
