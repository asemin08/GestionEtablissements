package eu.ensup.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Allan
 */

@WebServlet(urlPatterns = "/StatistiqueValue", name="StatistiqueValue")
public class StatistiqueValueServlet extends HttpServlet {

    public StatistiqueValueServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }


    //
    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if (person == null) {
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        } else {

            // Etape 1 récu^érer les paramétres
            if (method == "get") {
//                Redirection vers l'écran de connexion
                int badStudent = 0;
                int averageStudent = 0;
                int goodStudent = 0;
                int excellentStudent = 0;
                //Add item in combobox student
                PersonService ps = new PersonService();
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            if(((StudentDTO) p).getAverage() < 8){
                                badStudent++;
                            }else if(((StudentDTO) p).getAverage() >= 8 && ((StudentDTO) p).getAverage() < 12){
                                averageStudent++;
                            }else if(((StudentDTO) p).getAverage() >= 12 && ((StudentDTO) p).getAverage() < 17){
                                goodStudent++;
                            }else if(((StudentDTO) p).getAverage() >= 17){
                                excellentStudent++;
                            }
                        }
                    }
                } catch (ExceptionService es) {
                    req.setAttribute("error", es.getMessage());
                }
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.addValue(badStudent, "Etudiant mauvais (0 à 8)", "Etudiant mauvais (0 à 8)");
                dataset.addValue(averageStudent, "Etudiant moyen (8 à 12)", "Etudiant moyen (8 à 12)");
                dataset.addValue(goodStudent, "Etudiant bon (12 à 17)", "Etudiant bon (12 à 17)");
                dataset.addValue(excellentStudent, "Etudiant excellent (17 à 20)", "Etudiant excellent (17 à 20)");

                ArrayList couleurs = new ArrayList();


                ObjectMapper myObjectMapper = new ObjectMapper();
                ObjectWriter ow = myObjectMapper.writer().withDefaultPrettyPrinter();
                String datasetToJson = ow.writeValueAsString(dataset);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF8");
                resp.getWriter().write(String.valueOf(datasetToJson));

            }
//
        }
    }
}
