import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String selectedCourseId = req.getParameter("courseId");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userLogged") == null) {
            res.sendRedirect("login.html");
            return;
        }

        
        List<DashboardServlet.Course> availableCourses = List.of(
            new DashboardServlet.Course("CSC1013", "Inroduction", "Dr. Silva"),
            new DashboardServlet.Course("CSC1023", "OOP", "Prof. Perera"),
            new DashboardServlet.Course("CSC1032", "Algorithms", "Mr. Fernando")
        );

        
        List<String> enrolledList = (List<String>) session.getAttribute("enrolledList");
        if (enrolledList == null) {
            enrolledList = new ArrayList<>();
        }

        
        if (!enrolledList.contains(selectedCourseId)) {
            enrolledList.add(selectedCourseId);
        }

        session.setAttribute("enrolledList", enrolledList);

        res.sendRedirect("DashboardServlet");
    }
}
