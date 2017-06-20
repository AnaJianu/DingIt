/**
 * Created by ana on 10/06/2017.
 */

$(document).ready(function () {
    $('#registerForm').submit(function (e) {
        e.preventDefault();
        sendRequestForRegistration();
    });
});

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

    var fullNameValue = $('#inputFullName').val();
    var usernameValue = $('#inputUsername').val();
    var passwordValue = $('#inputPassword').val();
    var emailValue = $('#inputEmail').val();
    var cityValue = $('#inputCity').val();
    var countryValue = $('#inputCountry').val();

    $.ajax({
        type: "POST",
        url: AJAX.SEND_REQUEST_FOR_REGISTRATION,
        data: jQuery.param({fullName: fullNameValue,
                            username: usernameValue,
                            password: passwordValue,
                            email: emailValue,
                            city: cityValue,
                            country: countryValue
        }),
        success:function (registrationResponse) {
            console.log('Success registration response: '+ registrationResponse);
            if(registrationResponse.success) {
                $('#registerForm').hide();
                $('#postSubmitMessage').text(registrationResponse.message);
                $('#postSubmitMessage').show();
                $('#continueButton').show();
            } else {
                $('#postSubmitMessage').text(registrationResponse.message);
                $('#postSubmitMessage').show();
            }
        },
        error: function (registrationResponse) {
            console.log('Error registration response: '+ JSON.stringify(registrationResponse));
            $('#postSubmitMessage').text("An error has occurred. Please retry later!");
            $('#postSubmitMessage').show();
        }
        })
}