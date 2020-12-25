var someFloatIsActive = false;
var activeFloat;

$(document).ready(
    function () {
        let loginS = "login";
        let registerS = "register";

        $("#nav__login").click(function () {
            if (!floatIsActive(loginS))
                openFloat(loginS);
            else
                closeFloat(loginS);
        });
        $("#nav__register").click(function () {
            if (!floatIsActive(registerS))
                openFloat(registerS);
            else
                closeFloat(registerS);
        });
        $("#login__submit").click(function () {
            requestLogin();
        });
        $("#register__submit").click(function () {
            requestRegister();
        });



    });

function closeActiveFloat() {
    closeFloat(activeFloat);
}

function openFloat(id) {
    if (someFloatIsActive)
        closeActiveFloat();
    someFloatIsActive = true;
    activeFloat = id;
    $("#" + id).addClass("active");
    //console.log("added active to #" + id)
}

function closeFloat(id) {
    someFloatIsActive = false;
    activeFloat = null;
    $("#" + id).removeClass("active");
    //console.log("removed active from #" + id)
}

function floatIsActive(id) {
    //console.log("checked for #" + id)
    return $("#" + id).hasClass("active");
}

function requestLogin() {
    var username = $("#login__un").val();
    var password = $("#login__pw").val();
    
    console.log(username);
    console.log(password);
     
    var data = JSON.stringify({
            login: username,
            passHash: password
        });
    
    console.log(data);
    
    $.ajax({
        url: "/api/sign_in",
        method: "POST",
        converters: jQuery.parseJSON,
        contentType: 'application/json',
        dataType: JSON,
        data: data,
        success: login
    });

    $("#login__un").val("");
    $("#login__pw").val("");

    function login(data, textStatus, jqXHR) {
        console.log(data.token);
    }

}

function requestRegister() {
    var username = $("#register__un").val();
    var email = $("#register__email").val();
    var password = $("#register__pw").val();
    
    if (!checkFields) {
        $("#register__warn").val("Вы заполнили не все обязательные поля");
        
    }
    
    console.log(username);
    console.log(email);
    console.log(password);
    
    var data = JSON.stringify({
            login: username,
            password: password,
            email: email
        });
    
    console.log(data);
    
    $.ajax({
        url: "/api/sign_up",
        method: "POST",
        converters: jQuery.parseJSON,
        contentType: 'application/json',
        dataType: JSON,
        data: data,
        success: register
    });

    $("#register__un").val("");
    $("#register__email").val("");
    $("#register__pw").val("");

    function register(data, textStatus, jqXHR) {
        console.log(data.message);
        $("#float__message").val(data.message);
    }
    
    function checkFields(){
        if (username.length < 4) return false;
        if (password.length < 4) return false;
    }

}