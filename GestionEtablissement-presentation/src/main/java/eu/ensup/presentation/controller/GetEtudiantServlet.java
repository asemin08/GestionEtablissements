package eu.ensup.presentation.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.ensup.gestionetablissement.business.Person;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Allan
 */
@WebServlet(urlPatterns = "/GetEtudiant", name="GetEtudiant")
public class GetEtudiantServlet extends HttpServlet {

    public GetEtudiantServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }

    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        String etudiant = req.getParameter("id");
        PersonDTO person =  new PersonDTO();


        try {
            //Etape service
            PersonService ps = new PersonService();

            int etudiantID = Integer.parseInt(etudiant);

            person = ps.get(etudiantID);

            ObjectMapper myObjectMapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            myObjectMapper.setDateFormat(df);
            ObjectWriter ow = myObjectMapper.writer().withDefaultPrettyPrinter();
            String personjson = ow.writeValueAsString(person);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF8");
            resp.getWriter().write(String.valueOf(personjson));


        }catch (ExceptionService es) {
            req.setAttribute("error", es.getMessage());
        }

    }



}
