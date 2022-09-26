package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.business.repository.Repository;
import dev.julialoz.game.javarushgame.model.User;
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
import java.util.stream.Collectors;

@WebServlet(name = "starterServlet", value = "/start")
public class StarterServlet extends BaseServlet {

    protected static final Logger log = LogManager.getLogger();

    private Repository<User> userRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userRepository = (Repository<User>) servletContext.getAttribute("userRepository");
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

        List<User> users = userRepository.findAll().stream()
                .filter(user1 -> name.equals(user1.getName()))
                .collect(Collectors.toList());
        User user;
        if (users.size() > 0) {
            log.info("Loaded user from saved data.");
            user = users.get(0);
        } else {
            log.info("New user created.");
            user = User.builder()
                    .name(name)
                    .level(1)
                    .currentLocationId(1L)
                    .maxHealth(100)
                    .currentHealth(100)
                    .build();
            userRepository.save(user);
        }

        session.setAttribute("user", user);
        redirectToLocation(resp);
    }
}
