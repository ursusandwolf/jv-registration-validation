package core.basesyntax.service;

import core.basesyntax.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {

    private static RegistrationService registration;

    @BeforeAll
    public static void init(){
        registration = new RegistrationServiceImpl();
    }

    @Test
    void register_NewValidUser_Ok() {
        User user = new User();
        user.setLogin("Vasya12345");
        user.setPassword("Vasya$#145");
        user.setAge(21);

        registration.register(user);

//        assertEquals();
    }

    @Test
    void register_NullUser_Fail() {}
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