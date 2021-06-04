package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/AssocierEtudiantCours", name="AssocierEtudiantCours")
public class AssocierEtudiantCoursServlet extends HttpServlet {


    public AssocierEtudiantCoursServlet() {
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "post");
    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else {

                // On recupere les attributs et on les traites
                String etudiant = req.getParameter("etudiantSelect");
                String cours = req.getParameter("coursSelect");

            try {
                //Etape service
                PersonService ps = new PersonService();

                int etudiantID = Integer.parseInt(etudiant);
                int coursID = Integer.parseInt(cours);

                ps.linkToCourse(etudiantID, coursID);

                req.setAttribute("info", "Infos l\'étudiant " + etudiantID + " "  + "a bien été lier au cours : " + coursID);

            }catch (ExceptionService es) {
                req.setAttribute("error", es.getMessage());
            }

            // renvoit de la page
            this.getServletContext().getRequestDispatcher("/public/AddCourse.jsp").forward(req, resp);

        }


    }


}