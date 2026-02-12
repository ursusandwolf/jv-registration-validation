package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;
import core.basesyntax.model.UserRegisterException;

public class RegistrationServiceImpl implements RegistrationService {
    public static final int MIN_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new UserRegisterException("Invalid value null user");
        }
        String login = user.getLogin();
        validateLength(login, "Login");
        checkIfUserRegistered(login);

        validateLength(user.getPassword(), "Password");
        checkAge(user.getAge());

        storageDao.add(user);
        System.out.println("User " + login + " is registered with Id: " + user.getId());

        return user;
    }

    private void checkIfUserRegistered(String login) {
        User storageUser = storageDao.get(login);
        if (storageUser != null) {
            throw new UserRegisterException("User with such login already registered");
        }
    }

    private void checkAge(Integer age) {
        if (age < 18) {
            throw new UserRegisterException("Age is less than 18");
        }
    }

    private static void validateLength(String s, String name) {
        if (s == null || s.length() < MIN_LENGTH) {
            throw new UserRegisterException(name + " length less " + MIN_LENGTH + " characters");
        }
    }
}
