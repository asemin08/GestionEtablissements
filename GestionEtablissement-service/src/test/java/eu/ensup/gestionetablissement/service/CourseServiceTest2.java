package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Allan
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest2 {

    @Mock
    CourseDao mockCourseDAO;
    CourseService courseService;

//    @BeforeEach
//    public void beforeEach() {
//        MockitoAnnotations.initMocks(this);
//        courseService = new CourseService(mockCourseDAO);
//    }

    @Test
    public void getCourseTest() throws ExceptionDao, ExceptionService {
        courseService = new CourseService(mockCourseDAO);

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
