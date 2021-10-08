/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class add_cat extends HttpServlet {

    String cat_nm;
    String act_d;
    Connection con;
    PreparedStatement ps;
    String msg="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd=getServletContext().getRequestDispatcher("/add_cat.jsp");
        if ((request.getParameter("cat_name") != null) && (request.getParameter("cat_detail") != null)) {
            cat_nm = request.getParameter("cat_name");
            act_d = request.getParameter("cat_detail");
        
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery_db", "root", "77492652");
                ps = con.prepareStatement("insert into category(cat_name,cat_detail) values(?,?)");
                ps.setString(1, cat_nm);
                ps.setString(2, act_d);
                int i = ps.executeUpdate();
                if (i == 1) {
                  msg="Data successfully Added";
                  request.setAttribute("catmsg", msg);
                  rd.forward(request, response);
                }
                ps.close();
                
            }catch(Exception e)
            {
                out.println(e.getMessage());
            }
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
            protected void doGet
            (HttpServletRequest request, HttpServletResponse response)
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
            protected void doPost
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
            }

            /**
             * Returns a short description of the servlet.
             *
             * @return a String containing servlet description
             */
            @Override
            public String getServletInfo
            
                () {
        return "Short description";
            }// </editor-fold>

        }
