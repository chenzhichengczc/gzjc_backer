$(function () {

    //全局加载页面
    loadPage()

})

//加载index首页页面
function loadPage() {
    //任务弹框
    loadTask();
    //加载流量分析
    loadStaticVisit();
    //添加任务
    loadNewTask();
    //加载任务信息
    loadFindTask();

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

function loadTask(){
    $("#sjw-bt-task").click(function () {
        $("#sjw-rw-1").show(1000);
        $(".sjw-bt-1").val("");
        $(".sjw-nr-1").val("");
    });
    $("#sjw-bt-qx").click(function(){
        $("#sjw-rw-1").hide(1000);
    })
}

function loadNewTask(){
    $("#sjw-bt-tj").click(function(){
        var bt=$(".sjw-bt-1").val();
        var nr=$(".sjw-nr-1").val();
        if(bt=="") {alert("标题不可为空！");return ;}
        if(nr==""){alert("标题不可为空！");return ;}
        var url="../task/newTask";
        var data =$("#sjw-form-task").serialize();
        $.ajax({
            "url":url,
            "data":data,
            "type":"POST",
            "dataType":"json",
            "success":function(json){
                if(json.code == 0){
                    alert("添加成功！");
                    $("#sjw-rw-1").hide(1000);
                }else{
                    alert("添加失败！");
                }
            }
        })
    });
}

function loadFindTask(){

    var url="../task/findTask";
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                var data=json.data;
                for(var i=data.length-1;i>=0;i--){
                    var html='<li class="li-#{id}">'
                        +'<div class="admin-task-meta">Posted on #{createTime} by #{createName} to title is #{title}</div>'
                        +'<div class="admin-task-bd">'
                        +'#{content}'
                        +'</div>'
                        +'<div class="am-cf">'
                        +'<div class="am-btn-toolbar am-fl">'
                        +'<div class="am-btn-group am-btn-group-xs">'
                        +'<button type="button" class="am-btn am-btn-default"><span class="am-icon-check"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default"><span class="am-icon-pencil"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default"><span class="am-icon-times"></span></button>'
                        +'</div>'
                        +'</div>'
                        +'<div class="am-fr">'
                        +'<button type="button" class="am-btn am-btn-default am-btn-xs" onclick="scrw(#{id})">删除</button>'
                        +'</div>'
                        +'</div>'
                        +'</li>';

                    html=html.replace("#{id}",data[i].tid);
                    html=html.replace("#{createTime}",data[i].createTime);
                    html=html.replace("#{createName}",data[i].createBy);
                    html=html.replace("#{title}",data[i].title);
                    html=html.replace("#{content}",data[i].content);
                    html=html.replace("#{id}",data[i].tid);

                    $("#sjw-task-ul").append(html);
                }
            }else{
                alert("加载任务信息失败！");
            }
        }
    });
}


function scrw(tid) {
    alert(tid);
    var url="../task/removeTask?tid="+tid;
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                $(".li-"+tid).remove();
            }else{
                alert("删除失败！");
            }
        }
    })
}



