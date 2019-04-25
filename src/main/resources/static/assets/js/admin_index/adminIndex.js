$(function () {

    //全局加载页面
    loadPage()
})

//加载index首页页面
function loadPage() {
    //加载流量分析
    loadStaticVisit();

}

function loadStaticVisit() {
    $.ajax({
        url:'../statistic/getStatisticVisitCount',
        type:'GET', //GET
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success :function (data) {
            if(data.code == 0){
                var data = data.data
                $("#chromeCount").html(data.Chrome)
                $("#firefoxCount").html(data.Firefox)
                $("#ieCount").html(eval(data.IE + data.Trident))
                $("#operaCount").html(data.Opera)
                $("#safariCount").html(data.Safari)
            }
        }
    })
}
