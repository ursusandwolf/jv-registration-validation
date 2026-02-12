package core.basesyntax.service;

import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;
import core.basesyntax.model.UserRegisterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {

    private static RegistrationService registration;
    private static StorageDaoImpl dao;

    @BeforeAll
    public static void init(){
        registration = new RegistrationServiceImpl();
        dao = new StorageDaoImpl();
    }

    @Test
    void register_NewValidUser_Ok() {
        User user = new User();
        user.setLogin("Vasya12345");
        user.setPassword("Vasya$#145");
        user.setAge(21);

        registration.register(user);
        User saved = dao.get(user.getLogin());
        assertNotNull(saved);
        assertEquals(user, saved);
        assertEquals(user.getPassword(), saved.getPassword());
        assertEquals(user.getAge(), saved.getAge());
    }

    @Test
    void register_NullUser_Fail() {
        assertThrows(UserRegisterException.class,
                () -> registration.register(null));
    }
    @Test
    void register_EmptyUser_Fail() {}
    @Test
    void register_ShortNameUser_Fail() {}
    @Test
    void register_ShortPasswordUser_Fail() {}
    @Test
    void register_ExistedUser_Fail() {}
    @Test
    void register_NotAdultUser_Fail() {}
}