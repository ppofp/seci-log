$(function(){
    // 进行异步数据的请求
    var doAjax = function(data,successCallBack,errorCallBack){
        var _url = '/logweb/assetstat/getdata';
        
        $.ajax({
            url:_url,
            data:data,
            type:"post",
            dataType:"json",
            success:successCallBack,
            error:errorCallBack
        });
    };

    (function(){
        var ip = "ip=123";
        var successCallBack = function(data){
            if(data != null){
                var optionOrderByData = data.orderByDate || null;
                var optionOrderType  = data.orderType || null;
                var optionCustomProperty = data.customProperty || null;
                var optionCustomPropertyCount = data.customPropertyCount || null;
                var optionPropertyCountByCustomType = data.propertyCountByCustomType || null

                if(optionOrderByData != null && optionOrderByData.xAxis[0].data.length > 0){
                    var orderByDate = echarts.init(document.getElementById('orderByDate'));
                    orderByDate.clear();
                    orderByDate.setOption(optionOrderByData);
                }else{
                    $('#orderByDate').text('暂无数据!');
                }

                if(optionOrderType != null && optionOrderType.series[0].data.length > 0){
                    var orderType = echarts.init(document.getElementById('orderType'));
                    orderType.clear();
                    orderType.setOption(optionOrderType);
                }else{
                    $('#orderType').text('暂无数据!');
                }

                if(optionCustomProperty != null && optionCustomProperty.series[0].data.length > 0){
                    var customProperty = echarts.init(document.getElementById('customProperty'));
                    customProperty.clear();
                    customProperty.setOption(optionCustomProperty);
                }else{
                    $('#customProperty').text('暂无数据!');
                }

                if(optionCustomPropertyCount != null && optionCustomPropertyCount.xAxis[0].data.length){
                    var customPropertyCount = echarts.init(document.getElementById('customPropertyCount'));
                    customPropertyCount.clear();
                    customPropertyCount.setOption(optionCustomPropertyCount);
                }else{
                    $('#customPropertyCount').text('暂无数据!');
                }

                if(optionPropertyCountByCustomType != null && optionPropertyCountByCustomType.series[0].data.length){
                    var propertyCountByCustomType = echarts.init(document.getElementById('propertyCountByCustomType'));
                    propertyCountByCustomType.clear();
                    propertyCountByCustomType.setOption(optionPropertyCountByCustomType);
                }else{
                    $('#propertyCountByCustomType').text('暂无数据!');
                }
            }
        };
        var errorCallBack = function(){
            alert(0);
        } 
        doAjax(ip,successCallBack, errorCallBack);
    })();

})