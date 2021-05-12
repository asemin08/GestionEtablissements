package eu.ensup.presentation.controller;

import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.ExceptionService;
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
import java.util.logging.Logger;

/**
 * @author Allan
 */

@WebServlet(urlPatterns = "/Statistiquepiechart", name="Statistiquepiechart")
public class StatistiqueJfreepiechartServlet extends HttpServlet {

    protected final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());


    public StatistiqueJfreepiechartServlet() {
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

                JFreeChart piechart = getpiechart();

                int width = 700;
                int height = 450;

                System.out.println(piechart);

                ChartUtils.writeChartAsPNG(os, piechart, width, height);

//                Redirection vers l'écran de connexion
                this.getServletContext().getRequestDispatcher("/public/Statistiquejfreechart.jsp").forward(req, resp);

            }

        }
    }



    private JFreeChart getpiechart() {
        JFreeChart pieChart = ChartFactory.createPieChart("Niveau des étudiants par moyenne", createPieDataset());
        pieChart.getPlot().setBackgroundPaint(new Color(39,55,70));
        pieChart.getLegend().setBackgroundPaint(new Color(39,55,70));
        pieChart.setBackgroundPaint(new Color(39,55,70));
        pieChart.getTitle().setPaint(new Color(255,255,255));
        pieChart.getLegend().setItemPaint(new Color(255,255,255));
        pieChart.getPlot().setOutlinePaint(null);
        PiePlot plot1 = (PiePlot)pieChart.getPlot();
        plot1.setSectionPaint(0, new Color(217,83,79));
        plot1.setSectionPaint(1, new Color(240,173,78));
        plot1.setSectionPaint(2, new Color(92,184,92));
        plot1.setSectionPaint(3, new Color(2,117,216));

        ChartPanel chartPanel2 = new ChartPanel(pieChart);
        chartPanel2.setBackground(new Color(39,55,70));

        return pieChart;
    }


    private DefaultPieDataset createPieDataset() {
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

        int total = badStudent + averageStudent + goodStudent + excellentStudent;
        DefaultPieDataset dataset = new DefaultPieDataset();

        if(total == 0){
            dataset.setValue("Etudiant mauvais (0 à 8)", 0);
            dataset.setValue("Etudiant moyen (8 à 12)", 0);
            dataset.setValue("Etudiant bon (12 à 17)", 0);
            dataset.setValue("Etudiant excellent (17 à 20)", 0);
        }else{
            dataset.setValue("Etudiant mauvais (0 à 8)", (badStudent * 100 / total));
            dataset.setValue("Etudiant moyen (8 à 12)", (averageStudent * 100 / total));
            dataset.setValue("Etudiant bon (12 à 17)", (goodStudent * 100 / total));
            dataset.setValue("Etudiant excellent (17 à 20)", (excellentStudent * 100 / total));
        }
        return dataset;
    }


}