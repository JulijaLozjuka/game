package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.business.repository.Repository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.service.StarterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "starterServlet", value = "/start")
public class StarterServlet extends BaseServlet {

    protected static final Logger log = LogManager.getLogger();

    private StarterService starterService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        starterService = new StarterService((Repository<User>) servletContext.getAttribute("userRepository"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Starting servlet POST.");
        String name = req.getParameter("playerName");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            log.info("User found sending to location.");
            redirectToLocation(resp);
            return;
        }

        session.setAttribute("user", starterService.loadUser(name));
        redirectToLocation(resp);
    }
}
