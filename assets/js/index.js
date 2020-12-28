var someFloatIsActive = false;
var activeFloat;
var paintings;
var feed;

$(document).ready(
    function () {

        getPaintings();


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
        $("#nav__print").click(function () {
            callPrint("paintings__feed");
        });





    });

function callPrint(strid) {
    var prtContent = document.getElementById(strid).innerHTML;
    var prtCSS = '<style>.paintings__feed{max-width:800px;margin:0 auto}.preview{margin:10px;min-width:560px;height:auto;display:flex;justify-content:space-between;padding:10px;border-style:solid;border-radius:10px;border-width:3px}.preview__desc{max-width:400px;min-width:300px;display:flex;margin:10px;justify-content:space-between;flex-direction:column}.preview__picname{font-weight:700;font-size:20px}.preview__picdesc{word-break:break-all}.preview__link{text-decoration:none;width:auto;max-width:380px;padding:5px;margin:0 10px;border:3px solid #540000;border-radius:10px;box-shadow:#54000044 0px 0px 20px;transition:all .2s;text-align:center}.preview__link:hover{background:#54000077}</style>';
    var strOldOne = prtContent;
    var HTML = '<div id="print" class="contentpane">' + prtCSS + prtContent + '</div>';
    var WinPrint = window.open('', '', 'left=50,top=50,width=800,height=640,toolbar=0,scrollbars=1,status=0');
    console.log(HTML);
    WinPrint.document.write(HTML);
    WinPrint.document.close();
    console.log(WinPrint.document);
    WinPrint.focus();
    WinPrint.print();
    WinPrint.close();
}

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

function getPaintings() {

    $.ajax({
        url: "/api/get_paintings",
        method: "GET",
        converters: jQuery.parseJSON,
        contentType: 'application/json',
        success: get
    });

    function get(data, textStatus, jqXHR) {
        data = JSON.parse(data);
        paintings = data;
        feed = new Vue({
            el: '#paintings__feed',
            data: {
                paintings: paintings
            }
        });
    }
}
