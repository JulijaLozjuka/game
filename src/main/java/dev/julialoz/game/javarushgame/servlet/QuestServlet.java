package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.quest.Quest;
import dev.julialoz.game.javarushgame.service.QuestService;
import dev.julialoz.game.javarushgame.servlet.dto.QuestDto;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "questServlet", value = "/quest")
public class QuestServlet extends BaseServlet {

    private QuestService questService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        questService = new QuestService((QuestRepository) servletContext.getAttribute("questRepository"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String questIdParam = req.getParameter("questId");
        User user = (User) req.getSession().getAttribute("user");
        Long questId = questIdParam != null ? Long.valueOf(questIdParam.trim()) : null;
        Quest quest = questService.updateQuestStatus(questId, user);
        writeToResp(resp, QuestDto.builder()
                .questId(quest.getId())
                .description(quest.getDescription())
                .done(quest.isQuestDone(user))
                .build());
    }
}
