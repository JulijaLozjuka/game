package dev.julialoz.game.javarushgame.model.quest;

import dev.julialoz.game.javarushgame.model.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class KillingQuest extends Quest {
    @Override
    public boolean isQuestDone(User user) {
        return false;
    }
}
