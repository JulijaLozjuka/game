package dev.julialoz.game.javarushgame.service;

import dev.julialoz.game.javarushgame.business.repository.AnswerRepository;
import dev.julialoz.game.javarushgame.business.repository.MessageRepository;
import dev.julialoz.game.javarushgame.business.repository.NpcRepository;
import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.item.Item;
import dev.julialoz.game.javarushgame.model.npc.Answer;
import dev.julialoz.game.javarushgame.model.npc.Message;
import dev.julialoz.game.javarushgame.model.npc.Npc;
import dev.julialoz.game.javarushgame.model.quest.CollectingQuest;
import dev.julialoz.game.javarushgame.model.quest.Quest;
import dev.julialoz.game.javarushgame.servlet.dto.MessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DialogueServiceTest {

    Answer answer1;

    Answer answer2;

    Npc npc;
    Message message;

    Quest quest;

    User user;

    Item item;


    DialogueService dialogueService;

    MessageRepository messageRepository;
    AnswerRepository answerRepository;
    NpcRepository npcRepository;
    QuestRepository questRepository;

    @BeforeEach
    void setUp() {
        long npcId = 1L;
        long messageId = 1L;
        long answer1Id = 1L;
        long answer2Id = 2L;
        long questId = 1L;
        long itemId = 1L;
        message = Message.builder()
                .id(messageId)
                .answer1(answer1Id)
                .answer2(answer2Id)
                .build();
        String answer1Text = "Hi";
        String answer2Text = "NO";
        answer1 = Answer.builder()
                .id(answer1Id)
                .text(answer1Text)
                .build();
        answer2 = Answer.builder()
                .id(answer2Id)
                .text(answer2Text)
                .build();
        npc = Npc.builder()
                .id(npcId)
                .questId(questId)
                .build();
        user = User.builder().build();
        item = Item.builder()
                .id(itemId)
                .build();
        quest = CollectingQuest.builder()
                .id(questId)
                .requiredItem(item.getId())
                .build();

        setUpRepositories();
        dialogueService = new DialogueService(questRepository, messageRepository, answerRepository, npcRepository);
    }

    @Test
    void getMessageDtoIfQuestIsComplete() {

        String questCompleteText = "Complete";

        user.takeQuest(quest.getId());
        user.completeQuest(quest.getId());
        npc.setQuestCompleteText(questCompleteText);

        MessageDto result = dialogueService.getMessageDto(
                String.valueOf(message.getId()),
                user,
                String.valueOf(npc.getId()));

        assertEquals(DialogueService.QUEST_IS_COMPLETE_ANSWER_TEXT, result.getAnswer1().getText());
        assertEquals(npc.getQuestCompleteText(), result.getText());
        assertEquals(null, result.getAnswer2());
    }

    @Test
    void getMessageDtoIfQuestIsDone() {

        String questIsDoneText = "Done";

        user.takeQuest(quest.getId());
        user.pickUpItem(item.getId());
        npc.setQuestDoneText(questIsDoneText);

        MessageDto result = dialogueService.getMessageDto(
                String.valueOf(message.getId()),
                user,
                String.valueOf(npc.getId()));

        assertEquals(DialogueService.QUEST_IS_DONE_ANSWER_TEXT, result.getAnswer1().getText());
        assertEquals(npc.getQuestDoneText(), result.getText());
        assertEquals(null, result.getAnswer2());
    }

    @Test
    void getMessageDtoIfQuestIsInProgress() {

        String questInProgressText = "In progress";

        user.takeQuest(quest.getId());
        npc.setQuestInProgressText(questInProgressText);

        MessageDto result = dialogueService.getMessageDto(
                String.valueOf(message.getId()),
                user,
                String.valueOf(npc.getId()));

        assertEquals(DialogueService.QUEST_IN_PROGRESS_ANSWER_TEXT, result.getAnswer1().getText());
        assertEquals(npc.getQuestInProgressText(), result.getText());
        assertEquals(null, result.getAnswer2());
    }

    @Test
    void getMessageDtoWithAnswers() {

        MessageDto result = dialogueService.getMessageDto(
                String.valueOf(message.getId()),
                user,
                String.valueOf(npc.getId()));

        assertEquals(answer1.getText(), result.getAnswer1().getText());
        assertEquals(answer2.getText(), result.getAnswer2().getText());

    }

    private void setUpRepositories() {
        messageRepository = getMessageRepository(message);
        answerRepository = getAnswerRepository(answer1, answer2);
        npcRepository = getNpcRepository(npc);
        questRepository = getQuestRepository(quest);
    }

    private QuestRepository getQuestRepository(Quest quest) {
        QuestRepository questRepository = mock(QuestRepository.class);
        when(questRepository.findById(quest.getId())).thenReturn(Optional.of(quest));
        return questRepository;
    }

    private NpcRepository getNpcRepository(Npc npc) {
        NpcRepository npcRepository = mock(NpcRepository.class);
        when(npcRepository.findById(npc.getId())).thenReturn(Optional.of(npc));
        return npcRepository;
    }

    private AnswerRepository getAnswerRepository(Answer answer1, Answer answer2) {
        AnswerRepository answerRepository = mock(AnswerRepository.class);
        when(answerRepository.findById(answer1.getId())).thenReturn(Optional.of(answer1));
        when(answerRepository.findById(answer2.getId())).thenReturn(Optional.of(answer2));
        return answerRepository;
    }

    private MessageRepository getMessageRepository(Message message) {
        MessageRepository messageRepository = mock(MessageRepository.class);
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));
        return messageRepository;
    }
}