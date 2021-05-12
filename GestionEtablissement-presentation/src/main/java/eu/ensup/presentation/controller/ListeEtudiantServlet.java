package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Allan
 */

@WebServlet(urlPatterns = "/ListeEtudiant", name="ListeEtudiant")
public class ListeEtudiantServlet extends HttpServlet {


    public ListeEtudiantServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");

    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {
        // Etape 1 récu^érer les paramétres
        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if (person == null) {
            req.setAttribute("lien", req.getRequestURI());
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        } else {

            // Etape 1 récu^érer les paramétres
            if (method == "get") {
                PersonService ps = new PersonService();
                List<PersonDTO> personList = new ArrayList();

                try {
                    for (PersonDTO p : ps.getAll()) {
                        if (p instanceof StudentDTO) {
                            personList.add(p);
                            req.setAttribute("etudiantList", personList);
                        }
                    }

                } catch (ExceptionService es) {
                    req.setAttribute("error", es.getMessage());
                }

                maSession.setAttribute("etudiantList", personList);
                this.getServletContext().getRequestDispatcher("/public/ListEtudiant.jsp").forward(req, resp);


//            RequestDispatcher dispatcher = null;
//
//            PersonService ps = new PersonService();
//            List<PersonDTO> personList = new ArrayList();
//            try {
//                for (PersonDTO p : ps.getAll()) {
//                    if (p instanceof StudentDTO) {
//                        personList.add(p);
//                    }
//                }
//                req.setAttribute("etudiants", personList);
//                dispatcher = req.getRequestDispatcher("/public/ListEtudiant.jsp");
//            } catch (ExceptionService es) {
//                req.setAttribute("error", es.getMessage());
//            }
//
//            System.out.println(personList);
//
//
//            dispatcher.forward(req, resp);
            }
        }
    }

}
