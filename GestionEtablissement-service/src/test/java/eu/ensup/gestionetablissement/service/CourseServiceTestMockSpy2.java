package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.ICourseDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Allan
 */
public class CourseServiceTestMockSpy2 {

    @Spy
    ICourseService service;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCourseTest() throws ExceptionDao, ExceptionService {

        String courseSubject = "Java";
        ArrayList cours = new ArrayList();
        cours.add( new Course("Java",9,88));
        cours.add( new Course( "SVT", 2,86));


        Mockito.doReturn(cours).when(service).getAll();

        List c1 = service.getAll();
        assertEquals(c1.get(0), cours.get(0));

        Mockito.verify(service, Mockito.times(1)).getAll();
    }

    @AfterEach
    public void afterEach()
    {
        service = null;
    }
}
