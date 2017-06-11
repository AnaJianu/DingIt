/**
 * Created by ana on 10/06/2017.
 */


//do we need the .ready(function() {myscript}) or $(document).ready() functions?

function performSignIn() {

}


function performSignUp() {
  $('#signInForm').hide();
  $('#signUpForm').show();
}

var AJAX = {
    SEND_REQUEST_FOR_REGISTRATION: '<spring:url value = "/register" />',
    CONFIRM_REGISTRATION: '<spring:url value = "/confirmRegistration" />'
};

function sendRequestForRegistration() {
//TODO: validate inputs and check the registrationResponse

    $.ajax({
        type: "POST",
        url: AJAX.SEND_REQUEST_FOR_REGISTRATION,
        success:function (registrationResponse) {
            if(registrationResponse.success) {
                $('#postSubmitMessage').text(registrationResponse.message);
                $('postSubmitMessage').show();
            } else {
                $('#postSubmitMessage').text(registrationResponse.message);
                $('postSubmitMessage').show();
            }
        },
        failure: function (registrationResponse) {
            $('#postSubmitMessage').text("An error has occurred. Please retry later!");
            $('postSubmitMessage').show();
        }
        })
}