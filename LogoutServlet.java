import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

       
        HttpSession userSession = req.getSession(false);
        if (userSession != null) {
            userSession.invalidate();
        }

        
        Cookie[] clientCookies = req.getCookies();
        if (clientCookies != null) {
            for (Cookie ck : clientCookies) {
                if ("userLogged".equals(ck.getName())) {
                    ck.setMaxAge(0);
                    ck.setPath("/");
                    res.addCookie(ck);
                }
            }
        }

        
        res.sendRedirect("login.html");
    }
}
