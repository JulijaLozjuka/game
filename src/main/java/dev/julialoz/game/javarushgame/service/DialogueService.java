package dev.julialoz.game.javarushgame.service;

import dev.julialoz.game.javarushgame.business.repository.AnswerRepository;
import dev.julialoz.game.javarushgame.business.repository.MessageRepository;
import dev.julialoz.game.javarushgame.business.repository.NpcRepository;
import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.npc.Answer;
import dev.julialoz.game.javarushgame.model.npc.Message;
import dev.julialoz.game.javarushgame.model.npc.Npc;
import dev.julialoz.game.javarushgame.model.quest.Quest;
import dev.julialoz.game.javarushgame.servlet.dto.AnswerDto;
import dev.julialoz.game.javarushgame.servlet.dto.MessageDto;
import dev.julialoz.game.javarushgame.servlet.dto.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class DialogueService {

    protected static final Logger log = LogManager.getLogger();

    public static final String QUEST_IN_PROGRESS_ANSWER_TEXT = "Iļl get back to it.";
    public static final String QUEST_IS_DONE_ANSWER_TEXT = "Yes that was easy.";
    public static final String QUEST_IS_COMPLETE_ANSWER_TEXT = "See you later.";

    private final QuestRepository questRepository;
    private final MessageRepository messageRepository;
    private final AnswerRepository answerRepository;
    private final NpcRepository npcRepository;

    public MessageDto getMessageDto(String messageId, User user, String npcId) {
        MessageDto messageDto;
        Long npcIdLong = Long.valueOf(npcId);
        Npc npc = npcRepository.findById(npcIdLong)
                .orElseThrow(getRuntimeExceptionSupplier("No npc found with id: ", npcIdLong));
        if (user.isQuestTaken(npc.getQuestId())) {
            Quest quest = questRepository.findById(npc.getQuestId())
                    .orElseThrow(getRuntimeExceptionSupplier("No quest found with id: ", npc.getQuestId()));
            if (quest.isQuestDone(user)) {
                messageDto = MessageDto.builder()
                        .text(npc.getQuestDoneText())
                        .answer1(AnswerDto.builder()
                                .text(QUEST_IS_DONE_ANSWER_TEXT)
                                .questId(npc.getQuestId())
                                .build())
                        .build();
            } else {
                messageDto = buildMessageDto(npc.getQuestInProgressText(), QUEST_IN_PROGRESS_ANSWER_TEXT);
            }
        } else if (user.isQuestComplete(npc.getQuestId())) {
            messageDto = buildMessageDto(npc.getQuestCompleteText(), QUEST_IS_COMPLETE_ANSWER_TEXT);
        } else {
            long messageIdLong = Long.parseLong(messageId);
            Message message = messageRepository.findById(messageIdLong)
                    .orElseThrow(getRuntimeExceptionSupplier("No message found with id: ", messageIdLong));
            Answer answer1 = message.getAnswer1() != null
                    ? answerRepository.findById(message.getAnswer1())
                    .orElseThrow(getRuntimeExceptionSupplier("No answer found with id: ", message.getAnswer1()))
                    : null;
            Answer answer2 = message.getAnswer2() != null
                    ? answerRepository.findById(message.getAnswer2())
                    .orElseThrow(getRuntimeExceptionSupplier("No answer found with id: ", message.getAnswer2()))
                    : null;
            messageDto = MessageMapper.mapToMessageDto(message, answer1, answer2);
        }
        return messageDto;
    }

    private static Supplier<RuntimeException> getRuntimeExceptionSupplier(String errorText, Long id) {
        return () -> {
            String errorMessage = errorText + id;
            log.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        };
    }

    private static MessageDto buildMessageDto(String text, String answerText) {
        return MessageDto.builder()
                .text(text)
                .answer1(AnswerDto.builder().text(answerText).build())
                .build();
    }

}
