package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/login", name="login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "post");
    }

    protected void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {
        // Etape 1 récu^érer les paramétres
        if(method == "get"){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }else if( method == "post"){
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            //Etape service
            ConnectionService sc = new ConnectionService();
            PersonService sp = new PersonService();
            PersonDTO person = new PersonDTO();

            int idConnexion = 0;
            try {
                idConnexion = sc.checkConnection(login, password);
                person = sp.get(idConnexion);

            } catch (ExceptionService es) {
                req.setAttribute("error",  es.getMessage());
            }

            if(idConnexion > 0 && (person.getRole().toString() == "DIRECTOR" || person.getRole().toString() == "MANAGER")) {
                HttpSession maSession = req.getSession();
                maSession.setAttribute("utilisateur",person);
                resp.sendRedirect(req.getContextPath() + "/Accueil");
//                this.getServletContext().getRequestDispatcher("Accueil").forward(req, resp);
            }

            else{
                this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
            }

        }




    }

}


