/**
 * Created by ana on 13/08/2017.
 */

var AJAX = {
    SEND_REQUEST_FOR_YOUR_QUESTIONS: "/home/my/questions",
    SEND_REQUEST_FOR_YOUR_ANSWERS: "/home/my/advices"
};

function seeYourQuestions() {
    $.ajax({
        type: "GET",
        url: AJAX.SEND_REQUEST_FOR_YOUR_QUESTIONS,
        success: function () {
            $('#userPortfolio').hide();
            $('#userQuestions').show();
        }
    })
}


function seeYourAnswers() {
    $.ajax({
        type: "GET",
        url: AJAX.SEND_REQUEST_FOR_YOUR_ANSWERS

    })
}