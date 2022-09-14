package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "starterServlet", value = "/start")
public class StarterServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("room");
            return;
        }

        User user;
        if (userRepository.isExists(username)) {
            user = userRepository.getById(username);
        } else {
            userRepository.save(User.builder()
                    .name(name)
                    .currentRoomId(1)
                    .maxHealth(100)
                    .currentHealth(100)
                    .build());
        }

        session.setAttribute("user", user);
    }
}
