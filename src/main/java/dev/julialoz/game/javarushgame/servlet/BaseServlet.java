package dev.julialoz.game.javarushgame.servlet;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    protected static final Logger log = LogManager.getLogger();

    static void redirectToLocation(HttpServletResponse resp) {
        try {
            resp.sendRedirect("location");
        } catch (IOException exception) {
            log.error("Error redirecting to location.");
        }
    }
}
