/**
 * Created by ana on 10/06/2017.
 */

$(document).ready(function () {
    $('#registerForm').submit(function (e) {
        e.preventDefault();
        sendRequestForRegistration();
    });
    $('#loginForm').submit(function (e) {
        e.preventDefault();
        performSignIn();
    })
});

var hostname = "http://www.q-stion.com:8080";

var AJAX = {
    SEND_REQUEST_FOR_REGISTRATION: "/register",
    SEND_REQUEST_FOR_LOGIN: "/login"
};

function performSignIn() {
     var usernameSignInValue = $('#inputUsernameForSignIn').val();
     var passwordSignInValue = $('#inputPasswordForSignIn').val();

     if(usernameSignInValue.length != 0 && passwordSignInValue.length != 0) {
         $.ajax({
             type: "POST",
             url: AJAX.SEND_REQUEST_FOR_LOGIN,
             data: jQuery.param({username: usernameSignInValue,
                 password: passwordSignInValue
             }),

             success:function (loginResponse) {
                 if(loginResponse.success) {

                     window.location.href= hostname + "/home";
                 } else {
                     $('#signInErrorMessage').show();
                 }
             }


         })
     }
}

function performSignUp() {
    $('#signInForm').hide();
    $('#signUpForm').show();
}

function sendRequestForRegistration() {
//TODO: validate inputs

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