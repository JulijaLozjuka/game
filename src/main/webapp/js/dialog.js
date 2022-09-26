$(document).ready(function() {
    $('#dialogueStarter').click(function() {
        $.ajax({
            url : 'dialogue',
            data : {
                messageId : $('#dialogStarterValue').text()
            },
            success : function(messageDto) {
                $('#dialogue-text').text(messageDto.text.text);
                $('#answer-button1').text(messageDto.answer1.text);
                $('#answer-button2').text(messageDto.answer2.text);
            }
        });
    });
});