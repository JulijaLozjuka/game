package dev.julialoz.game.javarushgame;


import dev.julialoz.game.javarushgame.business.repository.*;
import dev.julialoz.game.javarushgame.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        GameBuilder initializer = new GameBuilder();
        ServletContext ctx = servletContextEvent.getServletContext();

        UserRepository userRepository = new UserRepository();
        for (User user : initializer.getUsers()) {
            userRepository.save(user);
        }
        ctx.setAttribute("userRepository", userRepository);

        loadDataToRepo(ctx, new ItemRepository(), "itemRepository", initializer.getItems());
        loadDataToRepo(ctx, new NpcRepository(), "npcRepository", initializer.getNpcs());
        loadDataToRepo(ctx, new EnemyRepository(), "enemyRepository", initializer.getEnemys());
        loadDataToRepo(ctx, new LocationRepository(), "locationRepository", initializer.getLocations());
        loadDataToRepo(ctx, new AnswerRepository(), "answerRepository", initializer.getAnswers());
        loadDataToRepo(ctx, new MessageRepository(), "messageRepository", initializer.getMessages());
        loadDataToRepo(ctx, new QuestRepository(), "questRepository", initializer.getQuests());

    }

    private static void loadDataToRepo(ServletContext ctx, Repository repository, String repoName, List list) {
        for (Object item : list) {
            repository.save(item);
        }
        ctx.setAttribute(repoName, repository);
    }

}