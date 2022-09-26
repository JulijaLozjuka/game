function setDialogueAnswerActions(answer, answerButton, nextMessageValue) {
    if (answer == null) return;
    if (answer.questId != null) {
        $('#quest-id-value').text(answer.questId);
        $('#quest-accept-button').text(answer.text);
        $('#quest-accept-button').show();
    } else if (answer.nextMessage == null) {
        $('#close-dialog-button').text(answer.text);
        $('#close-dialog-button').show();
    } else {
        answerButton.text(answer.text);
        nextMessageValue.text(answer.nextMessage);
        answerButton.show();
    }
}

function displayDialogueResp(messageDto) {
    $('#close-dialog-button').hide();
    $('#quest-accept-button').hide();
    $('#answer-button1').hide();
    $('#answer-button2').hide();

    $('#dialogue-text').text(messageDto.text);
    setDialogueAnswerActions(messageDto.answer1, $('#answer-button1'), $('#answer-message-id1'));
    setDialogueAnswerActions(messageDto.answer2, $('#answer-button2'), $('#answer-message-id2'));
}

function addDialogueEventOnClick(button, value) {
    button.click(function () {
        $.ajax({
            url: 'dialogue',
            data: {
                messageId: value.text(),
                npcId: $('#npcId').text()
            },
            success: function (messageDto) {
                displayDialogueResp(messageDto);
            }
        });
    });
}

$(document).ready(function () {
    addDialogueEventOnClick($('#dialogueStarter'), $('#dialogStarterValue'));
    addDialogueEventOnClick($('#answer-button1'), $('#answer-message-id1'));
    addDialogueEventOnClick($('#answer-button2'), $('#answer-message-id2'));

    $('#quest-accept-button').click(function () {
        $.ajax({
                type: 'POST',
                url: 'quest',
                data: {
                    questId: $('#quest-id-value').text()
                },
                success: function (questDto) {
                    let $userQuestList = $('#user-quests');
                    let allListElements = $userQuestList.find('li');
                    for (let i = 0; i < allListElements.length; i++) {
                        let attr = allListElements[i].id;
                        if (attr == questDto.questId) {
                            allListElements[i].remove();
                            break;
                        }
                    }
                    $userQuestList.append(
                        '<li class="list-group-item">'
                        + (questDto.description + ' ' + (questDto.done ? "(Done)" : "(In progress)")) +
                        '</li>')
                }
            }
        );
    });
    $("#exampleModal").on("hidden.bs.modal", function () {
        // put your default event here
    });
});