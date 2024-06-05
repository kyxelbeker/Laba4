import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MainService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private final MainService mainService;

    public MainServlet() {
        this.mainService = new MainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Пользовательская информация</title>");
        out.println("</head>");
        out.println("<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0;\">");

        out.println("<div style=\"max-width: 800px; margin: 20px auto;\">");
        out.println("<h2 style=\"text-align: center;\">Список пассажиров</h2>");
        out.println("<table style=\"width: 100%; border-collapse: collapse;\">");
        out.println("<tr style=\"background-color: #f2f2f2;\">");
        out.println("<th style=\"padding: 8px; text-align: left;\">ID</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Имя</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Пароль</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Email</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Дата рождения</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Удалить</th>");
        out.println("<th style=\"padding: 8px; text-align: left;\">Редактировать</th>");
        out.println("</tr>");

        for (Map<String, Object> row : mainService.getAllUsers()) {
            out.println("<tr>");
            out.println("<td style=\"padding: 8px;\">" + row.get("id") + "</td>");
            out.println("<td style=\"padding: 8px;\">" + row.get("name") + "</td>");
            out.println("<td style=\"padding: 8px;\">" + row.get("password") + "</td>");
            out.println("<td style=\"padding: 8px;\">" + row.get("email") + "</td>");
            out.println("<td style=\"padding: 8px;\">" + row.get("birth_at") + "</td>");
            out.println("<td style=\"padding: 8px;\"><a href='delete?id=" + row.get("id") + "' style=\"text-decoration: none; color: red;\">Удалить</a></td>");
            out.println("<td style=\"padding: 8px;\"><a href='edit?id=" + row.get("id") + "&name=" + row.get("name") + "&password=" + row.get("password") + "' style=\"text-decoration: none; color: blue;\">Изменить</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");

        out.println("<h2 style=\"text-align: center;\">Добавить пользователя</h2>");
        out.println("<div style=\"text-align: center;\">");
        out.println("<form method='post' style=\"display: inline-block;\">");
        out.println("<label for='name'>Введите Имя</label>");
        out.println("<input name='name' id='name' type='text' required /><br>");
        out.println("<label for='password'>Введите пароль</label>");
        out.println("<input name='password' id='password' type='text' required /><br>");
        out.println("<label for='email'>Введите email</label>");
        out.println("<input name='email' id='email' type='text' required /><br>");
        out.println("<input type='submit' value='Отправить'>");
        out.println("</form>");
        out.println("</div>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        mainService.addUser(name, password, email);
        resp.sendRedirect("/Beker/main");
    }
}