package dev.julialoz.game.javarushgame.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "indexServlet", value = "")
public class IndexServlet extends BaseServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                .forward(request, response);
    }
}