function setCookie() { //设置cookie
    var loginCode = $("#username").val(); 
    var pwd = $("#password").val(); 
    var checked = $("#remberpassword:checked"); 
    var date = new Date();
    var expiresDate = date.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000));

    if (checked && checked.length > 0) { 
        $.cookie("_sso_login_code", loginCode,{expires : expiresDate}); 
        $.cookie("_sso_pwd", $.base64.encode(pwd),{expires : expiresDate}); 
    } else {
        $.cookie("_sso_login_code", loginCode,{expires : expiresDate}); 
        $.cookie("_sso_pwd", null,{expires : expiresDate});
    }
}

function getCookie() { //获取cookie
    var loginCode = $.cookie("_sso_login_code"); 
    var pwd = $.cookie("_sso_pwd"); 
    if (pwd && pwd !== "null") { 
        $("[type='checkbox']").attr("checked", "true");
    }
    if (loginCode && loginCode !== "null") { 
        $("#username").val(loginCode);
    }else{
        $("#username").val('');
    }
    if (pwd && pwd !== "null") { 
        $("#password").val($.base64.decode(pwd));
    }else{
        $("#password").val('');
    }
}

$('form').submit(function(event) {
    var _userName = $('#username').val(),
        _password = $('#password').val();
    setCookie();
    if (!_userName || _userName.length < 1) {
        $("p.text-danger").text('用户账号不能为空！');
        $('#username').parent().addClass('has-error');
        return false;
    } else if (!_password || _password.length < 1) {
        $('p.text-danger').text('密码不能为空！');
        $('#password').parent().addClass('has-error');
        return false;
    }
});

$(function() {
    getCookie();
})

