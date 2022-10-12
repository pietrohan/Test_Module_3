package Controller;


import dao.StudentDAO;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentSevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertStudent(request, response);
                    break;
                case "edit":
                    updateStudent(request, response);
                    break;
                case "find":
                    search(request,response);
                    break;
                case "sort":
                    sort(request,response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        request.setAttribute("Student", existingStudent);
        dispatcher.forward(request, response);

        //Student existingStudent = StudentDAO.selectStudent(id);

        existingStudent = studentDAO.getStudentById(id);

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String dateofBirth = request.getParameter("dateofBirth");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Student newStudent = new Student(name,dateofBirth,address,phoneNumber, email);
        // StudentDAO.insertStudent(newStudent);
        studentDAO.insertStudent(newStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request, response);

        //StudentDAO.insertStudent(newStudent);

    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String dateofBirth = request.getParameter("dateofBirth");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        Student book = new Student(id, name,dateofBirth,address,phoneNumber, email);
        studentDAO.updateStudent(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);

        List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name= request.getParameter("name");
        List<Student> listStudent = studentDAO.selectStudentsByName(name);
        request.setAttribute("StudentsbyName", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/find.jsp");
        dispatcher.forward(request, response);
    }
    private void sort(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent = studentDAO.sortByName();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }
}
