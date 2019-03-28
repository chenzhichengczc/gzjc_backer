/**
 * 非空校验
 * @param value 传入的是对象的值
 */
function notNull(value) {
    if(value == null || value == "" || value == undefined){
        return false;
    }
    return true;
}

$(function () {

    $(".button").click(function () {
        if(notNull($("#username").val()) && notNull($("#password").val())){
            var data = {"username":$("#username").val(),"password":$("#password").val()}
            $.post("../admin/loginAdmin",data,function (result) {
                if(result.code == 0){
                    alert("登录成功")
                    //3秒后跳转过去
                    window.setTimeout(window.location.href="admin-index",3000)
                }else {
                    alert("登录管理者失败,请联系开发者")
                }
            },"json")
        }else {
            alert("信息为空,请确认!")
            return;
        }
    })
})


