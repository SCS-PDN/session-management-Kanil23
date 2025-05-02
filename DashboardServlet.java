import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userLogged") == null) {
            res.sendRedirect("login.html");
            return;
        }

        
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(new Course("CSC1013", "Inroduction", "Dr. Silva"));
        allCourses.add(new Course("CSC1023", "OOP", "Prof. Perera"));
        allCourses.add(new Course("CSC1032", "Algorithms", "Mr. Fernando"));

        req.setAttribute("courseList", allCourses);

        
        List<String> enrolled = (List<String>) session.getAttribute("enrolledList");
        if (enrolled == null) {
            enrolled = new ArrayList<>();
            session.setAttribute("enrolledList", enrolled);
        }

        req.setAttribute("myCourses", enrolled);

        
        RequestDispatcher view = req.getRequestDispatcher("dashboard.jsp");
        view.forward(req, res);
    }

    
    public static class Course {
        private final String code;
        private final String title;
        private final String lecturer;

        public Course(String code, String title, String lecturer) {
            this.code = code;
            this.title = title;
            this.lecturer = lecturer;
        }

        public String getCode() { return code; }
        public String getTitle() { return title; }
        public String getLecturer() { return lecturer; }
    }
}
