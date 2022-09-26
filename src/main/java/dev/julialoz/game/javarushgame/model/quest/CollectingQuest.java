package dev.julialoz.game.javarushgame.model.quest;

import dev.julialoz.game.javarushgame.model.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CollectingQuest extends Quest {

    private Long requiredItem;

    @Override
    public boolean isQuestDone(User user) {
        return user.getInventory().contains(requiredItem);
    }
}
