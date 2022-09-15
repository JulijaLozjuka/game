package dev.julialoz.game.javarushgame;

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
    private List<Item> items = new ArrayList<>();
    private List<Npc> npcs = new ArrayList<>();
    ;
    private List<Enemy> enemys = new ArrayList<>();
    ;
    private List<Location> locations;
    private List<Message> messages = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();
    private List<Quest> quests = new ArrayList<>();
    ;

    public GameBuilder() {
        items = generateItems();
        quests = generateQuests();
        answers = generateAnswers();
        messages = generateMessages();
        npcs = generateNpcs();
        locations = generateLocations();
    }

    private List<Quest> generateQuests() {
        List<Quest> quests = new ArrayList<>();
        // ladybug quest
        quests.add(CollectingQuest.builder()
                .id(1l)
                .questHolderId(1L) // ladybug id
                .description("Find some water in the swamp")
                .requiredItem(4L)
                .build());
        return quests;
    }

    private List<Answer> generateAnswers() {
        List<Answer> answers = new ArrayList<>();
        //ladybug answers
        // message 1
        answers.add(Answer.builder()
                .id(1l)
                .text("OMG what big bug, don't hurt me please!")
                .nextMessage(2L)
                .build());
        // message 2
        answers.add(Answer.builder()
                .id(2l)
                .text("Oh sorry, can you help me ? ")
                .nextMessage(3L)
                .build());
        answers.add(Answer.builder()
                .id(3l)
                .text("OoooH! (run away)")
                .nextMessage(null)
                .build());
        // message 3
        answers.add(Answer.builder()
                .id(4l)
                .text("Okay let's do it!")
                .nextMessage(null)
                .questId(1L)
                .build());
        answers.add(Answer.builder()
                .id(5l)
                .text("No thank you it sounds too difficult.")
                .nextMessage(null)
                .build());


        return answers;
    }

    private List<Message> generateMessages() {
        List<Message> messages = new ArrayList<>();
        // ladybug dialog
        messages.add(Message.builder()
                .id(1L)
                .answer1(1L)
                .text("Who are you? I have never seen something like you.")
                .build());
        messages.add(Message.builder()
                .id(2L)
                .answer1(2L)
                .answer2(3L)
                .text("Oh I won't hurt you, but you nearly broke my house.")
                .build());
        messages.add(Message.builder()
                .id(2L)
                .answer1(4L)
                .answer2(5L)
                .text("Yes. I can help you to get a weapon for these dangerous parts. You must go and get a drop of water from the swamp.")
                .build());
        return messages;
    }


    private List<Npc> generateNpcs() {
        List<Npc> npcs = new ArrayList<>();

        npcs.add(Npc.builder()
                .id(1L)
                .name("Ladybug")
                .questId(1L) // get water quest
                .dialogStartingMessage(1L)
                .build());

        return npcs;
    }

    private List<Location> generateLocations() {
        List<Location> result = new ArrayList<>();

        // start location
        result.add(Location.builder()
                .id(1L)
                .level(1)
                .name(LocationName.SMALL_GRASS)
                .description("Starting peaceful zone")
                .npcs(List.of(1L)) // 1 ladybug
                .enemyList(null)
                .key(null)
                .items(List.of(1L))
                .neighbouringLocations(List.of(2L, 3L))
                .build());

        // start location neighbours
        result.add(Location.builder()
                .id(2L)
                .level(1)
                .name(LocationName.STUMP)
                .description("Zone with sticks")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of(2L))
                .neighbouringLocations(List.of(1L))
                .build());

        result.add(Location.builder()
                .id(3L)
                .level(1)
                .name(LocationName.SWAMP)
                .description("Zone with swaps stuff")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of(3L))
                .neighbouringLocations(List.of(1L))
                .build());

        return result;
    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();

        items.add(Potion.builder()
                .id(1L)
                .name(ItemName.HEALTH_POTION)
                .build());

        items.add(Weapon.builder()
                .id(2L)
                .name(ItemName.STICK)
                .build());

        items.add(Armor.builder()
                .id(3L)
                .name(ItemName.BOTTLE_CAP)
                .build());

        // Item for ladybug quest
        items.add(QuestItem.builder()
                .id(4L)
                .name(ItemName.WATER)
                .build());

        return items;
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

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Quest> getQuests() {
        return quests;
    }

}
