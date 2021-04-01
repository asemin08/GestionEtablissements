package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Allan
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTestMockSpy {

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