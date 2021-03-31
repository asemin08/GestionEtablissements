package eu.ensup.gestionetablissement.dao;
import eu.ensup.gestionetablissement.business.Course;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


/**
 * @author Allan
 */

@DisplayName("Test pour le dao Login")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LoginDaoTest {


    @Test
    @Tag("hamcrest")
    @Disabled
    @DisplayName("On vérifie que si on rentre un compte qui existe pas on ne peut pas se connecter ")
    public void getObjectCoursTest()
    {
        LoginDao loginDao = new LoginDao();
        try {
            System.out.println(loginDao.checkPassword("ytetzte", "rgtzertzetzet"));
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }
    }

}
