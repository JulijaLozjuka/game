package dev.julialoz.game.javarushgame.service;

import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@RequiredArgsConstructor
public class QuestService {

    private static final Logger log = LogManager.getLogger();

    private final QuestRepository questRepository;

    public Quest updateQuestStatus(Long questId, User user) {
        Optional<Quest> questOptional = questRepository.findById(questId);
        Quest quest = questOptional.orElse(null);
        if (questId == null || user == null || quest == null) {
            log.warn("Can't update quest with id: {}", questId);
        } else if (quest.isQuestDone(user)) {
            user.completeQuest(questId);
        } else {
            user.takeQuest(questId);
        }
        return quest;
    }
}
