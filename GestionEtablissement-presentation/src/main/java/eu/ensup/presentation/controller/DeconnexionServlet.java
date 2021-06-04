
package eu.ensup.presentation.controller;

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

@WebServlet(urlPatterns = "/Deconnexion", name="Deconnexion")
public class DeconnexionServlet extends HttpServlet {


    public DeconnexionServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

      // Récupération et destruction de la session en cours

        HttpSession session = req.getSession();
        session.invalidate();

        //Redirection vers l'écran de connexion
        resp.sendRedirect(req.getContextPath() + "/login");

    }


}
