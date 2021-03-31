package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.ICourseDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Allan
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTestMockSpy2 {

    @Spy
    CourseService service;


//    @BeforeEach
//    public void beforeEach() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void getCourseTest() throws ExceptionDao, ExceptionService {

        String courseSubject = "Java";

//        Mockito.doReturn(new Course("Java",9,88)).when(dao).get(88);

        var c1 = service.getAll();
        System.out.println(c1);
        CourseDTO course = service.get(88);

        assertEquals(course.getCourseSubject(), courseSubject);

        Mockito.verify(service, Mockito.times(1)).get(88);
    }

    @AfterEach
    public void afterEach()
    {
        service = null;
    }
}
