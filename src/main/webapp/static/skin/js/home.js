;$(function(){
    var webroot = "";
    if(typeof _webroot === 'underdefine'){
        webroot = window.location.host;
    }else{
        webroot = _webroot;
    }
    // 下面主要是时间处理的部分内容，还有部分功能需要添加和修改
    var dateUtility= {
        Version:'1.0',
        Name:'时间处理工具',
        Date:new Date(),
        Initialize:(function(){

        })(),
        IsLeapYear:function(date){return (0==date.getYear()%4&&((date.getYear()%100!=0)||(date.getYear()%400==0)));},
        Format:function() { 
            var _len = arguments.length;
            var _str = '';
            var _date;

            if(1 == _len){
                _str = arguments[0];
                _date = this.date;
            } else if(2 == _len){
                if(typeof arguments[0] === 'string'){
                    _str = arguments[0];
                    _date = arguments[1];
                }else if(typeof arguments[1] === 'object'){
                    _date = arguments[0];
                    _str = arguments[1];
                }
            }else{
                return '参数传入有误';
            }

            var Week = ['日','一','二','三','四','五','六']; 
            _str=_str.replace(/yyyy|YYYY/,_date.getFullYear()); 
            _str=_str.replace(/yy|YY/,(_date.getYear() % 100)>9?(_date.getYear() % 100).toString():'0' + (_date.getYear() % 100));
            
            _str=_str.replace(/MM/,(_date.getMonth() + 1)>9?(_date.getMonth() + 1).toString():'0' + (_date.getMonth() + 1));
            _str=_str.replace(/M/g,_date.getMonth() + 1); 
       
            _str=_str.replace(/w|W/g,Week[_date.getDay()]); 
       
            _str=_str.replace(/dd|DD/,_date.getDate()>9?_date.getDate().toString():'0' + _date.getDate());
            _str=_str.replace(/d|D/g,_date.getDate()); 
      
            _str=_str.replace(/hh|HH/,_date.getHours()>9?_date.getHours().toString():'0' + _date.getHours());
            _str=_str.replace(/h|H/g,_date.getHours()); 
            _str=_str.replace(/mm/,_date.getMinutes()>9?_date.getMinutes().toString():'0' + _date.getMinutes());
            _str=_str.replace(/m/g,_date.getMinutes()); 

            _str=_str.replace(/ss|SS/,_date.getSeconds()>9?_date.getSeconds().toString():'0' + _date.getSeconds());
            _str=_str.replace(/s|S/g,_date.getSeconds()); 
            
            return _str; 
        },
        DateCountOfMonth: function(date){
            var dateCount = [31,28,31,30,31,30,31,31,30,31,30,31],
                leapDateCount = [31,29,31,30,31,30,31,31,30,31,30,31];
            if(IsLeapYear(date)){
                return leapDateCount[date.getMonth];
            }else{
                return dateCount[date.getMonth];
            }
        }

    }

    window.dateUtility = dateUtility;

    var vulStat = {};

    // 进行异步数据的请求
    vulStat.doAjax = function(data,successCallBack,errorCallBack){
        var _url = "http://" + webroot + '/home/getdata';
        
        $.ajax({
            url:_url,
            data:data,
            type:"post",
            dataType:"json",
            success:successCallBack,
            error:errorCallBack
        })
    }
    
    $('#timeType').on('click',function(){
    	var _searchType = $('#timeType').val();;
        $('.term').hide();

		if(_searchType == 5){
		    $($('.term')[0]).show();
		    $($('.term')[1]).show();
		    $($('.term')[2]).show();
		}else{
			$($('.term')[0]).show();
		}
    }); 

    $('#btn_search').on('click',function(){
        var _startTime = new Date();
        var _endTime = new Date();
        var _timeType = $('#timeType').val();
        var _te = dateUtility.Format('yyyy-MM-dd',_endTime);
        var _ts = '';

        if(_timeType == 1){
            _startTime = new Date(_endTime.setDate(_endTime.getDate() - 7));
        }
        if(_timeType == 2){
            _startTime = new Date(_endTime.setMonth(_endTime.getMonth() - 1));
        }
        if(_timeType == 3){
           _startTime = new Date(_endTime.setMonth(_endTime.getMonth() - 3));
        }
        if(_timeType == 4){
            _startTime = new Date(_endTime.setFullYear(_endTime.getFullYear() - 1));
        }
        _ts = dateUtility.Format('yyyy-MM-dd', _startTime);
        if(_timeType == 5){
            _ts = $('#txt_vultypeStartTime').val();
            _te = $('#txt_vultypeEndTime').val();
        }

        initData(_ts, _te);
    });

    // 初始化时间
    var initData = function(_startTime, _endTime){

        var _data = {'startTime':_startTime,'endTime':_endTime};
        var successCallBack = function(data){
            var eventlogStat = echarts.init(document.getElementById('eventlogStat'));
            eventlogStat.clear();
            if(data.eventStat.series[0].data != null && data.eventStat.series[0].data.length>0){
                var optionEvent = data.eventStat;
                eventlogStat.setOption(optionEvent);                
            }else{
                $('#eventlogStat').text('暂无数据!');
            }

            var alertStat = echarts.init(document.getElementById('alertStat'));
            alertStat.clear();
            
            if(data.alertStat.series[0].data != null && data.alertStat.series[0].data.length > 0){
                var optionAlert = data.alertStat;
                alertStat.setOption(optionAlert);         
            }else{
                $('#alertStat').text('暂无数据!');
            }
        };
        var errorCallBack = function(){
            alert("请求数据接口失败！");
        }

        vulStat.doAjax(_data, successCallBack, errorCallBack);
    };

    (function(){
        var _d = new Date();
        var _endTime = dateUtility.Format('yyyy-MM-dd',_d);
        var _startTime = dateUtility.Format('yyyy-MM-dd',new Date(_d.setDate(_d.getDate() - 7)));

        $($('.term')[1]).hide();
		$($('.term')[2]).hide();
        initData(_startTime, _endTime);
    })();
    
    var getTopEvent = function(){
	    $.ajax({
	        type : "post",
	        async : true,
	        dataType : "json",
	        url : "http://" + webroot + "/report/event/topevent",
	        data : {ip:"123"},
	        success : function(data, textStatus) {
	            var _html = '';
	            if(data != null && data.length > 0){
	                _html += '<p class="text-left"><h4>最新日志</h4></p>';
	                _html += '<table cellpadding="0" cellspacing="0" width="100%">';
	                _html += '    <thead>';
	                _html += '        <tr>';
	                _html += '            <td width="20%">事件类型</td>';
	                _html += '            <td width="25%">采集时间</td>';
	                _html += '            <td width="55%">事件内容</td>';
	                _html += '        </tr>';
	                _html += '    </thead>';
	                _html += '    <tbody>';
	                for(var i = 0;i < data.length; i++){
	                    var _eventType = data[i].eventType,
	                        _collectDate = data[i].collectDate;
	                        _eventDesc = data[i].eventDesc;
	                    if(_eventDesc.length > 40){
	                    	_eventDesc = _eventDesc.substr(0,40) + '…';
	
	                    }
	                    _html += '        <tr>';
	                    _html += '            <td width="20%" height="30" title="' + data[i].userCode + '">' + _eventType + '</td>';
	                    _html += '            <td width="25%" title="' + data[i].userName + '">' + _collectDate + '</td>';
	                    _html += '            <td width="55%" title="' + data[i].eventDesc + '">' + _eventDesc + '</td>';
	                    _html += '        </tr>';
	                }
	                _html += '    </tbody>';
	                _html += '</table>';
	            }
	            $('#topEvent').html(_html);
	        },
	        error : function(data) {
	            $('#topEvent').html("服务器数据异常！");
	        }
	    });
    }
    var getTopAlert = function(){
        $.ajax({
            type : "post",
            async : true,
            dataType : "json",
            url : "http://" + webroot + "/report/alert/topalert",
            data : {ip:"123"},
            success:function(data, textStatus){
                var _html = '';
                if(data != null && data.length > 0){
                    _html += '<p class="text-left"><h4>最新告警</h4></p>';
                    _html += '<table cellpadding="0" cellspacing="0" width="100%">';
                    _html += '    <thead>';
                    _html += '        <tr>';
                    _html += '            <td width="25%">告警名称</td>';
                    _html += '            <td width="25%">告警时间</td>';
                    _html += '            <td width="50%">告警内容</td>';
                    _html += '        </tr>';
                    _html += '    </thead>';
                    _html += '    <tbody>';
                    for(var i = 0; i < data.length; i++){
                        var _ruleName = data[i].ruleName,
                            _alertDate = data[i].alertDate,
                            _displayTitle = data[i].displayTitle;
                        if(_displayTitle.length > 40){
                        	_displayTitle = _displayTitle.substr(0,40) + '…';
                        }
                        _html += '        <tr>';
                        _html += '            <td width="25%" title="' + data[i].ruleName + '">' + _ruleName + '</td>';
                        _html += '            <td width="25%" title="' + data[i].alertDate + '">' + _alertDate + '</td>';
                        _html += '            <td width="50%" title="' + data[i].displayTitle + '">' + _displayTitle + '</td>';
                        _html += '        </tr>';
                    }
                    _html += '    </tbody>';
                    _html += '</table>';
                }
                $('#topAlert').html(_html);
            },
            error:function(){
                
            }
        });
    }
    getTopAlert();
    getTopEvent();
    setInterval(getTopAlert,60000);
    setInterval(getTopEvent,60000);
})