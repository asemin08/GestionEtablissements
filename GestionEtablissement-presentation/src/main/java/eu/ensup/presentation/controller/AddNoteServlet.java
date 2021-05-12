package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
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

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/AjouterNote", name="AjouterNote")
public class AddNoteServlet extends HttpServlet {


    public AddNoteServlet() {
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

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else {

            // Etape 1 récu^érer les paramétres
            if (method == "get") {
                PersonService ps = new PersonService();
                List<PersonDTO> personList = new ArrayList();

                CourseService cs = new CourseService();
                List<CourseDTO> coursList = new ArrayList<>();

                try {
                    for (PersonDTO p : ps.getAll()) {
                        if (p instanceof StudentDTO) {
                            personList.add(p);
                            req.setAttribute("etudiantList", personList);
                        }
                    }
                    for (CourseDTO p : cs.getAll()) {
                        coursList.add(p);
                        req.setAttribute("coursList", coursList);
                    }
                } catch (ExceptionService es) {
                    req.setAttribute("error", es.getMessage());
                }

                maSession.setAttribute("etudiantList", personList);
                maSession.setAttribute("coursList", coursList);

                //Redirection vers l'écran de connexion
                this.getServletContext().getRequestDispatcher("/public/AddNote.jsp").forward(req, resp);

            } else if (method == "post") {


                // On recupere les attributs et on les traites
                String note = req.getParameter("note");
                String appreciation = req.getParameter("appreciation");
                String etudiant = req.getParameter("etudiantSelect");
                String cours = req.getParameter("coursSelect");

                System.out.println(note + ".." + appreciation);
                int valeurManquante = 0;


                if (note == null || note == "") {
                    req.setAttribute("noteError", true);
                    valeurManquante++;
                } else {
                    req.setAttribute("note", note);
                    req.setAttribute("noteError", "valid");
                }
                if (appreciation == null || appreciation == "") {
                    req.setAttribute("appreciationError", true);
                    valeurManquante++;
                } else {
                    req.setAttribute("appreciation", appreciation);
                    req.setAttribute("appreciationError", "valid");
                }

                if (valeurManquante == 0) {
                    try {
                        //Déclaration du service et du dto
                        MarkService ms = new MarkService();
                        MarkDTO newmark = new MarkDTO();

                        //mappage data
                        int etudiantID = Integer.parseInt(etudiant);
                        int coursID = Integer.parseInt(cours);
                        Float noteFloat = Float.parseFloat(note);

                        newmark.setIdCourse(coursID);
                        newmark.setIdStudent(etudiantID);
                        newmark.setMark(noteFloat);
                        newmark.setAssessment(appreciation);


                        //Appel au service
                        ms.create(newmark);

                        //on remet a zero le formulaire
                        req.setAttribute("info", "Info la note pour l'étudiant " + etudiantID + " sur le cours : " + coursID  + " à bien été ajouter");
                        req.setAttribute("note", "");
                        req.setAttribute("appreciation", "");

                    } catch (ExceptionService es) {
                        req.setAttribute("error", es.getMessage());
                    }
                } else {
                    //Message d'erreur quand ils manques des valeurs dans le formulaires
                    req.setAttribute("error", "Veuillez saisir les valeurs manquantes");
                }


                // renvoit de la page
                this.getServletContext().getRequestDispatcher("/public/AddNote.jsp").forward(req, resp);
            }
        }
    }

}