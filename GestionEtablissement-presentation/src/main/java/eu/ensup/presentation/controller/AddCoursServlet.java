package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/AjouterCours", name="AjouterCours")
public class AddCoursServlet extends HttpServlet {


    public AddCoursServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "post");
    }


    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if (person == null) {
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        } else {
            // Etape 1 récu^érer les paramétres
            if (method == "get") {
                PersonService ps = new PersonService();
                List<PersonDTO> personList = new ArrayList();

                CourseService cs = new CourseService();
                List<CourseDTO> coursList = new ArrayList<>();

                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            personList.add(p);
                            req.setAttribute("etudiantList", personList);
                        }
                    }
                    for(CourseDTO p : cs.getAll()){
                            coursList.add(p);
                            req.setAttribute("coursList", coursList);
                    }
                } catch (ExceptionService es) {
                    req.setAttribute("error",  es.getMessage());
                }

                maSession.setAttribute("etudiantList" , personList);
                maSession.setAttribute("coursList", coursList);
                this.getServletContext().getRequestDispatcher("/public/AddCourse.jsp").forward(req, resp);

            } else if (method == "post") {

                String cours = req.getParameter("cours");
                String nbhour = req.getParameter("nbhour");

                System.out.println(cours + ".." + nbhour);
                int valeurManquante = 0;


                if(cours == null || cours == ""){
                    System.out.println("if");
                    req.setAttribute("coursError", true);
                    valeurManquante++;
                }else{
                    req.setAttribute("cours", cours);
                    req.setAttribute("coursError", "valid");
                }
                if(nbhour == null || nbhour == ""){
                    System.out.println("else");
                    req.setAttribute("nbhourError", true);
                    valeurManquante++;
                }else{
                    req.setAttribute("nbhour", nbhour);
                    req.setAttribute("nbhourError", "valid");
                }

                if(valeurManquante == 0){
                    try {
                        //Etape service
                        CourseService cp = new CourseService();
                        CourseDTO cour = new CourseDTO();
                        cour.setCourseSubject(cours);

                        float nhourFloat = Float.parseFloat(nbhour);
                        cour.setNbHours(nhourFloat);
                        cp.create(cour);

                        //on remet a zero le formulaire
                        req.setAttribute("infocours", "Info le cour " +cours + " " + nbhour + " à bien été créer");
                        req.setAttribute("cours", "");
                        req.setAttribute("nbhour", "");

                        //On met a jours la liste de cours
                        List<CourseDTO> coursList = new ArrayList();
                        for(CourseDTO p : cp.getAll()){
                            coursList.add(p);
                        }
                        maSession.setAttribute("coursList", coursList);

                    } catch (ExceptionService es) {
                        req.setAttribute("errorcours", es.getMessage());
                    }
                } else {
                    //Message d'erreur quand ils manques des valeurs dans le formulaires
                    req.setAttribute("errorcours", "Veuillez saisir les valeurs manquantes");
                }


                // renvoit de la page
                this.getServletContext().getRequestDispatcher("/public/AddCourse.jsp").forward(req, resp);
            }
        }
    }
}