$(function () {

    infoLog();

    $(".am-error-lg").on('click',function () {
        $(".am-pre-scrollable").children().remove();
        var websocketError = new WebSocket("ws://localhost:80/gzjc_backer/getErrorLog");
        websocketError.onmessage = function (event) {
            $(".am-pre-scrollable").append("<div><p>"+event.data+"</p></div>");
        }
    })

    $(".am-text-lg").on('click',function () {
        $(".am-pre-scrollable").children().remove();
        infoLog();
    })

})

function infoLog() {
    var websocket = new WebSocket('ws://localhost:80/gzjc_backer/getInfoLog');
    websocket.onmessage = function(event) {
        // 接收服务端的实时日志并添加到HTML页面中
        $(".am-pre-scrollable").append("<div><p>"+event.data+"</p></div>");
        // 滚动条滚动到最低部
        //$(".am-pre-scrollable").scrollTop = $(".am-pre-scrollable").scrollHeight;
    };
    websocket.onclose = function (ev) {
        console.log("close");
    }
}