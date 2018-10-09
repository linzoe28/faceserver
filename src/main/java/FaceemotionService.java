/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String base64 = request.getParameter("base64");
        String id = request.getParameter("id");
        String emtion = request.getParameter("emtion");
        String json = request.getParameter("json");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/emotional_physiology", "root", "len1028");
            PreparedStatement pstmt = conn.prepareStatement("insert into face_emotion(faceid, id, emotion, face_x, face_y, face_width, face_height, eyeleftouter_x, eyeleftouter_y, eyeleftinner_x, eyeleftinner_y, eyerightouter_x, eyerightouter_y, eyerightinner_x, eyerightinner_y, mouthleft_x, mouthleft_y, mouthright_x, mouthright_y, eyebrowleftouter_x, eyebrowleftouter_y, eyebrowrightouter_x, eyebrowrightouter_y, noseleftalarouttip_x, noseleftalarouttip_y, noserightalarouttip_x, noserightalarouttip_y, base64)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //pstmt.setString(1, faceid);
            pstmt.setString(2, id);
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
