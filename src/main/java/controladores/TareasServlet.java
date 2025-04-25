/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/**
 *
 * @author Personal
 */
@WebServlet(name = "TareasServlet", urlPatterns = {"/TareasServlet"})
public class TareasServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TareasServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TareasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        List<Tarea> listaTareas = (List<Tarea>)
                session.getAttribute("tareas");
        
        if (listaTareas == null){
            listaTareas = new ArrayList<>();
            session.setAttribute("tareas", listaTareas);
        }
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || "/".equals(pathInfo)){
            request.getRequestDispatcher("/WEB-INF/views/listarTareas.jsp").forward(request, response);
        }else if ("/nueva".equals(pathInfo)){
            request.getRequestDispatcher("/WEB-INF/views/nuevaTarea.jsp").forward(request, response);
        }else if (pathInfo.startsWith("/completar/")){
            try {
                int id = Integer.parseInt(pathInfo.substring("/completar/".length()));
                
                for (Tarea tarea : listaTareas){
                    if (tarea.getId()==id){
                        tarea.setCompletada(true);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                //ignorar
            }
            response.sendRedirect(request.getContextPath()+"/tareas/");
        }
        }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
HttpSession session = request.getSession();
List<Tarea> listaTareas = (List<Tarea>) session.getAttribute("tareas");

if (listaTareas == null){
listaTareas = new ArrayList<>();
session.setAttribute("tareas", listaTareas);
}
String pathInfo = request.getPathInfo();

if ("/guardar".equals(pathInfo)){
String descripcion = request.getParameter("descripcion");

if (descripcion != null && !descripcion.trim().isEmpty())
{
    int nuevoId = 1;
if (!listaTareas.isEmpty()){
    nuevoId = listaTareas.get (listaTareas.size()-1).getId() +1;
}
Tarea nuevaTarea = new Tarea(nuevoId, descripcion);
listaTareas.add (nuevaTarea);
}
    response.sendRedirect (request.getContextPath()+"/tareas");
}
}
}


