package eu.ensup.gestionetablissement.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import  eu.ensup.gestionetablissement.dao.Connect;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @author Allan
 */

@DisplayName("Test pour le dao Connect")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ConnectTest {

    private static Connection cn;


    @BeforeEach
    public void Connection() {
        System.out.println("before test ");

        try {
            cn = Connect.openConnection();
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }
    }

    @Test
    @Tag("hamcrest")
    @DisplayName("Test de connection a la bdd Hamcrest")
    public void connectionTest()
    {
        assertThat(cn, notNullValue());
    }

    @Test
    @Tag("junit")
    @DisplayName("Test de connection a la bdd Junit")
    public void connection2Test()
    {
        assertNotNull(cn);
    }


    @AfterEach
    public void deonnection() {
        System.out.println("After test ");
        try {
            cn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    @Disabled
    @Tag("hamcrest")
    @DisplayName("Test de la déconnexion a la bdd")
    public void deconnectionTest()
    {
        assertThat(cn, notNullValue());
    }
}
