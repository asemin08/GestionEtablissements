package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Allan
 */

@WebServlet(urlPatterns = "/Statistiquebarchart", name="Statistiquebarchart")
public class StatistiqueJfreebarchartServlet extends HttpServlet {

    protected final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());


    public StatistiqueJfreebarchartServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        traitement(req, resp, "get");
    }


    //
    private void traitement(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {

        HttpSession maSession = req.getSession();

        PersonDTO person = (PersonDTO) maSession.getAttribute("utilisateur");

        if(person == null){
            this.getServletContext().getRequestDispatcher("/public/Login.jsp").forward(req, resp);
        }
        else {


            // Etape 1 récu^érer les paramétres
            if (method == "get") {

                resp.setContentType("image/png");
                OutputStream os = resp.getOutputStream();

                JFreeChart chart = getHistoChart();
                int width = 1000;
                int height = 450;



                ChartUtils.writeChartAsPNG(os, chart, width, height);

//                Redirection vers l'écran de connexion
                this.getServletContext().getRequestDispatcher("/public/Statistiquejfreechart.jsp").forward(req, resp);

            }

        }
    }

    private JFreeChart getHistoChart() {
        JFreeChart histo = ChartFactory.createBarChart("Classement des étudiants par moyenne", "Moyenne", "Etudiant", createDataset(), PlotOrientation.VERTICAL, true, true,false);
        histo.getPlot().setBackgroundPaint(new Color(39,55,70));
        histo.setBackgroundPaint(new Color(39,55,70));
        histo.getLegend().setBackgroundPaint(new Color(39,55,70));
        histo.getTitle().setPaint(new Color(255,255,255));
        CategoryPlot plot = histo.getCategoryPlot();

        // Change Bar colors */
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(217,83,79));
        renderer.setSeriesPaint(1, new Color(240,173,78));
        renderer.setSeriesPaint(2, new Color(92,184,92));
        renderer.setSeriesPaint(3, new Color(2,117,216));
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0);
        renderer.setLegendTextPaint(0,new Color(255,255,255));
        renderer.setLegendTextPaint(1,new Color(255,255,255));
        renderer.setLegendTextPaint(2,new Color(255,255,255));
        renderer.setLegendTextPaint(3,new Color(255,255,255));

        plot.getDomainAxis().setLabelPaint(new Color(255,255,255));
        plot.getRangeAxis().setLabelPaint(new Color(255,255,255));
        plot.getDomainAxis().setTickLabelPaint(new Color(255,255,255));
        plot.getRangeAxis().setTickLabelPaint(new Color(255,255,255));
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());


        return histo;
    }



    private DefaultCategoryDataset createDataset() {
        int badStudent = 0;
        int averageStudent = 0;
        int goodStudent = 0;
        int excellentStudent = 0;
        //Add item in combobox student
        PersonService ps = new PersonService();
        try {
            for(PersonDTO p : ps.getAll()){
                if(p instanceof StudentDTO) {
                    if(((StudentDTO) p).getAverage() < 8){
                        badStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 8 && ((StudentDTO) p).getAverage() < 12){
                        averageStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 12 && ((StudentDTO) p).getAverage() < 17){
                        goodStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 17){
                        excellentStudent++;
                    }
                }
            }
        } catch (ExceptionService es) {
            LOG.warning(es.getMessage());
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(badStudent, "Etudiant mauvais (0 à 8)", "Etudiant mauvais (0 à 8)");
        dataset.addValue(averageStudent, "Etudiant moyen (8 à 12)", "Etudiant moyen (8 à 12)");
        dataset.addValue(goodStudent, "Etudiant bon (12 à 17)", "Etudiant bon (12 à 17)");
        dataset.addValue(excellentStudent, "Etudiant excellent (17 à 20)", "Etudiant excellent (17 à 20)");
        return dataset;
    }


}