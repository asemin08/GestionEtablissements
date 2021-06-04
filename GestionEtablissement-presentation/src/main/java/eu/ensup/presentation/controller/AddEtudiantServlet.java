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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/AjouterEtudiant", name="AjouterEtudiant")
public class AddEtudiantServlet extends HttpServlet {

    public AddEtudiantServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "post");
    }


    protected void traitement(HttpServletRequest req, HttpServletResponse resp,  String method) throws ServletException, IOException {
        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else{
            // Etape 1 récu^érer les paramétres
                if(method == "get"){
                    this.getServletContext().getRequestDispatcher("/public/AddEtudiant.jsp").forward(req, resp);
                }else if(method == "post"){

                    // On recupere les attributs et on les traites
                    String nom = req.getParameter("nom");
                    String prenom = req.getParameter("prenom");
                    String addresse = req.getParameter("addresse");
                    String tel = req.getParameter("tel");
                    String email = req.getParameter("email");
                    String date = req.getParameter("date");
                    String password = req.getParameter("password");



                    int valeurManquante = 0;

                    if(nom == null || nom == ""){
                        System.out.println("if");
                        req.setAttribute("nomError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("nom", nom);
                        req.setAttribute("nomError", "valid");
                    }
                    if(prenom == null || prenom == ""){
                        System.out.println("else");
                        req.setAttribute("prenomError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("prenom", prenom);
                        req.setAttribute("prenomError", "valid");
                    }
                    if(tel == null || tel == ""){
                        req.setAttribute("telError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("tel", tel);
                        req.setAttribute("telError", "valid");
                    }
                    if(addresse == null || addresse == ""){
                        req.setAttribute("addresseError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("addresse", addresse);
                        req.setAttribute("addresseError", "valid");
                    }
                    if(email == null || email == ""){
                        req.setAttribute("emailError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("email", email);
                        req.setAttribute("emailError", "valid");
                    }
                    if(password == null || password == ""){
                        req.setAttribute("passwordError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("password", password);
                        req.setAttribute("passwordError", "valid");
                    }
                    if(date == null || date == ""){
                        req.setAttribute("dateError", true);
                        valeurManquante++;
                    }else{
                        req.setAttribute("date", date);
                        req.setAttribute("dateError", "valid");
                    }
                    if(valeurManquante == 0){
                        try {
                            //Etape service
                            PersonService sp = new PersonService();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                            Date auj = sdf.parse(date);
                            sp.create(nom, email, addresse, tel, prenom, password, 4, auj, "");

                            //on remet a zero le formulaire
                            req.setAttribute("info", "Infos l\'étudiant " +nom + " " + prenom + "a bien été créer");
                            req.setAttribute("nom", "");
                            req.setAttribute("prenom", "");
                            req.setAttribute("addresse", "");
                            req.setAttribute("tel", "");
                            req.setAttribute("email", "");
                            req.setAttribute("date", "");
                            req.setAttribute("password", "");

                        } catch (ExceptionService es) {
                            req.setAttribute("error", es.getMessage());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Message d'erreur quand ils manques des valeurs dans le formulaires
                        req.setAttribute("error", "Veuillez saisir les valeurs manquantes");
                    }

                    // renvoit de la page
                    this.getServletContext().getRequestDispatcher("/public/AddEtudiant.jsp").forward(req, resp);



                }
            }
        }
}
