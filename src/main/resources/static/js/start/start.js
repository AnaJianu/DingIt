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


var hostname = "http://localhost:8080";

var AJAX = {
    SEND_REQUEST_FOR_REGISTRATION: "/register",
    CONFIRM_REGISTRATION: "/confirmRegistration"
};

function sendRequestForRegistration() {
//TODO: validate inputs and check the registrationResponse
    $('#registerForm').preventDefault();
    $.ajax({
        type: "POST",
        url: AJAX.SEND_REQUEST_FOR_REGISTRATION,
        success:function (registrationResponse) {
            console.log('Success registration response: '+ registrationResponse);
            if(registrationResponse.success) {
                $('#postSubmitMessage').text(registrationResponse.message);
                $('#postSubmitMessage').show();
            } else {
                $('#postSubmitMessage').text(registrationResponse.message);
                $('#postSubmitMessage').show();
            }
        },
        failure: function (registrationResponse) {
            console.log('Error registration response: '+ registrationResponse);
            $('#postSubmitMessage').text("An error has occurred. Please retry later!");
            $('#postSubmitMessage').show();
        }
        })
}