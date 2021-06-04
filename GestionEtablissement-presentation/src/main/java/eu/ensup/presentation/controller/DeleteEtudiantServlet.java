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
@WebServlet(urlPatterns = "/DeleteEtudiant", name="DeleteEtudiant")
public class DeleteEtudiantServlet extends HttpServlet {

    public DeleteEtudiantServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else {

            if (method == "get") {

                String etudiant = req.getParameter("id");
                String redirect = req.getParameter("redirect");
                Boolean redirectBool = Boolean.parseBoolean(redirect);


                try {
                    //Etape service
                    PersonService ps = new PersonService();
                    List<PersonDTO> personList = new ArrayList();

                    int etudiantID = Integer.parseInt(etudiant);

                    ps.delete(etudiantID);

                    req.setAttribute("info", "Infos l\'étudiant " + etudiantID + " " + "a bien été supprimer");

                    //On met a jours la liste de cours
                    for (PersonDTO p : ps.getAll()) {
                        if (p instanceof StudentDTO) {
                            personList.add(p);
                            req.setAttribute("etudiantList", personList);
                        }
                    }
                    maSession.setAttribute("etudiantList", personList);

                } catch (ExceptionService es) {
                    req.setAttribute("error", es.getMessage());
                }
                if(redirectBool == true){
                    this.getServletContext().getRequestDispatcher("/public/GererEtudiant.jsp").forward(req, resp);
                }else if(redirectBool == false){
                    this.getServletContext().getRequestDispatcher("/public/ListEtudiant.jsp").forward(req, resp);
                }
            }
        }

    }

}
