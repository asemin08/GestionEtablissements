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
@DisplayName("Test pour le dao Course")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class CourseDaoTest {

    public List<Course> courseList;

    @BeforeEach
    public void getAllCoursTest() {
        System.out.println("On va récupérer tous les cours");

        try {
            CourseDao course = new CourseDao();
            courseList = course.getAll();

        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }
    }

    @Test
    @Tag("hamcrest")
    @DisplayName("On vrifie que la base des cours n'est pas vide ")
    public void getCoursTest()
    {
        assertThat(courseList, is(not(empty())));
    }

    @Test
    @Tag("junit")
    @DisplayName("On vrifie que la base des cours n'est pas vide")
    public void getCours2Test()
    {
        assertNotNull(courseList);
    }

    @Test
    @Tag("hamcrest")
    @DisplayName("On vérifie que la liste contient des objects Course")
    public void getObjectCoursTest()
    {
        assertThat(courseList, hasItem(isA(Course.class)));
    }

    @Test
    @Tag("junit")
    @DisplayName("On vérifie que la liste contient des objects Course")
    public void getObjectCours2Test()
    {
        for (Course course : courseList) {
          assertTrue(course instanceof Course);
        }
    }


    @Test
    @Tag("hamcrest")
    @DisplayName("On essaye de récupérer un cours avec un index invalid")
    public void getCourseWithInvalidIndexTest()
    {
        try {
            CourseDao course = new CourseDao();
            Boolean courseExist = course.indexExist(-1);
            assertThat(courseExist, is(Boolean.FALSE));
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }
    }

    @Test
    @Tag("junit")
    @DisplayName("On essaye de récupérer un cours avec un index invalid")
    public void getCourseWithInvalidIndex2Test()
    {
        try {
            CourseDao course = new CourseDao();
            Boolean courseExist = course.indexExist(-1);
            assertFalse(courseExist);
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }
    }

    @Test
    @Disabled
    @Tag("junit")
    @DisplayName("On vérifie que le cours saisie est bien un Cour")
    void testOnlyOnCiServer() {
        Course course1 = new Course();
        CourseDao courseDao = new CourseDao();
        assumeTrue(course1  instanceof Course);
        try {
            courseDao.create(course1);
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }

    }

    @Test
    @Tag("hamcrest")
    @DisplayName("On Créer un object cours et vérifie s'il contient des attributs")
    public void createCourseTest() {
        Course course = new Course("Test unitaire", 25);
        assertThat(course, hasProperty("courseSubject"));
    }

}
