package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Allan
 */
public class CourseServiceTestMockSpy {

    @Spy
    CourseDao mockCourseDAO;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCourseTest() throws ExceptionDao, ExceptionService {

        String courseSubject = "Java";

        Mockito.doReturn(new Course("Java",9,88)).when(mockCourseDAO).get(88);

        Course course = mockCourseDAO.get(88);

        assertEquals(course.getCourseSubject(), courseSubject);

        Mockito.verify(mockCourseDAO, Mockito.times(1)).get(88);
    }

    @AfterEach
    public void afterEach()
    {
        mockCourseDAO = null;
    }
}
