package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.Enemy;
import dev.julialoz.game.javarushgame.model.Location;
import dev.julialoz.game.javarushgame.model.LocationName;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.item.*;
import dev.julialoz.game.javarushgame.model.npc.Answer;
import dev.julialoz.game.javarushgame.model.npc.Message;
import dev.julialoz.game.javarushgame.model.npc.Npc;
import dev.julialoz.game.javarushgame.model.quest.CollectingQuest;
import dev.julialoz.game.javarushgame.model.quest.Quest;

import java.util.ArrayList;
import java.util.List;

// items
public class GameBuilder {

    private QuestBuilder questBuilder = new QuestBuilder();
    private AnswerBuilder answerBuilder = new AnswerBuilder();
    private MessageBuilder messageBuilder = new MessageBuilder();
    private NpcBuilder npcBuilder = new NpcBuilder();
    private LocationBuilder locationBuilder = new LocationBuilder();
    private ItemBuilder itemBuilder = new ItemBuilder();
    private List<Item> items;
    private List<Npc> npcs;
    private List<Enemy> enemys = new ArrayList<>();
    private List<Location> locations;
    private List<Message> messages;
    private List<Answer> answers;
    private List<Quest> quests;

    public GameBuilder() {
        items = itemBuilder.generateItems();
        quests = questBuilder.generateQuests();
        answers = answerBuilder.generateAnswers();
        messages = messageBuilder.generateMessages();
        npcs = npcBuilder.generateNpcs();
        locations = locationBuilder.generateLocations();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public List<Enemy> getEnemys() {
        return enemys;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<User> getUsers() {
        return new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Quest> getQuests() {
        return quests;
    }

}
