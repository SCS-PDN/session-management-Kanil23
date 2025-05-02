import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final Map<String, String> credentials = new HashMap<>();

    @Override
    public void init() {
        credentials.put("student01", "pass01");
        credentials.put("student02", "pass02");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if (user != null && pass != null && credentials.containsKey(user) && credentials.get(user).equals(pass)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userLogged", user);

            Cookie loginCookie = new Cookie("userLogged", user);
            loginCookie.setMaxAge(3600);
            resp.addCookie(loginCookie);

            resp.sendRedirect("DashboardServlet");
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
