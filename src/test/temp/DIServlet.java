package temp;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "DIServlet", urlPatterns = "/index.jsp")
public class DIServlet extends HttpServlet {

    @Inject
    ClientsServiceImpl clientsService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("test", 1234);
        Enumeration<String> names = session.getAttributeNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }
    }
}
