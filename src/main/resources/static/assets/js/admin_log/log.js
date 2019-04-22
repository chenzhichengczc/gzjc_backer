$(function () {
    console.log("fds");
    var websocket = new WebSocket('ws://localhost:80/gzjc_backer/getLog');
    websocket.onmessage = function(event) {
        // 接收服务端的实时日志并添加到HTML页面中
        console.log(event.data);
        $(".am-pre-scrollable").append(event.data);
        // 滚动条滚动到最低部
    };
})

