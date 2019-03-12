$(function() {

    var $leftSideNavHead = $('.left-side').find('.side-head');
    var $layout = $('.layout');

    //    (function(obj){
    //
    //    })($leftSideNavHead)

    (function(obj) {
        $($leftSideNavHead).removeClass('active');

        $(obj).each(function() {
            var _menuBox = $(this).next('ul');

            if (_menuBox.length == 0) {
                $(this).find('.glyphicon-chevron-down').removeClass('glyphicon glyphicon-chevron-down');
            }
        });
    })($leftSideNavHead);

    var init = (function() {
        var _windowHeight = $(document).height();
        $('.left-side').css({
            'min-height': _windowHeight - 53 + 'px'
        });

    })();

    (function(obj) {
        obj.on('click', function() {
            var _childMenu = $(this).next('ul');
            if (_childMenu && _childMenu.length > 0) {
                var _menuBox = _childMenu[0];


                if ($(_menuBox).is(':visible')) {
                    $(this).removeClass('active');
                    var _btnDown = $(this).find('.glyphicon-chevron-up');
                    if (_btnDown && _btnDown.length > 0) {
                        _btnDown.removeClass('glyphicon-chevron-up');
                        _btnDown.addClass('glyphicon-chevron-down');
                    }
                    $(_menuBox).slideUp(300);
                } else {
                    $(this).addClass('active');
                    var _btnUp = $(this).find('.glyphicon-chevron-down');

                    if (_btnUp && _btnUp.length > 0) {
                        _btnUp.removeClass('glyphicon-chevron-down');
                        _btnUp.addClass('glyphicon-chevron-up');
                    }
                    $(_menuBox).slideDown(300);
                }
            }
        });
    })($leftSideNavHead);


    (function(obj) {
        if (obj && obj.length > 0) {
            _size = $(obj).find('[data-ctr="layout-size"]')
            _close = $(obj).find('[data-ctr="layout-close"]')

            _size.on('click', function() {
                var _body = $(this).parents('.layout').find('.body');
                if ($(_body).is(':visible')) {
                    $(this).children('i').removeClass('fa-chevron-up');
                    $(this).children('i').addClass(' fa-chevron-down')
                    _body.slideUp(200);
                } else {
                    $(this).children('i').removeClass('fa-chevron-down');
                    $(this).children('i').addClass(' fa-chevron-up')
                    _body.slideDown(200);
                }

            });
            _close.on('click', function() {
                $(this).parents('.layout').fadeOut(200);
            });
        }
    })($layout)
    $('#btn_about').on('click', function() {
        showAboutBox();
    });

    var showAboutBox = function() {
        var _webPath = $('#webpath').val();

        var _modalStr = '<div class="modal fade" id="modal-about">';
        _modalStr += '      <div class="modal-dialog">';
        _modalStr += '          <div class="modal-content" style="border-radius:6px;">';
        _modalStr += '              <div class="modal-header" style="border-top-left-radius:5px;border-top-right-radius:5px;">';
        _modalStr += '                  <a class="btn-close" href="javascript:void(0)" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></a>';
        _modalStr += '                  <h4 class="modal-title">关于</h4>';
        _modalStr += '              </div>';
        _modalStr += '              <div class="modal-body">';
        _modalStr += '                  <div style="margin-top:30px;"><img src="' + _webPath + '/static/skin/images/system-name.png" /></div>'
        _modalStr += '                  <div style="background-color: #dfdfdf;border:1px solid #ccc; padding: 10px; margin-top:20px;">产品版本号:1.0</div>';
        _modalStr += '                  <div style="background-color: #dfdfdf; border:1px solid #ccc; padding: 5px 10px;; margin-top:15px; margin-bottom:30px">';
        _modalStr += '                  <table cellpadding="0" cellspacing="0" border="0">';
        _modalStr += '                  <tr>';
        _modalStr += '                  <td width="110" height="35">技术支持邮箱:</td>';
        _modalStr += '                  <td><a href="mailto:service@secisland.com" style="color:#65b900">service@secisland.com</a></td>';
        _modalStr += '                  </tr>';
        _modalStr += '                  <tr>';
        _modalStr += '                  <td height="35">QQ群支持:</td>';
        _modalStr += '                  <td>317896151</td>';
        _modalStr += '                  </tr>';
        _modalStr += '                  <tr>';
        _modalStr += '                  <td height="35">微博账号:</td>';
        _modalStr += '                  <td><a href="http://www.weibo.com/secislands" target="_blank" style="color:#65b900">secislands</a></td>';
        _modalStr += '                  </tr>';
        _modalStr += '                  <tr>';
        _modalStr += '                  <td height="35">微信账号:</td>';
        _modalStr += '                  <td>secisland</td>';
        _modalStr += '                  </tr>';
        _modalStr += '                  <tr>';
        _modalStr += '                  <td height="35">公司网址:</td>';
        _modalStr += '                  <td><a href="http://www.secisland.com" target="_blank" style="color:#65b900">http://www.secisland.com</a></td>';
        _modalStr += '                  </tr>';
        _modalStr += '                  </table>';
        _modalStr += '                  </div>';
        _modalStr += '              </div>';
        _modalStr += '              <div class="modal-footer">';
        _modalStr += '                  <p style="margin-bottom:0px; text-align: center;">Copyright ©2013 南京赛克蓝德网络科技有限公司</p>'
        _modalStr += '              </div>';
        _modalStr += '          </div>';
        _modalStr += '      </div>';
        _modalStr += '</div>';
        var _cheight = $(window).height();
        var _modalAbout = $('#modal-about');
        if (_modalAbout.length == 0) {
            var _dlg = $(_modalStr);
            _dlg.appendTo('body');
        }
        var _height = $('#modal-about>.modal-dialog').height();
        console.log(_cheight + ',' + _height);
        $('#modal-about').modal('show');
    };
})