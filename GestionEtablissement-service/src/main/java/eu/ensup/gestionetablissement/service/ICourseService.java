package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.dto.CourseDTO;

import java.util.List;

/**
 * The interface Course service.
 */
public interface ICourseService<CourseDTO> extends IService<CourseDTO>
{

    List<CourseDTO> getAll() throws ExceptionService;

}
