var someFloatIsActive = false;
var activeFloat;
var id;
var painting;
/*var username = localStorage.getItem("token").split("@")[0];
var nav_msg = new Vue({
    el: '#nav__message',
    data: {
        username: username
    }
})*/

$(document).ready(
    function () {
        id = document.location.pathname.substring(1).split("/")[1];
        getPainting();


        var loginS = "login";
        var registerS = "register";


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

        $("#delete").click(function () {
            deletePainting();
        });

        $("#edit").click(function () {
            requestRegister();
        });

        $("#moderate").click(function () {
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
        localStorage.setItem("token", data.token)
    }

}

function requestRegister() {
    var username = $("#register__un").val();
    var email = $("#register__email").val();
    var password = $("#register__pw").val();

    if (!checkFields) {
        $("#register__warn").val("Вы заполнили не все обязательные поля");
        return;
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

    function checkFields() {
        if (username.length < 4) return false;
        if (password.length < 4) return false;
    }

}

function getPainting() {

    $.ajax({
        url: "/api/get_painting/",
        method: "GET",
        converters: jQuery.parseJSON,
        contentType: 'application/json',
        data: {
            id: id
        },
        success: get
    });

    function get(data, textStatus, jqXHR) {
        data = JSON.parse(data);
        painting = new Vue({
            el: '#painting',
            data: {
                painting: data
            }
        });
    }
}

function deletePainting() {

    var data = JSON.stringify({
        id: id
    });

    console.log(data);

    $.ajax({
        url: "/api/delete_painting",
        method: "POST",
        converters: jQuery.parseJSON,
        contentType: 'application/json',
        dataType: JSON,
        data: data,
        success: success
    });

    $("#login__un").val("");
    $("#login__pw").val("");

    function success(data, textStatus, jqXHR) {
        location=location.host;
    }

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
        converters: JSON.parse,
        contentType: 'application/json',
        dataType: JSON,
        data: data,
        success: login
    });

    $("#login__un").val("");
    $("#login__pw").val("");

    function login(data, textStatus, jqXHR) {
        console.log(data.token);
        localStorage.setItem("token", data.token)
    }

}
