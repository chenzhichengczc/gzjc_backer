$(function () {
    //加载全局页面
    loadUser();

})

//加载user页面
function loadUser() {
    //加载流量分析

    //修改个人信息
    loadUpdateAdmin();
    //回显个人信息
    loadFindAdmin();
    //修改头像
    loadChangeAvatar();
}

//修改个人信息
function loadUpdateAdmin() {
    $("#user-btn").click(function () {
        var url = '../admin/updateAdmin';
        var data = $("#user-form").serialize();
        $.ajax({
            "url": url,
            "type": "POST", //POST
            "data": data,
            "dataType": "json",    //返回的数据格式：json/xml/html/script/jsonp/text
            "success": function (json) {
                if (json.code == 0) {
                    alert("修改成功！");
                } else {
                    alert(json.message);
                }
            }
        })
    });
}

//回显个人信息
function loadFindAdmin() {
    var url = '../admin/findByAdmin';
    $.ajax({
        "url": url,
        "type": "GET", //GET
        "dataType": "json",    //返回的数据格式：json/xml/html/script/jsonp/text
        "success": function (json) {
            if (json.code == 0) {
                var data = json.data;
                $("#user-username").val(data.username);
                $("#user-name").val(data.name);
                $("#user-email").val(data.email);
                $("#user-phone").val(data.telephone);
                $("#user-QQ").val(data.qq);
                $("#user-weibo").val(data.authority);
                $("#user-intro").val(data.intro);
            } else {
                alert(json.message);
            }
        }
    })
}

//修改头像
function loadChangeAvatar() {
    $(document).ready(function() {
        if ($.cookie("avatar") != null) {
            $("#img-avatar").attr("src","../static"+$.cookie("avatar"));
        }
    });

    $("#btn-change-avatar").click(function(){
        var url="../admin/changeAvatar";
        var data =new FormData($("#form-change-avatar")[0]);
        alert(data);
        $.ajax({
            "url":url,
            "data":data,
            "type":"POST",
            "dataType":"json",
            "contentType":false,
            "processData":false,
            "success":function(json){
                if(json.code == 0){
                    var avatarUrl = json.data;
                    $("#img-avatar").attr("src","../static"+avatarUrl);
                    // alert("头像修改成功！"+avatarUrl);
                    $("#form-change-avatar")[0].reset();
                    $.cookie("avatar", avatarUrl, {expires: 7 });
                }else{
                    alert(json.message);
                }
            }
        })
    });
}