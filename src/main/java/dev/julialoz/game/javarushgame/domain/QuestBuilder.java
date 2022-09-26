package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.quest.CollectingQuest;
import dev.julialoz.game.javarushgame.model.quest.Quest;

import java.util.ArrayList;
import java.util.List;

public class QuestBuilder {

    QuestBuilder() {
    }

    public List<Quest> generateQuests() {
        List<Quest> quests = new ArrayList<>();
        // ladybug quest
        quests.add(CollectingQuest.builder()
                .id(1l)
                .questHolderId(1L) // ladybug id
                .description("Find some water in the swamp")
                .requiredItem(4L) // water
                .build());

        // firefly quest
        quests.add(CollectingQuest.builder()
                .id(2l)
                .questHolderId(2L) // ladybug id
                .description("Find a hat you have see somewhere.")
                .requiredItem(5L) // hat
                .build());
        return quests;
    }
}
