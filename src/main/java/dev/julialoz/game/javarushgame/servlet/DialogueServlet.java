package dev.julialoz.game.javarushgame.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.julialoz.game.javarushgame.business.repository.AnswerRepository;
import dev.julialoz.game.javarushgame.business.repository.MessageRepository;
import dev.julialoz.game.javarushgame.model.npc.Answer;
import dev.julialoz.game.javarushgame.model.npc.Message;
import dev.julialoz.game.javarushgame.servlet.dto.AnswerDto;
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

    private MessageRepository messageRepository = null;
    private AnswerRepository answerRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        messageRepository = (MessageRepository) servletContext.getAttribute("messageRepository");
        answerRepository = (AnswerRepository) servletContext.getAttribute("answerRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageId = req.getParameter("messageId").trim();
        Message message = messageRepository.findById(Long.parseLong(messageId)).get();
        Answer answer1 = message.getAnswer1() != null ? answerRepository.findById(message.getAnswer1()).get() : null;
        Answer answer2 = message.getAnswer2() != null ? answerRepository.findById(message.getAnswer2()).get() : null;
        MessageDto messageDto = mapToMessageDto(message, answer1, answer2);
        ObjectMapper mapper = new ObjectMapper();
        String messageInJson = mapper.writeValueAsString(messageDto);

        resp.setContentType("application/json");
        resp.getWriter().write(messageInJson);
    }

    private MessageDto mapToMessageDto(Message message, Answer answer1, Answer answer2) {
        return MessageDto.builder()
                .text(message.getText())
                .answer1(AnswerDto.builder()
                        .text(answer1.getText())
                        .nextMessage(answer1.getNextMessage())
                        .questId(answer1.getQuestId())
                        .build())
                .answer2(AnswerDto.builder()
                        .text(answer2.getText())
                        .nextMessage(answer2.getNextMessage())
                        .questId(answer2.getQuestId())
                        .build())
                .build();
    }
}
