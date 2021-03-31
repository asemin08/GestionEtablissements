package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Allan
 */
public class CourseServiceTest {

    CourseDao mockCourseDAO;
    CourseService courseService;

    @BeforeEach
    public void beforeEach() {
        mockCourseDAO = Mockito.mock(CourseDao.class);
        courseService = new CourseService(mockCourseDAO);


    }
    @Test
    public void getCourseTest() throws ExceptionDao, ExceptionService {

        String courseSubject = "Java";

        Mockito.when(mockCourseDAO.get(88)).thenReturn(new Course("Java",9,88));

        CourseDTO course = courseService.get(88);

        assertEquals(course.getCourseSubject(), courseSubject);

        Mockito.verify(mockCourseDAO).get(88);
    }

    @AfterEach
    public void afterEach()
    {
        mockCourseDAO = null;
        courseService = null;
    }
}
