import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MainService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private final MainService mainService;

    public EditServlet() {
        this.mainService = new MainService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            mainService.updateUser(id, name, password);
            resp.sendRedirect("/Beker/main");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат ID");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Редактирование пассажира</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #FFA07A;");
        out.println("    background-size: 400% 400%;");
        out.println("    animation: gradient 15s ease infinite;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 100vh;");
        out.println("    margin: 0;");
        out.println("}");
        out.println("@keyframes gradient {");
        out.println("    0% { background-position: 0% 50%; }");
        out.println("    50% { background-position: 100% 50%; }");
        out.println("    100% { background-position: 0% 50%; }");
        out.println("}");
        out.println(".container {");
        out.println("    background: #fff;");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    box-shadow: 0 0 10px rgba(0,0,0,0.1);");
        out.println("    max-width: 400px;");
        out.println("    width: 100%;");
        out.println("}");
        out.println("h2 { color: #333; text-align: center; }");
        out.println("form { display: flex; flex-direction: column; }");
        out.println("label { margin-bottom: 5px; }");
        out.println("input[type='text'], input[type='submit'] {");
        out.println("    padding: 10px;");
        out.println("    margin-bottom: 15px;");
        out.println("    border-radius: 5px;");
        out.println("    border: 1px solid #ddd;");
        out.println("}");
        out.println("input[type='submit'] {");
        out.println("    background-color: #007BFF;");
        out.println("    color: white;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("input[type='submit']:hover {");
        out.println("    background-color: #0056b3;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Редактирование</h2>");
        out.println("<form method='post' action='/Beker/edit'>");
        out.println("<input type='hidden' name='id' value='" + req.getParameter("id") + "'>");
        out.println("<label for='name'>name:</label>");
        out.println("<input type='text' id='uname' name='name' value='" + req.getParameter("name") + "' required>");
        out.println("<label for='password'>Password:</label>");
        out.println("<input type='text' id='password' name='password' value='" + req.getParameter("password") + "' required>");
        out.println("<input type='submit' value='Сохранить изменения'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}