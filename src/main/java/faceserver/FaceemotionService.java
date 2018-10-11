package faceserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author imsofa
 */
@WebServlet(urlPatterns = {"/FaceemotionService"})
public class FaceemotionService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String base64 = request.getParameter("base64");
        String id = request.getParameter("id");
        String emotion = request.getParameter("emotion");
        String datatime = request.getParameter("datatime");
        String json = request.getParameter("json");
        Map map = new Gson().fromJson(json, Map.class);
        Map largestFaceBoundingBox = (Map) map.get("largestFaceBoundingBox");
        Double face_width = (Double) largestFaceBoundingBox.get("width");
        Double face_height = (Double) largestFaceBoundingBox.get("height");
        Double face_x = (Double) largestFaceBoundingBox.get("left");
        Double face_y = (Double) largestFaceBoundingBox.get("top");
        Map landmarks = (Map) map.get("landmarks");
        Double EyeRightOuter_x = (Double) ((List) landmarks.get("EyeRightOuter")).get(0);
        Double EyeRightOuter_y = (Double) ((List) landmarks.get("EyeRightOuter")).get(1);
        Double MouthLeft_x = (Double) ((List) landmarks.get("MouthLeft")).get(0);
        Double MouthLeft_y = (Double) ((List) landmarks.get("MouthLeft")).get(1);
        Double EyeLeftInner_x = (Double) ((List) landmarks.get("EyeLeftInner")).get(0);
        Double EyeLeftInner_y = (Double) ((List) landmarks.get("EyeLeftInner")).get(1);
        Double EyebrowRightOuter_x = (Double) ((List) landmarks.get("EyebrowRightOuter")).get(0);
        Double EyebrowRightOuter_y = (Double) ((List) landmarks.get("EyebrowRightOuter")).get(1);
        Double EyeRightInner_x = (Double) ((List) landmarks.get("EyeRightInner")).get(0);
        Double EyeRightInner_y = (Double) ((List) landmarks.get("EyeRightInner")).get(1);
        Double EyebrowLeftInner_x = (Double) ((List) landmarks.get("EyebrowLeftInner")).get(0);
        Double EyebrowLeftInner_y = (Double) ((List) landmarks.get("EyebrowLeftInner")).get(1);
        Double NoseRightAlarOutTip_x = (Double) ((List) landmarks.get("NoseRightAlarOutTip")).get(0);
        Double NoseRightAlarOutTip_y = (Double) ((List) landmarks.get("NoseRightAlarOutTip")).get(1);
        Double EyebrowRightInner_x = (Double) ((List) landmarks.get("EyebrowRightInner")).get(0);
        Double EyebrowRightInner_y = (Double) ((List) landmarks.get("EyebrowRightInner")).get(1);
        Double NoseLeftAlarOutTip_x = (Double) ((List) landmarks.get("NoseLeftAlarOutTip")).get(0);
        Double NoseLeftAlarOutTip_y = (Double) ((List) landmarks.get("NoseLeftAlarOutTip")).get(1);
        Double MouthRight_x = (Double) ((List) landmarks.get("MouthRight")).get(0);
        Double MouthRight_y = (Double) ((List) landmarks.get("MouthRight")).get(1);
        Double EyebrowLeftOuter_x = (Double) ((List) landmarks.get("EyebrowLeftOuter")).get(0);
        Double EyebrowLeftOuter_y = (Double) ((List) landmarks.get("EyebrowLeftOuter")).get(1);
        Double EyeLeftOuter_x = (Double) ((List) landmarks.get("EyeLeftOuter")).get(0);
        Double EyeLeftOuter_y = (Double) ((List) landmarks.get("EyeLeftOuter")).get(1);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaceemotionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/emotional_physiology", "root", "len1028");
            PreparedStatement pstmt = conn.prepareStatement("insert into face_emotion(faceid, base64, id, emotion, face_x, face_y, face_width, face_height, eyeleftouter_x, eyeleftouter_y, eyeleftinner_x, eyeleftinner_y, eyerightouter_x, eyerightouter_y, eyerightinner_x, eyerightinner_y, mouthleft_x, mouthleft_y, mouthright_x, mouthright_y, eyebrowleftouter_x, eyebrowleftouter_y, eyebrowrightouter_x, eyebrowrightouter_y, eyebrowleftinner_x, eyebrowleftinner_y, eyebrowrightinner_x, eyebrowrightinner_y, noseleftalarouttip_x, noseleftalarouttip_y, noserightalarouttip_x, noserightalarouttip_y,datatime)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, base64);
            pstmt.setString(3, id);
            pstmt.setString(4, emotion);
            pstmt.setDouble(5, face_x);
            pstmt.setDouble(6, face_y);
            pstmt.setDouble(7, face_width);
            pstmt.setDouble(8, face_height);
            pstmt.setDouble(9, EyeLeftOuter_x);
            pstmt.setDouble(10, EyeLeftOuter_y);
            pstmt.setDouble(11, EyeLeftInner_x);
            pstmt.setDouble(12, EyeLeftInner_y);
            pstmt.setDouble(13, EyeRightOuter_x);
            pstmt.setDouble(14, EyeRightOuter_y);
            pstmt.setDouble(15, EyeRightInner_x);
            pstmt.setDouble(16, EyeRightInner_y);
            pstmt.setDouble(17, MouthLeft_x);
            pstmt.setDouble(18, MouthLeft_y);
            pstmt.setDouble(19, MouthRight_x);
            pstmt.setDouble(20, MouthRight_y);
            pstmt.setDouble(21, EyebrowLeftOuter_x);
            pstmt.setDouble(22, EyebrowLeftOuter_y);
            pstmt.setDouble(23, EyebrowRightOuter_x);
            pstmt.setDouble(24, EyebrowRightOuter_y);
            pstmt.setDouble(25, EyebrowLeftInner_x);
            pstmt.setDouble(26, EyebrowLeftInner_y);
            pstmt.setDouble(27, EyebrowRightInner_x);
            pstmt.setDouble(28, EyebrowRightInner_y);
            pstmt.setDouble(29, NoseLeftAlarOutTip_x);
            pstmt.setDouble(30, NoseLeftAlarOutTip_y);
            pstmt.setDouble(31, NoseRightAlarOutTip_x);
            pstmt.setDouble(32, NoseRightAlarOutTip_y);
            pstmt.setString(33, datatime);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaceemotionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
