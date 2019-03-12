$(function(){
    var dealResult = function(){
        if(window._result != null){
            if(_result.code == 0){
                $('#modal').find('.modal-content').html('<div id="message" class="alert alert-danger" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>' + _result.message +'</div>');
                $('#modal').modal('toggle');
                setTimeout(function(){$('#modal').modal('hide')},1000)
            }else{
                $('#modal').find('.modal-content').html('<div id="message" class="alert alert-success" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>' + _result.message +'</div>');
                $('#modal').modal('toggle');
                setTimeout(function(){
                    $('#modal').modal('hide');
                    var _href = 'http://' + window.location.host;
                    var _url = $('form').attr('action');
                    if(_url != null && _url.length > 0){
                        var i = _url.indexOf('/account/user/dochangepwd');
                        if(i > 0){
                            _href += _url.substr(0, i);
                            console.log(_href);
                            document.location.href = _href;
                        }
                    }
                },1000)
            }
        }
    }
    dealResult();
    var checkPassword = function(newPassword, confirmPassWord){
        var _newPassword = $('#newPassword').val(),
            _confirmPassWord = $('#confirmPassWord').val();

        if(_newPassword != '' && _confirmPassWord != '' && _newPassword == _confirmPassWord){
            return true;
        }else{
            $('#modal').find('.modal-content').html('<div id="message" class="alert alert-danger" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>两次输入密码不同！</div>');
            $('#modal').modal('toggle');
            setTimeout(function(){$('#modal').modal('hide')},1000)
            return false;
        }
    }
    $('form').submit(function(event) {
        return checkPassword();
    });
});