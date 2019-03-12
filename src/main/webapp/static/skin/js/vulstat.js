

$(function(){
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

    vulStat.doAjax = function(data,successCallBack,errorCallBack){
        var _url = '/logweb/safemonitor/vulstat/getdata';
        
        $.ajax({
            url:_url,
            data:data,
            type:"post",
            dataType:"json",
            success:successCallBack,
            error:errorCallBack
        })
    }

    var getSearchType = function(){
        var _searchBtn = $('#btn_search');
        return $(_searchBtn).attr("data-type");
    }

    var initSearchTerm = function(){
        var _searchType = getSearchType();

        if(_searchType == 0){
            $($('.term')[1]).hide();
            $($('.term')[2]).hide();
        }else{
            $($('.term')[1]).show();
            $($('.term')[2]).show();
        }
    }

    $('#timeType').on('change',function(){
        var _timeType = $(this).val();
        if(_timeType == 5){
            $('#btn_search').attr({'data-type':1});
        }else{
            $('#btn_search').attr({'data-type':0});
        }
        initSearchTerm();
    });

    $('#btn_search').on('click',function(){
        var _searchType = getSearchType();
        var _startTime = new Date();
        var _endTime = new Date();
        var _timeType = $('#timeType').val();
        var _te = dateUtility.Format('yyyy-MM-dd',_endTime);
        var _ts = '';

        if(_searchType == 0 && _searchType != 5){
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
        }else{
            _ts = $('#txt_vultypeStartTime').val();
            _te = $('#txt_vultypeEndTime').val();
        }

        initData(_ts, _te);
    });

    var initData = function(_startTime, _endTime){

        var _data = {'startTime':_startTime,'endTime':_endTime};
        var successCallBack = function(data){
            var vulType = echarts.init(document.getElementById('orderByServiceType'));
            vulType.clear();
            if(data.vulType.series[0].data != null && data.vulType.series[0].data.length>0){
                var optionType = data.vulType;
                vulType.setOption(optionType);                
            }else{
                $('#orderByServiceType').text('暂无数据!');
            }

            var vulRisk = echarts.init(document.getElementById('vulStatByRisk'));
            vulRisk.clear();
            
            if(data.vulRisk.series[0].data != null && data.vulRisk.series[0].data.length > 0){
                var optionRisk = data.vulRisk;
                vulRisk.setOption(optionRisk);         
            }else{
                $('#vulStatByRisk').text('暂无数据!');
            }

            var vulCustType = echarts.init(document.getElementById('vulCustType'));
            vulCustType.clear();
            if(data.vulCustType.series[0].data != null && data.vulCustType.series[0].data.length >0){
                var optionCustType = data.vulCustType;
                vulCustType.setOption(optionCustType);                
            }else{
                $('#vulCustType').text('暂无数据!');
            }

            var userVulCountStat = echarts.init(document.getElementById('userVulCountStat'));
            userVulCountStat.clear();
            if(data.userVulCountStat.series[0].data != null && data.userVulCountStat.series[0].data.length > 0){
                var optionUserCountStat = data.userVulCountStat;
                userVulCountStat.setOption(optionUserCountStat);
            }else{
                $('#userVulCountStat').text('暂无数据');
            }

            var vulCountTrend = echarts.init(document.getElementById('vulCountTrend'));
            vulCountTrend.clear();
            if(data.vulCountTrend.series[0].data != null && data.vulCountTrend.series[0].data.length > 0){
                var optionCountTrend = data.vulCountTrend;
                vulCountTrend.setOption(optionCountTrend);
            }else{
                $('#vulCountTrend').text('暂无数据!');
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

        initSearchTerm();
        initData(_startTime, _endTime);
    })();
});
