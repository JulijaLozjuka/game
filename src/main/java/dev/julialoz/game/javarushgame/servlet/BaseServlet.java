package dev.julialoz.game.javarushgame.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    protected static final Logger log = LogManager.getLogger();

    void redirectToLocation(HttpServletResponse resp) {
        try {
            resp.sendRedirect("location");
        } catch (IOException exception) {
            log.error("Error redirecting to location.");
        }
    }

    void writeToResp(HttpServletResponse resp, Object respBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String messageInJson = mapper.writeValueAsString(respBody);
            resp.setContentType("application/json");
            resp.getWriter().write(messageInJson);
        } catch (IOException e) {
            log.error("Can't write to resp {} ", respBody);
        }
    }
}
