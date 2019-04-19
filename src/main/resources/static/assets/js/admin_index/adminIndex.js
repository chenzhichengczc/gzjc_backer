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
    //加载执行人成员
    loadFindExecutor();
    //添加任务
    loadNewTask();
    //加载任务信息
    loadFindTask();
    //加载留言信息
    loadFindLeave();

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
    $("#sjw-bt-leave").click(function () {
        $("#sjw-ly-1").show(1000);
        $(".sjw-lynr-1").val("");
    });
    $("#sjw-bt-task").click(function () {
        $("#sjw-rw-1").show(1000);
        $(".sjw-bt-1").val("");
        $(".sjw-nr-1").val("");
    });
    $("#sjw-bt-qx").click(function(){
        $("#sjw-rw-1").hide(1000);
    })
    $("#sjw-bt-qx2").click(function(){
        $("#sjw-rw-2").hide(1000);
    })
    $("#sjw-bt-qx3").click(function(){
        $("#sjw-ly-1").hide(1000);
    })
    $("#sjw-bt-tj2").click(function () {
        loadUpdate();
    })

    $("#sjw-date-task").change(function () {
        var date=$("#sjw-date-task").val();
        console.log(date);
    })

    $("#sjw-date-a").click(function () {
        var time=$("#sjw-date-task").val();
        var content = $("#sjw-text-task").val();
        console.log("time"+time);
        console.log("content"+content);
        loadSelectTaskMap(time,content);
    })

    $("#sjw-bt-ly").click(function () {
        newly();
    })

    $("#sjw-leave-a").click(function () {
        loadFindLeave();
    })
}

function loadFindExecutor() {
    var url = "../task/findAdmin";
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                var data=json.data;
                for (var i=0;i<data.length;i++){
                    var html='<option value="#{email}">#{name}</option>';
                    html = html.replace("#{email}",data[i].email);
                    html = html.replace("#{name}",data[i].name);

                    $("#sjw-executor").append(html);
                    $("#sjw-executor2").append(html);
                }
            }else{
                alert("添加失败！");
            }
        }
    })
}

function loadNewTask(){
    $("#sjw-bt-tj").click(function(){
        var bt=$(".sjw-bt-1").val();
        var nr=$(".sjw-nr-1").val();
        var jb=$(".sjw-select-level").val();
        if(bt=="") {alert("标题不可为空！");return ;}
        if(nr==""){alert("内容不可为空！");return ;}
        if(jb==0){alert("级别不可为空！");return ;}
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
                    parent.location.reload();
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
                    var html='<li class="li-#{id}"><a name="#{createTime}"></a>'
                        +'<div class="admin-task-meta">日期: #{createTime} 发布人: #{createName} @#{exename}</div>'
                        +'<div class="admin-task-bd">'
                        +'任务模块: #{title}'
                        +'</div>'
                        +'<div class="admin-task-bd">'
                        +'#{content}'
                        +'</div>'
                        +'<div class="admin-task-bd">'
                        +'---------------------------最后期限：#{deadline}'
                        +'</div>'
                        +'<div class="am-cf">'
                        +'<div class="am-btn-toolbar am-fl">'
                        +'<div class="am-btn-group am-btn-group-xs">'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgzt(#{id},0)"><span class="am-icon-check"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgrw(#{id})"><span class="am-icon-pencil"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgzt(#{id},1)"><span class="am-icon-times"></span></button>'
                        +'</div>'
                        +'</div>'
                        +'<div class="am-fr">'
                        +'<button type="button" class="am-btn am-btn-default am-btn-xs" onclick="scrw(#{id})">删除</button>'
                        +'</div>'
                        +'<div class="am-fl">'
                        +'<div class="sjw-level-#{level}"></div>'
                        +'<div class="sjw-complete-#{complete}">√</div>'
                        +'</div>'
                        +'</div>'
                        +'</li>';

                    html=html.replace(/#{id}/g,data[i].tid);
                    html=html.replace("#{createTime}",data[i].createTime.substring(0,10));
                    html=html.replace("#{createTime}",data[i].createTime);
                    html=html.replace("#{createName}",data[i].createBy);
                    html=html.replace("#{title}",data[i].title);
                    html=html.replace("#{exename}",data[i].exename);
                    html=html.replace("#{content}",data[i].content);
                    html=html.replace("#{deadline}",data[i].deadline!=null?data[i].deadline:"无期限");
                    html=html.replace("#{level}",data[i].level);
                    html=html.replace("#{complete}",data[i].complete);
                    $("#sjw-task-ul").append(html);
                }
            }else{
                alert("加载任务信息失败！");
            }
        }
    });
}


function scrw(tid) {
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

function xgrw(tid) {
    $("#sjw-rw-2").show();
    var url="../task/findById?tid="+tid;
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                var data=json.data;
                $("#sjw-form-task2 .sjw-bt-1").val(data.title);
                $("#sjw-form-task2 .sjw-nr-1").val(data.content);
                $("#sjw-form-task2 .sjw-select-level").val(data.level);
                $("#sjw-form-task2 .sjw-select-executor").val(data.executor);
                $("#sjw-form-task2 .sjw-disp").val(tid);
            }else{
                alert("显示失败！");
            }
        }
    });
}

function xgzt(tid,complete) {
    var url="../task/updateByTask";
    $.ajax({
        "url":url,
        "data":{
            tid:tid,
            complete:complete
        },
        "type":"POST",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                parent.location.reload();
            }else{
                alert("修改失败！");
            }
        }
    })
}

function loadUpdate(){
    var url="../task/updateByTask";
    var data =$("#sjw-form-task2").serialize();
    $.ajax({
        "url":url,
        "data":data,
        "type":"POST",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                alert("修改成功！");
                $("#sjw-rw-2").hide(1000);
                parent.location.reload();
            }else{
                alert("修改失败！");
            }
        }
    })
}


function loadSelectTaskMap(time,content) {
    var url="../task/selectTaskMap?time="+time+"&content="+content;
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                $("#sjw-task-ul-ul").html("");
                var data=json.data;
                for(var i=data.length-1;i>=0;i--){
                    var html='<li class="li-#{id}"><a name="#{createTime}"></a>'
                        +'<div class="admin-task-meta">日期: #{createTime} 发布人: #{createName} @#{exename}</div>'
                        +'<div class="admin-task-bd">'
                        +'任务模块: #{title}'
                        +'</div>'
                        +'<div class="admin-task-bd">'
                        +'#{content}'
                        +'</div>'
                        +'<div class="admin-task-bd">'
                        +'---------------------------最后期限：#{deadline}'
                        +'</div>'
                        +'<div class="am-cf">'
                        +'<div class="am-btn-toolbar am-fl">'
                        +'<div class="am-btn-group am-btn-group-xs">'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgzt(#{id},0)"><span class="am-icon-check"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgrw(#{id})"><span class="am-icon-pencil"></span></button>'
                        +'<button type="button" class="am-btn am-btn-default" onclick="xgzt(#{id},1)"><span class="am-icon-times"></span></button>'
                        +'</div>'
                        +'</div>'
                        +'<div class="am-fr">'
                        +'<button type="button" class="am-btn am-btn-default am-btn-xs" onclick="scrw(#{id})">删除</button>'
                        +'</div>'
                        +'<div class="am-fl">'
                        +'<div class="sjw-level-#{level}"></div>'
                        +'<div class="sjw-complete-#{complete}">√</div>'
                        +'</div>'
                        +'</div>'
                        +'</li>';

                    html=html.replace(/#{id}/g,data[i].tid);
                    html=html.replace("#{createTime}",data[i].createTime.substring(0,10));
                    html=html.replace("#{createTime}",data[i].createTime);
                    html=html.replace("#{createName}",data[i].createBy);
                    html=html.replace("#{title}",data[i].title);
                    html=html.replace("#{exename}",data[i].exename);
                    html=html.replace("#{content}",data[i].content);
                    html=html.replace("#{deadline}",data[i].deadline!=null?data[i].deadline:"无期限");
                    html=html.replace("#{level}",data[i].level);
                    html=html.replace("#{complete}",data[i].complete);
                    $("#sjw-task-ul-ul").append(html);
                }
                var p="<p class='sjw-ul-p'>以上为搜索的全部信息</p>";
                $("#sjw-task-ul-ul").append(p);
            }else{
                alert("加载任务信息失败！");
            }
        }
    });
}


function newly(){
    var url = "../leave/newLeave";
    var data = $("#sjw-form-leave").serialize();
    $.ajax({
        "url":url,
        "data":data,
        "type":"POST",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                $("#sjw-ly-1").hide(1000);
                loadFindLeave();
            }else{
                alert("留言失败！");
            }
        }
    })

}

function loadFindLeave(){
    var time = $("#sjw-date-leave").val();
    var content = $("#sjw-text-leave").val();
    var url = "../leave/findLeaves?time="+time+"&content="+content;
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                $("#sjw-ul-leave").html("");
                var data = json.data;
                for(var i = 0;i<data.length;i++){
                    var html = '<li class="am-comment">'
                        +'<a href="#"><img src="../static#{img}" alt="" class="am-comment-avatar" width="48" height="48"></a>'
                        +'<div class="am-comment-main">'
                        +'<header class="am-comment-hd">'
                        +'<div class="am-comment-meta"><a href="#" class="am-comment-author">#{lname}</a> 评论于 <time>#{create_time}</time></div>'
                        +'</header>'
                        +'<div class="am-comment-bd"><p>#{content}</p>'
                        +'</div>'
                        +'</div>'
                        +'</li>';
                    html = html.replace("#{img}",data[i].headPortrait);
                    html = html.replace("#{lname}",data[i].lname);
                    html = html.replace("#{create_time}",data[i].createTime);
                    html = html.replace("#{content}",data[i].content);

                    $("#sjw-ul-leave").append(html);
                }
                $(".sjw-leave-div").scrollTop($(".sjw-leave-div")[0].scrollHeight);
            }else{
                alert("失败！");
            }
        }
    })
}


