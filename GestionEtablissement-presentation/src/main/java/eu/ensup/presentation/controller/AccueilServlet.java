package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/Accueil", name="Accueil")
public class AccueilServlet extends HttpServlet {

    public AccueilServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp);
    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else{
            this.getServletContext().getRequestDispatcher("/public/Accueil.jsp").forward(req, resp);
        }


    }
}
