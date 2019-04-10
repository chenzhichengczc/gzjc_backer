$(function () {
    //加载全局页面
    loadTable();

})

//加载table页面
function loadTable() {
    var audit=3;
    var pageNum=1;
    var category=1;
    //表单弹框
    loadTask();
    //显示
    loadSelectAll(audit,category,pageNum);
    //添加网站
    loadFindTable();
    //
    loadUpdate();
    //
    loadAudit();
}

function loadTask(){
    $("#sjw-bt-table").click(function () {
        $("#sjw-rw-1").show(1000);
    });
    $("#sjw-bt-qx").click(function(){
        $("#sjw-rw-1").hide(1000);
    });
    $("#sjw-bt-ch").click(function(){
        $("#sjw-rw-2").hide(1000);
    })
}

function loadFindTable() {
    $("#sjw-bt-tj").click(function(){
        var bt=$(".sjw-bt-1").val();
        if(bt=="") {alert("请确保信息完整！");return ;}
        var url="../table/findTable";
        var data =$("#sjw-form-table").serialize();
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

function loadSelectAll(audit,category,pageNum) {
    var url="../table/selectAll?audit="+audit+"&category="+category+"&pageNum="+pageNum+"&pageSize=5";
    $.ajax({
        "url": url,
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.code == 0) {
                $("#sjw-table-tbody").html("");
                var data = json.data;
                for (var i = 0; i < data.list.length; i++) {
                    var html = '<tr class="sjw-tr tr-#{id}">'
                        + '<td><input type="checkbox" /></td>'
                        + '<td>#{id}</td>'
                        + '<td><a href="#{website}">#{title}</a></td>'
                        + '<td>#{category}</td>'
                        + '<td>#{author}</td>'
                        + '<td>#{Time}</td>'
                        + '<td>'
                        + '<div class="am-btn-toolbar">'
                        + '<div class="am-btn-group am-btn-group-xs">'
                        + '<button type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="loadUpdateTable(#{id})"><span class="am-icon-pencil-square-o"></span> 编辑</button>'
                        + '<button class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="loadRemoveTable(#{id})"><span class="am-icon-trash-o"></span> 删除</button>'
                        + '</div>'
                        + '</div>'
                        + '</td>'
                        + '<td>'
                        + '<select class="aditu-#{id}" onclick="">\n'
                        + ' <option value="0">已审核</option>\n'
                        + ' <option value="1">未审核</option>\n'
                        + ' <option value="2">未通过</option>\n'
                        + ' </select>'
                        + '</td>'
                        + '</tr>';
                    html = html.replace(/#{id}/g, data.list[i].tid);
                    html = html.replace("#{website}", data.list[i].website);
                    html = html.replace("#{title}", data.list[i].title);
                    html = html.replace("#{category}", data.list[i].category);
                    html = html.replace("#{author}", data.list[i].author);
                    html = html.replace("#{Time}", data.list[i].proUpdateTime);

                    $("#sjw-table-tbody").append(html);
                    $(".aditu-"+data.list[i].tid).val(data.list[i].audit);
                }
            }else {
                alert("添加失败！");
            }
        }
    });
}

function loadRemoveTable(tid) {
    alert(tid);
    var url="../table/removeTable?tid="+tid;
    $.ajax({
        "url":url,
        "type":"GET",
        "dataType":"json",
        "success":function(json){
            if(json.code == 0){
                $(".tr-"+tid).remove();
            }else{
                alert("删除失败！");
            }
        }
    })
}

function loadUpdateTable(tid) {
    $("#sjw-rw-2").show(1000);
    var url="../table/selectTable?tid="+tid;
    $.ajax({
        "url": url,
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.code == 0) {
                var data = json.data;
                $(".sjw-bt-2").val(data.title);
                $(".sjw-lb-2").val(data.category);
                $(".sjw-zz-2").val(data.author);
                $(".sjw-wz-2").val(data.website);
                $(".sjw-a").val(tid);
                $(".audit-sh").val(data.audit);
            }else {
                alert("添加失败！");
            }
        }
    });


}

function loadUpdate() {
    $("#sjw-bt-xg").click(function () {
        var tid = $("#sjw-bt-xg>a").val();
        var url="../table/updateTable?tid="+tid+"&title="+$(".sjw-bt-2").val()+"&category="+$(".sjw-lb-2").val()+"&author="+$(".sjw-zz-2").val()+"&website="+$(".sjw-wz-2").val()+"&audit="+$(".audit-sh").val();
        $.ajax({
            "url":url,
            "type":"GET",
            "dataType":"json",
            "success":function(json){
                if(json.code == 0){
                    alert("修改成功！");
                    $("#sjw-rw-2").hide(1000);
                    loadSelectAll(3,1,1);
                }else{
                    alert("修改失败！");
                }
            }
        });
    });
}

function loadAudit() {
    var audit = 3;
    var category=1;
    $("#audit-0").click(function () {
        audit = 0;
        loadSelectAll(audit,category,1);
    });
    $("#audit-1").click(function () {
        audit = 1;
        loadSelectAll(audit,category,1);
    });
    $("#sjw-category").change(function () {
        category=$("#sjw-category").val();
        loadSelectAll(audit,category,1);
    });
    $("#sjw-fy-a1").click(function () {
        loadSelectAll(audit,category,1);
    });
    $("#sjw-fy-a2").click(function () {
        loadSelectAll(audit,category,2);
    });
    $("#sjw-fy-a3").click(function () {
        loadSelectAll(audit,category,3);
    });
    $("#sjw-fy-a4").click(function () {
        loadSelectAll(audit,category,4);
    });
    $("#sjw-fy-a5").click(function () {
        loadSelectAll(audit,category,5);
    });
}
