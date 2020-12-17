function requestLogin() {
    const username = $("#un-si").val();
    const password = $("#pw-si").val();

    console.log(username);
    console.log(password);

    $.ajax({
        url: "/api/sign_in",
        method: "GET",
        converters: jQuery.parseJSON,
        dataType: "json",
        data: JSON.stringify({
            login: username,
            passHash: password,
        }),
        success: login
    });

    $("#un-si").text("");
    $("#pw-si").text("");

    function login(data, textStatus, jqXHR) {
        console.log(data.token);
    }

}
