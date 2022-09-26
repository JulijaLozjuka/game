package dev.julialoz.game.javarushgame.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.julialoz.game.javarushgame.business.repository.AnswerRepository;
import dev.julialoz.game.javarushgame.business.repository.MessageRepository;
import dev.julialoz.game.javarushgame.business.repository.NpcRepository;
import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.service.DialogueService;
import dev.julialoz.game.javarushgame.servlet.dto.MessageDto;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dialogueServlet", value = "/dialogue")
public class DialogueServlet extends BaseServlet {

    private DialogueService dialogueService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();

        dialogueService = new DialogueService(
                (QuestRepository) servletContext.getAttribute("questRepository"),
                (MessageRepository) servletContext.getAttribute("messageRepository"),
                (AnswerRepository) servletContext.getAttribute("answerRepository"),
                (NpcRepository) servletContext.getAttribute("npcRepository")
        );
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String messageId = req.getParameter("messageId").trim();
        String npcId = req.getParameter("npcId").trim();
        User user = (User) req.getSession().getAttribute("user");
        MessageDto messageDto = dialogueService.getMessageDto(messageId, user, npcId);
        writeToResp(resp, messageDto);
    }

}
